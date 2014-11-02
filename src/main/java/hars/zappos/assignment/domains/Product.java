package hars.zappos.assignment.domains;

import java.text.NumberFormat;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	private String productId;
	private String name;
	private String price;
	private double priceAsDouble;
	private String originalPrice;
	private String styleId;
	private String percentOff;
	private String productUrl;
	private String colorId;
	private String productName;
	private String brandName;
	private NumberFormat dlrFormatter = NumberFormat.getCurrencyInstance();
		
	public Product(String productId, String name, String productUrl, String price){
		this.productId = productId;
		this.name = name;
		this.productUrl = productUrl;
		this.price = price;
		priceAsDouble = Double.parseDouble(price.substring(1));
	}
	
	public double getPriceAsDouble() {
		return priceAsDouble;
	}

	public void setPriceAsDouble(double priceAsDouble) {
		this.priceAsDouble = priceAsDouble;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	public String getPercentOff() {
		return percentOff;
	}

	public void setPercentOff(String percentOff) {
		this.percentOff = percentOff;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	@Override
	public boolean equals(Object product){
		if(!(product instanceof Product)){
			return false;
		}
		Product prod = (Product)product; 
		if(name.equalsIgnoreCase(prod.getName())){
			return true;
		}
		return false;
	}
	
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getProductUrl() {
		return productUrl;
	}
	@Override
	public int hashCode(){
		return (int) (productId.hashCode()*11+name.hashCode()*13+price.hashCode()*23);
	}
	
	@Override
	public String toString(){
		//return name+"\t"+dlrFormatter.format(price);
		return name+"\t"+priceAsDouble;
	}
	
}
