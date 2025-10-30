package kr.co.sist.car_sell.design;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.car_sell.event.FirstSelectEvt;

public class FirstSelectDesign extends JFrame {

	private JButton jbtnUser;
	private JButton jbtnAdmin;

	public FirstSelectDesign() {
		super("중고차 관리 사이트");

		jbtnUser = new JButton("사용자");
		jbtnAdmin = new JButton("관리자");

		Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
		jbtnUser.setFont(buttonFont);
		jbtnAdmin.setFont(buttonFont);

		Dimension btnSize = new Dimension(250, 70);
		jbtnUser.setPreferredSize(btnSize);
		jbtnAdmin.setPreferredSize(btnSize);

		JLabel jlblTitle = new JLabel("중고차 관리 사이트");
		jlblTitle.setFont(new Font("SansSerif", Font.BOLD, 28));

		JLabel jlblSubTitle = new JLabel("Team2");
		jlblSubTitle.setFont(new Font("SansSerif", Font.PLAIN, 18));

		JPanel jpTitleCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpTitleCenter.add(jlblTitle);

		JPanel jpTitleSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpTitleSouth.add(jlblSubTitle);

		JPanel jpTitle = new JPanel(new BorderLayout());
		jpTitle.add(jpTitleCenter, BorderLayout.CENTER);
		jpTitle.add(jpTitleSouth, BorderLayout.SOUTH);

		JPanel jpButtons = new JPanel(new GridLayout(2, 1, 0, 10));
		jpButtons.add(jbtnUser);
		jpButtons.add(jbtnAdmin);

		JPanel jpButtonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
		jpButtonContainer.add(jpButtons);

		add(jpTitle, BorderLayout.NORTH);
		add(jpButtonContainer, BorderLayout.CENTER);
		
		new FirstSelectEvt(this);
		
		
		setSize(500, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public JButton getJbtnUser() {
		return jbtnUser;
	}

	public JButton getJbtnAdmin() {
		return jbtnAdmin;
	}

}