package kr.co.sist.car_sell.design;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.car_sell.dto.CarDTO;
import kr.co.sist.car_sell.service.CarInfoService;

public class CarInfoCenterPanel extends JDialog {
	
	private JButton jbtnImage1, jbtnImageIcon1, jbtnImage2, jbtnImageIcon2, jbtnImage3, jbtnImageIcon3, jbtnImage4, jbtnImageIcon4, jbtnPurchase;
	private JLabel	jlblCenterScroll, jlblImageBackground,jlblProductName, jlblBrand, jlblCarName, jlblPrice, jlblYear,
					jlblDistance, jlblDistanceKm, jlblCc, jlblOil, jlblNumberPlate, jlblOption, jlblDefect, jlblAccident, jlblRepair;
	private JTextField jtfBrand, jtfCarName, jtfPrice, jtfYear1, jtfYear2, jtfDistance, jtfCc, jtfNumberPlate;
	private JTextArea jtaOption, jtaDefect, jtaAccident, jtaRepair;
	private JScrollPane jspCenter, jspOption, jspDefect, jspAccident, jspRepair;
	private JPanel jpCenterScrollCover, jpImage, jpDetail, jpOption, jpDefect, jpAccident, jpRepair;
	private JCheckBox jcbOption, jcbDefect, jcbAccident, jcbRepair;
	private DefaultComboBoxModel<String> dcbmStatSold, dcbmOil;
	private JComboBox<String> jcbStatSold, jcbOil;
	private String carBrand, carName;
	private String[] oilArr, optionNameArr, carOptionNameArr, defectNameArr, carDefectNameArr, accidentNameArr, carAccidentNameArr, repairNameArr, carRepairNameArr;
	private int[] optionCodeArr;
	private CardLayout cl;
	private Map<JCheckBox, String> optionMap;
	
	private CarInfoDesign cid;
	private CarInfoService cis;
	private CarDTO cDTO;
	private int prodCode;
	private String userType;
	private int userCode;
	
	private static JPanel jpCenter;
	
