package kr.co.sist.car_sell.event;


import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.co.sist.car_sell.design.CarInfoCenterPanel;
import kr.co.sist.car_sell.design.CarInfoDesign;
import kr.co.sist.car_sell.design.CarInfoNorthPanel;
import kr.co.sist.car_sell.design.CarInfoSouthPanel;
import kr.co.sist.car_sell.design.CarListDesign;
import kr.co.sist.car_sell.design.UserOrderDesign;
import kr.co.sist.car_sell.dto.CarDTO;
import kr.co.sist.car_sell.service.CarInfoService;

public class CarInfoEvt extends WindowAdapter implements ActionListener {
	
	private CarInfoDesign cid;
	private CarListDesign cld;
	private CarInfoNorthPanel cinp;
	private CarInfoCenterPanel cicp;
	private CarInfoSouthPanel cisp;
	
	private JButton	jbtnImageIcon1, jbtnImageIcon2, jbtnImageIcon3, jbtnImageIcon4, jbtnPurchase,
					jbtnModify, jbtnDelete;
	private JComboBox<String> jcbStatSold, jcbOil;
	private DefaultComboBoxModel<String> dcbmStatSold, dcbmOil;
	private JPanel jpImage;
	private int prodCode;
	private int userCode;
	
	private CardLayout cl;
	
	public CarInfoEvt(CarInfoDesign cid, CarListDesign cld, int prodCode, int userCode, CarInfoNorthPanel cinp, CarInfoCenterPanel cicp, CarInfoSouthPanel cisp) {
		
		this.cid = cid;
		this.cld = cld;
		this.prodCode = prodCode;
		this.userCode = userCode;
		this.cinp = cinp;
		this.cicp = cicp;
		this.cisp = cisp;
		
	} // CarInfoEvt
	
	public void updateCars() throws IOException {
		
		// 이름, 이메일, 전화번호, 인트로, 이미지를 받아와서 추가 작업 수행
		int price = Integer.parseInt(cicp.getJtfPrice().getText().trim());
		int cc = Integer.parseInt(cicp.getJtfCc().getText().trim());
		int distance = Integer.parseInt(cicp.getJtfDistance().getText().trim());
		String prodName = cicp.getJtfCarName().getText();
		String regNum = cicp.getJtfNumberPlate().getText();
		int indSold = cicp.getJcbStatSold().getSelectedIndex();
		String soldStat = cicp.getDcbmStatSold().getElementAt(indSold).toString().trim();
		String carName = prodName.substring(0, prodName.indexOf(" ")).trim();
		int indOil = cicp.getJcbOil().getSelectedIndex();
		String oil = cicp.getDcbmOil().getElementAt(indOil).toString().trim();
		String brandName = cicp.getJtfBrand().getText().trim();
		DateTimeFormatter carDate = DateTimeFormatter.ofPattern("yyyyMMdd");
		Date carYear = Date.valueOf(LocalDate.parse(cicp.getJtfYear1().getText().trim()
				+ "0101", carDate));
		
		CarDTO cDTO = new CarDTO(prodCode, price, cc, distance, prodName, regNum, soldStat, carName, oil, brandName, carYear);
		
		List<String> selectedOptionCodes = new ArrayList<>();
		for (Map.Entry<JCheckBox, String> entry : cicp.getOptionMap().entrySet()) {
	        
	        JCheckBox jcbOption = entry.getKey();   // JCheckBox 컴포넌트
	        String optionCode = entry.getValue(); // "OPT_001" 같은 옵션 코드
	        
	        if (jcbOption.isSelected()) {
	            selectedOptionCodes.add(optionCode); // 선택된 것만 리스트에 추가
	        }
	    }
		
		CarInfoService cis = new CarInfoService();
		
		try {
	        cis.updateCarOptions(prodCode, selectedOptionCodes); 
	        
	        JOptionPane.showMessageDialog(cid, "옵션이 성공적으로 변경되었습니다.");
	        
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(cid, "업데이트 중 오류 발생: " + ex.getMessage());
	    }
		
		if(cis.updateCar(prodCode, cDTO)) {
			String msg = "차량 정보를 정상적으로 갱신하였습니다.";
			JOptionPane.showMessageDialog(cid, msg);
		} else {
			String msg = "차량 정보를 갱신할 수 없습니다.\n잠시 후 다시 시도해주세요.";
			JOptionPane.showMessageDialog(cid, msg);
		}
	}
	
