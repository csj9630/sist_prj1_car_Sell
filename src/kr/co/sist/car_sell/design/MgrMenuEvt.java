package kr.co.sist.car_sell.design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import kr.co.sist.car_sell.event.UserMgrEvt;

public class MgrMenuEvt extends WindowAdapter implements ActionListener, MouseListener {
	
	private JButton jbtnAddCar, jbtnManageMember, jbtnManageSettlement, jbtnOrderList;
	
	private MgrMenuDesign mmld;
	private MgrMenuCenterPanel mmcp;
	private MgrMenuNorthPanel mmnp;
	
	public MgrMenuEvt(MgrMenuDesign mmld, MgrMenuNorthPanel mmnp, MgrMenuCenterPanel mmcp) {
		this.mmld = mmld;
		this.mmnp = mmnp;
		this.mmcp = mmcp;
	}
	
	public void windowClosing(WindowEvent we) {
		mmld.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		jbtnAddCar = mmcp.getJbtnAddCar();
		
		if(ae.getSource() == jbtnAddCar) {
			JOptionPane.showMessageDialog(mmld, "차량등록 페이지에 진입합니다.");
			return;
		} // end if
		
		jbtnManageMember = mmcp.getJbtnManageMember();
		
		if(ae.getSource() == jbtnManageMember) {
			JOptionPane.showMessageDialog(mmld, "회원관리 페이지에 진입합니다.");
			UserMgrDesign umd=new UserMgrDesign(null);
			new UserMgrEvt(umd);
			umd.setModal(true);
			umd.setVisible(true);
			return;
		} // end if
		
		jbtnManageSettlement = mmcp.getJbtnManageSettlement();
		
		if(ae.getSource() == jbtnManageSettlement) {
			JOptionPane.showMessageDialog(mmld, "정산관리 페이지에 진입합니다.");
			new SettlementDesign(null);
			return;
		} // end if
		
		jbtnOrderList = mmcp.getJbtnOrderList();
		
		if(ae.getSource() == jbtnOrderList) {
			JOptionPane.showMessageDialog(mmld, "주문내역 페이지에 진입합니다.");
			new OrderListManagerDesign();
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
