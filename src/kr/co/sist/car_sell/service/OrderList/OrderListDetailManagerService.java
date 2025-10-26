package teamproject2;

import java.io.IOException;
import java.sql.SQLException;

public class OrderListDetailManagerService {
	private OrderListDetailManagerDAO oldmDAO;
	
	public int modifyOrder(int payment_code, String delivery_state) {
		int flag=0;
		oldmDAO=OrderListDetailManagerDAO.getInstance();
		
		try {
			flag=oldmDAO.updateOrder(payment_code, delivery_state);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}//end catch
		return flag;
	}//modifyOrder
	
	public OrderListDetailManagerDTO searchOneOrder(int payment_code) {
		OrderListDetailManagerDTO oldmDTO=null;
		oldmDAO=OrderListDetailManagerDAO.getInstance();
		try {
			oldmDTO=oldmDAO.selectOneOrder(payment_code);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}//end catch
		
		return oldmDTO;
	}//searchOneOrder
	
}
