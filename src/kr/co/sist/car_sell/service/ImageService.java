package kr.co.sist.car_sell.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import kr.co.sist.car_sell.dao.ImageDAO;

public class ImageService {
	//이미지 확장자
	private static final String ALLOWED_EXTENSIONS = "png,jpg,jpeg,gif,bmp";
	
	public void addImg() {
		// 파일 다이얼로그를 연다.
		JFileChooser jfc = new JFileChooser();
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

		ImageDAO idao = ImageDAO.getInstance();
		int imageCode = 0;
		
		try {
			imageCode=idao.insertImageBlob(imageFile);//DB에 이미지를 추가.
			
			if (imageCode > 0) {
                JOptionPane.showMessageDialog(null, "이미지 등록 성공 (Code: " + imageCode + ")");
            } else {
                // DB에서 롤백되었거나 행이 삽입되지 않은 경우
                JOptionPane.showMessageDialog(null, "이미지 등록에 실패했습니다.(삽입 실패)", "경고", JOptionPane.WARNING_MESSAGE);
            }//end else
		} catch (SQLException e) {//DB 오류
			handleException("데이터베이스 처리 중 오류가 발생했습니다.", e);
			return;
		} catch (IOException e) {//파일 오류
			handleException("이미지 파일 처리 중 오류가 발생했습니다.", e);
			return;
		} // end catch

//		return flag;
	}// addImg

	/**
	 * 파일명의 확장자가 이미지 확장자인지 체크.
	 * 
	 * @param fileName
	 * @return 이미지 확장자 맞으면 true.
	 */
	public boolean checkExt(String fileName) {
        String ext = "";
        int lastDot = fileName.lastIndexOf(".");
        
      //마지막 점이 파일 첫번째나 마지막이 아닌가?
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
