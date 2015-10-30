-- CJongs 10.09.09
ALTER TABLE vitalstats ADD COLUMN PNFundsStreet VARCHAR(50) DEFAULT '' AFTER TribalName;
ALTER TABLE vitalstats ADD COLUMN PNFundsCity VARCHAR(30) DEFAULT '' AFTER PNFundsStreet;
ALTER TABLE vitalstats ADD COLUMN PNFundsState VARCHAR(30) DEFAULT '' AFTER PNFundsCity;
ALTER TABLE vitalstats ADD COLUMN PNFundsZip VARCHAR(10) DEFAULT '' AFTER PNFundsState;
ALTER TABLE vitalstats ADD COLUMN PNFundsDepositedDate VARCHAR(8) DEFAULT '' AFTER PNFundsZip;

-- CJongs 10.12.09
ALTER TABLE locations ADD COLUMN OneTimeVendorCode VARCHAR(15) DEFAULT '' AFTER externalVendorLimit;
ALTER TABLE securityconfig ADD COLUMN vendorCodeLength INT(11) DEFAULT 12 AFTER passwordNotRepeated;

-- CJongs 10.13.29
CREATE TABLE `bankaccount` (
  `BankAccountID` INT(11) NOT NULL AUTO_INCREMENT,
  `LocaleID` INT(11) NOT NULL,
  `LocationID` INT(11) NOT NULL,
  `BankName` VARCHAR(50) DEFAULT '',
  `AccountName` VARCHAR(50) DEFAULT '',
  `RoutingNumber` VARCHAR(15) DEFAULT '',
  `AccountNumber` VARCHAR(30) DEFAULT '',
  `Street` VARCHAR(50) DEFAULT '',
  `City` VARCHAR(30) DEFAULT '',
  `State` VARCHAR(2) DEFAULT '',
  `Zip` VARCHAR(10) DEFAULT '',
  `PhoneNumber` VARCHAR(20) DEFAULT NULL,
  `OtherPhone` VARCHAR(20) DEFAULT NULL,
  `StartDate` DATE DEFAULT NULL,
  `Status` VARCHAR(5) DEFAULT '',
  `Balance` INT(11) DEFAULT 0,
  `AccountingCode` VARCHAR(15) DEFAULT '',
  `InitialBalance` INT(11) DEFAULT 0,
  PRIMARY KEY (`BankAccountID`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


-- CJongs 11.10.09
ALTER TABLE apmaster ADD COLUMN VoidedComment VARCHAR(200) DEFAULT '' AFTER VendorName;


