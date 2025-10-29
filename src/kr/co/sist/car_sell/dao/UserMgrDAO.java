package kr.co.sist.car_sell.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.car_sell.dto.UserMgrDTO;

public class UserMgrDAO {
	private static UserMgrDAO pmDAO;
		
		private UserMgrDAO() {
			
		}
		public static UserMgrDAO getInstance() {
			if(pmDAO==null) {
				pmDAO=new UserMgrDAO();
				
			}
			return pmDAO;
		}

		/**
		 * [수정] 동적 쿼리를 사용하여 이름 검색 기능 병합
		 */
		public List<UserMgrDTO> searchAllUserInfo(String name) throws SQLException {
			List<UserMgrDTO> list=new ArrayList<UserMgrDTO>();
			
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			GetConnection getCon=GetConnection.getInstance();
			
			try {
				con=getCon.getConn();
				
				StringBuilder selectQuery=new StringBuilder();
				selectQuery.append("select id,generate_date,pass,user_code, name, email, tel, address, status_activate ")
						 .append("from USER_INFO where status_activate = 'Y' ");
				
				if(name != null && !name.isEmpty()) {
					selectQuery.append("and name LIKE ? ");
				}
				
				selectQuery.append("order by user_code desc");
				
				pstmt=con.prepareStatement(selectQuery.toString());
				
				if(name != null && !name.isEmpty()) {
					pstmt.setString(1, "%" + name + "%");
				}
				
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
				    // DTO 생성자 순서: (int, String, String, String, String, String, String, String, Date)
				    UserMgrDTO umDTO = new UserMgrDTO(
				        rs.getInt("user_code"),           // 1. int
				        rs.getString("id"),               // 2. String
				        rs.getString("pass"),             // 3. String
				        rs.getString("name"),             // 4. String
				        rs.getString("email"),            // 5. String
				        rs.getString("address"),          // 6. String
				        rs.getString("tel"),              // 7. String
				        rs.getString("status_activate"),  // 8. String
				        rs.getDate("generate_date")       // 9. Date
				    );
				    list.add(umDTO);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				getCon.dbClose(con, pstmt, rs);
			}
			
			return list;
		}

		public int selectTotalUserCount() throws SQLException {
			int totalCount=0;
			
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			GetConnection getCon=GetConnection.getInstance();
			
			try {
				con=getCon.getConn();
				String selectCount="select count(*) from USER_INFO where status_activate = 'Y'";
				pstmt=con.prepareStatement(selectCount);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					totalCount=rs.getInt(1);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				getCon.dbClose(con, pstmt, rs);
			}
			
			return totalCount;
		}

		public int deleteUser(int userCode) throws SQLException {
			int rows=0;
			
			Connection con=null;
			PreparedStatement pstmt=null;
			GetConnection getCon=GetConnection.getInstance();
			
			try {
				con=getCon.getConn();
				String deleteUser="update USER_INFO set status_activate = 'N' where user_code = ?";
				pstmt=con.prepareStatement(deleteUser);
				pstmt.setInt(1, userCode);
				
				rows=pstmt.executeUpdate();
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				getCon.dbClose(con, pstmt, null); 
			}
			
			return rows;
		}
}