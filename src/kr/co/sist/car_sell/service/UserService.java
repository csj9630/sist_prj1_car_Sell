package kr.co.sist.car_sell.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import kr.co.sist.car_sell.dao.UserDAO;
import kr.co.sist.car_sell.dto.UserDTO;

public class UserService {
	public UserService() {
		super();
	} // UserService

	/**
	 * DB insert로 사용자 추가<br>
	 * 사용 기능 : 사용자 회원가입<br>
	 * @param uDTO
	 * @return 성공 시 true
	 */
	public boolean addUser(UserDTO uDTO) {
		boolean flag = false;// 기본은 실패 상태

		UserDAO uDAO = UserDAO.getInstance();
		try {
			flag = (uDAO.insertUser(uDTO) == 1); //1이 나오면 true 리턴.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch

		return flag;
	}// addUser

	public List<UserDTO> searchAllUser() {
		List<UserDTO> list = null;

		return list;

	}// searchAllUser

	public UserDTO searchOneUser(int user_code) {
		UserDTO uDTO = null;

		try {
			UserDAO uDAO = UserDAO.getInstance();
			uDTO = uDAO.selectOneUser(user_code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end catch

		return uDTO;
	}// searchOneUser

	public int modifyUser(UserDTO uDTO) {
		int flag = 0;
		try {
			UserDAO uDAO = UserDAO.getInstance();
			flag = uDAO.updateUser(uDTO);
		} catch (SQLException e) {
			flag = 3;
			e.printStackTrace();
		} catch (IOException e) {
			flag = 4;
			e.printStackTrace();
		} // end catch

		return flag;
	}// modifyUser

	public int modifyPassword(int user_code, String newPass) {
		int flag = 0;
		try {
			UserDAO uDAO = UserDAO.getInstance();
			flag = uDAO.updatePassword(user_code, newPass);
		} catch (SQLException e) {
			flag = 2;
			e.printStackTrace();
		} catch (IOException e) {
			flag = 3;
			e.printStackTrace();
		} // end catch

		return flag;
	}// modifyUser

//	public int removeUser(int UserNum) {
//		int flag = 0;
//
//		return flag;
//	}// removeUser

}
