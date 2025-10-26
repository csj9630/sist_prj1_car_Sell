SELECT * FROM CAR_INFO;
SELECT * FROM BRAND;
SELECT * FROM OIL;

-- ����� ����
DROP TABLE USER_INFO;

-- �ڵ��� ����
DROP TABLE  CAR_INFO;

-- ������
DROP TABLE BRAND;

-- ����
DROP TABLE OIL;

/*ȸ�� ������ ����*/
drop sequence SEQ_USER_INFO;

/*ȸ�� �������� ����*/
create sequence SEQ_USER_INFO
increment by 1
start with 1
maxvalue 999;

-- ����� ����
CREATE TABLE USER_INFO (
	user_code number(10) CONSTRAINT PK_USER_INFO PRIMARY KEY,-- ȸ����ȣ,
	id VARCHAR2(30) NOT NULL, -- ���̵�,
	pass VARCHAR2(30) NOT NULL, -- ��й�ȣ,
	name VARCHAR2(30) NOT NULL, -- �̸�,
	email VARCHAR2(30) NOT NULL, -- �̸���,
	tel VARCHAR2(30) NOT NULL, -- ��ȭ��ȣ,
	address VARCHAR2(300) NOT NULL, -- �ּ�,
	generate_date DATE NOT NULL, -- ���� ���� ����,
	status_activate CHAR(15) NOT NULL -- Ȱ��ȭ ����
);

-- ����� ���� �׽�Ʈ ������ ����
insert into USER_INFO(USER_CODE, ID, PASS, NAME, EMAIL, TEL, ADDRESS, GENERATE_DATE, STATUS_ACTIVATE)
values(SEQ_USER_INFO.NEXTVAL, 'testId', '1234', '������', 'lee@test.com', '010-1111-2222', '����� ������'
, sysdate, 'Y');

-- ������
CREATE TABLE BRAND(
	CAR_NAME VARCHAR2(30) CONSTRAINT PK_BRAND PRIMARY KEY,
	BRAND VARCHAR2(30));

-- ������ �׽�Ʈ ������ ����
INSERT INTO BRAND( CAR_NAME, BRAND )
VALUES('K5','���');

--����
CREATE TABLE OIL(
	OIL VARCHAR2(15) CONSTRAINT PK_OIL PRIMARY KEY);

--���� �׽�Ʈ ������ ����
INSERT INTO OIL(OIL) VALUES('����');

-- �ڵ��� ����
CREATE TABLE CAR_INFO (
	product_code number(10) CONSTRAINT PK_CAR_INFO PRIMARY KEY,-- �����ڵ�,
	product_name VARCHAR2(30), -- ������,
	brand VARCHAR2(30), -- �귣���,
	price number(30), -- ����,
	car_year DATE, -- ����,
	cc number(30) , -- ��ⷮ,
	distance number(30), -- ����Ÿ�,
	registration_number VARCHAR2(10), -- ������ȣ,
	car_option VARCHAR2(300), -- �ɼ�,
	car_img BLOB, -- �����̹���,
	status_sold VARCHAR2(15) NOT NULL, -- �Ǹſ���,
	car_name VARCHAR2(30)  CONSTRAINT FK_CAR_INFO_CAR_NAME REFERENCES BRAND(CAR_NAME),-- ����,
	oil VARCHAR2(15)  CONSTRAINT FK_CAR_INFO_OIL REFERENCES OIL(OIL)-- ����
);

--�ڵ��� ���� �׽�Ʈ ������ ����
INSERT INTO CAR_INFO(PRODUCT_CODE, PRODUCT_NAME, BRAND, PRICE, CAR_YEAR,
CC, DISTANCE, REGISTRATION_NUMBER, CAR_OPTION, CAR_IMG, STATUS_SOLD,
CAR_NAME, OIL)
VALUES(001,'K5','���',300000, SYSDATE, 300, 1500000, '15��-��',
'�¿���Ʈ,��ǳ��',NULL , '�ǸſϷ�','K5', '����');



-- �ɼ�
CREATE TABLE OPTION_TABLE (
	option_code number(10) CONSTRAINT PK_OPTION_TABLE PRIMARY KEY,-- �ɼ��ڵ�,
	option_name VARCHAR2(300), -- �ɼǸ�,
	optionadd_date DATE -- �߰���
);

--------------������

-- �����ɼ�
CREATE TABLE CAR_OPTION (
	option_code number(10) NOT NULL -- �ɼ��ڵ�,
	product_code number(10) NOT NULL -- �����ڵ�
);

-- �����ɼ�
ALTER TABLE CAR_OPTION
	ADD CONSTRAINT PK_CAR_OPTION -- �����ɼ� �⺻Ű
	PRIMARY KEY (
		option_code,  -- �ɼ��ڵ�
		product_code  -- �����ڵ�
	);

-- ����
CREATE TABLE DEFECT (
	defect_code number(10) NOT NULL -- �����ڵ�,
	problem_history VARCHAR2(300) NULL -- ��������,
	defect_type VARCHAR2(300) NULL -- Ÿ��,
	defectadd_date DATE NULL -- �߰�����
);

