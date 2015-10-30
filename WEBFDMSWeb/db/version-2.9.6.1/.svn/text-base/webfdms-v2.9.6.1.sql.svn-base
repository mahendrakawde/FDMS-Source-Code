-- CJongs 12/02/09
ALTER TABLE charges ADD COLUMN PackageId INT(11) DEFAULT 0 AFTER fromPackage;

-- CJongs 12/07/09
ALTER TABLE securityconfig ADD COLUMN CommissionLevel INT(11) DEFAULT 1 AFTER vendorCodeLength;
ALTER TABLE arrangers ADD COLUMN CommissionLevel INT(11) DEFAULT 0 AFTER IsCounselor;
ALTER TABLE invmaster ADD COLUMN Commissionable VARCHAR(1) DEFAULT 'N' AFTER AccountDescCDID;
ALTER TABLE pricelist ADD COLUMN Commissionable VARCHAR(1) DEFAULT 'N' AFTER AccountDescCDID;

/*
CREATE TABLE `commission` (
  `CommissionId` INT(11) NOT NULL AUTO_INCREMENT,
  `InvMasterId` INT(11) DEFAULT 0,
  `PricelistId` INT(11) DEFAULT 0,
  `Level1` DOUBLE DEFAULT 0,
  `Level2` DOUBLE DEFAULT 0,
  `Level3` DOUBLE DEFAULT 0,
  `Level4` DOUBLE DEFAULT 0,
  `Level5` DOUBLE DEFAULT 0,
  PRIMARY KEY  (`commissionId`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;
*/
CREATE TABLE `commission` (
  `CommissionId` INT(11) NOT NULL AUTO_INCREMENT,
  `Category` VARCHAR(15) DEFAULT '',
  `InvPricelistId` VARCHAR(15) DEFAULT '',
  `Level1` DOUBLE DEFAULT 0,
  `Level2` DOUBLE DEFAULT 0,
  `Level3` DOUBLE DEFAULT 0,
  `Level4` DOUBLE DEFAULT 0,
  `Level5` DOUBLE DEFAULT 0,
  `ManagerLevel1` DOUBLE DEFAULT 0,
  `ManagerLevel2` DOUBLE DEFAULT 0,
  `ManagerLevel3` DOUBLE DEFAULT 0,
  `ManagerLevel4` DOUBLE DEFAULT 0,
  `ManagerLevel5` DOUBLE DEFAULT 0,
  PRIMARY KEY  (`commissionId`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

-- CJongs 12/18/09
ALTER TABLE arrangers ADD COLUMN IsManagerForCommission TINYINT(1) DEFAULT 0 AFTER CommissionLevel;
ALTER TABLE arrangers ADD COLUMN ManagerCommissionLevel INT(11) DEFAULT 0 AFTER IsManagerForCommission;

-- CJongs 01/11/10
ALTER TABLE vitalstats ADD COLUMN TimeOfDeathStatus VARCHAR(15) DEFAULT '' AFTER PNFundsDepositedDate;

-- CJongs 01/06/10
ALTER TABLE vitalstats MODIFY Race VARCHAR(30) DEFAULT '';

-- CJongs 01/27/10
ALTER TABLE formsavailable ADD COLUMN AddLocalIDAndLocationIDReportFolder VARCHAR(1) DEFAULT 'N' AFTER XmlFile;

