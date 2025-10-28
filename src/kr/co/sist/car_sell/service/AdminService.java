package kr.co.sist.car_sell.service;

import java.io.IOException;
import java.sql.SQLException;
import kr.co.sist.car_sell.dao.AdminDAO;
import kr.co.sist.car_sell.dto.AdminDTO;

public class AdminService {
    private static AdminService adminService;

    private AdminService() { }

    public static AdminService getInstance() {
        if (adminService == null) { adminService = new AdminService(); }
        return adminService;
    }

    /** [로그인용] 관리자 로그인 로직 */
    public AdminDTO loginAdmin(String id, String passStr) throws SQLException, IOException {
        AdminDAO aDAO = AdminDAO.getInstance();
        AdminDTO aDTO = aDAO.selectAdminById(id);

        if (aDTO != null && aDTO.getAdminPass().equals(passStr)) {
            return aDTO; // 성공
        }
        return null; // 실패
    }
    // (다른 관리자 기능 Service 메소드들...)
}