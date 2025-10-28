package JunitTestField;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sist.car_sell.dao.AdminDAO;
import kr.co.sist.car_sell.dao.UserDAO;
import kr.co.sist.car_sell.dto.AdminDTO;
import kr.co.sist.car_sell.dto.UserDTO;


class jtest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	@DisplayName("insertTest")
	@Test
	void connectionTest1() {

		UserDTO udto = new UserDTO("asdf7894","q1w2e3r4","성진우","asdf@naver.com","010-7889-9988","경상북도 고령군 성산면 고탄길 37-1","8888-8888-8888-8888");
		UserDAO udao = UserDAO.getInstance();
		int result =0;
		try {
			result=udao.insertUser(udto);
			assertEquals(result, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//admin login select
	@DisplayName("AdminSelectOneTest")
	@Test
	@Disabled
	void connectionTest2() {
		
		AdminDAO adao = AdminDAO.getInstance();
		
		AdminDTO adto = null;
		
		try {
			adto=adao.selectOneAdmin("min");
			System.out.println(adto);
			assertNotNull(adto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//user info select 
		@DisplayName("SelectOneTest")
		@Test
		void connectionTest3() {
			
		
			UserDTO uDTO = null;
			try {
				UserDAO uDAO = UserDAO.getInstance();
				uDTO = uDAO.selectOneUser(2);
				assertNotNull(uDTO);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // end catch
			
		}


}
