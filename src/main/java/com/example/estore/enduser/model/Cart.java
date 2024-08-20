package com.example.estore.enduser.model;

public class Cart {

	private Integer cartId;
	private Integer productId;
	private Integer userId;

	public Cart(Integer cartId, Integer productId, Integer userId) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.userId = userId;
	}

	public Cart() {
		super();
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
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
		return "Cart [cartId=" + cartId + ", productId=" + productId + ", userId=" + userId + "]";
	}

}
