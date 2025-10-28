package kr.co.sist.car_sell.dto;

import java.sql.Date; // java.sql.Date 사용 확인

public class UserDTOnjw {

	private int user_code;
	private String id, pass, name, email, tel, address, card_num;
	private Date generate_date;
	private char status_activate; // DB CHAR 타입을 char로 매핑

	// 기본 생성자 (필요시 추가)
	public UserDTOnjw() {
	}

	@Override
	public String toString() {
		return "UserDTOnjw [user_code=" + user_code + ", id=" + id + ", pass=" + pass + ", name=" + name + ", email="
				+ email + ", tel=" + tel + ", address=" + address + ", card_num=" + card_num + ", generate_date="
				+ generate_date + ", status_activate=" + status_activate + "]";
	}

	// ---------------------------------------------------
	// Getters and Setters (모든 필드에 대해 생성)
	// ---------------------------------------------------
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

	public char getStatus_activate() {
		return status_activate;
	}

	public void setStatus_activate(char status_activate) {
		// DB CHAR(15) -> Java char 변환 시 주의 (첫 글자만 가져오기)
		this.status_activate = status_activate;
	}
	// DB에서 읽어올 때 문자열 처리 추가
	public void setStatus_activate(String status_activate_str) {
		if (status_activate_str != null && !status_activate_str.isEmpty()) {
			this.status_activate = status_activate_str.charAt(0);
		}
	}


	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}

}// class