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

insert into USER_INFO(USER_CODE, ID, PASS, name, EMAIL, TEL,
ADDRESS, GENERATE_DATE, STATUS_ACTIVATE)
values(SEQ_USER_INFO.nextval, 'testId2', 'user2', '박민수',
'tmujkl@imgfree.com', '010-3432-9787', '수원시 영통구', sysdate, 'Y');

insert into USER_INFO(USER_CODE, ID, PASS, name, EMAIL, TEL,
ADDRESS, GENERATE_DATE, STATUS_ACTIVATE)
values(SEQ_USER_INFO.nextval, 'testId3', 'user3', '김나은',
'mta5jkud@concu.net', '010-9879-6755', '부천시 양남구', sysdate, 'Y');

insert into USER_INFO(USER_CODE, ID, PASS, name, EMAIL, TEL,
ADDRESS, GENERATE_DATE, STATUS_ACTIVATE)
values(SEQ_USER_INFO.nextval, 'testId4', 'user4', '성진우',
'az8bhvf@cdnripple.com', '010-7765-1158', '성남시 자켈구', sysdate, 'Y');

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
	product_name varchar2(100)--csv,exerd에 맞게 추가함.
	price number(30), -- 가격,
	car_year date, -- 연식,
	cc number(30) , -- 배기량,
	distance number(30), -- 주행거리,
	registration_number varchar2(20), -- 차량번호(10->20)
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









CREATE TABLE CAR_TEMP_STAGE (
    product_code VARCHAR2(10),
    product_name VARCHAR2(100),
    brand VARCHAR2(50),
    price VARCHAR2(30),
    car_year VARCHAR2(10),
    cc VARCHAR2(10),
    distance VARCHAR2(30),
    registration_number VARCHAR2(20),
    options VARCHAR2(255),  -- 'option'은 예약어일 수 있어 'options'로 변경
    car_img VARCHAR2(255),
    status_sold VARCHAR2(20),
    car_name VARCHAR2(50),
    oil VARCHAR2(20)
);

select * from car_temp_stage
order by product_code;

select * from oil;

-- [1단계: CAR_INFO를 참조하는 모든 자식 테이블 삭제]
-- (순서는 상관없습니다)
DELETE FROM ORDER_HISTORY;
DELETE FROM CAR_DEFECT;
DELETE FROM CAR_OPTION;
DELETE FROM CAR_IMAGE;

-- [2단계: 부모 테이블 삭제]
-- 이제 자식들이 모두 사라졌으므로 CAR_INFO를 삭제할 수 있습니다.
DELETE FROM CAR_INFO;
DROP TABLE CAR_INFO CASCADE CONSTRAINTS;

-- 1. [수정됨] CAR_INFO 테이블을 올바르게 다시 생성
create table CAR_INFO (
	product_code number(10)
	constraint PK_CAR_INFO primary key,
	product_name varchar2(100), -- <--- 쉼표(,) 추가됨
	price number(30),
	car_year date,
	cc number(30),
	distance number(30),
	registration_number varchar2(20),
	status_sold varchar2(15) not null,
	car_name varchar2(30)
	constraint FK_CAR_INFO_CAR_NAME references BRAND(CAR_NAME),
	oil varchar2(15)
	constraint FK_CAR_INFO_OIL references OIL(OIL)
);

-- 2. CAR_INFO 테이블로 데이터 삽입
INSERT INTO CAR_INFO (
    product_code,
    product_name,
    price,
    car_year,
    cc,
    distance,
    registration_number,
    status_sold,
    car_name,
    oil
)
SELECT
    TO_NUMBER(product_code),
    product_name,
    TO_NUMBER(price),
    TO_DATE(car_year || '-01-01', 'YYYY-MM-DD'),
    TO_NUMBER(cc),
    TO_NUMBER(distance),
    registration_number,
    status_sold,
    car_name,
    oil
FROM CAR_TEMP_STAGE;

