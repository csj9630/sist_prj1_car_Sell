package kr.co.sist.car_sell.dao.car; // 패키지 경로 확인

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.util.ArrayList; // ImageDAO 분리 시 불필요
// import java.util.List; // ImageDAO 분리 시 불필요
import kr.co.sist.car_sell.dao.GetConnection; // GetConnection 임포트
import kr.co.sist.car_sell.dto.car.CarDTO; // DTO 임포트
// import kr.co.sist.car_sell.dto.image.ImageDTO; // ImageDAO 분리 시 불필요

/**
 * CAR_INFO 테이블 관련 DAO
 */
public class CarDAO {
    private static CarDAO carDAO;

    private CarDAO() { }

    public static CarDAO getInstance() {
        if (carDAO == null) { carDAO = new CarDAO(); }
        return carDAO;
    }

    /** [구매페이지/차량상세용] 차량 코드로 상세 정보 조회 */
    public CarDTO selectCarByCode(int productCode) throws SQLException, IOException {
        CarDTO car = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        GetConnection gc = GetConnection.getInstance();

        String sql = "SELECT * FROM CAR_INFO WHERE PRODUCT_CODE = ?";

        try {
            con = gc.getConn();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, productCode);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                car = new CarDTO();
                car.setProduct_code(rs.getInt("PRODUCT_CODE"));
                car.setProduct_name(rs.getString("PRODUCT_NAME"));
                car.setPrice(rs.getInt("PRICE"));
                car.setCar_year(rs.getDate("CAR_YEAR"));
                car.setCc(rs.getInt("CC"));
                car.setDistance(rs.getInt("DISTANCE"));
                car.setRegistration_number(rs.getString("REGISTRATION_NUMBER"));
                car.setStatus_sold(rs.getString("STATUS_SOLD"));
                car.setCar_name(rs.getString("CAR_NAME"));
                car.setOil(rs.getString("OIL"));
                car.setBrand_name(rs.getString("BRAND_NAME"));
            }
        } finally {
            gc.dbClose(con, pstmt, rs);
        }
        return car;
    }

    // (참고: 이미지 조회 메소드(selectCarImages)는 아래 ImageDAO로 분리했습니다.)
    // (필요 시 차량 목록 조회(selectAllCars), 차량 상태 변경(updateCarStatus) 등 추가)

}