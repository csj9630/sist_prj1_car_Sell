SELECT * FROM CAR_INFO;
SELECT * FROM BRAND;
SELECT * FROM OIL;

-- 사용자 정보
DROP TABLE USER_INFO;

-- 자동차 정보
DROP TABLE  CAR_INFO;

-- 제조사
DROP TABLE BRAND;

-- 유종
DROP TABLE OIL;

/*회원 시퀀스 삭제*/
drop sequence SEQ_USER_INFO;

/*회원 시퀀스를 생성*/
create sequence SEQ_USER_INFO
increment by 1
start with 1
maxvalue 999;

-- 사용자 정보
CREATE TABLE USER_INFO (
	user_code number(10) CONSTRAINT PK_USER_INFO PRIMARY KEY,-- 회원번호,
	id VARCHAR2(30) NOT NULL, -- 아이디,
	pass VARCHAR2(30) NOT NULL, -- 비밀번호,
	name VARCHAR2(30) NOT NULL, -- 이름,
	email VARCHAR2(30) NOT NULL, -- 이메일,
	tel VARCHAR2(30) NOT NULL, -- 전화번호,
	address VARCHAR2(300) NOT NULL, -- 주소,
	generate_date DATE NOT NULL, -- 계정 생성 일자,
	status_activate CHAR(15) NOT NULL -- 활성화 여부
);

-- 사용자 정보 테스트 데이터 삽입
insert into USER_INFO(USER_CODE, ID, PASS, NAME, EMAIL, TEL, ADDRESS, GENERATE_DATE, STATUS_ACTIVATE)
values(SEQ_USER_INFO.NEXTVAL, 'testId', '1234', '이정우', 'lee@test.com', '010-1111-2222', '서울시 강남구'
, sysdate, 'Y');

-- 제조사
CREATE TABLE BRAND(
	CAR_NAME VARCHAR2(30) CONSTRAINT PK_BRAND PRIMARY KEY,
	BRAND VARCHAR2(30));

-- 제조사 테스트 데이터 삽입
INSERT INTO BRAND( CAR_NAME, BRAND )
VALUES('K5','기아');

--유종
CREATE TABLE OIL(
	OIL VARCHAR2(15) CONSTRAINT PK_OIL PRIMARY KEY);

--유종 테스트 데이터 삽입
INSERT INTO OIL(OIL) VALUES('경유');

-- 자동차 정보
CREATE TABLE CAR_INFO (
	product_code number(10) CONSTRAINT PK_CAR_INFO PRIMARY KEY,-- 차량코드,
	product_name VARCHAR2(30), -- 차량명,
	brand VARCHAR2(30), -- 브랜드명,
	price number(30), -- 가격,
	car_year DATE, -- 연식,
	cc number(30) , -- 배기량,
	distance number(30), -- 주행거리,
	registration_number VARCHAR2(10), -- 차량번호,
	car_option VARCHAR2(300), -- 옵션,
	car_img BLOB, -- 차량이미지,
	status_sold VARCHAR2(15) NOT NULL, -- 판매여부,
	car_name VARCHAR2(30)  CONSTRAINT FK_CAR_INFO_CAR_NAME REFERENCES BRAND(CAR_NAME),-- 차종,
	oil VARCHAR2(15)  CONSTRAINT FK_CAR_INFO_OIL REFERENCES OIL(OIL)-- 유종
);

--자동차 정보 테스트 데이터 삽입
INSERT INTO CAR_INFO(PRODUCT_CODE, PRODUCT_NAME, BRAND, PRICE, CAR_YEAR,
CC, DISTANCE, REGISTRATION_NUMBER, CAR_OPTION, CAR_IMG, STATUS_SOLD,
CAR_NAME, OIL)
VALUES(001,'K5','기아',300000, SYSDATE, 300, 1500000, '15가-나',
'온열시트,냉풍기',NULL , '판매완료','K5', '경유');



-- 옵션
CREATE TABLE OPTION_TABLE (
	option_code number(10) CONSTRAINT PK_OPTION_TABLE PRIMARY KEY,-- 옵션코드,
	option_name VARCHAR2(300), -- 옵션명,
	optionadd_date DATE -- 추가일
);

--------------여까정

-- 차량옵션
CREATE TABLE CAR_OPTION (
	option_code number(10) NOT NULL -- 옵션코드,
	product_code number(10) NOT NULL -- 차량코드
);

-- 차량옵션
ALTER TABLE CAR_OPTION
	ADD CONSTRAINT PK_CAR_OPTION -- 차량옵션 기본키
	PRIMARY KEY (
		option_code,  -- 옵션코드
		product_code  -- 차량코드
	);

-- 하자
CREATE TABLE DEFECT (
	defect_code number(10) NOT NULL -- 하자코드,
	problem_history VARCHAR2(300) NULL -- 문제내역,
	defect_type VARCHAR2(300) NULL -- 타입,
	defectadd_date DATE NULL -- 추가일자
);

