package kr.co.sist.car_sell.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.car_sell.design.AdminDsign;
import kr.co.sist.car_sell.design.UserMgrDesign;

public class AdminEvt extends WindowAdapter implements ActionListener {

    private AdminDsign ad;

    public AdminEvt(AdminDsign ad) {
        this.ad = ad;

        // 각 버튼에 리스너 등록
        ad.getJbtnRegCar().addActionListener(this);
        ad.getJbtnUserMgmt().addActionListener(this);
        ad.getJbtnSalesMgmt().addActionListener(this);
        ad.getJbtnOrderList().addActionListener(this);
        ad.addWindowListener(this);
    }
    @Override
	public void windowClosing(WindowEvent e) {
		int choice = JOptionPane.showConfirmDialog(ad, "정말 종료하시겠습니까?", "종료 확인", JOptionPane.YES_NO_OPTION);
		if(choice == JOptionPane.YES_OPTION) {
			ad.dispose();
		}
	}

    @Override
    public void actionPerformed(ActionEvent ae) {
       
        // 신규 차량 등록 버튼 클릭 시
        if (ae.getSource() == ad.getJbtnRegCar()) {
            // new RegCarDialog(ad); // 'ad'를 부모 프레임으로 넘겨줌
        }

        // 회원관리 버튼 클릭 시
        if (ae.getSource() == ad.getJbtnUserMgmt()) {
            // 제공된 UserMgrDesign.java의 main 메서드를 호출하여 실행
            // 이렇게 하면 UserMgrDesign이 UserMgrEvt를 스스로 생성합니다.
        	UserMgrDesign umd = new UserMgrDesign(ad);
           new UserMgrEvt(umd);
        }

        // 정산관리 버튼 클릭 시
        if (ae.getSource() == ad.getJbtnSalesMgmt()) {
            // new SalesMgmtDialog(ad);
        }

        // 주문내역 버튼 클릭 시
        if (ae.getSource() == ad.getJbtnOrderList()) {
            // new OrderListDialog(ad);
        }
    }
}