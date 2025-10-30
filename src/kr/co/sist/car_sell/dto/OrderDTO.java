package kr.co.sist.car_sell.dto; // 패키지 경로 확인

public class OrderDTO {
    private int payment_code; // DB에서 생성됨
    private int product_code; // 주문 대상 차량
    private int user_code;    // 주문한 사용자
    // (필요 시 order_date, delivery_state 등 추가 가능)

    // 기본 생성자
    public OrderDTO() {}

    // --- Getters and Setters ---
    public int getPayment_code() { return payment_code; }
    public void setPayment_code(int payment_code) { this.payment_code = payment_code; }
    public int getProduct_code() { return product_code; }
    public void setProduct_code(int product_code) { this.product_code = product_code; }
    public int getUser_code() { return user_code; }
    public void setUser_code(int user_code) { this.user_code = user_code; }
    // --- Getters and Setters 끝 ---
}