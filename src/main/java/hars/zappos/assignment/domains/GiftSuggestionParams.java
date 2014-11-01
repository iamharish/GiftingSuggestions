package hars.zappos.assignment.domains;

public class GiftSuggestionParams {
	private int numOfGifts;
	private double maxBudget;
	
	public GiftSuggestionParams(String numOfGifts, String maxBudget){
		this.maxBudget = Double.parseDouble(maxBudget);
		this.numOfGifts = Integer.parseInt(numOfGifts);
	}
	
	public int getNumOfGifts() {
		return numOfGifts;
	}
	public void setNumOfGifts(int numOfGifts) {
		this.numOfGifts = numOfGifts;
	}
	public double getMaxBudget() {
		return maxBudget;
	}
	public void setMaxBudget(double maxBudget) {
		this.maxBudget = maxBudget;
	}

}
