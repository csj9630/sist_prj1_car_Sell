package kr.co.sist.car_sell.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.car_sell.dto.UserDTO;

/**
 * DBMS의 USER_INFO 테이블에서 값을 JAVA로 가져오기 위한 파트.
 */
public class UserDAO {
	private static UserDAO pstmt_uDAO;

	private UserDAO() {
		super();
	}// UserDAO

	public static UserDAO getInstance() {
		if (pstmt_uDAO == null) {// 객체가 생성되어 있지 않을 때만
			pstmt_uDAO = new UserDAO();// 새로 객체를 생성하라.
		} // end if
		return pstmt_uDAO;
	}// getInstance

	/** [로그인용] ID로 사용자 기본 정보 조회 (CARD 제외) */
	public UserDTO selectUserForLogin(String id) throws SQLException, IOException {
		UserDTO uDTO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GetConnection gc = GetConnection.getInstance();

		try {
			con = gc.getConn();
			String selectSql = "select user_code, id, pass, name, status_activate from user_info where id = ?";
			pstmt = con.prepareStatement(selectSql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				uDTO = new UserDTO();
				uDTO.setUser_code(rs.getInt("USER_CODE"));
				uDTO.setId(rs.getString("ID"));
				uDTO.setPass(rs.getString("PASS"));
				uDTO.setName(rs.getString("NAME"));
				uDTO.setStatus_activate(rs.getString("STATUS_ACTIVATE"));
			} // end if
		} finally {
			gc.dbClose(con, pstmt, rs);
		} // end finally
		return uDTO;

	}// selectUserForLogin

	/** [마이페이지/구매용] user_code로 사용자 상세 정보 조회 (CARD 포함) */
	public UserDTO selectOneUser(int user_code) throws SQLException, IOException {
		UserDTO uDTO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GetConnection gc = GetConnection.getInstance();

		try {
			con = gc.getConn();

			// ㅁ 쿼리문 세팅 및 생성 객체 얻기

			String selectOneUser = " select ui.user_code,ui.id,ui.pass,ui.name,ui.email,ui.tel,ui.address,ui.generate_date,ui.status_activate,ci.credit_card "
					+ " from user_info ui, card_info ci " + " where (ui.user_code = ci.user_code) and ui.USER_CODE=? ";

			pstmt = con.prepareStatement(selectOneUser);

			// ㅁ 바인드 변수 세팅
			pstmt.setInt(1, user_code);

			// ㅁ 쿼리문 수행 후 결과 받기
			rs = pstmt.executeQuery();

			// 조회 결과가 존재한다면, DTO에 넣기
			if (rs.next()) {
				uDTO = new UserDTO();
				uDTO.setUser_code(user_code);

				uDTO.setId(rs.getString("id"));
				uDTO.setPass(rs.getString("pass"));
				uDTO.setName(rs.getString("name"));
				uDTO.setEmail(rs.getString("email"));
				uDTO.setTel(rs.getString("tel"));
				uDTO.setAddress(rs.getString("address"));
				uDTO.setGenerate_date(rs.getDate("generate_date"));
				uDTO.setCard_num(rs.getString("credit_card"));
			} // end if

		} finally {
			gc.dbClose(con, pstmt, rs);
		} // end finally

		return uDTO;
	}// selectOneUser

