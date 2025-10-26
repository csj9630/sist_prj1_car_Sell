package project1.Design;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettlementDAO {
	// Singleton Patton 사용을 위해 private으로 설정
	private static SettlementDAO smDAO;
//   private int productCode;
//   private int price;
//   private Date carYear;
//   private Date orderDate;
//   private int cc;
//   private int distance;
//   private String registartionNumber;
//   private String statusSold;
//   private String carName;
//   private String oil;

	private SettlementDAO() {

	}// SettlementDAO

	// Singleton Patton을 사용해서 인스턴스 객체 얻기
	public static SettlementDAO getInstance() {
		if (smDAO == null) {
			smDAO = new SettlementDAO();
		} // end if
		return smDAO;
	}// SettlementDAO

//	//분기별 판매현황 테이블에서 쓰일 데이터 DB에서 가져오기
//	public void searchPeriodOptionQuarter(String qtYear, String quarter) throws SQLException{
//		List<SettlementDTO> smDTOList = new ArrayList<SettlementDTO>();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		GetConnection gc = GetConnection.getInsance();
//		ResultSet rs = null;
//		try {
//			con = gc.getConn();
//			
//			String selectCarInfo = ("select to_char(oh.order_date,'yyyy-mm'),sum(ci.price)\r\n"
//					+ "from car_info ci, order_history oh\r\n"
//					+ "where ci.product_code = oh.product_code\r\n"
//					+ "group by to_char(oh.order_date,'yyyy-mm')\r\n"
//					+ "having to_char(oh.order_date,'yyyy-mm') between ? and ?");
//			StringBuilder sbSelect = new StringBuilder(selectCarInfo);
//			
//			
//			// 다이나믹 쿼리로 값 삽입. 
//			//x분기에서 앞의 숫자값만 저장할 변수
//						int quarterInt = 0;
//
//						if( !quarter.equals("1년")) {
//							quarterInt = Integer.parseInt(quarter.substring(0,1));
//							
//						}
//						
//						if (!car_name.equals("차종")) {
//							sbSelect.append(" and ci.car_name = ?");
//						} // end if
//						if (!oil.equals("유종")) {
//							sbSelect.append(" and ci.oil = ?");
//						} // end if
//						sbSelect.append(" order by ci.product_code asc");
//
//						pstmt = con.prepareStatement(sbSelect.toString());
//
//						pstmt.setDate(1, java.sql.Date.valueOf(startPeriod.trim()));
//						pstmt.setDate(2, java.sql.Date.valueOf(endPeriod.trim()));
//						if (!delevery_state.equals("탁송 상태")) {
//							pstmt.setString(++appendCnt, delevery_state.trim());
//						} // end if
//						if (!car_name.equals("차종")) {
//							pstmt.setString(++appendCnt, car_name.trim());
//						} // end if
//						if (!oil.equals("유종")) {
//							pstmt.setString(++appendCnt, oil.trim());
//						} // end if
//						System.out.println(sbSelect);
//
//						rs = pstmt.executeQuery();
//			
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}//searchPeriodOptionQuarter
	
	//기간, 옵션 설정 후 DB에서 데이터 가져오기
	public List<SettlementDTO> searchPeriodOption(String startPeriod, String endPeriod, String delevery_state,
			String car_name, String oil) throws SQLException {
		List<SettlementDTO> smDTOList = new ArrayList<SettlementDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		GetConnection gc = GetConnection.getInsance();
		ResultSet rs = null;
		try {
			System.out.println("년도 시작 : " + startPeriod);
			System.out.println("년도 끝 : " + endPeriod);

			System.out.println(startPeriod + " = " + startPeriod.equals("2025-10-1"));
			System.out.println(endPeriod + " = " + endPeriod.equals("2025-12-10"));
			System.out.println(delevery_state + " = " + delevery_state.equals("배송중"));
			System.out.println(car_name + " = " + car_name.equals("K5"));
			System.out.println(oil + " = " + oil.equals("경유"));
			con = gc.getConn();
			// 쿼리문을 설정하여 생성 객체 얻기
			String selectCarInfo = ("select ci.product_code, ci.car_name, ci.oil,ci.price, "
					+ "to_char(oh.order_date,'yyyy-mm-dd') to_order_date " + "from car_info ci, order_history oh "
					+ "where ci.product_code = oh.product_code and oh.order_date between ? and ?");
			StringBuilder sbSelect = new StringBuilder(selectCarInfo);
			// 다이나믹 쿼리로 값을 넣을 때, append 위치 기억 용도
			int appendCnt = 2;

			if (!delevery_state.equals("탁송 상태")) {
				sbSelect.append(" and oh.delivery_state = ?");
			} // end if
			if (!car_name.equals("차종")) {
				sbSelect.append(" and ci.car_name = ?");
			} // end if
			if (!oil.equals("유종")) {
				sbSelect.append(" and ci.oil = ?");
			} // end if
			sbSelect.append(" order by ci.product_code asc");

			pstmt = con.prepareStatement(sbSelect.toString());

			pstmt.setDate(1, java.sql.Date.valueOf(startPeriod.trim()));
			pstmt.setDate(2, java.sql.Date.valueOf(endPeriod.trim()));
			if (!delevery_state.equals("탁송 상태")) {
				pstmt.setString(++appendCnt, delevery_state.trim());
			} // end if
			if (!car_name.equals("차종")) {
				pstmt.setString(++appendCnt, car_name.trim());
			} // end if
			if (!oil.equals("유종")) {
				pstmt.setString(++appendCnt, oil.trim());
			} // end if
			System.out.println(sbSelect);

			rs = pstmt.executeQuery();
//         System.out.println("rowCnt = " + rs.getString("car_name"));
			int productCode = 0;
			int price = 0;
			String carName = null;
			String tableOil = null;
			String orderDate = null;
			SettlementDTO smDTO = null;
//         carYear = null;
//           cc = 0;
//           distance = 0;
//           registartionNumber = null;
//           statusSold = null;
			int cnt = 0;
			while (rs.next()) {
				productCode = rs.getInt("product_code");
				carName = rs.getString("car_name");
				tableOil = rs.getString("oil");
				price = rs.getInt("price");
				orderDate = rs.getString("to_order_date");
				smDTO = new SettlementDTO(startPeriod, endPeriod, delevery_state, car_name, oil);
				smDTO.setProduct_code(productCode);
				smDTO.setCar_name(carName);
				smDTO.setOil(tableOil);
				smDTO.setPrice(price);
				smDTO.setOrder_date(orderDate);
				smDTOList.add(smDTO);
				cnt++;
			} // end while

			System.out.println("cnt = " + cnt);
			System.out.println("" + productCode + ", " + price + ',' + carName + ", " + tableOil + ", " + orderDate);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			gc.dbClose(con, pstmt, null);
		} // end finally
		return smDTOList;
	}// searchPeriodOption

//   public static void main(String[] args) {
//      SettlementDAO s = new SettlementDAO();
//      try {
//         s.searchPeriodOption("2025-10-1", "2025-11-20", "배송중", "K5", "경유");
//      } catch (SQLException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//   }

}// class
