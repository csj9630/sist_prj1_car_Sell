package kr.co.sist.car_sell.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import kr.co.sist.car_sell.service.CarListService;

public class CarListLeftPanel extends JFrame {
	
	private static JPanel jpLeft;
	
	private JCheckBox jcbBrand, jcbOil;
	private String[] brandArr, oilArr;
	
	private CarListDesign cld;
	private CarListService cls;
	
	public CarListLeftPanel(CarListDesign cld) {
			super("쌍용중고차");
			
			this.cld = cld;
			cls = cld.getCls();
			
			// 프로그램명
			jpLeft = new JPanel();
			jpLeft.setBackground(new Color(0x808080));
			jpLeft.setLayout(null);
			
			// 필터링_제목
			JLabel jlblFilterText = new JLabel("필터링");
			jlblFilterText.setFont(new Font("맑은 고딕", Font.BOLD, 40));
			jlblFilterText.setHorizontalAlignment(JLabel.CENTER);
			jlblFilterText.setVerticalAlignment(JLabel.CENTER);
			jlblFilterText.setForeground(new Color(0x000000));
			jlblFilterText.setBounds(5, 5, 274, 120);
			jpLeft.add(jlblFilterText);
			
			// 필터링_브랜드
			JPanel jpBrand = new JPanel(new GridLayout(0, 1));
			JScrollPane jspBrand = new JScrollPane(jpBrand);
			
			try {
				brandArr = cls.getAvailableBrands();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} // end try ~ catch
			
			for (String brand : brandArr) {
				// [중요] 람다에서 사용하기 위해 final 변수로 복사
				final String brandName = brand;
				
				jcbBrand = new JCheckBox(brandName);
				jcbBrand.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
				jcbBrand.setForeground(new Color(0x000000));
				jpBrand.add(jcbBrand);
				
			} // end for
			
			jspBrand.setBorder(BorderFactory.createLineBorder(new Color(0xC0C0C0), 1));
			jspBrand.setBackground(new Color(0xFFFFFF));
			jspBrand.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 1));
			jspBrand.getVerticalScrollBar().setUnitIncrement(20);
			jspBrand.setBounds(5, 125, 274, 400);
			jpLeft.add(jspBrand);
			
			// 필터링_유종
			JPanel jpOil = new JPanel(new GridLayout(0, 1));
			JScrollPane jspOil = new JScrollPane(jpOil);
			
			try {
				oilArr = cls.getAvailableOils();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} // end try ~ catch
			
			for (String oil : oilArr) {
				// [중요] 람다에서 사용하기 위해 final 변수로 복사
				final String oilType = oil;
				
				jcbOil = new JCheckBox(oilType);
				jcbOil.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
				jcbOil.setForeground(new Color(0x000000));
				jpOil.add(jcbOil);
				
			} // end for
			
			jspOil.setBorder(BorderFactory.createLineBorder(new Color(0xC0C0C0), 1));
			jspOil.setBackground(new Color(0xFFFFFF));
			jspOil.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 1));
			jspOil.getVerticalScrollBar().setUnitIncrement(20);
			jspOil.setBounds(5, 530, 274, 181);
			jpLeft.add(jspOil);
			
	} // CarListLeftPanel
	
	public static JPanel getJpLeft() {
		return jpLeft;
	} // getJpLeft
	
}
