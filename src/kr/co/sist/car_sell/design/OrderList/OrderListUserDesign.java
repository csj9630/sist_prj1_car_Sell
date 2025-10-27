package kr.co.sist.car_sell.design.OrderList;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.car_sell.dto.OrderList.OrderListUserDTO;
import kr.co.sist.car_sell.event.OrderList.OrderListUserEvent;
import kr.co.sist.car_sell.service.OrderList.OrderListUserService;

public class OrderListUserDesign extends JDialog{
	private JLabel jlogo;
	private JTable jOrderListUserTable;
	private DefaultTableModel dtmOrderList;
	private OrderListUserService olus;
	private DefaultTableCellRenderer colorRender;
	
	public OrderListUserDesign(int userCode) {
		setTitle("주문 내역[사용자]");
		
		//상단 쌍용중고차 로고
		jlogo=new JLabel("쌍용중고차", JLabel.CENTER);
		jlogo.setFont(new Font("맑은 고딕", Font.BOLD, 22));

		//테이블 컬럼명 설정 
		String[] columnNames={"주문 번호", "주문 일자", "차량 코드", "차량명", "금액", "탁송 상태"};
		dtmOrderList=new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jOrderListUserTable=new JTable(dtmOrderList);
		
		//Table 주문번호 색상 표시와 가운데 정렬을 위한 Anonymous 클래스 override 사용
		colorRender=new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
				
				//가운데 정렬
				label.setHorizontalAlignment(SwingConstants.CENTER);
				
				//주문번호 열만 색깔 표시
				if(column==0) {
					label.setForeground(Color.BLUE);
				} else {
					label.setForeground(Color.BLACK);
				}
				return label;
			}
		};//DefaultTableCellRender
		
		for (int i = 0; i < jOrderListUserTable.getColumnCount(); i++) {
			jOrderListUserTable.getColumnModel().getColumn(i).setCellRenderer(colorRender);
		}
				
		//OrderListUserService에서 method에 메개변수를 사용자 번호로 받아서 주문 내역 가져오는 부분
		olus=new OrderListUserService();
		List<OrderListUserDTO> orders=olus.searchAllOrder(userCode);
		
		if(orders==null || orders.isEmpty()) {
			JOptionPane.showMessageDialog(this, "주문 내역이 없습니다.");
		} else{
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
		}//end else
				
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
