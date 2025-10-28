package kr.co.sist.car_sell.design.UserMenu;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.car_sell.event.UserMenu.UserMenuEvt;

/**
 * 작업자 : 최승준<br>
 * '내 정보 메뉴' 화면을 디자인한 클래스<br>
 * 
 */
public class UserMenuDesign extends JFrame {

	private JButton jbtnModifyInfo, jbtnModifyPw, jbtnOrderList;
	private int user_code; // 사용자 코드(임시), 차후 다른 페이지를 통해서 사용

	public UserMenuDesign(int user_code) {

		super("사용자 정보");
		this.user_code = user_code;// 사용자 코드 받음.

		JLabel jlTitle = new JLabel("내 정보");

		JPanel jpCenter = new JPanel();
		JPanel jpNorth = new JPanel();

		// 버튼 객체 생성
		jbtnModifyInfo = new JButton("내 정보 수정");
		jbtnModifyPw = new JButton("비밀번호 수정");
		jbtnOrderList = new JButton("주문 내역 ");

		// 상단 패널에 타이틀 추가
		jpNorth.add(jlTitle);

		// ㅁㅁㅁㅁㅁㅁ테스트용 usercode 출력 ㅁㅁㅁㅁㅁㅁㅁㅁ
		JLabel jpUserCode = new JLabel("UserCode = " + user_code);
		jpUserCode.setFont(new Font("맑은고딕", Font.BOLD, 20));
		add("South", jpUserCode);
//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
		
		// 센터 패널에 버튼 추가
		jpCenter.add(jbtnModifyInfo);
		jpCenter.add(jbtnModifyPw);
		jpCenter.add(jbtnOrderList);

		// FlowLayout으로 설정
		jpCenter.setLayout(new FlowLayout());

		// 폰트 설정
		Font fontTitle = new Font("맑은고딕", Font.BOLD, 40);
		Font fontBtn = new Font("맑은고딕", Font.BOLD, 20);

		jlTitle.setFont(fontTitle);
		jbtnModifyInfo.setFont(fontBtn);
		jbtnModifyPw.setFont(fontBtn);
		jbtnOrderList.setFont(fontBtn);

		jbtnModifyInfo.setLocation(500, 500);

		// JPanel에 상하 여백 넣기
		// ----createEmptyBorder(int top, int left, int bottom, int right)
		jpNorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jpCenter.setBorder(BorderFactory.createEmptyBorder(100, 10, 100, 10));

		// JPanel에 배경색 넣기
		jpNorth.setBackground(Color.white);
		jpCenter.setBackground(Color.white);

		add("North", jpNorth);
		add("Center", jpCenter);

		// 이벤트 등록
		UserMenuEvt ume = new UserMenuEvt(this);

		jbtnModifyInfo.addActionListener(ume);
		jbtnModifyPw.addActionListener(ume);
		jbtnOrderList.addActionListener(ume);

		addWindowListener(ume);

		// 화면 설정
		setBounds(600, 250, 600, 500);
		setLocationRelativeTo(this); // 윈도우 창을 화면 가운데에 띄우는 역할을 함
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}// UserMenuDesign

	public JButton getjbtnModifyInfo() {
		return jbtnModifyInfo;
	}

	public JButton getjbtnModifyPw() {
		return jbtnModifyPw;
	}

	public JButton getjbtnOrderList() {
		return jbtnOrderList;
	}

	public int getUser_code() {
		return user_code;
	}

}// class
