package kr.co.sist.car_sell.event;
//	... (주석) ...

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.car_sell.design.UserMgrDesign;
import kr.co.sist.car_sell.dto.UserMgrDTO;
import kr.co.sist.car_sell.service.UserMgrService;

public class UserMgrEvt extends WindowAdapter implements ActionListener {
	
	private UserMgrService ums;
	private UserMgrDesign umd;
	
	public UserMgrEvt(UserMgrDesign umd) {
		this.umd=umd;
		ums=new UserMgrService();
		umd.getjbtnDelete().addActionListener(this);
		umd.getjtfSearchName().addActionListener(this); 
		umd.addWindowListener(this);
	}

	/**
	 * [수정] 총 회원 수를 searchAllUsers가 아닌 getTotalUserCount로 변경
	 */
	public void searchUser() {
		String nameToSearch = umd.getjtfSearchName().getText().trim();
		List<UserMgrDTO> userList;
		userList = ums.searchAllUsers(nameToSearch);
		
		this.refreshTable(userList);
		// [수정] 검색 결과 수(userList.size())가 아닌 DB의 총 회원 수를 가져오도록 변경
		int totalCount = ums.getTotalUserCount(); 
		umd.getJlbTotalUsers().setText("총 회원수 : " + totalCount + "명");
	}
		
	
	/**
	 * list or array
	 */
	public void deleteUsers() {
	//마지막에 refreshtable	
	List<Integer> codesToDelete = new ArrayList<>();
		DefaultTableModel dtm = umd.getdtmMemberList();
		
		for(int i = 0; i < dtm.getRowCount(); i++) {
			// 0번 컬럼: 선택, 1번 컬럼: 회원번호 (숨김)
			if( (Boolean)dtm.getValueAt(i, 0) ) { 
				int userCode = (Integer)dtm.getValueAt(i, 1);
				codesToDelete.add(userCode);
			}
		}
		
		if(codesToDelete.isEmpty()) {
			JOptionPane.showMessageDialog(umd, "삭제할 회원을 선택해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int choice = JOptionPane.showConfirmDialog(umd, 
				codesToDelete.size() + "명의 회원을 정말로 삭제하시겠습니까?", 
				"삭제 확인", 
				JOptionPane.YES_NO_OPTION);
		
		if(choice == JOptionPane.YES_OPTION) {
			boolean deleteSuccess = ums.removeUsers(codesToDelete);
			
			if(deleteSuccess) {
				JOptionPane.showMessageDialog(umd, "선택한 회원이 삭제되었습니다.", "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(umd, "삭제 중 오류가 발생했습니다.", "삭제 실패", JOptionPane.ERROR_MESSAGE);
			}
			
			// [중요] 삭제 후 테이블 새로고침을 위해 searchUser() 호출
			searchUser();
		}
	}
	
	
private void refreshTable(List<UserMgrDTO> userList) {
		DefaultTableModel dtm = umd.getdtmMemberList();
		dtm.setRowCount(0); // 테이블 초기화
		
		if(userList == null || userList.isEmpty()) {
			return; // 데이터가 없으면 종료
		}
		
		for(UserMgrDTO dto : userList) {
			Object[] rowData = {
				false, // 선택
				dto.getUser_code(), // 회원번호 (숨김)
				dto.getName(), // 이름
				dto.getTel(), // 전화번호
				dto.getGenerate_date(),//가입일
				dto.getEmail(), // 이메일
				dto.getAddress() // 주소
			};
			dtm.addRow(rowData);
		}
	}

/**
	 * 프로그램 시작 시 전체 회원 조회
	 */
	 @Override
	 public void windowOpened(WindowEvent e) {
		searchUser(); 
	 }

	/**
	 * [수정] 창 닫기 시 확인 다이얼로그 추가
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		int choice = JOptionPane.showConfirmDialog(umd, "정말 종료하시겠습니까?", "종료 확인", JOptionPane.YES_NO_OPTION);
		if(choice == JOptionPane.YES_OPTION) {
			umd.dispose();
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==umd.getjbtnDelete()) {
			deleteUsers();
		}
		if(ae.getSource()==umd.getjtfSearchName()) {
			searchUser();
		}
	}


}
//class