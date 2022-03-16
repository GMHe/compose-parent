drop table if exists area;
CREATE TABLE `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country_id` int(11) DEFAULT NULL,
  `code` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `lower_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

INSERT INTO `area` (`id`, `country_id`, `code`, `name`, `cname`, `lower_name`) VALUES ('1', '226', '1', 'New England', '新英格兰地区', 'new england');