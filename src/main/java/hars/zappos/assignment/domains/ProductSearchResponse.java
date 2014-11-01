package hars.zappos.assignment.domains;

import com.fasterxml.jackson.databind.JsonNode;

public class ProductSearchResponse {
	private int statusCode;
	private JsonNode results;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public JsonNode getResults() {
		return results;
	}
	public void setResults(JsonNode results) {
		this.results = results;
	}
}
