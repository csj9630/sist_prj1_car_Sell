package kr.co.sist.car_sell.run;

import kr.co.sist.car_sell.design.LoginRegister.FirstSelectDesign;
import kr.co.sist.car_sell.event.LoginRegister.FirstSelectEvt;

public class Run_Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FirstSelectDesign fsd = new FirstSelectDesign();
		new FirstSelectEvt(fsd);
	}

}
