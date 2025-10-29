package kr.co.sist.car_sell.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import kr.co.sist.car_sell.design.OrderListDetailUserDesign;
import kr.co.sist.car_sell.design.OrderListUserDesign;

public class OrderListUserEvent extends WindowAdapter implements WindowListener, MouseListener {
	
	private OrderListUserDesign olud;
	
	public OrderListUserEvent(OrderListUserDesign olud) {
		this.olud=olud;
	}//OrderListUserEvent
	
	//마우스 클릭할 때 발생하는 이벤트
	@Override
	public void mouseClicked(MouseEvent e) {
		int row=olud.getjOrderListUserTable().getSelectedRow(); //선택한 행 
		int col=olud.getjOrderListUserTable().getSelectedColumn(); //선택된 열
		if(row!=-1 && col==0) {
			int orderNum=(int) olud.getjOrderListUserTable().getValueAt(row, 0);
			showOrderDetail(orderNum, row);
		}
	}//mouseClicked

	public void showOrderDetail(int payment_code, int selectedRow) {
		JOptionPane.showMessageDialog(olud, "주문 번호 " + payment_code + "의 상세 내역 페이지로 이동합니다.");
		new OrderListDetailUserDesign(payment_code, selectedRow);
	}//showOrderDetail
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
