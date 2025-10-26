package kr.co.sist.car_sell.event.LoginRegister; 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.car_sell.design.LoginRegister.FirstSelectDesign;
import kr.co.sist.car_sell.design.LoginRegister.LoginDesign;

public class FirstSelectEvt implements ActionListener {

    private FirstSelectDesign fsd;
    
    //1. LoginDesign을 멤버 변수로 선언
    private LoginDesign ld; 

    public FirstSelectEvt(FirstSelectDesign fsd) {
        this.fsd = fsd;
        fsd.getJbtnUser().addActionListener(this);
        fsd.getJbtnAdmin().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == fsd.getJbtnUser()) {
            openLoginDialog("u"); 
        } else if (source == fsd.getJbtnAdmin()) {
            openLoginDialog("a"); 
        }
    }

    /**
     * 로그인 다이얼로그를 생성하고, LoginEvt를 붙여서 보여주는 메소드
     */
    private void openLoginDialog(String type) {
        
        // 2. "이미 ld가 생성되어 떠 있다면" 실행하지 않음
        // (ld가 null이 아닐 때만)
        if (ld != null) {
            ld.toFront(); // 이미 떠 있다면 창을 맨 앞으로 가져옴
            return;
        }
        // 
            
        // 3. (ld가 null일 때) 새로 생성
        ld = new LoginDesign(fsd, type); // 멤버 변수 ld에 할당
        new LoginEvt(ld, fsd); 
        
        // 4. (중요) 창이 닫힐 때 ld를 null로 되돌리는 리스너 추가
        ld.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // 다이얼로그가 완전히 닫히면, 멤버변수 ld를 null로 설정
                ld = null;
            }
        });
        //
        
        ld.setVisible(true);
    }
}