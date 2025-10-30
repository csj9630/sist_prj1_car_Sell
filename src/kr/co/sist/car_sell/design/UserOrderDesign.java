package kr.co.sist.car_sell.design; // 패키지 경로 확인

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image; // 이미지 크기 조절 시 필요
import java.awt.event.ActionListener;
import java.text.DecimalFormat; // 가격 포맷용
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon; // 이미지 표시용
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import kr.co.sist.car_sell.dto.CarDTO;
import kr.co.sist.car_sell.dto.UserDTO; // dto 패키지 (njw 버전)
import kr.co.sist.car_sell.event.UserOrderEvt;

public class UserOrderDesign extends JDialog {

	// --- 컴포넌트 변수 선언 ---
	private JLabel jlblTitle, jlblSubTitle;
	private CardLayout cardLayout;
	private JPanel jpCardImages;
	private JButton jbtnPrev, jbtnNext;
	private JTextField jtfCarName;
	private JScrollPane jspRightPanel;
	private JLabel jlblName, jlblEmail, jlblTel, jlblCardNum, jlblAddr;
	private JTextField jtfName, jtfEmail, jtfTel, jtfCardNum, jtfAddr;
	private JLabel jlblPrice;
	private JTextField jtfPrice;
	private JButton jbtnOrder;

	// --- DB 데이터를 저장할 멤버 변수 ---
	private UserDTO uDTO; // 로그인한 사용자 정보
	private CarDTO cDTO; // 조회된 차량 정보
	private List<String> imagePathList;// 조회된 이미지경로들

	/**
	 * 생성자: 부모 프레임, 로그인 사용자 정보(uDTO), 구매할 상품 코드(productCode)를 받음
	 */
	public UserOrderDesign(JDialog owner, int user_code, int productCode) {
		super(owner, "차량 주문 페이지", true); // 모달 다이얼로그

		// --- 이벤트 클래스 등록 ---
		UserOrderEvt uoe = new UserOrderEvt(this, user_code, productCode);

		// 이벤트 클래스 생성 때 로딩한 정보를 가져오기.
		this.uDTO = uoe.getuDTO();
		this.cDTO = uoe.getcDTO();
		this.imagePathList = uoe.getImagePathList();
		
		//비어 있는 list를 주고 빈 이미지 나오는지테스트.
//		this.imagePathList = new ArrayList<String>();
		
		
		//DB 데이터 테스트.
		System.out.println(uDTO);
		System.out.println(cDTO);
		System.out.println(imagePathList);

		// --- DB 조회 및 컴포넌트 초기화 ---

		// --- Swing 컴포넌트 생성 및 설정 ---
		jlblTitle = new JLabel("중고차 관리 사이트");
		jlblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		jlblTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		jlblSubTitle = new JLabel("구매");
		jlblSubTitle.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		jlblSubTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		// --- 이미지 패널 (CardLayout) 설정 ---
		cardLayout = new CardLayout();
		jpCardImages = new JPanel(cardLayout);
		jpCardImages.setPreferredSize(new Dimension(350, 250)); // 패널 크기 지정

		// --- 이미지 삽입 ----
		buildCarImg(imagePathList);

		// --- 이미지 네비게이션 버튼 ---
		jbtnPrev = new JButton("◀ 이전");
		jbtnNext = new JButton("다음 ▶");
		ActionListener cardNavListener = e -> {
			if (e.getSource() == jbtnNext)
				cardLayout.next(jpCardImages);
			else if (e.getSource() == jbtnPrev)
				cardLayout.previous(jpCardImages);
		};
		jbtnPrev.addActionListener(cardNavListener);
		jbtnNext.addActionListener(cardNavListener);

		// --- 차량명 (DB 데이터 적용) ---
		jtfCarName = new JTextField(cDTO.getProduct_name()); // getProduct_name() 사용
		jtfCarName.setEditable(false);
		jtfCarName.setHorizontalAlignment(JTextField.CENTER);
		jtfCarName.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

		// --- 사용자 정보 (DB 데이터 적용) ---
		jlblName = new JLabel("이름(국문)");
		jlblEmail = new JLabel("이메일");
		jlblTel = new JLabel("전화번호");
		jlblCardNum = new JLabel("카드번호");
		jlblAddr = new JLabel("주소");
		// 로그인 시 전달받은 uDTO 객체 사용
		jtfName = new JTextField(uDTO.getName(), 20);
		jtfName.setEditable(false);
		jtfEmail = new JTextField(uDTO.getEmail(), 20);
		jtfEmail.setEditable(false);
		jtfTel = new JTextField(uDTO.getTel(), 20);
		jtfTel.setEditable(false);
		jtfCardNum = new JTextField(uDTO.getCard_num(), 20);
		jtfCardNum.setEditable(false); // DTO의 getCard_num()
		jtfAddr = new JTextField(uDTO.getAddress(), 20);
		jtfAddr.setEditable(false);

		// --- 가격 (DB 데이터 적용) ---
		jlblPrice = new JLabel("주문 대금");
		jlblPrice.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		DecimalFormat df = new DecimalFormat("#,###"); // 숫자 포맷
		jtfPrice = new JTextField(df.format(cDTO.getPrice()) + " 원", 20); // getPrice() 사용
		jtfPrice.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		jtfPrice.setEditable(false);
		jtfPrice.setHorizontalAlignment(JTextField.RIGHT);
		jtfPrice.setBackground(Color.WHITE);
		jtfPrice.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		jbtnOrder = new JButton("주문하기");
		jbtnOrder.setFont(new Font("맑은 고딕", Font.BOLD, 14));

		// --- 레이아웃 구성 ---
		buildLayout(); // 레이아웃 구성 코드를 별도 메소드로 분리

		// 버튼에 리스너 등록
		getJbtnOrder().addActionListener(uoe);
		// (참고: 이미지 넘김 버튼(jbtnPrev, jbtnNext) 이벤트는
		// UserOrderDesign 생성자 내부에서 이미 자체적으로 처리되었습니다.)

		// --- 다이얼로그 기본 설정 ---
		
//		setSize(1250, 700);
//		setLocationRelativeTo(owner);
		setBounds(owner.getX() + 30, owner.getY() + 30, owner.getWidth() - 50, owner.getHeight() -50); // 부모좌표를 가져올 수 있음.		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	}// UserOrderDesign

	/**
	 * 화면 레이아웃을 구성하는 메소드
	 */
	private void buildLayout() {
		// 상단 패널 (타이틀)
		JPanel jpTop = new JPanel(new BorderLayout());
		JPanel jpTitles = new JPanel();
		jpTitles.setLayout(new BoxLayout(jpTitles, BoxLayout.Y_AXIS));
		jlblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		jlblSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpTitles.add(jlblTitle);
		jpTitles.add(jlblSubTitle);
		jpTop.add(jpTitles, BorderLayout.CENTER);
		jpTop.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));

