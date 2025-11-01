package kr.co.sist.car_sell.design;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.car_sell.event.CarInfoEvt;

public class CarInfoSouthPanel extends JDialog{
	
	private JButton jbtnModify, jbtnDelete;
	private JLabel jlblUserCode;
	private static JPanel jpSouth; 
	
	private CarInfoDesign cid;
	private CarInfoNorthPanel cinp;
	private CarInfoCenterPanel cicp;
	private CarInfoEvt cie;
	private int prodCode;
	private String userType;
	private int userCode;
	
	public CarInfoSouthPanel(CarInfoDesign cid, int prodCode, String userType, int userCode) {
		
		this.cid = cid;
		this.prodCode = prodCode;
		this.userType = userType;
		this.userCode = userCode;
		
		jpSouth = new JPanel();
		
		jpSouth.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jpSouth.setLayout(null);
		
		// 수정 버튼
		jbtnModify = new JButton("수정");
		jbtnModify.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jbtnModify.setForeground(new Color(0x000000));
		jbtnModify.setBackground(new Color(0xC0C0C0));
		jbtnModify.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jbtnModify.setBounds(375, 10, 200, 60);
		
		// 삭제 버튼
		jbtnDelete = new JButton("삭제");
		jbtnDelete.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jbtnDelete.setForeground(new Color(0x000000));
		jbtnDelete.setBackground(new Color(0xC0C0C0));
		jbtnDelete.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jbtnDelete.setBounds(625, 10, 200, 60);
		
		jpSouth.add(jbtnModify);
		jpSouth.add(jbtnDelete);
		
	}
	
	public JPanel getJpSouth() {
		return jpSouth;
	}
	
	public JButton getJbtnModify() {
		return jbtnModify;
	}
	
	public JButton getJbtnDelete() {
		return jbtnDelete;
	}
	
}