-- 3. 최종 저장
COMMIT;

SELECT COUNT(*) FROM CAR_INFO; -- 50.
SELECT * FROM CAR_INFO ORDER BY product_code;

-- 1. OPTION_TABLE용 시퀀스 생성
CREATE SEQUENCE SEQ_OPTION_TABLE
START WITH 1
INCREMENT BY 1;

-- 2. IMAGE 테이블용 시퀀스 생성
CREATE SEQUENCE SEQ_IMAGE
START WITH 1
INCREMENT BY 1;

-- 3. 기존 테스트 데이터 삭제
DELETE FROM CAR_OPTION;
DELETE FROM OPTION_TABLE;
DELETE FROM CAR_IMAGE;
DELETE FROM IMAGE;



INSERT INTO OPTION_TABLE (option_code, option_name)
SELECT
    SEQ_OPTION_TABLE.NEXTVAL, -- 1, 2, 3...
    option_name
FROM (
    SELECT DISTINCT
           TRIM(REGEXP_SUBSTR(options, '[^|]+', 1, LEVEL)) AS option_name
    FROM CAR_TEMP_STAGE
    WHERE options IS NOT NULL
    CONNECT BY LEVEL <= REGEXP_COUNT(options, '\|') + 1 -- '|' 개수 + 1 만큼 반복
           AND PRIOR product_code = product_code -- 차량별로 묶어서 처리
           AND PRIOR SYS_GUID() IS NOT NULL -- CONNECT BY 무한루프 방지
)
WHERE option_name IS NOT NULL; -- 쪼갰을 때 빈 값(null)이 나오면 제외



INSERT INTO CAR_OPTION (option_code, product_code)
SELECT
    opt.option_code,
    spl.product_code
FROM (
    -- -----------------------------------------------------------------
    -- (시작) 이 부분이 CAR_TEMP_STAGE의 options 열을 쪼개는 하위 쿼리입니다.
    -- -----------------------------------------------------------------
    SELECT
        TO_NUMBER(product_code) AS product_code,
        TRIM(REGEXP_SUBSTR(options, '[^|]+', 1, LEVEL)) AS option_name
    FROM CAR_TEMP_STAGE
    WHERE options IS NOT NULL
    CONNECT BY LEVEL <= REGEXP_COUNT(options, '\|') + 1
           AND PRIOR product_code = product_code
           AND PRIOR SYS_GUID() IS NOT NULL
    -- -----------------------------------------------------------------
    -- (끝) 하위 쿼리
    -- -----------------------------------------------------------------
) spl
JOIN OPTION_TABLE opt ON spl.option_name = opt.option_name;



-- 1. IMAGE 마스터 테이블 삽입
INSERT INTO IMAGE (image_code, image_name)
SELECT
    SEQ_IMAGE.NEXTVAL,
    car_img
FROM (
    SELECT DISTINCT car_img
    FROM CAR_TEMP_STAGE
    WHERE car_img IS NOT NULL
);

-- 2. CAR_IMAGE 매핑 테이블 삽입
INSERT INTO CAR_IMAGE (image_code, product_code)
SELECT
    img.image_code,
    TO_NUMBER(stg.product_code)
FROM CAR_TEMP_STAGE stg
JOIN IMAGE img ON stg.car_img = img.image_name
WHERE stg.car_img IS NOT NULL;



-- 1. 최종 저장
COMMIT;

-- 2. 데이터 확인
SELECT * FROM OPTION_TABLE;
SELECT * FROM CAR_OPTION ORDER BY product_code;
SELECT * FROM IMAGE;
SELECT * FROM CAR_IMAGE ORDER BY product_code;
select* from car_info;

-- 3. (선택) 임시 테이블 삭제
select * from car_temp_stage;
DROP TABLE CAR_TEMP_STAGE;
select * from BRAND;
select * from CAR_DEFECT;
select * from DEFECT; --임시 데이터
