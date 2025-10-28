package kr.co.sist.car_sell.design.CarList;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.car_sell.event.CarListEvt;

public class CarListNorthPanel extends JFrame {
	
	private JLabel jlblTitle;
	private JButton jbtnMgrMenu, jbtnUserMenu, jbtnLogout;
	private static JPanel jpNorth;
	
	private CarListDesign cld;
	
	public CarListNorthPanel(CarListDesign cld) {
		
		this.cld = cld;
		
		jpNorth = new JPanel();
		
		jlblTitle = new JLabel("쌍용중고차");
		jbtnMgrMenu = new JButton("관리자 메뉴");
		jbtnUserMenu = new JButton("내정보");
		jbtnLogout = new JButton("로그아웃");
		
		// 중앙 상단 쌍용중고차 제목
		jlblTitle.setBounds(400, 25, 400, 70);
		jlblTitle.setHorizontalAlignment(0);
		jlblTitle.setVerticalAlignment(0);
		jlblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		jlblTitle.setForeground(new Color(0x000000));
		
		// 좌측 상단 관리자 메뉴 버튼
		jbtnMgrMenu.setBounds(87, 35, 180, 60);
		jbtnMgrMenu.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jbtnMgrMenu.setBackground(new Color(0xC0C0C0));
		jbtnMgrMenu.setForeground(new Color(0x000000));
		jbtnMgrMenu.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		
		// 우측 상단 내정보 버튼
		jbtnUserMenu.setBounds(874, 35, 130, 60);
		jbtnUserMenu.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jbtnUserMenu.setBackground(new Color(0xC0C0C0));
		jbtnUserMenu.setForeground(new Color(0x000000));
		jbtnUserMenu.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		
		// 우측 상단 로그아웃 버튼
		jbtnLogout.setBounds(1014, 35, 130, 60);
		jbtnLogout.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jbtnLogout.setBackground(new Color(0xC0C0C0));
		jbtnLogout.setForeground(new Color(0x000000));
		jbtnLogout.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		
		jpNorth.setLayout(null);
		jpNorth.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jpNorth.setBackground(new Color(0xFFFFFF));
		jpNorth.add(jlblTitle);
		jpNorth.add(jbtnMgrMenu);
		jpNorth.add(jbtnUserMenu);
		jpNorth.add(jbtnLogout);
		
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
