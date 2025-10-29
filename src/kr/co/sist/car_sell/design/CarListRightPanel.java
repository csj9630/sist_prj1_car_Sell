package kr.co.sist.car_sell.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import kr.co.sist.car_sell.event.CarListEvt;

public class CarListRightPanel extends JFrame {
	
	private JButton jbtnImage;
	private JTextField jtfBrand, jtfCarName, jtfFuelType, jtfPrice;
    private JPanel jpScroll;
    private JScrollPane jspRight;
    private JLabel jlblList;
	private static JPanel jpRight;
	
	private CarListDesign cld;
	private CarListNorthPanel clnp;
	private CarListLeftPanel cllp;
	private CarListEvt cle;
	
	public CarListRightPanel(CarListDesign cld) {
		
		this.cld = cld;
		
		jpRight = new JPanel(null);
		jpScroll = new JPanel();
		jpScroll.setLayout(new GridLayout(0, 1));
		
		jspRight = new JScrollPane(jpScroll);
		jspRight.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jspRight.setBackground(new Color(0x808080));
		
		int rowCnt = 50;
		
		JLabel[] jlblListArr = new JLabel[rowCnt];
		JButton[] jbtnImageArr = new JButton[rowCnt];
		JTextField[] jtfBrandArr = new JTextField[rowCnt];
		JTextField[] jtfCarNameArr = new JTextField[rowCnt];
		JTextField[] jtfFuelTypeArr = new JTextField[rowCnt];
		JTextField[] jtfPriceArr = new JTextField[rowCnt];
		
		ImageIcon ii = new ImageIcon("C:/dev/car_img/genesis/g80/g80_1_prev.png");
		
		for(int i = 0; i < rowCnt; i++) {
			
			jlblListArr[i] = new JLabel("라벨");
			jbtnImageArr[i] = new JButton(ii);
			jtfBrandArr[i] = new JTextField(" Genesis");
			jtfCarNameArr[i] = new JTextField(" G80");
			jtfFuelTypeArr[i] = new JTextField(" 휘발유");
			jtfPriceArr[i] = new JTextField("4,390만원 ");
			
			jlblList = jlblListArr[i];
			jbtnImage = jbtnImageArr[i];
			jtfBrand = jtfBrandArr[i];
			jtfCarName = jtfCarNameArr[i];
			jtfFuelType = jtfFuelTypeArr[i];
			jtfPrice = jtfPriceArr[i];
			
			jlblList.setLayout(null);
			jlblList.setFont(new Font("맑은 고딕", Font.BOLD, 124));
			jlblList.setOpaque(true);
			jlblList.setHorizontalAlignment(JLabel.CENTER);
			jlblList.setVerticalAlignment(JLabel.CENTER);
			jlblList.setForeground(new Color(0x808080));
			jlblList.setBackground(new Color(0x808080));
			
			jbtnImage.setBounds(0, 3, 284, 160);
			jbtnImage.setBackground(new Color(0x808080));
			
			jtfBrand.setBounds(289, 4, 356, 54);
			jtfBrand.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jtfBrand.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 1));
			jtfBrand.setEditable(false);
			jtfBrand.setBackground(new Color(0xFFFFFF));
			
			jtfCarName.setBounds(289, 58, 356, 50);
			jtfCarName.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jtfCarName.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 1));
			jtfCarName.setEditable(false);
			jtfCarName.setBackground(new Color(0xFFFFFF));
			
			jtfFuelType.setBounds(289, 108, 356, 54);
			jtfFuelType.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jtfFuelType.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 1));
			jtfFuelType.setEditable(false);
			jtfFuelType.setBackground(new Color(0xFFFFFF));
			
			jtfPrice.setBounds(645, 4, 149, 158);
			jtfPrice.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jtfPrice.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 1));
			jtfPrice.setHorizontalAlignment(4);
			jtfPrice.setEditable(false);
			jtfPrice.setBackground(new Color(0xFFFFFF));
			
			jlblList.add(jbtnImage);
			jlblList.add(jtfBrand);
			jlblList.add(jtfCarName);
			jlblList.add(jtfFuelType);
			jlblList.add(jtfPrice);
			
			jpScroll.add(jlblList);
			
		}
		
		jspRight.setBounds(5, 5, 826, 716);
		
		jpRight.add(jspRight);
	}
	
	public static JPanel getJpRight() {
		return jpRight;
	} // getJpRight
	
	public JButton getJbtnImage() {
		return jbtnImage;
	} // getJbtnImage
	
}
