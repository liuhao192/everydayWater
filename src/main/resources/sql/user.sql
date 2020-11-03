drop table if exists t_sys_user;
create table t_sys_user
(
   id   varchar(64) not null comment '主键id',
   user_name         varchar(64)  comment '用户账号',
   real_name            varchar(64)  comment '在线状态(0 正常 1异常)',
   password               varchar(64)  comment '密码',
   nick_name       varchar(64)  comment '昵称',
   avatar_url        varchar(255) comment '微信头像',
   gender        int(1) comment '性别(0-默认未知,1-男,2-女)',
   phone               varchar(64) comment '电话',
   status              int(1) comment '状态(1-正常,2-冻结)',
   open_id     varchar(64)  comment '微信的用户open_id',
   union_id     varchar(64)  comment '微信的用户唯一标识',
   creator              varchar(64) comment '记录创建人',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '记录创建时间',
   update_person        varchar(64)  comment '最后更新人',
   update_date          datetime  comment '最后更新时间',
   delete_flag          char(1) not null comment '删除标识',
   primary key (id)
);
alter table t_sys_user  comment '系统用户表';

drop table if exists water_num_config;
create table water_num_config
(
   id   varchar(64) not null comment '主键id',
   open_id         varchar(64)  comment '微信的用户open_id',
   num                 int(4) comment '喝水数量',
   ml_num                 int(4) comment '喝水毫升',
   status              int(1) comment '状态(1-正常,2-废弃)',
   creator              varchar(64) comment '记录创建人',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '记录创建时间',
   update_person        varchar(64)  comment '最后更新人',
   update_date          datetime  comment '最后更新时间',
   delete_flag          char(1) not null comment '删除标识',
   primary key (id)
);
alter table water_num_config  comment '用户喝水数量配置表';

drop table if exists water_user_day;
create table water_user_day
(
   id   varchar(64) not null comment '主键id',
   open_id         varchar(64)  comment '微信的用户open_id',
   num                 int(4) comment '喝水数量(累计)',
   ml_num                 int(4) comment '喝水毫升(累计)',
   standard                 int(1) comment '是否达标(0未达标，1达标)',
   day_date             varchar(64) comment '日期(yyyy-MM-dd)',
   creator              varchar(64) comment '记录创建人',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '记录创建时间',
   update_person        varchar(64)  comment '最后更新人',
   update_date          datetime  comment '最后更新时间',
   delete_flag          char(1) not null comment '删除标识',
   primary key (id)
);
alter table water_user_day  comment '用户喝水每天统计表';


drop table if exists water_user_count;
create table water_user_count
(
   id   varchar(64) not null comment '主键id',
   open_id         varchar(64)  comment '微信的用户open_id',
   drink_day                 int(8) comment '喝水天数',
   drink_num                 int(8) comment '喝水次数',
   standard_day                int(8) comment '达标天数',
   creator              varchar(64) comment '记录创建人',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '记录创建时间',
   update_person        varchar(64)  comment '最后更新人',
   update_date          datetime  comment '最后更新时间',
   delete_flag          char(1) not null comment '删除标识',
   primary key (id)
);
alter table water_user_count  comment '用户喝水统计表';


drop table if exists water_user_log;
create table water_user_log
(
   id   bigint(11) NOT NULL AUTO_INCREMENT comment '主键id',
   open_id         varchar(64)  comment '微信的用户open_id',
   num                 int(4) comment '喝水数量',
   ml_num                 int(4) comment '喝水毫升',
   drink_time                 datetime comment '喝水时间',
   creator              varchar(64) comment '记录创建人',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '记录创建时间',
   update_person        varchar(64)  comment '最后更新人',
   update_date          datetime  comment '最后更新时间',
   delete_flag          char(1) not null comment '删除标识',
   primary key (id)
);
alter table water_user_log  comment '用户喝水日志表';


drop table if exists user_remind_config;
create table user_remind_config
(
id   varchar(64) not null comment '主键id',
   open_id         varchar(64)  comment '微信的用户open_id',
   is_remind                int(1) comment '是否开启提醒',
   start_time                 time comment '开始时间',
   end_time                 time comment '结束时间',
   lunch_start_time                 time comment '午休开始时间',
   lunch_end_time                 time comment '午休结束时间',
   is_lunch                 int(1) comment '是否启用午休模式',
   remind_mode              int(1) comment '提醒模式',
   remind_interval         int(4) comment '提醒时间间隔',
   creator              varchar(64) comment '记录创建人',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '记录创建时间',
   update_person        varchar(64)  comment '最后更新人',
   update_date          datetime  comment '最后更新时间',
   delete_flag          char(1) not null comment '删除标识',
   primary key (id)
);
alter table user_remind_config  comment '用户提醒配置表';


drop table if exists user_remind_log;
create table user_remind_log
(
   id   bigint(11) NOT NULL AUTO_INCREMENT comment '主键id',
   open_id         varchar(64)  comment '微信的用户open_id',
  info_id      varchar(64)  comment '信息的id',
   is_read      int(1) comment '是否已读',
   remind_time                datetime comment '提醒时间',
   remind_content             varchar(255)  comment '提醒内容',
   is_success                int(1) comment '是否成功提醒',
   creator              varchar(64) comment '记录创建人',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '记录创建时间',
   update_person        varchar(64)  comment '最后更新人',
   update_date          datetime  comment '最后更新时间',
   delete_flag          char(1) not null comment '删除标识',
   primary key (id)
);
alter table user_remind_log  comment '用户提醒日志表';

drop table if exists user_remind_data;
create table user_remind_data
(
 id   varchar(64) not null comment '主键id',
   open_id         varchar(64)  comment '微信的用户open_id',
   remind_time      time comment '提醒时间',
   creator              varchar(64) comment '记录创建人',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '记录创建时间',
   update_person        varchar(64)  comment '最后更新人',
   update_date          datetime  comment '最后更新时间',
   delete_flag          char(1) not null comment '删除标识',
   primary key (id)
);
alter table user_remind_data  comment '用户提醒信息表';


drop table if exists user_remind_subscribe;
create table user_remind_subscribe
(
 id   varchar(64) not null comment '主键id',
   open_id         varchar(64)  comment '微信的用户open_id',
   template_id      varchar(64) comment '提醒模板id',
   is_subscribe      int(1) comment '是否订阅 0已订阅 1 未订阅',
   creator              varchar(64) comment '记录创建人',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '记录创建时间',
   update_person        varchar(64)  comment '最后更新人',
   update_date          datetime  comment '最后更新时间',
   delete_flag          char(1) not null comment '删除标识',
   primary key (id)
);
alter table user_remind_subscribe  comment '用户订阅表';


