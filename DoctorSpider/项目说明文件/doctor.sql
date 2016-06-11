/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50096
Source Host           : 127.0.0.1:3306
Source Database       : doctor

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2016-06-11 21:49:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `Id` bigint(20) NOT NULL auto_increment,
  `DoctorId` varchar(50) default NULL,
  `QuestionId` bigint(20) default NULL,
  `Content` text,
  `AnswerTime` datetime default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for askquestion
-- ----------------------------
DROP TABLE IF EXISTS `askquestion`;
CREATE TABLE `askquestion` (
  `Id` bigint(20) NOT NULL auto_increment,
  `Type` varchar(30) default NULL,
  `OutId` varchar(30) default NULL,
  `AuthorTime` datetime default NULL,
  `SpiderFlag` int(10) default '0',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1187405 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for doctorinfo
-- ----------------------------
DROP TABLE IF EXISTS `doctorinfo`;
CREATE TABLE `doctorinfo` (
  `Id` bigint(20) NOT NULL auto_increment,
  `DocId` varchar(50) default NULL,
  `Name` varchar(50) default NULL,
  `Title` varchar(200) default NULL,
  `Expertise` varchar(100) default NULL,
  `LastSpiderTime` datetime default NULL,
  `Type` int(11) default '0',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=162104 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fhquestion
-- ----------------------------
DROP TABLE IF EXISTS `fhquestion`;
CREATE TABLE `fhquestion` (
  `Id` bigint(20) NOT NULL auto_increment,
  `OutId` varchar(20) default NULL,
  `MuluId` bigint(20) default NULL,
  `MuluName` varchar(20) default NULL,
  `SpiderFlag` int(11) default NULL,
  `WriteFileFlag` int(11) default '0',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2000001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hdfdoctorinfo
-- ----------------------------
DROP TABLE IF EXISTS `hdfdoctorinfo`;
CREATE TABLE `hdfdoctorinfo` (
  `Id` bigint(20) NOT NULL auto_increment,
  `Name` varchar(20) default NULL,
  `HospitalName` varchar(50) default NULL,
  `Major` varchar(20) default NULL,
  `OutId` varchar(50) default NULL,
  `Title` varchar(30) default NULL,
  `GetContentFlag` int(11) default '0',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hdfmulu
-- ----------------------------
DROP TABLE IF EXISTS `hdfmulu`;
CREATE TABLE `hdfmulu` (
  `Id` bigint(20) NOT NULL auto_increment,
  `Name` varchar(50) default NULL,
  `Url` varchar(200) default NULL,
  `SpiderFlag` int(11) default NULL,
  `Level` int(11) default NULL,
  `TagName` varchar(50) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1479 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hdfpage
-- ----------------------------
DROP TABLE IF EXISTS `hdfpage`;
CREATE TABLE `hdfpage` (
  `Id` bigint(20) NOT NULL auto_increment,
  `Riqi` varchar(20) default NULL,
  `Url` varchar(255) default NULL,
  `SpiderFlag` int(11) default '0',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3055 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jibing
-- ----------------------------
DROP TABLE IF EXISTS `jibing`;
CREATE TABLE `jibing` (
  `Id` bigint(20) NOT NULL auto_increment,
  `Name` varchar(50) default NULL,
  `Suoxie` varchar(50) default NULL,
  `Jianjie` text,
  `Zhengzhuang` text,
  `FBYY` text,
  `GetContentFlag` int(11) default '0',
  `Url` varchar(255) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8091 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mulu
-- ----------------------------
DROP TABLE IF EXISTS `mulu`;
CREATE TABLE `mulu` (
  `Id` bigint(11) NOT NULL auto_increment,
  `Name` varchar(20) default NULL,
  `Url` varchar(255) default NULL,
  `Source` varchar(20) default NULL,
  `SpiderFlag` int(11) default NULL,
  `Level` int(11) default NULL,
  `ParentId` bigint(11) default NULL,
  `Type` varchar(20) default NULL,
  `AskId` int(11) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=897 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `Id` bigint(20) NOT NULL auto_increment,
  `Title` varchar(2000) default NULL,
  `Content` text,
  `Type` varchar(50) default NULL,
  `GetContentFlag` int(11) default '0',
  `AskId` bigint(20) default NULL,
  `Description` varchar(255) default NULL,
  `NeedHelp` varchar(255) default NULL,
  `AuthorId` varchar(50) default NULL,
  `AuthorTime` datetime default NULL,
  `LastSpiderTime` datetime default NULL,
  `AuthorSex` varchar(20) default NULL,
  `Laiyuan` varchar(20) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
