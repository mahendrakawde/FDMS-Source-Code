-- cjongs 12/06/10
ALTER TABLE securityconfig ADD COLUMN FuneralSyncId INT DEFAULT 0 AFTER CommissionLevel;
ALTER TABLE locations ADD COLUMN FuneralSyncLocationId VARCHAR(40) DEFAULT '' AFTER OneTimeVendorCode;

CREATE TABLE `creditcard` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Passkey` VARCHAR(30) DEFAULT '',
  `Fristname` VARCHAR(30) DEFAULT '',
  `Lastname` VARCHAR(30) DEFAULT '',
  `CardNumber` VARCHAR(30) DEFAULT '',
  `ExpirationDate` VARCHAR(30) DEFAULT '',
  `CVV` VARCHAR(30) DEFAULT '',
  `Address` VARCHAR(30) DEFAULT '',
  `City` VARCHAR(30) DEFAULT '',
  `State` VARCHAR(30) DEFAULT '',
  `Zip` VARCHAR(30) DEFAULT '',
  `ReferanceId` VARCHAR(30) DEFAULT '',
  `Amount` VARCHAR(30) DEFAULT '',
  `RecNo` VARCHAR(30) DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;


ALTER TABLE securityconfig ADD SMSNumberOfTime SMALLINT DEFAULT 0 AFTER FuneralSyncId;
ALTER TABLE securityconfig ADD SMSFirstname VARCHAR(20) DEFAULT '' AFTER SMSNumberOfTime;
ALTER TABLE securityconfig ADD SMSLastname VARCHAR(20) DEFAULT '' AFTER SMSFirstname;
ALTER TABLE securityconfig ADD SMSAreaCode VARCHAR(3) DEFAULT '' AFTER SMSLastname;
ALTER TABLE securityconfig ADD SMSPhone VARCHAR(7) DEFAULT '' AFTER SMSAreaCode;
ALTER TABLE securityconfig ADD SMSGreeting VARCHAR(50) DEFAULT '' AFTER SMSPhone;


ALTER TABLE securityconfig ADD TTVNumberOfTime SMALLINT DEFAULT 0 AFTER SMSGreeting;
ALTER TABLE securityconfig ADD TTVFirstname VARCHAR(20) DEFAULT '' AFTER TTVNumberOfTime;
ALTER TABLE securityconfig ADD TTVLastname VARCHAR(20) DEFAULT '' AFTER TTVFirstname;
ALTER TABLE securityconfig ADD TTVAreaCode VARCHAR(3) DEFAULT '' AFTER TTVLastname;
ALTER TABLE securityconfig ADD TTVPhone VARCHAR(7) DEFAULT '' AFTER TTVAreaCode;
ALTER TABLE securityconfig ADD TTVGreeting VARCHAR(50) DEFAULT '' AFTER TTVPhone;

CREATE TABLE `smsttvrecipient` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Firstname` VARCHAR(30) DEFAULT '',
  `Lastname` VARCHAR(30) DEFAULT '',
  `AreaCode` VARCHAR(30) DEFAULT '',
  `Phone` VARCHAR(30) DEFAULT '',
  `Greeting` VARCHAR(30) DEFAULT '',
  `Type` VARCHAR(30) DEFAULT '',
  `ModifyDate` DATETIME ,
  PRIMARY KEY (`Id`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;


-- CJongs 04/25/11
ALTER TABLE invmaster MODIFY ItemName VARCHAR(39);
ALTER TABLE invonhand MODIFY ItemName VARCHAR(39);
ALTER TABLE invhistory MODIFY ItemName VARCHAR(39);
ALTER TABLE transactionhistory MODIFY InventoryItemName VARCHAR(39);

ALTER TABLE survivors ADD PreferCommunicateYN CHAR(1) DEFAULT '' AFTER GroupType;

UPDATE speeddatarule SET Col7 = 'Fax' WHERE TabCategory LIKE 'doctor';
UPDATE speeddata SET tabdata=CONCAT(tabdata,',') WHERE tabcategory LIKE 'doctor';

ALTER TABLE locations ADD MerchandiseId INT DEFAULT 0 AFTER FuneralSyncLocationId;

ALTER TABLE creditcard ADD TranDate DATETIME AFTER RecNo;
ALTER TABLE creditcard ADD MerchandiseId INT DEFAULT 0 AFTER TranDate;
ALTER TABLE creditcard ADD VitalsId INT DEFAULT 0 AFTER MerchandiseId;

ALTER TABLE vitalstats ADD CONSTRAINT uc_preventDuplication UNIQUE (CaseCode,ContractCode,LocaleNumber,ChapelNumber,DeceasedFirstName,DeceasedLastName);

ALTER TABLE locations ADD COLUMN TurnOnApplyFinanceCharge CHAR(1) DEFAULT 'N' AFTER MerchandiseId;
ALTER TABLE locations ADD COLUMN MonthlyInterestRate VARCHAR(10) DEFAULT '0.0' AFTER TurnOnApplyFinanceCharge;
ALTER TABLE locations ADD COLUMN ERegisterPage VARCHAR(100) DEFAULT '' AFTER MonthlyInterestRate;
ALTER TABLE locations ADD COLUMN ERegisterTargetPage VARCHAR(100) DEFAULT '' AFTER ERegisterPage;

UPDATE speeddatarule SET Col5 = 'Country' WHERE TabCategory LIKE 'CITY_STATE';
UPDATE speeddata SET tabdata=CONCAT(tabdata,',') WHERE tabcategory LIKE 'CITY_STATE';

ALTER TABLE securityconfig ADD COLUMN ERegisterBookURL VARCHAR(100) DEFAULT 'http://www.eregisterbook.mobi/eregisterbook/view.php' AFTER TTVGreeting;




