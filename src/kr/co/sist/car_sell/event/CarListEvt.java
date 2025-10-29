package kr.co.sist.car_sell.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import kr.co.sist.car_sell.design.CarInfoDesign;
import kr.co.sist.car_sell.design.CarListDesign;
import kr.co.sist.car_sell.design.CarListLeftPanel;
import kr.co.sist.car_sell.design.CarListNorthPanel;
import kr.co.sist.car_sell.design.CarListRightPanel;
import kr.co.sist.car_sell.design.MgrMenuDesign;
import kr.co.sist.car_sell.design.UserMenuDesign;
import kr.co.sist.car_sell.service.CarListService;

public class CarListEvt extends WindowAdapter implements ActionListener, MouseListener {
	
	private JButton jbtnImage, jbtnMgrMenu, jbtnUserMenu, jbtnLogout;
	
	private CarListDesign cld;
	private CarListNorthPanel clnp;
	private CarListLeftPanel cllp;
	private CarListRightPanel clrp;
	private CarListService cls;
	private int userCode;
	
	public CarListEvt(CarListDesign cld, int userCode, CarListNorthPanel clnp, CarListLeftPanel cllp, CarListRightPanel clrp, CarListService cls) {
		this.cld = cld;
		this.clnp = clnp;
		this.cllp = cllp;
		this.clrp = clrp;
		this.cls = cls;
		this.userCode =  userCode; // 추후 로그인 페이지의 user_code를 받아올 것.
	} // CarListEvt
	
	public void windowClosing(WindowEvent we) {
		cld.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		jbtnMgrMenu = clnp.getJbtnMgrMenu();
		jbtnUserMenu = clnp.getJbtnUserMenu();
		jbtnLogout = clnp.getJbtnLogout();
		
		if(ae.getSource() == jbtnMgrMenu) {
			new MgrMenuDesign();
			return;
		} // end if
		
		if(ae.getSource() == jbtnUserMenu) {
			JOptionPane.showMessageDialog(cld, "내 정보 페이지에 진입합니다.");
			new UserMenuDesign(userCode); //내 정보 메뉴 페이지 진입
			// 나중에 매개변수 userCode로 수정할 것.
			return;
		} // end if
		
		if(ae.getSource() == jbtnLogout) {
			JOptionPane.showMessageDialog(cld, "최초 로그인 선택 페이지에 진입합니다.");
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
