package kr.co.sist.car_sell.dao.OrderList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.car_sell.dao.GetConnection;
import kr.co.sist.car_sell.dto.OrderList.OrderListManagerDTO;


public class OrderListManagerDAO {
	private static OrderListManagerDAO olmDAO;
	
	private OrderListManagerDAO() {
		
	}
	
	public static OrderListManagerDAO getInstance() {
		if(olmDAO==null) {
			olmDAO=new OrderListManagerDAO();
		}//end if
		
		return olmDAO;
	}//getInstance
	
	public List<OrderListManagerDTO> selectAllOrder() throws IOException, SQLException {
		List<OrderListManagerDTO> list=new ArrayList<OrderListManagerDTO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		GetConnection gc=GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			String selectOrderAll="select o.payment_code, o.order_date, o.delivery_state,\r\n"
					+ "			 u.user_name,\r\n"
					+ "			 c.product_code, c.car_name, c.price\r\n"
					+ "from ORDER_HISTORY o\r\n"
					+ "join car_info c on o.product_code=c.product_code\r\n"
					+ "join user_info u on o.user_code=u.user_code";
			pstmt=con.prepareStatement(selectOrderAll);
			rs=pstmt.executeQuery();
			
			int payment_code=0; //주문 번호
			Date order_date=null; //주문 일자
			String user_name=null; //고객명
			int product_code=0; //차량 코드
			String car_name=null; //차량명
			int price=0; //금액
			String delivery_state=null; //탁송 상태 
			
			OrderListManagerDTO olmDTO=null;
			while(rs.next()) {
				payment_code=rs.getInt("payment_code");
				order_date=rs.getDate("order_date");
				user_name=rs.getString("user_name");
				product_code=rs.getInt("product_code");
				car_name=rs.getString("car_name");
				price=rs.getInt("price");
				delivery_state=rs.getString("delivery_state");
				
				olmDTO=new OrderListManagerDTO(payment_code, price, product_code, user_name, car_name, delivery_state, order_date);
				list.add(olmDTO);
			}//end if
			
		} finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		
		return list;
	}
}
