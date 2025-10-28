package kr.co.sist.car_sell.event;


import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.co.sist.car_sell.design.CarInfo.CarInfoCenterPanel;
import kr.co.sist.car_sell.design.CarInfo.CarInfoDesign;
import kr.co.sist.car_sell.design.CarInfo.CarInfoNorthPanel;
import kr.co.sist.car_sell.design.CarInfo.CarInfoSouthPanel;
import kr.co.sist.car_sell.design.LoginRegister.UserOrderDesign;

public class CarInfoEvt extends WindowAdapter implements ActionListener, MouseListener {
	
	private CarInfoDesign cid;
	
	private CarInfoNorthPanel cinp;
	private CarInfoCenterPanel cicp;
	private CarInfoSouthPanel cisp;
	
	private JButton jbtnImage1, jbtnImage2, jbtnImage3, jbtnImage4, jbtnPurchase, jbtnModify, jbtnDelete;
	private JPanel jpImage;
	
	private CardLayout cl;
	
	public CarInfoEvt(CarInfoDesign cid, CarInfoNorthPanel cinp, CarInfoCenterPanel cicp, CarInfoSouthPanel cisp) {
		
		this.cid = cid;
		this.cinp = cinp;
		this.cicp = cicp;
		this.cisp = cisp;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		jbtnImage1 = cicp.getJbtnImage1();
		jbtnImage2 = cicp.getJbtnImage2();
		jbtnImage3 = cicp.getJbtnImage3();
		jbtnImage4 = cicp.getJbtnImage4();
		jpImage = cicp.getJpImage();
		
		cl = cicp.getCl();
		
		jbtnPurchase = cicp.getJbtnPurchase();
		
		jbtnModify = cisp.getJbtnModify();
		jbtnDelete = cisp.getJbtnDelete();
		
		if(ae.getSource() == jbtnImage1) {
			cl.show(jpImage, "inputA");
		} // end if
		
		if(ae.getSource() == jbtnImage2) {
			cl.show(jpImage, "inputB");
		} // end if
		
		if(ae.getSource() == jbtnImage3) {
			cl.show(jpImage, "inputC");
		} // end if
		
		if(ae.getSource() == jbtnImage4) {
			cl.show(jpImage, "inputD");
		} // end if
		
		if(ae.getSource() == jbtnPurchase) {
//			new UserOrderDesign();
			//생성자 조건을 명확히 할 것.
		} // end if
		
		if(ae.getSource() == jbtnModify) {
			JOptionPane.showMessageDialog(cid, "차량 정보를 수정합니다.");
		} // end if
		
		if(ae.getSource() == jbtnDelete) {
			JOptionPane.showMessageDialog(cid, "차량 정보를 삭제합니다.");
		} // end if
		
	} // actionPerformed

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
