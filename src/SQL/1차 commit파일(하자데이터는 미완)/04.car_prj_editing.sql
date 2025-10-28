--CREATE TABLE CAR_TEMP_STAGE (
--    product_code VARCHAR2(10),
--    product_name VARCHAR2(100),
--    brand VARCHAR2(50),
--    price VARCHAR2(30),
--    car_year VARCHAR2(10),
--    cc VARCHAR2(10),
--    distance VARCHAR2(30),
--    registration_number VARCHAR2(20),
--    options VARCHAR2(255),  -- 'option'은 예약어일 수 있어 'options'로 변경
--    car_img VARCHAR2(255),
--    status_sold VARCHAR2(20),
--    car_name VARCHAR2(50),
--    oil VARCHAR2(20)
--);
--
--select * from car_temp_stage;

--CAR_TEMP_STAGE안만들었다면 주석풀고 만들고 이 테이블로 import후,
--데이터 잘 들어왔다면 다시 싹 다 주석하고 실행.
--------------------------------------------------------------------------------

-- 1. 모든 테이블, 시퀀스 삭제 (자식 -> 부모)

-- 자식 테이블 먼저 삭제
drop table CARD_INFO;
drop table ORDER_HISTORY;
drop table CAR_DEFECT;
drop table CAR_OPTION;
drop table CAR_IMAGE;

-- 부모 테이블 삭제
drop table USER_INFO;
drop table CAR_INFO;
drop table DEFECT;
drop table OPTION_TABLE;
drop table IMAGE;
drop table OIL;
drop table BRAND;
drop table ADMIN;

-- 시퀀스 삭제
drop sequence SEQ_USER_INFO;
drop sequence SEQ_OPTION_TABLE;
drop sequence SEQ_IMAGE;

--------------------------------------------------------------------------

-- 시퀀스 생성

create sequence SEQ_USER_INFO
increment by 1
start with 1
maxvalue 999;

create sequence SEQ_OPTION_TABLE
increment by 1
start with 1;

create sequence SEQ_IMAGE
increment by 1
start with 1;

--------------------------------------------------------------------------------

-- 테이블 생성 (부모 -> 자식)
-- 부모 테이블 생성
create table USER_INFO (
  user_code number(10)
  constraint PK_USER_INFO primary key,-- 회원번호
  id varchar2(30) not null, -- 아이디
  pass varchar2(30) not null, -- 비밀번호
  name varchar2(30) not null, -- 이름
  email varchar2(30) not null, -- 이메일
  tel varchar2(30) not null, -- 전화번호
  address varchar2(300) not null, -- 주소
  generate_date date not null, -- 계정 생성 일자
  status_activate char(15) not null -- 활성화 여부
);

create table OIL(
  OIL varchar2(15)
  constraint PK_OIL primary key
);

create table BRAND(
  CAR_NAME varchar2(30)
  constraint PK_BRAND primary key,
  BRAND varchar2(30)
);

create table DEFECT (
  defect_code NUMBER(10) not null
  constraint PK_DEFECT primary key,-- 하자코드
  problem_history varchar2(300),  -- 문제내역
  defect_type varchar2(300),  -- 타입
  defectadd_date date default sysdate -- 추가일자
);

create table OPTION_TABLE (
  option_code number(10) constraint PK_OPTION_TABLE primary key,-- 옵션코드
  option_name varchar2(300), -- 옵션명
  optionadd_date date default sysdate-- 추가일
);

create table IMAGE(
  image_code number(10) not null
  constraint PK_IMAGE primary key,
  image_name varchar2(300),
  imageadd_date date default sysdate
);

create table admin (
  admin_id varchar2(30) constraint PK_ADMIN primary key,-- 아이디
  admin_pass varchar2(30) null, -- 비밀번호
  admin_address varchar2(300) null, -- 주소
  admin_contact varchar2(30) null, -- 연락처
  admin_fax varchar2(30) null, -- FAX
  adminadd_date date default sysdate-- 추가일자
);

