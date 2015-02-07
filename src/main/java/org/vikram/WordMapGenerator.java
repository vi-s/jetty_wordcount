package org.vikram;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class WordMapGenerator {
	private HashMap<String, WordStat> wordStatMap;
	private String prjDir, resDir;

	public WordMapGenerator() {
		this.wordStatMap = new HashMap<String, WordStat>();
		this.prjDir = System.getProperty("user.dir");
		this.resDir = prjDir + "/resources";
	}

	public HashMap<String, WordStat> generateMap() {
		this.wordStatMap = new HashMap<String, WordStat>();
		this.traverseParseDirectory(new File(this.resDir));

		return this.wordStatMap;
	}

	private void traverseParseDirectory(File folder) {
		File[] files = folder.listFiles();
		for (File f : files) {
			if (f.getName().matches("(.*)\\.txt$")) {
				this.parseFile(f);
			}
		}
	}

	private void parseFile(File file) {
		try {
			Scanner s = new Scanner(file);
			while (s.hasNextLine()) 
				this.parseLine(s.nextLine());
	
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
	}

	private void parseLine(String line) {
		int pos = 0,
			start = 0;
		String nonDelimRe = "[A-Za-z0-9]";

		while (pos < line.length()) {
			while (pos < line.length() 
				&& (""+line.charAt(pos)).matches(nonDelimRe)) {
				pos += 1;
			}

			String word = line.substring(start, pos).toUpperCase();
			this.processWord(word);

			start = pos + 1;
			pos = start;
		}

	}

	private void processWord(String word) {
		if(word.length() > 1) {
			WordStat stat = this.wordStatMap.get(word),
					newStat;

			if (stat != null) {
				newStat = new WordStat(stat.apiCalls, stat.wordFreq + 1);
			} else {
				newStat = new WordStat(0, 1);
			}
		
			this.wordStatMap.put(word, newStat);
		}
	}

}
