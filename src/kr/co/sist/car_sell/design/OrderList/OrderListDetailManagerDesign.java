package kr.co.sist.car_sell.design.OrderList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import kr.co.sist.car_sell.dto.OrderList.OrderListDetailManagerDTO;
import kr.co.sist.car_sell.event.OrderList.OrderListDetailManagerEvent;
import kr.co.sist.car_sell.service.OrderList.OrderListDetailManagerService;

/**
 * 수정
 */
public class OrderListDetailManagerDesign extends JDialog{
	private JLabel jlblLogo, jlblOrderListDetail, jlblstatus;
	private JPanel jplHeader, jplMain, jplUserInfo, jplCarInfo, jplOrderInfo, jplStatus;
	private JComboBox<String> jcbStatus;
    private JButton jbtnChange;
	private OrderListDetailManagerService oldms;
	private OrderListDetailManagerDTO oldmDTO;
	private OrderListManagerDesign olmd;
	private int selectedRow;
	
	public OrderListDetailManagerDesign(OrderListManagerDesign olmd, int payment_code, int selectedRow) {
		this.olmd=olmd;
		this.selectedRow=selectedRow;
		
		setTitle("주문 상세 내역[관리자]");
		setLayout(new BorderLayout());
		
		//상단 로고 및 제목
		jplHeader=new JPanel();
		jplHeader.setLayout(new GridLayout(2, 1, 10, 10));
		
		jlblLogo=new JLabel("쌍용중고차", JLabel.CENTER);
		jlblLogo.setFont(new Font("맑은 고딕", Font.BOLD, 22));

		jlblOrderListDetail=new JLabel("주문 상세 내역", JLabel.CENTER);
		jlblOrderListDetail.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		
		jplHeader.add(jlblLogo);
		jplHeader.add(jlblOrderListDetail);
		
		//중앙 내용 전체 패널
		jplMain=new JPanel();
		jplMain.setLayout(new GridLayout(3, 1, 10, 10));
		jplMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//고객 정보 패널
		jplUserInfo=new JPanel(new GridLayout(2, 2, 10, 5));
		jplUserInfo.setBorder(new TitledBorder("[고객 정보]"));
		
		oldms=new OrderListDetailManagerService();
		oldmDTO = oldms.searchOneOrder(payment_code); //버튼 클릭시 넘어오는 주문 번호
		
		jplUserInfo.add(new JLabel("이름: "+oldmDTO.getName()));
		jplUserInfo.add(new JLabel("이메일: "+oldmDTO.getEmail()));
		jplUserInfo.add(new JLabel("전화번호: "+oldmDTO.getTel()));
		jplUserInfo.add(new JLabel("주소: "+oldmDTO.getAddress()));
				
		//차량 정보 패널
		jplCarInfo=new JPanel(new GridLayout(2, 2, 10, 5));
		jplCarInfo.setBorder(new TitledBorder("[차량 정보]"));
		
		jplCarInfo.add(new JLabel("차량명: "+oldmDTO.getProduct_name()));
		jplCarInfo.add(new JLabel("제조사: "+oldmDTO.getBrand()));
		jplCarInfo.add(new JLabel("연식: "+oldmDTO.getCar_year()));
		jplCarInfo.add(new JLabel("주행 거리: "+String.format("%,d", oldmDTO.getDistance())+"km"));
        
		//거래 정보 패널
		jplOrderInfo=new JPanel(new GridLayout(2, 2, 20, 5));
		jplOrderInfo.setBorder(new TitledBorder("[주문 정보]"));
		
		jplOrderInfo.add(new JLabel("주문 ID: "+oldmDTO.getPayment_code()));
        jplOrderInfo.add(new JLabel("주문일: "+oldmDTO.getOrder_date()));
        jplOrderInfo.add(new JLabel("금액: "+String.format("%,d", oldmDTO.getPrice())+"원"));
        jplOrderInfo.add(jlblstatus=new JLabel("상태: "+oldmDTO.getDelivery_state())); //다른 라벨처럼 관리하면 변경버튼 눌렀을때 값이 변하지 않으므로 변수 선언
		
        //상태 변경 패널
        jplStatus=new JPanel(new GridLayout(2, 1, 20, 5));
        jcbStatus=new JComboBox<>(new String[] {"탁송 대기", "탁송 중", "탁송 완료"});
        jbtnChange=new JButton("변경");
        jbtnChange.setPreferredSize(new Dimension(10, 10));
		
        jplStatus.add(jcbStatus);
        jplStatus.add(jbtnChange);
        
        jplMain.add(jplUserInfo);
		jplMain.add(jplCarInfo);
		jplMain.add(jplOrderInfo);
		
		OrderListDetailManagerEvent oldme=new OrderListDetailManagerEvent(this);
		jbtnChange.addActionListener(oldme);
		jcbStatus.addActionListener(oldme);
		
		add(jplHeader, BorderLayout.NORTH);
		add(jplMain, BorderLayout.CENTER);
		add(jplStatus, BorderLayout.SOUTH);
		
		setBounds(500, 400, 550, 400);
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}//orderlistdetailmanagerdesign
	
	public JLabel getJlblLogo() {
		return jlblLogo;
	}

	public JLabel getJlblOrderListDetail() {
		return jlblOrderListDetail;
	}

	public JLabel getJlblstatus() {
		return jlblstatus;
	}

	public OrderListDetailManagerService getOldms() {
		return oldms;
	}

	public OrderListDetailManagerDTO getOldmDTO() {
		return oldmDTO;
	}

	public JPanel getJplHeader() {
		return jplHeader;
	}

	public JPanel getJplMain() {
		return jplMain;
	}

	public JPanel getJplUserInfo() {
		return jplUserInfo;
	}

	public JPanel getJplCarInfo() {
		return jplCarInfo;
	}

	public JPanel getJplOrderInfo() {
		return jplOrderInfo;
	}

	public JPanel getJplStatus() {
		return jplStatus;
	}

	public JComboBox<String> getJcbStatus() {
		return jcbStatus;
	}

	public JButton getJbtnChange() {
		return jbtnChange;
	}
	
	public int getPaymentCode() {
		return oldmDTO.getPayment_code();
	}
	
	/**
	 * 변경 버튼을 눌러도 고정되어버린 jplOrderInfo를 대신해 상태를 나타내기 위한 method  
	 * @param newState
	 */
	public void updateStatus(String newState) {
		jlblstatus.setText("상태: "+newState);
	}
	
	public void changeStatus(String newState) {
		olmd.getDtmOrderList().setValueAt(newState, selectedRow, 6);
	}
	
}
