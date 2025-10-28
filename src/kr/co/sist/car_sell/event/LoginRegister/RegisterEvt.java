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
import kr.co.sist.car_sell.dto.UserDTOnjw; // DTO 임포트
import kr.co.sist.car_sell.service.UserServiceNjw; // Service 임포트
// ▲▲▲

public class RegisterEvt implements ActionListener {

    private RegisterDesign rd;
    private static final String PHONE_PLACEHOLDER = "010-xxxx-xxxx"; // 상수 추가
    private static final String EMAIL_PLACEHOLDER = "@를 추가하세요"; // '@를 넣으세요' 보다 구체적인 예시
    private static final String CARD_PLACEHOLDER = "xxxx-xxxx-xxxx-xxxx";
    
    // 이름: 한글 또는 영문자만
    private static final String NAME_REGEX = "^[a-zA-Z가-힣]+$";
    // 이메일: 기본적인 형식 (@ 포함, . 포함)
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    // 전화번호: xxx-xxxx-xxxx 형식 (하이픈 필수)
    private static final String PHONE_REGEX = "^\\d{3}-\\d{4}-\\d{4}$";
    // 카드번호: xxxx-xxxx-xxxx-xxxx 형식 (하이픈 필수)
    private static final String CARD_REGEX = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$";

    public RegisterEvt(RegisterDesign rd) {
        this.rd = rd;
        rd.getJbtnSubmit().addActionListener(this);
        
     // --- 플레이스홀더 설정 ---
        setPlaceholder(rd.getJtfTel(), PHONE_PLACEHOLDER);
        setPlaceholder(rd.getJtfEmail(), EMAIL_PLACEHOLDER);
        setPlaceholder(rd.getJtfCardNum(), CARD_PLACEHOLDER);
    }

    /**
     * JTextField에 플레이스홀더 기능을 추가하는 헬퍼 메소드
     * @param textField 대상 JTextField
     * @param placeholder 힌트 문자열
     */
    private void setPlaceholder(JTextField textField, String placeholder) {
        // 1. 초기 상태 설정
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        // 2. FocusListener 추가
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // 포커스 얻었을 때, 플레이스홀더면 지우고 검정색으로
                if (textField.getForeground() == Color.GRAY) { // 색상으로 구분하는 것이 더 안전
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                // 포커스 잃었을 때, 비어있으면 플레이스홀더 다시 설정
                if (textField.getText().trim().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
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
        if (id.isEmpty()) { showError("아이디를 입력하세요", jtfId); return; }
        if (pass.isEmpty()) { showError("패스워드를 입력하세요", jtfPass); return; }
        if (passCheck.length == 0) { showError("패스워드 확인란을 입력하세요", jtfPassCheck); return; }
        String passCheckStr = new String(passCheck);
        if (!pass.equals(passCheckStr)) { showError("패스워드가 일치하지 않습니다", jtfPassCheck); return; }
        if (name.isEmpty()) { showError("이름을 입력하세요", jtfName); return; }
        if (email.isEmpty()) { showError("이메일을 입력하세요", jtfEmail); return; }
        // TODO: 이메일, 전화번호, 카드번호 형식 유효성 검사 추가 (정규 표현식 등 활용)
        if (tel.isEmpty()) { showError("전화번호를 입력하세요", jtfTel); return; }
        if (cardNum.isEmpty()) { showError("카드번호를 입력하세요", jtfCardNum); return; }
        if (addr.isEmpty()) { showError("주소를 입력하세요", jtfAddr); return; }
        
     // 3-1. 아이디 중복 확인 (DB 조회 필요)
        try {
            UserServiceNjw userService = UserServiceNjw.getInstance();
            if (userService.isIdDuplicate(id)) {
                showError("이미 사용 중인 아이디입니다.", jtfId);
                return;
            }
        } catch (SQLException | IOException dbEx) {
             // 아이디 중복 확인 중 DB 오류 발생 시, 일단 진행 중단
            JOptionPane.showMessageDialog(rd, "아이디 중복 확인 중 오류 발생\n" + dbEx.getMessage(), "DB 오류", JOptionPane.ERROR_MESSAGE);
            dbEx.printStackTrace();
            return;
        }

        // 3-2. 이메일 형식 확인
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            showError("이메일을 정확히 입력해주세요. (@ 포함)", jtfEmail);
            return;
        }

        // 3-3. 전화번호 형식 확인 (xxx-xxxx-xxxx)
        if (!Pattern.matches(PHONE_REGEX, tel)) {
            showError("전화번호 형식을 확인해주세요 (010-xxxx-xxxx)", jtfTel);
            return;
        }

        // 3-4. 카드번호 형식 확인 (xxxx-xxxx-xxxx-xxxx)
        if (!Pattern.matches(CARD_REGEX, cardNum)) {
            showError("카드번호 형식을 확인해주세요 (xxxx-xxxx-xxxx-xxxx)", jtfCardNum);
            return;
        }
        
        
        // --- 유효성 검사 끝 ---

        // --- 실제 회원가입 로직 (DB 연동) ---
        try {
            // 1. DTO(바구니)에 데이터 담기
            UserDTOnjw uDTO = new UserDTOnjw();
            uDTO.setId(id);
            uDTO.setPass(pass); // 비밀번호 String으로
            uDTO.setName(name);
            uDTO.setEmail(email);
            uDTO.setTel(tel);
            uDTO.setAddress(addr);
            uDTO.setCard_num(cardNum); // DTO에 카드번호 필드 포함

            // 2. Service의 회원가입 메소드 호출 (DAO 트랜잭션 포함)
            UserServiceNjw userService = UserServiceNjw.getInstance();
            userService.registerUser(uDTO);

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
                JOptionPane.showMessageDialog(rd, "데이터베이스 오류가 발생했습니다.\n" + se.getMessage(), "DB 오류", JOptionPane.ERROR_MESSAGE);
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
        if (field != null && field.isRequestFocusEnabled()) { // 포커스 가능한 컴포넌트일 때만
             field.requestFocus();
        }
    }
}