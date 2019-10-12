--指向当前要使用的数据库
use master
go
--判断当前数据库是否存在
if exists (select * from sysdatabases where name='ErpOfSeiei')
drop database ErpOfSeiei --删除数据库
go
--创建数据库
create database ErpOfSeiei
on primary
(
	--数据库文件的逻辑名
    name='ErpOfSeiei_data',
    --数据库物理文件名（绝对路径）
    filename='C:\ErpOfSeiei_data.mdf',
    --数据库文件初始大小
    size=10MB,
    --数据文件增长量
    filegrowth=1MB
)
--创建日志文件
log on
(
    name='ErpOfSeiei_log',
    filename='C:\ErpOfSeiei_log.ldf',
    size=2MB,
    filegrowth=1MB
)
go
--用户信息列表
use ErpOfSeiei
go
if exists (select * from sysobjects where name='UserMsg')
drop table UserMsg
go
create table UserMsg
(
    id int identity primary key, -- 用户ID
    username varchar(20) not null, --用户名称
    usercode varchar(20) not null, --用户字母编码',
    users_group_id int not null, --用户组别ID',
    password varchar(50) not null, --用户密码',
    email varchar(20) DEFAULT null, --用户邮箱',
    phone varchar(20) DEFAULT null, --用户手机',
    role int DEFAULT 0 , --用户权限（1 表示管理员，0 表示普通用户，默认 0）',
    update_user_id int not null, --更新用户ID',
    create_time datetime not null, --创建时间',
    update_time datetime not null --更新时间',
)
go
--用户组别列表
if exists(select * from sysobjects where name='UserGroup')
drop table UserGroup
go
create table UserGroup
(
    id int identity primary key, --组别主键
    parent_id int not null, --父组别主键（0 为根目录）
    name varchar(20) not null, --类别名字
    update_user_id int not null, --更新用户ID
    create_time datetime not null, --创建时间
    update_time datetime not null --更新时间
)
go
--用户可操作生产线权限信息，允许操作的生产线列表
if exists(select * from sysobjects where name='ProductionLineRight')
drop table ProductionLineRight
go
create table ProductionLineRight
(
    id int identity primary key, --主键
    user_id int not null, --用户ID
    product_line_id int not null, --生产线ID
    update_user_id int not null, --更新用户ID
    create_time datetime not null, --创建时间
    update_time datetime not null --更新时间
)
go
--生产线主要信息列表
if exists(select * from sysobjects where name='ProductionLine')
drop table ProductionLine
create table ProductionLine
(
    id int identity primary key, --主键
    workgroup varchar(20) not null, --生产组别
    workshop varchar(20) not null, --生产车间
    line_code varchar(20) not null, --生产线
    people_num int not null, --生产线人数
    workhours decimal(10, 2) not null, --生产线工作时长
    isInvalid bit default 0, --生产线状态(0表示正常运行, 1表示失效)
    update_user_id int not null, --更新用户ID
    create_time datetime not null, --创建时间
    update_time datetime not null --更新时间
)
go
--生产线工作人数信息列表
if exists(select * from sysobjects where name='PeopleNumOfLine')
drop table PeopleNumOfLine
create table PeopleNumOfLine
(
    id int identity primary key, --主键
    production_line_id int not null, --生产线ID
    people_num int not null, --生产线人数
    start_time datetime not null, --开始时间
    end_time datetime not null, --结束时间
    update_user_id int not null, --更新用户ID
    create_time datetime not null, --创建时间
    update_time datetime not null --更新时间
)
go
--生产线工作时间信息列表
if exists(select * from sysobjects where name='WorkhoursOfLine')
drop table WorkhoursOfLine
create table WorkhoursOfLine
(
    id int identity primary key, --主键
    production_line_id int not null, --生产线ID
    workhours decimal(10, 2) not null, --生产线工作时长
    start_time datetime not null, --开始时间
    end_time datetime not null, --结束时间
    update_user_id int not null, --更新用户ID
    create_time datetime not null, --创建时间
    update_time datetime not null --更新时间
)
go
--生产线生产效率信息列表
if exists(select * from sysobjects where name='EfficiencyOfLine')
drop table EfficiencyOfLine
create table EfficiencyOfLine
(
    id int identity primary key, --主键
    production_line_id int not null, --生产线ID
    style_name varchar(20) not null, --所属的款式分类ID
    efficiency decimal(10, 2) not null, --效率
    update_user_id int not null, --更新用户ID
    create_time datetime not null, --创建时间
    update_time datetime not null --更新时间
)
go
--产品类型信息列表
if exists(select * from sysobjects where name='ProductClass')
drop table ProductClass
create table ProductClass
(
    id int identity primary key, --主键
    name varchar(20) not null, --产品类型名称
    product_style_name varchar(20) not null, --所属的款式分类
    efficiency decimal(10, 2) not null, --默认效率
    update_user_id int not null, --更新用户ID
    create_time datetime not null, --创建时间
    update_time datetime not null, --更新时间
)
go
--产品类型信息列表
if exists(select * from sysobjects where name='ProductClassEfficiency')
drop table ProductClassEfficiency
create table ProductClassEfficiency
(
    id int identity primary key, --主键
    product_class_id int not null, --所属的款式分类ID
    start_quantity int not null, --开始计件数
    end_quantity int not null, --结束计件数
    efficiency decimal(10, 2) not null, --产品类效率
    update_user_id int not null, --更新用户ID
    create_time datetime not null, --创建时间
    update_time datetime not null, --更新时间
)
go
--产品款式分类信息列表
if exists(select * from sysobjects where name='ProductStyle')
drop table ProductStyle
create table ProductStyle
(
    id int identity primary key, --主键
    name varchar(20) not null, --款式名称
    update_user_id int not null, --更新用户ID
    create_time datetime not null, --创建时间
    update_time datetime not null --更新时间
)
go
--排产计划信息
if exists(select * from sysobjects where name='ProductionPlanning')
drop table ProductionPlanning
create table ProductionPlanning
(
    id int identity primary key, --主键
  production_line_id int not null, --生产线ID
  start_time datetime not null, --开始时间
  end_time datetime not null, --结束时间
  qty_plan int not null, --计划数量
  qty_finish int DEFAULT '0', --已完成数量
  custnames varchar(50) not null, --客户名称
  ordernums varchar(50) not null, --制单号
  update_user_id int not null, --更新用户ID
  create_time datetime not null, --创建时间
  update_time datetime not null --更新时间
)
go
--排产计划详情信息
if exists(select * from sysobjects where name='ProductionPlanningDetail')
drop table ProductionPlanningDetail
create table ProductionPlanningDetail
(
    id int identity primary key, --主键
    is_to_do bit not null, --是否已经添加到排产计划中',
    production_planning_id int DEFAULT NULL, --排产计划主信息ID',
    production_line_id int not null, --生产线ID',
    custname varchar(50) not null, --客户名称',
    good_name varchar(50) not null, --货物名称',
    ordernum varchar(50) not null, --制单号',
    season varchar(10) DEFAULT NULL, --季节',
    color varchar(10) DEFAULT NULL, --颜色',
    qty_plan int not null, --计划数量',
    qty_finish int DEFAULT '0', --已完成数量',
    product_class_name varchar(20) not null, --产品类名称',
    leaving_time datetime not null, --离厂日期',
    update_user_id int not null, --更新用户ID',
    create_time datetime not null, --创建时间',
    update_time datetime not null --更新时间',
)
go
--排产计划详情信息
if exists(select * from sysobjects where name='Festival')
drop table Festival
create table Festival
(
    id int identity primary key, --主键
    working_date_setting_id int not null, --有效年份
    festival_name varchar(10) not null, --节日名称
    begin_date datetime not null, --开始日期
    end_date datetime not null, --结束日期
    update_user_id int not null, --更新用户ID
    create_time datetime not null, --创建时间
    update_time datetime not null --更新时间
)
go
--工作日，工作时间设置 信息
if exists(select * from sysobjects where name='WorkingDateSetting')
drop table WorkingDateSetting
create table WorkingDateSetting
(
  id int identity primary key, --主键
  effective_year int not null, -- 有效年份
  monday bit DEFAULT 1, -- 星期一是否已经勾选
  tuesday bit DEFAULT 1, -- 星期二是否已经勾选
  wednesday bit DEFAULT 1, -- 星期三是否已经勾选
  thursday bit DEFAULT 1, -- 星期四是否已经勾选
  friday bit DEFAULT 1, -- 星期五是否已经勾选
  saturday bit DEFAULT 1, -- 星期六是否已经勾选
  sunday bit DEFAULT 1, -- 星期日是否已经勾选
  update_user_id int not null, -- 更新用户ID
  create_time datetime not null, -- 创建时间
  update_time datetime not null, -- 更新时间
)

-------------------------------------------插入数据--------------------------------------
use ErpOfSeiei
go
INSERT INTO UserMsg VALUES ('1', '管理员', 'admin', '2', '1936246BB4D209F363A0EB93413C4191', '123@qq.com', '123456789', '1', '1', '2016-11-06 16:56:45', '2017-04-04 19:27:36');
INSERT INTO UserGroup VALUES ( '0', 'ROOT', '1', '2016-11-06 16:56:45', '2017-04-04 19:27:36');
INSERT INTO UserGroup VALUES ( '1', '管理员', '1', '2016-11-06 16:56:45', '2017-04-04 19:27:36');
INSERT INTO ProductStyle VALUES ('中等款', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');
INSERT INTO ProductStyle VALUES ('及肯款', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');
INSERT INTO ProductStyle VALUES ('平车款', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');

