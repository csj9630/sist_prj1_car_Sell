-- ī�� ����
drop table CARD_INFO;
-- �ֹ� ����
drop table ORDER_HISTORY;
-- ����� ���� ���̺� ����
drop table USER_INFO ;
--�������� ���̺� ����
drop table CAR_DEFECT;
--���� ���̺� ����
drop table DEFECT;
--�����ɼ� ���̺� ����
drop table CAR_OPTION;
--�ɼ� ���̺� ����
drop table OPTION_TABLE;
--�����̹��� ���̺� ����
drop table CAR_IMAGE;
--�̹��� ���̺� ����
drop table IMAGE;
-- �ڵ��� ���� ���̺� ����
drop table  CAR_INFO;
-- ���� ���̺� ����
drop table OIL;
-- ������ ���̺� ����
drop table BRAND;

-- ������ ����
drop TABLE ADMIN;






/*�̹��� ������ ����*/
drop sequence SEQ_USER_INFO;

/*ȸ�� �������� ����*/
create sequence SEQ_USER_INFO
increment by 1
start with 1
maxvalue 999;

-- ����� ����
create table USER_INFO (
	user_code number(10)
	constraint PK_USER_INFO primary key,-- ȸ����ȣ,
	id varchar2(30) not null, -- ���̵�,
	pass varchar2(30) not null, -- ��й�ȣ,
	name varchar2(30) not null, -- �̸�,
	email varchar2(30) not null, -- �̸���,
	tel varchar2(30) not null, -- ��ȭ��ȣ,
	address varchar2(300) not null, -- �ּ�,
	generate_date date not null, -- ���� ���� ����,
	status_activate char(15) not null -- Ȱ��ȭ ����
);

-- ����� ���� �׽�Ʈ ������ ����
insert into USER_INFO(USER_CODE, ID, PASS, name, EMAIL, TEL,
ADDRESS, GENERATE_DATE, STATUS_ACTIVATE)
values(SEQ_USER_INFO.nextval, 'testId', '1234', '������',
'lee@test.com', '010-1111-2222', '����� ������', sysdate, 'Y');

--����� ���� ��ȸ
--SELECT * FROM USER_INFO;

--����
create table OIL(
	OIL varchar2(15)
	constraint PK_OIL primary key);

--���� �׽�Ʈ ������ ����
insert into OIL(OIL) values('����');

--���� ���� ���� ��ȸ
--SELECT * FROM OIL;

-- ������
create table BRAND(
	CAR_NAME varchar2(30)
	constraint PK_BRAND primary key,
	BRAND varchar2(30));

-- ������ �׽�Ʈ ������ ����
insert into BRAND( CAR_NAME, BRAND )
values('K5','���');

--������ ���� ���� ��ȸ
--SELECT * FROM BRAND;

-- ����
create table DEFECT (
	defect_code NUMBER(10) not null
	constraint PK_DEFECT primary key,-- �����ڵ�,
	problem_history varchar2(300),  -- ��������,
	defect_type varchar2(300),  -- Ÿ��,
	defectadd_date date default sysdate -- �߰�����
);

--���� �׽�Ʈ ������ ����
insert into DEFECT(DEFECT_CODE, PROBLEM_HISTORY, DEFECT_TYPE)
values(1,'ħ����', 'ħ����2');

--���� ���� ��ȸ
--SELECT * FROM DEFECT;

-- �ڵ��� ����
create table CAR_INFO (
	product_code number(10)
	constraint PK_CAR_INFO primary key,-- �����ڵ�,
	product_name varchar2(100)--csv,exerd�� �°� �߰���.
	price number(30), -- ����,
	car_year date, -- ����,
	cc number(30) , -- ��ⷮ,
	distance number(30), -- ����Ÿ�,
	registration_number varchar2(20), -- ������ȣ(10->20)
	status_sold varchar2(15) not null, -- �Ǹſ���,
	car_name varchar2(30)
	constraint FK_CAR_INFO_CAR_NAME references BRAND(CAR_NAME),-- ����,
	oil varchar2(15)
	constraint FK_CAR_INFO_OIL references OIL(OIL)-- ����
);

--�ڵ��� ���� �׽�Ʈ ������ ����
insert into CAR_INFO(PRODUCT_CODE, PRICE, CAR_YEAR,
CC, DISTANCE, REGISTRATION_NUMBER, STATUS_SOLD,
CAR_NAME, OIL)
values(001,300000, sysdate, 300, 1500000, '15��-��'
, '�ǸſϷ�','K5', '����');

