package kr.co.sist.car_sell.service;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import kr.co.sist.car_sell.dao.ImageDAO;
import kr.co.sist.car_sell.dao.ImageDAO_CSJ;
import kr.co.sist.car_sell.dto.ImageDTO;
import kr.co.sist.util.img.ImageResize;

public class ImageService {
	// 이미지 확장자
	private static final String ALLOWED_EXTENSIONS = "png,jpg,jpeg,gif,bmp";

	/**
	 * 상품코드를 받아서 파일 다이얼로그를 열어서 이미지 정보를 dto에 저장하고<br>
	 * 그 dto로 이미지 파일을 blob 저장을 수행한다.
	 * 
	 * @param product_code
	 */
	public void saveImg(int product_code) {
		// 파일 다이얼로그를 연다.
		JFileChooser jfc = new JFileChooser();
		jfc.setMultiSelectionEnabled(true);
		jfc.showOpenDialog(null);
		
		File imageFile = jfc.getSelectedFile();
		if (imageFile == null) {
			JOptionPane.showMessageDialog(null, "이미지가 선택되지 않았습니다.");
			return;
		} // end if

		if (!checkExt(imageFile.getName())) {
			JOptionPane.showMessageDialog(null, "이미지 확장자가 아닙니다.");
			return;
		} // end if

		// ImageDTO 생성
		ImageDTO idto = new ImageDTO();
		idto.setProduct_code(product_code);
		idto.setImage_name(imageFile.getName());
		idto.setFile(imageFile);

		ImageDAO_CSJ idao = ImageDAO_CSJ.getInstance();
		int imageCode = 0;// 삽입하는 이미지코드

		try {
			imageCode = idao.insertImageBlob(idto);// DB에 이미지를 추가.
			if (imageCode > 0) {
				JOptionPane.showMessageDialog(null, "이미지 등록 성공 (Code: " + imageCode + ")");
			} else {
				// DB에서 롤백되었거나 행이 삽입되지 않은 경우
				JOptionPane.showMessageDialog(null, "이미지 등록에 실패했습니다.(삽입 실패)", "경고", JOptionPane.WARNING_MESSAGE);
			} // end else
		} catch (SQLException e) {// DB 오류
			handleException("데이터베이스 처리 중 오류가 발생했습니다.", e);
			return;
		} catch (IOException e) {// 파일 오류
			handleException("이미지 파일 처리 중 오류가 발생했습니다.", e);
			return;
		} // end catch

//		return flag;
	}// addImg

	
	public void saveImg_All(int product_code) {
		// 파일 다이얼로그를 연다.
		
		for (int i = 1; i <= 200; i++) {

	          File imageFile = new File(String.format("src/temp_dir/%03d.jpg", i));

	          // 파일 존재 여부 확인
	          if (!imageFile.exists()) {
	              System.out.println(imageFile.getName() + " 파일이 존재하지 않아 건너뜁니다.");
	              continue; // 다음 루프로 이동
	          }

	          if (!checkExt(imageFile.getName())) {
	              JOptionPane.showMessageDialog(null, "이미지 확장자가 아닙니다.");
	              return;
	          }

	          // ImageDTO 생성
	          ImageDTO idto = new ImageDTO();
	          
	          // ★ 1. 현재 product_code 값을 DTO에 설정합니다.
	          idto.setProduct_code(product_code); 
	          
	          idto.setImage_name(imageFile.getName());
	          idto.setFile(imageFile);

	          ImageDAO_CSJ idao = ImageDAO_CSJ.getInstance();
	          int imageCode = 0;

	          try {
	              imageCode = idao.insertImageBlob(idto); // DB에 이미지를 추가.
	              
	              if (imageCode > 0) {
	                  System.out.println(imageFile.getName() + " 등록 성공 (ProductCode: " + product_code + ", ImageCode: " + imageCode + ")");
	              } else {
	                  JOptionPane.showMessageDialog(null, imageFile.getName() + " 등록에 실패했습니다.", "경고", JOptionPane.WARNING_MESSAGE);
	              }
	          } catch (SQLException e) {
	              handleException("데이터베이스 처리 중 오류가 발생했습니다.", e);
	              return;
	          } catch (IOException e) {
	              handleException("이미지 파일 처리 중 오류가 발생했습니다.", e);
	              return;
	          }

	          // --- ▼ 4개 배치 처리 로직 ▼ ---
	          
	          // ★ 2. 4번째 이미지가 방금 처리되었는지 확인합니다. (i가 4의 배수인가?)
	          if (i % 4 == 0) {
	              // 4개의 이미지가 한 배치를 완료했으므로, product_code를 1 증가시킵니다.
	              product_code++;
	              
	              System.out.println("--- 4개 배치 완료. 다음 Product Code: " + product_code + " ---");
	          }
	          // --- ▲ 4개 배치 처리 로직 ▲ ---

	      } // end for

	      // 100개 작업 완료
	      JOptionPane.showMessageDialog(null, "이미지 등록 작업이 완료되었습니다.");
		
	}// addImg
	
	
	
	
	/**
	 * DB에서 Blob 데이터를 불러와서 ImageIcon으로 리턴.
	 */
	public ImageIcon loadDBImage(int imageCode) {
		ImageIcon icon = null;
		ImageDAO_CSJ idao = ImageDAO_CSJ.getInstance();
		try {
			icon = idao.getImageIconFromBlob(imageCode);
			if(icon ==null) {
				JOptionPane.showMessageDialog(null, "imagecode가 일치하는 이미지가 없습니다!", "경고", JOptionPane.WARNING_MESSAGE);
			} // end else
		} catch (SQLException e) {
			handleException("데이터베이스 처리 중 오류가 발생했습니다.", e);
		} catch (IOException e) {
			handleException("이미지 파일 처리 중 오류가 발생했습니다.", e);
		} // end catch

		return icon;
	}// loadDBImage

//	public void prviewImg(File file) {
//		if (file == null) {
//			System.out.println("이미지크기 변경 불가");
//			return;
//		}
//		String fileName = file.getName();
//
//		if (!checkExt(fileName)) {
//			JOptionPane.showMessageDialog(null, "이미지 확장자가 아닙니다.");
//			return;
//		} // end if
//
//		// 이미지 크기 변경 -> 360*240
//		ImageResize.resizeImage(file.getAbsolutePath(), 360, 240);
//		selectedImg = file.getParent() + "/" + "rs_" + file.getName();
//		System.out.println(selectedImg);
//
//		wd.getJlbImage().setIcon(new ImageIcon(selectedImg));
//
//
//	}// prviewImg
	
	
	