-- 하자
ALTER TABLE DEFECT
	ADD CONSTRAINT PK_DEFECT -- 하자 기본키
	PRIMARY KEY (
		defect_code -- 하자코드
	);

-- 차량하자
CREATE TABLE CAR_DEFECT (
	defect_code number(10) NOT NULL -- 하자코드,
	product_code number(10) NOT NULL -- 차량코드,
	processing_status VARCHAR2(15) NULL -- 처리여부,
	processing_date DATE NULL -- 처리일자
);

-- 차량하자
ALTER TABLE CAR_DEFECT
	ADD CONSTRAINT PK_CAR_DEFECT -- 차량하자 기본키
	PRIMARY KEY (
		defect_code,  -- 하자코드
		product_code  -- 차량코드
	);

-- 자동차 정보
ALTER TABLE CAR_INFO
	ADD CONSTRAINT FK_BRAND_TO_CAR_INFO -- 제조사 -> 자동차 정보
	FOREIGN KEY (
		car_name -- 차종
	)
	REFERENCES BRAND ( -- 제조사
		car_name -- 차종
	);

-- 자동차 정보
ALTER TABLE CAR_INFO
	ADD CONSTRAINT FK_OIL_TO_CAR_INFO -- 유종 -> 자동차 정보
	FOREIGN KEY (
		oil -- 유종
	)
	REFERENCES OIL ( -- 유종
		oil -- 유종
	);

-- 주문 내역
ALTER TABLE ORDER_HISTORY
	ADD CONSTRAINT FK_CAR_INFO_TO_ORDER_HISTORY -- 자동차 정보 -> 주문 내역
	FOREIGN KEY (
		product_code -- 차량코드
	)
	REFERENCES CAR_INFO ( -- 자동차 정보
		product_code -- 차량코드
	);

-- 주문 내역
ALTER TABLE ORDER_HISTORY
	ADD CONSTRAINT FK_USER_INFO_TO_ORDER_HISTORY -- 사용자 정보 -> 주문 내역
	FOREIGN KEY (
		user_code -- 회원번호
	)
	REFERENCES USER_INFO ( -- 사용자 정보
		user_code -- 회원번호
	);

-- 임시 테이블
ALTER TABLE 권한
	ADD CONSTRAINT FK_USER_INFO_TO_권한 -- 사용자 정보 -> 임시 테이블
	FOREIGN KEY (
		user_code -- 회원번호
	)
	REFERENCES USER_INFO ( -- 사용자 정보
		user_code -- 회원번호
	);

-- 카드 정보
ALTER TABLE CARD_INFO
	ADD CONSTRAINT FK_USER_INFO_TO_CARD_INFO -- 사용자 정보 -> 카드 정보
	FOREIGN KEY (
		user_code -- 회원번호
	)
	REFERENCES USER_INFO ( -- 사용자 정보
		user_code -- 회원번호
	);

-- 임시 테이블3
ALTER TABLE 차량내역
	ADD CONSTRAINT FK_CAR_INFO_TO_차량내역 -- 자동차 정보 -> 임시 테이블3
	FOREIGN KEY (
		product_code -- 차량코드
	)
	REFERENCES CAR_INFO ( -- 자동차 정보
		product_code -- 차량코드
	);

-- 차량옵션
ALTER TABLE CAR_OPTION
	ADD CONSTRAINT FK_OPTION_TO_CAR_OPTION -- 옵션 -> 차량옵션
	FOREIGN KEY (
		option_code -- 옵션코드
	)
	REFERENCES OPTION ( -- 옵션
		option_code -- 옵션코드
	);

-- 차량옵션
ALTER TABLE CAR_OPTION
	ADD CONSTRAINT FK_CAR_INFO_TO_CAR_OPTION -- 자동차 정보 -> 차량옵션
	FOREIGN KEY (
		product_code -- 차량코드
	)
	REFERENCES CAR_INFO ( -- 자동차 정보
		product_code -- 차량코드
	);

-- 차량하자
ALTER TABLE CAR_DEFECT
	ADD CONSTRAINT FK_DEFECT_TO_CAR_DEFECT -- 하자 -> 차량하자
	FOREIGN KEY (
		defect_code -- 하자코드
	)
	REFERENCES DEFECT ( -- 하자
		defect_code -- 하자코드
	);

-- 차량하자
ALTER TABLE CAR_DEFECT
	ADD CONSTRAINT FK_CAR_INFO_TO_CAR_DEFECT -- 자동차 정보 -> 차량하자
	FOREIGN KEY (
		product_code -- 차량코드
	)
	REFERENCES CAR_INFO ( -- 자동차 정보
		product_code -- 차량코드
	);