--�ڵ��� ���� ���� ��ȸ
--SELECT * FROM CAR_INFO;

-- ��������
create table CAR_DEFECT (
	defect_code number(10) not null
	constraint FK_CAR_DEFECT_defect_code references DEFECT(defect_code), -- �����ڵ�,
	product_code number(10) not null
	constraint FK_CAR_DEFECT_product_code REFERENCES CAR_INFO(product_code), -- �����ڵ�,
	processing_status varchar2(15) null, -- ó������
	processing_date date default sysdate, -- ó������
  constraint PK_CAR_DEFECT primary key(defect_code, product_code)
);

--�������� �׽�Ʈ ������ ����
insert into CAR_DEFECT(DEFECT_CODE, PRODUCT_CODE, PROCESSING_STATUS, PROCESSING_DATE)
values(1,1,'ó����2',null);

--�������� ���� ��ȸ
--SELECT * FROM CAR_DEFECT;

-- �ɼ�
create table OPTION_TABLE (
	option_code number(10) constraint PK_OPTION_TABLE primary key,-- �ɼ��ڵ�,
	option_name varchar2(300), -- �ɼǸ�,
	optionadd_date date default sysdate-- �߰���
);

--�ɼ� �׽�Ʈ ������ ����
insert into OPTION_TABLE(OPTION_CODE, OPTION_NAME, OPTIONADD_DATE)
values(1, '����', sysdate);

--�ɼ� ���� ��ȸ
--SELECT * FROM OPTION_TABLE;

-- �����ɼ�
create table CAR_OPTION (
	option_code number(10) not null
	constraint FK_CAR_OPTION_option_code references OPTION_TABLE(option_code), -- �ɼ��ڵ�,
	product_code number(10) not null
	constraint FK_CAR_OPTION_product_code references CAR_INFO(product_code), -- �����ڵ�
	constraint PK_CAR_OPTION primary key(option_code,product_code)
);

--�����ɼ� �׽�Ʈ ������ ����
insert into CAR_OPTION(OPTION_CODE, PRODUCT_CODE)
values(1,1);

--�����ɼ� ���� ��ȸ
--SELECT * FROM CAR_OPTION;

--�̹���
create table IMAGE(
 image_code number(10) not null
 constraint PK_IMAGE primary key,
 image_name varchar2(300),
 imageadd_date date default sysdate
 );

--�̹��� �׽�Ʈ ������ ����
insert into IMAGE(IMAGE_CODE, IMAGE_NAME)
values (1, '������ʹ�');

--�̹��� ���� ��ȸ
--SELECT * FROM IMAGE;

--�����̹���
create table CAR_IMAGE(
 image_code number(10) not null
 constraint FK_CAR_IMAGE_image_code references IMAGE(image_code),
 product_code number(10) not null
 constraint FK_CAR_IMAGE_product_code references CAR_INFO(product_code),
 constraint PK_CAR_IMAGE primary key(image_code,product_code)
 );

--�����̹��� �׽�Ʈ ������ ����
insert into CAR_IMAGE(IMAGE_CODE, PRODUCT_CODE)
values(1,1);

--�����̹��� ���� ��ȸ
--SELECT * FROM CAR_IMAGE;


-- �ֹ� ����
create table ORDER_HISTORY (
	payment_code number(10) constraint PK_ORDER_HISTORY primary key, -- �ֹ���ȣ,
	order_date date default sysdate, -- �ֹ�����,
	delivery_state varchar2(15), -- Ź�� ����,
	product_code number(10) constraint fk_order_history_product_code references car_info(product_code), -- �����ڵ�,
	user_code number(10) constraint fk_order_history_user_code references user_info(user_code)-- ȸ����ȣ
);

insert into ORDER_HISTORY(payment_code, order_date, delivery_state,
product_code, user_code)
values(1, sysdate, '�����', 1, 1);

select * from ORDER_HISTORY;


-- ī�� ����
create table CARD_INFO (
	user_code number(10) constraint pk_card_info primary key
	constraint fk_card_info references user_info(user_code), -- ȸ����ȣ,
	credit_card varchar2(20) not null, -- ī���ȣ,
	registration_date date default sysdate -- �����
);

/*
�̹� ȸ�����Ե� ������� ī�� ��ȣ�� �����ϴ� �����̹Ƿ�
user_code�� ��������
*/
-- ī�� ���� �׽�Ʈ ������ ����(���� ����)
insert into CARD_INFO(user_code, credit_card, registration_date)
values(1, 'xxx-xxxx-xxxx', sysdate);

select * from CARD_INFO;

