package kr.co.sist.car_sell.design.MgrMenu;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MgrMenuDesign extends JFrame {
	
	private static JPanel jpNorth, jpCenter;
	
	public MgrMenuDesign() {
		
		new MgrMenuNorthPanel();
		new MgrMenuCenterPanel();
		
		jpNorth = MgrMenuNorthPanel.getJpNorth();
		jpCenter = MgrMenuCenterPanel.getJpCenter();
		
		jpNorth.setBounds(-5, -5, 1195, 125);
		jpCenter.setBounds(-5, 120, 1195, 780);
		
		add(jpNorth);
		add(jpCenter);
		
		setLayout(null);
		setBounds(360, 90, 1200, 900);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new MgrMenuDesign();
	}

}
