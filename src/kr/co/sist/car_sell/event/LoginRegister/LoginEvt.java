package kr.co.sist.car_sell.event.LoginRegister; // 패키지명 확인

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// ▼▼▼ DB 연동 및 Service 사용을 위한 import ▼▼▼
import java.io.IOException;
import java.sql.SQLException;

// ▲▲▲
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kr.co.sist.car_sell.dao.AdminDAO;
import kr.co.sist.car_sell.design.CarList.CarListDesign;
import kr.co.sist.car_sell.design.LoginRegister.LoginDesign;
import kr.co.sist.car_sell.design.LoginRegister.RegisterDesign;
import kr.co.sist.car_sell.design.MgrMenu.MgrMenuDesign;
import kr.co.sist.car_sell.dto.AdminDTO;
import kr.co.sist.car_sell.dto.UserDTO;
import kr.co.sist.car_sell.service.AdminService;
import kr.co.sist.car_sell.service.UserService;

public class LoginEvt implements ActionListener {

	private LoginDesign ld;
	private JFrame owner; // FirstSelectDesign (부모 프레임)
	private UserService us; // ☆☆☆☆추가분
	private AdminService as; //☆☆☆☆추가분)

	public LoginEvt(LoginDesign ld, JFrame owner) {
		this.ld = ld;
		this.owner = owner;
		this.us = new UserService();// ☆☆☆☆추가분)UserService 객체 생성 후 저장.
//		this.as = new AdminService();// ☆☆☆☆추가분)AdminService 객체 생성 후 저장.

		ld.getJbtnLogin().addActionListener(this);
		if (ld.getJbtnRegister() != null) {
			ld.getJbtnRegister().addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ld.getJbtnLogin()) {
			performLogin(); // 로그인 로직 호출
		} else if (e.getSource() == ld.getJbtnRegister()) {
			openRegisterDialog(); // 회원가입 창 열기
		}
	}

	/**
	 * 로그인 버튼 클릭 시 실행될 메소드 (DB 연동)
	 */
	private void performLogin() {
		String id = ld.getJtfId().getText().trim();
		char[] pass = ld.getJpfPass().getPassword();

		// --- 유효성 검사 ---
		if (id.isEmpty()) {
			JOptionPane.showMessageDialog(ld, "아이디를 입력하세요", "알림", JOptionPane.WARNING_MESSAGE);
			ld.getJtfId().requestFocus();
			return;
		}
		if (pass.length == 0) {
			JOptionPane.showMessageDialog(ld, "비밀번호를 입력하세요", "알림", JOptionPane.WARNING_MESSAGE);
			ld.getJpfPass().requestFocus();
			return;
		}
		// --- 유효성 검사 끝 ---

		String passStr = new String(pass); // char[] -> String 변환

		// --- 관리자 로그인 시도 ---
		if ("a".equals(ld.getLoginType())) {
			try {
				// AdminService 호출
				AdminService adminService = AdminService.getInstance();
				AdminDTO aDTO = adminService.loginAdmin(id, passStr);
//				AdminDTO aDTO = as.loginAdmin(id, passStr);

				if (aDTO != null) { // 로그인 성공
					JOptionPane.showMessageDialog(ld, "관리자로 로그인 되었습니다.");
					ld.dispose(); // 로그인 창 닫기
					// TODO: 관리자 메인 창 열기 (예: new AdminDesign() 또는 new MgrMenuDesign())

					new MgrMenuDesign();

				} else { // 로그인 실패
					JOptionPane.showMessageDialog(ld, "아이디 또는 비밀번호를 확인해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
					ld.getJpfPass().setText(""); // 비밀번호 필드 비우기
				}
			} catch (SQLException | IOException ex) {
				// DB 접속 오류 등 예외 처리
				handleLoginException(ex);
			}
		}
		// --- 사용자 로그인 시도 ---
		else if ("u".equals(ld.getLoginType())) {
			try {
				// UserServiceNjw 호출
//				UserService userService = UserService.getInstance();
//				UserDTO uDTO = userService.loginUser(id, passStr);
				//☆☆☆☆☆수정파트☆☆☆☆☆☆☆☆
				UserDTO uDTO = us.loginUser(id, passStr);
				
				if (uDTO != null) { // 로그인 성공
					JOptionPane.showMessageDialog(ld, uDTO.getName() + " 님, 환영합니다!");
					ld.dispose(); // 로그인 창 닫기

					// TODO: 사용자 메인 창(차량 목록 화면) 열기
					// 예시: new CarListDesign(); // 로그인 후 보여줄 메인 화면
					// (CarListDesign 생성자가 UserDTOnjw를 받도록 수정 필요)

			//☆★☆★☆★☆★☆★☆★페이지호출☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
					new CarListDesign(); // 차량 리스트 디자인 화면 실행
//					new CarListDesign(uDTO.getUser_code()); // 차량 리스트 디자인 화면 실행
					//CarListDesign 매개변수 수정 후 바꿀 내용.
			//☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★

				} else { // 로그인 실패
					JOptionPane.showMessageDialog(ld, "아이디 또는 비밀번호를 확인해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
					ld.getJpfPass().setText(""); // 비밀번호 필드 비우기
				}
			} catch (SQLException se) { // 비활성 계정 예외 포함
				handleLoginException(se);
			} catch (IOException ie) {
				handleLoginException(ie);
			}
		}
	}

	/**
	 * 회원가입 창 열기
	 */
	private void openRegisterDialog() {
		RegisterDesign rd = new RegisterDesign(owner);
		new RegisterEvt(rd); // 회원가입 이벤트 연결
		rd.setVisible(true);
	}

	/**
	 * 로그인 시 발생하는 예외 처리 헬퍼 메소드
	 */
	private void handleLoginException(Exception ex) {
		// 비활성 계정 예외 메시지 보여주기
		if (ex instanceof SQLException && ex.getMessage().contains("비활성화")) {
			JOptionPane.showMessageDialog(ld, ex.getMessage(), "로그인 실패", JOptionPane.WARNING_MESSAGE);
		}
		// 일반 DB 오류
		else if (ex instanceof SQLException) {
			JOptionPane.showMessageDialog(ld, "데이터베이스 오류가 발생했습니다.", "DB 오류", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace(); // 개발 중 확인을 위해 콘솔에 스택 트레이스 출력
		}
		// 설정 파일 오류
		else if (ex instanceof IOException) {
			JOptionPane.showMessageDialog(ld, "DB 설정 파일(properties)을 찾을 수 없습니다.", "파일 오류", JOptionPane.ERROR_MESSAGE);
		}
		// 그 외 알 수 없는 오류
		else {
			JOptionPane.showMessageDialog(ld, "알 수 없는 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
		// 오류 발생 시 비밀번호 필드 초기화
		ld.getJpfPass().setText("");
	}
}