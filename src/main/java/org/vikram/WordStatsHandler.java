package org.vikram;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import java.util.HashMap;

public class WordStatsHandler extends AbstractHandler {
	private HashMap<String, WordStat> wordStatMap;

	public WordStatsHandler(HashMap<String, WordStat> map) {
		this.wordStatMap = map;
	}

    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        String wordParam = request.getParameter("word").toUpperCase();
        WordStat stat = this.wordStatMap.get(wordParam),
				 newStat;

        if ( stat == null ) {
        	newStat = new WordStat(1, 0);
        } else {
        	newStat = new WordStat(stat.apiCalls + 1, stat.wordFreq);
        }

        this.wordStatMap.put(wordParam, newStat);
        response.getWriter().print(this.generateJsonStr(newStat));
        baseRequest.setHandled(true);
    }

    private String generateJsonStr(WordStat stat) {
        return String.format(
            "{\"api_calls\": \"%d\", \"word_frequency\": \"%d\"}",
            stat.apiCalls, stat.wordFreq);
    }

}