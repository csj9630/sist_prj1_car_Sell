package kr.co.sist.car_sell.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.sist.car_sell.dao.UserMgrDAO;
import kr.co.sist.car_sell.dto.UserMgrDTO;

/**
 * ... (클래스 다이어그램 주석) ...
 */
public class UserMgrService {

	private UserMgrDAO umDAO;

	public UserMgrService() {
		umDAO = UserMgrDAO.getInstance();
	}


	/**
	 * [수정] DB 조회 실패 또는 결과가 없을 시 더미 데이터 반환 (검색어 필터링)
	 */
	public List<UserMgrDTO> searchAllUsers(String name) {
		List<UserMgrDTO> list=null;
		try {
			// [수정] DAO의 병합된 메서드 호출
			list=umDAO.searchAllUserInfo(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * [수정] 전체 유저 수 조회 (DB가 비어있으면 더미 데이터 수 반환)
	 */
	public int getTotalUserCount() {
		int totalCount=0;
		try {
			totalCount = umDAO.selectTotalUserCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return totalCount;
	}

	
	// removeUsers 메서드는 동일 (수정 필요 없음)
	public boolean removeUsers(List<Integer> userCodes) {
		boolean allSuccess=true;
		
		try {
			for(int code : userCodes) {
				int rowsAffected = umDAO.deleteUser(code);
				if(rowsAffected == 0) { 
					allSuccess=false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			allSuccess=false; 
		}
		
		return allSuccess;
	}
	
}
//class