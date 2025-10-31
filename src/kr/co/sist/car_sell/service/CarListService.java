package kr.co.sist.car_sell.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import kr.co.sist.car_sell.dao.CarDAO;
import kr.co.sist.car_sell.dto.CarDTO;

public class CarListService {
	
	private CarDAO cDAO;
	
	public CarListService() {
		cDAO = CarDAO.getInstance();
	}
	
//	public CarInfoDTO searchCars(String brandName, String oil) {
//		CarInfoDTO cDTO = null;
//		
//		CarInfoDAO cDAO = CarInfoDAO.getInstance();
//		try {
//			cDTO = cDAO.searchCars(brandName, oil);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} // end try ~ catch
//		
//		return cDTO;
//		
//	} // searchCars
	
	public CarDTO getProductDetails(int prodCode) throws Exception {
		
		CarDTO cDTO = cDAO.selectCarByCode(prodCode);

        if (cDTO == null) {
            throw new Exception(prodCode + "에 해당하는 상품 정보를 찾을 수 없습니다.");
        }
        
        return cDTO;
    } // getProductDetails
	
	public CarDTO selectCar(int prodCode) {
		CarDTO cDTO = null;
		
		try {
			cDTO = cDAO.selectCar(prodCode);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end try ~ catch
		
		return cDTO;
		
	} // selectCar
	
	public int[] getAvailableProductCodes() throws SQLException, IOException {
		
        List<Integer> prodCodeList = cDAO.findProductCodesByStatus("판매중");
        
        int[] codeArray = prodCodeList.stream()
        					.mapToInt(Integer::intValue)
        					.toArray();
        
        return codeArray;
    } // getAvailableProductCodes
	
	public String[] getAvailableBrands() throws SQLException, IOException {
		
		List<String> brandList = cDAO.findBrand();
		
		String[] brandArray = brandList.stream()
				.toArray(String[]::new);
		
		return brandArray;
	} // getAvailableBrands
	
	public String[] getAvailableOils() throws SQLException, IOException {
		
		List<String> oilList = cDAO.findOil();
		
		String[] oilArray = oilList.stream()
				.toArray(String[]::new);
		
		return oilArray;
	} // getAvailableOils
	
}
