package kr.co.sist.car_sell.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import kr.co.sist.car_sell.dto.CarDTO;
import kr.co.sist.car_sell.service.CarListService;

public class CarListRightPanel extends JFrame {
	
	private JButton jbtnImage;
	private JTextField jtfBrand, jtfCarName, jtfOilType, jtfPrice;
    private JPanel jpScroll;
    private JScrollPane jspRight;
    private JLabel jlblList;
    private JLabel[] jlblListArr;
    private JButton[] jbtnImageArr;
    private JTextField[] jtfBrandArr, jtfCarNameArr, jtfOilTypeArr, jtfPriceArr;
    private int buttonIndex;
	private static JPanel jpRight;
	private int[] prodCodeArr;
	private int prodCode;
	private String userType;
	private int userCode;
	
	private CarListDesign cld;
	private CarListService cls;
	private CarDTO cDTO;
	
	public CarListRightPanel(CarListDesign cld, String userType, int userCode) {
		
		this.cld = cld;
		cls = cld.getCls();
		this.userType = userType;
		this.userCode = userCode;
		
		jpRight = new JPanel(null);
		jpScroll = new JPanel();
		jpScroll.setLayout(new GridLayout(0, 1));
		
		jspRight = new JScrollPane(jpScroll);
		jspRight.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jspRight.setBackground(new Color(0x808080));
		jspRight.getVerticalScrollBar().setUnitIncrement(20);
		
		try {
			prodCodeArr = cls.getAvailableProductCodes();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int code : prodCodeArr) {
            // [중요] 람다에서 사용하기 위해 final 변수로 복사
            final int productCode = code;

            try {
				cDTO = cls.getProductDetails(productCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
            ImageIcon ii = new ImageIcon(getClass().getResource("/images_prev/img_" + 1 + "_prev.png"));
            jbtnImage = new JButton(ii);
            
            jlblList = new JLabel("라벨");
            jtfBrand = new JTextField(" " + cDTO.getBrandName());
            jtfCarName = new JTextField(" " + cDTO.getCarName());
            jtfOilType = new JTextField(" " + cDTO.getOil());
            jtfPrice = new JTextField(cDTO.getPrice() + "만원 ");
			
			jlblList.setLayout(null);
			jlblList.setFont(new Font("맑은 고딕", Font.BOLD, 124));
			jlblList.setOpaque(true);
			jlblList.setHorizontalAlignment(JLabel.CENTER);
			jlblList.setVerticalAlignment(JLabel.CENTER);
			jlblList.setForeground(new Color(0x808080));
			jlblList.setBackground(new Color(0x808080));
			jpScroll.add(jlblList);
			
			jbtnImage.setBounds(0, 3, 284, 160);
			jbtnImage.setBackground(new Color(0x808080));
			jbtnImage.addActionListener(ae -> {
				new CarInfoDesign(productCode, userType, userCode);
			});
			
			jlblList.add(jbtnImage);
			
			jtfBrand.setBounds(289, 4, 285, 54);
			jtfBrand.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jtfBrand.setBorder(null);
			jtfBrand.setEditable(false);
			jtfBrand.setBackground(new Color(0xFFFFFF));
			jlblList.add(jtfBrand);
			
			jtfCarName.setBounds(289, 58, 285, 50);
			jtfCarName.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jtfCarName.setBorder(null);
			jtfCarName.setEditable(false);
			jtfCarName.setBackground(new Color(0xFFFFFF));
			jlblList.add(jtfCarName);
			
			jtfOilType.setBounds(289, 108, 285, 54);
			jtfOilType.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jtfOilType.setBorder(null);
			jtfOilType.setEditable(false);
			jtfOilType.setBackground(new Color(0xFFFFFF));
			jlblList.add(jtfOilType);
			
			jtfPrice.setBounds(574, 4, 220, 158);
			jtfPrice.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jtfPrice.setBorder(null);
			jtfPrice.setHorizontalAlignment(JTextField.RIGHT);
			jtfPrice.setEditable(false);
			jtfPrice.setBackground(new Color(0xFFFFFF));
			jlblList.add(jtfPrice);
			
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
	
	public JButton[] getJbtnImageArr() {
		return jbtnImageArr;
	} // getJbtnImageArr
	
	public int getProdCode() {
		return prodCode;
	}
	
	public int getButtonIndex() {
		return buttonIndex;
	}
	
}
