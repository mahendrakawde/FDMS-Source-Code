-- -------------------------------------
-- Cemetary Manager delivered by QPrime.  
-- 
-- 03/13/2007
-- -------------------------------------

#******************************
# Alter companies table add cemetary fields
#******************************
ALTER TABLE `companies` ADD COLUMN `FDMS_Funeral` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 AFTER `SQLPass`,
	ADD COLUMN `FDMS_Cemetery` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 AFTER `FDMS_Funeral`;

#******************************
# Alter usersecurity table add cemetary fields
#******************************
ALTER TABLE `usersecurity` ADD COLUMN `enableCemetery` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 AFTER `passwordTmstmp`,
	ADD COLUMN `enableFuneral` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 AFTER `enableCemetery`;

 
#******************************
# On Update trigger companies
#******************************

DROP TRIGGER `T_UpdateUser`;

DELIMITER $$

CREATE TRIGGER `T_UpdateUser` BEFORE UPDATE on `companies`
FOR EACH ROW BEGIN
      update usersecurity set enableCemetery = new.FDMS_Cemetery, 
      enableFuneral = new.FDMS_Funeral where companyid = new.companyid;
END$$

DELIMITER ;

#******************************
# On Update trigger usersecurity
#******************************

DROP TRIGGER `T_INSERT_User`;

DELIMITER $$

CREATE TRIGGER T_INSERT_User before INSERT ON usersecurity
	FOR EACH ROW BEGIN
    	SET new.enableFuneral = (select FDMS_Funeral from companies where companyid = new.companyid);
		SET new.enableCemetery = (select FDMS_Cemetery  from companies where companyid = new.companyid);
END$$

DELIMITER ;


/*************************
**   Role based security modifications
**   Added by David Rollins 04/24/07
*************************/
CREATE TABLE `ums_role` (
  `ROLE_ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(30) default NULL,
  `DESCR` varchar(255) default NULL,
  `STATUS` binary(1) default NULL,
  PRIMARY KEY  (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `ums_rolesmembership` (
  `roleID` bigint(20) default NULL,
  `userID` bigint(20) default NULL,
  UNIQUE KEY `roleID` (`roleID`,`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


insert  into `ums_role`(`ROLE_ID`,`NAME`,`DESCR`,`STATUS`) values (1,'Webfdms User','','1');
insert  into `ums_role`(`ROLE_ID`,`NAME`,`DESCR`,`STATUS`) values (2,'Dashboard User','','1');
insert  into `ums_role`(`ROLE_ID`,`NAME`,`DESCR`,`STATUS`) values (3,'Dashboard Accounting','','1');
insert  into `ums_role`(`ROLE_ID`,`NAME`,`DESCR`,`STATUS`) values (4,'Dashboard Admin','','1');
insert  into `ums_role`(`ROLE_ID`,`NAME`,`DESCR`,`STATUS`) values (5,'Webfdms Payments',NULL,'1');
insert  into `ums_role`(`ROLE_ID`,`NAME`,`DESCR`,`STATUS`) values (6,'Webfdms At-Need',NULL,'1');
insert  into `ums_role`(`ROLE_ID`,`NAME`,`DESCR`,`STATUS`) values (7,'Webfdms Pre-Need',NULL,'1');
insert  into `ums_role`(`ROLE_ID`,`NAME`,`DESCR`,`STATUS`) values (8,'Webfdms Financial',NULL,'1');
insert  into `ums_role`(`ROLE_ID`,`NAME`,`DESCR`,`STATUS`) values (9,'Webfdms Accounts Receivable',NULL,'1');
insert  into `ums_role`(`ROLE_ID`,`NAME`,`DESCR`,`STATUS`) values (10,'Webfdms Forms and Reports',NULL,'1');
insert  into `ums_role`(`ROLE_ID`,`NAME`,`DESCR`,`STATUS`) values (11,'Webfdms Delete Cases',NULL,'1');
insert  into `ums_role`(`ROLE_ID`,`NAME`,`DESCR`,`STATUS`) values (12,'Webfdms Inventory',NULL,'1');
insert  into `ums_role`(`ROLE_ID`,`NAME`,`DESCR`,`STATUS`) values (13,'Webfdms Save',NULL,'1');

/*************************
**   END Role based security modifications
*************************/
-- Srini: 05-25-2007 new field added to indicate database new or not.
alter table `companies` add column `ISDATABASENEW` char (1)  DEFAULT 'N' NULL  COMMENT 'New DataBase indicator' after `DBLookup`;
  