---------------------------------------------------------------------------

-- 자식 테이블 생성
create table CAR_INFO (
  product_code number(10)
  constraint PK_CAR_INFO primary key,-- 차량코드
  product_name varchar2(100), -- 차량명
  price number(30), -- 가격
  car_year date, -- 연식
  cc number(30) , -- 배기량
  distance number(30), -- 주행거리
  registration_number varchar2(20), -- 차량번호
  status_sold varchar2(15) not null, -- 판매여부
  car_name varchar2(30)
  constraint FK_CAR_INFO_CAR_NAME references BRAND(CAR_NAME),-- 차종
  oil varchar2(15)
  constraint FK_CAR_INFO_OIL references OIL(OIL)-- 유종
);

create table CAR_DEFECT (
  defect_code number(10) not null
  constraint FK_CAR_DEFECT_defect_code references DEFECT(defect_code), -- 하자코드
  product_code number(10) not null
  constraint FK_CAR_DEFECT_product_code REFERENCES CAR_INFO(product_code), -- 차량코드
  processing_status varchar2(15) null, -- 처리여부
  processing_date date default sysdate, -- 처리일자
    constraint PK_CAR_DEFECT primary key(defect_code, product_code)
);

create table CAR_OPTION (
  option_code number(10) not null
  constraint FK_CAR_OPTION_option_code references OPTION_TABLE(option_code), -- 옵션코드
  product_code number(10) not null
  constraint FK_CAR_OPTION_product_code references CAR_INFO(product_code), -- 차량코드
  constraint PK_CAR_OPTION primary key(option_code,product_code)
);

create table CAR_IMAGE(
  image_code number(10) not null
  constraint FK_CAR_IMAGE_image_code references IMAGE(image_code),
  product_code number(10) not null
  constraint FK_CAR_IMAGE_product_code references CAR_INFO(product_code),
  constraint PK_CAR_IMAGE primary key(image_code,product_code)
);

create table ORDER_HISTORY (
  payment_code number(10) constraint PK_ORDER_HISTORY primary key, -- 주문번호
  order_date date default sysdate, -- 주문일자
  delivery_state varchar2(15), -- 탁송 상태
  product_code number(10) constraint fk_order_history_product_code references car_info(product_code), -- 차량코드
  user_code number(10) constraint fk_order_history_user_code references user_info(user_code)-- 회원번호
);

create table CARD_INFO (
  user_code number(10) constraint pk_card_info primary key
  constraint fk_card_info references user_info(user_code), -- 회원번호
  credit_card varchar2(20) not null, -- 카드번호
  registration_date date default sysdate -- 등록일
);

-------------------------------------------------------------------------------------------------------------------------

--임시데이터 삽입
--(하자관련 데이터는 csv미완이므로 임시데이터)

-- 사용자 정보 테스트 데이터 삽입
insert into USER_INFO(USER_CODE, ID, PASS, name, EMAIL, TEL,
ADDRESS, GENERATE_DATE, STATUS_ACTIVATE)
values(SEQ_USER_INFO.nextval, 'testId', '1234', '이정우',
'lee@test.com', '010-1111-2222', '서울시 강남구', sysdate, 'Y');

-- 하자 테스트 데이터 삽입
insert into DEFECT(DEFECT_CODE, PROBLEM_HISTORY, DEFECT_TYPE)
values(1,'침수차', '침수차2');

-- 관리자 정보 테스트 데이터 삽입
insert into admin(admin_id, admin_pass, admin_address, admin_contact, admin_fax, adminadd_date)
values('min', 'test1234', 'min@test.com', '010-1234-5678', '0504-XXXX-XXXX', sysdate);

-------------------------------------------------------------------------------------------------------

-- 데이터 삽입
-- CAR_TEMP_STAGE에서 원래의 테이블로 데이터 옮기기

-- OIL 테이블 삽입 (부모)
INSERT INTO OIL (OIL)
SELECT DISTINCT oil
FROM CAR_TEMP_STAGE
WHERE oil IS NOT NULL;

