package kr.co.sist.car_sell.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import kr.co.sist.car_sell.dao.UserDAO;
import kr.co.sist.car_sell.dao.car.CarDAO;
import kr.co.sist.car_sell.dao.image.ImageDAO;
import kr.co.sist.car_sell.dao.order.OrderDAO;
import kr.co.sist.car_sell.dto.UserDTO;
import kr.co.sist.car_sell.dto.car.CarDTO;
import kr.co.sist.car_sell.dto.order.OrderDTO;

public class UserService {
	public UserService() {
		super();
	} // UserService

	/**
	 * 에러 발생 시 사용자에게 보여줄 다이얼로그
	 */
	public void showErrorDg() {
		JOptionPane.showMessageDialog(null, "잠시 후에 다시 시도하여 주십시오.");
		return;
	}// showErrorJD

//==================남지우 파트====================================
	/** [로그인용] 사용자 로그인 로직 */
	public UserDTO loginUser(String id, String passStr) throws SQLException, IOException {
		UserDAO uDAO = UserDAO.getInstance();
		UserDTO uDTO = uDAO.selectUserForLogin(id);

		if (uDTO == null || !uDTO.getPass().equals(passStr)) {
			return null;
		} // ID 없거나 비번 불일치
		if (uDTO.getStatus_activate() != "Y") {
			throw new SQLException("비활성화된 계정입니다.");
		} // 비활성 계정
		return uDTO; // 성공
	}// loginUser

	/** [회원가입용] 사용자 등록 로직 */
	public void registerUser(UserDTO uDTO) throws SQLException, IOException {
		UserDAO uDAO = UserDAO.getInstance();
		int result = uDAO.insertUser(uDTO); // DAO에서 트랜잭션 처리
		if (result < 2) {
			throw new SQLException("회원가입 DB 처리 오류");
		}
	}// registerUser

	// ▼▼▼ 주석 해제 및 클래스/패키지명 확인 ▼▼▼
	/** [구매페이지용] 차량 상세 정보 조회 */
	public CarDTO getCarDetails(int productCode) throws SQLException, IOException {
		return CarDAO.getInstance().selectCarByCode(productCode); // CarDAO 사용
	}// getCarDetails

	/** [구매페이지용] 차량 이미지 경로 목록 조회 */
	public List<String> getCarImagePaths(int productCode) throws SQLException, IOException { // 반환 타입 List<String>
		return ImageDAO.getInstance().selectCarImagePaths(productCode); // ImageDAO, 새 메소드 사용
	}// getCarImagePaths

	/** [구매페이지용] 주문하기 로직 (트랜잭션) */
	public void placeOrder(OrderDTO oDTO) throws SQLException, IOException {
		OrderDAO oDAO = OrderDAO.getInstance(); // OrderDAO 사용
		int result = oDAO.insertOrder(oDTO);
		if (result < 2) { // 성공 시 2 가정
			throw new SQLException("주문 DB 처리 오류");
		}
	}// placeOrder

	// ==================최승준 파트====================================
	/**
	 * DB insert로 사용자 추가<br>
	 * 사용 기능 : 사용자 회원가입<br>
	 * 
	 * @param uDTO
	 * @return 성공 시 true
	 */
	public boolean addUser(UserDTO uDTO) {
		boolean flag = false;// 기본은 실패 상태

		UserDAO uDAO = UserDAO.getInstance();
		try {
			flag = (uDAO.insertUser(uDTO) == 2); // 2이 나오면 true 리턴.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showErrorDg();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showErrorDg();
		} // end catch

		return flag;
	}// addUser

	public UserDTO searchOneUser(int user_code) {
		UserDTO uDTO = null;

		try {
			UserDAO uDAO = UserDAO.getInstance();
			uDTO = uDAO.selectOneUser(user_code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showErrorDg();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showErrorDg();
		} // end catch

		return uDTO;
	}// searchOneUser

	public boolean modifyUser(UserDTO uDTO) {
		boolean flag = false;
		try {
			UserDAO uDAO = UserDAO.getInstance();
			flag = (uDAO.updateUser(uDTO) == 2);
		} catch (SQLException e) {
			e.printStackTrace();
			showErrorDg();
		} catch (IOException e) {

			e.printStackTrace();
			showErrorDg();
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
			showErrorDg();
		} catch (IOException e) {
			flag = 3;
			e.printStackTrace();
			showErrorDg();
		} // end catch

		return flag;
	}// modifyUser

}// class
