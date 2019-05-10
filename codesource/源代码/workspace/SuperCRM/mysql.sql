-- 创建客户表
DROP TABLE IF EXISTS CUSTOMER;
CREATE TABLE CUSTOMER (
  CUSTOMER_ID 		int(20) NOT NULL default '0',
  DISPLAY_NAME 		varchar(255) NOT NULL default '',
  WEB_SITE 		varchar(255) default NULL,
  CUSTOMER_CATEGORY 	varchar(64) default NULL,
  NUM_EMPLOYEE 		varchar(255) default NULL,
  ACTIVE_STATUS 	varchar(4) NOT NULL default 'Y'
) TYPE=MyISAM ;

-- 创建联系人表
DROP TABLE IF EXISTS CONTACT;
CREATE TABLE CONTACT(
  CONTACT_ID 		int(20) NOT NULL default '0',
  CUSTOMER_ID 		int(20) NOT NULL default '0',
  DISPLAY_NAME 		varchar(255) NOT NULL default '',
  CONTACT_TYPE 		varchar(16) NOT NULL default '',
  SEX 			varchar(64) default NULL,
  BIRTH_DATE 		varchar(64) default NULL,
  JOB_TITLE 		varchar(64) default NULL,
  ACTIVE_STATUS 	varchar(4) NOT NULL default 'Y'
) TYPE=MyISAM ;

-- 初始化客户和联系人表数据
INSERT INTO CUSTOMER VALUES(1,'Nest Cafe','http://www.aaaa.com','Customer','51-100','Y');
INSERT INTO CUSTOMER VALUES(2,'Apache Fundation','http://www.bbbb.com','Customer','11-50','Y');
INSERT INTO CUSTOMER VALUES(3,'Sun System','http://www.cccc.com','Partener','101-500','Y');

INSERT INTO CONTACT VALUES(1,1,'Janet','Friend','Female','8.5','CEO','Y');
INSERT INTO CONTACT VALUES(2,1,'Tina','Family','Male','6.6','CTO','Y');
INSERT INTO CONTACT VALUES(3,2,'Jone','Friend','Female','10.6','CIO','Y');