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

import kr.co.sist.car_sell.design.CarAddCenterPanel;
import kr.co.sist.car_sell.design.CarAddDesign;
import kr.co.sist.car_sell.design.CarAddNorthPanel;
import kr.co.sist.car_sell.design.CarAddSouthPanel;
import kr.co.sist.car_sell.design.CarListDesign;
import kr.co.sist.car_sell.dto.CarDTO;
import kr.co.sist.car_sell.service.CarAddService;
import kr.co.sist.car_sell.service.ImageService;

public class CarAddEvt extends WindowAdapter implements ActionListener {
	
	private CarAddDesign cad;
	private CarListDesign cld;
	private CarAddNorthPanel canp;
	private CarAddCenterPanel cacp;
	private CarAddSouthPanel casp;
	
	private JButton	jbtnImage1, jbtnImage2, jbtnImage3, jbtnImage4, jbtnPurchase,
					jbtnInsert, jbtnImageIcon1, jbtnImageIcon2, jbtnImageIcon3, jbtnImageIcon4;
	private JComboBox<String> jcbStatSold, jcbOil;
	private DefaultComboBoxModel<String> dcbmStatSold, dcbmOil;
	private JPanel jpImage;
	private int prodCode;
	private int userCode;
	
	private CardLayout cl;
	
	public CarAddEvt(CarAddDesign cad, CarAddNorthPanel canp, CarAddCenterPanel cacp, CarAddSouthPanel casp) {
		
		this.cad = cad;
		this.canp = canp;
		this.cacp = cacp;
		this.casp = casp;
		
	} // CarInfoEvt
	
	public void addCars() throws IOException {
		
		CarAddService cas = new CarAddService();
		// 이름, 이메일, 전화번호, 인트로, 이미지를 받아와서 추가 작업 수행
		int price = Integer.parseInt(cacp.getJtfPrice().getText().trim());
		int cc = Integer.parseInt(cacp.getJtfCc().getText().trim());
		int distance = Integer.parseInt(cacp.getJtfDistance().getText().trim());
		String prodName = cacp.getJtfCarName().getText()+" ";
		String regNum = cacp.getJtfNumberPlate().getText();
		int indSold = cacp.getJcbStatSold().getSelectedIndex();
		String soldStat = cacp.getDcbmStatSold().getElementAt(indSold).toString().trim();
		String carName = prodName.substring(0, prodName.indexOf(" ")).trim();
		int indOil = cacp.getJcbOil().getSelectedIndex();
		String oil = cacp.getDcbmOil().getElementAt(indOil).toString().trim();
		String brandName = cacp.getJtfBrand().getText().trim();
		DateTimeFormatter carDate = DateTimeFormatter.ofPattern("yyyyMMdd");
		Date carYear = Date.valueOf(LocalDate.parse(cacp.getJtfYear1().getText().trim()
				+ "0101", carDate));
		
		CarDTO cDTO = new CarDTO(prodCode, price, cc, distance, prodName, regNum, soldStat, carName, oil, brandName, carYear);
		System.out.println();
		
		int tempProdCode = cas.addCar(cDTO);
		System.out.println(String.valueOf(tempProdCode));
		
		if(tempProdCode > 0) {
			String msg = "차량 정보를 정상적으로 갱신하였습니다.";
			JOptionPane.showMessageDialog(cad, msg);
			
			prodCode = tempProdCode;
			System.out.println(prodCode);
		} else {
			String msg = "차량 정보를 갱신할 수 없습니다.\n잠시 후 다시 시도해주세요.";
			JOptionPane.showMessageDialog(cad, msg);
			
			return;
		}
		
		
		System.out.println(prodCode);
		
		List<String> selectedOptionCodes = new ArrayList<>();
		
		for (Map.Entry<JCheckBox, String> entry : cacp.getOptionMap().entrySet()) {
	        
	        JCheckBox jcbOption = entry.getKey();
	        String optionCode = entry.getValue();
	        
	        if (jcbOption.isSelected()) {
	            selectedOptionCodes.add(optionCode);
	        }
	    }
		
		try {
			cas.updateCarOptions(prodCode, selectedOptionCodes);
	        
	        JOptionPane.showMessageDialog(cad, "옵션이 성공적으로 변경되었습니다.");
	        
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(cad, "업데이트 중 오류 발생: " + ex.getMessage());
	    }
		
		List<String> selectedDefectCodes = new ArrayList<>();
		
		for (Map.Entry<JCheckBox, String> entry : cacp.getDefectMap().entrySet()) {
			
			JCheckBox jcbdefect = entry.getKey();   // JCheckBox 컴포넌트
			String defectCode = entry.getValue(); // "OPT_001" 같은 옵션 코드
			
			if (jcbdefect.isSelected()) {
				selectedDefectCodes.add(defectCode); // 선택된 것만 리스트에 추가
			}
		}
		
		try {
			cas.updateCarDefects(prodCode, selectedDefectCodes);
			
			JOptionPane.showMessageDialog(cad, "차량내역이 성공적으로 변경되었습니다.");
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(cad, "업데이트 중 오류 발생: " + ex.getMessage());
		}
		
		cacp.getJtfPrice().setText("");
		cacp.getJtfCc().setText("");
		cacp.getJtfDistance().setText("");
		cacp.getJtfCarName().setText("");
		cacp.getJtfNumberPlate().setText("");
		cacp.getJtfYear1().setText("");
		
	}
	
