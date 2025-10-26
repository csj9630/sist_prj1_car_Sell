package kr.co.sist.car_sell.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import kr.co.sist.car_sell.design.CarInfo.CarInfoDesign;
import kr.co.sist.car_sell.design.CarList.CarListDesign;
import kr.co.sist.car_sell.design.CarList.CarListLeftPanel;
import kr.co.sist.car_sell.design.CarList.CarListNorthPanel;
import kr.co.sist.car_sell.design.CarList.CarListRightPanel;
import kr.co.sist.car_sell.design.MgrMenu.MgrMenuDesign;

public class CarListEvt extends WindowAdapter implements ActionListener, MouseListener {
	
	private JButton jbtnImage, jbtnMgrMenu, jbtnUserMenu, jbtnLogout;
	
	private CarListDesign cld;
	private CarListNorthPanel clnp;
	private CarListLeftPanel cllp;
	private CarListRightPanel clrp;
	
	public CarListEvt(CarListDesign cld, CarListNorthPanel clnp, CarListLeftPanel cllp, CarListRightPanel clrp) {
		this.cld = cld;
		this.clnp = clnp;
		this.cllp = cllp;
		this.clrp = clrp;
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
