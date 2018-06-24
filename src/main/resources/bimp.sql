/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50637
Source Host           : 127.0.0.1:3306
Source Database       : bimp

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-06-24 11:05:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apply_info
-- ----------------------------
DROP TABLE IF EXISTS `apply_info`;
CREATE TABLE `apply_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `recordNo` varchar(30) DEFAULT NULL COMMENT '申请的订单编号',
  `nannyNo` varchar(20) DEFAULT NULL COMMENT '保姆编号',
  `occurTime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `recordNo` varchar(30) DEFAULT NULL COMMENT '订单编号',
  `nannyNo` varchar(30) DEFAULT NULL COMMENT '保姆编号',
  `customerNo` varchar(30) DEFAULT NULL COMMENT '客户编号',
  `comment` varchar(255) DEFAULT NULL COMMENT '评价内容',
  `createTime` varchar(19) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for customer_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info` (
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `customerNo` varchar(20) DEFAULT NULL COMMENT '客户编号',
  `customerName` varchar(50) DEFAULT NULL COMMENT '客户姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `pinCard` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `createTime` varchar(19) DEFAULT NULL COMMENT '创建日期',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机',
  `address` varchar(50) DEFAULT NULL COMMENT '客户地址',
  `status` varchar(20) DEFAULT NULL COMMENT '账户状态，0是正常，1是锁定',
  `lastLoginTime` varchar(19) DEFAULT NULL COMMENT '最后一次登录时间',
  `lockTime` varchar(19) DEFAULT NULL COMMENT '锁定时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dictClassificationCode` varchar(50) DEFAULT NULL COMMENT '字典编号',
  `name` varchar(50) DEFAULT NULL COMMENT '字段名称',
  `value` varchar(50) DEFAULT NULL COMMENT '字典值',
  `parentId` varchar(24) DEFAULT NULL COMMENT '字典上级ID',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  `updateDateTime` varchar(19) DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for nanny_info
-- ----------------------------
DROP TABLE IF EXISTS `nanny_info`;
CREATE TABLE `nanny_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `nannyNo` varchar(20) DEFAULT NULL COMMENT '保姆编号',
  `nannyName` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `birthday` varchar(19) DEFAULT NULL COMMENT '生日',
  `pinCard` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `status` varchar(20) DEFAULT NULL COMMENT '账户状况，0，正常，1，已锁定，',
  `tag` varchar(200) DEFAULT NULL COMMENT '个人标签',
  `edu` varchar(20) DEFAULT NULL COMMENT '教育程度',
  `require` varchar(200) DEFAULT NULL COMMENT '职位要求',
  `describe` varchar(200) DEFAULT NULL COMMENT '自我介绍',
  `aim` varchar(50) DEFAULT NULL COMMENT '意向人群',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for record_info
-- ----------------------------
DROP TABLE IF EXISTS `record_info`;
CREATE TABLE `record_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `recordNo` varchar(30) DEFAULT NULL COMMENT '服务编号',
  `customerNo` varchar(20) DEFAULT NULL COMMENT '客户编号',
  `nannyNo` varchar(20) DEFAULT NULL COMMENT '保姆编号',
  `createTime` varchar(19) DEFAULT NULL COMMENT '服务创建时间',
  `startTime` varchar(19) DEFAULT NULL COMMENT '服务开始时间',
  `endTime` varchar(19) DEFAULT NULL COMMENT '服务结束时间',
  `score` varchar(20) DEFAULT NULL COMMENT '评分',
  `describe` varchar(200) DEFAULT NULL COMMENT '工作内容描述',
  `type` varchar(50) DEFAULT NULL COMMENT '订单类型',
  `pay` varchar(10) DEFAULT NULL COMMENT '报酬',
  `workAddress` varchar(50) DEFAULT NULL COMMENT '工作地址',
  `mobile` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `status` varchar(10) DEFAULT NULL COMMENT '状态，0是开启，1是关闭',
  `evaluate` varchar(255) DEFAULT NULL COMMENT '评价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
