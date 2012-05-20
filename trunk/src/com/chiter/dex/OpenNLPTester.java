package com.chiter.dex;

import java.io.*;
import java.util.*;
import opennlp.tools.tokenize.*;
import opennlp.tools.tokenize.Tokenizer;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.*;
import org.apache.lucene.util.*;

public class OpenNLPTester {

	public static void main(String[] args) 
	{
		//TestTokenizer();
		TestStemming();
	}

	private static void TestStemming() 
	{
		String s = "I am the man and they, are were is going, to the parks working grocery groceries!";
		try{
			System.out.println(removeStopWordsAndStem(s));
		}
		catch(Exception e)
		{
			//do ntohing
		}
	}

	public static String removeStopWordsAndStem(String input) throws IOException {
	    Set<String> stopWords = new HashSet<String>();
	    stopWords.add("a");
	    stopWords.add("I");
	    stopWords.add("the");

	    TokenStream tokenStream = new StandardTokenizer(
	            Version.LUCENE_36, new StringReader(input));
	    tokenStream = new StopFilter(Version.LUCENE_36, tokenStream, stopWords);
	    tokenStream = new PorterStemFilter(tokenStream);

	    StringBuilder sb = new StringBuilder();
	    CharTermAttribute termAttr = tokenStream.getAttribute(CharTermAttribute.class);
	    while (tokenStream.incrementToken()) {
	        if (sb.length() > 0) {
	            sb.append(" ");
	        }
	        sb.append(termAttr.toString());
	    }
	    return sb.toString();
	}

	private static void TestTokenizer() 
	{
		Tokenizer t = SimpleTokenizer.INSTANCE;
		// Splits 12th -> '12' and 'th'
		String[] tokens = t.tokenize("On Nov. 12th 1912 this some bullshit started. For real, this is some business.");
		for (String s: tokens)
		{
			System.out.println(s);
		}
	}

}
