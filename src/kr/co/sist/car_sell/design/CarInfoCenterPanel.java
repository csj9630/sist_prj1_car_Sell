package kr.co.sist.car_sell.design;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.car_sell.event.CarInfoEvt;

public class CarInfoCenterPanel extends JDialog {
	
	private JButton jbtnImage1, jbtnImage2, jbtnImage3, jbtnImage4, jbtnPurchase;
	private JLabel	jlblCenterScroll, jlblImageBackground, jlblImage1, jlblImage2, jlblImage3, jlblImage4,
					jlblProductName, jlblPrice, jlblYear, jlblDistance, jlblCC, jlblFuelType, jlblNumberPlate,
					jlblOption, jlblDefect, jlblAccident, jlblRepair;
	private JTextField jtfBrand, jtfCarName, jtfPrice, jtfYear1, jtfYear2, jtfDistance, jtfCC, jtfFuelType, jtfNumberPlate;
	private JTextArea jtaOption, jtaDefect, jtaAccident, jtaRepair;
	private JScrollPane jspCenter, jspOption, jspDefect, jspAccident, jspRepair;
	private JPanel jpCenterScrollCover, jpImage, jpDetail;
	private CardLayout cl;

	private CarInfoDesign cid;
	private CarInfoEvt cie;
	
	private static JPanel jpCenter;
	
