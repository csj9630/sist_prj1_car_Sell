package carSell_prj_test_all;

import java.io.IOException; 
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;

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
		 * [추가] 더미 데이터를 DB에 삽입하기 위한 메서드
		 * @param umDTO
		 * @return
		 * @throws SQLException
		 */
		public int insertUser(UserMgrDTO umDTO) throws SQLException {
			int rowsAffected = 0;
			
			Connection con=null;
			PreparedStatement pstmt=null;
			GetConnection getCon=GetConnection.getInstance();
			
			try {
				con=getCon.getConn();
				// [가정] user_code는 DTO의 값을 그대로 사용, status_activate는 'Y'로 고정
				String insertSql = "insert into TEST_USER_INFO(user_code, id, pass, name, email, tel, address, generate_date, status_activate) "
                        + "values (SEQ_USER_INFO.nextval, ?, ?, ?, ?, ?, ?, sysdate, 'Y')";
				
				pstmt=con.prepareStatement(insertSql);
				
				pstmt.setString(1, umDTO.getId());
		        pstmt.setString(2, umDTO.getPass());
		        pstmt.setString(3, umDTO.getName());
		        pstmt.setString(4, umDTO.getEmail());
		        pstmt.setString(5, umDTO.getTel());
		        pstmt.setString(6, umDTO.getAddress());
				
				rowsAffected = pstmt.executeUpdate();
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				getCon.dbClose(con, pstmt, null);
			}
			
			return rowsAffected;
		}


		/**
		 * [수정] 동적 쿼리를 사용하여 이름 검색 기능 병합
		 * (이하 코드는 이전과 동일)
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
						 .append("from TEST_USER_INFO where status_activate = 'Y' ");
				
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
				String selectCount="select count(*) from TEST_USER_INFO where status_activate = 'Y'";
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
				String deleteUser="update TEST_USER_INFO set status_activate = 'N' where user_code = ?";
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