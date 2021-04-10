drop table if exists t_fund;
CREATE TABLE `t_fund` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fund_no` varchar(30) NOT NULL,
  `name` varchar(100)  DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` tinyint DEFAULT NULL COMMENT '基金类型：1股票型，2混合型，3债券型',
  `mark` tinyint DEFAULT '0' COMMENT '是否有执行过 0，未执行，1 已执行',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fund_no` (`fund_no`)
) ENGINE=InnoDB AUTO_INCREMENT=13445 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


drop table if exists t_fund_gp;
CREATE TABLE `t_fund_gp` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fund_no` varchar(30) unique NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

drop table if exists t_fund_detail;
CREATE TABLE `t_fund_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fund_no` varchar(30) COLLATE utf8_unicode_ci NOT NULL comment '基金编号',
  `total_money` varchar(60) DEFAULT NULL comment '基金规模:金额',
  `manager` varchar(60) DEFAULT NULL comment '基金管理人',
  `manage_company` varchar(60) DEFAULT NULL comment '基金管理公司',
  `open_date` varchar(60) DEFAULT NULL comment '基金成立日期',
  `net_worth_estimation_time` varchar(60) DEFAULT NULL comment '净值估算时间',
  `net_worth_estimation_value` varchar(60) DEFAULT NULL  comment '净值估算',
  `net_value_of_unit_time` varchar(60) DEFAULT NULL comment '最新单位净值时间',
  `net_value_of_unit` varchar(60) DEFAULT NULL comment '最新单位净值',
  `cumulative_net_value` varchar(60) DEFAULT NULL comment '最新累计单位净值',
  `last_day_increase` varchar(60) DEFAULT NULL comment '近1日涨幅',
  `last_month_increase` varchar(60) DEFAULT NULL comment '近1月涨幅',
  `three_month_increase` varchar(60) DEFAULT NULL comment '近3月涨幅',
  `six_month_increase` varchar(60) DEFAULT NULL comment '近6月涨幅',
  `last_year_increase` varchar(60) DEFAULT NULL comment '近1年涨幅',
  `three_year_increase` varchar(60) DEFAULT NULL comment '近3年涨幅',
  `total_increase` varchar(60) DEFAULT NULL comment '成立来涨幅',
  `stock_last_time` varchar(60) DEFAULT NULL comment '持仓截止日期',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `total_money_value` decimal(10,2) DEFAULT NULL,
  `last_month_increase_value` decimal(10,2) DEFAULT NULL,
  `three_month_increase_value` decimal(10,2) DEFAULT NULL,
  `six_month_increase_value` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

drop table if exists t_fund_stock;
CREATE TABLE `t_fund_stock` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fund_no` varchar(30)  NOT NULL comment '基金编号',
  `stock_code` varchar(60) DEFAULT NULL comment '股票代码',
  `stock_name` varchar(60) DEFAULT NULL comment '股票名称',
  `position_ratio` varchar(60) DEFAULT NULL comment '持仓比例',
  `daily_rise_and_fall` varchar(60) DEFAULT NULL comment '当日涨跌幅',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `position_ratio_value` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
