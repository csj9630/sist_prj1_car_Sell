package kr.co.sist.car_sell.run;

import javax.swing.JFrame;

import kr.co.sist.car_sell.design.UserOrderDesign;

public class Run_UserOrderDesign {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		UserDTO udto = new UserDTO(1,"ggggg", "q1w2e3r4", "김처루", "asdf@ggggg.com", "010-7889-9988",
//				"경상북도 고령군 성산면 고탄길 37-1", "9999-9999-9999-9999");
		new UserOrderDesign(new JFrame(), 1, 1);
	}

}
