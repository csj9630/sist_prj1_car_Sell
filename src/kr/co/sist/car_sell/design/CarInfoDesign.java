package kr.co.sist.car_sell.design;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kr.co.sist.car_sell.event.CarInfoEvt;

public class CarInfoDesign extends JDialog {
	
	private CarInfoNorthPanel cinp;
	private CarInfoCenterPanel cicp;
	private CarInfoSouthPanel cisp;
	private CarInfoEvt cie;
	
	private static JPanel jpNorth, jpCenter, jpSouth; 
	
	public CarInfoDesign() {
		
		cinp = new CarInfoNorthPanel(this);
		cicp = new CarInfoCenterPanel(this);
		cisp = new CarInfoSouthPanel(this);
		
		jpNorth = CarInfoNorthPanel.getJpNorth();
		jpCenter = CarInfoCenterPanel.getJpCenter();
		jpSouth = CarInfoSouthPanel.getJpSouth();
		
		cie = new CarInfoEvt(this, cinp, cicp, cisp);
		
		cicp.getJbtnImage1().addActionListener(cie);
		cicp.getJbtnImage2().addActionListener(cie);
		cicp.getJbtnImage3().addActionListener(cie);
		cicp.getJbtnImage4().addActionListener(cie);
		cicp.getJbtnPurchase().addActionListener(cie);
		
		cisp.getJbtnModify().addActionListener(cie);
		cisp.getJbtnDelete().addActionListener(cie);
		
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
	
	public static JPanel getJpCenter() {
		return jpCenter;
	}
	
	public static JPanel getJpSouth() {
		return jpSouth;
	}
	
	public static void main(String[] args) {
		new CarInfoDesign();
	}
	
}
