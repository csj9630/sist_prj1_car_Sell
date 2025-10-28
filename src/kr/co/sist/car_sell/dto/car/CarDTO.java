package kr.co.sist.car_sell.dto.car; // 패키지 경로 확인

import java.sql.Date;

public class CarDTO {
    private int product_code;
    private String product_name;
    private int price;
    private Date car_year;
    private int cc;
    private int distance;
    private String registration_number;
    private String status_sold;
    private String car_name;
    private String oil;
    private String brand_name; // F5 스크립트 수정 기준

    // 기본 생성자
    public CarDTO() {}

    // --- Getters and Setters ---
    public int getProduct_code() { return product_code; }
    public void setProduct_code(int product_code) { this.product_code = product_code; }
    public String getProduct_name() { return product_name; }
    public void setProduct_name(String product_name) { this.product_name = product_name; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public Date getCar_year() { return car_year; }
    public void setCar_year(Date car_year) { this.car_year = car_year; }
    public int getCc() { return cc; }
    public void setCc(int cc) { this.cc = cc; }
    public int getDistance() { return distance; }
    public void setDistance(int distance) { this.distance = distance; }
    public String getRegistration_number() { return registration_number; }
    public void setRegistration_number(String registration_number) { this.registration_number = registration_number; }
    public String getStatus_sold() { return status_sold; }
    public void setStatus_sold(String status_sold) { this.status_sold = status_sold; }
    public String getCar_name() { return car_name; }
    public void setCar_name(String car_name) { this.car_name = car_name; }
    public String getOil() { return oil; }
    public void setOil(String oil) { this.oil = oil; }
    public String getBrand_name() { return brand_name; }
    public void setBrand_name(String brand_name) { this.brand_name = brand_name; }
    // --- Getters and Setters 끝 ---
}