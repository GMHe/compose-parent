/*
 Navicat Premium Data Transfer

 Source Server         : 虚拟机—192.168.32.2_8_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 192.168.32.2:3306
 Source Schema         : compose_alert

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 18/04/2022 10:28:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alert
-- ----------------------------
DROP TABLE IF EXISTS `alert`;
CREATE TABLE `alert` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `category_item_id` bigint(20) NOT NULL COMMENT 'category_item表ID',
  `name` varchar(50) NOT NULL COMMENT '规则名称',
  `category_env_id` bigint(20) NOT NULL COMMENT '环境ID',
  `status` tinyint(4) NOT NULL COMMENT '状态：0-禁用；1-启用；2-逻辑删除',
  `filter` varchar(2048) NOT NULL COMMENT '过滤值，用于设置yangjian-alert的告警过滤条件',
  `filter_description` text NOT NULL COMMENT '过滤描述，用于前端展示',
  `threshold` varchar(1024) NOT NULL COMMENT '阈值表达式，用于设置yangjian-alert告警阈值过滤条件',
  `threshold_description` varchar(1024) NOT NULL COMMENT '阈值描述，用于前端展示',
  `config_sync_status` tinyint(4) NOT NULL COMMENT '配置发生变化并需要再次同步时的状态状态：0-待同步；1-已同步；定时任务检查状态并同步到yangjian-alert',
  `config_sync_time` datetime DEFAULT NULL COMMENT '配置发生变更后会异步调用yangjian-alert生效，该时间为生效时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(64) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`),
  KEY `idx_category_item_id` (`category_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='各种场景的告警配置';

-- ----------------------------
-- Records of alert
-- ----------------------------
BEGIN;
INSERT INTO `alert` VALUES (430394610822265344, 7, '测试资源显示', 4, 1, '[{\"appName\":\"devops-alarm-service\",\"namespace\":\"architecture-test\"}, {\"appName\":\"\",\"namespace\":\"architecture-prod\"}]', '[{\"type\":\"namespace\",\"parentType\":\"devops/namespace\",\"value\":\"architecture-prod\",\"parentValue\":\"devops-compass\",\"spaceValue\":\"devops-compass/architecture-prod\",\"key\":\"architecture-prod\",\"automatic\":true,\"isNode\":0},{\"type\":\"appName\",\"parentType\":\"devops/namespace/appName\",\"value\":\"devops-alarm-ui\",\"parentValue\":\"architecture-prod\",\"spaceValue\":\"devops-compass/architecture-prod/devops-alarm-ui\",\"key\":\"devops-alarm-ui\",\"automatic\":false,\"isNode\":1},{\"type\":\"namespace\",\"parentType\":\"devops/namespace\",\"value\":\"architecture-test\",\"parentValue\":\"devops-compass\",\"spaceValue\":\"devops-compass/architecture-test\",\"key\":\"architecture-test\",\"automatic\":false,\"isNode\":0},{\"type\":\"appName\",\"parentType\":\"devops/namespace/appName\",\"value\":\"devops-alarm-service\",\"parentValue\":\"architecture-test\",\"spaceValue\":\"devops-compass/architecture-test/devops-alarm-service\",\"key\":\"devops-alarm-service\",\"automatic\":false,\"isNode\":1}]', '[{\"categoryItemId\":\"7\",\"name\":\"测试资源显示\",\"tip\":[\"Warning\"],\"kind\":[\"ConfigMap\"]}]', '事件类型包含：ConfigMap|事件等级包含：Warning', 1, '2021-07-01 15:55:03', '2021-07-01 15:54:32', '2021-07-01 15:55:03', NULL, NULL);
INSERT INTO `alert` VALUES (432114422711778304, 1, '测试阈值告警链接跳转', 1, 1, '[{\"namespace\":\"architecture-prod\"}, {\"namespace\":\"architecture-test\"}, {\"namespace\":\"architecture-dev\"}]', '[{\"type\":\"namespace\",\"parentType\":\"devops/namespace\",\"value\":\"architecture-prod\",\"parentValue\":\"devops-compass\",\"spaceValue\":\"devops-compass/architecture-prod\",\"key\":\"architecture-prod\",\"automatic\":false,\"isNode\":0},{\"type\":\"namespace\",\"parentType\":\"devops/namespace\",\"value\":\"architecture-test\",\"parentValue\":\"devops-compass\",\"spaceValue\":\"devops-compass/architecture-test\",\"key\":\"architecture-test\",\"automatic\":false,\"isNode\":0},{\"type\":\"namespace\",\"parentType\":\"devops/namespace\",\"value\":\"architecture-dev\",\"parentValue\":\"devops-compass\",\"spaceValue\":\"devops-compass/architecture-dev\",\"key\":\"architecture-dev\",\"automatic\":false,\"isNode\":0}]', '[{\"catItemThresholdName\":\"测试阈值告警链接跳转\",\"unit\":\"%\",\"categoryItemId\":\"1\",\"time\":\"1\",\"text\":\"平均值\",\"expression\":\">=\",\"value\":1,\"categoryItemName\":\"POD的CPU limit\",\"fieldKey\":\"cpuValue\"}]', 'POD的CPU limit 平均值>=1%', 1, '2021-07-06 09:49:06', '2021-07-06 09:48:27', '2021-07-06 09:49:06', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID，自增ID',
  `order_number` varchar(64) DEFAULT NULL COMMENT '订单编号-唯一',
  `outer_trade_no` varchar(64) DEFAULT NULL COMMENT '商户订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '买家ID，可为空',
  `status` tinyint(11) DEFAULT NULL COMMENT '订单状态',
  `pay_amount` decimal(12,2) DEFAULT NULL COMMENT '支付金额',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `total_amount` decimal(12,2) DEFAULT NULL COMMENT '资源金额',
  `resource_id` int(11) DEFAULT NULL COMMENT '资源ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '交易备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_info
-- ----------------------------
BEGIN;
INSERT INTO `order_info` VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `order_info` VALUES (2, '6014cc7a-6d3c-46cc-819d-1d1fc100f466', '18863671493132288', 1, 2, 1.01, '2021-08-09 20:51:56', 1.01, 1, NULL, '2021-08-09 20:51:56', NULL);
INSERT INTO `order_info` VALUES (3, '4edf7108-37f9-453f-a92e-8270331da80b', '18882903224221696', 1, 2, 1.01, '2021-08-09 20:52:34', 1.01, 1, NULL, '2021-08-09 20:52:34', NULL);
INSERT INTO `order_info` VALUES (4, '13ded3e0-9ffa-42e2-982a-cdde2fc86527', '36990372253138944', 1, 2, 1.01, '2021-09-28 20:06:37', 1.01, 1, NULL, '2021-09-28 20:06:37', NULL);
INSERT INTO `order_info` VALUES (5, '13ded3e0-9ffa-42e2-982a-cdde2fc86527', '36990372253138944', 1, 2, 1.01, '2021-09-28 20:06:57', 1.01, 1, NULL, '2021-09-28 20:06:57', NULL);
INSERT INTO `order_info` VALUES (6, '3ee23637-5888-4a1f-942e-8c9a1cb89f04', '36991062690103296', 1, 2, 1.01, '2021-09-28 20:08:01', 1.01, 1, NULL, '2021-09-28 20:08:01', NULL);
COMMIT;

-- ----------------------------
-- Table structure for prov_order
-- ----------------------------
DROP TABLE IF EXISTS `prov_order`;
CREATE TABLE `prov_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID，自增ID',
  `order_number` varchar(64) NOT NULL COMMENT '订单编号-唯一',
  `platform_order_no` varchar(64) DEFAULT NULL COMMENT 'PM支付订单号',
  `resource_id` int(11) NOT NULL COMMENT '资源ID',
  `user_id` int(11) DEFAULT NULL COMMENT '买家ID，可为空',
  `status` tinyint(11) NOT NULL COMMENT '订单状态(0待支付，1支付失败，2支付成功)',
  `pay_amount` decimal(12,2) NOT NULL COMMENT '需要支付金额',
  `pay_amount_actual` decimal(12,2) DEFAULT NULL COMMENT '实际支付金额',
  `valid_time` int(11) NOT NULL COMMENT '订单有效时间',
  `pay_url` varchar(255) DEFAULT NULL COMMENT '支付地址Url',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `resorce_amount` decimal(12,2) NOT NULL COMMENT '资源金额',
  `remark` varchar(255) DEFAULT NULL COMMENT '交易备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`,`order_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prov_order
-- ----------------------------
BEGIN;
INSERT INTO `prov_order` VALUES (1, '1d766482-8fc9-4696-9d45-5c80edd8b8e4', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 15:29:38', NULL);
INSERT INTO `prov_order` VALUES (2, '5a33e1cc-534d-4c7d-9039-096e67373276', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 15:31:10', NULL);
INSERT INTO `prov_order` VALUES (3, '7071b98c-1d49-4bc7-80c4-ce01d5700ebb', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 15:45:47', NULL);
INSERT INTO `prov_order` VALUES (4, 'b793ce77-372b-4e9a-865d-b0bd72a702ca', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 15:47:53', NULL);
INSERT INTO `prov_order` VALUES (5, '70cdf9cf-4edb-4fdb-b684-8b9d8c891a77', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 15:48:51', NULL);
INSERT INTO `prov_order` VALUES (6, 'fd4bb4c9-b42c-4fed-a31e-97e053ab0f0e', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 15:49:08', NULL);
INSERT INTO `prov_order` VALUES (7, '8f697707-1c86-447e-ae21-7a109b1ba355', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 15:49:53', NULL);
INSERT INTO `prov_order` VALUES (8, 'b5e68a81-2a53-4c44-90a1-187366ac2cd2', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 15:50:59', NULL);
INSERT INTO `prov_order` VALUES (9, '0dc00592-2079-4e5a-a49a-95bf9a2d9275', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 15:53:49', NULL);
INSERT INTO `prov_order` VALUES (10, '01ab20ab-a57e-442a-863e-8d6f5248b633', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 15:54:49', NULL);
INSERT INTO `prov_order` VALUES (11, '953b6a05-9173-41c8-83a7-b686ed75965a', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 16:15:15', NULL);
INSERT INTO `prov_order` VALUES (12, '772cf68f-dcdc-438c-aed0-00d3de3cf554', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 16:15:27', NULL);
INSERT INTO `prov_order` VALUES (13, 'dab6b3f9-1df9-4186-9b73-c532a13db990', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 16:16:41', NULL);
INSERT INTO `prov_order` VALUES (14, '7bbadcff-8dce-48ff-b9ec-a4eeeed85b2d', NULL, 1, 1, 0, 1.00, NULL, 5, NULL, NULL, 1.00, NULL, '2021-08-08 16:25:59', NULL);
INSERT INTO `prov_order` VALUES (15, 'dbf38fd8-d8b4-4bdc-ae58-eea3cb553ed5', NULL, 1, 1, 0, 1.01, NULL, 5, NULL, NULL, 1.01, NULL, '2021-08-08 16:27:25', NULL);
INSERT INTO `prov_order` VALUES (16, '1ac5eadb-ee2f-4268-9acb-a8aa108e87df', NULL, 1, 1, 0, 1.01, NULL, 5, NULL, NULL, 1.01, NULL, '2021-08-08 16:47:23', NULL);
INSERT INTO `prov_order` VALUES (17, 'f04a2113-aa52-44aa-ba08-2ee6b7652f1e', NULL, 1, 1, 0, 1.01, NULL, 5, NULL, NULL, 1.01, NULL, '2021-08-08 16:58:53', NULL);
INSERT INTO `prov_order` VALUES (18, 'f5da6c99-7cd7-4987-93b4-28caf9820812', NULL, 1, 1, 0, 1.01, NULL, 5, NULL, NULL, 1.01, NULL, '2021-08-08 17:08:14', NULL);
INSERT INTO `prov_order` VALUES (19, '926962bd-5a11-4d28-991c-4b6decae878d', '18464422892797952', 1, 1, 0, 1.01, NULL, 5, 'https://page-4gttfzlfpj4.zhifu.fm.it88168.com/pay?orderNo=18464422892797952', NULL, 1.01, NULL, '2021-08-08 17:09:18', NULL);
INSERT INTO `prov_order` VALUES (20, '6014cc7a-6d3c-46cc-819d-1d1fc100f466', '18863671493132288', 1, 1, 2, 1.01, 1.01, 5, 'https://page-4gttfzlfpj4.zhifu.fm.it88168.com/pay?orderNo=18863671493132288', '2021-08-09 20:51:56', 1.01, NULL, '2021-08-09 19:35:43', NULL);
INSERT INTO `prov_order` VALUES (21, 'a2c0c5c3-8994-4b2e-ae06-077e0327327f', NULL, 1, 1, 0, 1.01, NULL, 5, NULL, NULL, 1.01, NULL, '2021-08-09 19:50:46', NULL);
INSERT INTO `prov_order` VALUES (22, '14cb5482-8683-4fd0-97eb-833c16f65448', '18873299610238976', 1, 1, 0, 1.01, NULL, 5, 'https://page-4gttfzlfpj4.zhifu.fm.it88168.com/pay?orderNo=18873299610238976', NULL, 1.01, NULL, '2021-08-09 20:13:59', NULL);
INSERT INTO `prov_order` VALUES (23, '4edf7108-37f9-453f-a92e-8270331da80b', '18882903224221696', 1, 1, 2, 1.01, 1.01, 5, 'https://page-4gttfzlfpj4.zhifu.fm.it88168.com/pay?orderNo=18882903224221696', '2021-08-09 20:52:34', 1.01, NULL, '2021-08-09 20:52:09', NULL);
INSERT INTO `prov_order` VALUES (24, '59f265c5-e189-418e-9a8d-ef48137b7de4', NULL, 1, 1, 0, 1.01, NULL, 5, NULL, NULL, 1.01, NULL, '2021-09-28 19:40:24', NULL);
INSERT INTO `prov_order` VALUES (25, 'a10a260e-4abc-479d-bebe-aa6ddd5ba88d', '36984633413468160', 1, 1, 0, 1.01, NULL, 5, 'https://page-4gttfzlfpj4.zhifu.fm.it88168.com/pay?orderNo=36984633413468160', NULL, 1.01, NULL, '2021-09-28 19:42:00', NULL);
INSERT INTO `prov_order` VALUES (26, '13ded3e0-9ffa-42e2-982a-cdde2fc86527', '36990372253138944', 1, 1, 2, 1.01, 1.01, 5, 'https://page-4gttfzlfpj4.zhifu.fm.it88168.com/pay?orderNo=36990372253138944', '2021-09-28 20:06:57', 1.01, NULL, '2021-09-28 20:04:48', NULL);
INSERT INTO `prov_order` VALUES (27, '3ee23637-5888-4a1f-942e-8c9a1cb89f04', '36991062690103296', 1, 1, 2, 1.01, 1.01, 5, 'https://page-4gttfzlfpj4.zhifu.fm.it88168.com/pay?orderNo=36991062690103296', '2021-09-28 20:08:01', 1.01, NULL, '2021-09-28 20:07:33', NULL);
INSERT INTO `prov_order` VALUES (28, '478966618454758144', '53246876027322368', 1, 1, 0, 1.01, NULL, 5, 'https://page-4gttfzlfpj4.zhifu.fm.it88168.com/pay?orderNo=53246876027322368', NULL, 1.01, NULL, '2021-11-12 16:42:21', NULL);
INSERT INTO `prov_order` VALUES (29, '478975709101310976', '53255966015225856', 1, 1, 0, 1.01, NULL, 5, 'https://page-4gttfzlfpj4.zhifu.fm.it88168.com/pay?orderNo=53255966015225856', NULL, 1.01, NULL, '2021-11-12 17:18:28', NULL);
COMMIT;

-- ----------------------------
-- Table structure for resource_base
-- ----------------------------
DROP TABLE IF EXISTS `resource_base`;
CREATE TABLE `resource_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `resource_id` int(11) NOT NULL COMMENT '资源ID',
  `resource_type_id` int(11) NOT NULL COMMENT '资源类型ID',
  `resource_name` varchar(255) NOT NULL COMMENT '资源名称',
  `resource_url` varchar(255) NOT NULL COMMENT '资源百度云盘链接',
  `download_pwd` varchar(255) NOT NULL COMMENT '资源百度云盘密码',
  `resource_password` varchar(255) NOT NULL COMMENT '资源解压私钥',
  `status` tinyint(4) NOT NULL COMMENT '资源状态(1启用，0禁用)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_base
-- ----------------------------
BEGIN;
INSERT INTO `resource_base` VALUES (1, 1, 1, '小马哥spring boot核心思想', 'http://www.baidu.com', '', '123456', 1, '2021-08-04 19:48:51', '2021-08-04 19:48:53');
COMMIT;

-- ----------------------------
-- Table structure for resource_detail
-- ----------------------------
DROP TABLE IF EXISTS `resource_detail`;
CREATE TABLE `resource_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `resource_id` int(11) DEFAULT NULL COMMENT '资源ID',
  `price` decimal(10,2) DEFAULT NULL COMMENT '售价',
  `describe` varchar(255) DEFAULT NULL COMMENT '商品详细描述',
  `image_url` varchar(255) DEFAULT NULL COMMENT '商品图片URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_detail
-- ----------------------------
BEGIN;
INSERT INTO `resource_detail` VALUES (1, 1, 1.01, '技术资料描述描述', 'url');
COMMIT;

-- ----------------------------
-- Table structure for resource_type
-- ----------------------------
DROP TABLE IF EXISTS `resource_type`;
CREATE TABLE `resource_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `resource_type_id` int(11) NOT NULL COMMENT '资源类型ID',
  `type_name` varchar(64) NOT NULL COMMENT '资源类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_type
-- ----------------------------
BEGIN;
INSERT INTO `resource_type` VALUES (1, 1, 'Java技术资料');
COMMIT;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(11) DEFAULT NULL COMMENT '商品Id',
  `num` bigint(11) DEFAULT NULL COMMENT '数量',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户唯一Id',
  `create_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '订单状态 1 未付款 2 已付款 3 已完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=480335079127023617 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
INSERT INTO `t_order` VALUES (480334426690453504, 1, 2, 'abc123', '2021-11-16 11:17:32', 2);
INSERT INTO `t_order` VALUES (480335079127023616, 1, 2, 'abc123', '2021-11-16 11:20:07', 2);
COMMIT;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='AT transaction mode undo table';

-- ----------------------------
-- Records of undo_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `user_name` varchar(20) NOT NULL COMMENT '名字',
  `account` varchar(20) NOT NULL COMMENT '账号',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `password` varchar(16) NOT NULL COMMENT '密码',
  `status` tinyint(11) NOT NULL COMMENT '账号状态',
  `vip` tinyint(11) NOT NULL COMMENT '是否是VIP',
  `create_by` varchar(16) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(16) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `account_index` (`account`) USING BTREE COMMENT '账号索引'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 1, 'user1', 'user1', '123', '123456', 1, 0, 'admin', '2021-08-04 19:50:08', 'admin', '2021-08-04 19:50:21');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
