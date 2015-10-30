-- added by Kier 05/31/06
CREATE TABLE `visitations` (
  `id` int(11) NOT NULL auto_increment,
  `VitalsMasterKey` int(11) NOT NULL,
  `Place` varchar(100) default NULL,
  `Room` varchar(20) default NULL,
  `Address` varchar(100) default NULL,
  `Address2` varchar(100) default NULL,
  `City` varchar(100) default NULL,
  `State` char(2) default NULL,
  `Zip` varchar(7) default NULL,
  `StartTime` time default NULL,
  `EndTime` time default NULL,
  `EventDate` date default NULL,
  `Private` tinyint(1) default '0',
  `Notes` text,
  `Deleted` tinyint(1) default '0',
  `Modified` timestamp NULL default NULL,
  PRIMARY KEY  (`id`)
);

-- added by David Rollins 6/2/2006
ALTER TABLE `servicedata` ADD COLUMN `AddnlServicePlace` VARCHAR(49) COMMENT 'Additional Service Place',
 ADD COLUMN `AddnlServiceStreet` VARCHAR(29) COMMENT 'Additional Service Street' AFTER `AddnlServicePlace`,
 ADD COLUMN `AddnlServiceCity` VARCHAR(39) COMMENT 'Addtional Service City' AFTER `AddnlServiceStreet`,
 ADD COLUMN `AddnlServiceState` VARCHAR(2) COMMENT 'Additional Service State' AFTER `AddnlServiceCity`;

-- added by David Rollins 6/2/2006
ALTER TABLE `servicedata` ADD COLUMN `PlaceOfServState` VARCHAR(2) COMMENT 'Service Place State';

-- added by David Rollins 6/6/2006
ALTER TABLE `servicedata` ADD COLUMN `CrematoryCounty` VARCHAR(40) COMMENT 'County of Crematory Permit',
 ADD COLUMN `CemeteryVault` VARCHAR(30) COMMENT 'Cemetery Vault' AFTER `CrematoryCounty`;


-- added by David Rollins 6/16/2006
CREATE TABLE `securityconfig` (
  `securityconfigID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `companyID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `minPasswordLength` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `maxPasswordLength` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `numberRequired` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  `symbolRequired` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  `mixedCaseRequired` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  `adjacentNumberAllowed` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  `failedLoginLockout` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  `passwordContainsUserNameAllowed` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  `passwordExpirationEnforced` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  `passwordExpirationInDays` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `failedLoginAttemptsAllowed` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `lockoutDuration` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `passwordExpirationWarning` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY(`securityconfigID`)
);

-- added by drollins 6/26/06

CREATE TABLE `passwordlog` (
  `passwordLogID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `userID` INTEGER NOT NULL DEFAULT 0,
  `password` VARCHAR(45) NOT NULL DEFAULT '',
  `tmstmp` TIMESTAMP NOT NULL DEFAULT 0,
  PRIMARY KEY(`passwordLogID`)
) ENGINE = InnoDB;


-- Added by Kier Heyl 06/26/06
update invmaster set imageurl = lower(imageurl);


-- Added by David Rollins 6/29/06
ALTER TABLE `locales` ADD COLUMN `CompanyID` INTEGER UNSIGNED NOT NULL DEFAULT 0;


-- Added by David Rollins 6/30/06
ALTER TABLE `securityconfig` ADD COLUMN `passwordNotRepeated` SMALLINT UNSIGNED NOT NULL DEFAULT 0;


-- Added by David Rollins 7/10/2006

ALTER TABLE `locales` ADD COLUMN `InactiveCode` CHAR(1) NOT NULL DEFAULT '';

-- Need to speed data cleanup utility
-- Need to run user security utility
-- Need to run date conversion utility
-- Need to run Company Locale/Location utility



-- Added by David Rollins 8/22/2006

ALTER TABLE `locales` 
 ADD COLUMN `ConfigAcctInterfaceDateRange` TINYINT UNSIGNED NOT NULL DEFAULT 0,
 ADD COLUMN `ConfigShowFinancing` TINYINT UNSIGNED NOT NULL DEFAULT 1 AFTER `ConfigAcctInterfaceDateRange`;

-- Added by Kier Heyl 9/18/06
alter table vitalstats add column Employer varchar(100);


-- Added by David Rollins 9/28/06
ALTER TABLE `pmntcomponents` ADD COLUMN `Source` VARCHAR(30);


-- Added by David Rollins 9/29/06
ALTER TABLE `vitalstats` ADD COLUMN `DispositionCounty` VARCHAR(30),
 ADD COLUMN `OperationPerformed` CHAR(1) AFTER `DispositionCounty`;



-- Added by Kier Heyl 9/6/2006
alter table glacctdefault 
	modify revenueAcct varchar(30),
	modify invAssetAcct varchar(30),
	modify InvCogsAcct varchar(30);
alter table charges
	modify ARacct varchar(30),
	modify GLacct varchar(30);
alter table transactionhistory
	modify ARacct varchar(30),
	modify GLacct varchar(30);
alter table pricelist
	modify GLAcctNo varchar(30);
alter table invmaster
	modify SalesGLAcct varchar(30),
	modify AssetGLAcct varchar(30),
	modify CostGLAcct varchar(30);
alter table invhistory
	modify SalesAcct varchar(30),
	modify AssetAcct varchar(30),
	modify CostAcct varchar(30);
alter table defaultpricelist
	modify GLAcctNo varchar(30);


-- Added by David Rollins 10/9/2006
ALTER TABLE `locations` ADD COLUMN `LocationNum` VARCHAR(10) AFTER `CompanyNumber`;


-- Added by David Rollins 10/18/2006
ALTER TABLE `veteransinfo` ADD COLUMN `FlagBurialStreet` VARCHAR(45),
 ADD COLUMN `FlagBurialCity` VARCHAR(45) AFTER `FlagBurialStreet`,
 ADD COLUMN `FlagBurialState` VARCHAR(10) AFTER `FlagBurialCity`,
 ADD COLUMN `FlagBurialZipCode` VARCHAR(10) AFTER `FlagBurialState`;