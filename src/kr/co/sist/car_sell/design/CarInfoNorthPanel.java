package kr.co.sist.car_sell.design;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CarInfoNorthPanel extends JFrame {
	
	private CarInfoDesign cid;
	
	private JLabel jlblTitle;
	private static JPanel jpNorth;
	
	public CarInfoNorthPanel(CarInfoDesign cid) {
		
		this.cid = cid;
		
		jpNorth = new JPanel();
		
		
		jlblTitle = new JLabel("쌍용중고차");
		
		// 중앙 상단 쌍용중고차 제목
		jlblTitle.setBounds(400, 25, 400, 70);
		jlblTitle.setHorizontalAlignment(0);
		jlblTitle.setVerticalAlignment(0);
		jlblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		jlblTitle.setForeground(new Color(0x000000));
		
		jpNorth.setLayout(null);
		jpNorth.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jpNorth.setBackground(new Color(0xFFFFFF));
		jpNorth.add(jlblTitle);
		
	}
	
	public static JPanel getJpNorth() {
		return jpNorth;
	} // getJpNorth
	
}
