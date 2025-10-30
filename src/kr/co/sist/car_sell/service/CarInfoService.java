package kr.co.sist.car_sell.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import kr.co.sist.car_sell.dao.CarDAO;
import kr.co.sist.car_sell.dto.CarDTO;

public class CarInfoService {
	
	private CarDAO cDAO;
	
	public CarInfoService() {
		cDAO = CarDAO.getInstance();
	}
	
	public CarDTO getProductDetails(int prodCode) throws Exception {
		
		CarDTO cDTO = cDAO.selectCarByCode(prodCode);

        if (cDTO == null) {
            throw new Exception(prodCode + "에 해당하는 상품 정보를 찾을 수 없습니다.");
        }
        
        return cDTO;
    } // getProductDetails
	
	
	public boolean addCar(CarDTO cDTO) {
		boolean flag = false;
		
		CarDAO cDAO = CarDAO.getInstance();
		
		try {
			cDAO.insertCarsMgr(cDTO);
			flag = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return flag;
	} // addCar
	
	public boolean updateCar(int prodCode, CarDTO cDTO) {
		boolean flag = false;
		
		CarDAO cDAO = CarDAO.getInstance();
		
		try {
			cDAO.updateCarsMgr(prodCode, cDTO);
			flag = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return flag;
	} // updateCar
	
	public String[] getAvailableOils() throws SQLException, IOException {
		
		List<String> oilList = cDAO.findOil();
		
		String[] oilArray = oilList.stream()
				.toArray(String[]::new);
		
		return oilArray;
	} // getAvailableOils
	
	public String[] getAvailableOptions() throws SQLException, IOException {
		
		List<String> optionList = cDAO.findOption();
		
		String[] optionArray = optionList.stream()
				.toArray(String[]::new);
		
		return optionArray;
	} // getAvailableOptions
	
	public String[] getProductOptionDetails(int prodCode) throws Exception {
		
		List<String> carOptionList = cDAO.findOptionNamesByProductCode(prodCode);
		
		String[] carOptionArray = carOptionList.stream()
				.toArray(String[]::new);
		
        return carOptionArray;
    } // getProductOptionDetails
	
	public String[] getAvailableDefects() throws SQLException, IOException {
		
		List<String> defectList = cDAO.findDefect();
		
		String[] defectArray = defectList.stream()
				.toArray(String[]::new);
		
		return defectArray;
	} // getAvailableDefects
	
	public String[] getProductDefectDetails(int prodCode) throws Exception {
		
		List<String> carDefectList = cDAO.findDefectNamesByProductCode(prodCode);
		// 1. (기존) DAO를 호출하여 기본 차량 정보를 가져옵니다.
		
		String[] carDefectArray = carDefectList.stream()
				.toArray(String[]::new);
		
		// 3. [추가] DTO 객체에 옵션 목록을 저장(결합)합니다.
		
		// 4. 옵션 정보까지 포함된 DTO를 Controller로 반환합니다.
		return carDefectArray;
	} // getProductDefectDetails
	
	public String[] getAvailableAccidents() throws SQLException, IOException {
		
		List<String> accidentList = cDAO.findAccident();
		
		String[] accidentArray = accidentList.stream()
				.toArray(String[]::new);
		
		return accidentArray;
	} // getAvailableAccidents
	
	public String[] getProductAccidentDetails(int prodCode) throws Exception {
		
		List<String> carAccidentList = cDAO.findAccidentNamesByProductCode(prodCode);
		
		String[] carAccidentArray = carAccidentList.stream()
				.toArray(String[]::new);
		
		return carAccidentArray;
	} // getProductAccidentDetails
	
	public String[] getAvailableRepairs() throws SQLException, IOException {
		
		List<String> repairList = cDAO.findRepair();
		
		String[] repairArray = repairList.stream()
				.toArray(String[]::new);
		
		return repairArray;
	} // getAvailableRepairs
	
	public String[] getProductRepairDetails(int prodCode) throws Exception {
		
		List<String> carRepairList = cDAO.findRepairNamesByProductCode(prodCode);
		
		String[] carRepairArray = carRepairList.stream()
				.toArray(String[]::new);
		
		return carRepairArray;
	} // getProductRepairDetails
	
} // class
