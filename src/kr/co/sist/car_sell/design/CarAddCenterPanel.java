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
import javax.swing.JTextField;

import kr.co.sist.car_sell.dto.CarDTO;
import kr.co.sist.car_sell.service.CarAddService;
import kr.co.sist.car_sell.service.ImageService;

public class CarAddCenterPanel extends JDialog {
	
	private JButton jbtnImage1, jbtnImageIcon1, jbtnImage2, jbtnImageIcon2, jbtnImage3, jbtnImageIcon3, jbtnImage4, jbtnImageIcon4;
	private JLabel	jlblCenterScroll, jlblImageBackground, jlblImage1, jlblImage2, jlblImage3, jlblImage4,
					jlblProductName, jlblBrand, jlblCarName, jlblPrice, jlblYear, jlblDistanceKm, jlblCc, jlblOil, jlblNumberPlate,
					jlblOption, jlblDefect, jlblAccident, jlblRepair, jlblDistance;
	private JTextField jtfBrand, jtfCarName, jtfPrice, jtfYear1, jtfYear2, jtfDistance, jtfCc, jtfNumberPlate;
	private JScrollPane jspCenter, jspOption, jspDefect, jspAccident, jspRepair;
	private JPanel jpCenterScrollCover, jpImage, jpDetail, jpOption, jpDefect, jpAccident, jpRepair;
	private JCheckBox jcbOption, jcbDefect, jcbAccident, jcbRepair;
	private DefaultComboBoxModel<String> dcbmStatSold, dcbmOil;
	private JComboBox<String> jcbStatSold, jcbOil;
	private String carBrand, carName;
	private String[] oilArr, optionNameArr, carOptionNameArr, defectNameArr, carDefectNameArr, accidentNameArr, carAccidentNameArr, repairNameArr, carRepairNameArr;
	private int[] optionCodeArr, defectCodeArr, repairCodeArr, accidentCodeArr;
	private CardLayout cl;
	private Map<JCheckBox, String> optionMap, defectMap;
	
	private CarAddDesign cad;
	private CarAddService cas;
	private CarDTO cDTO;
	private int prodCode;
	private String userType;
	private int userCode;
	
	private static JPanel jpCenter;
	
