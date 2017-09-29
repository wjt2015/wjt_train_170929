
DROP DATABASE onduty_db;
/*创建值班表数据库*/
CREATE DATABASE onduty_db;
USE onduty_db;

/*创建员工值班信息表，该表只记录每个员工下一次的值班安排、值班类型、值班业务和某些员工因未按时值班而需要的补班*/
CREATE TABLE employee_onduty_table
(
id INT UNSIGNED AUTO_INCREMENT COMMENT '自增id，作为主键',
name VARCHAR(20) NOT NULL DEFAULT '' COMMENT '值班的员工姓名',
email VARCHAR(20) NOT NULL DEFAULT '' COMMENT '值班的员工邮箱，用于发邮件',
qtalk VARCHAR(20) NOT NULL DEFAULT '' COMMENT '值班的员工qtalk，用于发值班消息',
onduty_type_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '值班的类型编号，例如正常值班、因休病假而补班等。参照onduty_type_table内的id' REFERENCES onduty_type_table (id),
onduty_work_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '值班的业务编号，也就是值班时所做的工作编号。参照onduty_work_table内的id' REFERENCES onduty_work_table (id),
onduty_date DATE NOT NULL DEFAULT '0000-00-00' COMMENT '值班的具体日期',
INDEX idx_name USING BTREE (name),
PRIMARY KEY(id)
)ENGINE=InnoDB CHARSET=UTF8MB4 COMMENT = '员工值班信息表，该表只记录每个员工下一次的值班安排、值班类型、值班业务和某些员工因未按时值班而需要的补班';

CREATE TABLE onduty_type_table
(
id INT UNSIGNED AUTO_INCREMENT COMMENT '自增主键id',
detail VARCHAR(127) NOT NULL DEFAULT '' COMMENT '值班类型的详细解释',
PRIMARY KEY (id)
)ENGINE=InnoDB CHARSET=UTF8MB4 COMMENT='员工的值班类型信息表';

CREATE TABLE onduty_work_table
(
id INT UNSIGNED AUTO_INCREMENT COMMENT '自增主键id',
detail VARCHAR(127) NOT NULL DEFAULT '' COMMENT '值班工作的详细说明',
PRIMARY KEY (id)
)ENGINE=InnoDB CHARSET=UTF8MB4 COMMENT='员工的值班工作信息表';

CREATE TABLE non_onduty_date_table
(
id INT UNSIGNED AUTO_INCREMENT COMMENT '自增主键id',
non_onduty_date DATE NOT NULL DEFAULT '0000-00-00' COMMENT '不安排值班的日期，例如国家法定节假日',
PRIMARY KEY(id)
)ENGINE=InnoDB CHARSET=UTF8MB4 COMMENT='不安排值班的日期信息表；安排值班时需要参考此表，此表内的日期都不安排值班；此表变更时，employee_onduty_table数据表内的值班日期安排也要变化';

/**/
DROP TABLE employee_onduty_table;

/*创建值班表数据库*/
CREATE DATABASE onduty_db;
USE onduty_db;
/*创建值班表数据表*/
CREATE TABLE employee_onduty_table
(
id INT UNSIGNED AUTO_INCREMENT COMMENT '自增主键id',
onduty_module VARCHAR(30) NOT NULL DEFAULT '' COMMENT '值班模块',
qtalk_list VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '值班员工Qtalk列表构成的字符串',
idx INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '当前值班员工列表的索引',
UNIQUE uniq_onduty_module USING BTREE (onduty_module),
PRIMARY KEY (id)
)ENGINE=InnoDB,CHARSET=utf8mb4,COMMENT='员工值班数据表';

/*插入示例数据*/
INSERT INTO employee_onduty_table(onduty_module,qtalk_list) VALUES
('大住宿系统A的维护','bus.qiao;gates.bill;clinton.bill;langpu.te;bush.gorge;bama.ao;hongzhang.li;guofan.zeng'),
('大住宿系统B的维护','langpu.te;bush.gorge;bus.qiao;gates.bill;clinton.bill;zhidong.zhang;zongtang.zuo');










