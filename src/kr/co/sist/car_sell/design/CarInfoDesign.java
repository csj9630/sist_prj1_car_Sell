package kr.co.sist.car_sell.design;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kr.co.sist.car_sell.event.CarInfoEvt;
import kr.co.sist.car_sell.service.CarInfoService;

public class CarInfoDesign extends JDialog {
	
	private CarListDesign cld;
	private CarInfoNorthPanel cinp;
	private CarInfoCenterPanel cicp;
	private CarInfoSouthPanel cisp;
	private CarInfoEvt cie;
	private CarInfoService cis;
	private int prodCode;
	private String userType;
	private int userCode;
	
	private static JPanel jpNorth, jpCenter, jpSouth; 
	
	public CarInfoDesign(CarListDesign cld, int prodCode, String userType, int userCode) {
		
		this.cld = cld;
		this.prodCode = prodCode;
		this.userType = userType;
		this.userCode = userCode;
		
		cis = new CarInfoService();
		cinp = new CarInfoNorthPanel(this, prodCode);
		cicp = new CarInfoCenterPanel(this, prodCode, userType, userCode);
		cisp = new CarInfoSouthPanel(this, prodCode, userType, userCode);
		
		cie = new CarInfoEvt(this, cld, prodCode, userCode, cinp, cicp, cisp);
		
		if(userType.equals("a")) {
			cicp.getJbtnImageIcon1().addActionListener(cie);
			cicp.getJbtnImageIcon2().addActionListener(cie);
			cicp.getJbtnImageIcon3().addActionListener(cie);
			cicp.getJbtnImageIcon4().addActionListener(cie);
			cicp.getJcbStatSold().addActionListener(cie);
			cicp.getJcbOil().addActionListener(cie);
			cisp.getJbtnModify().addActionListener(cie);
			cisp.getJbtnDelete().addActionListener(cie);
		} else if(userType.equals("u")) {
			cicp.getJbtnPurchase().addActionListener(cie);
		} // end if ~ else if
		
		jpNorth = cinp.getJpNorth();
		jpCenter = cicp.getJpCenter();
		jpSouth = cisp.getJpSouth();
		
		jpNorth.setBounds(-5, -5, 1195, 125);
		jpCenter.setBounds(0, 120, 1200, 666);
		jpSouth.setBounds(-10, 786, 1200, 80);
		add(jpNorth);
		add(jpCenter);
		add(jpSouth);
		
		setLayout(null);
		setBounds(360, 90, 1200, 900);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static JPanel getJpNorth() {
		return jpNorth;
	}
	
	public static JPanel getJpSouth() {
		return jpSouth;
	}
	
	public static JPanel getJpCenter() {
		return jpCenter;
	}
	
	public CarInfoEvt getCie() {
		return cie;
	}
	
	public CarInfoNorthPanel getCinp() {
		return cinp;
	}
	
	public CarInfoCenterPanel getCicp() {
		return cicp;
	}
	
	public CarInfoSouthPanel getCisp() {
		return cisp;
	}
	
	public int getProdCode() {
		return prodCode;
	}
	
	public CarInfoService getCis() {
		return cis;
	}
	
}
