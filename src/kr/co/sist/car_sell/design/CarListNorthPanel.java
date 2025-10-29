package kr.co.sist.car_sell.design;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.car_sell.event.CarListEvt;
import prj.CarListDesign;

public class CarListNorthPanel extends JFrame {
	
	private JLabel jlblTitle;
	private JButton jbtnMgrMenu, jbtnUserMenu, jbtnLogout;
	private static JPanel jpNorth;
	
	private CarListDesign cld;
	private String userType;
	
	public CarListNorthPanel(CarListDesign cld, String userType) {
		
		this.cld = cld;
		this.userType = userType;
		
		jpNorth = new JPanel();
		jpNorth.setLayout(null);
		jpNorth.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jpNorth.setBackground(new Color(0xFFFFFF));
		
		// 중앙 상단 쌍용중고차 제목
		jlblTitle = new JLabel("쌍용중고차");
		jlblTitle.setBounds(400, 25, 400, 70);
		jlblTitle.setHorizontalAlignment(0);
		jlblTitle.setVerticalAlignment(0);
		jlblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		jlblTitle.setForeground(new Color(0x000000));
		jpNorth.add(jlblTitle);
		
		// 우측 상단 내정보 버튼
		jbtnUserMenu = new JButton("내정보");
		jbtnUserMenu.setBounds(874, 35, 130, 60);
		jbtnUserMenu.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jbtnUserMenu.setBackground(new Color(0xC0C0C0));
		jbtnUserMenu.setForeground(new Color(0x000000));
		jbtnUserMenu.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jpNorth.add(jbtnUserMenu);
		
		// 우측 상단 로그아웃 버튼
		jbtnLogout = new JButton("로그아웃");
		jbtnLogout.setBounds(1014, 35, 130, 60);
		jbtnLogout.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jbtnLogout.setBackground(new Color(0xC0C0C0));
		jbtnLogout.setForeground(new Color(0x000000));
		jbtnLogout.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jpNorth.add(jbtnLogout);
		
		if(userType.equals("a")) {
			
			// 좌측 상단 관리자 메뉴 버튼
			jbtnMgrMenu = new JButton("관리자 메뉴");
			jbtnMgrMenu.setBounds(87, 35, 180, 60);
			jbtnMgrMenu.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jbtnMgrMenu.setBackground(new Color(0xC0C0C0));
			jbtnMgrMenu.setForeground(new Color(0x000000));
			jbtnMgrMenu.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
			jpNorth.add(jbtnMgrMenu);
				
		} // end if
		
	}
	
	public static JPanel getJpNorth() {
		return jpNorth;
	} // getJpNorth
	
	public JButton getJbtnMgrMenu() {
		return jbtnMgrMenu;
	}
	
	public JButton getJbtnUserMenu() {
		return jbtnUserMenu;
	}
	
	public JButton getJbtnLogout() {
		return jbtnLogout;
	}
	
}
