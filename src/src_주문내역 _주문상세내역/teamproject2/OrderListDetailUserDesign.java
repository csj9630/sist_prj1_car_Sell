package teamproject2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class OrderListDetailUserDesign extends JDialog{
	private JLabel jlblLogo, jlblOrderListDetail;
	private JPanel jplHeader, jplMain, jplCarInfo, jplOrderInfo;
	private OrderListDetailUserService oldus;
	
	public OrderListDetailUserDesign(int payment_code) {
		setTitle("주문 상세 내역[사용자]");
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
		jplMain.setLayout(new GridLayout(2, 1, 10, 10));
		jplMain.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		oldus=new OrderListDetailUserService();
		OrderListDetailUserDTO olduDTO=oldus.searchOneOrder(payment_code);
		
		//차량 정보 패널
		jplCarInfo=new JPanel(new GridLayout(2, 2, 10, 5));
		jplCarInfo.setBorder(new TitledBorder("[차량 정보]"));
		
		jplCarInfo.add(new JLabel("차량명: "+olduDTO.getProduct_name()));
		jplCarInfo.add(new JLabel("제조사: "+olduDTO.getBrand()));
		jplCarInfo.add(new JLabel("연식: "+olduDTO.getCar_year()));
		jplCarInfo.add(new JLabel("주행 거리: "+String.format("%,d", olduDTO.getDistance())+"km"));
        
		//거래 정보 패널
		jplOrderInfo=new JPanel(new GridLayout(2, 2, 20, 5));
		jplOrderInfo.setBorder(new TitledBorder("[주문 정보]"));
		
		jplOrderInfo.add(new JLabel("주문 ID: "+olduDTO.getPayment_code()));
        jplOrderInfo.add(new JLabel("주문일: "+olduDTO.getOrder_date()));
        jplOrderInfo.add(new JLabel("금액: "+String.format("%,d", olduDTO.getPrice())+"원"));
        jplOrderInfo.add(new JLabel("상태: "+olduDTO.getDelivery_state()));
		
		jplMain.add(jplCarInfo);
		jplMain.add(jplOrderInfo);
		
		add(jplHeader, BorderLayout.NORTH);
		add(jplMain, BorderLayout.CENTER);
		
		setBounds(500, 400, 500, 300);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}//orderlistdetailuserdesign
	
	public JLabel getJlblLogo() {
		return jlblLogo;
	}

	public JLabel getJlblOrderListDetail() {
		return jlblOrderListDetail;
	}

	public JPanel getJplHeader() {
		return jplHeader;
	}

	public JPanel getJplMain() {
		return jplMain;
	}

	public JPanel getJplCarInfo() {
		return jplCarInfo;
	}

	public JPanel getJplOrderInfo() {
		return jplOrderInfo;
	}

}//class
