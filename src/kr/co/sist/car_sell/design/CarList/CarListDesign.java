package kr.co.sist.car_sell.design.CarList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CarListDesign extends JFrame{
	
	private CarListNorthPanel clnp;
	private CarListLeftPanel cllp;
	private CarListRightPanel clrp;
	
	public CarListDesign() {
		super("쌍용중고차");
		
		clnp = new CarListNorthPanel(this);
		cllp = new CarListLeftPanel(this);
		clrp = new CarListRightPanel(this);
		
		JPanel jpNorth = CarListNorthPanel.getJpNorth();
		JPanel jpRight = CarListRightPanel.getJpRight();
		JPanel jpLeft = CarListLeftPanel.getJpLeft();
		
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
	
}
