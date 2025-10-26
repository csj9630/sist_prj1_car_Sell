package prj_1.run;

import prj_1.design.FirstSelectDesign;
import prj_1.evt.FirstSelectEvt;

public class team2Run {

	public static void main(String[] args) {

		FirstSelectDesign fsd = new FirstSelectDesign();
		new FirstSelectEvt(fsd);

	} // main

} // class