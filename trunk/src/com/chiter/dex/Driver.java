package com.chiter.dex;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Driver {
	
	public static void main (String[] args) {

		String searchString = args[0];
		searchString = OpenNLPTester.removeStopWordsAndStem(searchString);
		System.out.println(searchString);
		List<String> documents = new ArrayList<String>();
		try {
			FileInputStream fstream = new FileInputStream("textfile.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				documents.add(strLine);
			}
			//Close the input stream
			in.close();
		} catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

		TfIdfManager manager = new TfIdfManager();
		List<TfIdfResponse> responseList = manager.calculateTfIdf(searchString, documents);

		// sort this thing
		Collections.sort(responseList);

		System.out.println("---------------------------------------------------------");
		// TODO: return results
		for (TfIdfResponse response : responseList) {
			System.out.println(response);
		}
	}
}