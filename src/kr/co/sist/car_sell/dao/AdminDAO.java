package kr.co.sist.car_sell.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.car_sell.dto.AdminDTO;

public class AdminDAO {
	private static AdminDAO pstmt_aDAO;

	
	private AdminDAO() {
		super();
	}// AdminDAO

	public static AdminDAO getInstance() {
		if (pstmt_aDAO == null) {// 객체가 생성되어 있지 않을 때만
			pstmt_aDAO = new AdminDAO();// 새로 객체를 생성하라.
		} // end if
		return pstmt_aDAO;
	}// getInstance

	public AdminDTO selectOneAdmin(String admin_id) throws SQLException, IOException  {
		AdminDTO aDTO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GetConnection gc = GetConnection.getInstance();
		
		try {
			con = gc.getConn();

			// ㅁ 쿼리문 세팅 및 생성 객체 얻기

			String selectOneAdmin = " select ADMIN_ID, ADMIN_PASS, ADMIN_ADDRESS, ADMIN_CONTACT, ADMIN_FAX, ADMINADD_DATE "
					+ " from ADMIN "
					+ " where ADMIN_ID=? ";
			pstmt = con.prepareStatement(selectOneAdmin);

			// ㅁ 바인드 변수 세팅
			pstmt.setString(1, admin_id);

			// ㅁ 쿼리문 수행 후 결과 받기
			rs = pstmt.executeQuery();

//admin_id, admin_pass, admin_address, admin_contact, admin_fax, adminadd_date
			
			// 조회 결과가 존재한다면, DTO에 넣기
			if (rs.next()) {
				aDTO = new AdminDTO();

				aDTO.setAdmin_id(admin_id);
				aDTO.setAdmin_pass(rs.getString("admin_pass"));
				aDTO.setAdmin_address(rs.getString("admin_address"));
				aDTO.setAdmin_contact(rs.getString("admin_contact"));
				aDTO.setAdmin_fax(rs.getString("admin_fax"));
				aDTO.setAdmin_add_date(rs.getDate("adminadd_date"));
				
			} // end if

		} finally {
			gc.dbClose(con, pstmt, rs);
		} // end finally

		return aDTO;
	}// selectOneAdmin

//	public int insertAdmin(AdminDTO uDTO) {
//		int rowCnt =0;
//		
//		return rowCnt;
//	}//insertAdmin
//	
//	public  int updateAdmin(AdminDTO uDTO) {
//		int flag = 0;
//		
//		return flag;
//	}//updateAdmin

//	public List<AdminDTO> selectAllAdmin() {
//		List<AdminDTO> list = new ArrayList<AdminDTO>();
//		
//		return list;
//	}//selectAllAdmin

//	public int deleteAdmin(int admin_id) {
//		int flag=0;
//		
//		return flag;
//	}//deleteAdmin
	
}// class
