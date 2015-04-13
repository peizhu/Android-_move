package com.raipeng.appAB.model;

/**
 * @author Administrator 商品
 */
public class ProductEntity {
	private String product_image_url;
	private String name;
	private String price;
	private int inventoryNum;
	private int salesNum;
	public ProductEntity() {
		super();
	}

	public ProductEntity(String product_image_url, String name, String price,
			int inventoryNum, int salesNum) {
		super();
		this.product_image_url = product_image_url;
		this.name = name;
		this.price = price;
		this.inventoryNum = inventoryNum;
		this.salesNum = salesNum;
	}
	public String getProduct_image_url() {
		return product_image_url;
	}

	public void setProduct_image_url(String product_image_url) {
		this.product_image_url = product_image_url;
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

	public int getInventoryNum() {
		return inventoryNum;
	}

	public void setInventoryNum(int inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

	public int getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(int salesNum) {
		this.salesNum = salesNum;
	}
}
