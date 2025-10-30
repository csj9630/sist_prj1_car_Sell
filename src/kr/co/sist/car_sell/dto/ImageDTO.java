package kr.co.sist.car_sell.dto; // 패키지 경로 확인

import java.io.File;
import java.sql.Date;

public class ImageDTO {
    	

	private int image_code, product_code;
    private String image_name; // 이미지 경로
    private Date imageadd_date;
    private File file;

    // 기본 생성자
    public ImageDTO() {}
    
    
	@Override
	public String toString() {
		return "ImageDTO [image_code=" + image_code + ", product_code=" + product_code + ", image_name=" + image_name
				+ ", imageadd_date=" + imageadd_date + "]";
	}
  

	// --- Getters and Setters ---
    public int getProduct_code() {return product_code;}
    public void setProduct_code(int product_code) {	this.product_code = product_code;}
    public int getImage_code() { return image_code; }
    public void setImage_code(int image_code) { this.image_code = image_code; }
    public String getImage_name() { return image_name; }
    public void setImage_name(String image_name) { this.image_name = image_name; }
    public Date getImageadd_date() { return imageadd_date; }
    public void setImageadd_date(Date imageadd_date) { this.imageadd_date = imageadd_date; }
    public File getFile() {	return file;}
	public void setFile(File file) {this.file = file;	}

    // --- Getters and Setters 끝 ---
}