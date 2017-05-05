package com.taotao.commom.pojo;

public class SearchItem {
	private String id;
	private String title;
	private String sell_point;
	private long price;
	private String image;
	private String item_cat_name;
	private String item_desc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSell_point() {
		return sell_point;
	}
	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getItem_cat_name() {
		return item_cat_name;
	}
	public void setItem_cat_name(String item_cat_name) {
		this.item_cat_name = item_cat_name;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	@Override
	public String toString() {
		return "SearchItem [image=" + image + "]";
	}
	
}
