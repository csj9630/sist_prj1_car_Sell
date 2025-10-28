package kr.co.sist.car_sell.dto;

import java.sql.Date;

public class UserDTO {

	private int user_code;
	private String id, pass, name, email, tel, address, card_num, status_activate;
	private Date generate_date;

	@Override
	public String toString() {
		return "UserDTO [user_code=" + user_code + ", id=" + id + ", pass=" + pass + ", name=" + name + ", email="
				+ email + ", tel=" + tel + ", address=" + address + ", card_num=" + card_num + ", generate_date="
				+ generate_date + ", status_activate=" + status_activate + "]";
	}

	
	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public UserDTO(int user_code, String id, String pass, String name, String email, String tel, String address, String card_num) {
		super();
		this.user_code=user_code;
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.card_num = card_num;
	}



	public int getUser_code() {
		return user_code;
	}

	public void setUser_code(int user_code) {
		this.user_code = user_code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getGenerate_date() {
		return generate_date;
	}

	public void setGenerate_date(Date generate_date) {
		this.generate_date = generate_date;
	}

	public String getStatus_activate() {
		return status_activate;
	}

	public void setStatus_activate(String status_activate) {
		this.status_activate = status_activate;
	}

	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}

}// class