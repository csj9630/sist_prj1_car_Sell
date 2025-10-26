package carSell_prj_test_all;

import java.sql.Date;

public class UserMgrDTO {
	private int user_code;
	private String id,pass,name,email,address,tel,status_activate;
	private Date generate_date;
	public UserMgrDTO() {
	}
	public UserMgrDTO(int user_code, String id, String pass, String name, String email, String address, String tel,
			String status_activate, Date generate_date) {
		this.user_code = user_code;
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.email = email;
		this.address = address;
		this.tel = tel;
		this.status_activate = status_activate;
		this.generate_date = generate_date;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getStatus_activate() {
		return status_activate;
	}
	public void setStatus_activate(String status_activate) {
		this.status_activate = status_activate;
	}
	public Date getGenerate_date() {
		return generate_date;
	}
	public void setGenerate_date(Date generate_date) {
		this.generate_date = generate_date;
	}
	@Override
	public String toString() {
		return "UserMgrDTO [user_code=" + user_code + ", id=" + id + ", pass=" + pass + ", name=" + name + ", email="
				+ email + ", address=" + address + ", tel=" + tel + ", status_activate=" + status_activate
				+ ", generate_date=" + generate_date + "]";
	}
	
	
}
//class