	public CarInfoCenterPanel(CarInfoDesign cid) {
		
		this.cid = cid;
		
		// 차량 정보 구역
		jpCenter = new JPanel(null);
		
		// 차량 정보 구역 - 스크롤 바
		jpCenterScrollCover = new JPanel(new GridLayout(0, 1));
		jspCenter = new JScrollPane(jpCenterScrollCover);
		jspCenter.setBorder(null);
		jspCenter.setBackground(new Color(0xFFFFFF));
		jspCenter.setBounds(0, 0, 1185, 666);
		jpCenter.add(jspCenter);
		
		// 차량 정보 구역 - 크기 설정
		jlblCenterScroll = new JLabel("I");
		jlblCenterScroll.setFont(new Font("맑은 고딕", Font.BOLD, 1034));
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
		ImageIcon ii1 = new ImageIcon("C:/dev/car_img/genesis/g80/g80_1.png");
		ImageIcon ii2 = new ImageIcon("C:/dev/car_img/genesis/g80/g80_2.png");
		ImageIcon ii3 = new ImageIcon("C:/dev/car_img/genesis/g80/g80_3.png");
		ImageIcon ii4 = new ImageIcon("C:/dev/car_img/genesis/g80/g80_4.png");
		
		jlblImage1 = new JLabel(ii1);
		jlblImage2 = new JLabel(ii2);
		jlblImage3 = new JLabel(ii3);
		jlblImage4 = new JLabel(ii4);
		
		jpImage.add(jlblImage1, "inputA");
		jpImage.add(jlblImage2, "inputB");
		jpImage.add(jlblImage3, "inputC");
		jpImage.add(jlblImage4, "inputD");
		
		cl.show(jpImage, "inputA");
		
		// 차량 이미지 - 이미지 선택
		ImageIcon ii1Small = new ImageIcon("C:/dev/car_img/genesis/g80/g80_1_small.png");
		ImageIcon ii2Small = new ImageIcon("C:/dev/car_img/genesis/g80/g80_2_small.png");
		ImageIcon ii3Small = new ImageIcon("C:/dev/car_img/genesis/g80/g80_3_small.png");
		ImageIcon ii4Small = new ImageIcon("C:/dev/car_img/genesis/g80/g80_4_small.png");
		
		jbtnImage1 = new JButton(ii1Small);
		jbtnImage2 = new JButton(ii2Small);
		jbtnImage3 = new JButton(ii3Small);
		jbtnImage4 = new JButton(ii4Small);
		
		jbtnImage1.setBackground(new Color(0xC0C0C0));
		jbtnImage2.setBackground(new Color(0xC0C0C0));
		jbtnImage3.setBackground(new Color(0xC0C0C0));
		jbtnImage4.setBackground(new Color(0xC0C0C0));
		
		jbtnImage1.setBounds(5, 370, 160, 90);
		jbtnImage2.setBounds(165, 370, 160, 90);
		jbtnImage3.setBounds(325, 370, 160, 90);
		jbtnImage4.setBounds(485, 370, 160, 90);
		
		jlblImageBackground.add(jbtnImage1);
		jlblImageBackground.add(jbtnImage2);
		jlblImageBackground.add(jbtnImage3);
		jlblImageBackground.add(jbtnImage4);
		
		// 차량명
		jlblProductName = new JLabel();
		jlblProductName.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		jlblProductName.setOpaque(true);
		jlblProductName.setBackground(new Color(0xFFFFFF));
		jlblProductName.setForeground(new Color(0xFFFFFF));
		jlblProductName.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jlblProductName.setBounds(10, 485, 650, 130);
		jlblCenterScroll.add(jlblProductName);
		
		// 차량명 - 브랜드
		jtfBrand = new JTextField("Genesis");
		jtfBrand.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jtfBrand.setForeground(new Color(0x000000));
		jtfBrand.setBackground(new Color(0xFFFFFF));
		jtfBrand.setBorder(null);
		jtfBrand.setBounds(15, 5, 630, 70);
		jlblProductName.add(jtfBrand);
		
		// 차량명 - 차종
		jtfCarName = new JTextField("G80 2.5T AWD");
		jtfCarName.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfCarName.setForeground(new Color(0x000000));
		jtfCarName.setBackground(new Color(0xFFFFFF));
		jtfCarName.setBorder(null);
		jtfCarName.setBounds(15, 65, 630, 50);
		jlblProductName.add(jtfCarName);
		
		// 차량 가격 - 단위
		jlblPrice = new JLabel("만원 ");
		jlblPrice.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		jlblPrice.setOpaque(true);
		jlblPrice.setForeground(new Color(0x000000));
		jlblPrice.setBackground(new Color(0xFFFFFF));
		jlblPrice.setHorizontalAlignment(4);
		jlblPrice.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jlblPrice.setBounds(670, 485, 488, 60);
		jlblCenterScroll.add(jlblPrice);
		
		// 차량 가격 - 금액
		jtfPrice = new JTextField("4,390");
		jtfPrice.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		jtfPrice.setForeground(new Color(0x000000));
		jtfPrice.setBackground(new Color(0xFFFFFF));
		jtfPrice.setHorizontalAlignment(4);
		jtfPrice.setBorder(BorderFactory.createLineBorder(new Color(0x000000), 2));
		jtfPrice.setBounds(5, 5, 396, 50);
		jlblPrice.add(jtfPrice);
		
		// 차량 구매
		jbtnPurchase = new JButton("구매하기");
		jbtnPurchase.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		jbtnPurchase.setForeground(new Color(0x000000));
		jbtnPurchase.setBackground(new Color(0xC0C0C0));
		jbtnPurchase.setHorizontalAlignment(0);
		jbtnPurchase.setVerticalAlignment(0);
		jbtnPurchase.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jbtnPurchase.setBounds(670, 555, 488, 60);
		jlblCenterScroll.add(jbtnPurchase);
		
		// 차량 세부 정보
		jpDetail = new JPanel(null);
		jpDetail.setBackground(new Color(0x808080));
		jpDetail.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jpDetail.setBounds(670, 10, 488, 465);
		jlblCenterScroll.add(jpDetail);
		
		// 차량 세부 정보 - 연식
		jlblYear = new JLabel(" 연식: 0000년 00월");
		jlblYear.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlblYear.setOpaque(true);
		jlblYear.setForeground(new Color(0x000000));
		jlblYear.setBackground(new Color(0xFFFFFF));
		jlblYear.setBorder(null);
		jlblYear.setBounds(5, 5, 478, 42);
		jpDetail.add(jlblYear);
		
		// 차량 세부 정보 - 연식 연도
		jtfYear1 = new JTextField("2023");
		jtfYear1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfYear1.setForeground(new Color(0x000000));
		jtfYear1.setBackground(new Color(0xFFFFFF));
		jtfYear1.setHorizontalAlignment(4);
		jtfYear1.setBorder(null);
		jtfYear1.setBounds(75, 0, 58, 42);
		jlblYear.add(jtfYear1);
		
		// 차량 세부 정보 - 연식 월
		jtfYear2 = new JTextField("06");
		jtfYear2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfYear2.setForeground(new Color(0x000000));
		jtfYear2.setBackground(new Color(0xFFFFFF));
		jtfYear2.setHorizontalAlignment(4);
		jtfYear2.setBorder(null);
		jtfYear2.setBounds(164, 0, 30, 42);
		jlblYear.add(jtfYear2);
		
		// 차량 세부 정보 - 누적 주행거리
		jlblDistance = new JLabel(" 주행거리: 67,000km");
		jtfDistance = new JTextField("67,000");
		jtfDistance.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfDistance.setForeground(new Color(0x000000));
		jtfDistance.setBackground(new Color(0xFFFFFF));
		jtfDistance.setBorder(null);
		jtfDistance.setBounds(5, 47, 478, 42);
		jpDetail.add(jtfDistance);
		
		// 차량 세부 정보 - 배기량
		jlblCC = new JLabel(" 배기량: 2,499cc");
		jtfCC = new JTextField(" 배기량: 2,499cc");
		jtfCC.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfCC.setForeground(new Color(0x000000));
		jtfCC.setBackground(new Color(0xFFFFFF));
		jtfCC.setBorder(null);
		jtfCC.setBounds(5, 89, 478, 42);
		jpDetail.add(jtfCC);
		
		// 차량 세부 정보 - 유종
		jlblFuelType = new JLabel(" 유종: 휘발유");
		jtfFuelType = new JTextField(" 유종: 휘발유");
		jtfFuelType.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfFuelType.setForeground(new Color(0x000000));
		jtfFuelType.setBackground(new Color(0xFFFFFF));
		jtfFuelType.setBorder(null);
		jtfFuelType.setBounds(5, 131, 478, 42);
		jpDetail.add(jtfFuelType);
		
		// 차량 세부 정보 - 번호판
		jlblNumberPlate = new JLabel(" 번호판: 123가4567");
		jtfNumberPlate = new JTextField(" 번호판: 123가4567");
		jtfNumberPlate.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtfNumberPlate.setForeground(new Color(0x000000));
		jtfNumberPlate.setBackground(new Color(0xFFFFFF));
		jtfNumberPlate.setBorder(null);
		jtfNumberPlate.setBounds(5, 173, 478, 42);
		jpDetail.add(jtfNumberPlate);
		
		// 차량 세부 정보 - 옵션 목록
		jtaOption = new JTextArea
				("옵션1\n옵션2\n옵션3\n옵션4\n옵션5\n옵션6\n옵션7\n옵션8\n옵션9\n옵션10");
		jtaOption.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtaOption.setForeground(new Color(0x000000));
		jtaOption.setBackground(new Color(0xFFFFFF));
		jtaOption.setBorder(null);
		
		// 차량 세부 정보 - 옵션 목록 스크롤 바
		jspOption = new JScrollPane(jtaOption);
		jspOption.setBorder(null);
		jspOption.setForeground(new Color(0x000000));
		jspOption.setBackground(new Color(0xFFFFFF));
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
		jtaDefect = new JTextArea
				(" 하자내역\n 하자1\t처리일1\n 하자2\t처리일2\n 하자3\t처리일3\n 하자4\t처리일4\n 하자5\t처리일5\n 하자6\t처리일6");
		jtaDefect.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		jtaDefect.setForeground(new Color(0x000000));
		jtaDefect.setBackground(new Color(0xFFFFFF));
		jtaDefect.setBorder(null);
		
		// 하자내역 - 하자목록 스크롤 바
		jspDefect = new JScrollPane(jtaDefect);
		jspDefect.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jspDefect.setForeground(new Color(0x000000));
		jspDefect.setBackground(new Color(0xFFFFFF));
		jspDefect.setBounds(10, 625, 1148, 240);
		jlblCenterScroll.add(jspDefect);
		
		// 사고내역 - 사고목록
		jtaAccident = new JTextArea
				(" 사고내역\n 사고1\t사고일1\n 사고2\t사고일2\n 사고3\t사고일3\n 사고4\t사고일4\n 사고5\t사고일5\n 사고6\t사고일6");
		jtaAccident.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		jtaAccident.setForeground(new Color(0x000000));
		jtaAccident.setBackground(new Color(0xFFFFFF));
		jtaAccident.setBorder(null);
		
		// 사고내역 - 사고목록 스크롤 바
		jspAccident = new JScrollPane(jtaAccident);
		jspAccident.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jspAccident.setForeground(new Color(0x000000));
		jspAccident.setBackground(new Color(0xFFFFFF));
		jspAccident.setBounds(10, 875, 1148, 240);
		jlblCenterScroll.add(jspAccident);
		
		// 수리내역 - 수리목록
		jtaRepair = new JTextArea
				(" 수리내역\n 수리1\t수리일1\n 수리2\t수리일2\n 수리3\t수리일3\n 수리4\t수리일4\n 수리5\t수리일5\n 수리6\t수리일6");
		jtaRepair.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		jtaRepair.setForeground(new Color(0x000000));
		jtaRepair.setBackground(new Color(0xFFFFFF));
		jtaRepair.setBorder(null);
		
		// 수리내역 - 수리목록 스크롤 바
		jspRepair = new JScrollPane(jtaRepair);
		jspRepair.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 5));
		jspRepair.setForeground(new Color(0x000000));
		jspRepair.setBackground(new Color(0xFFFFFF));
		jspRepair.setBounds(10, 1125, 1148, 240);
		jlblCenterScroll.add(jspRepair);
		
	} // CarInfoCenterPanel
	

	
	public static JPanel getJpCenter() {
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
	
	public JButton getJbtnPurchase() {
		return jbtnPurchase;
	} // getJbtnPurchase
	
	public JPanel getJpImage() {
		return jpImage;
	}

	public CardLayout getCl() {
		return cl;
	}
	
	
	
} // class
