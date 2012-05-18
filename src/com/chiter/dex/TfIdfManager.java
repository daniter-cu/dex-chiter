package com.chiter.dex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TfIdfManager {

	public List<TfIdfResponse> calculateTfIdf(String searchString, List<String> documents) {
		
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

		List<TfIdfResponse> documentResults = new ArrayList<TfIdfResponse>();
		
		for (String document : documents) {

			Map<String, Double> tf = new HashMap<String, Double>();
			for (String searchWord : splitSearchString) {

				if (!tf.containsKey(searchWord)) {
					tf.put(searchWord, 0.0);
				}

				String[] splitString = document.split("\\s+");
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
			
			
			TfIdfResponse response = new TfIdfResponse(document, sum);
			documentResults.add(response);
			
		}

		return documentResults;
	}
	
	
	
}
