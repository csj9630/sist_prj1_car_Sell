package kr.co.sist.car_sell.dao;

import java.io.ByteArrayOutputStream;
import java.io.File; // 파일 처리용
import java.io.FileInputStream; // 파일 읽기용
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

import kr.co.sist.car_sell.dto.ImageDTO;

public class ImageDAO_CSJ {
	private static ImageDAO_CSJ imageDAO;

	private ImageDAO_CSJ() {
	}

	public static ImageDAO_CSJ getInstance() {
		if (imageDAO == null) {
			imageDAO = new ImageDAO_CSJ();
		}
		return imageDAO;
	}

	/**
	 * [신규 추가] 차량 코드로 이미지 '경로' 목록 조회 (String 리스트 반환)
	 * 
	 * @param productCode 차량 코드
	 * @return 이미지 경로(String) 리스트
	 */
	public List<String> selectCarImagePaths(int productCode) throws SQLException, IOException {
		List<String> pathList = new ArrayList<>(); // String 리스트 생성
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GetConnection gc = GetConnection.getInstance();

		// IMAGE 테이블에서 image_name (경로) 컬럼만 조회
		String sql = "SELECT i.image_name " + "FROM CAR_IMAGE ci " + "JOIN IMAGE i ON ci.IMAGE_CODE = i.IMAGE_CODE "
				+ "WHERE ci.PRODUCT_CODE = ? ORDER BY i.IMAGE_CODE";

		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, productCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pathList.add(rs.getString("image_name")); // 경로 문자열을 리스트에 추가
			}
		} finally {
			gc.dbClose(con, pstmt, rs);
		}
		return pathList; // 경로 리스트 반환
	}

	/**
	 * [관리자/데이터입력용] 이미지 파일을 DB BLOB 컬럼에 저장
	 * 
	 * @param imageFile 파일 선택기로 고른 이미지 파일
	 * @return 1이면 성공
	 * @throws SQLException
	 * @throws IOException
	 */
	public int insertImageBlob(ImageDTO idto) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmtInsert = null;
		PreparedStatement pstmtSeq = null; // 시퀀스 조회용
		ResultSet rsSeq = null;
		FileInputStream fis = null; // 파일 읽기 스트림

		int generatedImageCode = 0;

		int rowsAffected = 0;

		GetConnection gc = GetConnection.getInstance();

		// 1. 시퀀스 값 먼저 가져오기
		String seqSql = "SELECT SEQ_IMAGE.NEXTVAL FROM DUAL";

		// ㅁ BLOB 데이터 삽입 쿼리
		String insertSql = "INSERT INTO IMAGE (image_code, product_code, image_name, image_blob, image_add_date) "
				+ "VALUES (?, ?, ?, ?, SYSDATE)";

		try {
			con = gc.getConn();
			con.setAutoCommit(false); // 트랜잭션 시작 (선택 사항)

			// 1-1. 시퀀스 실행
			pstmtSeq = con.prepareStatement(seqSql);
			rsSeq = pstmtSeq.executeQuery();

			if (rsSeq.next()) {
				generatedImageCode = rsSeq.getInt(1); // 시퀀스 넘버를 받음.
				System.out.println("gic : " + generatedImageCode);
			} else {
				throw new SQLException("SEQ_IMAGE 시퀀스 값을 가져오지 못했습니다.");
			} // end else

			// 1. 파일 스트림 열기
			File imageFile = idto.getFile();// idto에서 파일 받아옴.
			fis = new FileInputStream(imageFile);

			// 2. INSERT 쿼리 실행
			pstmtInsert = con.prepareStatement(insertSql);
			pstmtInsert.setInt(1, generatedImageCode); // 시퀀스로 받은 image_code값
			pstmtInsert.setInt(2, idto.getProduct_code()); // product_code값
			pstmtInsert.setString(3, imageFile.getName()); // 원본 파일명
			// ★ setBinaryStream(파라미터 인덱스, 파일 입력 스트림, 파일 길이) ★
			pstmtInsert.setBinaryStream(4, fis, (int) imageFile.length());

			rowsAffected = pstmtInsert.executeUpdate();
			if (rowsAffected == 1) {
				con.commit();
			} else {
				con.rollback(); // 실패 시 롤백
				generatedImageCode = 0;
			} // end else

		} catch (SQLException | IOException e) {
			if (con != null)
				con.rollback(); // 예외 발생 시 롤백
			throw e; // 예외 다시 던지기
		} finally {
			// 스트림 닫기
			if (fis != null)
				fis.close();
			// DB 리소스 닫기
			if (pstmtInsert != null)
				pstmtInsert.close();
			gc.dbClose(con, pstmtSeq, rsSeq); // Connection 닫기
		} // end finally
		return generatedImageCode; // 성공 시 생성된 image_code, 실패 시 0 반환
	}// insertImageBlob

	/** [구매/상세용] 이미지 코드로 BLOB 데이터를 ImageIcon으로 반환 */
	public ImageIcon getImageIconFromBlob(int imageCode) throws SQLException, IOException {
		ImageIcon icon = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GetConnection gc = GetConnection.getInstance();
		InputStream is = null;
		ByteArrayOutputStream baos = null; // 출력 데이터를 메모리(RAM) 상의 바이트 배열에 저장

		String sql = "SELECT image_blob FROM IMAGE WHERE product_code = ? order by image_code"; // BLOB 컬럼명 확인

		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, imageCode);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				is = rs.getBinaryStream("image_blob"); // BLOB 읽기
				if (is != null) {
					baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
					int bytesRead;
					while ((bytesRead = is.read(buffer)) != -1) { // 메모리로 복사
						baos.write(buffer, 0, bytesRead);
					}
					baos.flush();
					byte[] imageData = baos.toByteArray(); // byte 배열로 변환
					if (imageData.length > 0) {
						icon = new ImageIcon(imageData); // ImageIcon 생성
					}
				}
			}
		} finally {
			if (is != null)
				is.close();
			if (baos != null)
				baos.close();
			gc.dbClose(con, pstmt, rs);
		}
		return icon;
	}// getImageIconFromBlob

	/** [구매/상세용] 차량 코드로 '이미지 정보(DTO)' 목록 조회 (BLOB 제외) */
	public List<ImageIcon> selectImageList(int productCode) throws SQLException, IOException {
		// (이전에 제공한 코드와 동일 - image_code, image_name 등만 가져옴)
		List<ImageIcon> iconlist = new ArrayList<>();
		ImageIcon icon = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GetConnection gc = GetConnection.getInstance();

		InputStream is = null;
		ByteArrayOutputStream baos = null;
//		String sql = "SELECT i.IMAGE_CODE, i.IMAGE_NAME, i.IMAGE_ADD_DATE " + "FROM CAR_IMAGE ci "
//				+ "JOIN IMAGE i ON ci.IMAGE_CODE = i.IMAGE_CODE " + "WHERE ci.PRODUCT_CODE = ? ORDER BY i.IMAGE_CODE";
//		String sql = " select image_code, product_code, image_name, image_blob, image_add_date from image where product_code = ? order by image_code ";
		String sql = " select image_blob from image where product_code = ? order by image_code ";

		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, productCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				is = rs.getBinaryStream("image_blob"); // BLOB 읽기
				if (is != null) {
					baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
					int bytesRead;
					while ((bytesRead = is.read(buffer)) != -1) { // 메모리로 복사
						baos.write(buffer, 0, bytesRead);
					}
					baos.flush();
					byte[] imageData = baos.toByteArray(); // byte 배열로 변환
					if (imageData.length > 0) {
						icon = new ImageIcon(imageData); // ImageIcon 생성해서 blob이미지 저장.
					} // end if
				} // end if
				iconlist.add(icon); // List에 imageIcon 추가.
			} // end while

		} finally {
			if (is != null) is.close(); 
			if (baos != null) baos.close();
			gc.dbClose(con, pstmt, rs);
		} // end finally

		return iconlist;
	}// method

	// ★ 중요: CAR_IMAGE 테이블에 매핑 정보 추가하는 메소드도 필요 ★
	public void insertCarImageMapping(int productCode, int imageCode) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		GetConnection gc = GetConnection.getInstance();
		String sql = "INSERT INTO CAR_IMAGE (image_code, product_code) VALUES (?, ?)";

		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, imageCode);
			pstmt.setInt(2, productCode);
			pstmt.executeUpdate();
		} finally {
			gc.dbClose(con, pstmt, null);
		}
	}//insertCarImageMapping
}