	/**
	 * [회원가입용]<br>
	 * uDTO의 사용자 정보를 DB에 insert로 넣는다.<br>
	 * USER_INFO와 CARD_INFO에 트랜잭션으로 동시 삽입<br>
	 * 
	 * @param uDTO
	 * @return 성공 시 1 리턴.
	 * @throws SQLException
	 * @throws IOException
	 */
	public int insertUser(UserDTO uDTO) throws SQLException, IOException {
		int rowCntUser, rowCntCard = 0;
		int nextUserCode = 0;
		Connection con = null;
		PreparedStatement pstmtUser = null, pstmtCard = null;

		GetConnection gc = GetConnection.getInstance();

		try {
			con = gc.getConn();
			con.setAutoCommit(false); // 트랜잭션 시작, 오토커밋 해제

			// ㅁ 시퀀스 가져오기 (try-with-resources)
			try (PreparedStatement psSeq = con.prepareStatement("SELECT SEQ_USER_INFO.NEXTVAL FROM DUAL");
					ResultSet rsSeq = psSeq.executeQuery()) {
				if (rsSeq.next()) {
					nextUserCode = rsSeq.getInt(1);
				} else {
					con.rollback();
					throw new SQLException("시퀀스 오류");
				} // end else
			} // end try

			// ㅁ 쿼리문 객체 생성
			StringBuilder insertUser = new StringBuilder();
//@formatter:off
			insertUser
				.append(" insert into user_info ")
				.append(" (user_code, id, pass, name, email, tel, address, generate_date, status_activate) ")
				.append(" values(?,?,?,?,")
				.append("?,?,?,sysdate,'Y')")
				;			
//@formatter:on

			// USER_INFO:PreparedStatement 세팅
			pstmtUser = con.prepareStatement(insertUser.toString());

			// ㅁ 바인드 변수 값을 설정함.
			pstmtUser.setInt(1, nextUserCode);
			pstmtUser.setString(2, uDTO.getId());
			pstmtUser.setString(3, uDTO.getPass());
			pstmtUser.setString(4, uDTO.getName());
			pstmtUser.setString(5, uDTO.getEmail());
			pstmtUser.setString(6, uDTO.getTel());
			pstmtUser.setString(7, uDTO.getAddress());

			// ㅁ쿼리문 수행 후 결과 얻기 : 추가한 행 수를 리턴 받는다.
			rowCntUser = pstmtUser.executeUpdate();// 성공하면 1

			// -------------------------------------------------------------
			// CARD_INFO:PreparedStatement 세팅

			// ㅁ 쿼리문 객체 생성
			StringBuilder insertCard = new StringBuilder();
//@formatter:off
			insertCard
				.append(" insert into card_info ")
				.append(" (user_code, credit_card, registration_date )  ")
				.append(" values (?, ?, sysdate) ")
				;			
//@formatter:on

			pstmtCard = con.prepareStatement(insertCard.toString());

			// ㅁ 바인드 변수 값을 설정함.
			pstmtCard.setInt(1, nextUserCode);
			pstmtCard.setString(2, uDTO.getCard_num());

			// ㅁ 쿼리문 수행 후 결과 얻기 : 추가한 행 수를 리턴 받는다.
			rowCntCard = pstmtCard.executeUpdate();// 성공하면 1

			if (rowCntUser == 1 && rowCntCard == 1) {
				con.commit();
			} // 성공
			else {
				con.rollback();// 하나라도 실패 시 롤백
				throw new SQLException("삽입 실패");
			} // 실패

		} catch (SQLException e) {
			if (con != null)
				con.rollback();
			throw e;

		} finally {
			gc.dbClose(con, pstmtUser, null);
			gc.dbClose(con, pstmtCard, null);

		} // end finally

		return rowCntUser + rowCntCard;
	}// insertUser

	/**
	 * user_info 테이블과 card_info 테이블을 동시에 update한다.<br>
	 * 사용 파트 : 내정보수정<br>
	 * outer join에서 변경<br>
	 * 
	 * @param uDTO 사용자 정보 DTO
	 * @return 성공 시 2 리턴.
	 * @throws SQLException
	 * @throws IOException
	 */
	public int updateUser(UserDTO uDTO) throws SQLException, IOException {
		int rowCntUser, rowCntCard = 0;

		GetConnection gc = GetConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmtUser = null;
		PreparedStatement pstmtCard = null;

		try {
			// 1.드라이버 로딩+2.커넥션 얻기
			con = gc.getConn();
			con.setAutoCommit(false); // 트랜잭션 시작, 오토커밋 해제

			// 3. 쿼리문 생성 객체 얻기

			// 이걸 StringBuilder로 하기
			StringBuilder updateUser = new StringBuilder();

			// --------------------user_info 테이블 수정--------------------
			//@formatter:off		
			//회원번호를 사용하여 나이, 전화번호 변경
			updateUser
				.append("		update	user_info ")
				.append("		set 	name=?, email=?, tel=?, address=? ")
				.append("		where	user_code=? ");
			//@formatter:on

			pstmtUser = con.prepareStatement(updateUser.toString());

			// 4. 바인드변수에 값 설정
			pstmtUser.setString(1, uDTO.getName());
			pstmtUser.setString(2, uDTO.getEmail());
			pstmtUser.setString(3, uDTO.getTel());
			pstmtUser.setString(4, uDTO.getAddress());
			pstmtUser.setInt(5, uDTO.getUser_code());

			// 5. 쿼리문 수행 후 결과 얻기
			rowCntUser = pstmtUser.executeUpdate();// 변경한 행의 수가 리턴

			// -----------------card_info 테이블 수정--------------------
			StringBuilder updateCard = new StringBuilder();
			updateCard.append("update card_info ").append("   set credit_card = ? ").append(" where user_code = ? ");

			pstmtCard = con.prepareStatement(updateCard.toString());
			pstmtCard.setString(1, uDTO.getCard_num());
			pstmtCard.setInt(2, uDTO.getUser_code());

			rowCntCard = pstmtCard.executeUpdate();

			con.commit(); // 모든 UPDATE 성공 시 커밋

			// 리턴된 행 수 합이 2면 성공.
			// 하나라도 실패 시 롤백 & 어디가 실패했는데 에러 throw
			if (rowCntUser == 1 && rowCntCard == 1) {
				con.commit();
			} // 성공
			else if (rowCntUser == 0 && rowCntCard == 1) {
				con.rollback();//
				throw new SQLException("UserInfo 수정에 실패했습니다.");
			} else if (rowCntUser == 1 && rowCntCard == 0) {
				con.rollback();//
				throw new SQLException("CardInfo 수정에 실패했습니다.");
			} else {
				con.rollback();// 하나라도 실패 시 롤백
				throw new SQLException("UserInfo,CardInfo 수정에 실패했습니다.");
			} // 실패
		} catch (SQLException e) {
			if (con != null)
				con.rollback(); // 하나라도 실패 시 롤백
			throw e;

		} finally {
			// 5. 연결 끊기
			gc.dbClose(con, pstmtUser, null);
			gc.dbClose(null, pstmtCard, null);
		} // end finally

		return rowCntUser + rowCntCard;
	}// updateUser

