package kr.co.sist.car_sell.design.LoginRegister;


import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
public class RegisterDesign extends JDialog{ 
   
	private JLabel jlblTitle, jlblId, jlblPass, jlblPassCheck, jlblName, jlblEmail, jlblTel, jlblCardNum,jlblAddr;
	private JPasswordField jtfPassCheck; 
    private JTextField jtfId, jtfPass, jtfName, jtfEmail, jtfTel, jtfCardNum, jtfAddr; 
    private JButton jbtnSubmit;
   
     public RegisterDesign(JFrame owner) { 
         
          super(owner, "회원가입", true);
          setLayout(null);
          
          
          jlblTitle = new JLabel("사용자 회원가입");
          jlblId =new JLabel("아이디");
          jlblPass=new JLabel("패스워드");
          jlblPassCheck = new JLabel("패스워드 확인"); 
          jlblName=new JLabel("이름");
          jlblEmail=new JLabel("이메일");
          jlblTel=new JLabel("전화번호");
          jlblCardNum=new JLabel("카드번호");
          jlblAddr=new JLabel("주소");
         
          jlblTitle.setBounds(150,20,200,30); 
          jlblId.setBounds(20, 50, 100, 20);
          jlblPass.setBounds(20, 85, 100, 20);
          jlblPassCheck.setBounds(20, 120, 100, 20);          
          jlblName.setBounds(20, 155, 100, 20);
          jlblEmail.setBounds(20, 190, 100 , 20);
          jlblTel.setBounds(20, 225, 100, 20);
          jlblCardNum.setBounds(20, 260, 100, 20);
          jlblAddr.setBounds(20, 295, 100, 20);
         
          jlblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
          add(jlblTitle);
          add(jlblId);
          add(jlblPass);
          add(jlblPassCheck);
          add(jlblName);
          add(jlblEmail);
          add(jlblTel);
          add(jlblCardNum);
          add(jlblAddr);
         
          jtfId =new JTextField(20);
          jtfPass =new JTextField(20); 
          jtfPassCheck = new JPasswordField(20);
          jtfName= new JTextField(20);
          jtfEmail = new JTextField(20);
          jtfTel = new JTextField(20);
          jtfCardNum = new JTextField(20);
          jtfAddr = new JTextField(20);
         
          jtfId.setBounds(125, 50, 200, 30);
          jtfPass.setBounds(125, 85, 200, 30);
          jtfPassCheck.setBounds(125, 120, 200, 30);
          jtfName.setBounds(125, 155, 200, 30);
          jtfEmail.setBounds(125, 190, 200, 30);
          jtfTel.setBounds(125, 225, 200, 30);
          jtfCardNum.setBounds(125, 260, 200, 30);
          jtfAddr.setBounds(125, 295, 200, 30);
          
          add(jtfId);
          add(jtfPass);
          add(jtfPassCheck);
          add(jtfName);
          add(jtfEmail);
          add(jtfTel);
          add(jtfCardNum);
          add(jtfAddr);

          jbtnSubmit = new JButton("가입완료");
          
          jbtnSubmit.setBounds(150,340,150,30);
         
          add(jbtnSubmit);
         
          setSize(450, 450);
          setResizable(false);
          setResizable(false);
          setLocationRelativeTo(owner); 
          setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         
     }
     
     
     public JButton getJbtnSubmit() {
         return jbtnSubmit;
    }
     
     public JTextField getJtfId() {
         return jtfId;
     }

     public JTextField getJtfPass() {
         return jtfPass; 
     }

     public JPasswordField getJtfPassCheck() {
         return jtfPassCheck;
     }

     public JTextField getJtfName() {
         return jtfName;
     }

     public JTextField getJtfEmail() {
         return jtfEmail;
     }

     public JTextField getJtfTel() {
         return jtfTel;
     }

     public JTextField getJtfCardNum() {
         return jtfCardNum;
     }

     public JTextField getJtfAddr() {
         return jtfAddr;
     }
    
}