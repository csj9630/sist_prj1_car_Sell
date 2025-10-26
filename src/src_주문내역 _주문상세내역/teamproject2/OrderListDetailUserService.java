package teamproject2;

import java.io.IOException;
import java.sql.SQLException;

public class OrderListDetailUserService {
	
	private OrderListDetailUserDAO olduDAO;
	
	public OrderListDetailUserDTO searchOneOrder(int payment_code) {
		OrderListDetailUserDTO olduDTO=null;
		
		olduDAO=OrderListDetailUserDAO.getInstance();
		try {
			olduDTO=olduDAO.searchOneOrder(payment_code);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}//end catch
		
		return olduDTO;
	}
}//class