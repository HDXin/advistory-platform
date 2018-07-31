/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/18 23:40:58                           */
/*==============================================================*/


drop table if exists admin_user;

drop table if exists advistory_detail;

drop table if exists advistory_info;

drop table if exists application_user;

drop table if exists article_info;

drop table if exists favorite_info;

drop table if exists feedback_info;

drop table if exists member_level;

drop table if exists order_info;

drop table if exists swiper_info;

drop table if exists system_config;

/*==============================================================*/
/* Table: admin_user                                            */
/*==============================================================*/
create table admin_user
(
   user_id              bigint(20) not null auto_increment comment '主键',
   user_name            varchar(30) comment '用户名',
   password             varchar(30) comment '密码',
   password_salt        varchar(30) comment '密码salt',
   photo                varchar(300) comment '头像',
   gender               tinyint(1) comment '性别',
   mobile               varchar(30) comment '手机号',
   tel                  varchar(30) comment '座机号',
   email                varchar(60) comment '邮箱',
   user_status          varchar(30) comment '用户状态',
   display_order        int(11) default 0 comment '顺序号',
   version              int(11) default 0 comment '版本号',
   deleted              int(11) default 1 comment '状态',
   create_user_id       bigint(20) comment '创建人',
   create_user_name     varchar(30) comment '创建人姓名',
   create_time          datetime comment '创建时间',
   update_user_id       bigint(20) comment '修改人',
   update_user_name     varchar(30) comment '更新人姓名',
   update_time          datetime comment '修改时间',
   last_update          timestamp comment '最后更新时间',
   primary key (user_id)
);

alter table admin_user comment '运营管理账号表';

