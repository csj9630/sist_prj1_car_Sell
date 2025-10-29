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
	

	// admin login select
	@DisplayName("AdminSelectOneTest")
	@Test
	@Disabled
	void connectionTest2() {

		AdminDAO adao = AdminDAO.getInstance();

	}

	// user info select
	@Disabled
	@DisplayName("SelectOneTest")
	@Test
	void connectionTest3() {

		UserDTO uDTO = null;
		try {
			UserDAO uDAO = UserDAO.getInstance();
			uDTO = uDAO.selectUserForLogin("testId");
			System.out.println(uDTO);
			assertNotNull(uDTO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end catch

	}
	
	/** [마이페이지/구매용] user_code로 사용자 상세 정보 조회 (CARD 포함) */
	//@Disabled
	@DisplayName("SelectOneTest2")
	@Test
	void selectOneUserTest() {
		
		UserDTO uDTO = null;
		try {
			UserDAO uDAO = UserDAO.getInstance();
			uDTO = uDAO.selectOneUser(2);
			System.out.println(uDTO);
			assertNotNull(uDTO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end catch
		
	}//
	
	@Disabled
	@DisplayName("insertTest")
	@Test
	void connectionInsertTest() {

		UserDTO udto = new UserDTO(99,"asdf7894", "q1w2e3r4", "성진우", "asdf@naver.com", "010-7889-9988",
				"경상북도 고령군 성산면 고탄길 37-1", "8888-8888-8888-8888");
		UserDAO udao = UserDAO.getInstance();
		int result = 0;
		try {
			result = udao.insertUser(udto);
			assertEquals(result, 2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}//
	
//	@Disabled
	@DisplayName("insertTest")
	@Test
	void connectionUpdateTest() {

		UserDTO udto = new UserDTO(1,"ggggg", "q1w2e3r4", "김처루", "asdf@ggggg.com", "010-7889-9988",
				"경상북도 고령군 성산면 고탄길 37-1", "9999-9999-9999-9999");
		UserDAO udao = UserDAO.getInstance();
		int result = 0;
		try {
			result = udao.updateUser(udto);
			assertEquals(result, 2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}//

}
