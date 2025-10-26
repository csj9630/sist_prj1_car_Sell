package kr.co.sist.car_sell.design.loginRegister;

import javax.swing.*;
import java.awt.Font;
import javax.swing.JDialog; 

public class LoginDesign extends JDialog { 

	private JTextField jtfId;
	private JPasswordField jpfPass;
	private JButton jbtnLogin;
	private JButton jbtnRegister;
	private String type;

	public LoginDesign(JFrame owner, String type) {
		super(owner, ("u".equals(type) ? "사용자 로그인" : "관리자 로그인"), true);
		
		this.type = type;

		jtfId = new JTextField();
		jpfPass = new JPasswordField();
		jbtnLogin = new JButton("로그인");

		JLabel jlblIdLabel = new JLabel("아이디를 입력하세요");
		JLabel jlblPassLabel = new JLabel("비밀번호를 입력하세요");

		String title = "";
		if ("u".equals(type)) {
			title = "사용자 로그인";
		} else if ("a".equals(type)) {
			title = "관리자 로그인";
		}

		JLabel jlblTitle = new JLabel("중고차 관리 사이트");
		jlblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));

		JLabel jlblSubTitle = new JLabel(title);
		jlblSubTitle.setFont(new Font("SansSerif", Font.BOLD, 18));

		setLayout(null);

		jlblTitle.setBounds(140, 30, 250, 30);
		jlblSubTitle.setBounds(190, 60, 200, 30);

		jlblIdLabel.setBounds(80, 100, 200, 20);
		jtfId.setBounds(80, 120, 200, 40);

		jlblPassLabel.setBounds(80, 170, 200, 20);
		jpfPass.setBounds(80, 190, 200, 40);

		jbtnLogin.setBounds(300, 120, 100, 110);

		if ("u".equals(type)) {
			jbtnRegister = new JButton("회원가입");
			jbtnRegister.setBounds(300, 240, 100, 25);
			add(jbtnRegister);
		}

		add(jlblTitle);
		add(jlblSubTitle);
		add(jlblIdLabel);
		add(jtfId);
		add(jlblPassLabel);
		add(jpfPass);
		add(jbtnLogin);

		setSize(500, 320);
		setResizable(false);
		setLocationRelativeTo(owner); 
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public JTextField getJtfId() {
		return jtfId;
	}

	public JPasswordField getJpfPass() {
		return jpfPass;
	}

	public JButton getJbtnLogin() {
		return jbtnLogin;
	}

	public JButton getJbtnRegister() {
		return jbtnRegister;
	}
	
	public String getLoginType() {
		return type;
	}
	
}