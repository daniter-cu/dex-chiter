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
		

		TfIdfManager manager = new TfIdfManager();
		List<TfIdfResponse> responseList = manager.calculateTfIdf(searchString, documents);

		// TODO: sort this thing
		// TODO: return results
		
	}

}