	public CarAddCenterPanel(CarAddDesign cad) {
		
		this.cad = cad;
		cas = cad.getCas();
		
		optionMap = new HashMap<>();
		defectMap = new HashMap<>();
		
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
		ImageService is = new ImageService();
		
		ImageIcon ii1 = new ImageIcon("");
		ImageIcon ii2 = new ImageIcon("");
		ImageIcon ii3 = new ImageIcon("");
		ImageIcon ii4 = new ImageIcon("");
		
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
		ImageIcon ii1Small = new ImageIcon("");
		ImageIcon ii2Small = new ImageIcon("");
		ImageIcon ii3Small = new ImageIcon("");
		ImageIcon ii4Small = new ImageIcon("");
		
		jbtnImageIcon1 = new JButton(ii1Small);
		jbtnImageIcon2 = new JButton(ii2Small);
		jbtnImageIcon3 = new JButton(ii3Small);
		jbtnImageIcon4 = new JButton(ii4Small);
		
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
		jtfBrand = new JTextField("브랜드명을 입력하세요.");
		jtfBrand.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jtfBrand.setForeground(new Color(0x000000));
		jtfBrand.setBackground(new Color(0xFFFFFF));
		jtfBrand.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 1));
		jtfBrand.setBounds(15, 8, 620, 62);
		jlblProductName.add(jtfBrand);
		
		// 차량명 - 차종 수정
		jtfCarName = new JTextField("차종을 입력하세요.");
		jtfCarName.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfCarName.setForeground(new Color(0x000000));
		jtfCarName.setBackground(new Color(0xFFFFFF));
		jtfCarName.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 1));
		jtfCarName.setBounds(15, 70, 620, 52);
		jlblProductName.add(jtfCarName);
		
		// 차량 가격 - 금액 수정
		jtfPrice = new JTextField("차량 가격을 입력하세요.");
		jtfPrice.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		jtfPrice.setForeground(new Color(0x000000));
		jtfPrice.setBackground(new Color(0xFFFFFF));
		jtfPrice.setHorizontalAlignment(JTextField.RIGHT);
		jtfPrice.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 1));
		jtfPrice.setBounds(675, 490, 399, 50);
		jlblCenterScroll.add(jtfPrice);
		
		// 차량 가격 - 금액 표시
		jlblPrice = new JLabel("만원 ");
		jlblPrice.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		jlblPrice.setOpaque(true);
		jlblPrice.setForeground(new Color(0x000000));
		jlblPrice.setBackground(new Color(0xFFFFFF));
		jlblPrice.setHorizontalAlignment(JLabel.RIGHT);
		jlblPrice.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jlblPrice.setBounds(670, 485, 488, 60);
		jlblCenterScroll.add(jlblPrice);
		
		// 차량 세부 정보 - 판매여부 수정
		dcbmStatSold = new DefaultComboBoxModel<String>();
		jcbStatSold = new JComboBox<String>(dcbmStatSold);
		dcbmStatSold.addElement("판매중");
		dcbmStatSold.addElement("판매완료");
		jcbStatSold.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		jcbStatSold.setForeground(new Color(0x000000));
		jcbStatSold.setBackground(new Color(0xFFFFFF));
		jcbStatSold.setBorder(null);
		jcbStatSold.setBounds(670, 555, 488, 60);
		jlblCenterScroll.add(jcbStatSold);
		
		// 차량 세부 정보
		jpDetail = new JPanel(null);
		jpDetail.setBackground(new Color(0x808080));
		jpDetail.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jpDetail.setBounds(670, 10, 488, 465);
		jlblCenterScroll.add(jpDetail);
		
		// 차량 세부 정보 - 연식
		jlblYear = new JLabel(" 연식: 0000년");
		jlblYear.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblYear.setOpaque(true);
		jlblYear.setForeground(new Color(0x000000));
		jlblYear.setBackground(new Color(0xFFFFFF));
		jlblYear.setBorder(null);
		jlblYear.setBounds(5, 5, 478, 42);
		
		// 차량 세부 정보 - 연식 연도
		jtfYear1 = new JTextField("0000");
		jtfYear1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfYear1.setForeground(new Color(0x000000));
		jtfYear1.setBackground(new Color(0xFFFFFF));
		jtfYear1.setHorizontalAlignment(4);
		jtfYear1.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 1));
		jtfYear1.setBounds(73, 0, 60, 42);
		
		// 차량 세부 정보 - 누적 주행거리 수정
		jtfDistance = new JTextField("0000000");
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
		jlblDistance = new JLabel(" 주행거리: 0000000 km");
		jlblDistance.setOpaque(true);
		jlblDistance.setBackground(new Color(0xFFFFFF));
		jlblDistance.setForeground(new Color(0x000000));
		jlblDistance.setBorder(null);
		jlblDistance.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblDistance.setBounds(5, 47, 478, 42);
		
		// 차량 세부 정보 - 배기량 수정
		jtfCc = new JTextField("CC");
		jtfCc.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfCc.setForeground(new Color(0x000000));
		jtfCc.setBackground(new Color(0xFFFFFF));
		jtfCc.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 1));
		jtfCc.setBounds(101, 89, 61, 42);
		
		// 차량 세부 정보 - 배기량 표시
		jlblCc = new JLabel(" 배기량: " + 0000 + "cc");
		jlblCc.setOpaque(true);
		jlblCc.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblCc.setBackground(new Color(0xFFFFFF));
		jlblCc.setForeground(new Color(0x000000));
		jlblCc.setBorder(null);
		jlblCc.setBounds(5, 89, 478, 42);
		
		// 차량 세부 정보 - 유종 수정
		try {
			oilArr = cas.getAvailableOils();
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
		jcbOil.setBounds(77, 131, 150, 42);
		
		// 차량 세부 정보 - 유종 표시
		jlblOil = new JLabel(" 유종: ");
		jlblOil.setOpaque(true);
		jlblOil.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblOil.setBackground(new Color(0xFFFFFF));
		jlblOil.setForeground(new Color(0x000000));
		jlblOil.setBounds(5, 131, 478, 42);
		
		// 차량 세부 정보 - 번호판 수정
		jtfNumberPlate = new JTextField("000가 0000");
		jtfNumberPlate.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfNumberPlate.setForeground(new Color(0x000000));
		jtfNumberPlate.setBackground(new Color(0xFFFFFF));
		jtfNumberPlate.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 1));
		jtfNumberPlate.setBounds(104, 173, 136, 42);
		
		// 차량 세부 정보 - 번호판 표시
		jlblNumberPlate = new JLabel(" 번호판: ");
		jlblNumberPlate.setOpaque(true);
		jlblNumberPlate.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblNumberPlate.setForeground(new Color(0x000000));
		jlblNumberPlate.setBackground(new Color(0xFFFFFF));
		jlblNumberPlate.setBorder(null);
		jlblNumberPlate.setBounds(5, 173, 478, 42);
		
		
		jpDetail.add(jlblYear);
		jlblYear.add(jtfYear1);
		jpDetail.add(jtfDistance);
		jpDetail.add(jlblDistance);
		jpDetail.add(jtfCc);
		jpDetail.add(jcbOil);
		jpDetail.add(jtfNumberPlate);
		jpDetail.add(jlblDistanceKm);
		jpDetail.add(jlblCc);
		jpDetail.add(jlblOil);
		jpDetail.add(jlblNumberPlate);
		
		// 차량 세부 정보 - 옵션 목록 수정
		try {
			optionCodeArr = cas.getOptionCodes();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			optionNameArr = cas.getAvailableOptions();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end try ~ catch
		
		try {
			carOptionNameArr = cas.getProductOptionDetails(prodCode);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try ~ catch
		
		jpOption = new JPanel(new GridLayout(0, 1));
		for (int i = 0; i < optionCodeArr.length; i++) {
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
			
//			for (String carOption : carOptionNameArr) {
//				final String carOptionName = carOption;
//				
//				if(carOptionName.equals(optionName)) {
//					jcbOption.setSelected(true);
//				} // end if
//			} // end for
			jpOption.add(jcbOption);
		} // end for
		
		// 차량 세부 정보 - 옵션 목록 스크롤 바
		jspOption = new JScrollPane(jpOption);
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
		
		// 하자내역 - 하자목록 수정
		try {
			defectCodeArr = cas.getDefectCodes();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			defectNameArr = cas.getAvailableDefects();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end try ~ catch
		
		try {
			carDefectNameArr = cas.getProductDefectDetails(prodCode);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try ~ catch
		
		jpDefect = new JPanel(new GridLayout(0, 1));
		for (int i = 0; i < defectCodeArr.length; i++) {
			final String defectName = defectNameArr[i];
			final int defectCode = defectCodeArr[i];
			String defectCodeStr = String.valueOf(defectCode);
			
			jcbDefect = new JCheckBox(defectName);
			jcbDefect.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jcbDefect.setBackground(new Color(0xFFFFFF));
			jcbDefect.setForeground(new Color(0x000000));
			jcbDefect.setBorder(null);
			jcbDefect.setSelected(false);
			defectMap.put(jcbDefect, defectCodeStr);
			
//			for (String carDefect : carDefectNameArr) {
//				final String carDefectName = carDefect;
//				
//				if(carDefectName.equals(defectName)) {
//					jcbDefect.setSelected(true);
//				} // end if
//			} // end for
			jpDefect.add(jcbDefect);
		} // end for
		
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
		jspDefect = new JScrollPane(jpDefect);
		jspDefect.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jspDefect.setForeground(new Color(0x000000));
		jspDefect.setBackground(new Color(0xFFFFFF));
		jspDefect.getVerticalScrollBar().setUnitIncrement(25);
		jspDefect.setBounds(10, 670, 1148, 240);
		jlblCenterScroll.add(jspDefect);
		
		// 사고내역 - 사고목록 수정
		try {
			accidentCodeArr = cas.getAccidentCodes();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			accidentNameArr = cas.getAvailableAccidents();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end try ~ catch
		
		try {
			carAccidentNameArr = cas.getProductAccidentDetails(prodCode);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try ~ catch

		jpAccident = new JPanel(new GridLayout(0, 1));
		for (int i = 0; i < accidentCodeArr.length; i++) {
			final String accidentName = accidentNameArr[i];
			final int accidentCode = accidentCodeArr[i];
			String accidentCodeStr = String.valueOf(accidentCode);
			
			jcbAccident = new JCheckBox(accidentName);
			jcbAccident.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jcbAccident.setBackground(new Color(0xFFFFFF));
			jcbAccident.setForeground(new Color(0x000000));
			jcbAccident.setBorder(null);
			jcbAccident.setSelected(false);
			defectMap.put(jcbAccident, accidentCodeStr);
			
//			for (String carAccident : carAccidentNameArr) {
//				final String carAccidentName = carAccident;
//				
//				if(carAccidentName.equals(accidentName)) {
//					jcbAccident.setSelected(true);
//				} // end if
//			} // end for
			jpAccident.add(jcbAccident);
		} // end for
		
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
		jspAccident = new JScrollPane(jpAccident);
		jspAccident.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jspAccident.setForeground(new Color(0x000000));
		jspAccident.setBackground(new Color(0xFFFFFF));
		jspAccident.getVerticalScrollBar().setUnitIncrement(25);
		jspAccident.setBounds(10, 970, 1148, 240);
		jlblCenterScroll.add(jspAccident);
		
		// 수리내역 - 수리목록 수정
		try {
			repairCodeArr = cas.getRepairCodes();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			repairNameArr = cas.getAvailableRepairs();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end try ~ catch
		
		try {
			carRepairNameArr = cas.getProductRepairDetails(prodCode);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try ~ catch
		
		jpRepair = new JPanel(new GridLayout(0, 1));
		for (int i = 0; i < repairCodeArr.length; i++) {
			final String repairName = repairNameArr[i];
			final int repairCode = repairCodeArr[i];
			String repairCodeStr = String.valueOf(repairCode);
			
			jcbRepair = new JCheckBox(repairName);
			jcbRepair.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			jcbRepair.setBackground(new Color(0xFFFFFF));
			jcbRepair.setForeground(new Color(0x000000));
			jcbRepair.setBorder(null);
			jcbRepair.setSelected(false);
			defectMap.put(jcbRepair, repairCodeStr);
			
//			for (String carRepair : carRepairNameArr) {
//				final String carRepairName = carRepair;
//				
//				if(carRepairName.equals(repairName)) {
//					jcbRepair.setSelected(true);
//				} // end if
//			} // end for
			jpRepair.add(jcbRepair);
		} // end for
		
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
		jspRepair = new JScrollPane(jpRepair);
		jspRepair.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jspRepair.setForeground(new Color(0x000000));
		jspRepair.setBackground(new Color(0xFFFFFF));
		jspRepair.getVerticalScrollBar().setUnitIncrement(25);
		jspRepair.setBounds(10, 1270, 1148, 240);
		jlblCenterScroll.add(jspRepair);
		
	} // CarInfoCenterPanel
	
	public void setJlblOil(String strOil) {
		jlblOil.setText(strOil);
	}
	
	public JPanel getJpCenter() {
		return jpCenter;
	} // getJpCenter
	
	public JButton getJbtnImage1() {
		return jbtnImage1;
	} // getJbtnImage1
	
	public JButton getJbtnImage2() {
		return jbtnImage2;
	} // getJbtnImage2
	
	public JButton getJbtnImage3() {
		return jbtnImage3;
	} // getJbtnImage3
	
	public JButton getJbtnImage4() {
		return jbtnImage4;
	} // getJbtnImage4
	
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
	
	public JPanel getJpImage() {
		return jpImage;
	}
	
	public CardLayout getCl() {
		return cl;
	}
	
	public JTextField getJtfBrand() {
		return jtfBrand;
	}
	
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
	
	public Map<JCheckBox, String> getDefectMap() {
		return defectMap;
	}
	
} // class