-- ����
ALTER TABLE DEFECT
	ADD CONSTRAINT PK_DEFECT -- ���� �⺻Ű
	PRIMARY KEY (
		defect_code -- �����ڵ�
	);

-- ��������
CREATE TABLE CAR_DEFECT (
	defect_code number(10) NOT NULL -- �����ڵ�,
	product_code number(10) NOT NULL -- �����ڵ�,
	processing_status VARCHAR2(15) NULL -- ó������,
	processing_date DATE NULL -- ó������
);

-- ��������
ALTER TABLE CAR_DEFECT
	ADD CONSTRAINT PK_CAR_DEFECT -- �������� �⺻Ű
	PRIMARY KEY (
		defect_code,  -- �����ڵ�
		product_code  -- �����ڵ�
	);

-- �ڵ��� ����
ALTER TABLE CAR_INFO
	ADD CONSTRAINT FK_BRAND_TO_CAR_INFO -- ������ -> �ڵ��� ����
	FOREIGN KEY (
		car_name -- ����
	)
	REFERENCES BRAND ( -- ������
		car_name -- ����
	);

-- �ڵ��� ����
ALTER TABLE CAR_INFO
	ADD CONSTRAINT FK_OIL_TO_CAR_INFO -- ���� -> �ڵ��� ����
	FOREIGN KEY (
		oil -- ����
	)
	REFERENCES OIL ( -- ����
		oil -- ����
	);

-- �ֹ� ����
ALTER TABLE ORDER_HISTORY
	ADD CONSTRAINT FK_CAR_INFO_TO_ORDER_HISTORY -- �ڵ��� ���� -> �ֹ� ����
	FOREIGN KEY (
		product_code -- �����ڵ�
	)
	REFERENCES CAR_INFO ( -- �ڵ��� ����
		product_code -- �����ڵ�
	);

-- �ֹ� ����
ALTER TABLE ORDER_HISTORY
	ADD CONSTRAINT FK_USER_INFO_TO_ORDER_HISTORY -- ����� ���� -> �ֹ� ����
	FOREIGN KEY (
		user_code -- ȸ����ȣ
	)
	REFERENCES USER_INFO ( -- ����� ����
		user_code -- ȸ����ȣ
	);

-- �ӽ� ���̺�
ALTER TABLE ����
	ADD CONSTRAINT FK_USER_INFO_TO_���� -- ����� ���� -> �ӽ� ���̺�
	FOREIGN KEY (
		user_code -- ȸ����ȣ
	)
	REFERENCES USER_INFO ( -- ����� ����
		user_code -- ȸ����ȣ
	);

-- ī�� ����
ALTER TABLE CARD_INFO
	ADD CONSTRAINT FK_USER_INFO_TO_CARD_INFO -- ����� ���� -> ī�� ����
	FOREIGN KEY (
		user_code -- ȸ����ȣ
	)
	REFERENCES USER_INFO ( -- ����� ����
		user_code -- ȸ����ȣ
	);

-- �ӽ� ���̺�3
ALTER TABLE ��������
	ADD CONSTRAINT FK_CAR_INFO_TO_�������� -- �ڵ��� ���� -> �ӽ� ���̺�3
	FOREIGN KEY (
		product_code -- �����ڵ�
	)
	REFERENCES CAR_INFO ( -- �ڵ��� ����
		product_code -- �����ڵ�
	);

-- �����ɼ�
ALTER TABLE CAR_OPTION
	ADD CONSTRAINT FK_OPTION_TO_CAR_OPTION -- �ɼ� -> �����ɼ�
	FOREIGN KEY (
		option_code -- �ɼ��ڵ�
	)
	REFERENCES OPTION ( -- �ɼ�
		option_code -- �ɼ��ڵ�
	);

-- �����ɼ�
ALTER TABLE CAR_OPTION
	ADD CONSTRAINT FK_CAR_INFO_TO_CAR_OPTION -- �ڵ��� ���� -> �����ɼ�
	FOREIGN KEY (
		product_code -- �����ڵ�
	)
	REFERENCES CAR_INFO ( -- �ڵ��� ����
		product_code -- �����ڵ�
	);

-- ��������
ALTER TABLE CAR_DEFECT
	ADD CONSTRAINT FK_DEFECT_TO_CAR_DEFECT -- ���� -> ��������
	FOREIGN KEY (
		defect_code -- �����ڵ�
	)
	REFERENCES DEFECT ( -- ����
		defect_code -- �����ڵ�
	);

-- ��������
ALTER TABLE CAR_DEFECT
	ADD CONSTRAINT FK_CAR_INFO_TO_CAR_DEFECT -- �ڵ��� ���� -> ��������
	FOREIGN KEY (
		product_code -- �����ڵ�
	)
	REFERENCES CAR_INFO ( -- �ڵ��� ����
		product_code -- �����ڵ�
	);
