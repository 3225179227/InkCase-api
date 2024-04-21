/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50721
 Source Host           : localhost
 Source Database       : blog

 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 04/28/2018 09:14:32 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL,
  `comment_counts` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `summary` varchar(100) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `view_counts` int(11) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `body_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKndx2m69302cso79y66yxiju4h` (`author_id`),
  KEY `FKrd11pjsmueckfrh9gs7bc6374` (`body_id`),
  KEY `FKjrn3ua4xmiulp8raj7m9d2xk6` (`category_id`),
  CONSTRAINT `FKjrn3ua4xmiulp8raj7m9d2xk6` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKndx2m69302cso79y66yxiju4h` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrd11pjsmueckfrh9gs7bc6374` FOREIGN KEY (`body_id`) REFERENCES `article_body` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `me_article_body`
-- ----------------------------
DROP TABLE IF EXISTS `article_body`;
CREATE TABLE `article_body` (
  `id` bigint(20) NOT NULL,
  `content` longtext,
  `content_html` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `me_category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `categoryname` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `me_comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `to_uid` bigint(20) DEFAULT NULL,
  `level` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKecq0fuo9k0lnmea6r01vfhiok` (`article_id`),
  KEY `FKkvuyh6ih7dt1rfqhwsjomsa6i` (`author_id`),
  KEY `FKaecafrcorkhyyp1luffinsfqs` (`parent_id`),
  KEY `FK73dgr23lbs3ebex5qvqyku308` (`to_uid`),
  CONSTRAINT `FK73dgr23lbs3ebex5qvqyku308` FOREIGN KEY (`to_uid`) REFERENCES `user` (`id`),
  CONSTRAINT `FKaecafrcorkhyyp1luffinsfqs` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKecq0fuo9k0lnmea6r01vfhiok` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `FKkvuyh6ih7dt1rfqhwsjomsa6i` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `account` varchar(64) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `mobile_phone_number` varchar(20) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_awpog86ljqwb89aqa1c5gvdrd` (`account`),
  UNIQUE KEY `UK_ahtq5ew3v0kt1n7hf1sgp7p8l` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
