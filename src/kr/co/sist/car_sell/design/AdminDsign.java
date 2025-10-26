package kr.co.sist.car_sell.design;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.co.sist.car_sell.event.AdminEvt;

public class AdminDsign extends JFrame {

    private JButton jbtnRegCar;
    private JButton jbtnUserMgmt;
    private JButton jbtnSalesMgmt;
    private JButton jbtnOrderList;

    public AdminDsign() {
        setTitle("쌍용중고차 - 관리자 메뉴");
        setLayout(new BorderLayout(10, 10));

        // 상단 타이틀 패널
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel jlTitle1 = new JLabel("쌍용중고차");
        jlTitle1.setFont(new Font("맑은 고딕", Font.BOLD, 28));
        jlTitle1.setAlignmentX(Component.CENTER_ALIGNMENT); // 가운데 정렬

        JLabel jlTitle2 = new JLabel("관리자 메뉴");
        jlTitle2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        jlTitle2.setAlignmentX(Component.CENTER_ALIGNMENT); // 가운데 정렬

        northPanel.add(jlTitle1);
        northPanel.add(Box.createVerticalStrut(10)); // 타이틀 사이 간격
        northPanel.add(jlTitle2);

        // 중앙 버튼 패널 (2x2 그리드)
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 15, 15)); // 2행 2열, 수평/수직 간격 15
        centerPanel.setBorder(new EmptyBorder(10, 20, 20, 20)); // 패딩

        Font buttonFont = new Font("맑은 고딕", Font.BOLD, 18);

        jbtnRegCar = new JButton("신규 차량 등록");
        jbtnUserMgmt = new JButton("회원관리");
        jbtnSalesMgmt = new JButton("정산관리");
        jbtnOrderList = new JButton("주문내역");
        
        jbtnRegCar.setFont(buttonFont);
        jbtnUserMgmt.setFont(buttonFont);
        jbtnSalesMgmt.setFont(buttonFont);
        jbtnOrderList.setFont(buttonFont);

        centerPanel.add(jbtnRegCar);
        centerPanel.add(jbtnUserMgmt);
        centerPanel.add(jbtnSalesMgmt);
        centerPanel.add(jbtnOrderList);

        // 프레임에 패널 추가
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        // 프레임 기본 설정
        setSize(500, 400);
        setLocationRelativeTo(null); // 화면 중앙에 배치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 메인 메뉴를 닫으면 프로그램 종료
        setVisible(true);
    }

    // 이벤트 클래스에서 버튼에 접근하기 위한 Getter 메서드
    public JButton getJbtnRegCar() {
        return jbtnRegCar;
    }

    public JButton getJbtnUserMgmt() {
        return jbtnUserMgmt;
    }

    public JButton getJbtnSalesMgmt() {
        return jbtnSalesMgmt;
    }

    public JButton getJbtnOrderList() {
        return jbtnOrderList;
    }

    public static void main(String[] args) {
        // AdminDesign을 생성하고 AdminEvt와 연결
        AdminDsign ad = new AdminDsign();
        new AdminEvt(ad);
    }
}