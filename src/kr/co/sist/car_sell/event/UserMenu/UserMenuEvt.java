package kr.co.sist.car_sell.event.UserMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.car_sell.design.UserMenu.ModifyUserInfoDesign;
import kr.co.sist.car_sell.design.UserMenu.ModifyUserPasswordDesign;
import kr.co.sist.car_sell.design.UserMenu.UserMenuDesign;

/**
 * 작업자 : 최승준<br>
 * '내 정보 메뉴' 화면의 이벤트 처리 클래스<br>
 */
public class UserMenuEvt extends WindowAdapter implements ActionListener {
	private UserMenuDesign umd;

	public UserMenuEvt(UserMenuDesign umd) {
		this.umd = umd;
	}// UserMenuEvt

	/**
	 * 각 버튼별 액션 처리.<br>
	 * 버튼 별로 JDialog를 상속한 각 기능 다이얼로그 객체를 생성하여 연다.<br>
	 * 객체 이름까진 현 시점에선 필요없으나, 혹시 모르니 추가.<br>
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == umd.getjbtnModifyInfo()) {
			ModifyUserInfoDesign muid = new ModifyUserInfoDesign(umd, true, umd.getUser_code());
		} // end if
		if (ae.getSource() == umd.getjbtnModifyPw()) {
			ModifyUserPasswordDesign  mupd = new ModifyUserPasswordDesign(umd, true,umd.getUser_code());
		} // end if
		if (ae.getSource() == umd.getjbtnOrderList()) {
			//주문 내역은 공사 중.
			//민병조 영역
			JOptionPane.showMessageDialog(umd, "주문 내역 영역");
		} // end if

	}// actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowClosing(e);
	}

}
