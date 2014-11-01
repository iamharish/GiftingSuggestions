package hars.zappos.assignment.domains;

import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class SuggestedResult {
	Set<Product> products;
	double price;
	private NumberFormat dlrFormatter = NumberFormat.getCurrencyInstance();
	
	public SuggestedResult(){
		products = new HashSet<Product>();
		price = 0;
	}
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString(){
		return "Total price for result "+price+" with products "+products;
	}
}
