package kr.co.sist.car_sell.design.loginRegister;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class UserOrderDesign extends JDialog { 

    // 공통 컴포넌트
    private JLabel jlblTitle, jlblSubTitle;
    private CardLayout cardLayout;       
    private JPanel jpCardImages;         
    private JButton jbtnPrev, jbtnNext;  
    private JTextField jtfCarName;
    private JScrollPane jspRightPanel; 
    private JPanel jpForm; 
    private JLabel jlblName, jlblEmail, jlblTel, jlblCardNum, jlblAddr;
    private JTextField jtfName, jtfEmail, jtfTel, jtfCardNum, jtfAddr;
    private JLabel jlblPrice;
    private JTextField jtfPrice;
    private JButton jbtnOrder;
    
    public UserOrderDesign(JFrame owner) {
        super(owner, "차량 주문 페이지", true);
        
        jlblTitle = new JLabel("중고차 관리 사이트");
        jlblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        jlblTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT); 
        
        jlblSubTitle = new JLabel("구매");
        jlblSubTitle.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        jlblSubTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        cardLayout = new CardLayout();
        jpCardImages = new JPanel(cardLayout);
        
        for (int i = 0; i < 4; i++) {
            JLabel lblImg = new JLabel("  (차량 이미지 " + (i+1) + ")  "); 
            lblImg.setPreferredSize(new Dimension(350, 250)); 
            lblImg.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            lblImg.setOpaque(true);
            lblImg.setBackground(Color.WHITE);
            jpCardImages.add(lblImg, "card" + (i + 1));
        }
        
        jbtnPrev = new JButton("◀ 이전");
        jbtnNext = new JButton("다음 ▶");
        
        ActionListener cardNavListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == jbtnNext) {
                    cardLayout.next(jpCardImages);
                } else if (source == jbtnPrev) {
                    cardLayout.previous(jpCardImages);
                }
            }
        };
        
        jbtnPrev.addActionListener(cardNavListener);
        jbtnNext.addActionListener(cardNavListener);

        jtfCarName = new JTextField("Nissan S30 Fairlady Z"); 
        jtfCarName.setEditable(false);
        jtfCarName.setHorizontalAlignment(JTextField.CENTER);
        
        jlblName = new JLabel("이름(국문)");
        jlblEmail = new JLabel("이메일");
        jlblTel = new JLabel("전화번호");
        jlblCardNum = new JLabel("카드번호");
        jlblAddr = new JLabel("주소");
        jtfName = new JTextField("홍길동", 20);
        jtfName.setEditable(false);
        jtfEmail = new JTextField("hong123@naver.com", 20);
        jtfEmail.setEditable(false);
        jtfTel = new JTextField("010-1234-5678", 20);
        jtfTel.setEditable(false);
        jtfCardNum = new JTextField("5555-4444", 20);
        jtfCardNum.setEditable(false);
        jtfAddr = new JTextField("경기도 하남시 미사2동", 20);
        jtfAddr.setEditable(false);
        
        jlblPrice = new JLabel("주문 대금");
        jlblPrice.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        
        jtfPrice = new JTextField("80,000,000", 20);
        jtfPrice.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        jtfPrice.setEditable(false);
        jtfPrice.setHorizontalAlignment(JTextField.RIGHT);

        jbtnOrder = new JButton("주문하기");
        jbtnOrder.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        
        // 레이아웃
        JPanel jpTop = new JPanel(new BorderLayout());
        JPanel jpTitles = new JPanel();
        jpTitles.setLayout(new BoxLayout(jpTitles, BoxLayout.Y_AXIS));
        jlblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlblSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        jpTitles.add(jlblTitle);
        jpTitles.add(jlblSubTitle);
        jpTop.add(jpTitles, BorderLayout.CENTER);
        jpTop.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5)); 
        
        JPanel jpMain = new JPanel(new GridLayout(1, 2, 10, 0));

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
        jpRightForm.add(jbtnOrder);
        
        jspRightPanel = new JScrollPane(jpRightForm,
                                     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                     JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jspRightPanel.getVerticalScrollBar().setUnitIncrement(16);

        jpMain.add(jspRightPanel); 
       
        add(jpTop, BorderLayout.NORTH);
        add(jpMain, BorderLayout.CENTER);
        
        setSize(950, 700); 
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    public JButton getJbtnOrder() {
        return jbtnOrder;
    }
    
}