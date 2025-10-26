package kr.co.sist.car_sell.service.OrderList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.car_sell.dao.OrderList.OrderListManagerDAO;
import kr.co.sist.car_sell.dto.OrderList.OrderListManagerDTO;

public class OrderListManagerService {
	
	/**
	 * 주문 내역(관리자)에 있는 JTable에 넣을 데이터들을 return하는 method 
	 * @return
	 */
	public List <OrderListManagerDTO> searchAllOrder() {
		List<OrderListManagerDTO> list=new ArrayList<OrderListManagerDTO>();
		OrderListManagerDAO olmDAO=OrderListManagerDAO.getInstance();
		
		try {
			list=olmDAO.selectAllOrder();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}//searchAllOrder

}//class