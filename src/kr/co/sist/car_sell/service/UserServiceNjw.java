//package kr.co.sist.car_sell.service;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List; // List 임포트 확인
//
//// ▼▼▼ 모든 필요한 DAO/DTO 임포트 확인 (패키지 경로 주의!) ▼▼▼
//import kr.co.sist.car_sell.dao.car.CarDAO;       // car 패키지
//import kr.co.sist.car_sell.dao.image.ImageDAO;     // image 패키지
//import kr.co.sist.car_sell.dao.order.OrderDAO;     // order 패키지
//import kr.co.sist.car_sell.dao.UserDAOnjw;
//import kr.co.sist.car_sell.dto.car.CarDTO;       // car 패키지
//import kr.co.sist.car_sell.dto.image.ImageDTO;     // image 패키지
//import kr.co.sist.car_sell.dto.order.OrderDTO;     // order 패키지
//import kr.co.sist.car_sell.dto.UserDTOnjw;
//// ▲▲▲
//
//public class UserServiceNjw {
//    private static UserServiceNjw userService;
//
//    private UserServiceNjw() { }
//
//    public static UserServiceNjw getInstance() {
//        if(userService == null) { userService = new UserServiceNjw(); }
//        return userService;
//    }
//
//    /** [로그인용] 사용자 로그인 로직 */
//    public UserDTOnjw loginUser(String id, String passStr) throws SQLException, IOException {
//        UserDAOnjw uDAO = UserDAOnjw.getInstance();
//        UserDTOnjw uDTO = uDAO.selectUserForLogin(id);
//
//        if (uDTO == null || !uDTO.getPass().equals(passStr)) { return null; } // ID 없거나 비번 불일치
//        if (uDTO.getStatus_activate() != 'Y') { throw new SQLException("비활성화된 계정입니다."); } // 비활성 계정
//        return uDTO; // 성공
//    }
//
//    /** [회원가입용] 사용자 등록 로직 */
//    public void registerUser(UserDTOnjw uDTO) throws SQLException, IOException {
//        UserDAOnjw uDAO = UserDAOnjw.getInstance();
//        int result = uDAO.insertUser(uDTO); // DAO에서 트랜잭션 처리
//        if (result < 2) { throw new SQLException("회원가입 DB 처리 오류"); }
//    }
//    
//    /**
//     * [신규 추가] 아이디 중복 여부를 확인하는 메소드 (회원가입 전 체크용)
//     * @param id 확인할 아이디
//     * @return 중복이면 true, 아니면 false
//     */
//    public boolean isIdDuplicate(String id) throws SQLException, IOException {
//        UserDAOnjw uDAO = UserDAOnjw.getInstance();
//        // 로그인용 메소드를 재활용하여 ID 존재 여부만 확인
//        UserDTOnjw existingUser = uDAO.selectUserForLogin(id);
//        return existingUser != null; // 조회된 사용자가 있으면 중복(true)
//    }
//
// // ▼▼▼ 주석 해제 및 클래스/패키지명 확인 ▼▼▼
//    /** [구매페이지용] 차량 상세 정보 조회 */
//    public CarDTO getCarDetails(int productCode) throws SQLException, IOException {
//        return CarDAO.getInstance().selectCarByCode(productCode); // CarDAO 사용
//    }
//
//    /** [구매페이지용] 차량 이미지 경로 목록 조회 */
//    public List<String> getCarImagePaths(int productCode) throws SQLException, IOException { // 반환 타입 List<String>
//        return ImageDAO.getInstance().selectCarImagePaths(productCode); // ImageDAO, 새 메소드 사용
//    }
//
//    /** [구매페이지용] 주문하기 로직 (트랜잭션) */
//    public void placeOrder(OrderDTO oDTO) throws SQLException, IOException {
//        OrderDAO oDAO = OrderDAO.getInstance(); // OrderDAO 사용
//        int result = oDAO.insertOrder(oDTO);
//        if (result < 2) { // 성공 시 2 가정
//             throw new SQLException("주문 DB 처리 오류");
//        }
//    }
//    // (마이페이지 관련 Service 메소드들...)
//}