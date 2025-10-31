package kr.co.sist.car_sell.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import kr.co.sist.car_sell.dao.ImageDAO;
import kr.co.sist.car_sell.design.UserOrderDesign;
import kr.co.sist.car_sell.dto.CarDTO;
import kr.co.sist.car_sell.dto.UserDTO;
import kr.co.sist.car_sell.service.UserService;

/**
 * UserOrderDesign의 이벤트 처리와 DB값을 받아온다.<br>
 * design의 멤버 변수들 get메서드로 불러온다.<br>
 * ㄴ 이걸 생성자 매개변수로 하려고 했다가 종류가 3개는 되어서 get메서드로 전환.<br>
 */
public class UserOrderEvt implements ActionListener {

	public UserDTO getuDTO() {
		return uDTO;
	}

	public CarDTO getcDTO() {
		return cDTO;
	}

	public List<String> getImagePathList() {
		return imagePathList;
	}
	
	
	private UserOrderDesign uod; // 현재 창(주문)

	// --- DB 데이터를 저장할 멤버 변수 ---
	private UserDTO uDTO; // 로그인한 사용자 정보
	private CarDTO cDTO; // 조회된 차량 정보
	private List<String> imagePathList;//조회된 이미지경로들
	
	private int productCode; // 구매할 차량 코드
	private int user_code;
	private UserService userService;


	public UserOrderEvt(UserOrderDesign uod, int user_code, int productCode) {
		this.uod = uod;
		this.user_code=user_code;
		this.productCode=productCode;
		this.userService = new UserService();
		loadDatas(); //DB 데이터 로드

	}// UserOrderEvt

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == uod.getJbtnOrder()) {// 1. "구매하시겠습니까?" (예/아니오) 다이얼로그를 띄웁니다.
			int result = JOptionPane.showConfirmDialog(uod, // 부모 창
					"구매하시겠습니까?", // 메시지
					"구매 확인", // 타이틀
					JOptionPane.YES_NO_OPTION); // 버튼 (예/아니오)

			// 2. 사용자가 "예(Yes)" 버튼을 눌렀는지 확인합니다.
			if (result == JOptionPane.YES_OPTION) {

				// --- "예"를 눌렀을 때만 기존 로직 실행 ---
				// TODO: 실제 주문 로직 처리 (DAO 연동)

				System.out.println("주문하기 시도");

				// (임시) 주문이 성공했다고 가정하고 성공 메시지 띄우기
				JOptionPane.showMessageDialog(uod, "구매가 완료되었습니다.");
				uod.dispose(); // 현재 주문 창 닫기

			} // end if
		} // end if
	}// actionPerformed
	
	

	/**
	 * DB에서 데이터를 받아서 userDTO, carDTO와 List 차량이미지경로를 받아온다.
	 * 
	 * @return
	 */
	public void loadDatas() {
		// Service & DAO 인스턴스
		ImageDAO imgDAO = ImageDAO.getInstance(); // ImageDAO 사용
		// 1. 차량 상세 정보 조회
		try {
			uDTO = userService.searchOneUser(user_code);
			if (this.uDTO == null) { // 차량 정보 없으면 예외 발생
				throw new SQLException("사용자 정보를 찾을 수 없습니다.");
			} // end if
			this.cDTO = userService.getCarDetails(productCode);
			if (this.cDTO == null) { // 차량 정보 없으면 예외 발생
				throw new SQLException("차량 정보(productCode: " + productCode + ")를 찾을 수 없습니다.");
			} // end if

			// 2. 차량 이미지 경로 목록 조회 (String 리스트)
			this.imagePathList = userService.getCarImagePaths(productCode); // Service의 메소드 호출

		} catch (SQLException | IOException ex) {// DB 관련 예외
			// TODO Auto-generated catch block
			handleException("데이터를 불러오는 중 오류가 발생했습니다.", ex);
			uod.dispose();
			return;
		} catch (Exception ex) { // 그 외 예외 (이미지 경로 오류 등)
			handleException("화면 구성 중 오류가 발생했습니다.", ex);
			uod.dispose();
			return;
		} // end catch


	}// dataLoad
	/**
	 * 예외 처리 및 메시지 표시 헬퍼 메소드
	 */
	private void handleException(String message, Exception ex) {
		ex.printStackTrace(); // 콘솔에 상세 오류 출력
		JOptionPane.showMessageDialog(uod, message + "\n" + ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
	}//handleException
	
	

}// class