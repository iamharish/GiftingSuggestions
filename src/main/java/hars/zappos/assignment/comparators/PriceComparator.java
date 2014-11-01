package hars.zappos.assignment.comparators;

import hars.zappos.assignment.domains.SuggestedResult;

import java.util.Comparator;


public class PriceComparator implements Comparator<SuggestedResult> {

	@Override
	public int compare(SuggestedResult o1, SuggestedResult o2) {
		// TODO Auto-generated method stub
		if(!(o1 instanceof SuggestedResult) 
				&& !(o2 instanceof SuggestedResult)){
			//TODO handle error
			return 0;
		}
		return (int) (((SuggestedResult)o2).getPrice() - ((SuggestedResult)o1).getPrice());
	}

}
