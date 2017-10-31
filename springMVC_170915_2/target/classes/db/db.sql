

USE crm_statistic;
SELECT * FROM login_user_table;



SELECT * FROM auth_table;

CREATE TABLE auth_table(
id INT AUTO_INCREMENT COMMENT '主键，自增',
user_name VARCHAR(10) NOT NULL DEFAULT '' COMMENT '用户名',
password VARCHAR(10) NOT NULL  DEFAULT '' COMMENT '密码',
role TINYINT NOT NULL DEFAULT 0 COMMENT '用户角色，分为0、1、2、...',

PRIMARY KEY (id));

DROP TABLE login_user_table;

SELECT * FROM login_user_table;

CREATE TABLE login_user_table(
id INT AUTO_INCREMENT COMMENT '主键，自增',
user_name VARCHAR(10) NOT NULL DEFAULT '' COMMENT '用户名',
password VARCHAR(10) NOT NULL  DEFAULT '' COMMENT '密码',
login_time BIGINT NOT NULL DEFAULT -1 COMMENT '登录时刻的时间戳',
role TINYINT NOT NULL DEFAULT 0 COMMENT '用户角色，分为0、1、2、...',
is_login TINYINT NOT NULL DEFAULT 0 COMMENT '0表示未登录，1表示已经登录',
UNIQUE INDEX user_index USING BTREE (user_name,password) COMMENT '用户名和密码是唯一的',
PRIMARY KEY(id)
)ENGINE=InnoDB,CHARACTER SET=UTF8MB4,COMMENT='这个数据表记录已经注册的用户的登录信息';




