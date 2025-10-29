package kr.co.sist.car_sell.function.UserMenu;

import kr.co.sist.car_sell.design.UserMenu.ModifyUserPasswordDesign;
import kr.co.sist.car_sell.service.UserService;

public class ModifyUserPasswordFunction {
	private ModifyUserPasswordDesign mpd;
	private UserService us;


	public ModifyUserPasswordFunction(ModifyUserPasswordDesign mpd) {
		this.mpd = mpd;
		this.us = new UserService();
	}//ModifyUserFunction
	
	public void updatePassword() {
		
	}//updatePassword
	
}// class
