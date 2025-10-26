package kr.co.sist.car_sell.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.sist.car_sell.dao.SettlementDAO;
import kr.co.sist.car_sell.dto.SettlementDTO;

//searchPeriodOption(String, String,String, String, String) : List<SettlementDTO>
//
//+searchSalesSettlement(String, String) : List<QuarterlySalesDTO>
//
//+searchTotalSummary(String, String) : TotalSummaryDTO 

public class SettlementService {
   
   public SettlementService() {
      
   }//SettlementService
   
   public List<SettlementDTO> searchPeriodOption(String startPeriod, String endPeriod, String delevery_state, String car_name, String oil) {
      List<SettlementDTO> list = null;
      
      SettlementDAO smDAO = SettlementDAO.getInstance();
      try {
//         System.out.println("이게 중요\n" + startPeriod +" ,"+ endPeriod +" ,"+ delevery_state +" ,"+ car_name +" ," + oil);
         list = smDAO.searchPeriodOption(startPeriod, endPeriod, delevery_state, car_name, oil);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return list;
   }//searchPeriodOption
   
   
}//class
