package prj_1.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays; // char[] 비교를 위해 필요

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import prj_1.design.LoginDesign;
import prj_1.design.RegisterDesign;
import prj_1.design.UserOrderDesign;

public class LoginEvt implements ActionListener {

    private LoginDesign ld; // 현재 창(로그인)
    private JFrame owner;   // 메인 창 (새 다이얼로그를 띄울 때 필요)

    public LoginEvt(LoginDesign ld, JFrame owner) {
        this.ld = ld;
        this.owner = owner;
        
        ld.getJbtnLogin().addActionListener(this);
        if (ld.getJbtnRegister() != null) { 
            ld.getJbtnRegister().addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == ld.getJbtnLogin()) {
            
            // --- 유효성 검사 (ID, PW 입력 여부) ---
            String id = ld.getJtfId().getText().trim();
            char[] pass = ld.getJpfPass().getPassword(); 
            
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

            // 계정 정보 확인 로직 시작

            // 입력된 비밀번호를 비교하기 위해 String으로 변환
            String passStr = new String(pass);

            // 1. 관리자 로그인 처리
            if ("a".equals(ld.getLoginType())) {
                if ("min".equals(id) && "test1234".equals(passStr)) {
                    // 관리자 로그인 성공
                    JOptionPane.showMessageDialog(ld, "관리자로 로그인 되었습니다.");
                    ld.dispose(); // 로그인 창 닫기
                    // TODO: 관리자 메인 창을 여는 로직 추가
                } else {
                    // 관리자 로그인 실패
                    JOptionPane.showMessageDialog(ld, "아이디 또는 비밀번호를 확인해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                    ld.getJpfPass().setText(""); // 비밀번호 필드 초기화
                }
                return; // 관리자 로직이 끝났으므로 여기서 종료
            }

            // 2. 사용자 로그인 처리
            if ("u".equals(ld.getLoginType())) {
                if ("test".equals(id) && "1234".equals(passStr)) {
                	JOptionPane.showMessageDialog(ld, id + " 님, 환영합니다!");
                    // 사용자 로그인 성공
                    ld.dispose(); // 로그인 창 닫기
                    openUserOrderDialog(); // 주문 창 열기
                } else {
                    // 사용자 로그인 실패
                    JOptionPane.showMessageDialog(ld, "아이디 또는 비밀번호를 확인해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                    ld.getJpfPass().setText(""); // 비밀번호 필드 초기화
                }
            }
            // 계정 정보 확인 로직 끝
            
        } else if (source == ld.getJbtnRegister()) {
            // 회원가입 창 열기 (로그인 창 닫지 않음)
            openRegisterDialog();
        }
    }

    private void openRegisterDialog() {
        RegisterDesign rd = new RegisterDesign(owner);
        new RegisterEvt(rd);
        rd.setVisible(true);
    }

    private void openUserOrderDialog() {
        UserOrderDesign uod = new UserOrderDesign(owner);
        new UserOrderEvt(uod);
        uod.setVisible(true);
    }
}