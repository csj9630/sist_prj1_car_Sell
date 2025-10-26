package teamproject2;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class OrderListManagerDesign extends JDialog{
	private JLabel jlogo;
	private JTable jOrderListManagerTable;
	private DefaultTableModel dtmOrderList;
	private OrderListManagerService olms;
	
	public OrderListManagerDesign() {
		setTitle("주문 내역[관리자]");
		olms=new OrderListManagerService();
		
		jlogo=new JLabel("쌍용중고차", JLabel.CENTER);
		jlogo.setFont(new Font("맑은 고딕", Font.BOLD, 22));

		String[] columnNames={"주문 번호", "주문 일자", "고객명", "차량 코드", "차량명", "금액", "탁송 상태"};
		dtmOrderList=new DefaultTableModel(columnNames, 0);
		jOrderListManagerTable=new JTable(dtmOrderList);

		List<OrderListManagerDTO> orders=olms.searchAllOrder();
		
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
		}
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
