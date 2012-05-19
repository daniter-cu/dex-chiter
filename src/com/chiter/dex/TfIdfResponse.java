package com.chiter.dex;

public class TfIdfResponse implements Comparable<TfIdfResponse> {

	private double tfIdfValue;
	private String content;

	public TfIdfResponse() {
		content = "";
		tfIdfValue = 0.0;
	}

	public TfIdfResponse(final String content, final double tfIdfValue) {
		this.content = content;
		this.tfIdfValue = tfIdfValue;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public void setTfIdfValue(final double tfIdfValue) {
		this.tfIdfValue = tfIdfValue;
	}

	public double getTfIdfValue() {
		return tfIdfValue;
	}
	
	public String getContent() {
		return content;
	}

	@Override
	public int compareTo(TfIdfResponse response) {

		if (this.tfIdfValue > response.getTfIdfValue()) {
			return -1;
		}
		else if (this.tfIdfValue == response.getTfIdfValue()) {
			return 0;
		}
		else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return String.format("Content [%s], tfidf [%f]", content, tfIdfValue);
	}
}
