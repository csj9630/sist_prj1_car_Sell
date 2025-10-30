package kr.co.sist.car_sell.dto;

import java.sql.Date;

public class OrderListDetailManagerDTO {
	private int distance, payment_code, price;
	private String name, email, tel, address, product_name, brand, delivery_state;
	private Date car_year, order_date;
	
	public OrderListDetailManagerDTO() {
		
	}

	public OrderListDetailManagerDTO(int distance, int payment_code, int price, String name, String email, String tel,
			String address, String product_name, String brand, String delivery_state, Date car_year, Date order_date) {
		super();
		this.distance = distance;
		this.payment_code = payment_code;
		this.price = price;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.product_name = product_name;
		this.brand = brand;
		this.delivery_state = delivery_state;
		this.car_year = car_year;
		this.order_date = order_date;
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

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getTel() {
		return tel;
	}

	public String getAddress() {
		return address;
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

	public Date getCar_year() {
		return car_year;
	}

	public Date getOrder_date() {
		return order_date;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public void setCar_year(Date car_year) {
		this.car_year = car_year;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	@Override
	public String toString() {
		return "OrderListDetailManagerDTO [distance=" + distance + ", payment_code=" + payment_code + ", price=" + price
				+ ", name=" + name + ", email=" + email + ", tel=" + tel + ", address=" + address + ", product_name="
				+ product_name + ", brand=" + brand + ", delivery_state=" + delivery_state + ", car_year=" + car_year
				+ ", order_state=" + order_date + "]";
	}
	
}
