package com.example.estore.enduser.model;

public class Wishlist {

	private Integer wishListId;
	private Integer productId;
	private Integer userId;

	public Wishlist(Integer wishListId, Integer productId, Integer userId) {
		super();
		this.wishListId = wishListId;
		this.productId = productId;
		this.userId = userId;
	}

	public Wishlist() {
		super();
	}

	public Integer getWishListId() {
		return wishListId;
	}

	public void setWishListId(Integer wishListId) {
		this.wishListId = wishListId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Wishlist [wishListId=" + wishListId + ", productId=" + productId + ", userId=" + userId + "]";
	}

}