CREATE TABLE `advistory_detail` (
  `advistory_detail_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `advistory_id` bigint(20) DEFAULT NULL COMMENT '文章ID',
  `advistory_detail_type` varchar(30) DEFAULT NULL COMMENT '类型',
  `content` varchar(3000) DEFAULT NULL COMMENT '文本',
  `background` varchar(300) DEFAULT NULL COMMENT '背景图',
  `link` varchar(300) DEFAULT NULL COMMENT '跳转',
  `display_order` int(11) DEFAULT '0' COMMENT '顺序号',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `deleted` int(11) DEFAULT '1' COMMENT '状态',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`advistory_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='咨询详情';



drop table if exists advistory_info;

/*==============================================================*/
/* Table: advistory_info                                        */
/*==============================================================*/
create table advistory_info
(
   advistory_id         bigint(20) not null auto_increment comment '主键',
   advistory_level      varchar(30) comment '文章等级',
   advistory_type       varchar(30) comment '文章类型',
   title                varchar(60) comment '标题',
   digest               varchar(300) comment '摘要',
   favorite_number      bigint(20) default 0 comment '收藏数',
   read_number          bigint(20) default 0 comment '阅读数',
   cover_image          varchar(300) comment '封面图',
   recommend_status     tinyint(1) comment '是否推荐',
   publish_user_id      bigint(20) comment '发布人ID',
   publish_user_name    varchar(30) comment '发布人名',
   publish_operation_time datetime comment '操作发布时间',
   publish_time         datetime comment '发布时间(搜索范围判断字段)',
   author               varchar(30) comment '作者',
   display_order        int(11) default 0 comment '顺序号',
   version              int(11) default 0 comment '版本号',
   deleted              int(11) default 0 comment '状态',
   create_user_id       bigint(20) comment '创建人',
   create_user_name     varchar(30) comment '创建人姓名',
   create_time          datetime comment '创建时间',
   update_user_id       bigint(20) comment '修改人',
   update_user_name     varchar(30) comment '更新人姓名',
   update_time          datetime comment '修改时间',
   last_update          timestamp comment '最后更新时间',
   primary key (advistory_id)
);

alter table advistory_info comment '咨询';




/*==============================================================*/
/* Table: application_user                                      */
/*==============================================================*/
create table application_user
(
   user_id              bigint(20) not null auto_increment comment '主键',
   user_name            varchar(30) comment '用户名',
   full_name            varchar(30) comment '真是姓名',
   nickname             varchar(30) comment '昵称',
   photo                varchar(300) comment '头像',
   gender               tinyint(1) comment '性别',
   mobile               varchar(30) comment '手机号',
   mobile_valid         tinyint(1) comment '手机号是否认证',
   tel                  varchar(30) comment '座机号',
   email                varchar(60) comment '邮箱',
   user_status          varchar(30) comment '用户状态',
   idcard_image_reverse varchar(300) comment '身份证反面照',
   idcard_image_front   varchar(300) comment '身份证正面照',
   idcard_name          varchar(300) comment '证件名',
   idcard_no            varchar(60) comment '身份证号',
   end_time             datetime comment '会员有效时间结束',
   begin_time           datetime comment '会员有效时间开始',
   user_type            varchar(30) comment '用户类型',
   display_order        int(11) default 0 comment '顺序号',
   idcard_valid         tinyint(1) comment '身份证是否认证',
   version              int(11) default 0 comment '版本号',
   deleted              int(11) default 1 comment '状态',
   create_user_id       bigint(20) comment '创建人',
   create_user_name     varchar(30) comment '创建人姓名',
   create_time          datetime comment '创建时间',
   update_user_id       bigint(20) comment '修改人',
   update_user_name     varchar(30) comment '更新人姓名',
   update_time          datetime comment '修改时间',
   last_update          timestamp comment '最后更新时间',
   primary key (user_id)
);

alter table application_user comment '客户端用户';

/*==============================================================*/
/* Table: article_info                                          */
/*==============================================================*/
create table article_info
(
   article_id           bigint(20) not null auto_increment,
   code                 varchar(30),
   title                varchar(100),
   content              varchar(3000),
   display_order        int(11) default 0 comment '顺序号',
   version              int(11) default 0 comment '版本号',
   deleted              int(11) default 1 comment '状态',
   create_user_id       bigint(20) comment '创建人',
   create_user_name     varchar(30) comment '创建人姓名',
   create_time          datetime comment '创建时间',
   update_user_id       bigint(20) comment '修改人',
   update_user_name     varchar(30) comment '更新人姓名',
   update_time          datetime comment '修改时间',
   last_update          timestamp comment '最后更新时间',
   primary key (article_id)
);

alter table article_info comment '文章';

/*==============================================================*/
/* Table: favorite_info                                         */
/*==============================================================*/
create table favorite_info
(
   favorite_id          bigint(20) not null auto_increment comment '主键',
   user_id              bigint(20) comment '用户ID',
   relation_type        varchar(30) comment '类型(ADVISTORY:文章)',
   relation_id          bigint(20) comment '业务ID',
   title                varchar(60) comment '标题',
   enable               tinyint(1) comment '收藏是否可用',
   favorite_status      varchar(30) comment '收藏状态(ADD_FAVORITE:添加收藏, CANCEL_FAVORITE:取消收藏)',
   display_order        int(11) default 0 comment '顺序号',
   version              int(11) default 0 comment '版本号',
   deleted              int(11) default 0 comment '状态',
   create_user_id       bigint(20) comment '创建人',
   create_user_name     varchar(30) comment '创建人姓名',
   create_time          datetime comment '创建时间',
   update_user_id       bigint(20) comment '修改人',
   update_user_name     varchar(30) comment '更新人姓名',
   update_time          datetime comment '修改时间',
   last_update          timestamp comment '最后更新时间',
   primary key (favorite_id)
);

alter table favorite_info comment '收藏表';

/*==============================================================*/
/* Table: feedback_info                                         */
/*==============================================================*/
create table feedback_info
(
   feedback_id          bigint(20) not null auto_increment comment '主键',
   content              varchar(30) comment '反馈内容',
   apply_user_id        bigint(20) comment '申请人ID',
   apply_user_photo     varchar(300) comment '申请人头像',
   apply_user_name      varchar(30) comment '申请人姓名',
   apply_time           datetime comment '申请时间',
   apply_user_phone     varchar(11) comment '申请人手机号',
   display_order        int(11) default 0 comment '顺序号',
   version              int(11) default 0 comment '版本号',
   deleted              int(11) default 1 comment '状态',
   create_user_id       bigint(20) comment '创建人',
   create_user_name     varchar(30) comment '创建人姓名',
   create_time          datetime comment '创建时间',
   update_user_id       bigint(20) comment '修改人',
   update_user_name     varchar(30) comment '更新人姓名',
   update_time          datetime comment '修改时间',
   last_update          timestamp comment '最后更新时间',
   primary key (feedback_id)
);

alter table feedback_info comment '已经反馈';

drop table if exists member_level;

/*==============================================================*/
/* Table: member_level                                          */
/*==============================================================*/
create table member_level
(
   member_level_id      bigint(20) not null auto_increment comment '主键',
   level                varchar(30) comment '会员等级',
   description          varchar(60) comment '等级描述',
   origin_price         bigint(20) comment '原价',
   final_price          bigint(20) comment '折后价',
   months               int(11) comment '月数',
   image_url            varchar(300) comment '背景图',
   order_number         int(11) comment '排序号',
   display_order        int(11) default 0 comment '顺序号',
   version              int(11) default 0 comment '版本号',
   deleted              int(11) default 1 comment '状态',
   create_user_id       bigint(20) comment '创建人',
   create_user_name     varchar(30) comment '创建人姓名',
   create_time          datetime comment '创建时间',
   update_user_id       bigint(20) comment '修改人',
   update_user_name     varchar(30) comment '更新人姓名',
   update_time          datetime comment '修改时间',
   last_update          timestamp comment '最后更新时间',
   primary key (member_level_id)
);

alter table member_level comment '会员价格表';


/*==============================================================*/
/* Table: order_info                                            */
/*==============================================================*/
create table order_info
(
   order_id             bigint(20) not null auto_increment comment '主键',
   order_no             varchar(20) comment '单号',
   amount               bigint(20) comment '订单金额',
   order_status         varchar(30) comment '订单状态',
   level_id             bigint(20) comment '购买项 ID',
   level_name           varchar(30) comment '购买项名称',
   month_number         int(11) comment '月数',
   pay_status           varchar(30) comment '支付状态',
   prepay_id            bigint(20) comment '预支付人ID',
   prepay_name          varchar(30) comment '预支付人名称',
   prepay_time          datetime comment '预支付时间',
   payer_id             bigint(20) comment '支付人ID',
   payer_name           varchar(30) comment '支付人名称',
   payer_time           datetime comment '支付时间',
   display_order        int(11) default 0 comment '顺序号',
   version              int(11) default 0 comment '版本号',
   deleted              int(11) default 1 comment '状态',
   create_user_id       bigint(20) comment '创建人',
   create_user_name     varchar(30) comment '创建人姓名',
   create_time          datetime comment '创建时间',
   update_user_id       bigint(20) comment '修改人',
   update_user_name     varchar(30) comment '更新人姓名',
   update_time          datetime comment '修改时间',
   last_update          timestamp comment '最后更新时间',
   primary key (order_id)
);

alter table order_info comment '购买单';

/*==============================================================*/
/* Table: swiper_info                                           */
/*==============================================================*/
create table swiper_info
(
   swiper_id            bigint(20) not null auto_increment comment '主键',
   title                varchar(100) comment '标题',
   description          varchar(600) comment '描述',
   image                varchar(300) comment '图片路径',
   link                 varchar(300) comment '链接',
   enable_status        varchar(30) comment '启用/禁用',
   type                 varchar(30) comment '类型',
   open_type            varchar(30) comment '打开方式',
   begin_time           datetime comment '开始时间',
   end_time             datetime comment '结束时间',
   display_order        int(11) default 0 comment '顺序号',
   version              int(11) default 0 comment '版本号',
   deleted              int(11) default 1 comment '状态',
   create_user_id       bigint(20) comment '创建人',
   create_user_name     varchar(30) comment '创建人姓名',
   create_time          datetime comment '创建时间',
   update_user_id       bigint(20) comment '修改人',
   update_user_name     varchar(30) comment '更新人姓名',
   update_time          datetime comment '修改时间',
   last_update          timestamp comment '最后更新时间',
   primary key (swiper_id)
);

alter table swiper_info comment '轮播图';

/*==============================================================*/
/* Table: system_config                                         */
/*==============================================================*/
create table system_config
(
   system_config_id     bigint(20) not null auto_increment comment '主键',
   config_key           varchar(60) comment '配置键',
   config_value         varchar(3000) comment '配置值',
   display_order        int(11) default 0 comment '顺序号',
   version              int(11) default 0 comment '版本号',
   deleted              int(11) default 1 comment '状态',
   create_user_id       bigint(20) comment '创建人',
   create_user_name     varchar(30) comment '创建人姓名',
   create_time          datetime comment '创建时间',
   update_user_id       bigint(20) comment '修改人',
   update_user_name     varchar(30) comment '更新人姓名',
   update_time          datetime comment '修改时间',
   last_update          timestamp comment '最后更新时间',
   primary key (system_config_id)
);

alter table system_config comment '系统配置表';

