-- DROP TABLE timestamp_table;
-- CREATE TABLE IF NOT EXISTS timestamp_table(
-- date TIMESTAMP,
-- id INT UNSIGNED AUTO_INCREMENT,
-- name VARCHAR(20) DEFAULT '--',
-- PRIMARY KEY(id)
-- )CHARSET=UTF8;
--
INSERT INTO timestamp_table(name) VALUES
('李鸿章1'),
('张之洞1'),
('屈原1');
UPDATE timestamp_table SET name='曾国藩' WHERE name='李鸿章1';
INSERT INTO onduty_module(name) VALUES
(,,);

