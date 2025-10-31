package blobTest;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import kr.co.sist.car_sell.service.ImageService;

public class ImgBlobDesign extends JFrame implements ActionListener {

	private JButton jbtnSave, jbtnAdd, jbtnSelect, jbtnLoad4;
	private JTextField jtfCode;
	private JLabel jlbImage;
	private JLabel[] setImg;

	private int product_code = 1;// 차량코드 임시 데이터
//	private int image_code = 64;//이미지코드 임시 데이터 

	public ImgBlobDesign() {
		super("BlobBraker");

		JLabel jlbInform = new JLabel("image_code로 blob에서 이미지 부르기.");

		ImageIcon ii = new ImageIcon("src/images/default.png");
		jlbImage = new JLabel(ii);
		jbtnSave = new JButton("복수로 업로드해보기");
		jbtnAdd = new JButton("blob 추가");
		jtfCode = new JTextField("");
		jbtnSelect = new JButton("blob 불러오기");
		jbtnLoad4 = new JButton("productCode로 4개 load");

		// 테스트용 이미지 넣을 라벨.
		setImg = new JLabel[4];
		for (int i = 0; i < 4; i++) {
			setImg[i] = new JLabel(ii);

			setImg[i].setSize(100, 100);
			setImg[i].setBorder(new LineBorder(Color.GRAY, 2, true));
			setImg[i].setLocation(100 + i * 100, 350);
			setImg[i].setOpaque(true);
			setImg[i].setHorizontalAlignment(SwingConstants.CENTER);
			add(setImg[i]);
		} // end for

		setLayout(null);

		// 타이틀 라벨 세팅
		jlbInform.setSize(400, 50);
		jlbInform.setLocation(600, 10);
		add(jlbInform);

		// 이미지 라벨 세팅
		jlbImage.setSize(300, 300);
		jlbImage.setLocation(100, 20);
//		jlbImage.setBackground(Color.BLUE);
		jlbImage.setOpaque(true);
		jlbImage.setHorizontalAlignment(SwingConstants.CENTER);
		add(jlbImage);

		// 추가 버튼 세팅
		jbtnAdd.setSize(170, 50);
		jbtnAdd.setLocation(600, 100);
		add(jbtnAdd);

		// 선택 버튼 세팅
		jbtnSelect.setSize(170, 50);
		jbtnSelect.setLocation(600, 200);
		add(jbtnSelect);

		// 업로드 버튼 세팅
		jbtnSave.setSize(170, 50);
		jbtnSave.setLocation(600, 300);
		add(jbtnSave);

		// 업로드 버튼 세팅
		jbtnLoad4.setSize(170, 50);
		jbtnLoad4.setLocation(600, 400);
		add(jbtnLoad4);

		// 텍스트필드 세팅
		jtfCode.setSize(150, 60);
		jtfCode.setLocation(800, 200);
		add(jtfCode);

		jbtnSave.setText("복수로 업로드--비활성화)");

		// 이벤트 등록
		jbtnSave.addActionListener(this);
		jbtnAdd.addActionListener(this);
		jbtnSelect.addActionListener(this);
		jbtnLoad4.addActionListener(this);

		setBounds(100, 100, 1000, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// 생성자

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == getJbtnSave()) {
			new ImageService().saveImg_All(product_code);
		} // end if
		if (ae.getSource() == getJbtnAdd()) {
			new ImageService().saveImg(product_code);
		} // end if

		if (ae.getSource() == getJbtnSelect()) {
			String tempImgCode = getJtfCode().getText();
			if (tempImgCode.isEmpty()) {
				JOptionPane.showMessageDialog(this, "입력값이 비어있다.");
				return;
			}
			getJlbImage().setIcon(new ImageService().loadDBImage(Integer.parseInt(tempImgCode)));
		} // end if

		if (ae.getSource() == getJbtnLoad4()) {
			System.out.println("bt4");
			int i = 0;
			List<ImageIcon> iconlist = new ImageService().loadCarImgList(product_code);
			for (ImageIcon imageIcon : iconlist) {
				if (i < 4) {
					setImg[i].setIcon(imageIcon);
					i++;
				}//end if

			} // end for
		} // end if
	}//

	public JLabel getJlbImage() {
		return jlbImage;
	}

	public JButton getJbtnSave() {
		return jbtnSave;
	}

	public JButton getJbtnAdd() {
		return jbtnAdd;
	}

	public JButton getJbtnSelect() {
		return jbtnSelect;
	}

	public JTextField getJtfCode() {
		return jtfCode;
	}

	public JButton getJbtnLoad4() {
		return jbtnLoad4;
	}

	public JLabel[] getSetImg() {
		return setImg;
	}

	public static void main(String[] args) {
		new ImgBlobDesign();
	}

}
