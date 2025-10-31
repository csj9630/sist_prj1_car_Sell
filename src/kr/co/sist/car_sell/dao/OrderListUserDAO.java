package kr.co.sist.car_sell.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.car_sell.dto.OrderListUserDTO;

public class OrderListUserDAO {
	private static OrderListUserDAO oluDAO;
	
	private OrderListUserDAO() {
		
	}//OrderListUserDAO
	
	public static OrderListUserDAO getInstance() {
		if(oluDAO==null) {
			oluDAO=new OrderListUserDAO();
		}//end if
		return oluDAO;
	}//getInstance

	public List<OrderListUserDTO> selectAllOrder(int userCode) throws SQLException, IOException{
		List<OrderListUserDTO> list=new ArrayList<OrderListUserDTO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		GetConnection gc=GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			String selectOrder="select o.payment_code,o.order_date, o.delivery_state,\r\n"
					+ "			 	   c.product_code,c.car_name, c.price,\r\n"
					+ "				   u.user_code\r\n						  "
					+ "			from ORDER_HISTORY o\r\n"
					+ "			join CAR_info c on o.product_code=c.product_code\r\n"
					+ "			join user_info u on o.user_code=u.user_code\r\n"
					+ "			where u.user_code=?						   \r\n"
					+ "		    order by payment_code 						   ";
			pstmt=con.prepareStatement(selectOrder);
			pstmt.setInt(1, userCode);
			rs=pstmt.executeQuery(); 
			
			int payment_code=0; //주문 번호
			Date order_date=null; //주문 일자
			int product_code=0; //차량 코드
			String product_name=null; //차량명
			int price=0; //금액
			String delivery_state=null; //탁송 상태
			int user_code=0; //사용자 번호
			
			OrderListUserDTO oluDTO=null;
			while(rs.next()) {
				payment_code=rs.getInt("payment_code");
				order_date=rs.getDate("order_date");
				product_code=rs.getInt("product_code");
				product_name=rs.getString("car_name");
				price=rs.getInt("price");
				delivery_state=rs.getString("delivery_state");
				user_code=rs.getInt("user_code");
				
				oluDTO=new OrderListUserDTO(payment_code, order_date, price, product_code, product_name, delivery_state, user_code);
				list.add(oluDTO);
			}//end while
			
		} finally {
			gc.dbClose(con, pstmt, rs);
		}
		
		return list;
	}
	
}//class