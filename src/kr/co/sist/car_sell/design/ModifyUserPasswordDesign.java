package kr.co.sist.car_sell.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import kr.co.sist.car_sell.event.ModifyUserPasswordEvt;

public class ModifyUserPasswordDesign extends JDialog {

	private JPasswordField jpfPw, jpfNewPw, jpfNewPwCheck;
	private JLabel jlWrnPw, jlWrnNewPw, jlWrnNewPwCheck;
	private JButton jbtnModify;

	public ModifyUserPasswordDesign(UserMenuDesign umd, boolean modal, int user_code) {
		super(umd, "비밀번호 수정", modal);

		// 폰트 설정
		Font fontTitle = new Font("맑은고딕", Font.BOLD, 30);
		Font fontTxtLabel = new Font("맑은고딕", Font.BOLD, 18);
		Font fontWrn = new Font("맑은고딕", Font.BOLD, 14);
		Font fontBtn = new Font("맑은고딕", Font.BOLD, 20);

		// 타이틀 라벨
		JLabel jlTitle = new JLabel("비밀번호 수정");
		jlTitle.setFont(fontTitle);

		// 텍스트필드명 라벨
		JLabel jlPw = new JLabel("현재 비밀번호");
		JLabel jlNewPw = new JLabel("새 비밀번호");
		JLabel jlNewPwCheck = new JLabel("새 비밀번호 확인");

		jlPw.setFont(fontTxtLabel);
		jlNewPw.setFont(fontTxtLabel);
		jlNewPwCheck.setFont(fontTxtLabel);

		// 경고 라벨
		jlWrnPw = new JLabel("비번 경고1");
		jlWrnNewPw = new JLabel("비번 경고2");
		jlWrnNewPwCheck = new JLabel("비번 경고3");
		
		//경고 라벨 폰트색
		jlWrnPw.setForeground(Color.red);
		jlWrnNewPw.setForeground(Color.red);
		jlWrnNewPwCheck.setForeground(Color.red);
		
		//경고 라벨 안 보이게
		jlWrnPw.setVisible(false);
		jlWrnNewPw.setVisible(false);
		jlWrnNewPwCheck.setVisible(false);

		// 텍스트필드
		jpfPw = new JPasswordField("");
		jpfNewPw = new JPasswordField("");
		jpfNewPwCheck = new JPasswordField("");

		// 버튼
		jbtnModify = new JButton("비밀번호 수정");
		jbtnModify.setFont(fontBtn);
		jbtnModify.setForeground(Color.white);
		jbtnModify.setBackground(new Color(37, 157, 237));
		jbtnModify.setSize(this.WIDTH, 60);

		// 패널
		JPanel jpNorth = new JPanel();
		JPanel jpCenter = new JPanel();
		JPanel jpSouth = new JPanel();

		// 세트로 들어갈 패널
		JPanel jpPw = new JPanel();
		JPanel jpNewPw = new JPanel();
		JPanel jpNewPwCheck = new JPanel();


		// 각각 패널에 세로 3칸짜리 레이아웃 넣기
		jpPw.setLayout(new GridLayout(3, 1));
		jpNewPw.setLayout(new GridLayout(3, 1));
		jpNewPwCheck.setLayout(new GridLayout(3, 1));

		// 3개의 패널을 center 패널에 삽입.
		jpCenter.setLayout(new GridLayout(3, 1));

		// 타이틀 배치
		jpNorth.add(jlTitle);

		// 각 패널 여백
		jpPw.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));// 상좌하우 여백
		jpNewPw.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));// 상좌하우 여백
		jpNewPwCheck.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));// 상좌하우 여백

		// jlPw 패널 배치
		jpPw.add(jlPw);
		jpPw.add(jpfPw);
		jpPw.add(jlWrnPw);
//				jpPw.setSize(100, 100);
		jpCenter.add(jpPw);

		// jlNewPw 패널 배치
		jpNewPw.add(jlNewPw);
		jpNewPw.add(jpfNewPw);
		jpNewPw.add(jlWrnNewPw);

		jpCenter.add(jpNewPw);

		// jlNewPwCheck 패널 배치
		jpNewPwCheck.add(jlNewPwCheck);
		jpNewPwCheck.add(jpfNewPwCheck);
		jpNewPwCheck.add(jlWrnNewPwCheck);
		jpCenter.add(jpNewPwCheck);

		// 버튼 배치
		jpSouth.add(jbtnModify);



		// 배경색깔
		jpPw.setBackground(Color.white);
		jpNewPw.setBackground(Color.white);
		jpNewPwCheck.setBackground(Color.white);

		jpCenter.setBackground(Color.white);
		jpNorth.setBackground(Color.white);

		// 스크롤 패널을 center에 삽입
		// 스크롤 패널 생성
//		JScrollPane jsp = new JScrollPane(jpCenter);

		// north, center 패널 배치
		add("North", jpNorth);
//		add("Center", jsp);
		add("Center", jpCenter);
		add("South", jpSouth);

		//이벤트 리스너 등록
		ModifyUserPasswordEvt mupe = new ModifyUserPasswordEvt(this, user_code);
		jbtnModify.addActionListener(mupe);
		
		// 창 설정
		setBounds(umd.getX() + 30, umd.getY() + 30, umd.getWidth() - 100, umd.getHeight()); // 부모좌표를 가져올 수 있음.
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}// ModifyUserInfoDesign

	public JPasswordField getJpfPw() {
		return jpfPw;
	}

	public JPasswordField getJpfNewPw() {
		return jpfNewPw;
	}

	public JPasswordField getJpfNewPwCheck() {
		return jpfNewPwCheck;
	}

	public JLabel getJlWrnPw() {
		return jlWrnPw;
	}

	public JLabel getJlWrnNewPw() {
		return jlWrnNewPw;
	}

	public JLabel getJlWrnNewPwCheck() {
		return jlWrnNewPwCheck;
	}

	public JButton getJbtnModify() {
		return jbtnModify;
	}

}// class
