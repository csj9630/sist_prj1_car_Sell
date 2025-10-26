package kr.co.sist.car_sell.event.LoginRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.car_sell.design.LoginRegister.RegisterDesign;

public class RegisterEvt implements ActionListener {

    private RegisterDesign rd; // 현재 창(회원가입)

    public RegisterEvt(RegisterDesign rd) {
        this.rd = rd;
        rd.getJbtnSubmit().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == rd.getJbtnSubmit()) {
            
            //유효성 검사 로직
            // 1. 컴포넌트 가져오기
        	JTextField jtfId = rd.getJtfId();
            JTextField jtfPass = rd.getJtfPass(); 
            JPasswordField jtfPassCheck = rd.getJtfPassCheck();
            JTextField jtfName = rd.getJtfName();
            JTextField jtfEmail = rd.getJtfEmail();
            JTextField jtfTel = rd.getJtfTel();
            JTextField jtfCardNum = rd.getJtfCardNum();
            JTextField jtfAddr = rd.getJtfAddr();

            // 2. 값 가져오기
            String id = jtfId.getText().trim();
            String pass = jtfPass.getText().trim();
            char[] passCheck = jtfPassCheck.getPassword();
            String name = jtfName.getText().trim();
            String email = jtfEmail.getText().trim();
            String tel = jtfTel.getText().trim();
            String cardNum = jtfCardNum.getText().trim();
            String addr = jtfAddr.getText().trim();

            // 3. 유효성 검사 (순서대로)
            if (id.isEmpty()) {
                showError("아이디를 입력하세요", jtfId);
                return;
            }
            if (pass.isEmpty()) { // .length == 0 -> .isEmpty()로 변경
                showError("패스워드를 입력하세요", jtfPass);
                return;
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
            if (tel.isEmpty()) {
                showError("전화번호를 입력하세요", jtfTel);
                return;
            }
            if (cardNum.isEmpty()) {
                showError("카드번호를 입력하세요", jtfCardNum);
                return;
            }
            if (addr.isEmpty()) {
                showError("주소를 입력하세요", jtfAddr);
                return;
            }
            // 유효성 검사 끝

            // TODO: 실제 회원가입 로직 처리 (DAO 연동)
            
            System.out.println("가입완료 시도");
            
            // (임시) 가입이 성공했다고 가정하고
            JOptionPane.showMessageDialog(rd, "회원가입이 완료되었습니다.");
            rd.dispose(); // 현재 회원가입 창 닫기
        }
    }
    
    /**
     * 에러 메시지 다이얼로그를 띄우고 해당 컴포넌트에 포커스를 주는 헬퍼 메소드
     */
    private void showError(String message, JComponent field) {
        JOptionPane.showMessageDialog(rd, message, "알림", JOptionPane.WARNING_MESSAGE);
        field.requestFocus();
    }

}