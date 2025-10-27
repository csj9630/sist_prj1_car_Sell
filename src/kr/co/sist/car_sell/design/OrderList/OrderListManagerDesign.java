package kr.co.sist.car_sell.design.OrderList;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import kr.co.sist.car_sell.dto.OrderList.OrderListManagerDTO;
import kr.co.sist.car_sell.event.OrderList.OrderListManagerEvent;
import kr.co.sist.car_sell.service.OrderList.OrderListManagerService;

public class OrderListManagerDesign extends JDialog{
	private JLabel jlogo;
	private JTable jOrderListManagerTable;
	private DefaultTableModel dtmOrderList;
	private OrderListManagerService olms;
	private DefaultTableCellRenderer managerRender;
	
	public OrderListManagerDesign() {
		setTitle("주문 내역[관리자]");
		olms=new OrderListManagerService();
		
		jlogo=new JLabel("쌍용중고차", JLabel.CENTER);
		jlogo.setFont(new Font("맑은 고딕", Font.BOLD, 22));

		String[] columnNames={"주문 번호", "주문 일자", "고객명", "차량 코드", "차량명", "금액", "탁송 상태"};
		
		//Table 내부의 데이터 값을 수정할 수 없도록 Anonymous클래스 override 사용
		dtmOrderList=new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		jOrderListManagerTable=new JTable(dtmOrderList);
		
		//Table 주문번호 색상 표시와 가운데 정렬을 위한 Anonymous 클래스 override 사용
		managerRender=new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
				//주문번호 열만 색깔 표시
				if(column==0) {
					label.setForeground(Color.BLUE);
					//가운데 정렬
					label.setHorizontalAlignment(SwingConstants.CENTER);
					//글씨 굵기 설정
					label.setFont(label.getFont().deriveFont(Font.BOLD));
					//밑줄 처리
					label.setText("<html><u>"+value.toString()+"</u></html>");
					return label;
				} else {
					label.setForeground(Color.BLACK);
					//가운데 정렬
					label.setHorizontalAlignment(SwingConstants.CENTER);
					
					return label;
				}//end else
			}//getTableCellRenderComponent
		};//DefaultTableCellRender
		
		for (int i = 0; i < jOrderListManagerTable.getColumnCount(); i++) {
			jOrderListManagerTable.getColumnModel().getColumn(i).setCellRenderer(managerRender);
		}
		
		List<OrderListManagerDTO> orders=olms.searchAllOrder();
		
		if(orders==null || orders.isEmpty()) {
			JOptionPane.showMessageDialog(this, "주문 내역이 없습니다.");
		} else {
			for(OrderListManagerDTO olmDTO:orders) {
				Object[] rowData=new Object[7];
				rowData[0]=olmDTO.getPayment_code();
				rowData[1]=olmDTO.getOrder_date();
				rowData[2]=olmDTO.getUser_name();
				rowData[3]=olmDTO.getProduct_code();
				rowData[4]=olmDTO.getProduct_name();
				rowData[5]=String.format("%,d", olmDTO.getPrice())+"원";
				rowData[6]=olmDTO.getDelivery_state();
				
				dtmOrderList.addRow(rowData);
			}//end for
		}//end else
				
		JScrollPane jscrollPane=new JScrollPane(jOrderListManagerTable);

		add("North", jlogo);
		add("Center", jscrollPane);
		
		OrderListManagerEvent olme=new OrderListManagerEvent(this);
		jOrderListManagerTable.addMouseListener(olme); //테이블을 누를때의 이벤트 리스너 추가
		
		setBounds(100, 100, 500, 300);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}//orderlistuserdesign
	
	public JLabel getJlogo() {
		return jlogo;
	}

	public JTable getjOrderListManagerTable() {
		return jOrderListManagerTable;
	}

	public DefaultTableModel getDtmOrderList() {
		return dtmOrderList;
	}

}
