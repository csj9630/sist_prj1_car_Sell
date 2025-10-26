package kr.co.sist.car_sell.event.OrderList;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import kr.co.sist.car_sell.design.OrderList.OrderListDetailManagerDesign;
import kr.co.sist.car_sell.design.OrderList.OrderListManagerDesign;
import kr.co.sist.car_sell.service.OrderList.OrderListManagerService;


public class OrderListManagerEvent extends WindowAdapter implements WindowListener, MouseListener {
	private OrderListManagerDesign olmd;
	private OrderListManagerService olms;
	
	public OrderListManagerEvent(OrderListManagerDesign olmd) {
		this.olmd=olmd;
	}//OrderListManagerEvent
	
	@Override
	public void windowClosing(WindowEvent we) {
		olmd.dispose();
	}//windowClosing
	
	@Override
	public void mouseClicked(MouseEvent me) {
		int row=olmd.getjOrderListManagerTable().getSelectedRow(); //선택된 행
		int col=olmd.getjOrderListManagerTable().getSelectedColumn(); //선택된 열
		if(row!=-1 && col==0) {
			int orderNum=(int) olmd.getjOrderListManagerTable().getValueAt(row, 0);
			showOrderDetail(orderNum, row);
		}//end if
	}

	public void showOrderDetail(int payment_code, int selectedRow) {
		// 이곳에 주문 상세 페이지를 여는 코드 작성
		JOptionPane.showMessageDialog(olmd, "주문 번호 " + payment_code + "의 상세 내역 페이지로 이동합니다.");
		new OrderListDetailManagerDesign(olmd, payment_code, selectedRow);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	
}
