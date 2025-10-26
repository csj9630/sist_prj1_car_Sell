package kr.co.sist.car_sell.run;

import kr.co.sist.car_sell.design.loginRegister.FirstSelectDesign;
import kr.co.sist.car_sell.event.loginRegister.FirstSelectEvt;

public class team2Run {

	public static void main(String[] args) {

		FirstSelectDesign fsd = new FirstSelectDesign();
		new FirstSelectEvt(fsd);

	} // main

} // class