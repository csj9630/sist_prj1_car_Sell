package kr.co.sist.car_sell.design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import kr.co.sist.car_sell.design.SettlementDesign;

public class dialogTest extends JFrame implements ActionListener{
	
	public dialogTest() {
		super("확인용");
		JButton jbnTest = new JButton("확인용 버튼");
		
		add(jbnTest);
		jbnTest.addActionListener(this);
		setBounds(100, 100, 400, 600);
	    setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		SettlementDesign smd = new SettlementDesign(this);
	}
}
