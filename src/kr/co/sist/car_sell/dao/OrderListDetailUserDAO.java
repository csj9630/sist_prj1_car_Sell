package kr.co.sist.car_sell.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.car_sell.dto.OrderListDetailUserDTO;

public class OrderListDetailUserDAO {
	private static OrderListDetailUserDAO olduDAO;	
	
	private OrderListDetailUserDAO() {
		
	}
	
	public static OrderListDetailUserDAO getInstance() {
		if(olduDAO==null) {
			olduDAO=new OrderListDetailUserDAO();
		}//end if
		
		return olduDAO;
	}//getInstance
	
	public OrderListDetailUserDTO searchOneOrder(int payment_code) throws SQLException, IOException{
		OrderListDetailUserDTO olduDTO=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		GetConnection gc=GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			StringBuilder selectOneOrder=new StringBuilder();
			selectOneOrder
			.append("		select o.payment_code, o.order_date, o.delivery_state,\r\n"
					+ "			 c.price, c.car_name, c.car_year, c.distance,\r\n" 
					+ " 		 b.brand_name		")
			.append("	from ORDER_HISTORY o\r\n"
					+ "join car_info c on o.product_code=c.product_code\r\n"
					+ "join user_info u on o.user_code=u.user_code\r\n"
					+ "join brand b on b.brand_name=c.brand_name")
			.append("	where payment_code=?	");
			pstmt=con.prepareStatement(selectOneOrder.toString());
			//바인드 변수 값 설정
			pstmt.setInt(1, payment_code);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				olduDTO=new OrderListDetailUserDTO();
				olduDTO.setProduct_name(rs.getString("car_name"));
				olduDTO.setBrand(rs.getString("brand_name"));
				olduDTO.setCar_year(rs.getDate("car_year"));
				olduDTO.setDistance(rs.getInt("distance"));
				olduDTO.setPayment_code(rs.getInt("payment_code"));
				olduDTO.setOrder_date(rs.getDate("order_date"));
				olduDTO.setPrice(rs.getInt("price"));
				olduDTO.setDelivery_state(rs.getString("delivery_state"));
			}//end if
			
		} finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		
		return olduDTO;
	}//searchOneOrder
	
}//class
