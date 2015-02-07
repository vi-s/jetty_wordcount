package org.vikram;
 
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {
        WordMapGenerator wg = new WordMapGenerator();
        HashMap<String, WordStat> wordStatMap = wg.generateMap();
        Server server = new Server(8080);

        ContextHandler ctx = new ContextHandler();
        ctx.setContextPath("/wordstats");
        ctx.setHandler( new WordStatsHandler(wordStatMap) );

        server.setHandler( ctx );
  
        server.start();
        server.join();
    }
}
