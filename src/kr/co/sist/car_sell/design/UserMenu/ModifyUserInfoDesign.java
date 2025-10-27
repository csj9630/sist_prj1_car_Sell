package kr.co.sist.car_sell.design.UserMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import kr.co.sist.car_sell.event.UserMenu.ModifyUserInfoEvt;

public class ModifyUserInfoDesign extends JDialog {

//	private JTextField jtfName, jtfEmail, jtfTel, jtfCard, jtfAddr ;
	private JTextField jtfName, jtfEmail, jtfAddr;
	private JLabel jlWrnName, jlWrnEmail, jlWrnTel, jlWrnCard, jlWrnAddr;
	private JButton jbtnModify;
	
	private JFormattedTextField jtfTel, jtfCard;

	public ModifyUserInfoDesign(UserMenuDesign umd, boolean modal, int user_code) {
		super(umd, "내 정보 수정", modal);

		// 폰트 설정
		Font fontTitle = new Font("맑은고딕", Font.BOLD, 30);
		Font fontTxtLabel = new Font("맑은고딕", Font.BOLD, 18);
		//Font fontWrn = new Font("맑은고딕", Font.BOLD, 14);
		Font fontBtn = new Font("맑은고딕", Font.BOLD, 20);
		Font fontJtf = new Font("맑은고딕", Font.PLAIN, 18);
		
		// 타이틀 라벨
		JLabel jlTitle = new JLabel("내 정보 수정");
		jlTitle.setFont(fontTitle);

		// 텍스트필드명 라벨
		JLabel jlName = new JLabel("이름(국문)");
		JLabel jlEmail = new JLabel("이메일");
		JLabel jlTel = new JLabel("전화번호");
		JLabel jlCard = new JLabel("카드번호");
		JLabel jlAddr = new JLabel("주소");
		jlName.setFont(fontTxtLabel);
		jlEmail.setFont(fontTxtLabel);
		jlTel.setFont(fontTxtLabel);
		jlCard.setFont(fontTxtLabel);
		jlAddr.setFont(fontTxtLabel);

		// 경고 라벨
		jlWrnName = new JLabel("이름이 (가) 비어있습니다");
		jlWrnEmail = new JLabel("이메일 이(가) 비어있습니다");
		jlWrnTel = new JLabel("전화번호 이(가) 비어있습니다");
		jlWrnCard = new JLabel("카드번호 이(가) 비어있습니다");
		jlWrnAddr = new JLabel("주소 이(가) 비어있습니다");
		
		//경고 라벨 폰트색
		jlWrnName.setForeground(Color.red);
		jlWrnEmail.setForeground(Color.red);
		jlWrnTel.setForeground(Color.red);
		jlWrnCard.setForeground(Color.red);
		jlWrnAddr.setForeground(Color.red);

		//경고 라벨 안 보이게
		jlWrnName.setVisible(false);
		jlWrnEmail.setVisible(false);
		jlWrnTel.setVisible(false);
		jlWrnCard.setVisible(false);
		jlWrnAddr.setVisible(false);
		
		// 텍스트필드
		jtfName = new JTextField("김철수");
		jtfEmail = new JTextField("javaSist@naver.com");
//		jtfTel = new JTextField("010-1234-5678");
//		jtfCard = new JTextField("1234-****-****-7896");
		jtfAddr = new JTextField("강남시 영통구 우환동 국민로12번길 504-1");
		

		//텍스트필드에 숫자 입력만 가능하게 제한
		
		//텍스트필드 입력 마스크
		try {
			MaskFormatter formatTel = new MaskFormatter("###-####-####");
			MaskFormatter formatCard = new MaskFormatter("####-####-####-####");
			jtfTel = new JFormattedTextField(formatTel);
			jtfTel.setColumns(20);
			jtfCard = new JFormattedTextField(formatCard);
			jtfCard.setColumns(20);
		}catch(ParseException e1) {
			e1.printStackTrace();
		}//end catch
		

		//텍스트필드 폰트 지정
		jtfName.setFont(fontJtf);
		jtfEmail.setFont(fontJtf);
		jtfTel.setFont(fontJtf);
		jtfCard.setFont(fontJtf);
		jtfAddr.setFont(fontJtf);
		
		
		
		// 버튼
		jbtnModify = new JButton("내 정보 수정");
		jbtnModify.setFont(fontBtn);
		jbtnModify.setForeground(Color.white);
		jbtnModify.setBackground(new Color(37, 157, 237));
		jbtnModify.setSize(this.getWidth(), 60);

		// 패널
		JPanel jpNorth = new JPanel();
		JPanel jpCenter = new JPanel();
		JPanel jpSouth = new JPanel();

		// 세트로 들어갈 패널
		JPanel jpName = new JPanel();
		JPanel jpEmail = new JPanel();
		JPanel jpTel = new JPanel();
		JPanel jpCard = new JPanel();
		JPanel jpAddr = new JPanel();

		// 각각 패널에 세로 3칸짜리 레이아웃 넣기
		jpName.setLayout(new GridLayout(3, 1));
		jpEmail.setLayout(new GridLayout(3, 1));
		jpTel.setLayout(new GridLayout(3, 1));
		jpCard.setLayout(new GridLayout(3, 1));
		jpAddr.setLayout(new GridLayout(3, 1));

		// 5개의 패널을 center 패널에 삽입.
		jpCenter.setLayout(new GridLayout(5, 1));

		// 타이틀 배치
		jpNorth.add(jlTitle);

		// 각 패널 여백
		jpName.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));// 상좌하우 여백
		jpEmail.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));// 상좌하우 여백
		jpTel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));// 상좌하우 여백
		jpCard.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));// 상좌하우 여백
		jpAddr.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));// 상좌하우 여백

		// name 패널 배치
		jpName.add(jlName);
		jpName.add(jtfName);
		jpName.add(jlWrnName);
