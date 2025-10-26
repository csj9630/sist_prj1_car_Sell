-- 카드 정보
drop table CARD_INFO;
-- 주문 내역
drop table ORDER_HISTORY;
-- 사용자 정보 테이블 삭제
drop table USER_INFO ;
--차량하자 테이블 삭제
drop table CAR_DEFECT;
--하자 테이블 삭제
drop table DEFECT;
--차량옵션 테이블 삭제
drop table CAR_OPTION;
--옵션 테이블 삭제
drop table OPTION_TABLE;
--차량이미지 테이블 삭제
drop table CAR_IMAGE;
--이미지 테이블 삭제
drop table IMAGE;
-- 자동차 정보 테이블 삭제
drop table  CAR_INFO;
-- 유종 테이블 삭제
drop table OIL;
-- 제조사 테이블 삭제
drop table BRAND;

-- 관리자 정보
drop TABLE ADMIN;






/*이미지 시퀀스 삭제*/
drop sequence SEQ_USER_INFO;

/*회원 시퀀스를 생성*/
create sequence SEQ_USER_INFO
increment by 1
start with 1
maxvalue 999;

-- 사용자 정보
create table USER_INFO (
	user_code number(10)
	constraint PK_USER_INFO primary key,-- 회원번호,
	id varchar2(30) not null, -- 아이디,
	pass varchar2(30) not null, -- 비밀번호,
	name varchar2(30) not null, -- 이름,
	email varchar2(30) not null, -- 이메일,
	tel varchar2(30) not null, -- 전화번호,
	address varchar2(300) not null, -- 주소,
	generate_date date not null, -- 계정 생성 일자,
	status_activate char(15) not null -- 활성화 여부
);

-- 사용자 정보 테스트 데이터 삽입
insert into USER_INFO(USER_CODE, ID, PASS, name, EMAIL, TEL,
ADDRESS, GENERATE_DATE, STATUS_ACTIVATE)
values(SEQ_USER_INFO.nextval, 'testId', '1234', '이정우',
'lee@test.com', '010-1111-2222', '서울시 강남구', sysdate, 'Y');

--사용자 정보 조회
--SELECT * FROM USER_INFO;

--유종
create table OIL(
	OIL varchar2(15)
	constraint PK_OIL primary key);

--유종 테스트 데이터 삽입
insert into OIL(OIL) values('경유');

--유종 정보 정보 조회
--SELECT * FROM OIL;

-- 제조사
create table BRAND(
	CAR_NAME varchar2(30)
	constraint PK_BRAND primary key,
	BRAND varchar2(30));

-- 제조사 테스트 데이터 삽입
insert into BRAND( CAR_NAME, BRAND )
values('K5','기아');

--제조사 정보 정보 조회
--SELECT * FROM BRAND;

-- 하자
create table DEFECT (
	defect_code NUMBER(10) not null
	constraint PK_DEFECT primary key,-- 하자코드,
	problem_history varchar2(300),  -- 문제내역,
	defect_type varchar2(300),  -- 타입,
	defectadd_date date default sysdate -- 추가일자
);

--하자 테스트 데이터 삽입
insert into DEFECT(DEFECT_CODE, PROBLEM_HISTORY, DEFECT_TYPE)
values(1,'침수차', '침수차2');

--하자 정보 조회
--SELECT * FROM DEFECT;

-- 자동차 정보
create table CAR_INFO (
	product_code number(10)
	constraint PK_CAR_INFO primary key,-- 차량코드,
	price number(30), -- 가격,
	car_year date, -- 연식,
	cc number(30) , -- 배기량,
	distance number(30), -- 주행거리,
	registration_number varchar2(10), -- 차량번호,
	status_sold varchar2(15) not null, -- 판매여부,
	car_name varchar2(30)
	constraint FK_CAR_INFO_CAR_NAME references BRAND(CAR_NAME),-- 차종,
	oil varchar2(15)
	constraint FK_CAR_INFO_OIL references OIL(OIL)-- 유종
);

--자동차 정보 테스트 데이터 삽입
insert into CAR_INFO(PRODUCT_CODE, PRICE, CAR_YEAR,
CC, DISTANCE, REGISTRATION_NUMBER, STATUS_SOLD,
CAR_NAME, OIL)
values(001,300000, sysdate, 300, 1500000, '15가-나'
, '판매완료','K5', '경유');

--자동차 정보 정보 조회
--SELECT * FROM CAR_INFO;

-- 차량하자
create table CAR_DEFECT (
	defect_code number(10) not null
	constraint FK_CAR_DEFECT_defect_code references DEFECT(defect_code), -- 하자코드,
	product_code number(10) not null
	constraint FK_CAR_DEFECT_product_code REFERENCES CAR_INFO(product_code), -- 차량코드,
	processing_status varchar2(15) null, -- 처리여부
	processing_date date default sysdate, -- 처리일자
  constraint PK_CAR_DEFECT primary key(defect_code, product_code)
);

