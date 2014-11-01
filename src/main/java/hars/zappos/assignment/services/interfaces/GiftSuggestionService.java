package hars.zappos.assignment.services.interfaces;

import hars.zappos.assignment.domains.GiftSuggestionParams;
import hars.zappos.assignment.domains.SuggestedResult;

import java.util.SortedSet;

public interface GiftSuggestionService {

	public SortedSet<SuggestedResult> getSuggestedResults(GiftSuggestionParams params)
			throws Exception;
}
