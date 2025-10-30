package kr.co.sist.car_sell.dao; // 패키지 경로 확인

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.car_sell.dto.CarDTO;

/**
 * CAR_INFO 테이블 관련 DAO
 */
public class CarDAO {
	
	private static CarDAO carDAO;
	private List<Integer> prodCodes;
	private List<String> brands;
	private List<String> oils;
	private List<String> options;
	private List<String> carOptions;
	private List<String> defects;
	private List<String> carDefects;
	private List<String> accidents;
	private List<String> carAccidents;
	private List<String> repairs;
	private List<String> carRepairs;
	
	private CarDAO() { }
	
	public static CarDAO getInstance() {
		if (carDAO == null) { carDAO = new CarDAO(); }
		return carDAO;
	}
	
	// 차량 리스트용
	// "판매중"인 차량의 상품 코드 받아오기
	public List<Integer> findProductCodesByStatus(String status) throws SQLException, IOException  {
		prodCodes = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		String availableProd = "SELECT PRODUCT_CODE FROM CAR_INFO WHERE STATUS_SOLD = ?";
		
		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(availableProd);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				prodCodes.add(rs.getInt("PRODUCT_CODE"));
			}
		} finally {
			gc.dbClose(con, pstmt, rs);
		}
		return prodCodes;
	} // findProductCodesByStatus
 	
	// 모든 브랜드 조회
	// 차량 리스트 - 브랜드 필터링 JCheckBox
	public List<String> findBrand() throws SQLException, IOException {
		brands = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		String brandFind = "SELECT * FROM BRAND";
		
		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(brandFind);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				brands.add(rs.getString("BRAND_NAME"));
			}
		} finally {
			gc.dbClose(con, pstmt, rs);
		}
		
		return brands;
	} // findBrand	
	
	// 모든 유종 조회
	// 차량 리스트 - 유종 필터링 JCheckBox
	// 차량 상세 정보 - 유종 정보 변경 JComboBox
	public List<String> findOil() throws SQLException, IOException {
		oils = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		String oilFind = "SELECT * FROM OIL";
		
		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(oilFind);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				oils.add(rs.getString("OIL"));
			}
        } finally {
        	gc.dbClose(con, pstmt, rs);
        }
        return oils;
	} // findOil	
	
	// 모든 옵션 조회
	// 차량 상세 정보 - 옵션 편집을 위한 전체 옵션 목록
	public List<String> findOption() throws SQLException, IOException {
		options = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		String optionFind = "SELECT OPTION_NAME FROM OPTION_TABLE";
		
		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(optionFind);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				options.add(rs.getString("OPTION_NAME"));
			}
		} finally {
			gc.dbClose(con, pstmt, rs);
		}
		return options;
	} // findOption
	
	// 차량 코드가 prodCode인 차량의 옵션 목록 조회
	// 차량 상세 정보 - 사용자에게 선택한 차량의 옵션 목록 출력
	// 차량 상세 정보 - 관리자에게 전체 옵션 목록 중 선택한 차량의 옵션 목록 표기
	public List<String> findOptionNamesByProductCode(int prodCode) throws SQLException, IOException {
		carOptions = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		String carOptionFind = "SELECT B.OPTION_NAME FROM CAR_INFO A JOIN(SELECT A.PRODUCT_CODE, A.OPTION_CODE, B.OPTION_NAME FROM CAR_OPTION A JOIN OPTION_TABLE B ON A.OPTION_CODE = B.OPTION_CODE)B ON A.PRODUCT_CODE = B.PRODUCT_CODE WHERE A.PRODUCT_CODE = ?";
		
		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(carOptionFind);
			pstmt.setInt(1, prodCode);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				carOptions.add(rs.getString("OPTION_NAME"));
			}
		} finally {
			gc.dbClose(con, pstmt, null);
		}
		return carOptions;
	} // findOptionNamesByProductCode
	
	// 모든 하자 조회
	// 차량 상세 정보 - 하자 편집을 위한 전체 하자 목록
	public List<String> findDefect() throws SQLException, IOException {
		defects = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		String defectFind =
				"SELECT PROBLEM_HISTORY FROM DEFECT WHERE DEFECT_TYPE='외관 하자' OR DEFECT_TYPE='내부 하자' OR DEFECT_TYPE='차량 기능 하자'";
		
		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(defectFind);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				defects.add(rs.getString("PROBLEM_HISTORY"));
			}
		} finally {
			gc.dbClose(con, pstmt, rs);
		}
		return defects;
	} // findDefect
	
	// 차량 코드가 prodCode인 하자 옵션 목록 조회
	// 차량 상세 정보 - 사용자에게 선택한 차량의 하자 목록 출력
	// 차량 상세 정보 - 관리자에게 전체 하자 목록 중 선택한 차량의 하자 목록 표기
	public List<String> findDefectNamesByProductCode(int prodCode) throws SQLException, IOException {
		carDefects = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		String carDefectFind = "SELECT B.PROBLEM_HISTORY FROM CAR_INFO A JOIN(SELECT A.PRODUCT_CODE, A.DEFECT_CODE, B.PROBLEM_HISTORY, B.DEFECT_TYPE FROM CAR_DEFECT A JOIN DEFECT B ON A.DEFECT_CODE = B.DEFECT_CODE)B ON A.PRODUCT_CODE = B.PRODUCT_CODE WHERE A.PRODUCT_CODE = ? AND (B.DEFECT_TYPE = '외관 하자' or B.DEFECT_TYPE = '내부 하자' or B.DEFECT_TYPE = '차량 기능 하자')";
		
		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(carDefectFind);
			pstmt.setInt(1, prodCode);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				carDefects.add(rs.getString("PROBLEM_HISTORY"));
			}
		} finally {
			gc.dbClose(con, pstmt, rs);
		}
		return carDefects;
	} // findDefectNamesByProductCode
	
	// 모든 사고 조회
	// 차량 상세 정보 - 사고 편집을 위한 전체 사고 목록
	public List<String> findAccident() throws SQLException, IOException {
		accidents = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		String accidentFind =
				"SELECT PROBLEM_HISTORY FROM DEFECT WHERE DEFECT_TYPE='사고'";
		
		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(accidentFind);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				accidents.add(rs.getString("PROBLEM_HISTORY"));
			}
		} finally {
			gc.dbClose(con, pstmt, rs);
		}
		
		return accidents;
	} // findAccident
	
	// 차량 코드가 prodCode인 사고 목록 조회
	// 차량 상세 정보 - 사용자에게 선택한 차량의 사고 목록 출력
	// 차량 상세 정보 - 관리자에게 전체 사고 목록 중 선택한 차량의 사고 목록
	public List<String> findAccidentNamesByProductCode(int prodCode) throws SQLException, IOException {
		carAccidents = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		String carAccidentFind = "SELECT B.PROBLEM_HISTORY FROM CAR_INFO A JOIN(SELECT A.PRODUCT_CODE, A.DEFECT_CODE, B.PROBLEM_HISTORY, B.DEFECT_TYPE FROM CAR_DEFECT A JOIN DEFECT B ON A.DEFECT_CODE = B.DEFECT_CODE)B ON A.PRODUCT_CODE = B.PRODUCT_CODE WHERE A.PRODUCT_CODE = ? AND B.DEFECT_TYPE = '사고'";
		
		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(carAccidentFind);
			pstmt.setInt(1, prodCode);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				carAccidents.add(rs.getString("PROBLEM_HISTORY"));
			}
		} finally {
			gc.dbClose(con, pstmt, rs);
		}
		return carAccidents;
	} // findAccidentNamesByProductCode
	
	// 모든 수리 조회
	// 차량 상세 정보 - 수리 편집을 위한 전체 수리 목록
	public List<String> findRepair() throws SQLException, IOException {
		repairs = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		String repairFind =
				"SELECT PROBLEM_HISTORY FROM DEFECT WHERE DEFECT_TYPE='수리'";
		
		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(repairFind);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				repairs.add(rs.getString("PROBLEM_HISTORY"));
			}
		} finally {
			gc.dbClose(con, pstmt, rs);
		}
		
		return repairs;
	} // findRepair
	
	// 차량 코드가 prodCode인 수리 목록 조회
	// 차량 상세 정보 - 사용자에게 선택한 차량의 수리 목록 출력
	// 차량 상세 정보 - 관리자에게 전체 수리 목록 중 선택한 차량의 수리 목록
	public List<String> findRepairNamesByProductCode(int prodCode) throws SQLException, IOException {
		carRepairs = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		String carRepairFind = "SELECT B.PROBLEM_HISTORY FROM CAR_INFO A JOIN(SELECT A.PRODUCT_CODE, A.DEFECT_CODE, B.PROBLEM_HISTORY, B.DEFECT_TYPE FROM CAR_DEFECT A JOIN DEFECT B ON A.DEFECT_CODE = B.DEFECT_CODE)B ON A.PRODUCT_CODE = B.PRODUCT_CODE WHERE A.PRODUCT_CODE = ? AND B.DEFECT_TYPE = '수리'";
		
		try {
			con = gc.getConn();
			pstmt = con.prepareStatement(carRepairFind);
			pstmt.setInt(1, prodCode);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				carRepairs.add(rs.getString("PROBLEM_HISTORY"));
			}
		} finally {
			gc.dbClose(con, pstmt, rs);
		}
		return carRepairs;
	} // findRepairNamesByProductCode
	
	// 차량 리스트 - 리스트에 표기할 차량 정보 조회
    /** [구매페이지/차량상세용] 차량 코드로 상세 정보 조회 */
    public CarDTO selectCarByCode(int productCode) throws SQLException, IOException {
        CarDTO car = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        GetConnection gc = GetConnection.getInstance();
        
        String sql = "SELECT * FROM CAR_INFO WHERE PRODUCT_CODE = ?";
        
        try {
            con = gc.getConn();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, productCode);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                car = new CarDTO();
                car.setProduct_code(rs.getInt("PRODUCT_CODE"));
                car.setProduct_name(rs.getString("PRODUCT_NAME"));
                car.setPrice(rs.getInt("PRICE"));
                car.setCar_year(rs.getDate("CAR_YEAR"));
                car.setCc(rs.getInt("CC"));
                car.setDistance(rs.getInt("DISTANCE"));
                car.setRegistration_number(rs.getString("REGISTRATION_NUMBER"));
                car.setStatus_sold(rs.getString("STATUS_SOLD"));
                car.setCar_name(rs.getString("CAR_NAME"));
                car.setOil(rs.getString("OIL"));
                car.setBrand_name(rs.getString("BRAND_NAME"));
            } // end if
        } finally {
            gc.dbClose(con, pstmt, rs);
        } // end try ~ finally
        return car;
    } // selectCarByCode
    
    // 신규 차량 등록
	public void insertCarsMgr(CarDTO cDTO) throws SQLException, IOException {
		// 1. 드라이버 로딩
		
		// 2. 커넥션 얻기
		Connection con = null;
		PreparedStatement pstmtBrand = null;
		PreparedStatement pstmtCar = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		String insertBrand = "insert into brand(brand_name) values(?)";
		
		String insertCar
		= "insert into car_info(product_code, product_name, price, car_year, cc,"
				+ "distance, registration_number, status_sold, car_name, oil, brand_name)"
				+ "values(SEQ_CAR_INFO.nextval,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			con = gc.getConn();
			
			try {
	            pstmtBrand = con.prepareStatement(insertBrand);
	            pstmtBrand.setString(1, cDTO.getBrand_name()); // "쉐보레"
	            pstmtBrand.executeUpdate();
	            
	            System.out.println("신규 브랜드 '" + cDTO.getBrand_name() + "' 등록 성공");

	        } catch (SQLException e) {
	            if (e.getErrorCode() == 1) {
	                System.out.println("'" + cDTO.getBrand_name() + "'는 이미 존재하는 브랜드입니다.");
	            } else {
	                throw e; 
	            }
	        } finally {
	            if (pstmtBrand != null) pstmtBrand.close();
	        }
			
			pstmtCar = con.prepareStatement(insertCar);
			
			pstmtCar.setString(1, cDTO.getProduct_name());
			pstmtCar.setInt(2, cDTO.getPrice());
			pstmtCar.setDate(3, cDTO.getCar_year());
			pstmtCar.setInt(4, cDTO.getCc());
			pstmtCar.setInt(5, cDTO.getDistance());
			pstmtCar.setString(6, cDTO.getRegistration_number());
			pstmtCar.setString(7, cDTO.getStatus_sold());
			pstmtCar.setString(8, cDTO.getCar_name());
			pstmtCar.setString(9, cDTO.getOil());
			pstmtCar.setString(10, cDTO.getBrand_name());
			
			pstmtCar.executeUpdate();
			
		} finally {
			gc.dbClose(con, pstmtCar, null);	// 연결을 끊을 때는 commit을 수행하고 끊는다.
		} // end try ~ finally
	} // insertCarsMgr
	
    // 차량 정보 수정
	public void updateCarsMgr(int prodCode, CarDTO cDTO) throws SQLException, IOException {
		
		Connection con = null;
		PreparedStatement pstmtCar = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		String updateCar
		= "UPDATE car_info "
			    + "SET product_name = ?, price = ?, car_year = ?, cc = ?, "
			    + "distance = ?, registration_number = ?, status_sold = ?, "
			    + "car_name = ?, oil = ?, brand_name = ? "
			    + "WHERE product_code = " + String.valueOf(prodCode);
			    
		try {
			con = gc.getConn();
			
			pstmtCar = con.prepareStatement(updateCar);
			
			pstmtCar.setString(1, cDTO.getProduct_name());
			pstmtCar.setInt(2, cDTO.getPrice());
			pstmtCar.setDate(3, cDTO.getCar_year());
			pstmtCar.setInt(4, cDTO.getCc());
			pstmtCar.setInt(5, cDTO.getDistance());
			pstmtCar.setString(6, cDTO.getRegistration_number());
			pstmtCar.setString(7, cDTO.getStatus_sold());
			pstmtCar.setString(8, cDTO.getCar_name());
			pstmtCar.setString(9, cDTO.getOil());
			pstmtCar.setString(10, cDTO.getBrand_name());
			
			pstmtCar.executeUpdate();
			
		} finally {
			gc.dbClose(con, pstmtCar, null);	// 연결을 끊을 때는 commit을 수행하고 끊는다.
		} // end try ~ finally
	} // updateCarsMgr
	
	public CarDTO selectCar(int prodCode) throws SQLException, IOException {
		CarDTO cDTO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		InputStream is = null;
//		FileOutputStream fos = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			con = gc.getConn();
			
			StringBuilder selectCar = new StringBuilder();
			selectCar
			.append("	SELECT	BRAND_NAME, PRODUCT_NAME, PRICE, CAR_YEAR, CC, DISTANCE, REGISTRATION_NUMBER, STATUS_SOLD, OIL"	)
			.append("	FROM	CAR_INFO"																			)
			.append("	WHERE	PRODUCT_CODE = ?"																	);
			
			pstmt = con.prepareStatement(selectCar.toString());
			pstmt.setInt(1, prodCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cDTO = new CarDTO();
				cDTO.setProduct_code(prodCode);
				// 입력된 번호 할당
				// DBMS table에서 조회된 결과를 설정
				cDTO.setProduct_name(rs.getString("product_name"));
				cDTO.setPrice(rs.getInt("price"));
				cDTO.setCar_year(rs.getDate("car_year"));
				cDTO.setCc(rs.getInt("cc"));
				cDTO.setDistance(rs.getInt("distance"));
				cDTO.setRegistration_number(rs.getString("registration_number"));
				cDTO.setStatus_sold(rs.getString("status_sold"));
				cDTO.setBrand_name(rs.getString("brand_name"));
				cDTO.setOil(rs.getString("oil"));
			} // end if
		} finally {
			gc.dbClose(con, pstmt, rs);
		}
		return cDTO;
	}
	// (참고: 이미지 조회 메소드(selectCarImages)는 아래 ImageDAO로 분리했습니다.)
	// (필요 시 차량 목록 조회(selectAllCars), 차량 상태 변경(updateCarStatus) 등 추가)
	
}