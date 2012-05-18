package com.chiter.dex;

import opennlp.tools.tokenize.*;

public class OpenNLPTester {

	public static void main(String[] args) 
	{
		TestTokenizer();
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
