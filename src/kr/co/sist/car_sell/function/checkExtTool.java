package kr.co.sist.car_sell.function;

import java.util.Arrays;

/**

 */
public class checkExtTool {

	// 이미지 확장자
	private static final String ALLOWED_EXTENSIONS = "png,jpg,jpeg,gif,bmp";

	/**
	 * 파일명의 확장자가 이미지 확장자인지 체크.
	 * 
	 * @param fileName
	 * @return 이미지 확장자 맞으면 true.
	 */
	public static boolean checkExt(String fileName) {
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

}//class