-- BRAND 테이블 삽입 (부모)
INSERT INTO BRAND (CAR_NAME, BRAND)
SELECT DISTINCT car_name, brand
FROM CAR_TEMP_STAGE
WHERE car_name IS NOT NULL
  AND brand IS NOT NULL;

-- CAR_INFO 테이블 삽입 (자식)
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

-- OPTION_TABLE 삽입 (부모)
INSERT INTO OPTION_TABLE (option_code, option_name)
SELECT
    SEQ_OPTION_TABLE.NEXTVAL,
    option_name
FROM (
    SELECT DISTINCT
           TRIM(REGEXP_SUBSTR(options, '[^|]+', 1, LEVEL)) AS option_name
    FROM CAR_TEMP_STAGE
    WHERE options IS NOT NULL
    CONNECT BY LEVEL <= REGEXP_COUNT(options, '\|') + 1
           AND PRIOR product_code = product_code
           AND PRIOR SYS_GUID() IS NOT NULL
)
WHERE option_name IS NOT NULL;

-- CAR_OPTION 삽입 (자식, 매핑 테이블)

INSERT INTO CAR_OPTION (option_code, product_code)
SELECT
    opt.option_code,
    spl.product_code
FROM (
    SELECT
        TO_NUMBER(product_code) AS product_code,
        TRIM(REGEXP_SUBSTR(options, '[^|]+', 1, LEVEL)) AS option_name
    FROM CAR_TEMP_STAGE
    WHERE options IS NOT NULL
    CONNECT BY LEVEL <= REGEXP_COUNT(options, '\|') + 1
           AND PRIOR product_code = product_code
           AND PRIOR SYS_GUID() IS NOT NULL
) spl
JOIN OPTION_TABLE opt ON spl.option_name = opt.option_name;

-- IMAGE 삽입 (부모)
INSERT INTO IMAGE (image_code, image_name)
SELECT
    SEQ_IMAGE.NEXTVAL,
    car_img
FROM (
    SELECT DISTINCT car_img
    FROM CAR_TEMP_STAGE
    WHERE car_img IS NOT NULL
);

-- CAR_IMAGE 삽입 (자식, 매핑 테이블)
INSERT INTO CAR_IMAGE (image_code, product_code)
SELECT
    img.image_code,
    TO_NUMBER(stg.product_code)
FROM CAR_TEMP_STAGE stg
JOIN IMAGE img ON stg.car_img = img.image_name
WHERE stg.car_img IS NOT NULL;

----------------------------------------------------------------------------------------------

-- 데이터 삽입
--CSV 데이터와 연동되는 나머지 테스트 데이터 삽입
--product_code=1, user_code=1이 존재하므로 삽입 가능)

-- 차량하자 테스트 데이터 삽입
insert into CAR_DEFECT(DEFECT_CODE, PRODUCT_CODE, PROCESSING_STATUS, PROCESSING_DATE)
values(1,1,'처리완2',null); -- (defect_code 1번, product_code 1번)

-- 주문 내역 테스트 데이터 삽입
insert into ORDER_HISTORY(payment_code, order_date, delivery_state,
product_code, user_code)
values(1, sysdate, '배송중', 1, 1); -- (product_code 1번, user_code 1번)

-- 카드 정보 테스트 데이터 삽입
insert into CARD_INFO(user_code, credit_card, registration_date)
values(1, 'xxx-xxxx-xxxx', sysdate); -- (user_code 1번)

-------------------------------------------------------------------------------

COMMIT;


select * from CARD_INFO;
select * from BRAND;
select * from ADMIN;
select * from CAR_DEFECT;
select * from CAR_IMAGE;
select * from CAR_OPTION;
select * from DEFECT;
select * from IMAGE;
select * from OIL;
select * from OPTION_TABLE;
select * from ORDER_HISTORY;
select * from CAR_INFO;
