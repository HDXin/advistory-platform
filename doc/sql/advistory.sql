/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/16 0:28:34                            */
/*==============================================================*/


drop table if exists admin_user;

drop table if exists application_user;

drop table if exists article_info;

drop table if exists article_item;

drop table if exists favorite_info;

drop table if exists feedback_info;

drop table if exists member_level;

drop table if exists order_info;

drop table if exists system_article_info;

drop table if exists system_article_item;

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
   article_id           bigint(20) not null auto_increment comment '主键',
   article_type         varchar(30) comment '文章类型',
   title                varchar(60) comment '标题',
   digest               varchar(300) comment '摘要',
   favorite_number      int(11) default 0 comment '收藏数',
   read_number          int(11) default 0 comment '阅读数',
   publish_time         datetime comment '发布时间(搜索范围判断字段)',
   cover_image          varchar(300) comment '封面图',
   author               varchar(30) comment '作者',
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

alter table article_info comment '文章模块';

/*==============================================================*/
/* Table: article_item                                          */
/*==============================================================*/
create table article_item
(
   article_item_id      bigint(20) not null auto_increment comment '主键',
   article_id           bigint(20) comment '文章ID',
   order_number         int(11) comment '排序号',
   article_item_type    varchar(30) comment '类型',
   content              varchar(3000) comment '文本',
   image                varchar(300) comment '图片',
   linke                varchar(300) comment '跳转',
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
   primary key (article_item_id)
);

alter table article_item comment '文章详情表';

/*==============================================================*/
/* Table: favorite_info                                         */
/*==============================================================*/
create table favorite_info
(
   favorite_id          bigint(20) not null auto_increment comment '主键',
   user_id              bigint(20) comment '用户ID',
   relation_type        varchar(30) comment '类型(ARTICLE:文章)',
   relation_id          bigint(20) comment '业务ID',
   title                varchar(60) comment '标题',
   enable               tinyint(1) comment '收藏是否可用',
   favorite_status      varchar(30) comment '收藏状态(ADD_FAVORITE:添加收藏, CANCEL_FAVORITE:取消收藏)',
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

/*==============================================================*/
/* Table: member_level                                          */
/*==============================================================*/
create table member_level
(
   member_level_id      bigint(20) not null auto_increment comment '主键',
   description          varchar(100) comment '等级描述',
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

alter table member_level comment '会员等级表';

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
/* Table: system_article_info                                   */
/*==============================================================*/
create table system_article_info
(
   system_article_id    bigint(20) not null auto_increment comment '主键',
   title                varchar(60) comment '标题',
   code                 varchar(30) comment '编码',
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
   primary key (system_article_id)
);

alter table system_article_info comment '系统文章';

/*==============================================================*/
/* Table: system_article_item                                   */
/*==============================================================*/
create table system_article_item
(
   system_article_item_id bigint(20) not null auto_increment,
   system_article_id    bigint(20),
   article_item_type    varchar(30),
   content              varchar(3000),
   image_url            varchar(300),
   link                 varchar(300),
   order_number         int(11),
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
   primary key (system_article_item_id)
);

alter table system_article_item comment '系统文章详情';

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