	/**
	 * 비번만 업데이트 하는 메서드.<br>
	 * 나중에 Dynamic SQL로 바꿔보자.
	 * 
	 * @param user_code
	 * @param newPass
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public int updatePassword(int user_code, String newPass) throws SQLException, IOException {
		int flag = 0;

		GetConnection gc = GetConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 1.드라이버 로딩+2.커넥션 얻기
			con = gc.getConn();
			con.setAutoCommit(false); // 트랜잭션 시작, 오토커밋 해제

			// 3. 쿼리문 생성 객체 얻기
			String sql = "update user_info set pass = ? where user_code = ?";
			pstmt = con.prepareStatement(sql);

			// 4. 바인드변수에 값 설정
			pstmt.setString(1, newPass);
			pstmt.setInt(2, user_code);

			// 5. 쿼리문 수행 후 결과 얻기
			flag = pstmt.executeUpdate();// 변경한 행의 수가 리턴

		} finally {
			// 5. 연결 끊기
			gc.dbClose(con, pstmt, null);
		} // end finally

		return flag;
	}// updateUser

//	public List<UserDTO> selectAllUser() {
//		List<UserDTO> list = new ArrayList<UserDTO>();
//
//		return list;
//	}// selectAllUser

	/**
	 * 테스트 중인 동적 쿼리문
	 * 
	 * @param uDTO
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public int updateDynamic(UserDTO uDTO) throws SQLException, IOException {
		int flag = 0;

		List<Object> bindParam = new ArrayList<Object>();

		GetConnection gc = GetConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 1.드라이버 로딩+2.커넥션 얻기
			con = gc.getConn();

			// 3. 쿼리문 생성 객체 얻기

			// 이걸 StringBuilder로 하기
			StringBuilder updateSQL = new StringBuilder();

			// --------------------user_info 테이블 수정--------------------
			//@formatter:off		
			//회원번호를 사용하여 나이, 전화번호 변경
			updateSQL
				.append("		update	user_info set ")
				.append("		set 	")
				.append("		where	user_code=? ");
			//@formatter:on

			// Dynamic SQL, uDTO에서 null이 아닌 파트만 update 한다.
			if (uDTO.getName() != null) {
				updateSQL.append(" name=?, ");
				bindParam.add(uDTO.getName());
			} // end if
			if (uDTO.getPass() != null) {
				updateSQL.append(" pass=?, ");
				bindParam.add(uDTO.getPass());
			} // end if
			if (uDTO.getEmail() != null) {
				updateSQL.append(" email=?, ");
				bindParam.add(uDTO.getEmail());
			} // end if
			if (uDTO.getTel() != null) {
				updateSQL.append(" tel=?, ");
				bindParam.add(uDTO.getTel());
			} // end if
			if (uDTO.getAddress() != null) {
				updateSQL.append(" address=?, ");
				bindParam.add(uDTO.getAddress());
			} // end if
			if (uDTO.getStatus_activate() != null) {
				updateSQL.append(" status_activate=?, ");
				bindParam.add(uDTO.getStatus_activate());
			} // end if

			pstmt = con.prepareStatement(updateSQL.toString());

			// 4. 바인드변수에 값 설정
			pstmt.setString(1, uDTO.getName());
			pstmt.setString(2, uDTO.getEmail());
			pstmt.setString(3, uDTO.getTel());
			pstmt.setString(4, uDTO.getAddress());
			pstmt.setInt(5, uDTO.getUser_code());

			System.out.println(uDTO);
			// 5. 쿼리문 수행 후 결과 얻기
			flag += pstmt.executeUpdate();// 변경한 행의 수가 리턴

		} catch (SQLException e) {
			if (con != null)
				con.rollback(); // 하나라도 실패 시 롤백
			throw e;
		} finally {
			// 5. 연결 끊기
			gc.dbClose(con, pstmt, null);
		} // end finally

		return flag;
	}// updateUser

//	public int deleteUser(int user_code) {
//		int flag = 0;
//
//		return flag;
//	}// deleteUser

}// class
