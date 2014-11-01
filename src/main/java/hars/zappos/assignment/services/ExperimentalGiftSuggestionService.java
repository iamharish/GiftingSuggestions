package hars.zappos.assignment.services;

import hars.zappos.assignment.comparators.PriceComparator;
import hars.zappos.assignment.domains.GiftSuggestionParams;
import hars.zappos.assignment.domains.Product;
import hars.zappos.assignment.domains.SuggestedResult;
import hars.zappos.assignment.services.interfaces.GiftSuggestionService;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ExperimentalGiftSuggestionService implements GiftSuggestionService {

	@Autowired
	private ZapposApiCallHelper apiCallHelper;
	@Autowired
	private Environment env;
	private Logger logger = Logger.getLogger(ExperimentalGiftSuggestionService.class);
	private final int[] ranges = {50, 100, 200, 200};
	private Random random;
	

	@Override
	public SortedSet<SuggestedResult> getSuggestedResults(GiftSuggestionParams params) throws Exception {
		SuggestedResult result = new SuggestedResult();
		random = new Random();
		SortedSet<SuggestedResult> suggestions = new TreeSet<SuggestedResult>(new PriceComparator());
		String filters = getFilters(params);
		StringBuilder uri = new StringBuilder(env.getProperty("searchApi"));
		if(apiCallHelper.execute(uri.toString(), filters)){
			List<Product> product = apiCallHelper.getResults();
			int minSuggestions = Integer.parseInt(env.getProperty("minSuggestions"));
			int maxCount = 0;
			while(minSuggestions > suggestions.size() && maxCount < 10){
				maxCount++;
				logger.debug("Attempt "+maxCount);
				result = addProducts(params.getMaxBudget(), product, params.getNumOfGifts());
				suggestions.add(result);
			}
		}
		return suggestions;
	}
	
	private String getFilters(GiftSuggestionParams params){
		logger.debug("entering getFilters method");
		StringBuilder filters = new StringBuilder();
		double budget = params.getMaxBudget();
		int giftsCount = params.getNumOfGifts();
		/*for(int i=0;i<ranges.length;i++){
			
			if(budget/giftsCount <=  ranges[i]){
				filters.append(env.getProperty("filter"+ranges[i]+"AndUnder"));
				logger.info("Filters used "+filters.toString());
				return filters.toString();
			}
			
		}*/
		return null;
	}
	
	private boolean isSortFilterNeeded(double budget, int count, int range){
		String filter = null;
		if(budget/count <=  range){
			//budget is very low. apply asc sort filter
			return true;
		}	
		return false;
	}
	
	private SuggestedResult addProducts(double maxPrice, List<Product> products, int count){
		SuggestedResult suggestedResult = new SuggestedResult();
		double price = 0;
		int maxSearchCount = 0;
		logger.debug(maxPrice+" "+price+" "+count);
		logger.debug("products "+products);
		logger.debug(maxPrice < price && count < 9);
		while(maxPrice > price && maxSearchCount < 9 && suggestedResult.getProducts().size() < count){
			logger.debug("battle begins");
			maxSearchCount++;
			Product product = getRandomProduct(products);
			logger.debug("Validating if a product "+product+" can be added when price is "+price+" and maxPrice is "+maxPrice);
			if(maxPrice > product.getPriceAsDouble()+price){
				logger.info("Adding product"+product);
				suggestedResult.getProducts().add(product);
				suggestedResult.setPrice(price += product.getPriceAsDouble());
			}
		}
		return suggestedResult;
	}
	
	private Product getRandomProduct(List<Product> products){
		int size = products.size();
		int index = random.nextInt(size); 
		logger.debug("Picking product at "+index);
		return products.get(index);
	}

}