		// 메인 패널 (좌우 분할)
		JPanel jpMain = new JPanel(new GridLayout(1, 2, 10, 0));

		// 왼쪽 패널 (이미지 + 차량명)
		JPanel jpLeft = new JPanel(new BorderLayout(0, 10));
		JPanel jpNavButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpNavButtons.add(jbtnPrev);
		jpNavButtons.add(jbtnNext);
		JPanel jpImagePanel = new JPanel(new BorderLayout());
		jpImagePanel.add(jpCardImages, BorderLayout.CENTER);
		jpImagePanel.add(jpNavButtons, BorderLayout.SOUTH);
		jpLeft.add(jpImagePanel, BorderLayout.CENTER);
		jpLeft.add(jtfCarName, BorderLayout.SOUTH);
		jpMain.add(jpLeft);

		// 오른쪽 패널 (사용자 정보 + 가격 + 주문 버튼)
		JPanel jpRightForm = new JPanel(new GridLayout(13, 1, 0, 5));
		jpRightForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jpRightForm.add(jlblName);
		jpRightForm.add(jtfName);
		jpRightForm.add(jlblEmail);
		jpRightForm.add(jtfEmail);
		jpRightForm.add(jlblTel);
		jpRightForm.add(jtfTel);
		jpRightForm.add(jlblCardNum);
		jpRightForm.add(jtfCardNum);
		jpRightForm.add(jlblAddr);
		jpRightForm.add(jtfAddr);
		jpRightForm.add(jlblPrice);
		jpRightForm.add(jtfPrice);
		jpRightForm.add(new JLabel()); // 빈 공간 (Grid 채우기용)
		jpRightForm.add(jbtnOrder);

		// 스크롤 패널에 오른쪽 폼 추가
		jspRightPanel = new JScrollPane(jpRightForm);
		jspRightPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jspRightPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jspRightPanel.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도
		jpMain.add(jspRightPanel);

		// 프레임에 최종 추가
		add(jpTop, BorderLayout.NORTH);
		add(jpMain, BorderLayout.CENTER);
	}// buildLayout

	/**
	 * DB에서 로딩한 이미지가 없을 때 불러낼 이미지.
	 */
	private void buildBlankImg() {
		JLabel lblNoImg = new JLabel("  (이미지 없음)  ");
		// (스타일 설정...)
		lblNoImg.setPreferredSize(new Dimension(350, 250));
		lblNoImg.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lblNoImg.setOpaque(true);
		lblNoImg.setBackground(Color.WHITE);
		lblNoImg.setHorizontalAlignment(JLabel.CENTER);
		jpCardImages.add(lblNoImg, "card1");
	}// setBlankImg

	private void buildCarImg(List<String> imagePathList) {
		String localPath = "src/";
		
		if (imagePathList.isEmpty()) { // 이미지 없을 때
			buildBlankImg();
		} else { // 이미지 있을 때
			int cardIndex = 1;
			for (String imagePath : imagePathList) {
				// ★ 경로로 ImageIcon 생성 (파일 존재 및 경로 확인 필수!) ★
				ImageIcon icon = new ImageIcon(localPath+imagePath);
				JLabel lblImg;

				// 이미지 로딩 상태 확인 (선택 사항)
				if (icon.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
					System.err.println("이미지 로드 실패: " + imagePath);
					lblImg = new JLabel("  (X)  "); // 실패 시 표시
				} else {
					// (선택) 이미지 크기 조절 (패널 크기에 맞게)
					Image scaledImage = icon.getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);
					lblImg = new JLabel(new ImageIcon(scaledImage));
				} // end else

				lblImg.setPreferredSize(new Dimension(350, 250));
				lblImg.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				lblImg.setHorizontalAlignment(JLabel.CENTER);
				jpCardImages.add(lblImg, "card" + cardIndex++);
			} // end for
		} // end else
	}// buildCarImg

	// --- Evt 클래스용 Getter 메소드 ---
	public JButton getJbtnOrder() {
		return jbtnOrder;
	}

	// --- Getter 끝 ---
}