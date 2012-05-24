package com.chiter.dex;

import java.util.List;

import com.chiter.dex.tfidf.TfIdfManager;
import com.chiter.dex.tfidf.TfIdfResponse;

public class Driver {
	
	public static void main (String[] args) {
		//TODO: Change to get searchString from file
		//TODO: Do to do 
		if (args.length <= 0) {
			System.out.println("Enter a search string via command line arguments");
			System.exit(1);
		}
		String searchString = args[0];
		
		TfIdfManager tfIdfManager = new TfIdfManager("corpora/corperaplusplus.txt", searchString);
		tfIdfManager.execute();
		
		// print non zero tfidf results
		List<TfIdfResponse> nonZeroResponseList = tfIdfManager.getSortedNonZeroResults();
		
		Driver.printResponse(nonZeroResponseList);
	}
	
	private static void printResponse(List<TfIdfResponse> responseList) 
	{
		for(int i=0; i<responseList.size(); i++)
		{
			System.out.println(responseList.get(i));
			if(i+1 != responseList.size() && responseList.get(i+1).getTfIdfValue() != responseList.get(i).getTfIdfValue())
			{
				System.out.println("____________________________________________________________________");
			}
		}
	}
	
}