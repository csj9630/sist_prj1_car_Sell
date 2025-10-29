package kr.co.sist.car_sell.design;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CarInfoSouthPanel extends JDialog{
	
	private CarInfoDesign cid;
	
	private JButton jbtnModify, jbtnDelete;
	private static JPanel jpSouth; 
		
	public CarInfoSouthPanel(CarInfoDesign cid) {
		
		this.cid = cid;
		
		jpSouth = new JPanel();
		
		jpSouth.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jpSouth.setLayout(null);
		
		jbtnModify = new JButton("수정");
		jbtnDelete = new JButton("삭제");
		
		jbtnModify.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jbtnModify.setForeground(new Color(0x000000));
		jbtnModify.setBackground(new Color(0xC0C0C0));
		jbtnModify.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jbtnModify.setBounds(375, 10, 200, 60);
		
		jbtnDelete.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jbtnDelete.setForeground(new Color(0x000000));
		jbtnDelete.setBackground(new Color(0xC0C0C0));
		jbtnDelete.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jbtnDelete.setBounds(625, 10, 200, 60);
		
		jpSouth.add(jbtnModify);
		jpSouth.add(jbtnDelete);
	}
	
	public JButton getJbtnModify() {
		return jbtnModify;
	}
	
	public JButton getJbtnDelete() {
		return jbtnDelete;
	}
	
	public static JPanel getJpSouth() {
		return jpSouth;
	}
	
}