	public CarInfoCenterPanel(CarInfoDesign cid, int prodCode, String userType, int userCode) {
		
		this.cid = cid;
		this.prodCode = prodCode;
		this.userType = userType;
		this.userCode = userCode;
		cis = cid.getCis();
		
		try {
			cDTO = cis.getProductDetails(prodCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		optionMap = new HashMap<>();
		
		// 차량 정보 구역
		jpCenter = new JPanel(null);
		
		// 차량 정보 구역 - 스크롤 바
		jpCenterScrollCover = new JPanel(new GridLayout(0, 1));
		jspCenter = new JScrollPane(jpCenterScrollCover);
		jspCenter.setBorder(null);
		jspCenter.setBackground(new Color(0xFFFFFF));
		jspCenter.setBounds(0, 0, 1185, 666);
		jspCenter.getVerticalScrollBar().setUnitIncrement(15);
		jpCenter.add(jspCenter);
		
		// 차량 정보 구역 - 크기 설정
		jlblCenterScroll = new JLabel("I");
		jlblCenterScroll.setFont(new Font("맑은 고딕", Font.BOLD, 1143));
		jlblCenterScroll.setOpaque(true);
		jlblCenterScroll.setBackground(new Color(0xFFFFFF));
		jlblCenterScroll.setForeground(new Color(0xFFFFFF));
		jlblCenterScroll.setHorizontalAlignment(JLabel.CENTER);
		jlblCenterScroll.setVerticalAlignment(JLabel.CENTER);
		jpCenterScrollCover.add(jlblCenterScroll);
		
		// 차량 이미지
		jlblImageBackground = new JLabel();
		jlblImageBackground.setOpaque(true);
		jlblImageBackground.setBackground(new Color(0x808080));
		jlblImageBackground.setBounds(10, 10, 650, 465);
		jlblCenterScroll.add(jlblImageBackground);
		
		// 차량 이미지 - 출력 위치
		jpImage = new JPanel();
		cl = new CardLayout();
		jpImage.setLayout(cl);
		jpImage.setBackground(new Color(0xC0C0C0));
		jpImage.setBounds(5, 5, 640, 360);
		jlblImageBackground.add(jpImage);
		
		// 차량 이미지 - 출력 이미지
		ImageIcon ii1 = new ImageIcon(getClass().getResource("/images/img_" + 1 + "_1.png"));
		ImageIcon ii2 = new ImageIcon(getClass().getResource("/images/img_" + 1 + "_2.png"));
		ImageIcon ii3 = new ImageIcon(getClass().getResource("/images/img_" + 1 + "_3.png"));
		ImageIcon ii4 = new ImageIcon(getClass().getResource("/images/img_" + 1 + "_4.png"));
		
		jbtnImage1 = new JButton(ii1);
		jbtnImage2 = new JButton(ii2);
		jbtnImage3 = new JButton(ii3);
		jbtnImage4 = new JButton(ii4);
		
		jpImage.add(jbtnImage1, "inputA");
		jpImage.add(jbtnImage2, "inputB");
		jpImage.add(jbtnImage3, "inputC");
		jpImage.add(jbtnImage4, "inputD");
		
		cl.show(jpImage, "inputA");
		
		// 차량 이미지 - 이미지 선택
		ImageIcon ii1icon = new ImageIcon(getClass().getResource("/images_icon/img_" + 1 + "_1_icon.png"));
		ImageIcon ii2icon = new ImageIcon(getClass().getResource("/images_icon/img_" + 1 + "_2_icon.png"));
		ImageIcon ii3icon = new ImageIcon(getClass().getResource("/images_icon/img_" + 1 + "_3_icon.png"));
		ImageIcon ii4icon = new ImageIcon(getClass().getResource("/images_icon/img_" + 1 + "_4_icon.png"));
		
		jbtnImageIcon1 = new JButton(ii1icon);
		jbtnImageIcon2 = new JButton(ii2icon);
		jbtnImageIcon3 = new JButton(ii3icon);
		jbtnImageIcon4 = new JButton(ii4icon);
		
		jbtnImageIcon1.setBackground(new Color(0xC0C0C0));
		jbtnImageIcon2.setBackground(new Color(0xC0C0C0));
		jbtnImageIcon3.setBackground(new Color(0xC0C0C0));
		jbtnImageIcon4.setBackground(new Color(0xC0C0C0));
		
		jbtnImageIcon1.setBounds(5, 370, 160, 90);
		jbtnImageIcon2.setBounds(165, 370, 160, 90);
		jbtnImageIcon3.setBounds(325, 370, 160, 90);
		jbtnImageIcon4.setBounds(485, 370, 160, 90);
		
		jlblImageBackground.add(jbtnImageIcon1);
		jlblImageBackground.add(jbtnImageIcon2);
		jlblImageBackground.add(jbtnImageIcon3);
		jlblImageBackground.add(jbtnImageIcon4);
		
		// 차량명
		jlblProductName = new JLabel();
		jlblProductName.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		jlblProductName.setOpaque(true);
		jlblProductName.setBackground(new Color(0xFFFFFF));
		jlblProductName.setForeground(new Color(0xFFFFFF));
		jlblProductName.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jlblProductName.setBounds(10, 485, 650, 130);
		jlblCenterScroll.add(jlblProductName);
		
		// 차량명 - 브랜드 수정
		carBrand = cDTO.getBrandName();
		jtfBrand = new JTextField(carBrand);
		jtfBrand.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jtfBrand.setForeground(new Color(0x000000));
		jtfBrand.setBackground(new Color(0xFFFFFF));
		jtfBrand.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 1));
		jtfBrand.setBounds(15, 8, 620, 62);
		
		// 차량명 - 차종 수정
		carName = cDTO.getProdName();
		jtfCarName = new JTextField(carName);
		jtfCarName.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfCarName.setForeground(new Color(0x000000));
		jtfCarName.setBackground(new Color(0xFFFFFF));
		jtfCarName.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 1));
		jtfCarName.setBounds(15, 70, 620, 52);
		
		// 차량명 - 브랜드 표시
		jlblBrand = new JLabel(carBrand);
		jlblBrand.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jlblBrand.setForeground(new Color(0x000000));
		jlblBrand.setBackground(new Color(0xFFFFFF));
		jlblBrand.setBorder(null);
		jlblBrand.setBounds(15, 8, 620, 62);
		
		// 차량명 - 차종 표시
		jlblCarName = new JLabel(carName);
		jlblCarName.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblCarName.setForeground(new Color(0x000000));
		jlblCarName.setBackground(new Color(0xFFFFFF));
		jlblCarName.setBorder(null);
		jlblCarName.setBounds(15, 70, 620, 52);
		
		// 차량 가격 - 금액 수정
		jtfPrice = new JTextField(String.valueOf(cDTO.getPrice()));
		jtfPrice.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		jtfPrice.setForeground(new Color(0x000000));
		jtfPrice.setBackground(new Color(0xFFFFFF));
		jtfPrice.setHorizontalAlignment(JTextField.RIGHT);
		jtfPrice.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 1));
		jtfPrice.setBounds(675, 490, 399, 50);
		
		// 차량 가격 - 금액 표시
		jlblPrice = new JLabel(cDTO.getPrice() + "만원 ");
		jlblPrice.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		jlblPrice.setOpaque(true);
		jlblPrice.setForeground(new Color(0x000000));
		jlblPrice.setBackground(new Color(0xFFFFFF));
		jlblPrice.setHorizontalAlignment(4);
		jlblPrice.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jlblPrice.setBounds(670, 485, 488, 60);
		
		// 차량 구매
		jbtnPurchase = new JButton("구매하기");
		jbtnPurchase.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		jbtnPurchase.setForeground(new Color(0x000000));
		jbtnPurchase.setBackground(new Color(0xC0C0C0));
		jbtnPurchase.setHorizontalAlignment(0);
		jbtnPurchase.setVerticalAlignment(0);
		jbtnPurchase.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jbtnPurchase.setBounds(670, 555, 488, 60);
		
		// 차량 세부 정보 - 판매여부 수정
		dcbmStatSold = new DefaultComboBoxModel<String>();
		jcbStatSold = new JComboBox<String>(dcbmStatSold);
		dcbmStatSold.addElement("판매중");
		dcbmStatSold.addElement("판매완료");
		jcbStatSold.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		jcbStatSold.setForeground(new Color(0x000000));
		jcbStatSold.setBackground(new Color(0xFFFFFF));
		jcbStatSold.setBorder(null);
		jcbStatSold.setSelectedItem(cDTO.getSoldStat());
		jcbStatSold.setBounds(670, 555, 488, 60);
		
		if(userType.equals("a")) {
			jlblProductName.add(jtfBrand);
			jlblProductName.add(jtfCarName);
			jlblCenterScroll.add(jtfPrice);
			jlblCenterScroll.add(jcbStatSold);
		} else if(userType.equals("u")) {
			jlblCenterScroll.add(jbtnPurchase);
		} // end if ~ else if
		
		jlblProductName.add(jlblBrand);
		jlblProductName.add(jlblCarName);
		jlblCenterScroll.add(jlblPrice);
		
		// 차량 세부 정보
		jpDetail = new JPanel(null);
		jpDetail.setBackground(new Color(0x808080));
		jpDetail.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jpDetail.setBounds(670, 10, 488, 465);
		jlblCenterScroll.add(jpDetail);
		
		// 차량 세부 정보 - 연식
		String carYearInfo = cDTO.getCarYear().toString();
		String carYearInfoYear = carYearInfo.substring(0, carYearInfo.indexOf("-"));
		jlblYear = new JLabel(" 연식: " + carYearInfoYear + "년 ");
		jlblYear.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblYear.setOpaque(true);
		jlblYear.setForeground(new Color(0x000000));
		jlblYear.setBackground(new Color(0xFFFFFF));
		jlblYear.setBorder(null);
		jlblYear.setBounds(5, 5, 478, 42);
		
		// 차량 세부 정보 - 연식 연도
		jtfYear1 = new JTextField(carYearInfoYear);
		jtfYear1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfYear1.setForeground(new Color(0x000000));
		jtfYear1.setBackground(new Color(0xFFFFFF));
		jtfYear1.setHorizontalAlignment(4);
		jtfYear1.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 1));
		jtfYear1.setBounds(73, 0, 60, 42);
		
		// 차량 세부 정보 - 누적 주행거리 수정
		jtfDistance = new JTextField(String.valueOf(cDTO.getDistance()));
		jtfDistance.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfDistance.setForeground(new Color(0x000000));
		jtfDistance.setBackground(new Color(0xFFFFFF));
		jtfDistance.setHorizontalAlignment(JTextField.RIGHT);
		jtfDistance.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 1));
		jtfDistance.setBounds(129, 47, 102, 42);
		
		// 차량 세부 정보 - 누적 주행거리 수정 단위
		jlblDistanceKm = new JLabel("km");
		jlblDistanceKm.setOpaque(true);
		jlblDistanceKm.setBackground(new Color(0xFFFFFF));
		jlblDistanceKm.setForeground(new Color(0x000000));
		jlblDistanceKm.setBorder(null);
		jlblDistanceKm.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblDistanceKm.setBounds(231, 47, 50, 42);
		
		// 차량 세부 정보 - 누적 주행거리 표시
		jlblDistance = new JLabel(" 주행거리: " + cDTO.getDistance() + "km");
		jlblDistance.setOpaque(true);
		jlblDistance.setBackground(new Color(0xFFFFFF));
		jlblDistance.setForeground(new Color(0x000000));
		jlblDistance.setBorder(null);
		jlblDistance.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblDistance.setBounds(5, 47, 478, 42);
		
		// 차량 세부 정보 - 배기량 수정
		jtfCc = new JTextField(String.valueOf(cDTO.getCc()));
		jtfCc.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfCc.setForeground(new Color(0x000000));
		jtfCc.setBackground(new Color(0xFFFFFF));
		jtfCc.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 1));
		jtfCc.setBounds(101, 89, 61, 42);
		
		// 차량 세부 정보 - 배기량 표시
		jlblCc = new JLabel(" 배기량: " + cDTO.getCc() + "cc");
		jlblCc.setOpaque(true);
		jlblCc.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblCc.setBackground(new Color(0xFFFFFF));
		jlblCc.setForeground(new Color(0x000000));
		jlblCc.setBorder(null);
		jlblCc.setBounds(5, 89, 478, 42);
		
		// 차량 세부 정보 - 유종 수정
		try {
			oilArr = cis.getAvailableOils();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end try ~ catch
		
		dcbmOil = new DefaultComboBoxModel<String>();
		jcbOil = new JComboBox<String>(dcbmOil);
		for (String oil : oilArr) {
			final String oilType = oil;
			
			dcbmOil.addElement(oilType);
			jcbOil.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			jcbOil.setForeground(new Color(0x000000));
		} // end for
		
		jcbOil.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jcbOil.setForeground(new Color(0x000000));
		jcbOil.setBackground(new Color(0xFFFFFF));
		jcbOil.setBorder(null);
		jcbOil.setSelectedItem(cDTO.getOil());
		jcbOil.setBounds(77, 131, 150, 42);
		
		// 차량 세부 정보 - 유종 표시
		jlblOil = new JLabel(" 유종: " + cDTO.getOil());
		jlblOil.setOpaque(true);
		jlblOil.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblOil.setBackground(new Color(0xFFFFFF));
		jlblOil.setForeground(new Color(0x000000));
		jlblOil.setBounds(5, 131, 478, 42);
		
		// 차량 세부 정보 - 번호판 수정
		jtfNumberPlate = new JTextField(cDTO.getRegNum());
		jtfNumberPlate.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfNumberPlate.setForeground(new Color(0x000000));
		jtfNumberPlate.setBackground(new Color(0xFFFFFF));
		jtfNumberPlate.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 1));
		jtfNumberPlate.setBounds(104, 173, 136, 42);
		
		// 차량 세부 정보 - 번호판 표시
		jlblNumberPlate = new JLabel(" 번호판: " + cDTO.getRegNum());
		jlblNumberPlate.setOpaque(true);
		jlblNumberPlate.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblNumberPlate.setForeground(new Color(0x000000));
		jlblNumberPlate.setBackground(new Color(0xFFFFFF));
		jlblNumberPlate.setBorder(null);
		jlblNumberPlate.setBounds(5, 173, 478, 42);
		
		if(userType.equals("a")) {
			jlblYear.add(jtfYear1);
			jpDetail.add(jtfDistance);
			jpDetail.add(jlblDistanceKm);
			jpDetail.add(jtfCc);
			jpDetail.add(jcbOil);
			jpDetail.add(jtfNumberPlate);
		} // end if
		
		jpDetail.add(jlblYear);
		jpDetail.add(jlblDistance);
		jpDetail.add(jlblCc);
		jpDetail.add(jlblOil);
		jpDetail.add(jlblNumberPlate);
		
		// 차량 세부 정보 - 옵션 목록
		jtaOption = new JTextArea("");
		jtaOption.setEditable(false);
		StringBuilder sbOption = new StringBuilder();
		jtaOption.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtaOption.setForeground(new Color(0x000000));
		jtaOption.setBackground(new Color(0xFFFFFF));
		jtaOption.setBorder(null);
		
		// 차량 세부 정보 - 옵션 목록 수정
		try {
			optionCodeArr = cis.getAvailableOptionCodes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			optionNameArr = cis.getAvailableOptions();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end try ~ catch
		
		try {
			carOptionNameArr = cis.getProductOptionDetails(prodCode);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try ~ catch
		
		jpOption = new JPanel(new GridLayout(0, 1));
		for (int i = 0; i < optionCodeArr.length; i++) {
//			final String optionName = option;
//			final int optionInd = 0;
			final String optionName =  optionNameArr[i];
			final int optionCode =  optionCodeArr[i];
			String optionCodeStr = String.valueOf(optionCode);
			
			jcbOption = new JCheckBox(optionName);
			jcbOption.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jcbOption.setBackground(new Color(0xFFFFFF));
			jcbOption.setForeground(new Color(0x000000));
			jcbOption.setBorder(null);
			jcbOption.setSelected(false);
			optionMap.put(jcbOption, optionCodeStr);
			
			for (String carOption : carOptionNameArr) {
				final String carOptionName = carOption;
				
				if(carOptionName.equals(optionName)) {
					jcbOption.setSelected(true);
					sbOption.append(carOptionName).append("\n");
				} // end if
			} // end for
			jpOption.add(jcbOption);
		} // end for
		
		jtaOption.setText(sbOption.toString());
		
		// 차량 세부 정보 - 옵션 목록 스크롤 바
		if(userType.equals("a")) {
			jspOption = new JScrollPane(jpOption);
		} else if(userType.equals("u")) {
			jspOption = new JScrollPane(jtaOption);
		} // end if ~ else if
		jspOption.setBorder(null);
		jspOption.setForeground(new Color(0x000000));
		jspOption.setBackground(new Color(0xFFFFFF));
		jspOption.getVerticalScrollBar().setUnitIncrement(25);
		jspOption.setBounds(14, 254, 469, 206);
		jpDetail.add(jspOption);
		
		// 차량 세부 정보 - 옵션 제목
		jlblOption = new JLabel(" 옵션");
		jlblOption.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblOption.setOpaque(true);
		jlblOption.setBorder(null);
		jlblOption.setForeground(new Color(0x000000));
		jlblOption.setBackground(new Color(0xFFFFFF));
		jlblOption.setVerticalAlignment(1);
		jlblOption.setBounds(5, 220, 478, 240);
		jpDetail.add(jlblOption);
		
		// 하자내역 - 하자목록
		jtaDefect = new JTextArea();
		jtaDefect.setEditable(false);
		StringBuilder sbDefect = new StringBuilder();
		jtaDefect.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		jtaDefect.setForeground(new Color(0x000000));
		jtaDefect.setBackground(new Color(0xFFFFFF));
		jtaDefect.setBorder(null);
		
		// 하자내역 - 하자목록 수정
		try {
			defectNameArr = cis.getAvailableDefects();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end try ~ catch
		
		try {
			carDefectNameArr = cis.getProductDefectDetails(prodCode);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try ~ catch
		
		jpDefect = new JPanel(new GridLayout(0, 1));
		for (String defect : defectNameArr) {
			// [중요] 람다에서 사용하기 위해 final 변수로 복사
			final String defectName = defect;
			
			jcbDefect = new JCheckBox(defectName);
			jcbDefect.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jcbDefect.setBackground(new Color(0xFFFFFF));
			jcbDefect.setForeground(new Color(0x000000));
			jcbDefect.setBorder(null);
			jcbDefect.setSelected(false);
			
			for (String carDefect : carDefectNameArr) {
				final String carDefectName = carDefect;
				
				if(carDefectName.equals(defectName)) {
					jcbDefect.setSelected(true);
					sbDefect.append(" ").append(carDefectName).append("\n");
				} // end if
			} // end for
			jpDefect.add(jcbDefect);
		} // end for
		
		jtaDefect.setText(sbDefect.toString());
		
		// 하자내역 - 하자제목
		jlblDefect = new JLabel("하자내역");
		jlblDefect.setOpaque(true);
		jlblDefect.setBackground(new Color(0xFFFFFF));
		jlblDefect.setForeground(new Color(0x000000));
		jlblDefect.setHorizontalAlignment(JLabel.CENTER);
		jlblDefect.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		jlblDefect.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jlblDefect.setBounds(10, 620, 1148, 55);
		jlblCenterScroll.add(jlblDefect);
		
		// 하자내역 - 하자목록 스크롤 바
		if(userType.equals("a")) {
			jspDefect = new JScrollPane(jpDefect);
		} else if(userType.equals("u")) {
			jspDefect = new JScrollPane(jtaDefect);
		} // end if ~ else if
		jspDefect.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jspDefect.setForeground(new Color(0x000000));
		jspDefect.setBackground(new Color(0xFFFFFF));
		jspDefect.getVerticalScrollBar().setUnitIncrement(25);
		jspDefect.setBounds(10, 670, 1148, 240);
		jlblCenterScroll.add(jspDefect);
		
		// 사고내역 - 사고목록
		jtaAccident = new JTextArea();
		StringBuilder sbAccident = new StringBuilder();
		jtaAccident.setEditable(false);
		jtaAccident.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		jtaAccident.setForeground(new Color(0x000000));
		jtaAccident.setBackground(new Color(0xFFFFFF));
		jtaAccident.setBorder(null);
		
		// 사고내역 - 사고목록 수정
		try {
			accidentNameArr = cis.getAvailableAccidents();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end try ~ catch
		
		try {
			carAccidentNameArr = cis.getProductAccidentDetails(prodCode);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try ~ catch
		
		jpAccident = new JPanel(new GridLayout(0, 1));
		for (String accident : accidentNameArr) {
			// [중요] 람다에서 사용하기 위해 final 변수로 복사
			final String accidentName = accident;
			
			jcbAccident = new JCheckBox(accidentName);
			jcbAccident.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jcbAccident.setBackground(new Color(0xFFFFFF));
			jcbAccident.setForeground(new Color(0x000000));
			jcbAccident.setBorder(null);
			jcbAccident.setSelected(false);
			
			for (String carAccident : carAccidentNameArr) {
				final String carAccidentName = carAccident;
				
				if(carAccidentName.equals(accidentName)) {
					jcbAccident.setSelected(true);
					sbAccident.append(" ").append(carAccidentName).append("\n");
				} // end if
			} // end for
			jpAccident.add(jcbAccident);
		} // end for
		
		jtaAccident.setText(sbAccident.toString());
		
		// 사고내역 - 사고제목
		jlblAccident = new JLabel("사고내역");
		jlblAccident.setOpaque(true);
		jlblAccident.setBackground(new Color(0xFFFFFF));
		jlblAccident.setForeground(new Color(0x000000));
		jlblAccident.setHorizontalAlignment(JLabel.CENTER);
		jlblAccident.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		jlblAccident.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jlblAccident.setBounds(10, 920, 1148, 55);
		jlblCenterScroll.add(jlblAccident);
		
		// 사고내역 - 사고목록 스크롤 바
		if(userType.equals("a")) {
			jspAccident = new JScrollPane(jpAccident);
		} else if(userType.equals("u")) {
			jspAccident = new JScrollPane(jtaAccident);
		} // end if ~ else if
		jspAccident.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jspAccident.setForeground(new Color(0x000000));
		jspAccident.setBackground(new Color(0xFFFFFF));
		jspAccident.getVerticalScrollBar().setUnitIncrement(25);
		jspAccident.setBounds(10, 970, 1148, 240);
		jlblCenterScroll.add(jspAccident);
		
		// 수리내역 - 수리목록
		jtaRepair = new JTextArea();
		StringBuilder sbRepair = new StringBuilder();
		jtaRepair.setEditable(false);
		jtaRepair.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		jtaRepair.setForeground(new Color(0x000000));
		jtaRepair.setBackground(new Color(0xFFFFFF));
		jtaRepair.setBorder(null);
		
		// 수리내역 - 수리목록 수정
		try {
			repairNameArr = cis.getAvailableRepairs();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end try ~ catch
		
		try {
			carRepairNameArr = cis.getProductRepairDetails(prodCode);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try ~ catch
		
		jpRepair = new JPanel(new GridLayout(0, 1));
		for (String repair : repairNameArr) {
			// [중요] 람다에서 사용하기 위해 final 변수로 복사
			final String repairName = repair;
			
			jcbRepair = new JCheckBox(repairName);
			jcbRepair.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jcbRepair.setBackground(new Color(0xFFFFFF));
			jcbRepair.setForeground(new Color(0x000000));
			jcbRepair.setBorder(null);
			jcbRepair.setSelected(false);
			
			for (String carRepair : carRepairNameArr) {
				final String carRepairName = carRepair;
				
				if(carRepairName.equals(repairName)) {
					jcbRepair.setSelected(true);
					sbRepair.append(" ").append(carRepairName).append("\n");
				} // end if
			} // end for
			jpRepair.add(jcbRepair);
		} // end for
		
		jtaRepair.setText(sbRepair.toString());
		
		// 수리내역 - 수리제목
		jlblRepair = new JLabel("수리내역");
		jlblRepair.setOpaque(true);
		jlblRepair.setBackground(new Color(0xFFFFFF));
		jlblRepair.setForeground(new Color(0x000000));
		jlblRepair.setHorizontalAlignment(JLabel.CENTER);
		jlblRepair.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		jlblRepair.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jlblRepair.setBounds(10, 1220, 1148, 55);
		jlblCenterScroll.add(jlblRepair);
		
		// 수리내역 - 수리목록 스크롤 바
		if(userType.equals("a")) {
			jspRepair = new JScrollPane(jpRepair);
		} else if(userType.equals("u")) {
			jspRepair = new JScrollPane(jtaRepair);
		} // end if ~ else if
		jspRepair.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jspRepair.setForeground(new Color(0x000000));
		jspRepair.setBackground(new Color(0xFFFFFF));
		jspRepair.getVerticalScrollBar().setUnitIncrement(25);
		jspRepair.setBounds(10, 1270, 1148, 240);
		jlblCenterScroll.add(jspRepair);
		
	} // CarInfoCenterPanel
	
	public void setJlblOil(String strOil) {
		jlblOil.setText(strOil);
	} // setJlblOil
	
	public JPanel getJpCenter() {
		return jpCenter;
	} // getJpCenter
	
	public JButton getJbtnImageIcon1() {
		return jbtnImageIcon1;
	} // getJbtnImage1
	
	public JButton getJbtnImageIcon2() {
		return jbtnImageIcon2;
	} // getJbtnImage2
	
	public JButton getJbtnImageIcon3() {
		return jbtnImageIcon3;
	} // getJbtnImage3
	
	public JButton getJbtnImageIcon4() {
		return jbtnImageIcon4;
	} // getJbtnImage4
	
	public JButton getJbtnPurchase() {
		return jbtnPurchase;
	} // getJbtnPurchase
	
	public JPanel getJpImage() {
		return jpImage;
	} // getJpImage
	
	public CardLayout getCl() {
		return cl;
	} // getCl
	
	public JTextField getJtfBrand() {
		return jtfBrand;
	} // getJtfBrand
	
	public JTextField getJtfCarName() {
		return jtfCarName;
	}
	
	public JTextField getJtfPrice() {
		return jtfPrice;
	}
	
	public JTextField getJtfYear1() {
		return jtfYear1;
	}
	
	public JTextField getJtfYear2() {
		return jtfYear2;
	}
	
	public JTextField getJtfDistance() {
		return jtfDistance;
	}
	
	public JTextField getJtfCc() {
		return jtfCc;
	}
	
	public JComboBox<String> getJcbStatSold() {
		return jcbStatSold;
	}
	
	public DefaultComboBoxModel<String> getDcbmStatSold(){
		return dcbmStatSold;
	}
	
	public JComboBox<String> getJcbOil() {
		return jcbOil;
	}
	
	public DefaultComboBoxModel<String> getDcbmOil(){
		return dcbmOil;
	}
	
	public JTextField getJtfNumberPlate() {
		return jtfNumberPlate;
	}
	
	public String getCarBrand() {
		return carBrand;
	}
	
	public String getCarName() {
		return carName;
	}

	public Map<JCheckBox, String> getOptionMap() {
		return optionMap;
	}
	
	
	
	
} // class