--차량하자 테스트 데이터 삽입
insert into CAR_DEFECT(DEFECT_CODE, PRODUCT_CODE, PROCESSING_STATUS, PROCESSING_DATE)
values(1,1,'처리완2',null);

--차량하자 정보 조회
--SELECT * FROM CAR_DEFECT;

-- 옵션
create table OPTION_TABLE (
	option_code number(10) constraint PK_OPTION_TABLE primary key,-- 옵션코드,
	option_name varchar2(300), -- 옵션명,
	optionadd_date date default sysdate-- 추가일
);

--옵션 테스트 데이터 삽입
insert into OPTION_TABLE(OPTION_CODE, OPTION_NAME, OPTIONADD_DATE)
values(1, '히터', sysdate);

--옵션 정보 조회
--SELECT * FROM OPTION_TABLE;

-- 차량옵션
create table CAR_OPTION (
	option_code number(10) not null
	constraint FK_CAR_OPTION_option_code references OPTION_TABLE(option_code), -- 옵션코드,
	product_code number(10) not null
	constraint FK_CAR_OPTION_product_code references CAR_INFO(product_code), -- 차량코드
	constraint PK_CAR_OPTION primary key(option_code,product_code)
);

--차량옵션 테스트 데이터 삽입
insert into CAR_OPTION(OPTION_CODE, PRODUCT_CODE)
values(1,1);

--차량옵션 정보 조회
--SELECT * FROM CAR_OPTION;

--이미지
create table IMAGE(
 image_code number(10) not null
 constraint PK_IMAGE primary key,
 image_name varchar2(300),
 imageadd_date date default sysdate
 );

--이미지 테스트 데이터 삽입
insert into IMAGE(IMAGE_CODE, IMAGE_NAME)
values (1, '집가고싶다');

--이미지 정보 조회
--SELECT * FROM IMAGE;

--차량이미지
create table CAR_IMAGE(
 image_code number(10) not null
 constraint FK_CAR_IMAGE_image_code references IMAGE(image_code),
 product_code number(10) not null
 constraint FK_CAR_IMAGE_product_code references CAR_INFO(product_code),
 constraint PK_CAR_IMAGE primary key(image_code,product_code)
 );

--차량이미지 테스트 데이터 삽입
insert into CAR_IMAGE(IMAGE_CODE, PRODUCT_CODE)
values(1,1);

--차량이미지 정보 조회
--SELECT * FROM CAR_IMAGE;


-- 주문 내역
create table ORDER_HISTORY (
	payment_code number(10) constraint PK_ORDER_HISTORY primary key, -- 주문번호,
	order_date date default sysdate, -- 주문일자,
	delivery_state varchar2(15), -- 탁송 상태,
	product_code number(10) constraint fk_order_history_product_code references car_info(product_code), -- 차량코드,
	user_code number(10) constraint fk_order_history_user_code references user_info(user_code)-- 회원번호
);

insert into ORDER_HISTORY(payment_code, order_date, delivery_state,
product_code, user_code)
values(1, sysdate, '배송중', 1, 1);

select * from ORDER_HISTORY;


-- 카드 정보
create table CARD_INFO (
	user_code number(10) constraint pk_card_info primary key
	constraint fk_card_info references user_info(user_code), -- 회원번호,
	credit_card varchar2(20) not null, -- 카드번호,
	registration_date date default sysdate -- 등록일
);

/*
이미 회원가입된 사용자의 카드 번호를 생성하는 과정이므로
user_code는 수동삽입
*/
-- 카드 정보 테스트 데이터 삽입(수동 삽입)
insert into CARD_INFO(user_code, credit_card, registration_date)
values(1, 'xxx-xxxx-xxxx', sysdate);

select * from CARD_INFO;

-- 관리자 정보
create table admin (
	admin_id varchar2(30) constraint PK_ADMIN primary key,-- 아이디,
	admin_pass varchar2(30) null, -- 비밀번호,
	admin_address varchar2(300) null, -- 주소,
	admin_contact varchar2(30) null, -- 연락처,
	admin_fax varchar2(30) null, -- FAX,
	adminadd_date date default sysdate-- 추가일자
);

-- 관리자 정보 테스트 데이터 삽입
insert into admin(admin_id, admin_pass, admin_address, admin_contact, admin_fax, adminadd_date)
values('min', 'test1234', 'min@test.com', '010-1234-5678', '0504-XXXX-XXXX', sysdate);

select * from admin;
