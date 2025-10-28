package kr.co.sist.car_sell.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import kr.co.sist.car_sell.dto.AdminDTO;

public class AdminDAO {
    private static AdminDAO adminDAO;

    private AdminDAO() { }

    public static AdminDAO getInstance() {
        if (adminDAO == null) { adminDAO = new AdminDAO(); }
        return adminDAO;
    }

    /** [로그인용] 관리자 ID로 관리자 정보 조회 */
    public AdminDTO selectAdminById(String adminId) throws SQLException, IOException {
        AdminDTO aDTO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        GetConnection gc = GetConnection.getInstance();

        try {
            con = gc.getConn();
            String selectSql = "SELECT ADMIN_ID, ADMIN_PASS FROM ADMIN WHERE ADMIN_ID = ?";
            pstmt = con.prepareStatement(selectSql);
            pstmt.setString(1, adminId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                aDTO = new AdminDTO();
                aDTO.setAdminId(rs.getString("ADMIN_ID"));
                aDTO.setAdminPass(rs.getString("ADMIN_PASS"));
            }
        } finally {
            gc.dbClose(con, pstmt, rs);
        }
        return aDTO;
    }
    // (다른 관리자 기능 DAO 메소드들...)
}
