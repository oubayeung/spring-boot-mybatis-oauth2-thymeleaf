/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : oauth

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2016-08-31 23:30:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `userId` varchar(100) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('f041e5a1', 'ROLE_ADMIN,ROLE_USER');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `loginId` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('459a02e3', 'Summer', '123456', '霍三娘', '昌平区', '1', '2016-08-31 23:26:55', null, 'ROLE_USER,ROLE_GUEST');
INSERT INTO `user` VALUES ('6226a39c', 'Ammy', '123456', '二月红', '北京市东城区', '1', '2016-08-31 23:24:05', null, 'ROLE_USER,ROLE_GUEST');
INSERT INTO `user` VALUES ('b77e0c74', 'Winter', '123456', '解九爷', '丰台区', '1', '2016-08-31 23:28:31', null, 'ROLE_USER,ROLE_GUEST');
INSERT INTO `user` VALUES ('f041e5a1', 'pual', '123456', '张启山', '北京市海淀区', '1', '2016-08-26 17:41:44', '2016-08-31 23:13:52', 'ROLE_ADMIN,ROLE_USER');
SET FOREIGN_KEY_CHECKS=1;
