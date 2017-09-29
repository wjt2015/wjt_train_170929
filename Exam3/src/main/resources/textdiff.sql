/**/
CREATE DATABASE text_db;
USE text_db;

CREATE TABLE IF NOT EXISTS text_diff_table
(
id INT AUTO_INCREMENT COMMENT '两个文件对比结果记录的唯一标识，设置为主键，自动递增',
date DATETIME NOT NULL COMMENT '两个文件执行对比的时间',
src_text TEXT NOT NULL COMMENT '源文件内容',
target_text TEXT NOT NULL COMMENT '目标文件内容',
text_diff TEXT NOT NULL COMMENT '两个文件的对比结果',
PRIMARY key(id)
)CHARSET=UTF8 COMMENT='数据表text_diff_table用于存储两个文本文件的对比结果';


