package teamproject2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderListUserService {
	
	private OrderListUserDAO oluDAO;
	
	public OrderListUserService() {
		oluDAO=OrderListUserDAO.getInstance();
	}
	
	public List<OrderListUserDTO> searchAllOrder(int userCode) {
		List<OrderListUserDTO> list=new ArrayList<OrderListUserDTO>();
		try {
			list=oluDAO.selectAllOrder(userCode);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}//searchAllOrder
	
}//class