	public void deleteCars() throws IOException {
		
		// 이름, 이메일, 전화번호, 인트로, 이미지를 받아와서 추가 작업 수행
		CarInfoService cis = new CarInfoService();
		String msg = "차량 정보를 비활성화할 수 없습니다.\n잠시 후 다시 시도해주세요.";
		
		if(cis.deleteCar(prodCode)) {
			msg = "차량 정보를 정상적으로 비활성화하였습니다.";
		}
		JOptionPane.showMessageDialog(cid, msg);
		
	}
	
	public void windowClosing(WindowEvent we) {
		cid.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		this.cicp = cid.getCicp();
		this.cisp = cid.getCisp();
		
		jbtnImageIcon1 = cicp.getJbtnImageIcon1();
		jbtnImageIcon2 = cicp.getJbtnImageIcon2();
		jbtnImageIcon3 = cicp.getJbtnImageIcon3();
		jbtnImageIcon4 = cicp.getJbtnImageIcon4();
		jpImage = cicp.getJpImage();
		
		cl = cicp.getCl();
		
		dcbmOil = cicp.getDcbmOil();
		jcbOil = cicp.getJcbOil();
		
		dcbmStatSold = cicp.getDcbmStatSold();
		jcbStatSold = cicp.getJcbStatSold();
		
		jbtnPurchase = cicp.getJbtnPurchase();
		
		jbtnModify = cisp.getJbtnModify();
		jbtnDelete = cisp.getJbtnDelete();
		
		if(ae.getSource() == jbtnImageIcon1) {
			cl.show(jpImage, "inputA");
			return;
		} // end if
		
		if(ae.getSource() == jbtnImageIcon2) {
			cl.show(jpImage, "inputB");
			return;
		} // end if
		
		if(ae.getSource() == jbtnImageIcon3) {
			cl.show(jpImage, "inputC");
			return;
		} // end if
		
		if(ae.getSource() == jbtnImageIcon4) {
			cl.show(jpImage, "inputD");
			return;
		} // end if
		
		if(ae.getSource() == jbtnPurchase) {
			JOptionPane.showMessageDialog(cid, "구매 페이지 실행");
			new UserOrderDesign(cid, userCode, prodCode);
			return;
		} // end if
		
//		if(ae.getSource() == jbtnModify) {
//			int ind = jcbOil.getSelectedIndex();
//			if(ind != 0) {
//				String str = dcbmOil.getElementAt(ind);
//				cicp.setJlblOil(" 유종: " + str);
//			}	// end if
//			
//			return;
//		} // end if
//
//		if(ae.getSource() == jbtnModify) {
//			int ind = jcbStatSold.getSelectedIndex();
//			if(ind != 0) {
//				String str = dcbmStatSold.getElementAt(ind);
////				cicp.setJlblOil(" 유종: " + str);
//			}	// end if
//			
//			return;
//		} // end if
//		
		if(ae.getSource() == jbtnModify) {
			JOptionPane.showMessageDialog(cid, "차량 정보를 수정합니다.");
			try {
				updateCars();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
//			JOptionPane.showMessageDialog(cid, "수정 실행");
//			try {
//				addCars();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return;
		} // end if
		
		if(ae.getSource() == jbtnDelete) {
			JOptionPane.showMessageDialog(cid, "삭제 실행");
			try {
				deleteCars();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		} // end if
		
	} // actionPerformed
	
}
