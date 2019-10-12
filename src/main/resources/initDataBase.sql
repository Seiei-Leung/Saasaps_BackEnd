SET NAMES utf8;

/*
	用户信息列表
*/
DROP TABLE IF exists User;

CREATE TABLE User (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  username varchar(20) NOT NULL COMMENT '用户名称',
  usercode varchar(20) NOT NULL COMMENT '用户字母编码',
  users_group_id int(11) NOT NULL COMMENT '用户组别ID',
  password varchar(50) NOT NULL COMMENT '用户密码',
  email varchar(20) DEFAULT NULL COMMENT '用户邮箱',
  phone varchar(20) DEFAULT NULL COMMENT '用户手机',
  role int(4) DEFAULT 0 COMMENT '用户权限（1 表示管理员，0 表示普通用户，默认 0）',
  update_user_id int(11) NOT NULL COMMENT '更新用户ID',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO User VALUES ('1', '管理员', 'admin', '2', '1936246BB4D209F363A0EB93413C4191', '786883603@qq.com', '18689407365', '1', '1', '2016-11-06 16:56:45', '2017-04-04 19:27:36');
COMMIT;

/*
	用户组别列表
*/
DROP TABLE IF exists UserGroup;

CREATE TABLE UserGroup (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '组别主键',
  parent_id int(11) NOT NULL COMMENT '父组别主键（0 为根目录）',
  name varchar(20) NOT NULL COMMENT '类别名字',
  update_user_id int(11) NOT NULL COMMENT '更新用户ID',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO UserGroup VALUES ('1', '0', 'ROOT', '1', '2016-11-06 16:56:45', '2017-04-04 19:27:36'),
('2', '1', '管理员', '1', '2016-11-06 16:56:45', '2017-04-04 19:27:36');
COMMIT;

/*
	用户可操作生产线权限信息，允许操作的生产线列表
*/
DROP TABLE IF exists ProductionLineRight;

CREATE TABLE ProductionLineRight (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  user_id int(11) NOT NULL COMMENT '用户ID',
  product_line_id int(11) NOT NULL COMMENT '生产线ID',
  update_user_id int(11) NOT NULL COMMENT '更新用户ID',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO ProductionLineRight VALUES ('1', '1', '1', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');
COMMIT;

/*
	生产线主要信息列表
*/
DROP TABLE IF exists ProductionLine;

CREATE TABLE ProductionLine (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '生产线主键',
  workgroup varchar(20) NOT NULL COMMENT '生产组别',
  workshop varchar(20) NOT NULL COMMENT '生产车间',
  line_code varchar(20) NOT NULL COMMENT '生产线',
  status tinyint(1) DEFAULT 1 COMMENT '生产线状态(1表示正常运行)',
  update_user_id int(11) NOT NULL COMMENT '更新用户ID',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO ProductionLine VALUES ('1', '2F', '2A', 'A', '1', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');
COMMIT;

/*
	生产线工作人数信息列表
*/
DROP TABLE IF exists PeopleNumOfLine;

CREATE TABLE PeopleNumOfLine (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  production_line_id int(11) NOT NULL COMMENT '生产线ID',
  people_num int(11) NOT NULL COMMENT '生产线人数',
  start_time datetime NOT NULL COMMENT '开始时间',
  end_time datetime NOT NULL COMMENT '结束时间',
  update_user_id int(11) NOT NULL COMMENT '更新用户ID',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO PeopleNumOfLine VALUES ('1', '1', '24', '2017-03-25 16:46:00', '2017-03-25 16:46:00', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');
COMMIT;

/*
	生产线工作时间信息列表
*/
DROP TABLE IF exists WorkhoursOfLine;

CREATE TABLE WorkhoursOfLine (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  production_line_id int(11) NOT NULL COMMENT '生产线ID',
  workhours float(4) NOT NULL COMMENT '生产线工作时长',
  start_time datetime NOT NULL COMMENT '开始时间',
  end_time datetime NOT NULL COMMENT '结束时间',
  update_user_id int(11) NOT NULL COMMENT '更新用户ID',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO WorkhoursOfLine VALUES ('1', '1', '8', '2017-03-25 16:46:00', '2017-03-25 16:46:00', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');
COMMIT;

/*
	生产线生产效率信息列表
*/
DROP TABLE IF exists EfficiencyOfLine;

CREATE TABLE EfficiencyOfLine (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  production_line_id int(11) NOT NULL COMMENT '生产线ID',
  style_name varchar(20) NOT NULL COMMENT '所属的款式分类ID',
  efficiency decimal(20, 2) NOT NULL COMMENT '效率',
  update_user_id int(11) NOT NULL COMMENT '更新用户ID',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO EfficiencyOfLine VALUES ('1', '1', '中等款', '0.9', '1',  '2017-03-25 16:46:00', '2017-03-25 16:46:00');
COMMIT;

/*
	产品类型信息列表
*/

DROP TABLE IF exists ProductClass;

CREATE TABLE ProductClass (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  name varchar(20) NOT NULL COMMENT '产品类型名称',
  style_name varchar(20) NOT NULL COMMENT '所属的款式分类ID',
  start_quantity int(11) NOT NULL COMMENT '开始计件数',
  end_quantity int(11) NOT NULL COMMENT '结束计件数',
  efficiency decimal(20, 2) NOT NULL COMMENT '产品类效率',
  update_user_id int(11) NOT NULL COMMENT '更新用户ID',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO ProductClass VALUES ('1', '针织长裤开拉链袋', '1', '中等款', '10000', '0.8', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');
COMMIT;

/*
	产品款式分类信息列表
*/
DROP TABLE IF exists ProductStyle;

CREATE TABLE ProductStyle (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  name varchar(20) NOT NULL COMMENT '款式名称',
  update_user_id int(11) NOT NULL COMMENT '更新用户ID',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO ProductStyle VALUES
('1', '中等款', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00'),
('2', '及肯款', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00'),
('3', '平车款', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');
COMMIT;

/*
  排产计划信息
*/
DROP TABLE IF exists ProductionPlanning;

CREATE TABLE ProductionPlanning (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  production_line_id int(11) NOT NULL COMMENT '生产线ID',
  start_time datetime NOT NULL COMMENT '开始时间',
  end_time datetime NOT NULL COMMENT '结束时间',
  qty_plan int(11) DEFAULT '0' COMMENT '计划数量',
  qty_finish int(11) DEFAULT '0' COMMENT '已完成数量',
  custnames varchar(50) NOT NULL COMMENT '客户名称',
  ordernums varchar(50) NOT NULL COMMENT '制单号',
  update_user_id int(11) NOT NULL COMMENT '更新用户ID',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  KEY production_line_id_index (production_line_id) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*
  排产计划详情信息
*/
DROP TABLE IF exists ProductionPlanningDetail;

CREATE TABLE ProductionPlanningDetail (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  is_to_do tinyint(1) NOT NULL COMMENT '是否已经添加到排产计划中',
  production_planning_id int(11) DEFAULT NULL COMMENT '排产计划主信息ID',
  production_line_id int(11) NOT NULL COMMENT '生产线ID',
  custname varchar(50) NOT NULL COMMENT '客户名称',
  good_name varchar(50) NOT NULL COMMENT '货物名称',
  ordernum varchar(50) NOT NULL COMMENT '制单号',
  season varchar(10) DEFAULT NULL COMMENT '季节',
  color varchar(10) DEFAULT NULL COMMENT '颜色',
  qty_plan int(11) DEFAULT '0' COMMENT '计划数量',
  qty_finish int(11) DEFAULT '0' COMMENT '已完成数量',
  product_class_name varchar(20) NOT NULL COMMENT '产品类名称',
  leaving_time datetime NOT NULL COMMENT '离厂日期',
  update_user_id int(11) NOT NULL COMMENT '更新用户ID',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  KEY production_planning_id_index (production_planning_id) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


/*
  节假日信息
*/
DROP TABLE IF exists Festival;

CREATE TABLE Festival (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  working_date_setting_id int(11) NOT NULL COMMENT '有效年份',
  festival_name varchar(10) NOT NULL COMMENT '节日名称',
  begin_date datetime NOT NULL COMMENT '开始日期',
  end_date datetime NOT NULL COMMENT '结束日期',
  update_user_id int(11) NOT NULL COMMENT '更新用户ID',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO Festival VALUES
('1', '2019', '劳动节', '2019-05-01 15:36:20', '2019-05-07 15:36:28', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');
COMMIT;

/*
  工作日，工作时间设置 信息
*/
DROP TABLE IF exists WorkingDateSetting;

CREATE TABLE WorkingDateSetting (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  effective_year int(11) NOT NULL COMMENT '有效年份',
  monday tinyint(1) DEFAULT 1 COMMENT '星期一是否已经勾选',
  tuesday tinyint(1) DEFAULT 1 COMMENT '星期二是否已经勾选',
  wednesday tinyint(1) DEFAULT 1 COMMENT '星期三是否已经勾选',
  thursday tinyint(1) DEFAULT 1 COMMENT '星期四是否已经勾选',
  friday tinyint(1) DEFAULT 1 COMMENT '星期五是否已经勾选',
  saturday tinyint(1) DEFAULT 1 COMMENT '星期六是否已经勾选',
  sunday tinyint(1) DEFAULT 1 COMMENT '星期日是否已经勾选',
  begin_working_time varchar(10) DEFAULT NULL COMMENT '一天工作的开始时间',
  end_working_time varchar(10) DEFAULT NULL COMMENT '一天工作的结束时间',
  update_user_id int(11) NOT NULL COMMENT '更新用户ID',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO WorkingDateSetting VALUES
('1', '2019', '1', '1', '1', '1', '1', '1', '1', '8:30', '17:30', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');
COMMIT;