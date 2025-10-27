package kr.co.sist.car_sell.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.sist.car_sell.dao.SettlementDAO;
import kr.co.sist.car_sell.dto.SettlementDTO;

public class SettlementService {

	public SettlementService() {

	}// SettlementService

	// 기간 및 옵션 설정 후 사용할 select문 호출 후 list에 값 담기
	public List<SettlementDTO> searchPeriodOption(String startPeriod, String endPeriod, String delevery_state,
			String car_name, String oil) {
		List<SettlementDTO> list = null;
		SettlementDAO smDAO = SettlementDAO.getInstance();

		try {
			list = smDAO.selectPeriodOption(startPeriod, endPeriod, delevery_state, car_name, oil);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		return list;
	}// searchPeriodOption

	// 분기별 판매현황용 select문 호출 후 리스트에 값 담기
	public List<SettlementDTO> searchPeriodOptionQuarter(String qtYear, String quarter) {
		List<SettlementDTO> list = null;
		SettlementDAO smDAO = SettlementDAO.getInstance();

		try {
			list = smDAO.selectPeriodOptionQuarter(qtYear, quarter);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		return list;
	}// searchPeriodOptionQuarter

}// class

