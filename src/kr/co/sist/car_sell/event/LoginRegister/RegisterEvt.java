package kr.co.sist.car_sell.event.LoginRegister; // 패키지명 확인

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// ▼▼▼ DB 연동 및 Service 사용을 위한 import ▼▼▼
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.regex.Pattern;
import java.awt.event.FocusAdapter; // FocusListener 대신 FocusAdapter 사용
import java.awt.event.FocusEvent; // FocusEvent 임포트 추가

import kr.co.sist.car_sell.design.LoginRegister.RegisterDesign; // Design 임포트
import kr.co.sist.car_sell.dto.UserDTO;
import kr.co.sist.car_sell.function.PasswordValidator;
import kr.co.sist.car_sell.service.UserService;

public class RegisterEvt implements ActionListener {

	private RegisterDesign rd;
	private UserService us; // 추가분☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
	
	
	public RegisterEvt(RegisterDesign rd) {
		this.rd = rd;
		rd.getJbtnSubmit().addActionListener(this);
		this.us = new UserService();// 추가분)UserService 객체 생성 후 저장.☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rd.getJbtnSubmit()) {
			performRegister(); // 회원가입 로직 호출
		}
	}

	/**
	 * 회원가입 버튼 클릭 시 실행될 메소드 (DB 연동)
	 */
	private void performRegister() {

		
		// --- 컴포넌트 및 값 가져오기 ---
		JTextField jtfId = rd.getJtfId();
		JTextField jtfPass = rd.getJtfPass(); // Design 파일 확인 필요 (JTextField 인지 JPasswordField 인지)
		JPasswordField jtfPassCheck = rd.getJtfPassCheck();
		JTextField jtfName = rd.getJtfName();
		JTextField jtfEmail = rd.getJtfEmail();
		JTextField jtfTel = rd.getJtfTel();
		JTextField jtfCardNum = rd.getJtfCardNum();
		JTextField jtfAddr = rd.getJtfAddr();

		String id = jtfId.getText().trim();
		String pass = jtfPass.getText().trim(); // JTextField 값
		char[] passCheck = jtfPassCheck.getPassword();
		String name = jtfName.getText().trim();
		String email = jtfEmail.getText().trim();
		String tel = jtfTel.getText().trim();
		String cardNum = jtfCardNum.getText().trim();
		String addr = jtfAddr.getText().trim();

		// --- 유효성 검사 ---
		if (id.isEmpty()) {
			showError("아이디를 입력하세요", jtfId);
			return;
		}
		if (pass.isEmpty()) {
			showError("패스워드를 입력하세요", jtfPass);
			return;
		}
		
		//☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆추가분☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
		//비번 형식 체크.
		if(PasswordValidator.isValid(pass)) {
			showError("영문자, 숫자, 특수문자를 포함한 8자 이상으로 만들어주세요.", jtfPass);
		}
		
		if (passCheck.length == 0) {
			showError("패스워드 확인란을 입력하세요", jtfPassCheck);
			return;
		}
		String passCheckStr = new String(passCheck);
		if (!pass.equals(passCheckStr)) {
			showError("패스워드가 일치하지 않습니다", jtfPassCheck);
			return;
		}
		if (name.isEmpty()) {
			showError("이름을 입력하세요", jtfName);
			return;
		}
		if (email.isEmpty()) {
			showError("이메일을 입력하세요", jtfEmail);
			return;
		}
		// TODO: 이메일, 전화번호, 카드번호 형식 유효성 검사 추가 (정규 표현식 등 활용)
		
		
		// ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆추가분☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
		//포맷 마스크에서 숫자만 추출해서 비어있는지 체크
		if (tel.replaceAll("[^0-9]", "").isEmpty()) {
			showError("전화번호를 입력하세요", jtfTel);
			return;
		}
		if (cardNum.replaceAll("[^0-9]", "").isEmpty()) {
			showError("카드번호를 입력하세요", jtfCardNum);
			return;
		}
//		if (tel.isEmpty()) {
//			showError("전화번호를 입력하세요", jtfTel);
//			return;
//		}
//		if (cardNum.isEmpty()) {
//			showError("카드번호를 입력하세요", jtfCardNum);
//			return;
//		}
		if (addr.isEmpty()) {
			showError("주소를 입력하세요", jtfAddr);
			return;
		}
		// --- 유효성 검사 끝 ---

		// --- 실제 회원가입 로직 (DB 연동) ---
		try {
			// 1. DTO(바구니)에 데이터 담기
			UserDTO uDTO = new UserDTO();
			uDTO.setId(id);
			uDTO.setPass(pass); // 비밀번호 String으로
			uDTO.setName(name);
			uDTO.setEmail(email);
			uDTO.setTel(tel);
			uDTO.setAddress(addr);
			uDTO.setCard_num(cardNum); // DTO에 카드번호 필드 포함

			// ☆☆☆☆☆☆☆☆☆☆수정파트☆☆☆☆☆☆☆☆☆☆☆)
			
			// 2. Service의 회원가입 메소드 호출 (DAO 트랜잭션 포함)
//            UserServiceNjw userService = UserServiceNjw.getInstance();
//            userService.registerUser(uDTO);
			us.registerUser(uDTO);

			// 3. 성공 처리
			JOptionPane.showMessageDialog(rd, name + " 님, 회원가입이 완료되었습니다.", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
			rd.dispose(); // 회원가입 창 닫기

		} catch (SQLException se) {
			// DAO에서 던진 예외 처리
			// ORA-00001: unique constraint (PK/UK 위반) - ID 중복
			// (정확한 제약조건 이름은 DB 확인 필요, 예: PK_USER_INFO)
			if (se.getErrorCode() == 1 && se.getMessage().toUpperCase().contains("PK_USER_INFO")) {
				showError("이미 사용 중인 아이디입니다.", jtfId);
			}
			// 그 외 DB 오류 (예: 테이블 없음, 컬럼 불일치 등)
			else {
				JOptionPane.showMessageDialog(rd, "데이터베이스 오류가 발생했습니다.\n" + se.getMessage(), "DB 오류",
						JOptionPane.ERROR_MESSAGE);
				se.printStackTrace(); // 개발 중 확인을 위해 콘솔에 스택 트레이스 출력
			}
		} catch (IOException ie) {
			// GetConnection에서 발생한 예외 처리
			JOptionPane.showMessageDialog(rd, "DB 설정 파일(properties)을 찾을 수 없습니다.", "파일 오류", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			// 그 외 예상치 못한 예외 처리
			JOptionPane.showMessageDialog(rd, "알 수 없는 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	/**
	 * 에러 메시지 다이얼로그 및 포커스 헬퍼 메소드
	 */
	private void showError(String message, JComponent field) {
		JOptionPane.showMessageDialog(rd, message, "입력 오류", JOptionPane.WARNING_MESSAGE);
		field.requestFocus();
	}
}