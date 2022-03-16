drop table if exists source;
CREATE TABLE `source` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `source_group_id` int(11) DEFAULT NULL COMMENT '分组',
  `sign` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT '' COMMENT '消费标识',
  `partition_number` int(11) DEFAULT NULL COMMENT '分区个数',
  `replication_factor` int(11) DEFAULT NULL,
  `hash_key` varchar(255) DEFAULT '' COMMENT '消费指定数据key',
  `key_type_mapping` varchar(255) DEFAULT NULL,
  `last_input_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

INSERT INTO `source` (`id`, `source_group_id`, `sign`, `name`, `partition_number`, `replication_factor`, `hash_key`, `key_type_mapping`, `last_input_time`, `remark`, `create_time`, `update_time`) VALUES ('1', '1', 'compose-admin', 'admin服务消费', '1', '1', 'name', NULL, NULL, NULL, NULL, NULL);