//		jpName.setSize(100, 100);
		jpCenter.add(jpName);

		// Email 패널 배치
		jpEmail.add(jlEmail);
		jpEmail.add(jtfEmail);
		jpEmail.add(jlWrnEmail);
//		jpEmail.setSize(400, 100);

		jpCenter.add(jpEmail);

		// Tel 패널 배치
		jpTel.add(jlTel);
		jpTel.add(jtfTel);
		jpTel.add(jlWrnTel);
//		jpTel.setSize(400, 100);
		jpCenter.add(jpTel);

		// Card 패널 배치
		jpCard.add(jlCard);
		jpCard.add(jtfCard);
		jpCard.add(jlWrnCard);
//		jpCard.setSize(400, 100);
		jpCenter.add(jpCard);

		// Addr 패널 배치
		jpAddr.add(jlAddr);
		jpAddr.add(jtfAddr);
		jpAddr.add(jlWrnAddr);
//		jpAddr.setSize(400, 100);
		jpCenter.add(jpAddr);

		// 버튼 배치
		jpSouth.add(jbtnModify);

		// South 패널 크기 지정
		jpSouth.setSize(this.getWidth(), 100);

		// 배경색깔
		jpName.setBackground(Color.white);
		jpEmail.setBackground(Color.white);
		jpTel.setBackground(Color.white);
		jpCard.setBackground(Color.white);
		jpAddr.setBackground(Color.white);
		jpCenter.setBackground(Color.white);
		jpNorth.setBackground(Color.white);
		
		
	
		

		// 스크롤 패널을 center에 삽입
		// 스크롤 패널 생성
		JScrollPane jsp = new JScrollPane(jpCenter);

		// north, center 패널 배치
		add("North", jpNorth);
		add("Center", jsp);
		add("South", jpSouth);
		
		//이벤트 리스너 등록
		ModifyUserInfoEvt muie = new ModifyUserInfoEvt(this, user_code);
		jbtnModify.addActionListener(muie);
		
		

		
		// 창 설정
		setBounds(umd.getX() + 30, umd.getY() + 30, umd.getWidth() - 50, umd.getHeight() + 100); // 부모좌표를 가져올 수 있음.
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}// ModifyUserInfoDesign

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfEmail() {
		return jtfEmail;
	}

//	public JTextField getJtfTel() {
//		return jtfTel;
//	}
	public JFormattedTextField getJtfTel() {
		return jtfTel;
	}

	public JFormattedTextField getJtfCard() {
		return jtfCard;
	}

	public JTextField getJtfAddr() {
		return jtfAddr;
	}

	public JLabel getJlWrnName() {
		return jlWrnName;
	}

	public JLabel getJlWrnEmail() {
		return jlWrnEmail;
	}

	public JLabel getJlWrngTel() {
		return jlWrnTel;
	}

	public JLabel getJlWrnCard() {
		return jlWrnCard;
	}

	public JLabel getJlWrnAddr() {
		return jlWrnAddr;
	}

	public JButton getJbtnModify() {
		return jbtnModify;
	}
	
}// class