	public void windowClosing(WindowEvent we) {
		cad.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		this.cacp = cad.getCacp();
		this.casp = cad.getCasp();
		
		ImageService is = new ImageService();
		
		jbtnImageIcon1 = cacp.getJbtnImageIcon1();
		jbtnImageIcon2 = cacp.getJbtnImageIcon2();
		jbtnImageIcon3 = cacp.getJbtnImageIcon3();
		jbtnImageIcon4 = cacp.getJbtnImageIcon4();
		
		jbtnImage1 = cacp.getJbtnImage1();
		jbtnImage2 = cacp.getJbtnImage2();
		jbtnImage3 = cacp.getJbtnImage3();
		jbtnImage4 = cacp.getJbtnImage4();
		jpImage = cacp.getJpImage();
		
		cl = cacp.getCl();
		
		dcbmOil = cacp.getDcbmOil();
		jcbOil = cacp.getJcbOil();
		
		dcbmStatSold = cacp.getDcbmStatSold();
		jcbStatSold = cacp.getJcbStatSold();
		
		jbtnInsert = casp.getJbtnInsert();
		
		if(ae.getSource() == jbtnImage1) {
			is.saveImg(prodCode);
			return;
		} // end if
		
		if(ae.getSource() == jbtnImage2) {
			cl.show(jpImage, "inputB");
			is.saveImg(prodCode);
			return;
		} // end if
		
		if(ae.getSource() == jbtnImage3) {
			cl.show(jpImage, "inputC");
			is.saveImg(prodCode);
			return;
		} // end if
		
		if(ae.getSource() == jbtnImage4) {
			cl.show(jpImage, "inputD");
			is.saveImg(prodCode);
			return;
		} // end if
		
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
		
		if(ae.getSource() == jbtnInsert) {
			int result = JOptionPane.showConfirmDialog(cacp, "신규 차량을 등록하시겠습니까?");
			if(result == JOptionPane.OK_OPTION) {
				try {
					addCars();
					JOptionPane.showMessageDialog(cacp, "신규 차량을 등록했습니다.");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(cacp, "신규 차량을 등록하지 못했습니다.");
				}
				return;
			} else {
				JOptionPane.showMessageDialog(cacp, "신규 차량 등록을 취소합니다.");
			}
				
			
		} // end if
		
	} // actionPerformed
	
}
