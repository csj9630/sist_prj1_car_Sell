package kr.co.sist.car_sell.dto;

import java.sql.Date;
import java.util.List;

public class CarDTO {
	
	private int prodCode, price, cc, distance;
	private String prodName, regNum, soldStat, carName, oil, brandName;
	private Date carYear;
	private List<Integer> optionCodes, defectCodes, accidentCodes, repairCodes;
	
	public CarDTO() {
		
	} // CarInfoDTO

	public CarDTO(int prodCode, int price, int cc, int distance, String prodName, String regNum, String soldStat,
			String carName, String oil, String brandName, Date carYear) {
		super();
		this.prodCode = prodCode;
		this.price = price;
		this.cc = cc;
		this.distance = distance;
		this.prodName = prodName;
		this.regNum = regNum;
		this.soldStat = soldStat;
		this.carName = carName;
		this.oil = oil;
		this.brandName = brandName;
		this.carYear = carYear;
	} // CarInfoDTO
	
	public void setProdCode(int prodCode) {
		this.prodCode = prodCode;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setCc(int cc) {
		this.cc = cc;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	
	public void setSoldStat(String soldStat) {
		this.soldStat = soldStat;
	}
	
	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	public void setOil(String oil) {
		this.oil = oil;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public void setCarYear(Date carYear) {
		this.carYear = carYear;
	}
	
	public void setOptionCodes(List<Integer> optionCodes) {
        this.optionCodes = optionCodes;
    }
	
	public void setDefectCodes(List<Integer> defectCodes) {
		this.defectCodes = defectCodes;
	}
	
	public void setAccidentCodes(List<Integer> accidentCodes) {
		this.accidentCodes = accidentCodes;
	}
	
	public void setRepairCodes(List<Integer> repairCodes) {
		this.repairCodes = repairCodes;
	}
	
	public int getProdCode() {
		return prodCode;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getCc() {
		return cc;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public String getProdName() {
		return prodName;
	}
	
	public String getRegNum() {
		return regNum;
	}
	
	public String getSoldStat() {
		return soldStat;
	}
	
	public String getCarName() {
		return carName;
	}
	
	public String getOil() {
		return oil;
	}
	
	public String getBrandName() {
		return brandName;
	}
	
	public Date getCarYear() {
		return carYear;
	}
	
	public List<Integer> getOptionCodes() {
        return optionCodes;
    }
	
	public List<Integer> getDefectCodes() {
		return defectCodes;
	}
	
	public List<Integer> getAccidentCodes() {
		return accidentCodes;
	}
	
	public List<Integer> getRepairCodes() {
		return repairCodes;
	}
	
	@Override
	public String toString() {
		return "CarDTO [prodCode=" + prodCode + ", price=" + price + ", cc=" + cc + ", distance=" + distance
				+ ", prodName=" + prodName + ", regNum=" + regNum + ", soldStat=" + soldStat + ", carName=" + carName
				+ ", oil=" + oil + ", brandName=" + brandName + ", carYear=" + carYear + "]";
	}
	
} // class
