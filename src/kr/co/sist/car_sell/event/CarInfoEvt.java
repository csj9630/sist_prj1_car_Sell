package kr.co.sist.car_sell.event;


import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JPanel;

import kr.co.sist.car_sell.design.CarInfo.CarInfoCenterPanel;

public class CarInfoEvt extends WindowAdapter implements ActionListener, MouseListener {
	
	private CarInfoCenterPanel cicp;
	
	private JButton jbtnImage1, jbtnImage2, jbtnImage3, jbtnImage4, jbtnPurchase;
	private JPanel jpImage;
	
	private CardLayout cl;
	
	public CarInfoEvt(CarInfoCenterPanel cicp) {
		
		this.cicp = cicp;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		jbtnImage1 = cicp.getJbtnImage1();
		jbtnImage2 = cicp.getJbtnImage2();
		jbtnImage3 = cicp.getJbtnImage3();
		jbtnImage4 = cicp.getJbtnImage4();
		jpImage = cicp.getJpImage();
		
		cl = cicp.getCl();
		
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
