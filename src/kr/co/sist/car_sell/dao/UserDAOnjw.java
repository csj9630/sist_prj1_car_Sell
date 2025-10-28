package kr.co.sist.car_sell.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import kr.co.sist.car_sell.dto.UserDTOnjw;

public class UserDAOnjw {
	private static UserDAOnjw pstmt_uDAO;

	private UserDAOnjw() { }

	public static UserDAOnjw getInstance() {
		if (pstmt_uDAO == null) { pstmt_uDAO = new UserDAOnjw(); }
		return pstmt_uDAO;
	}

    /** [로그인용] ID로 사용자 기본 정보 조회 (CARD 제외) */
    public UserDTOnjw selectUserForLogin(String id) throws SQLException, IOException {
        UserDTOnjw uDTO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        GetConnection gc = GetConnection.getInstance();

        try {
            con = gc.getConn();
            String selectSql = "SELECT USER_CODE, ID, PASS, NAME, STATUS_ACTIVATE FROM USER_INFO WHERE ID = ?";
            pstmt = con.prepareStatement(selectSql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                uDTO = new UserDTOnjw();
                uDTO.setUser_code(rs.getInt("USER_CODE"));
                uDTO.setId(rs.getString("ID"));
                uDTO.setPass(rs.getString("PASS"));
                uDTO.setName(rs.getString("NAME"));
                uDTO.setStatus_activate(rs.getString("STATUS_ACTIVATE"));
            }
        } finally {
            gc.dbClose(con, pstmt, rs);
        }
        return uDTO;
    }

	/** [회원가입용] USER_INFO와 CARD_INFO에 트랜잭션으로 동시 삽입 */
	public int insertUser(UserDTOnjw uDTO) throws SQLException, IOException {
		int rowCntUser = 0, rowCntCard = 0, nextUserCode = 0;
		Connection con = null;
		PreparedStatement pstmtUser = null, pstmtCard = null;
		GetConnection gc = GetConnection.getInstance();

		try {
			con = gc.getConn();
			con.setAutoCommit(false); // 트랜잭션 시작

            // 1. 시퀀스 가져오기 (try-with-resources)
            try (PreparedStatement psSeq = con.prepareStatement("SELECT SEQ_USER_INFO.NEXTVAL FROM DUAL");
                 ResultSet rsSeq = psSeq.executeQuery()) {
                if (rsSeq.next()) { nextUserCode = rsSeq.getInt(1); }
                else { con.rollback(); throw new SQLException("시퀀스 오류"); }
            }

			// 2. USER_INFO 삽입
			String insertUserSql = "INSERT INTO USER_INFO (USER_CODE, ID, PASS, NAME, EMAIL, TEL, ADDRESS, GENERATE_DATE, STATUS_ACTIVATE) VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE, 'Y')";
            pstmtUser = con.prepareStatement(insertUserSql);
            // ... (pstmtUser 값 설정: nextUserCode, uDTO.getId(), ...) ...
            pstmtUser.setInt(1, nextUserCode);
            pstmtUser.setString(2, uDTO.getId());
            pstmtUser.setString(3, uDTO.getPass());
            pstmtUser.setString(4, uDTO.getName());
            pstmtUser.setString(5, uDTO.getEmail());
            pstmtUser.setString(6, uDTO.getTel());
            pstmtUser.setString(7, uDTO.getAddress());
            rowCntUser = pstmtUser.executeUpdate();

            // 3. CARD_INFO 삽입
            String insertCardSql = "INSERT INTO CARD_INFO (USER_CODE, CREDIT_CARD, REGISTRATION_DATE) VALUES (?, ?, SYSDATE)";
            pstmtCard = con.prepareStatement(insertCardSql);
            // ... (pstmtCard 값 설정: nextUserCode, uDTO.getCard_num()) ...
            pstmtCard.setInt(1, nextUserCode);
            pstmtCard.setString(2, uDTO.getCard_num());
            rowCntCard = pstmtCard.executeUpdate();

            if (rowCntUser == 1 && rowCntCard == 1) { con.commit(); } // 성공
            else { con.rollback(); throw new SQLException("삽입 실패"); } // 실패

		} catch (SQLException e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (pstmtUser != null) pstmtUser.close();
            if (pstmtCard != null) pstmtCard.close();
			gc.dbClose(con, null, null);
		}
		return rowCntUser + rowCntCard; // 성공 시 2
	}

    // (selectOneUser, updateUser, updatePassword 등 팀원이 만든 다른 메소드들...)
	/** [마이페이지/구매용] user_code로 사용자 상세 정보 조회 (CARD 포함) */
	public UserDTOnjw selectOneUser(int user_code) throws SQLException, IOException {
		UserDTOnjw uDTO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GetConnection gc = GetConnection.getInstance();

		try {
			con = gc.getConn();
			String selectOneUser =
					" select ui.user_code,ui.id,ui.pass,ui.name,ui.email,ui.tel,ui.address,ui.generate_date,ui.status_activate,ci.credit_card "
					+ " from user_info ui LEFT JOIN card_info ci ON ui.user_code = ci.user_code "
					+ " where ui.USER_CODE=? ";

			pstmt = con.prepareStatement(selectOneUser);
			pstmt.setInt(1, user_code);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				uDTO = new UserDTOnjw();
				uDTO.setUser_code(user_code);
				uDTO.setId(rs.getString("id"));
				uDTO.setPass(rs.getString("pass"));
				uDTO.setName(rs.getString("name"));
				uDTO.setEmail(rs.getString("email"));
				uDTO.setTel(rs.getString("tel"));
				uDTO.setAddress (rs.getString("address"));
				uDTO.setGenerate_date(rs.getDate("generate_date"));
				uDTO.setCard_num(rs.getString("credit_card"));
                uDTO.setStatus_activate(rs.getString("status_activate"));
			}
		} finally {
			gc.dbClose(con, pstmt, rs);
		}
		return uDTO;
	}
}