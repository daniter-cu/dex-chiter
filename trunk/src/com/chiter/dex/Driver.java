package com.chiter.dex;

import java.util.List;

import com.chiter.dex.tfidf.TfIdfManager;
import com.chiter.dex.tfidf.TfIdfResponse;

public class Driver {
	
	public static void main (String[] args) {
		//TODO: Change to get searchString from file
		String searchString = args[0];
		
		TfIdfManager tfIdfManager = new TfIdfManager("corpus.txt", searchString);
		tfIdfManager.execute();
		
		// print non zero tfidf results
		List<TfIdfResponse> nonZeroResponseList = tfIdfManager.getSortedNonZeroResults();
		
		Driver.printResponse(nonZeroResponseList);
	}
	
	private static void printResponse(List<TfIdfResponse> responseList) {
		for (TfIdfResponse response : responseList) {
			System.out.println(response);
		}
	}
	
}