package kr.co.sist.car_sell.service;

import java.io.IOException;
import java.sql.SQLException;

import kr.co.sist.car_sell.dao.AdminDAO;
import kr.co.sist.car_sell.dto.AdminDTO;

public class AdminService {
	public AdminService() {
	} // AdminService

//	public boolean addAdmin(AdminDTO uDTO) {
//		boolean flag = false;// 기본은 실패 상태
//
//		return flag;
//	}// addAdmin

//	public List<AdminDTO> searchAllAdmin() {
//		List<AdminDTO> list = null;
//
//		return list;
//
//	}// searchAllAdmin

	/**
	 * 관리자 로그인을 위한 DB searchOne.
	 * @param admin_id 관리자 아이디
	 * @return 관리자 정보 DTO
	 */
	public AdminDTO searchOneAdmin(String admin_id) {
		AdminDTO aDTO = null;
		
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			aDTO = aDAO.selectOneAdmin(admin_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// end catch
		
		return aDTO;
	}// searchOneAdmin

//	public int modifyAdmin(AdminDTO uDTO) {
//		int flag = 0;
//
//		return flag;
//	}// modifyAdmin
//
//	public int removeAdmin(int AdminNum) {
//		int flag = 0;
//
//		return flag;
//	}// removeAdmin
}
