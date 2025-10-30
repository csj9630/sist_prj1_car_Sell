package kr.co.sist.car_sell.dto;

import java.sql.Date;

public class OrderListUserDTO {
	private int user_code, payment_code, price, product_code; //
	private String name, product_name, delivery_state;
	private Date order_date;
	
	public OrderListUserDTO() {
		
	}

	public OrderListUserDTO(int payment_code, Date order_date, int price, int product_code, String product_name,
			String delivery_state, int user_code) {
		super();
		this.payment_code = payment_code;
		this.order_date = order_date;
		this.price = price;
		this.product_code = product_code;
		this.product_name = product_name;
		this.delivery_state = delivery_state;
		this.user_code=user_code;
	}

	public int getUser_code() {
		return user_code;
	}

	public int getPayment_code() {
		return payment_code;
	}

	public int getPrice() {
		return price;
	}

	public int getProduct_code() {
		return product_code;
	}

	public String getProduct_name() {
		return product_name;
	}

	public String getDelivery_state() {
		return delivery_state;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setUser_code(int user_code) {
		this.user_code = user_code;
	}

	public void setPayment_code(int payment_code) {
		this.payment_code = payment_code;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public void setDelivery_state(String delivery_state) {
		this.delivery_state = delivery_state;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	@Override
	public String toString() {
		return "OrderListUserDTO [user_code=" + user_code + ", payment_code=" + payment_code + ", price=" + price
				+ ", product_code=" + product_code + ", name=" + name + ", product_name=" + product_name
				+ ", delivery_state=" + delivery_state + ", order_date=" + order_date + "]";
	}
}
