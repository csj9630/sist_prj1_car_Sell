package kr.co.sist.car_sell.design.LoginRegister; // 패키지 경로 확인

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
// ▼▼▼ DB 연동 및 이미지/데이터 표시 위한 import ▼▼▼
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat; // 가격 포맷용
import java.util.List;          // 이미지 목록용
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;     // 이미지 표시용
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;   // 오류 메시지용
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
// --- 올바른 패키지 경로로 수정 ---
import kr.co.sist.car_sell.dao.image.ImageDAO;     // image 패키지 DAO
import kr.co.sist.car_sell.dto.car.CarDTO;       // car 패키지 DTO
import kr.co.sist.car_sell.event.LoginRegister.UserOrderEvt;
import kr.co.sist.car_sell.dto.UserDTOnjw;       // dto 패키지 (njw 버전)
import kr.co.sist.car_sell.service.UserServiceNjw; // service 패키지 (njw 버전)
// ▲▲▲

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
    private UserDTOnjw uDTO;      // 로그인한 사용자 정보
    private CarDTO cDTO;          // 조회된 차량 정보
    private int productCode;    // 구매할 차량 코드
    private int user_code;

    /**
     * 생성자: 부모 프레임, 로그인 사용자 정보(uDTO), 구매할 상품 코드(productCode)를 받음
     */
    public UserOrderDesign(JDialog owner, int user_code, int productCode) {
        super(owner, "차량 주문 페이지", true); // 모달 다이얼로그
        this.productCode = productCode;
        this.user_code=user_code;

        // --- DB 조회 및 컴포넌트 초기화 ---
        try {
            // Service & DAO 인스턴스
            UserServiceNjw userService = UserServiceNjw.getInstance();
            ImageDAO imgDAO = ImageDAO.getInstance(); // ImageDAO 사용

            // 1. 차량 상세 정보 조회
            this.cDTO = userService.getCarDetails(productCode);
            if (this.cDTO == null) { // 차량 정보 없으면 예외 발생
                throw new SQLException("차량 정보(productCode: " + productCode + ")를 찾을 수 없습니다.");
            }

            // 2. 차량 이미지 경로 목록 조회 (String 리스트)
            List<String> imagePathList = userService.getCarImagePaths(productCode); // Service의 메소드 호출

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

            if (imagePathList.isEmpty()) { // 이미지 없을 때
                JLabel lblNoImg = new JLabel("  (이미지 없음)  ");
                // (스타일 설정...)
                lblNoImg.setPreferredSize(new Dimension(350, 250));
                lblNoImg.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                lblNoImg.setOpaque(true);
                lblNoImg.setBackground(Color.WHITE);
                lblNoImg.setHorizontalAlignment(JLabel.CENTER);
                jpCardImages.add(lblNoImg, "card1");
            } else { // 이미지 있을 때
                int cardIndex = 1;
                for (String imagePath : imagePathList) {
                    // ★ 경로로 ImageIcon 생성 (파일 존재 및 경로 확인 필수!) ★
                    ImageIcon icon = new ImageIcon(imagePath);
                    JLabel lblImg;

                    // 이미지 로딩 상태 확인 (선택 사항)
                    if (icon.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
                        System.err.println("이미지 로드 실패: " + imagePath);
                        lblImg = new JLabel("  (X)  "); // 실패 시 표시
                    } else {
                        // (선택) 이미지 크기 조절 (패널 크기에 맞게)
                        Image scaledImage = icon.getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);
                        lblImg = new JLabel(new ImageIcon(scaledImage));
                    }

                    lblImg.setPreferredSize(new Dimension(350, 250));
                    lblImg.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    lblImg.setHorizontalAlignment(JLabel.CENTER);
                    jpCardImages.add(lblImg, "card" + cardIndex++);
                }
            }

            // --- 이미지 네비게이션 버튼 ---
            jbtnPrev = new JButton("◀ 이전");
            jbtnNext = new JButton("다음 ▶");
            ActionListener cardNavListener = e -> {
                if (e.getSource() == jbtnNext) cardLayout.next(jpCardImages);
                else if (e.getSource() == jbtnPrev) cardLayout.previous(jpCardImages);
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
            jtfName = new JTextField(uDTO.getName(), 20); jtfName.setEditable(false);
            jtfEmail = new JTextField(uDTO.getEmail(), 20); jtfEmail.setEditable(false);
            jtfTel = new JTextField(uDTO.getTel(), 20); jtfTel.setEditable(false);
            jtfCardNum = new JTextField(uDTO.getCard_num(), 20); jtfCardNum.setEditable(false); // DTO의 getCard_num()
            jtfAddr = new JTextField(uDTO.getAddress(), 20); jtfAddr.setEditable(false);

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

        } catch (SQLException | IOException ex) { // DB 관련 예외
            handleException("데이터를 불러오는 중 오류가 발생했습니다.", ex);
            dispose(); return;
        } catch (Exception ex) { // 그 외 예외 (이미지 경로 오류 등)
             handleException("화면 구성 중 오류가 발생했습니다.", ex);
             dispose(); return;
        }

        // --- 레이아웃 구성 ---
        buildLayout(); // 레이아웃 구성 코드를 별도 메소드로 분리

        // --- 다이얼로그 기본 설정 ---
        setSize(1250, 700);
        setResizable(false);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

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
        jpRightForm.add(jlblName); jpRightForm.add(jtfName);
        jpRightForm.add(jlblEmail); jpRightForm.add(jtfEmail);
        jpRightForm.add(jlblTel); jpRightForm.add(jtfTel);
        jpRightForm.add(jlblCardNum); jpRightForm.add(jtfCardNum);
        jpRightForm.add(jlblAddr); jpRightForm.add(jtfAddr);
        jpRightForm.add(jlblPrice); jpRightForm.add(jtfPrice);
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
    }

    /**
     * 예외 처리 및 메시지 표시 헬퍼 메소드
     */
    private void handleException(String message, Exception ex) {
        ex.printStackTrace(); // 콘솔에 상세 오류 출력
        JOptionPane.showMessageDialog(this, message + "\n" + ex.getMessage(),
                                     "오류", JOptionPane.ERROR_MESSAGE);
    }
    
    // --- Evt 클래스용 Getter 메소드 ---
    public JButton getJbtnOrder() { return jbtnOrder; }
    public UserDTOnjw getUserDTO() { return uDTO; }
    public int getProductCode() { return productCode; }
   
    // --- Getter 끝 ---
}