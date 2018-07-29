/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50636
Source Host           : 127.0.0.1:3306
Source Database       : winy_project

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: xxxx
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_no` varchar(50) NOT NULL COMMENT '订单编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(12) DEFAULT '0' COMMENT '0 待支付  1已支付  2已失效 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