	/**
	 * DB에서 상품코드가 같은 이미지를 리스트로 불러온다.
	 * @param product_code
	 * @return 상품이미지 리스트
	 */
	public List<ImageIcon> loadCarImgList(int product_code){
		List<ImageIcon> iconlist = new ArrayList<>();
		
		ImageDAO_CSJ idao = ImageDAO_CSJ.getInstance();
		
		//이미지가 없을 때의 오류처리
		try {
			iconlist = idao.selectImageList(product_code);
		} catch (SQLException e) {
			handleException("데이터베이스 처리 중 오류가 발생했습니다.", e);
		} catch (IOException e) {
			handleException("이미지 파일 처리 중 오류가 발생했습니다.", e);
		} // end catch
		
		
		
		return iconlist;
	}//loadCarImgSet
	
	
	
	//----------------------------동작 메서드-----------------------------------------------------

	/**
	 * 파일명의 확장자가 이미지 확장자인지 체크.
	 * 
	 * @param fileName
	 * @return 이미지 확장자 맞으면 true.
	 */
	public boolean checkExt(String fileName) {
		String ext = "";
		int lastDot = fileName.lastIndexOf(".");

		// 마지막 점이 파일 첫번째나 마지막이 아닌가?
		if (lastDot > 0 && lastDot < fileName.length() - 1) {
			ext = fileName.substring(lastDot + 1).toLowerCase();
		} else {
			return false; // 확장자가 없거나 파일명 오류
		}

		// 확장자 목록을 Set이나 List로 만들어서 contains()으로 체크
		return Arrays.asList(ALLOWED_EXTENSIONS.split(",")).contains(ext);
	}// checkExt

	/**
	 * 예외 처리 및 메시지 표시 헬퍼 메소드
	 */
	private void handleException(String message, Exception ex) {
		ex.printStackTrace(); // 콘솔에 상세 오류 출력
		JOptionPane.showMessageDialog(null, message + "\n" + ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
	}// handleException
	
}// class
