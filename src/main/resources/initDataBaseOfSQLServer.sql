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
    default_style_name varchar(20) not null, --默认款式分类属性
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
--排产计划详情信息
if exists(select * from sysobjects where name='ProductionPlanningDetail')
drop table ProductionPlanningDetail
create table ProductionPlanningDetail
(
    id int identity primary key, -- 主键
    summaryId int not null, -- 总表 Id
    is_planning bit default '0', -- 是否已排产
    efficiency_by_setting decimal(10, 2) default null, -- 自定义的效率（自选效率）
    productionLineId int default null, -- 生产线外键 Id
    start_time datetime default null, --开始时间
    end_time datetime default null, --结束时间
    qty_finish int DEFAULT '0', --已完成数量
    season varchar(10) default null, -- 季节
    color varchar(50) default null, -- 颜色
    sizes varchar(50) default null, -- 尺码
    clientName varchar(100) default null, -- 客户
    clientStyleNo varchar(50) default null, -- 客户款号
    orderNo varchar(50) default null, -- 制单号
    orderNum int default null, -- 制单数
    orderKind varchar(50) default null, -- 订单类别
    styleNo varchar(50) default null, -- 款号
    goodName varchar(50) default null, -- 产品名称
    style varchar(50) default null, -- 款式
    deliveryOfContract_time datetime default null, -- 合同交期
    deliveryOfFactory_time datetime default null, -- 工厂离厂期
    arriveWarehouse_time datetime default null, -- 到仓期
    qtyOfBatchedDelivery int default null, -- 分批走货数量
    lining varchar(50) default null, -- 面料
    liningOfStitching_time datetime default null, -- 车缝辅料期
    suppliesOfLining varchar(50) default null, -- 面料供应商
    cloth_time datetime default null, -- 布期
    sam decimal(10, 2) default null, -- sam
    samOfLocal decimal(10,2) default null, -- 本厂 sam
    sah decimal(10, 2) default null, -- sah
    approve_time datetime default null, -- 批办时间
    embroider varchar(50) default null, -- 车印花
    embroider_dayNum int default null, -- 车印花天数
    embroider_time datetime default null, -- 车印花日期
    factory_embroider varchar(50) default null, -- 车花工厂1
    factory_embroider2 varchar(50) default null, -- 车花工厂2
    printAfterembroider varchar(50) default null, -- 车花后再印花
    printAfterembroider_dayNum int default null, -- 车花后再印花天数
    factory_print varchar(50) default null, -- 印花工厂1
    factory_print2 varchar(50) default null, -- 印花工厂2
    backPart_dayNum int default null, -- 后整天数
    memo varchar(500) default null, -- 备注
    cuttingQty int default null, -- 裁剪数
    is_finish_cutting bit default '0', -- 是否结束裁剪
    advanceCutting_dayNum int default null, -- 提前开裁天数
    update_user_id int not null, --更新用户ID',
    create_time datetime not null, --创建时间',
    update_time datetime not null --更新时间',
)
go
--排产计划详情信息主表
if exists(select * from sysobjects where name='SummaryOfProductionPlanningDetail')
drop table SummaryOfProductionPlanningDetail
create table SummaryOfProductionPlanningDetail
(
    id int identity primary key, --主键
    billno varchar(20) not null, --单号
    clientName varchar(50) default null, --客户
    season varchar(20) default null, -- 季节
    update_user_id int not null, --更新用户ID
    create_time datetime not null, --创建时间
    update_time datetime not null --更新时间
)
go
--节假日信息
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
go
--颜色设置
if exists(select * from sysobjects where name='ColorSetting')
drop table ColorSetting
create table ColorSetting
(
  id int identity primary key, -- 主键
  default_color varchar(20) not null, -- 默认颜色
  default_delay_color varchar(20) default null, -- 推迟颜色
  default_advance_color varchar(20) default null, -- 提前颜色
  advance_color varchar(20) default null, -- 提前某天数的颜色
  delay_color varchar(20) default null, -- 推迟某天数时的颜色
  advance_daynum int default null, -- 提前天数
  delay_daynum int default null, -- 推迟天数
  user_id int not null, -- 用户ID，该设置是属于哪个用户
  update_user_id int not null, -- 更新用户ID
  create_time datetime not null, -- 创建时间
  update_time datetime not null, -- 更新时间
)

-------------------------------------------插入数据--------------------------------------
use ErpOfSeiei
go
INSERT INTO UserMsg VALUES ('管理员', 'admin', '2', '1936246BB4D209F363A0EB93413C4191', '123@qq.com', '123456789', '1', '1', '2016-11-06 16:56:45', '2017-04-04 19:27:36');
INSERT INTO UserGroup VALUES ( '0', 'ROOT', '1', '2016-11-06 16:56:45', '2017-04-04 19:27:36');
INSERT INTO UserGroup VALUES ( '1', '管理员', '1', '2016-11-06 16:56:45', '2017-04-04 19:27:36');
INSERT INTO ProductStyle VALUES ('中等款', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');
INSERT INTO ProductStyle VALUES ('及肯款', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');
INSERT INTO ProductStyle VALUES ('平车款', '1', '2017-03-25 16:46:00', '2017-03-25 16:46:00');
INSERT INTO ColorSetting VALUES ('#1FEF87', '#1FEF87', '#1FEF87', '#1FEF87', '#1FEF87', '10', '2', '1', '1', '2016-11-06 16:56:45', '2017-04-04 19:27:36');

