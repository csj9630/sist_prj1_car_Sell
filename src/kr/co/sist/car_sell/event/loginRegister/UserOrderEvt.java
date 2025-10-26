package kr.co.sist.car_sell.event.loginRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import prj_1.design.UserOrderDesign;

public class UserOrderEvt implements ActionListener {

	private UserOrderDesign uod; // 현재 창(주문)

	public UserOrderEvt(UserOrderDesign uod) {
		this.uod = uod;

		// 버튼에 리스너 등록
		uod.getJbtnOrder().addActionListener(this);
		// (참고: 이미지 넘김 버튼(jbtnPrev, jbtnNext) 이벤트는
		// UserOrderDesign 생성자 내부에서 이미 자체적으로 처리되었습니다.)
	}

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

			}
		}
	}
}