-- ������ ����
create table admin (
	admin_id varchar2(30) constraint PK_ADMIN primary key,-- ���̵�,
	admin_pass varchar2(30) null, -- ��й�ȣ,
	admin_address varchar2(300) null, -- �ּ�,
	admin_contact varchar2(30) null, -- ����ó,
	admin_fax varchar2(30) null, -- FAX,
	adminadd_date date default sysdate-- �߰�����
);

-- ������ ���� �׽�Ʈ ������ ����
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
    options VARCHAR2(255),  -- 'option'�� ������� �� �־� 'options'�� ����
    car_img VARCHAR2(255),
    status_sold VARCHAR2(20),
    car_name VARCHAR2(50),
    oil VARCHAR2(20)
);

select * from car_temp_stage
order by product_code;

select * from oil;

-- [1�ܰ�: CAR_INFO�� �����ϴ� ��� �ڽ� ���̺� ����]
-- (������ ��������ϴ�)
DELETE FROM ORDER_HISTORY;
DELETE FROM CAR_DEFECT;
DELETE FROM CAR_OPTION;
DELETE FROM CAR_IMAGE;

-- [2�ܰ�: �θ� ���̺� ����]
-- ���� �ڽĵ��� ��� ��������Ƿ� CAR_INFO�� ������ �� �ֽ��ϴ�.
DELETE FROM CAR_INFO;
DROP TABLE CAR_INFO CASCADE CONSTRAINTS;

-- 1. [������] CAR_INFO ���̺��� �ùٸ��� �ٽ� ����
create table CAR_INFO (
	product_code number(10)
	constraint PK_CAR_INFO primary key,
	product_name varchar2(100), -- <--- ��ǥ(,) �߰���
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

-- 2. CAR_INFO ���̺�� ������ ����
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

-- 3. ���� ����
COMMIT;

SELECT COUNT(*) FROM CAR_INFO; -- 50.
SELECT * FROM CAR_INFO ORDER BY product_code;

-- 1. OPTION_TABLE�� ������ ����
CREATE SEQUENCE SEQ_OPTION_TABLE
START WITH 1
INCREMENT BY 1;

-- 2. IMAGE ���̺�� ������ ����
CREATE SEQUENCE SEQ_IMAGE
START WITH 1
INCREMENT BY 1;

-- 3. ���� �׽�Ʈ ������ ����
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
    CONNECT BY LEVEL <= REGEXP_COUNT(options, '\|') + 1 -- '|' ���� + 1 ��ŭ �ݺ�
           AND PRIOR product_code = product_code -- �������� ��� ó��
           AND PRIOR SYS_GUID() IS NOT NULL -- CONNECT BY ���ѷ��� ����
)
WHERE option_name IS NOT NULL; -- �ɰ��� �� �� ��(null)�� ������ ����



INSERT INTO CAR_OPTION (option_code, product_code)
SELECT
    opt.option_code,
    spl.product_code
FROM (
    -- -----------------------------------------------------------------
    -- (����) �� �κ��� CAR_TEMP_STAGE�� options ���� �ɰ��� ���� �����Դϴ�.
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
    -- (��) ���� ����
    -- -----------------------------------------------------------------
) spl
JOIN OPTION_TABLE opt ON spl.option_name = opt.option_name;



-- 1. IMAGE ������ ���̺� ����
INSERT INTO IMAGE (image_code, image_name)
SELECT
    SEQ_IMAGE.NEXTVAL,
    car_img
FROM (
    SELECT DISTINCT car_img
    FROM CAR_TEMP_STAGE
    WHERE car_img IS NOT NULL
);

-- 2. CAR_IMAGE ���� ���̺� ����
INSERT INTO CAR_IMAGE (image_code, product_code)
SELECT
    img.image_code,
    TO_NUMBER(stg.product_code)
FROM CAR_TEMP_STAGE stg
JOIN IMAGE img ON stg.car_img = img.image_name
WHERE stg.car_img IS NOT NULL;



-- 1. ���� ����
COMMIT;

-- 2. ������ Ȯ��
SELECT * FROM OPTION_TABLE;
SELECT * FROM CAR_OPTION ORDER BY product_code;
SELECT * FROM IMAGE;
SELECT * FROM CAR_IMAGE ORDER BY product_code;
select* from car_info;

-- 3. (����) �ӽ� ���̺� ����
select * from car_temp_stage;
DROP TABLE CAR_TEMP_STAGE;
select * from BRAND;
select * from CAR_DEFECT;
select * from DEFECT; --�ӽ� ������

