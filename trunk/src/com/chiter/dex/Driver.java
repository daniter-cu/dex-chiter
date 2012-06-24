package com.chiter.dex;

import java.util.List;
import java.util.Scanner;

import com.chiter.dex.tfidf.TfIdfManager;
import com.chiter.dex.tfidf.TfIdfResponse;

public class Driver {
	
	public static void main (String[] args) {
		//TODO: Change to get searchString from file
		//TODO: Do to do
		String searchString;
		
		while(true)
		{
			if (args.length <= 0) {
				System.out.println("Enter a search string");
				//System.exit(1);
				Scanner in = new Scanner(System.in);
				searchString = in.nextLine();
			}
			else{
				searchString = args[0];
			}
			
			TfIdfManager tfIdfManager = new TfIdfManager("corpora/corpus7", searchString);
			tfIdfManager.execute();
			
			// print non zero tfidf results
			List<TfIdfResponse> nonZeroResponseList = tfIdfManager.getSortedNonZeroResults();
			
			Driver.printResponse(nonZeroResponseList);
		}
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