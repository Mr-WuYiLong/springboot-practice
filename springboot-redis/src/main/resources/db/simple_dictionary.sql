/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2024-07-18 13:50:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for simple_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `simple_dictionary`;
CREATE TABLE `simple_dictionary` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '编码',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of simple_dictionary
-- ----------------------------
INSERT INTO `simple_dictionary` VALUES ('1', 'education-level', '教育程度', '0', '2024-07-11 15:18:07');
INSERT INTO `simple_dictionary` VALUES ('2', 'education-level', '大专', '1', null);
INSERT INTO `simple_dictionary` VALUES ('3', 'education-level', '本科', '1', '2024-07-11 15:19:58');
INSERT INTO `simple_dictionary` VALUES ('4', 'education-level', '硕士', '1', null);
INSERT INTO `simple_dictionary` VALUES ('5', 'education-level', '博士', '1', null);
INSERT INTO `simple_dictionary` VALUES ('6', 'education-level', '高中', '1', null);
INSERT INTO `simple_dictionary` VALUES ('7', 'education-level', '初中', '1', null);
INSERT INTO `simple_dictionary` VALUES ('8', 'education-level', '小学', '1', null);
INSERT INTO `simple_dictionary` VALUES ('9', 'education-level', '幼儿园', '1', null);
INSERT INTO `simple_dictionary` VALUES ('10', 'education-level', '学术硕士', '4', null);
INSERT INTO `simple_dictionary` VALUES ('11', 'education-level', '专业硕士', '4', null);
