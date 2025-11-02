package kr.co.sist.car_sell.design;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import kr.co.sist.car_sell.event.CarAddEvt;

public class CarAddSouthPanel extends JDialog{
	
	private JButton jbtnInsert;
	private static JPanel jpSouth; 
	
	private CarAddDesign cad;
	private CarAddEvt cae;
	
	public CarAddSouthPanel(CarAddDesign cad) {
		
		this.cad = cad;
		
		jpSouth = new JPanel();
		
		jpSouth.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jpSouth.setLayout(null);
		
		// 수정 버튼
		jbtnInsert = new JButton("추가");
		jbtnInsert.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jbtnInsert.setForeground(new Color(0x000000));
		jbtnInsert.setBackground(new Color(0xC0C0C0));
		jbtnInsert.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jbtnInsert.setBounds(500, 10, 200, 60);
		
		jpSouth.add(jbtnInsert);
		
	}
	
	public JPanel getJpSouth() {
		return jpSouth;
	}
	
	public JButton getJbtnInsert() {
		return jbtnInsert;
	}
	
	
}
