package hars.zappos.assignment.services;

import hars.zappos.assignment.domains.Product;
import hars.zappos.assignment.domains.ProductSearchResponse;
import hars.zappos.assignment.services.interfaces.ZapposApiCallHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ZapposSearchApiCallHelper extends RestTemplate implements ZapposApiCallHelper{
	private RestTemplate restTemplate;
	private ProductSearchResponse data;
	@Autowired
	private Environment env;
	private Logger logger = Logger.getLogger(ZapposSearchApiCallHelper.class);
	
	public ZapposSearchApiCallHelper(){
		super();
		HttpMessageConverter<?> jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		// Add the Jackson Message converter
		((MappingJackson2HttpMessageConverter) jsonHttpMessageConverter).getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		messageConverters.add(jsonHttpMessageConverter);
		// Add the message converters to the restTemplate
		super.setMessageConverters(messageConverters);
		//restTemplate.execute("http://graph.facebook.com/pivotalsoftware", HttpMethod.GET, null, responseExtractor);
	}
	
	public boolean execute(String uri, String filters) throws RestClientException, EncoderException{
		String[] keys = getAllApiKeys();
		URLCodec codec = new URLCodec();
		for(int i=0;i<keys.length;i++){
			if(null != filters && !filters.isEmpty()){
				logger.info("calling"+uri+keys[i]+filters);
				data = getForObject(uri+keys[i]+filters, ProductSearchResponse.class);
			}
			else {
				logger.info("calling"+uri+keys[i]);
				data = getForObject(uri+keys[i], ProductSearchResponse.class);
			}
			int statusCode = data.getStatusCode();
			if(statusCode == Integer.parseInt(env.getProperty("successCode"))){
				return true;
			}
			else {
				//If not a forbidden error. Retry with different key
				if(!(statusCode == Integer.parseInt(env.getProperty("forbiddenCode"))))
				return false;
			}
		}
		//Tried with all keys. No success
		return false;
	}
	
	public List<Product> getResults() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		System.out.println("Data: "+data);
		//String productResultsJson = data.get(env.getProperty("resultsType")); 
		JsonNode productResultsJson = data.getResults();
		List<Product> products = new ArrayList<Product>();
		for(JsonNode element: productResultsJson){
			Product product = new Product(element.get("productId").asText(),element.get("productName").asText(),
					element.get("productUrl").asText(), element.get("price").asText());
			products.add(product);
		}
		return products;
	}
	
	private String[] getAllApiKeys() {
		return env.getProperty("apiKeys").split(",");
	}	
}
