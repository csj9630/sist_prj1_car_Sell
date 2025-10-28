package kr.co.sist.car_sell.dto;

import java.sql.Date;

public class AdminDTO {
	private String admin_id, admin_pass, admin_address, admin_contact, admin_fax;
	private Date admin_add_date;

	
	@Override
	public String toString() {
		return "AdminDTO [admin_id=" + admin_id + ", admin_pass=" + admin_pass + ", admin_address=" + admin_address
				+ ", admin_contact=" + admin_contact + ", admin_fax=" + admin_fax + ", admin_add_date=" + admin_add_date
				+ "]";
	}
	public AdminDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminDTO(String admin_id, String admin_pass, String admin_address, String admin_contact, String admin_fax,
			Date admin_add_date) {
		super();
		this.admin_id = admin_id;
		this.admin_pass = admin_pass;
		this.admin_address = admin_address;
		this.admin_contact = admin_contact;
		this.admin_fax = admin_fax;
		this.admin_add_date = admin_add_date;
	}
	
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_pass() {
		return admin_pass;
	}
	public void setAdmin_pass(String admin_pass) {
		this.admin_pass = admin_pass;
	}
	public String getAdmin_address() {
		return admin_address;
	}
	public void setAdmin_address(String admin_address) {
		this.admin_address = admin_address;
	}
	public String getAdmin_contact() {
		return admin_contact;
	}
	public void setAdmin_contact(String admin_contact) {
		this.admin_contact = admin_contact;
	}
	public String getAdmin_fax() {
		return admin_fax;
	}
	public void setAdmin_fax(String admin_fax) {
		this.admin_fax = admin_fax;
	}
	public Date getAdmin_add_date() {
		return admin_add_date;
	}
	public void setAdmin_add_date(Date admin_add_date) {
		this.admin_add_date = admin_add_date;
	}
}//class
