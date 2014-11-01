package hars.zappos.assignment.services;

import hars.zappos.assignment.domains.Product;

import java.util.List;
import java.util.Set;

import org.apache.commons.codec.EncoderException;
import org.springframework.web.client.RestClientException;

public interface ZapposApiCallHelper {
	
	public boolean execute(String uri, String filters) throws Exception;
	public List<Product> getResults() throws Exception;
}
