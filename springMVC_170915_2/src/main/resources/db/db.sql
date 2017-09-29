

USE crm_statistic;

SELECT * FROM auth_table;

CREATE TABLE auth_table(
id INT AUTO_INCREMENT COMMENT '主键，自增',
user_name VARCHAR(10) NOT NULL DEFAULT '' COMMENT '用户名',
password VARCHAR(10) NOT NULL  DEFAULT '' COMMENT '密码',
role TINYINT NOT NULL DEFAULT 0 COMMENT '用户角色，分为0、1、2、...',
PRIMARY KEY (id));

