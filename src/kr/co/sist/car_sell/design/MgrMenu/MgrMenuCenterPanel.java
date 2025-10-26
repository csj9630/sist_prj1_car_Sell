package kr.co.sist.car_sell.design.MgrMenu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MgrMenuCenterPanel extends JFrame {
	
	private JLabel jlblTitle;
	private JButton jbtnAddCar, jbtnManageMember, jbtnManageSettlement, jbtnOrderList;
	
	private static JPanel jpCenter;
	
	private MgrMenuEvt mme;
	
	public MgrMenuCenterPanel() {
		
		jpCenter = new JPanel();
		jpCenter.setLayout(null);
		jpCenter.setBorder(null);
		jpCenter.setBackground(new Color(0xFFFFFF));
		
		// 중앙 관리자 메뉴 제목
		jlblTitle = new JLabel("관리자 메뉴");
		jlblTitle.setHorizontalAlignment(0);
		jlblTitle.setVerticalAlignment(0);
		jlblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jlblTitle.setForeground(new Color(0x000000));
		jlblTitle.setBounds(400, 78, 400, 70);
		jpCenter.add(jlblTitle);
		
		jbtnAddCar = new JButton("신규 차량 등록");
		jbtnAddCar.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jbtnAddCar.setBackground(new Color(0xC0C0C0));
		jbtnAddCar.setForeground(new Color(0x000000));
		jbtnAddCar.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jbtnAddCar.addActionListener(null);
		jbtnAddCar.setBounds(220, 226, 360, 140);
		jpCenter.add(jbtnAddCar);
		
		jbtnManageMember = new JButton("회원관리");
		jbtnManageMember.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jbtnManageMember.setBackground(new Color(0xC0C0C0));
		jbtnManageMember.setForeground(new Color(0x000000));
		jbtnManageMember.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jbtnManageMember.addActionListener(null);
		jbtnManageMember.setBounds(620, 226, 360, 140);
		jpCenter.add(jbtnManageMember);
		
		jbtnManageSettlement = new JButton("정산관리");
		jbtnManageSettlement.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jbtnManageSettlement.setBackground(new Color(0xC0C0C0));
		jbtnManageSettlement.setForeground(new Color(0x000000));
		jbtnManageSettlement.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jbtnManageSettlement.addActionListener(null);
		jbtnManageSettlement.setBounds(220, 408, 360, 140);
		jpCenter.add(jbtnManageSettlement);
		
		jbtnOrderList = new JButton("주문내역");
		jbtnOrderList.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jbtnOrderList.setBackground(new Color(0xC0C0C0));
		jbtnOrderList.setForeground(new Color(0x000000));
		jbtnOrderList.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jbtnOrderList.addActionListener(null);
		jbtnOrderList.setBounds(620, 408, 360, 140);
		jpCenter.add(jbtnOrderList);
		
	} // MgrMenuCenterPanel
	
	public static JPanel getJpCenter() {
		return jpCenter;
	} // getJpCenter
	
	public JButton getJbtnAddCar() {
		return jbtnAddCar;
	}
	
	public JButton getJbtnManageMember() {
		return jbtnManageMember;
	}
	
	public JButton getJbtnManageSettlement() {
		return jbtnManageSettlement;
	}
	
	public JButton getJbtnOrderList() {
		return jbtnOrderList;
	}
	
}
