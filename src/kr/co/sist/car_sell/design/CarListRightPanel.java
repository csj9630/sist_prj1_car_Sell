package kr.co.sist.car_sell.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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
import kr.co.sist.car_sell.service.ImageService;

public class CarListRightPanel extends JFrame {
	
	private JButton jbtnImage;
	private JTextField jtfBrand, jtfCarName, jtfOilType, jtfPrice;
    private JPanel jpScroll;
    private JScrollPane jspRight;
    private JLabel jlblList;
	private static JPanel jpRight;
	private int[] prodCodeArr;
	private int prodCode;
	private String userType;
	private int userCode;
//	private List<ImageIcon> iiList;
	
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
			prodCodeArr = cls.getFilteredCars(null, null, userType);
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
            
            jlblList = new JLabel("라벨");
            jlblList.setLayout(null);
            jlblList.setFont(new Font("맑은 고딕", Font.BOLD, 124));
            jlblList.setOpaque(true);
            jlblList.setHorizontalAlignment(JLabel.CENTER);
            jlblList.setVerticalAlignment(JLabel.CENTER);
            jlblList.setForeground(new Color(0x808080));
            jlblList.setBackground(new Color(0x808080));
            jpScroll.add(jlblList);
            
			ImageService is = new ImageService();
			ImageIcon ii = is.loadDBImage(productCode);
			Image origImg = ii.getImage();
			Image resizedImg = origImg.getScaledInstance(284, 160, Image.SCALE_SMOOTH);
			ImageIcon reii = new ImageIcon(resizedImg);
            
            jbtnImage = new JButton(reii);
            jbtnImage.setBounds(0, 3, 284, 160);
            jbtnImage.setBackground(new Color(0x808080));
            jbtnImage.addActionListener(ae -> {
            	new CarInfoDesign(cld, productCode, userType, userCode).setVisible(true);
            });
            jlblList.add(jbtnImage);
            
            jtfBrand = new JTextField(" " + cDTO.getBrandName());
            jtfCarName = new JTextField(" " + cDTO.getCarName());
            jtfOilType = new JTextField(" " + cDTO.getOil());
            jtfPrice = new JTextField(cDTO.getPrice() + "만원 ");
            
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
	
	public void refreshCarList(int[] prodCodeArr) throws Exception {
		
		// 1. 기존의 모든 차량 정보(컴포넌트)를 패널에서 제거합니다.
		jpScroll.removeAll();
		
		// 2. 새로운 차량 코드 리스트(carCodes)를 기반으로 UI 컴포넌트를 다시 만듭니다.
//		jpScroll = new JPanel();
//		jpScroll.setLayout(new GridLayout(0, 1));
//		
//		jspRight = new JScrollPane(jpScroll);
//		jspRight.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
//		jspRight.setBackground(new Color(0x808080));
//		jspRight.getVerticalScrollBar().setUnitIncrement(20);
		
		try {
			for (int prodCode : prodCodeArr) {
				CarDTO cDTO = this.cls.getProductDetails(prodCode);
				
				jlblList = new JLabel("라벨");
	            jlblList.setLayout(null);
	            jlblList.setFont(new Font("맑은 고딕", Font.BOLD, 124));
	            jlblList.setOpaque(true);
	            jlblList.setHorizontalAlignment(JLabel.CENTER);
	            jlblList.setVerticalAlignment(JLabel.CENTER);
	            jlblList.setForeground(new Color(0x808080));
	            jlblList.setBackground(new Color(0x808080));
	            jpScroll.add(jlblList);
	            
				ImageService is = new ImageService();
				ImageIcon ii = is.loadDBImage(prodCode);
				Image origImg = ii.getImage();
				Image resizedImg = origImg.getScaledInstance(284, 160, Image.SCALE_SMOOTH);
				ImageIcon reii = new ImageIcon(resizedImg);
	            
	            jbtnImage = new JButton(reii);
	            jbtnImage.setBounds(0, 3, 284, 160);
	            jbtnImage.setBackground(new Color(0x808080));
	            jbtnImage.addActionListener(ae -> {
	            	new CarInfoDesign(cld, prodCode, userType, userCode).setVisible(true);
	            });
	            jlblList.add(jbtnImage);
	            
	            jtfBrand = new JTextField(" " + cDTO.getBrandName());
	            jtfCarName = new JTextField(" " + cDTO.getCarName());
	            jtfOilType = new JTextField(" " + cDTO.getOil());
	            jtfPrice = new JTextField(cDTO.getPrice() + "만원 ");
	            
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
			
//			jspRight.setBounds(5, 5, 826, 716);
//			
//			jpRight.add(jspRight);
			
		} catch (SQLException e) {
			e.printStackTrace();
			// 오류 처리
		}
		
		// 3. 패널의 레이아웃을 새로고침하고 다시 그리도록 강제합니다. (필수!)
		jpScroll.revalidate(); // 레이아웃을 다시 계산
		jpScroll.repaint();    // 화면을 다시 그림
	}
	
	public static JPanel getJpRight() {
		return jpRight;
	} // getJpRight
	
	public JButton getJbtnImage() {
		return jbtnImage;
	} // getJbtnImage
	
	public int getProdCode() {
		return prodCode;
	} // getProdCode
	
	public int[] getProdCodeArr() {
		return prodCodeArr;
	} // getProdCodeArr
	
	public void setProdCodeArr(int[] prodCodeArr) {
		this.prodCodeArr = prodCodeArr;
	} // setProdCodeArr
	
}
