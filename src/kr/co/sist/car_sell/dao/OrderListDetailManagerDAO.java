package kr.co.sist.car_sell.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.car_sell.dto.OrderListDetailManagerDTO;

public class OrderListDetailManagerDAO {

	private static OrderListDetailManagerDAO oldmDAO;
	
	private OrderListDetailManagerDAO() {
		
	}
	
	public static OrderListDetailManagerDAO getInstance() {	
		if(oldmDAO==null) {
			oldmDAO=new OrderListDetailManagerDAO();		
		}//end if
		return oldmDAO;
	}//getInstance
	
	public int updateOrder(int payment_code, String delivery_state) throws SQLException, IOException{
		int flag=0;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		GetConnection gc=GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			StringBuilder updateOrder=new StringBuilder();
			updateOrder
			.append("		update ORDER_HISTORY		")
			.append("	set DELIVERY_STATE=?		")
			.append("	where payment_code=?	");
			pstmt=con.prepareStatement(updateOrder.toString());
			
			//바인드 변수 값 설정
			pstmt.setString(1 , delivery_state);
			pstmt.setInt(2 , payment_code);
			flag=pstmt.executeUpdate();
		} finally {
			gc.dbClose(con, pstmt, null);
		}
		
		return flag;
	}//updateOrder
	
	public OrderListDetailManagerDTO selectOneOrder(int payment_code) throws SQLException, IOException {
		OrderListDetailManagerDTO oldmDTO=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		GetConnection gc=GetConnection.getInstance();
		
		try {
			con=gc.getConn();
			StringBuilder selectOneOrder=new StringBuilder();
			selectOneOrder
			.append("		select u.name, u.email, u.tel, u.address,\r\n"
					+ "			 o.payment_code, o.order_date, o.delivery_state,\r\n"
					+ "			 c.price, c.car_name, c.car_year, c.distance,\r\n" 
					+ " 		 b.brand_name		")
			.append("	from ORDER_HISTORY o\r\n"
					+ "join car_info c on o.product_code=c.product_code\r\n"
					+ "join user_info u on o.user_code=u.user_code\r\n"
					+ "join brand b on b.brand_name=c.brand_name")
			.append("	where payment_code=?	");
			pstmt=con.prepareStatement(selectOneOrder.toString());
			
			//바인드 변수 값 설정
			pstmt.setInt(1 , payment_code);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				oldmDTO=new OrderListDetailManagerDTO();
				oldmDTO.setName(rs.getString("name"));
				oldmDTO.setEmail(rs.getString("email"));
				oldmDTO.setTel(rs.getString("tel"));
				oldmDTO.setAddress(rs.getString("address"));
				oldmDTO.setProduct_name(rs.getString("car_name"));
				oldmDTO.setBrand(rs.getString("brand_name"));
				oldmDTO.setCar_year(rs.getDate("order_date"));
				oldmDTO.setDistance(rs.getInt("distance"));
				oldmDTO.setPayment_code(rs.getInt("payment_code"));
				oldmDTO.setOrder_date(rs.getDate("order_date"));
				oldmDTO.setPrice(rs.getInt("price"));
				oldmDTO.setDelivery_state(rs.getString("delivery_state"));
			}
		} finally {
			gc.dbClose(con, pstmt, rs);
		}//end finally
		
		return oldmDTO;
	}//selectOneOrder
	
}//class
