package org.vikram;

public class WordStat {
    long apiCalls,
         wordFreq;

    public WordStat(long apiCalls, long wordFreq) {
    	this.apiCalls = apiCalls;
    	this.wordFreq = wordFreq;
    }

    public WordStat copy() {
    	return new WordStat(this.apiCalls, this.wordFreq);
    }

}