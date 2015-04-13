package com.raipeng.appAB.model;

import java.util.List;

/**
 * @author Administrator
 *分类列表
 */
public class ProductClassfyEntity {
private String classfyname;
private int num;
private List<ProductEntity> productList;
public String getClassfyname() {
	return classfyname;
}
public void setClassfyname(String classfyname) {
	this.classfyname = classfyname;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public List<ProductEntity> getProductList() {
	return productList;
}
public void setProductList(List<ProductEntity> productList) {
	this.productList = productList;
}

}
