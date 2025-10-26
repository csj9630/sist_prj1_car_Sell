package kr.co.sist.car_sell.design.OrderList;

import java.awt.Font;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.car_sell.dto.OrderList.OrderListUserDTO;
import kr.co.sist.car_sell.event.OrderList.OrderListUserEvent;
import kr.co.sist.car_sell.service.OrderList.OrderListUserService;

public class OrderListUserDesign extends JDialog{
	private JLabel jlogo;
	private JTable jOrderListUserTable;
	private DefaultTableModel dtmOrderList;
	private OrderListUserService olus;
	
	public OrderListUserDesign(int userCode) {
		setTitle("주문 내역[사용자]");
		
		//상단 쌍용중고차 로고
		jlogo=new JLabel("쌍용중고차", JLabel.CENTER);
		jlogo.setFont(new Font("맑은 고딕", Font.BOLD, 22));

		//테이블 컬럼명 설정 
		String[] columnNames={"주문 번호", "주문 일자", "차량 코드", "차량명", "금액", "탁송 상태"};
		dtmOrderList=new DefaultTableModel(columnNames, 0);
		jOrderListUserTable=new JTable(dtmOrderList);
		
		//OrderListUserService에서 method에 메개변수를 사용자 번호로 받아서 주문 내역 가져오는 부분
		olus=new OrderListUserService();
		List<OrderListUserDTO> orders=olus.searchAllOrder(userCode);
		
		for(OrderListUserDTO oluDTO:orders) {
			Object[] rowData=new Object[6];
			rowData[0]=oluDTO.getPayment_code();
			rowData[1]=oluDTO.getOrder_date();
			rowData[2]=oluDTO.getProduct_code();
			rowData[3]=oluDTO.getProduct_name();
			rowData[4]=String.format("%,d", oluDTO.getPrice())+"원";
			rowData[5]=oluDTO.getDelivery_state();
			
			dtmOrderList.addRow(rowData);
		}
				
		JScrollPane jscrollPane=new JScrollPane(jOrderListUserTable);

		add("North", jlogo);
		add("Center", jscrollPane);
		
		OrderListUserEvent olue=new OrderListUserEvent(this);
		jOrderListUserTable.addMouseListener(olue);
		
		setBounds(100, 100, 500, 300);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}//orderlistuserdesign
	
	public JLabel getJlogo() {
		return jlogo;
	}

	public JTable getjOrderListUserTable() {
		return jOrderListUserTable;
	}

	public DefaultTableModel getDtmOrderList() {
		return dtmOrderList;
	}
	
}//class
