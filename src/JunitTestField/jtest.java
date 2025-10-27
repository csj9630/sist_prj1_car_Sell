package JunitTestField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sist.car_sell.dao.UserDAO;
import kr.co.sist.car_sell.dto.UserDTO;


class jtest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	@DisplayName("insertTest")
	@Test
	void connectionTest() {

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
		
		
//		assertNotNull(flag);
	}


}
