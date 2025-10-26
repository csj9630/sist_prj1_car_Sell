package kr.co.sist.car_sell.event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.car_sell.design.SettlementDesign;
import kr.co.sist.car_sell.dto.SettlementDTO;
import kr.co.sist.car_sell.service.SettlementService;

public class SettlementEvt extends WindowAdapter implements ActionListener{
   private SettlementDesign smd;
//   private SettlementService sms;
   private int totalSell,  totalCount;
   private double totalProfit;
   private DefaultTableModel dtmSettlement;
   public SettlementEvt(SettlementDesign smd) {
      this.smd = smd;
   }//SettlementEvt

   /**
    * 검색 버튼 -> DTO에서 값 받아오기
    */
   public void searchPeriodOption(String startPeriod, String endPeriod, String delevery_state, String car_name, String oil) {
//      public void searchPeriodOption(String startPeriod) {
//      SettlementDesign smd = new SettlementDesign(); 
      dtmSettlement = smd.getDtmSettlementInfot();
      SettlementService sms = new SettlementService();
      
      List<SettlementDTO> listStDTO = sms.searchPeriodOption(startPeriod, endPeriod, delevery_state, car_name, oil);
      String[] rowData = null;
      dtmSettlement.setRowCount(0);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
      
      //검색 버튼을 누르는데, 분기별 판매 현황 드롭박스가 아니라면, 카드레이아웃을 기본으로 바꿔주고, 드롭박스도 기본 상태로 바꿔줌.
      if (!smd.getJcbQuarterlySettlement().getSelectedItem().equals("분기별 판매현황")) {
          smd.getCardLayout().show(smd.getJpCardPanel(), "cardSettlement");
          smd.getJcbQuarterlySettlement().setSelectedIndex(0);
       }//end if
      
      //검색 했는데 검색된 값이 없다면 다이아로그로 알려주고 다시 검색하라고 함.
      if( listStDTO.size() == 0 ) {
    	  JOptionPane.showMessageDialog(smd, "검색된 값이 없습니다. 옵션을 조정해주세요.");
      }//end if
      
      //테이블에 넣을 값 추가.
      for(SettlementDTO smDTO : listStDTO) {
         rowData = new String[7];
         rowData[0] = String.valueOf(smDTO.getProduct_code());
         rowData[1] = smDTO.getCar_name();
         rowData[2] = smDTO.getOil();
         rowData[3] = String.format("%,d" ,smDTO.getPrice())+" 원";
         rowData[4] = String.format("%,.1f",smDTO.getPrice() * 0.1) + " 원";
         rowData[5] = String.format("%,.1f",smDTO.getPrice() * 0.9) + " 원";
         rowData[6] = smDTO.getOrder_date();
         dtmSettlement.addRow(rowData);
         totalSell += smDTO.getPrice();
         totalProfit +=  smDTO.getPrice() * 0.9;
         totalCount++;
      }//end for
      setTotalSummary();
   }//searchSettleInfo
   
   //중간 부분에 있는 총 판매 현황 요약 부분 정보 셋팅
   public void setTotalSummary() {
	 String inputPeriod = smd.getJtfStartPeriod().getText() + " ~ " + smd.getJtfEndPeriod().getText();
	 smd.getJlbMdPeriod().setText(inputPeriod);
	 smd.getJlbSell().setText("총 판매 금액 : " + String.format("%,d",totalSell) + " 원");
	 smd.getJlbProfit().setText("총 정산액(순이익) : " + String.format("%,.2f",totalProfit) + " 원");
	 smd.getJlbCount().setText("총 판매 건 수 : " + totalCount + " 건");
	   totalSell = 0;
	   totalProfit = 0;
	   totalCount = 0;
   }//setTotalSummary
   
   //리셋 버튼을 누르면 설정 옵션들을 기본 상태로 되돌림
   public void setReset() {
	   smd.getJtfStartPeriod().setText(String.valueOf(smd.getLdEndPo()));
	   smd.getJtfEndPeriod().setText(String.valueOf(smd.getLdStartPo()));
	   smd.getJcbdeleveryState().setSelectedIndex(0);
	   smd.getJcbcarName().setSelectedIndex(0);
	   smd.getJcbOil().setSelectedIndex(0);
	   smd.getJcbQuarterlySettlement().setSelectedIndex(0);
	   smd.getYearSpinner().setValue(2025);
	   if (dtmSettlement != null) {
		   dtmSettlement.setRowCount(0);
	   };
	   setTotalSummary();
   }// setReset
   
   @Override
   public void actionPerformed(ActionEvent ae) {
      //JSpinal값 가져오기
      
      //분기별 드롭박스 누르면 카드레이아웃으로 테이블 변경
      if(smd.getJcbQuarterlySettlement() == ae.getSource()) {
         changeCardLayout();
      }//end if
      //검색버튼 누르면 검색 결과 찾아서 테이블에 추가
      if(smd.getJbtnSearch() == ae.getSource()) {
         searchPeriodOption(smd.getJtfStartPeriod().getText(), smd.getJtfEndPeriod().getText(), smd.getJcbdeleveryState().getSelectedItem().toString(), 
               smd.getJcbcarName().getSelectedItem().toString(), smd.getJcbOil().getSelectedItem().toString()); ;
      }//end if
      //리셋버튼 누르면 검색 조건 및 테이블 값 리셋
      if(smd.getJbtnReset() == ae.getSource()) {
    	  setReset();
      }//end if
   }//end actionPerformed
   
   
   /**
    * 분기별 드롭박스 누르면 카드레이아웃으로 테이블 변경해주기
    */
   public void changeCardLayout() {
      if (smd.getJcbQuarterlySettlement().getSelectedItem().equals("분기별 판매현황")) {
         smd.getCardLayout().show(smd.getJpCardPanel(), "cardSettlement");
      } else {
         smd.getCardLayout().show(smd.getJpCardPanel(), "cardMonthly");
      }//end if
   }//changeCardLayout
   @Override
   public void windowClosing(WindowEvent e) {
      smd.dispose();
   }//windowClosing
   
   
   
   
}//end class
