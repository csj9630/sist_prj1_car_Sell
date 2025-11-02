package kr.co.sist.car_sell.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import kr.co.sist.car_sell.design.CarListDesign;
import kr.co.sist.car_sell.design.CarListLeftPanel;
import kr.co.sist.car_sell.design.CarListNorthPanel;
import kr.co.sist.car_sell.design.CarListRightPanel;
import kr.co.sist.car_sell.design.FirstSelectDesign;
import kr.co.sist.car_sell.design.MgrMenuDesign;
import kr.co.sist.car_sell.design.UserMenuDesign;
import kr.co.sist.car_sell.service.CarListService;

public class CarListEvt extends WindowAdapter implements ActionListener, MouseListener {
	
	private JButton jbtnFilter, jbtnMgrMenu, jbtnUserMenu, jbtnLogout;
	
	private CarListDesign cld;
	private CarListNorthPanel clnp;
	private CarListLeftPanel cllp;
	private CarListRightPanel clrp;
	private CarListService cls;
	private String userType;
	private int[] prodCodeArray;
	private int userCode;
	
	public CarListEvt(CarListDesign cld, String userType, int userCode, CarListNorthPanel clnp, CarListLeftPanel cllp, CarListRightPanel clrp, CarListService cls) {
		this.cld = cld;
		this.clnp = clnp;
		this.cllp = cllp;
		this.clrp = clrp;
		this.cls = cls;
		this.userType = userType;
		this.userCode = userCode;
	} // CarListEvt
	
	public int[] searchCars() {
		
		List<String> SelectedBrands = new ArrayList<>();
		List<String> SelectedOils = new ArrayList<>();
		
		for(Map.Entry<JCheckBox, String> entry : cllp.getBrandMap().entrySet()) {
	        JCheckBox jcbBrand = entry.getKey();
	        String BrandName = entry.getValue();
			
	        if (jcbBrand.isSelected()) {
	            SelectedBrands.add(BrandName);
	        }
		}
		
		for(Map.Entry<JCheckBox, String> entry : cllp.getOilMap().entrySet()) {
			JCheckBox jcbOil = entry.getKey();
			String OilName = entry.getValue();
			
			if (jcbOil.isSelected()) {
				SelectedOils.add(OilName);
			}
		}
		
		// 2. Service 레이어를 호출합니다.
		try {
			// Service에 필터링된 차량 목록을 요청합니다.
	        
	        prodCodeArray = cls.getFilteredCars(SelectedBrands, SelectedOils, userType);
	        
	        try {
				clrp.refreshCarList(prodCodeArray);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
		} catch (SQLException e) {
			e.printStackTrace();
			// 사용자에게 오류 메시지 표시
			JOptionPane.showMessageDialog(cld, "차량 검색 중 오류가 발생했습니다.");
		} catch (IOException e) {
			e.printStackTrace();
			// 사용자에게 오류 메시지 표시
			JOptionPane.showMessageDialog(cld, "차량 검색 중 오류가 발생했습니다.");
		}
		 
		return prodCodeArray;
	}
	
	public void windowClosing(WindowEvent we) {
		cld.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		jbtnFilter = cllp.getJbtnFilter();
		jbtnMgrMenu = clnp.getJbtnMgrMenu();
		jbtnUserMenu = clnp.getJbtnUserMenu();
		jbtnLogout = clnp.getJbtnLogout();
		
		if(ae.getSource() == jbtnFilter) {
			searchCars();
			return;
		} // end if
		
		if(ae.getSource() == jbtnMgrMenu) {
			new MgrMenuDesign();
			return;
		} // end if
		
		if(ae.getSource() == jbtnUserMenu) {
			JOptionPane.showMessageDialog(cld, "내 정보 메뉴 진입");
			new UserMenuDesign(userCode);
			return;
		} // end if
		
		if(ae.getSource() == jbtnLogout) {
			JOptionPane.showMessageDialog(cld, "최초 로그인 선택창 진입");
			cld.dispose();
			new FirstSelectDesign();
			return;
		} // end if
		
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		
	}

	@Override
	public void mouseExited(MouseEvent me) {
		
	}

	
}
