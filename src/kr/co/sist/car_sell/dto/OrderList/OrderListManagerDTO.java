package teamproject2;

import java.sql.Date;

public class OrderListManagerDTO {
	private int payment_code, price, product_code; 
	private String user_name, product_name, delivery_state;
	private Date order_date;

	public OrderListManagerDTO() {
		
	}

	public OrderListManagerDTO(int payment_code, int price, int product_code, String user_name, String product_name,
			String delivery_state, Date order_date) {
		super();
		this.payment_code = payment_code;
		this.price = price;
		this.product_code = product_code;
		this.user_name = user_name;
		this.product_name = product_name;
		this.delivery_state = delivery_state;
		this.order_date = order_date;
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

	public String getUser_name() {
		return user_name;
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

	public void setPayment_code(int payment_code) {
		this.payment_code = payment_code;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
		return "OrderListManagerDTO [payment_code=" + payment_code + ", price=" + price + ", product_code="
				+ product_code + ", user_name=" + user_name + ", product_name=" + product_name + ", delivery_state="
				+ delivery_state + ", order_date=" + order_date + "]";
	}
	
}
