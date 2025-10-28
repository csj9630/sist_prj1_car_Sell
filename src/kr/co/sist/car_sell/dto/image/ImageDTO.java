package kr.co.sist.car_sell.dto.image; // 패키지 경로 확인

import java.sql.Date;

public class ImageDTO {
    private int image_code;
    private String image_name; // 이미지 경로
    private Date imageadd_date;

    // 기본 생성자
    public ImageDTO() {}
    
    @Override
	public String toString() {
		return "ImageDTO [image_name=" + image_name + "]";
	}

	// --- Getters and Setters ---
    public int getImage_code() { return image_code; }
    public void setImage_code(int image_code) { this.image_code = image_code; }
    public String getImage_name() { return image_name; }
    public void setImage_name(String image_name) { this.image_name = image_name; }
    public Date getImageadd_date() { return imageadd_date; }
    public void setImageadd_date(Date imageadd_date) { this.imageadd_date = imageadd_date; }
    // --- Getters and Setters 끝 ---
}