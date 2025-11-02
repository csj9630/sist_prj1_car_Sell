package kr.co.sist.car_sell.design;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kr.co.sist.car_sell.event.CarAddEvt;
import kr.co.sist.car_sell.service.CarAddService;

public class CarAddDesign extends JDialog {
	
	private MgrMenuDesign mmd;
	private CarAddNorthPanel canp;
	private CarAddCenterPanel cacp;
	private CarAddSouthPanel casp;
	private CarAddEvt cae;
	private CarAddService cas;
	private int prodCode;
	private String userType;
	private int userCode;
	
	private static JPanel jpNorth, jpCenter, jpSouth; 
	
	public CarAddDesign(MgrMenuDesign mmd) {
		
		this.mmd = mmd;
		
		cas = new CarAddService();
		canp = new CarAddNorthPanel(this);
		cacp = new CarAddCenterPanel(this);
		casp = new CarAddSouthPanel(this);
		
		cae = new CarAddEvt(this, canp, cacp, casp);
		
		cacp.getJbtnImage1().addActionListener(cae);
		cacp.getJbtnImage2().addActionListener(cae);
		cacp.getJbtnImage3().addActionListener(cae);
		cacp.getJbtnImage4().addActionListener(cae);
		cacp.getJbtnImageIcon1().addActionListener(cae);
		cacp.getJbtnImageIcon2().addActionListener(cae);
		cacp.getJbtnImageIcon3().addActionListener(cae);
		cacp.getJbtnImageIcon4().addActionListener(cae);
		
		cacp.getJcbOil().addActionListener(cae);
		casp.getJbtnInsert().addActionListener(cae);
		
		jpNorth = canp.getJpNorth();
		jpCenter = cacp.getJpCenter();
		jpSouth = casp.getJpSouth();
		
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
	
	public CarAddEvt getCae() {
		return cae;
	}
	
	public CarAddNorthPanel getCanp() {
		return canp;
	}
	
	public CarAddCenterPanel getCacp() {
		return cacp;
	}
	
	public CarAddSouthPanel getCasp() {
		return casp;
	}
	
	public CarAddService getCas() {
		return cas;
	}
	
	public int getProdCode() {
		return prodCode;
	}
	
	public static void main(String[] args) {
		new CarAddDesign(new MgrMenuDesign());
	}
	
}
