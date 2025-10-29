package kr.co.sist.car_sell.design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import kr.co.sist.car_sell.event.UserMgrEvt;


public class UserMgrDesign extends JDialog {
	private JLabel jlbTotalUsers,jltitle,jlbSearch;
	private JTextField jtfSearchName;
	private JTable jtMember;
	private DefaultTableModel dtmMemberList;
	private JButton jbtnDelete;
	private JPanel northPanel,southPanel,searchPanel,subPanel;
	private JScrollPane jsp;
	
	
	public UserMgrDesign(JFrame parentFrame){
		
		setTitle("회원 관리");
        setLayout(new BorderLayout(10, 10)); 
        Color lightGray = new Color(199, 237, 190);
		
		// (이하 생성자 코드는 동일)
		northPanel=new JPanel();
		
		northPanel.setLayout(new BoxLayout(northPanel,  BoxLayout.Y_AXIS));
		northPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
		northPanel.setBackground(lightGray);
		
		jltitle=new JLabel("회원 관리");
		jltitle.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        jltitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(Box.createVerticalStrut(15)); 
		northPanel.add(jltitle);
		
		
		subPanel=new JPanel();
		subPanel.setBorder(new EmptyBorder(0,10,0,10));
		subPanel.setLayout(new BorderLayout());
		jlbTotalUsers=new JLabel("총 회원수 : 0명"); 
		subPanel.setBackground(lightGray);
		subPanel.add(jlbTotalUsers,BorderLayout.WEST);
		
		searchPanel =new JPanel();
		jlbSearch=new JLabel("검색"); 
		jtfSearchName=new JTextField(15);
		searchPanel.add(jlbSearch);
		searchPanel.add(jtfSearchName);
		searchPanel.setBackground(lightGray);
		subPanel.add(searchPanel,BorderLayout.EAST);

		southPanel =new JPanel(new BorderLayout());
		southPanel.setBorder(new EmptyBorder(5, 20, 10, 10));
		southPanel.setBackground(lightGray);
		jbtnDelete=new JButton("삭제");
		jbtnDelete.setPreferredSize(new Dimension(100,30));
		southPanel.add(jbtnDelete,BorderLayout.EAST);
		
		northPanel.add(subPanel);
		
		
		String[] columNames={"선택","회원번호","이름","전화번호","가입일","이메일","주소"};
		dtmMemberList=new DefaultTableModel(columNames,0) {
		@Override
        public boolean isCellEditable(int row, int column) {
            return column == 0;
        }
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0) return Boolean.class; 
            return Object.class;
        }
		};
		jtMember=new JTable(dtmMemberList);

		TableColumn firstColumn = jtMember.getColumnModel().getColumn(0);
		firstColumn.setMinWidth(50);
		firstColumn.setMaxWidth(50);
		firstColumn.setPreferredWidth(50);

		TableColumn codeColumn = jtMember.getColumnModel().getColumn(1);
		codeColumn.setMinWidth(0);
		codeColumn.setMaxWidth(0);
		codeColumn.setPreferredWidth(0);
		
		TableColumn nameColumn = jtMember.getColumnModel().getColumn(2);
		nameColumn.setMinWidth(95);
		nameColumn.setMaxWidth(95);
		nameColumn.setPreferredWidth(95);
		TableColumn telColumn = jtMember.getColumnModel().getColumn(3);
		telColumn.setMinWidth(125);
		telColumn.setMaxWidth(125);
		telColumn.setPreferredWidth(125);
		
		

        jsp=new JScrollPane(jtMember);
        jsp.setBorder(BorderFactory.createLineBorder(new Color(199, 237, 190), 2));
        
        //View의 더미 데이터 생성 코드 삭제
       
		add(jsp,BorderLayout.CENTER);
		add(northPanel,BorderLayout.NORTH);
		add(southPanel,BorderLayout.SOUTH);
		setSize(950, 900);
		setLocationRelativeTo(parentFrame);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}
	
	public JButton getjbtnDelete() {
		return jbtnDelete;
	}
	public JTextField getjtfSearchName() {
		return jtfSearchName;
	}
	public DefaultTableModel getdtmMemberList() {
		return dtmMemberList;
	}
	public JTable getJtMember() {
		return jtMember;
	}
	public JLabel getJlbTotalUsers() {
		return jlbTotalUsers;
	}

	
//	public static void main(String[] args) {
//		UserMgrDesign umd = new UserMgrDesign(null);
//		new UserMgrEvt(umd);
//	}//main
	
}
//class