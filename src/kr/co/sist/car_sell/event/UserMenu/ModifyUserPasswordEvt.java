package kr.co.sist.car_sell.event.UserMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import kr.co.sist.car_sell.design.UserMenu.ModifyUserPasswordDesign;
import kr.co.sist.car_sell.dto.UserDTO;
import kr.co.sist.car_sell.function.UserMenu.ModifyUserPasswordFunction;
import kr.co.sist.car_sell.service.UserService;

/**
 * 비밀번호 수정 페이지의 이벤트 클래스.<br>
 * 일단 암호화는 상정하지 않음<br>
 */
public class ModifyUserPasswordEvt extends WindowAdapter implements ActionListener {
	private ModifyUserPasswordDesign mpd;
	private ModifyUserPasswordFunction mpf;
	private String pwCurrent, pwNew, pwNewCheck;
	private JLabel wrnPW, wrnPwNew, wrnPwCheck;
	private String userPw;// 사용자 비번
	private int user_code;// 사용자 식별 코드

	public ModifyUserPasswordEvt(ModifyUserPasswordDesign mpd,int user_code) {
		this.mpd = mpd;
		//this.mpf = new ModifyUserPasswordFunction(mpd);
		this.user_code= user_code;
		loadUserPw(this.user_code);// 사용자 비번을 불러와서 저장.
	}// ModifyUserPasswordEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mpd.getJbtnModify()) {// 버튼이 클릭되면

			getPasswordField(); // 패스워드 필드의 String을 인스턴스 변수로 저장.
			warningSet(false);// 경고 문구 전부 비활성화

			// 텍스트필드 공백, 이전 비번 틀림, 새 비번 불일치 시 Early Return
			if (!jtfEmptyWarning() || !pwCheck() || !newPwCheck()) {
				return;
			} // end if;

			// 성공 시
			warningSet(false);// 경고 문구 전부 비활성화

			// 확인창을 열고, '확인' 외의 버튼이면 Early Return
			if (!(JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(mpd, "비밀번호를 변경하시겠습니까?"))) {
				return;
			} // end if

			// 수정한 비번을 DB 저장하는 파트
			saveModifiedPW(user_code);

			JOptionPane.showMessageDialog(mpd, "비밀번호가 변경되었습니다.");
			jpfClean();// 비번 정보 삭제
			mpd.dispose();// 화면 닫기
		} // end if
	}// actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowClosing(e);
	}// windowClosing

//-----------------------------------------------------------------------	
	/**
	 * 디자인 클래스의 패스워드 필드를 가져와서 String 인스턴스 변수로 저장한다.<br>
	 * 경고 문구용 J라벨이 자주 쓰이는 관계로 같이 가져왔다.<br>
	 */
	public void getPasswordField() {
		//텍스트필드 값을 인스턴스 변수에 저장.
		pwCurrent = new String(mpd.getJpfPw().getPassword());
		pwNew = new String(mpd.getJpfNewPw().getPassword());
		pwNewCheck = new String(mpd.getJpfNewPwCheck().getPassword());

		//경고문구용 JLabel을 인스턴스 변수로 저장.
		wrnPW = mpd.getJlWrnPw();
		wrnPwNew = mpd.getJlWrnNewPw();
		wrnPwCheck = mpd.getJlWrnNewPwCheck();

	}// getPasswordField

	/**
	 * 액션 시 텍스트필드가 비어 있으면 경고문 JLabel을 보이게 함.<br>
	 */
	public boolean jtfEmptyWarning() {
		boolean flag = true;

		if (pwCurrent.isEmpty()) {
			wrnPW.setVisible(true);
			wrnPW.setText("내용이 비어있습니다.");
			flag = false;
		} else {
			wrnPW.setVisible(false);
		} // end else

		if (pwNew.isEmpty()) {
			wrnPwNew.setVisible(true);
			wrnPwNew.setText("내용이 비어있습니다.");
			flag = false;
		} else {
			wrnPwNew.setVisible(false);
		} // end else

		if (pwNewCheck.isEmpty()) {
			wrnPwCheck.setVisible(true);
			wrnPwCheck.setText("내용이 비어있습니다.");
			flag = false;
		} else {
			wrnPwCheck.setVisible(false);
		} // end else

		return flag;
	}// jtfEmptyWarning

	/**
	 * 비번 변경에 쓴 모든 정보를 삭제함.
	 */
	public void jpfClean() {
		mpd.getJpfPw().setText("");
		mpd.getJpfNewPw().setText("");
		mpd.getJpfNewPwCheck().setText("");

		userPw = "";
		pwCurrent = "";
		pwNew = "";
		pwNewCheck = "";

	}// jpfClean

	/**
	 * 모든 경고문을 보이게 하거나, 안 보이게 한다.
	 * 
	 * @param flag true일 때 경고문 표시.
	 */
	public void warningSet(boolean flag) {
		wrnPW.setVisible(flag);
		wrnPwNew.setVisible(flag);
		wrnPwCheck.setVisible(flag);
	}// warningSet
	//-----------------------------------------------------------------------
	/**
	 * 이전 패스워드와 일치하는지 체크.
	 * 
	 * @return 일치하면 true 리턴.
	 */
	public boolean pwCheck() {
		boolean flag = false;

		if (userPw.equals(pwCurrent)) {
			wrnPW.setVisible(false);
			flag = true;
		} else {
			wrnPW.setText("이전 비밀번호를 잘못 입력하셨습니다.");
			wrnPW.setVisible(true);
		} // end else

		return flag;
	}// pwCheck

	/**
	 * 새 비번, 새 비번 확인이 일치하는지 체크.
	 * 
	 * @return 일치하면 true 리턴.
	 */
	public boolean newPwCheck() {
		boolean flag = false;

		if (pwNew.equals(pwNewCheck)) {
			wrnPW.setVisible(false);
			flag = true;
		} else {
			wrnPwCheck.setVisible(true);
			wrnPwCheck.setText("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			mpd.getJpfNewPwCheck().setText("");
		} // end else
		return flag;
	}// pwCheck

// ----------DB 로직--------------------------------------------------------

	/**
	 * DB에서 Select로 사용자 비번을 가져와서 저장함.
	 * 
	 * @param user_code
	 */
	private void loadUserPw(int user_code) {
//		String userPw = "";
		UserDTO uDTO = new UserService().searchOneUser(user_code);

//		userPw = uDTO.getPass();
		this.userPw = uDTO.getPass();

//		return userPw;

	}// loadUserPw

	/**
	 * 수정한 사용자 비번을 DB에 update로 저장함.
	 */
	public void saveModifiedPW(int user_code) {
		System.out.println("new Password : " + pwNew); // 여기서 DB에 저장.

		int flag = new UserService().modifyPassword(user_code, pwNew);

		String outputMsg = "문제가 발생하였습니다. 잠시 후 다시 시도해주세요";
		switch (flag) {
		case 0:
			System.err.println("업데이트가 취소되었습니다.");
			break;

		case 1:
			outputMsg = "비밀번호를 변경하였습니다.";
			break;
		case 2:
			System.err.println("SQL문이 잘못되었습니다.");
			break;
		case 3:
			System.err.println("파일이 잘못되었습니다.");
			break;
		}// end switch
	}//saveModifiedPW
}// class
