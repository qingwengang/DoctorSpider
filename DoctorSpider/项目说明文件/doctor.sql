/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50162
Source Host           : localhost:3306
Source Database       : doctor

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2016-06-15 18:04:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `DoctorId` varchar(50) DEFAULT NULL,
  `QuestionId` bigint(20) DEFAULT NULL,
  `Content` text,
  `AnswerTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for askquestion
-- ----------------------------
DROP TABLE IF EXISTS `askquestion`;
CREATE TABLE `askquestion` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Type` varchar(30) DEFAULT NULL,
  `OutId` varchar(30) DEFAULT NULL,
  `AuthorTime` datetime DEFAULT NULL,
  `SpiderFlag` int(10) DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=150779 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for doctorinfo
-- ----------------------------
DROP TABLE IF EXISTS `doctorinfo`;
CREATE TABLE `doctorinfo` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `DocId` varchar(50) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Title` varchar(200) DEFAULT NULL,
  `Expertise` varchar(100) DEFAULT NULL,
  `LastSpiderTime` datetime DEFAULT NULL,
  `Type` int(11) DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6845 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hdfdoctorinfo
-- ----------------------------
DROP TABLE IF EXISTS `hdfdoctorinfo`;
CREATE TABLE `hdfdoctorinfo` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) DEFAULT NULL,
  `HospitalName` varchar(50) DEFAULT NULL,
  `Major` varchar(20) DEFAULT NULL,
  `OutId` varchar(50) DEFAULT NULL,
  `Title` varchar(30) DEFAULT NULL,
  `GetContentFlag` int(11) DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hdfmulu
-- ----------------------------
DROP TABLE IF EXISTS `hdfmulu`;
CREATE TABLE `hdfmulu` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Url` varchar(200) DEFAULT NULL,
  `SpiderFlag` int(11) DEFAULT NULL,
  `Level` int(11) DEFAULT NULL,
  `TagName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2957 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hdfpage
-- ----------------------------
DROP TABLE IF EXISTS `hdfpage`;
CREATE TABLE `hdfpage` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Riqi` varchar(20) DEFAULT NULL,
  `Url` varchar(255) DEFAULT NULL,
  `SpiderFlag` int(11) DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3055 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hdfpagedetail
-- ----------------------------
DROP TABLE IF EXISTS `hdfpagedetail`;
CREATE TABLE `hdfpagedetail` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Url` varchar(255) DEFAULT NULL,
  `SpiderFlag` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jibing
-- ----------------------------
DROP TABLE IF EXISTS `jibing`;
CREATE TABLE `jibing` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Suoxie` varchar(50) DEFAULT NULL,
  `Jianjie` text,
  `Zhengzhuang` text,
  `FBYY` text,
  `GetContentFlag` int(11) DEFAULT '0',
  `Url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8091 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jjquestion
-- ----------------------------
DROP TABLE IF EXISTS `jjquestion`;
CREATE TABLE `jjquestion` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `TypeName` varchar(50) DEFAULT NULL,
  `OutId` bigint(20) DEFAULT NULL,
  `SpiderFlag` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=123664 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jkquestion
-- ----------------------------
DROP TABLE IF EXISTS `jkquestion`;
CREATE TABLE `jkquestion` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `TypeName` varchar(50) DEFAULT NULL,
  `OutId` bigint(20) DEFAULT NULL,
  `SpiderFlag` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=123664 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mulu
-- ----------------------------
DROP TABLE IF EXISTS `mulu`;
CREATE TABLE `mulu` (
  `Id` bigint(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) DEFAULT NULL,
  `Url` varchar(255) DEFAULT NULL,
  `Source` varchar(20) DEFAULT NULL,
  `SpiderFlag` int(11) DEFAULT NULL,
  `Level` int(11) DEFAULT NULL,
  `ParentId` bigint(11) DEFAULT NULL,
  `Type` varchar(20) DEFAULT NULL,
  `AskId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1984 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Title` varchar(2000) DEFAULT NULL,
  `Content` text,
  `Type` varchar(50) DEFAULT NULL,
  `GetContentFlag` int(11) DEFAULT '0',
  `AskId` bigint(20) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `NeedHelp` varchar(255) DEFAULT NULL,
  `AuthorId` varchar(50) DEFAULT NULL,
  `AuthorTime` datetime DEFAULT NULL,
  `LastSpiderTime` datetime DEFAULT NULL,
  `AuthorSex` varchar(20) DEFAULT NULL,
  `Laiyuan` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
