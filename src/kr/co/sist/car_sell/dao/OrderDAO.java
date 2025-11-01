package kr.co.sist.car_sell.dao; // 패키지 경로 확인

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.car_sell.dto.OrderDTO;

/**
 * ORDER_HISTORY 테이블 관련 DAO (주문 처리)
 */
public class OrderDAO {
    private static OrderDAO orderDAO;

    private OrderDAO() { }

    public static OrderDAO getInstance() {
        if (orderDAO == null) { orderDAO = new OrderDAO(); }
        return orderDAO;
    }

    /**
     * [구매페이지용] 주문(Insert) + 차량상태(Update) 트랜잭션
     * @param oDTO 주문 정보 (user_code, product_code)
     * @return 성공 시 2 (Order 1 + Car_Update 1), 실패 시 0 또는 예외
     */
    public int insertOrder(OrderDTO oDTO) throws SQLException, IOException {
        int rowCntOrder = 0;
        int rowCntCarStatus = 0;
        Connection con = null;
        PreparedStatement pstmtOrder = null;
        PreparedStatement pstmtCar = null;
        // PreparedStatement pstmtSeq = null; // MAX+1 방식용
        // ResultSet rsSeq = null; // MAX+1 방식용
        GetConnection gc = GetConnection.getInstance();

        try {
            con = gc.getConn();
            con.setAutoCommit(false); // ★ 트랜잭션 시작

            // 1. 다음 주문번호(PAYMENT_CODE) 가져오기 (MAX+1 방식)
            int nextPaymentCode = 0;
            String seqSql = "SELECT NVL(MAX(PAYMENT_CODE), 0) + 1 FROM ORDER_HISTORY";
            try (PreparedStatement psSeq = con.prepareStatement(seqSql);
                 ResultSet rs = psSeq.executeQuery()) {
                if(rs.next()) { nextPaymentCode = rs.getInt(1); }
                else { con.rollback(); throw new SQLException("다음 주문번호 생성 실패"); }
            }

            // 2. 주문 내역(ORDER_HISTORY) 삽입
            String insertOrderSql =
                "INSERT INTO ORDER_HISTORY (PAYMENT_CODE, ORDER_DATE, DELIVERY_STATE, PRODUCT_CODE, USER_CODE) " +
                "VALUES (?, SYSDATE, '탁송 준비', ?, ?)"; // 초기 상태 '배송준비중'

            pstmtOrder = con.prepareStatement(insertOrderSql);
            pstmtOrder.setInt(1, nextPaymentCode);
            pstmtOrder.setInt(2, oDTO.getProduct_code());
            pstmtOrder.setInt(3, oDTO.getUser_code());
            rowCntOrder = pstmtOrder.executeUpdate();

            // 3. 차량 상태(CAR_INFO) '판매완료'로 변경
            String updateCarSql = "UPDATE CAR_INFO SET STATUS_SOLD = '판매완료' WHERE PRODUCT_CODE = ?";
            pstmtCar = con.prepareStatement(updateCarSql);
            pstmtCar.setInt(1, oDTO.getProduct_code());
            rowCntCarStatus = pstmtCar.executeUpdate();

            // 4. 두 작업 모두 성공했는지 확인
            if (rowCntOrder == 1 && rowCntCarStatus == 1) {
                con.commit(); // ★ 트랜잭션 성공
            } else {
                con.rollback(); // ★ 하나라도 실패하면 롤백
                // 실패 원인 파악을 위해 상세 정보 포함
                throw new SQLException("주문 또는 차량 상태 변경 실패 (Order:" + rowCntOrder + ", Car:" + rowCntCarStatus + ")");
            }
        } catch (SQLException e) {
            if (con != null) con.rollback(); // ★ 예외 발생 시 롤백
            throw e; // 예외 다시 던지기
        } finally {
            // 리소스 정리 (PreparedStatement 먼저)
            if (pstmtOrder != null) pstmtOrder.close();
            if (pstmtCar != null) pstmtCar.close();
            gc.dbClose(con, null, null); // Connection 닫기
        }
        return rowCntOrder + rowCntCarStatus; // 성공 시 2 반환
    }

    // (필요 시 주문 목록 조회(selectOrdersByUser), 주문 상세 조회(selectOrderByCode) 등 추가)
}