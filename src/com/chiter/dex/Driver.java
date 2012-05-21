package com.chiter.dex;

import java.util.List;

import com.chiter.dex.tfidf.TfIdfManager;
import com.chiter.dex.tfidf.TfIdfResponse;

public class Driver {
	
	public static void main (String[] args) {
		String searchString = args[0];
		
		TfIdfManager tfIdfManager = new TfIdfManager();
		List<TfIdfResponse> responseList = tfIdfManager.getSortedResults("textfile.txt", searchString);
		
		Driver.printResponse(responseList);
	}
	
	private static void printResponse(List<TfIdfResponse> responseList) {
		for (TfIdfResponse response : responseList) {
			System.out.println(response);
		}
	}
	
}