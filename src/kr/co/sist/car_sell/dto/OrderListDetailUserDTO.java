package kr.co.sist.car_sell.dto;

import java.sql.Date;

public class OrderListDetailUserDTO {
	private String product_name, brand, delivery_state;
	private int distance, payment_code, price;
	private Date car_year, order_date;

	public OrderListDetailUserDTO() {
		
	}

	public OrderListDetailUserDTO(String product_name, String brand, String delivery_state, int distance,
			int payment_code, int price, Date car_year, Date order_date) {
		super();
		this.product_name = product_name;
		this.brand = brand;
		this.delivery_state = delivery_state;
		this.distance = distance;
		this.payment_code = payment_code;
		this.price = price;
		this.car_year = car_year;
		this.order_date = order_date;
	}

	public String getProduct_name() {
		return product_name;
	}

	public String getBrand() {
		return brand;
	}

	public String getDelivery_state() {
		return delivery_state;
	}

	public int getDistance() {
		return distance;
	}

	public int getPayment_code() {
		return payment_code;
	}

	public int getPrice() {
		return price;
	}

	public Date getCar_year() {
		return car_year;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setDelivery_state(String delivery_state) {
		this.delivery_state = delivery_state;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setPayment_code(int payment_code) {
		this.payment_code = payment_code;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setCar_year(Date car_year) {
		this.car_year = car_year;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	@Override
	public String toString() {
		return "OrderListDetailUserDTO [product_name=" + product_name + ", brand=" + brand + ", delivery_state="
				+ delivery_state + ", distance=" + distance + ", payment_code=" + payment_code + ", price=" + price
				+ ", car_year=" + car_year + ", order_date=" + order_date + "]";
	}
	
}
