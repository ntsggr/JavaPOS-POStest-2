package posTest2;

public class Device {

	private String category;
	private String logicalName;
	private String vendor;
	private String productName;
	
	
	public Device(String category, String logicalName, String vendor,
			String productName) {
		
		this.category = category;
		this.logicalName = logicalName;
		this.vendor = vendor;
		this.productName = productName;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLogicalName() {
		return logicalName;
	}
	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}
	public String getVendor() {
		return vendor;
	}
	
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
	
	
}
