package com.chiter.dex.tfidf;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chiter.dex.nlp.OpenNLPTester;

public class TfIdfManager {
	
	public List<TfIdfResponse> getSortedResults(String file, String searchString) {		

		List<String> documents = readFromFile(file);
		
		TfIdfCalculator manager = new TfIdfCalculator();
		List<TfIdfResponse> responseList = manager.calculateTfIdf(OpenNLPTester.removeStopWordsAndStem(searchString), documents);

		// sort this thing
		Collections.sort(responseList);
		
		return responseList;
	}
	
	private List<String> readFromFile(String file) {
		List<String> documents = new ArrayList<String>();
		try {
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				documents.add(strLine);
			}
			//Close the input stream
			br.close();
			in.close();
			fstream.close();
			
		} catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return documents;
	}
}
