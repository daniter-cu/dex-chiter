package com.chiter.dex.nlp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;

import org.apache.lucene.analysis.PorterStemFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

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

	public static String removeStopWordsAndStem(String input) {
	    Set<String> stopWords = initStopWordSet();

	    input = input.toLowerCase();
	    TokenStream tokenStream = new StandardTokenizer(
	            Version.LUCENE_36, new StringReader(input));
	    tokenStream = new StopFilter(Version.LUCENE_36, tokenStream, stopWords);
	    tokenStream = new PorterStemFilter(tokenStream);

	    StringBuilder sb = new StringBuilder();
	    CharTermAttribute termAttr = tokenStream.getAttribute(CharTermAttribute.class);
	    try {
			while (tokenStream.incrementToken()) {
			    if (sb.length() > 0) {
			        sb.append(" ");
			    }
			    sb.append(termAttr.toString());
			}
		} catch (IOException e) {
			System.err.println("Tokens are messed up");
			e.printStackTrace();
		}
	    return sb.toString();
	}

	private static Set<String> initStopWordSet() 
	{
		Set<String> list = new HashSet<String>();
		try {
			FileInputStream fstream = new FileInputStream("stopwords.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				list.add(strLine);
			}
			//Close the input stream
			in.close();
		} catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

		return list;
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
