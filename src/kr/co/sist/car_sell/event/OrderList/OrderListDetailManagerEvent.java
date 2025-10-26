package kr.co.sist.car_sell.event.OrderList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import kr.co.sist.car_sell.design.OrderList.OrderListDetailManagerDesign;
import kr.co.sist.car_sell.service.OrderList.OrderListDetailManagerService;

public class OrderListDetailManagerEvent extends WindowAdapter implements ActionListener, WindowListener, ItemListener {
	
	private OrderListDetailManagerDesign oldmd;
	
	public OrderListDetailManagerEvent(OrderListDetailManagerDesign oldmd) {
		this.oldmd=oldmd;
	}//OrderListDetailManagerEvent
	
	@Override
	public void windowClosing(WindowEvent we) {
		oldmd.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==oldmd.getJcbStatus()) {
			String selectedState = (String) oldmd.getJcbStatus().getSelectedItem();
		}
		
		if(ae.getSource()==oldmd.getJbtnChange()) { //변경 버튼을 누르면
			String newState=(String)oldmd.getJcbStatus().getSelectedItem();
			int paymentCode=oldmd.getPaymentCode();
			
			OrderListDetailManagerService oldms = new OrderListDetailManagerService();
			oldms.modifyOrder(paymentCode, newState);
			
			oldmd.updateStatus(newState);
			oldmd.changeStatus(newState);
			
		}
	}//actionPerformed
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

}
