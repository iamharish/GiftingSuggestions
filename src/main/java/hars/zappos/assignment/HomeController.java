package hars.zappos.assignment;

import hars.zappos.assignment.domains.GiftSuggestionParams;
import hars.zappos.assignment.domains.SuggestedResult;
import hars.zappos.assignment.services.interfaces.GiftSuggestionService;

import java.util.Locale;
import java.util.SortedSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private GiftSuggestionService giftSuggestionService;
	//@Autowired
	//private HttpMessageConverter jsonHttpMessageConverter;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		/*try {
			//giftSuggestionService.getSuggestedResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );*/
		
		return "home";
	}
	
	@RequestMapping(value={"/suggestedResults/{items}/{budget}"}, method=RequestMethod.GET)
	public @ResponseBody SortedSet<SuggestedResult> createSuggestions(Locale locale, Model model, 
			@PathVariable(value="items") final String numOfGifts, 
			@PathVariable(value="budget") final String maxBudget){
		GiftSuggestionParams params = 
				new GiftSuggestionParams(numOfGifts, maxBudget);
		try {
			return giftSuggestionService.getSuggestedResults(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testRestCall(Locale locale, Model model){
        /*RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

        // Add the Jackson Message converter
        ((MappingJacksonHttpMessageConverter) jsonHttpMessageConverter).getObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        messageConverters.add(jsonHttpMessageConverter);

        // Add the message converters to the restTemplate
        restTemplate.setMessageConverters(messageConverters);

        Page page = restTemplate.getForObject("http://graph.facebook.com/pivotalsoftware", Page.class);
        System.out.println("Name:    " + page.getName());
        System.out.println("About:   " + page.getAbout());
        System.out.println("Phone:   " + page.getPhone());
        System.out.println("Website: " + page.getWebsite());
        model.addAttribute("serverTime", page.getName() );*/
        return "home";
	}
	
}
