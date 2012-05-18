package com.chiter.dex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Driver {


	public static void main (String[] args) {

		//useless comment
		String searchString = "What is my list of grocery ?";

		List<String> documents = new ArrayList<String>();

		String testDocuments1 = "Remember my car mechanic's number : 206-555-2222";
		String testDocuments2 = "Just met this girl named Jane";
		String testDocuments3 = "Here is a song that I like : Baby baby";
		String testDocuments4 = "Remind me to buy eggs and bagels for my grocery list";
		String testDocuments5 = "Add ham to my grocery list";
		String testDocuments6 = "Add bread to the grocery list";
		String testDocuments7 = "I need toilet paper in my grocery";
		
		documents.add(testDocuments1);
		documents.add(testDocuments2);
		documents.add(testDocuments3);
		documents.add(testDocuments4);
		documents.add(testDocuments5);
		documents.add(testDocuments6);
		documents.add(testDocuments7);
		
		Map<String, Double> idf = new HashMap<String, Double>();

		Set<String> searchSet = new HashSet<String>();
		
		// Put search string into a set
		String[] splitSearchString = searchString.split("\\s+");
		for (String searchWord : splitSearchString) {
			searchSet.add(searchWord);
		}
		
		for (String document : documents) {
				
			// Put document words in a set
			Set<String> documentSet = new HashSet<String>();
			String[] splitString = document.split("\\s+");
			for (String s : splitString) {
				documentSet.add(s);
			}
			
			// Iterate through search set
			Iterator<String> it = searchSet.iterator();
			while (it.hasNext()) {
				
				String currentWord = (String) it.next();
				
				if (!idf.containsKey(currentWord)) {
					idf.put(currentWord, 0.0);
				} 
				if (documentSet.contains(currentWord)) {
					idf.put(currentWord, idf.get(currentWord) + 1.0);
				}
			}
		}
		
		for (Map.Entry<String, Double> entry : idf.entrySet()) {
			if (entry.getValue() > 0.0) {

				idf.put(entry.getKey(), Math.log((double)documents.size()/entry.getValue()));
			}
		}
		
		System.out.println(printMap(idf));

		double[] documentResults = new double[documents.size()];
		
		for (int i = 0; i < documents.size(); i++) {

			Map<String, Double> tf = new HashMap<String, Double>();
			for (String searchWord : splitSearchString) {

				if (!tf.containsKey(searchWord)) {
					tf.put(searchWord, 0.0);
				}

				String[] splitString = documents.get(i).split("\\s+");
				for (String s : splitString) {

					if (searchWord.equals(s)) {
						tf.put(s, tf.get(s) + 1.0);

					}					
				}
			}
			
			
			Double sum = 0.0;
			for (Map.Entry<String, Double> entry : idf.entrySet()) {
				Double termFrequency = tf.get(entry.getKey());
				Double inverseDocumentFrequency = idf.get(entry.getKey());
				
				sum = sum + termFrequency * inverseDocumentFrequency;
			}
			
			documentResults[i] = sum;
			
		}


		for (int i = 0; i < documentResults.length; i++) {
			System.out.println("i: " + i + " tfidf: " + documentResults[i]);
		}
	}

	private static String printMap(Map<String, Double> map) {
		String s = "";
		for (Map.Entry<String, Double> entry : map.entrySet()) {
			s = s + "Key: " + entry.getKey() + " Value: " + entry.getValue() + "\n";
		}

		return s;
	}
}
