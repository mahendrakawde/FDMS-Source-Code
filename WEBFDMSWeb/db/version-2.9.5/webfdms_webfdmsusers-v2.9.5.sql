
-- Added by David Rollins on 5/21/2007
CREATE TABLE `uservacations` (
  `UserVacationID` int(10) unsigned NOT NULL auto_increment,
  `UserID` int(10) unsigned NOT NULL default '0',
  `notifyUserID` int(10) unsigned NOT NULL default '0',
  `dateFrom` date default NULL,
  `dateTo` date default NULL,
  PRIMARY KEY  (`UserVacationID`),
  KEY `Index_User` (`UserID`),
  KEY `Index_Dates` (`dateFrom`,`dateTo`)
) ENGINE=InnoDB;


-- Added by David Rollins on 5/24/2007
ALTER TABLE `usersecurity` ADD COLUMN `acctVacationFlag` BOOLEAN NOT NULL DEFAULT 0 AFTER `dbLookup`,
 ADD COLUMN `acctVacationUserID` INTEGER UNSIGNED AFTER `acctVacationFlag`;
 


/********************************
Added by David Rollins on 6/7/2007
********************************/

CREATE TABLE `ums_operation` (
  `OPERATION_ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(30) default NULL,
  `TOKEN` varchar(255) default NULL,
  `RESOURCE_ID` bigint(20) default NULL,
  `STATUS` binary(1) default NULL,
  PRIMARY KEY  (`OPERATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `ums_operation` */

insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (1,'User Management: Add','User Management: Add',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (2,'User Management: Edit','User Management: Edit',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (3,'User Management: Delete','User Management: Delete',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (4,'User Management: View','User Management: View',5,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (5,'Acct Check Approval: Add','Acct Check Approval: Add',5,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (6,'Acct Check Approval: Edit','Acct Check Approval: Edit',5,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (7,'Acct Check Approval: Delete','Acct Check Approval: Delete',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (8,'Acct Check Approval: View','Acct Check Approval: View',5,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (12,'Acct Vendor Management: Add','Acct Vendor Management: Add',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (13,'Acct Vendor Management: Edit','Acct Vendor Management: Edit',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (14,'Acct Vendor Management: Delete','Acct Vendor Management: Delete',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (15,'Acct Vendor Management: View','Acct Vendor Management: View',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (16,'Acct Limits Management: Add','Acct Limits Management: Add',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (17,'Acct Limits Management: Edit','Acct Limits Management: Edit',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (18,'Acct Limits Management: Delete','Acct Limits Management: Delete',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (19,'Acct Limits Management: View','Acct Limits Management: View',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (20,'My Account: Add','My Account: Add',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (21,'My Account: Edit','My Account: Edit',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (22,'My Account: Delete','My Account: Delete',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (23,'My Account: View','My Account: View',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (24,'Report Management: Add','Report Management: Add',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (25,'Report Management: Edit','Report Management: Edit',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (26,'Report Management: Delete','Report Management: Delete',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (27,'Report Management: View','Report Management: View',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (28,'Report Generation: Add','Report Generation: Add',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (29,'Report Generation: Edit','Report Generation: Edit',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (30,'Report Generation: Delete','Report Generation: Delete',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (31,'Report Generation: View','Report Generation: View',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (32,'Dashboard Menu: Add','Dashboard Menu: Add',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (33,'Dashboard Menu: Edit','Dashboard Menu: Edit',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (34,'Dashboard Menu: Delete','Dashboard Menu: Delete',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (35,'Dashboard Menu: View','Dashboard Menu: View',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (36,'Acct Menu: Add','Acct Menu: Add',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (37,'Acct Menu: Edit','Acct Menu: Edit',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (38,'Acct Menu: Delete','Acct Menu: Delete',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (39,'Acct Menu: View','Acct Menu: View',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (40,'Admin Menu: Add','Admin Menu: Add',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (41,'Admin Menu: Edit','Admin Menu: Edit',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (42,'Admin Menu: Delete','Admin Menu: Delete',4,'1');
insert  into `ums_operation`(`OPERATION_ID`,`NAME`,`TOKEN`,`RESOURCE_ID`,`STATUS`) values (43,'Admin Menu: View','Admin Menu: View',4,'1');

/*Table structure for table `ums_role_operation_xref` */

CREATE TABLE `ums_role_operation_xref` (
  `ROLE_ID` bigint(20) default NULL,
  `OPERATION_ID` bigint(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `ums_role_operation_xref` */

insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (2,23);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (2,21);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (2,31);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (2,35);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,23);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,21);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,31);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,35);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,12);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,13);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,14);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,15);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,16);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,17);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,18);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,19);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,36);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,37);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,38);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (3,39);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (4,40);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (4,41);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (4,42);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (4,43);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (4,1);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (4,2);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (4,3);
insert  into `ums_role_operation_xref`(`ROLE_ID`,`OPERATION_ID`) values (4,4);


/********************************
END Added by David Rollins on 6/7/2007
********************************/



/********************************
Added by David Rollins on 7/10/2007
********************************/
create table `companyoptionvalues`(
   `companyOptionValueID` int not null auto_increment,
   `companyOptionID` int not null,
   `companyID` int,
   `value` int,
    primary key (`companyOptionValueID`)
);

create table `companyoptions`(
   `companyOptionID` int not null auto_increment,
   `name` varchar(80) default '' not null,
   `description` varchar(200),
   `defaultValue` int,
    primary key (`companyOptionID`)
);

insert  into `companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) values (1,'Check Writer - Checks Must be approved','Checks require authorization',0);
insert  into `companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) values (2,'Check Writer - Checks use vendor limits','Checks use limits for vendors',0);

/********************************
END Added by David Rollins on 7/10/2007
********************************/    
    
-- Added by David Rollins 7/25/2007    
ALTER TABLE `usersecurity` ADD COLUMN `userLimitOverride` BOOLEAN NOT NULL DEFAULT 0,
 ADD COLUMN `limitInternalVendor` DOUBLE NOT NULL DEFAULT 0 AFTER `userLimitOverride`,
 ADD COLUMN `limitExternalVendor` DOUBLE NOT NULL DEFAULT 0 AFTER `limitInternalVendor`;    
 

-- Added by David Rollins 9/6/2007    
CREATE TABLE `config` (
  `ConfigID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(90) NOT NULL DEFAULT '',
  `Inactive` BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY(`ConfigID`)
)
ENGINE = InnoDB;

-- Added by David Rollins 9/6/2007    
CREATE TABLE `configvalues` (
  `ConfigValueID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `ConfigID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `ConfigCode` VARCHAR(80) NOT NULL DEFAULT '',
  `Value` VARCHAR(200),
  PRIMARY KEY(`ConfigValueID`),
  INDEX `Index_ConfigID`(`ConfigID`)
)
ENGINE = InnoDB;

-- Added by David Rollins 9/7/2007
ALTER TABLE `companies` ADD COLUMN `ConfigID` BIGINT NOT NULL DEFAULT 0 AFTER `ISDATABASENEW`;

-- Added by David Rollins 9/17/2007
insert  into `config`(`ConfigID`,`Name`,`Inactive`) values (1,'Development',0);
insert  into `config`(`ConfigID`,`Name`,`Inactive`) values (2,'US',0);

-- Added by David Rollins 9/17/2007
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (1,1,'WebAppContext','webfdms');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (2,1,'WebApp.warnMultipleLogins','true');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (3,1,'db.url','jdbc\\:mysql\\://localhost/[dbname]');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (4,1,'db.jndiTemplate','java:jdbc/FDMS_[companyName]');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (5,1,'db.users.url','jdbc:mysql://localhost/webfdmsusers');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (6,1,'db.users.jndi','java:jdbc/WEBFDMS');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (7,1,'db.fdmsdata1.jndi','java:jdbc/WebFdmsData1');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (8,1,'db.audit.jndi','java:jdbc/AuditDS');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (9,1,'db.common.jndi','java:jdbc/FDMS_Common');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (10,1,'db.driver','org.gjt.mm.mysql.Driver');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (11,1,'db.username','webfdms');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (12,1,'db.password','webapp');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (13,1,'CrystalServer.useReportingService','true');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (14,1,'CrystalServer.IP','localhost');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (15,1,'CrystalServer.dsn','WebfdmsUS');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (16,1,'CrystalServer.userSchema','webfdmsusers');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (17,1,'CrystalServer.auditSchema','fdmsaudit');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (18,1,'CrystalServer.reportsDir','UAT');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (19,1,'CrystalServer.reportsLib','C:\\\\reports\\\\');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (20,1,'CrystalServer.errorPg','reportError.html');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (21,1,'InterfaceLocation','glinterface');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (22,1,'smtp.mail.host','mail.aldorsolutions.com');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (23,1,'smtp.mail.from','noreply@aldorsolutions.com');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (24,1,'ApplicationDirectory','C:\\\\jboss-4.0.4\\\\server\\\\default\\\\deploy\\\\WEBFDMSEAR.ear\\\\WEBFDMSWeb.war\\\\');
insert into configvalues ( ConfigValueID, ConfigID, ConfigCode, Value)	values	( 25,1, "CrystalServer.ReportGenerator.IP", "asreports.aldorsolutions.com");
insert into configvalues ( ConfigValueID,ConfigID, ConfigCode, Value)	values	( 26,1, "CrystalServer.ReportServer.IP", "asreports.aldorsolutions.com");
insert into configvalues ( ConfigValueID,ConfigID, ConfigCode, Value)  values ( 27,1, "CrystalServer.WebService.NameSpace", "asreports.aldorsolutions.com");
insert into configvalues ( ConfigValueID,ConfigID, ConfigCode, Value)	values	( 28,1, "CrystalServer.Timeout", "600000");

insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (29,2,'WebAppContext','webfdms');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (30,2,'WebApp.warnMultipleLogins','true');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (31,2,'db.url','jdbc\\:mysql\\://asdatabase.aldorsolutions.com/[dbname]');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (32,2,'db.jndiTemplate','java:jdbc/FDMS_[companyName]');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (33,2,'db.users.url','jdbc:mysql://asdatabase.aldorsolutions.com/fdmsus_webfdmsusers');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (34,2,'db.users.jndi','java:jdbc/WEBFDMS');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (35,2,'db.fdmsdata1.jndi','java:jdbc/WebFdmsData1');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (36,2,'db.audit.jndi','java:jdbc/AuditDS');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (37,2,'db.common.jndi','java:jdbc/FDMS_Common');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (38,2,'db.driver','org.gjt.mm.mysql.Driver');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (39,2,'db.username','webfdms');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (40,2,'db.password','webapp');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (41,2,'CrystalServer.useReportingService','true');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (42,2,'CrystalServer.IP','asreports.aldorsolutions.com');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (43,2,'CrystalServer.dsn','WebfdmsUS');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (44,2,'CrystalServer.userSchema','fdmsus_webfdmsusers');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (45,2,'CrystalServer.auditSchema','fdmsus_fdmsaudit');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (46,2,'CrystalServer.reportsDir','US');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (47,2,'CrystalServer.reportsLib','C:\\\\reports\\\\');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (48,2,'CrystalServer.errorPg','reportError.html');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (49,2,'InterfaceLocation','glinterface');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (50,2,'smtp.mail.host','mail.aldorsolutions.com');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (51,2,'smtp.mail.from','noreply@aldorsolutions.com');
insert  into `configvalues`(`ConfigValueID`,`ConfigID`,`ConfigCode`,`Value`) values (52,2,'ApplicationDirectory','/drbd/jboss/server/default/deploy/WEBFDMSEAR.ear/WEBFDMSWeb.war/');
insert into configvalues ( ConfigValueID, ConfigID, ConfigCode, Value)	values	( 53,2, "CrystalServer.ReportGenerator.IP", "asreports.aldorsolutions.com");
insert into configvalues ( ConfigValueID,ConfigID, ConfigCode, Value)	values	( 54,2, "CrystalServer.ReportServer.IP", "asreports.aldorsolutions.com");
insert into configvalues ( ConfigValueID,ConfigID, ConfigCode, Value)  values ( 55,2, "CrystalServer.WebService.NameSpace", "asreports.aldorsolutions.com");
insert into configvalues ( ConfigValueID,ConfigID, ConfigCode, Value)	values	( 56,2, "CrystalServer.Timeout", "600000");

-- Added by Chad Lehnert  12/10/2007
insert  into `companyoptionvalues`(`companyOptionID`,`companyID`,`value`) values (3,90,1);
insert  into `companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) values (3,'Account Description','Flag to indicate if account descriptions should be used',0);

-- Added by Chai Jongs 12/26/2007
insert into `ums_operation` (`OPERATION_ID`, `NAME`, `TOKEN`, `RESOURCE_ID`, `STATUS`) values('44','Vendor Menu: Add','Vendor Menu: Add','4','1');
insert into `ums_operation` (`OPERATION_ID`, `NAME`, `TOKEN`, `RESOURCE_ID`, `STATUS`) values('45','Vendor Menu: Edit','Vendor Menu: Edit','4','1');
insert into `ums_operation` (`OPERATION_ID`, `NAME`, `TOKEN`, `RESOURCE_ID`, `STATUS`) values('46','Vendor Menu: Delete','Vendor Menu: Delete','4','1');
insert into `ums_operation` (`OPERATION_ID`, `NAME`, `TOKEN`, `RESOURCE_ID`, `STATUS`) values('47','Vendor Menu: View','Vendor Menu: View','4','1');


-- Added by Chai Jongs 01/07/2008 -- Add role to admin
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (4,35);
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (4,39);
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (4,47);
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (4,45);

-- Added by Chai Jongs 12/26/2007
insert into `ums_role_operation_xref` (`ROLE_ID`, `OPERATION_ID`) values('2','44');
insert into `ums_role_operation_xref` (`ROLE_ID`, `OPERATION_ID`) values('2','45');
insert into `ums_role_operation_xref` (`ROLE_ID`, `OPERATION_ID`) values('2','46');
insert into `ums_role_operation_xref` (`ROLE_ID`, `OPERATION_ID`) values('2','47');
insert into `ums_role_operation_xref` (`ROLE_ID`, `OPERATION_ID`) values('3','47');


-- Added by Chai Jongs 01/08/2008 -- Add operation role 
insert into `ums_operation` (`OPERATION_ID`, `NAME`, `TOKEN`, `RESOURCE_ID`, `STATUS`) values('48','Invoice Menu: Add','Invoice Menu: Add','4','1');
insert into `ums_operation` (`OPERATION_ID`, `NAME`, `TOKEN`, `RESOURCE_ID`, `STATUS`) values('49','Invoice Menu: Edit','Invoice Menu: Edit','4','1');
insert into `ums_operation` (`OPERATION_ID`, `NAME`, `TOKEN`, `RESOURCE_ID`, `STATUS`) values('50','Invoice Menu: Delete','Invoice Menu: Delete','4','1');
insert into `ums_operation` (`OPERATION_ID`, `NAME`, `TOKEN`, `RESOURCE_ID`, `STATUS`) values('51','Invoice Menu: View','Invoice Menu: View','4','1');

-- Added by Chai Jongs 01/08/2008 -- Add Role for invoice approve and submit
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (2,49);
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (4,37);
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (4,49);

-- Added by Chad Lehnert  01/16/2008 -- Add Role for invoice approve and submit
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (4,51);
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (2,51);

 -- Added by Chai Jons 02/06/08 -- for special menu only acct on submited invoice
insert into `ums_operation` (`OPERATION_ID`, `NAME`, `TOKEN`, `RESOURCE_ID`, `STATUS`)  values('52', 'Menu: Acct','Menu: Acct','4','1');
insert into `ums_operation` (`OPERATION_ID`, `NAME`, `TOKEN`, `RESOURCE_ID`, `STATUS`)  values('53', 'Menu: Adm','Menu: Adm','4','1');
insert into `ums_operation` (`OPERATION_ID`, `NAME`, `TOKEN`, `RESOURCE_ID`, `STATUS`)  values('54','Menu: User','Menu: User','4','1');
 
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (3,52);
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (4,53);
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (2,54);

-- Created a way to turn off the AP Module
insert  into `companyoptionvalues`(`companyOptionID`,`companyID`,`value`) values (4,90,1);
insert  into `companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) values (4,'AP Module','AP Module Active',0);

-- Created a way to turn off the Accounting Interface
ALTER TABLE `usersecurity` ADD COLUMN `accountingInterface` BOOLEAN NOT NULL DEFAULT 0 AFTER `viewOnly`;
ALTER TABLE `usersecurity` ADD COLUMN `speedData` BOOLEAN NOT NULL DEFAULT 0 AFTER `accountingInterface`;
ALTER TABLE `usersecurity` ADD COLUMN `arrangerManagement` BOOLEAN NOT NULL DEFAULT 0 AFTER `speedData`;
ALTER TABLE `usersecurity` ADD COLUMN `formsAvailable` BOOLEAN NOT NULL DEFAULT 0 AFTER `arrangerManagement`;
ALTER TABLE `usersecurity` ADD COLUMN `glSalesAccount` BOOLEAN NOT NULL DEFAULT 0 AFTER `formsAvailable`;

-- Created a way to turn off the AP Module - Invoice Line Items
-- insert  into `companyoptionvalues`(`companyOptionID`,`companyID`,`value`) values (5,90,1);
insert  into `companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) values (5,'AP Module Line Items','AP Module Line Items Active',0);

-- Add by CJongs 03/18/08 Invoice: add menu for Admin and user
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (4,48);
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (2,48);

-- Add by CJongs 03/18/08 Invoice: approval for Accountant 
insert into `ums_operation` (`OPERATION_ID`, `NAME`, `TOKEN`, `RESOURCE_ID`, `STATUS`)  values('55','Invoice Menu: Approval','Invoice Menu: Approval','4','1');
insert into ums_role_operation_xref (ROLE_ID, OPERATION_ID) values (3,55);

-- Add by CJongs 04/23/08 New At-Need service date is required for company option.
insert  into `companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) values (6,'New At-Need - Service Date','Service Date Required',0);

-- Added By Chad 7/9/08 New Messenger flag
insert  into `companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) values (7,'Messenger Modules','Used to turn on messenger exports',0);

-- Added By Chad 7/18/08 New Audit log off flag
insert  into `companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) values (8,'Turn Audit Log off','Used to turn off the audit log.  Set this value to 1 to turn off the audit log',0);
-- NOTE   NOTE   NOTE  When you run this SQL you need to go into the database and manually change ConfigID back to 0.  If not this feature will not work.  
-- NOTE  NOTE    NOTE  the database will automatically assign the next configID and ignores the fact that I need it set to 0
insert into `config` 	(`ConfigID`, `Name`, `Inactive`) values 	(0,'Global',0);
insert into `configvalues` (`ConfigID`, `ConfigCode`, `Value`) values (0,'Global.Audit.Log', 'on');

-- Added by Chad 8/5/08 Logging the login servers ip address for tracking.
ALTER TABLE `userlog` ADD COLUMN `loginServerIP` varchar(32) default NULL AFTER `ip`;

-- Add by CJongs 08/05/08 QTY for company option.
insert  into `companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) values (10,'QTY','Qty for service and mechandise of financial page.',0);

-- Add by CJongs 08/05/08 Monitoring perfermance of each action class by logging start and stop time for company option.
insert  into `companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) values (9,'ActionClass Log','To monitor operation time of each action class',0);
CREATE TABLE `actionclasslog` (
  `actionclassid` int(11) NOT NULL auto_increment,
  `actionclassname` varchar(70) default NULL,
  `userid` int(11) default NULL,
  `starttime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `stoptime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `executetime` int(11) default NULL COMMENT 'milliseconds',
  PRIMARY KEY  (`actionclassid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- Add by CJongs 08/05/08 Monitoring duplicate user login usring company option.
insert  into `companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) values (11,'Duplicate Login attempt  Log','To monitor login time of a user attempt to login while there is already in system',0);
CREATE TABLE `duplicateloginlog` (
  `logID` int(11) NOT NULL auto_increment,
  `userID` int(11) default NULL,
  `userName` varchar(40) default NULL,
  `loginTime` timestamp NULL default NULL,
  PRIMARY KEY  (`logID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Add by CJongs 09/05/08 Monitoring perfermance of each action class by logging start and stop time for company option.
insert  into `companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) values (12,'Using new report server','Using new report server',0);

-- Add by Chai 09/13/08
insert  into `companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) values (13,'FDMS to ASIMAS Link Allowed','This flag allows the Post To ASIMAS button to appear',0);





