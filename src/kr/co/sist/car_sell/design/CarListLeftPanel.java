package kr.co.sist.car_sell.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import kr.co.sist.car_sell.event.CarListEvt;

public class CarListLeftPanel extends JFrame {
	
	private static JPanel jpLeft;
	
	private CarListDesign cld;
	
	public CarListLeftPanel(CarListDesign cld) {
			super("쌍용중고차");
			
			this.cld = cld;
			
			// 사이트명 선언
			jpLeft = new JPanel();
			
			// 필터링_브랜드명 선언
			JCheckBox jcbBrand1 = new JCheckBox("Audi");
			JCheckBox jcbBrand2 = new JCheckBox("BMW");
			JCheckBox jcbBrand3 = new JCheckBox("Cadillac");
			JCheckBox jcbBrand4 = new JCheckBox("Chevrolet");
			JCheckBox jcbBrand5 = new JCheckBox("Chrysler");
			JCheckBox jcbBrand6 = new JCheckBox("Dodge");
			JCheckBox jcbBrand7 = new JCheckBox("Ferrari");
			JCheckBox jcbBrand8 = new JCheckBox("Ford");
			JCheckBox jcbBrand9 = new JCheckBox("Genesis");
			JCheckBox jcbBrand10 = new JCheckBox("Hyundai");
			JCheckBox jcbBrand11 = new JCheckBox("KGM");
			JCheckBox jcbBrand12 = new JCheckBox("KIA");
			JCheckBox jcbBrand13 = new JCheckBox("Lamborghini");
			JCheckBox jcbBrand14 = new JCheckBox("Lexus");
			JCheckBox jcbBrand15 = new JCheckBox("Lincoln");
			JCheckBox jcbBrand16 = new JCheckBox("Mercedes-Benz");
			JCheckBox jcbBrand17 = new JCheckBox("Nissan");
			JCheckBox jcbBrand18 = new JCheckBox("Peugeot");
			JCheckBox jcbBrand19 = new JCheckBox("Porsche");
			JCheckBox jcbBrand20 = new JCheckBox("Renault");
			JCheckBox jcbBrand21 = new JCheckBox("Rolls-Royce");
			JCheckBox jcbBrand22 = new JCheckBox("Subaru");
			JCheckBox jcbBrand23 = new JCheckBox("Tesla");
			JCheckBox jcbBrand24 = new JCheckBox("Toyota");
			JCheckBox jcbBrand25 = new JCheckBox("Volkswagen");
			JCheckBox jcbBrand26 = new JCheckBox("Volvo");
			
			// 필터링_유종 선언
			JCheckBox jcbFuel1 = new JCheckBox("Gasoline");
			JCheckBox jcbFuel2 = new JCheckBox("Diesel");
			JCheckBox jcbFuel3 = new JCheckBox("Hybrid");
			JCheckBox jcbFuel4 = new JCheckBox("LPG");
			JCheckBox jcbFuel5 = new JCheckBox("Electric");
			
			// 필터링_배경 선언
			JLabel jlblFilterText = new JLabel("필터링");
			
			// 필터링_카테고리 선언
			JPanel jpBrand = new JPanel(new GridLayout(0, 1));
			JPanel jpFuel = new JPanel(new GridLayout(0, 1));
			
			// 필터링_카테고리 스크롤바 선언
			JScrollPane jspBrand = new JScrollPane(jpBrand);
			JScrollPane jspFuel = new JScrollPane(jpFuel);
			
			jspBrand.setBorder(BorderFactory.createLineBorder(new Color(0xC0C0C0), 1));
			jspFuel.setBorder(BorderFactory.createLineBorder(new Color(0xC0C0C0), 1));
			
			// 필터링_브랜드명 추가
			jpBrand.add(jcbBrand1);
			jpBrand.add(jcbBrand2);
			jpBrand.add(jcbBrand3);
			jpBrand.add(jcbBrand4);
			jpBrand.add(jcbBrand5);
			jpBrand.add(jcbBrand6);
			jpBrand.add(jcbBrand7);
			jpBrand.add(jcbBrand8);
			jpBrand.add(jcbBrand9);
			jpBrand.add(jcbBrand10);
			jpBrand.add(jcbBrand11);
			jpBrand.add(jcbBrand12);
			jpBrand.add(jcbBrand13);
			jpBrand.add(jcbBrand14);
			jpBrand.add(jcbBrand15);
			jpBrand.add(jcbBrand16);
			jpBrand.add(jcbBrand17);
			jpBrand.add(jcbBrand18);
			jpBrand.add(jcbBrand19);
			jpBrand.add(jcbBrand20);
			jpBrand.add(jcbBrand21);
			jpBrand.add(jcbBrand22);
			jpBrand.add(jcbBrand23);
			jpBrand.add(jcbBrand24);
			jpBrand.add(jcbBrand25);
			jpBrand.add(jcbBrand26);
			
			// 필터링_유종 추가
			jpFuel.add(jcbFuel1);
			jpFuel.add(jcbFuel2);
			jpFuel.add(jcbFuel3);
			jpFuel.add(jcbFuel4);
			jpFuel.add(jcbFuel5);
			
			// 각 요소 font 지정
			jlblFilterText.setFont(new Font("맑은 고딕", Font.BOLD, 40));
			jlblFilterText.setHorizontalAlignment(JLabel.CENTER);
			jlblFilterText.setVerticalAlignment(JLabel.CENTER);
			
			jcbBrand1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand3.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand4.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand5.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand6.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand7.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand8.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand9.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand10.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand11.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand12.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand13.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand14.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand15.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand16.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand17.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand18.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand19.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand20.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand21.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand22.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand23.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand24.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand25.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbBrand26.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			
			jcbFuel1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbFuel2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbFuel3.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbFuel4.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbFuel5.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			
			// 각 요소 색상 지정
			jpLeft.setBackground(new Color(0x808080));
			jlblFilterText.setForeground(new Color(0x000000));
			jspBrand.setBackground(new Color(0xFFFFFF));
			jspFuel.setBackground(new Color(0xFFFFFF));
			jspBrand.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 1));
			jspFuel.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 1));
			
			jcbBrand1.setForeground(new Color(0x000000));
			jcbBrand2.setForeground(new Color(0x000000));
			jcbBrand3.setForeground(new Color(0x000000));
			jcbBrand4.setForeground(new Color(0x000000));
			jcbBrand5.setForeground(new Color(0x000000));
			jcbBrand6.setForeground(new Color(0x000000));
			jcbBrand7.setForeground(new Color(0x000000));
			jcbBrand8.setForeground(new Color(0x000000));
			jcbBrand9.setForeground(new Color(0x000000));
			jcbBrand10.setForeground(new Color(0x000000));
			jcbBrand11.setForeground(new Color(0x000000));
			jcbBrand12.setForeground(new Color(0x000000));
			jcbBrand13.setForeground(new Color(0x000000));
			jcbBrand14.setForeground(new Color(0x000000));
			jcbBrand15.setForeground(new Color(0x000000));
			jcbBrand16.setForeground(new Color(0x000000));
			jcbBrand17.setForeground(new Color(0x000000));
			jcbBrand18.setForeground(new Color(0x000000));
			jcbBrand19.setForeground(new Color(0x000000));
			jcbBrand20.setForeground(new Color(0x000000));
			jcbBrand21.setForeground(new Color(0x000000));
			jcbBrand22.setForeground(new Color(0x000000));
			jcbBrand23.setForeground(new Color(0x000000));
			jcbBrand24.setForeground(new Color(0x000000));
			jcbBrand25.setForeground(new Color(0x000000));
			jcbBrand26.setForeground(new Color(0x000000));
			
			jcbFuel1.setForeground(new Color(0x000000));
			jcbFuel2.setForeground(new Color(0x000000));
			jcbFuel3.setForeground(new Color(0x000000));
			jcbFuel4.setForeground(new Color(0x000000));
			jcbFuel5.setForeground(new Color(0x000000));
			
			// 레이아웃 구성
			jpLeft.setLayout(null);
			jlblFilterText.setBounds(5, 5, 274, 120);
			jspBrand.setBounds(5, 125, 274, 400);
			jspFuel.setBounds(5, 530, 274, 181);
			
			// 컴포넌트 배치
			jpLeft.add(jlblFilterText);
			jpLeft.add(jspBrand);
			jpLeft.add(jspFuel);
			
	} // CarListLeftPanel
	
	public static JPanel getJpLeft() {
		return jpLeft;
	} // getJpLeft
	
}
