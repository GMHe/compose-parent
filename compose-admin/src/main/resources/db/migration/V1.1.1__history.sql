drop table if exists code_history;
CREATE TABLE `code_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '历史表id',
  `history_id` varchar(64) DEFAULT NULL COMMENT '历史记录id',
  `input_data` text COMMENT '输入的数据',
  `ml_code` text COMMENT 'ml表达式',
  `run_code` text COMMENT '可运行代码',
  `cerate_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，1逻辑删除，0未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生成代码记录表';

drop table if exists user_token;
CREATE TABLE `user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户id',
  `user_email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名称',
  `status` tinyint(4) DEFAULT NULL COMMENT '授权状态，1弃用或未授权，0授权成功',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户邮箱授权表';

