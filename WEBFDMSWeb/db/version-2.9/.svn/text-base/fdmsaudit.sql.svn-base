/*
SQLyog - Free MySQL GUI v5.18
Host - 5.0.19-nt : Database - fdmsaudit
*********************************************************************
Server version : 5.0.19-nt
*/

SET NAMES utf8;

SET SQL_MODE='';

create database if not exists `fdmsaudit`;

USE `fdmsaudit`;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `audit_log` */

DROP TABLE IF EXISTS `audit_log`;

CREATE TABLE `audit_log` (
  `AUDIT_ID` int(11) NOT NULL auto_increment,
  `COMPANY_ID` int(11) NOT NULL,
  `LOCALE_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `MESSAGE` text,
  `CREATED_DATE` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`AUDIT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET SQL_MODE=@OLD_SQL_MODE;