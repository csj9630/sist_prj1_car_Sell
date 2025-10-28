package kr.co.sist.car_sell.dto;

public class AdminDTO {
    private String adminId; // ADMIN 테이블의 ADMIN_ID (PK)
    private String adminPass; // ADMIN 테이블의 ADMIN_PASS
    // (필요 시 관리자 이름, 연락처 등 다른 컬럼 추가)

    // 기본 생성자
    public AdminDTO() {
    }

    // Getters and Setters
    public String getAdminId() {
        return adminId;
    }
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    public String getAdminPass() {
        return adminPass;
    }
    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }
}
