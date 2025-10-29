package kr.co.sist.car_sell.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import kr.co.sist.car_sell.design.CarInfo.CarInfoDesign;
import kr.co.sist.car_sell.design.CarList.CarListDesign;
import kr.co.sist.car_sell.design.CarList.CarListLeftPanel;
import kr.co.sist.car_sell.design.CarList.CarListNorthPanel;
import kr.co.sist.car_sell.design.CarList.CarListRightPanel;
import kr.co.sist.car_sell.design.MgrMenu.MgrMenuDesign;
import kr.co.sist.car_sell.design.UserMenu.UserMenuDesign;

public class CarListEvt extends WindowAdapter implements ActionListener, MouseListener {
	
	private JButton jbtnImage, jbtnMgrMenu, jbtnUserMenu, jbtnLogout;
	
	private CarListDesign cld;
	private CarListNorthPanel clnp;
	private CarListLeftPanel cllp;
	private CarListRightPanel clrp;
	private int user_code;
	
	public CarListEvt(CarListDesign cld, CarListNorthPanel clnp, CarListLeftPanel cllp, CarListRightPanel clrp) {
		this.cld = cld;
		this.clnp = clnp;
		this.cllp = cllp;
		this.clrp = clrp;
		this.user_code=1; // 추후 로그인 페이지의 user_code를 받아올 것.
	}
	
	public void windowClosing(WindowEvent we) {
		cld.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		jbtnMgrMenu = clnp.getJbtnMgrMenu();
		
		if(ae.getSource() == jbtnMgrMenu) {
			new MgrMenuDesign();
			return;
		} // end if
		
		jbtnMgrMenu = clnp.getJbtnUserMenu();
		
		if(ae.getSource() == jbtnMgrMenu) {
			JOptionPane.showMessageDialog(cld, "내 정보 페이지에 진입합니다.");
			new UserMenuDesign(user_code); //내 정보 메뉴 페이지 진입
			// 나중에 매개변수 user code로 수정할 것.
			
			return;
		} // end if
		
		jbtnLogout = clnp.getJbtnLogout();
		
		if(ae.getSource() == jbtnLogout) {
			JOptionPane.showMessageDialog(cld, "최초 로그인 선택 페이지에 진입합니다.");
			return;
		} // end if
		
		jbtnImage = clrp.getJbtnImage();
		
		if(ae.getSource() == jbtnImage) {
			new CarInfoDesign();
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
