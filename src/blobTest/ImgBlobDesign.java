package blobTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.co.sist.car_sell.service.ImageService;

public class ImgBlobDesign extends JFrame implements ActionListener {

	public JButton getJbtnSelect() {
		return jbtnSelect;
	}

	public JTextField getJtfCode() {
		return jtfCode;
	}

	private JButton jbtnImage, jbtnAdd, jbtnSelect;
	private JTextField jtfCode;
	private JLabel jlbImage;
	
	private int product_code = 1;//차량코드 임시 데이터 

	public ImgBlobDesign() {
		super("친구관리");

		JLabel jlbInform = new JLabel("이미지 추가");

		ImageIcon ii = new ImageIcon("src/images/default.png");
		jlbImage = new JLabel(ii);
		jbtnImage = new JButton("선택--비활성화");
		jbtnAdd = new JButton("추가");
		jtfCode = new JTextField("");
		jbtnSelect = new JButton("불러오기--비활성화");
		
		setLayout(null);

		//타이틀 라벨 세팅
		jlbInform.setSize(200, 50);
		jlbInform.setLocation(10, 10);
		add(jlbInform);

		//이미지 라벨 세팅
		jlbImage.setSize(500, 500);
		jlbImage.setLocation(100, 20);
//		jlbImage.setBackground(Color.BLUE);
		jlbImage.setOpaque(true);
		jlbImage.setHorizontalAlignment(SwingConstants.CENTER);
		add(jlbImage);

		//이미지 버튼 세팅
		jbtnImage.setSize(170, 50);
		jbtnImage.setLocation(600, 200);
		add(jbtnImage);
		
		//추가 버튼 세팅
		jbtnAdd.setSize(170, 50);
		jbtnAdd.setLocation(600, 300);
		add(jbtnAdd);
		
		
		//선택 버튼 세팅
		jbtnSelect.setSize(170, 50);
		jbtnSelect.setLocation(600, 400);
		add(jbtnSelect);
		
		//텍스트필드 세팅
		jtfCode.setSize(200, 30);
		jtfCode.setLocation(600, 10);
		add(jtfCode);

		

		// 이벤트 등록
		//jbtnImage.addActionListener(this);
		jbtnAdd.addActionListener(this);
		//jbtnSelect.addActionListener(this);

		setBounds(100, 100, 1000, 600);
		setVisible(true);

	}//생성자
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == getJbtnImage()) {
			
		} // end if
		if (ae.getSource() == getJbtnAdd()) {
			new ImageService().addImg(product_code);
		} // end if
		
		if (ae.getSource() == getJbtnSelect()) {
			
		}// end if
	}

	public JLabel getJlbImage() {
		return jlbImage;
	}

	public JButton getJbtnImage() {
		return jbtnImage;
	}

	public JButton getJbtnAdd() {
		return jbtnAdd;
	}

	public static void main(String[] args) {
		new ImgBlobDesign();
	}

}
