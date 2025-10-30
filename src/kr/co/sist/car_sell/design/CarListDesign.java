package kr.co.sist.car_sell.design;

import javax.swing.JFrame;
import javax.swing.JPanel;

import kr.co.sist.car_sell.event.CarListEvt;
import kr.co.sist.car_sell.service.CarListService;

public class CarListDesign extends JFrame{
	
	private CarListNorthPanel clnp;
	private CarListLeftPanel cllp;
	private CarListRightPanel clrp;
	private CarListEvt cle;
	private CarListService cls;
	private String userType;
	private int userCode;
	
	public CarListDesign(String userType, int userCode) {
		super("쌍용중고차");
		
		this.userType = userType;
		this.userCode = userCode;
		
		cls = new CarListService();
		cllp = new CarListLeftPanel(this);
		clnp = new CarListNorthPanel(this, userType);
		clrp = new CarListRightPanel(this, userType, userCode);
		
		JPanel jpNorth = CarListNorthPanel.getJpNorth();
		JPanel jpRight = CarListRightPanel.getJpRight();
		JPanel jpLeft = CarListLeftPanel.getJpLeft();
		
		cle = new CarListEvt(this, userCode, clnp, cllp, clrp, cls);
		
		clnp.getJbtnUserMenu().addActionListener(cle);
		clnp.getJbtnLogout().addActionListener(cle);
		
		if(userType.equals("a")) {
			clnp.getJbtnMgrMenu().addActionListener(cle);
		} // end if
		
		setLayout(null);
		
		jpNorth.setBounds(-5, -5, 1195, 125);
		add(jpNorth);
		
		jpRight.setBounds(334, 120, 900, 721);
		add(jpRight);
		
		jpLeft.setBounds(30, 125, 284, 716);
		add(jpLeft);
		
		setBounds(360, 90, 1200, 900);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public CarListNorthPanel getClnp() {
		return clnp;
	}
	
	public CarListLeftPanel getCllp() {
		return cllp;
	}
	
	public CarListRightPanel getClrp() {
		return clrp;
	}
	
	public CarListService getCls() {
		return cls;
	}
	
	public int getUserCode() {
		return userCode;
	}
}
