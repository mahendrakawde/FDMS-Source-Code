-- CJongs 02/18/10
CREATE TABLE `webservicegreeting` (
  `IndexId` INT(11) NOT NULL AUTO_INCREMENT,
  `DateTime` DATETIME,
  `Greeting` VARCHAR(255) DEFAULT '',
  PRIMARY KEY  (`IndexId`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

-- CJongs 03/02/10
ALTER TABLE vitalstats ADD COLUMN FacilitySameAsChapel VARCHAR(1) DEFAULT 'N' AFTER TimeOfDeathStatus;

-- CJongs 030410
ALTER TABLE speeddatarule ADD COLUMN LockForAdmin VARCHAR(1) DEFAULT 'N' AFTER Col10;

UPDATE speeddatarule SET LockForAdmin = 'Y' WHERE TabCategory IN
("Accident","Ancestry","DecEducation","inpatdoa","PLCATGRY","PRODLINE","Race");

-- CJongs 030510
ALTER TABLE apaccounts ADD COLUMN Inactive VARCHAR(1) DEFAULT 'N' AFTER LocationID;

-- CJongs 030810
CREATE TABLE `eden` (
  `EdenId` INT(11) NOT NULL AUTO_INCREMENT,
  `State` VARCHAR(5) DEFAULT '',
  `SequenceNumber` SMALLINT(6) DEFAULT 0,
  `Length` SMALLINT(6) DEFAULT 0,
  `Description` VARCHAR(100) DEFAULT '',
  `ColumnDesc` VARCHAR(20) DEFAULT '',
  `UnknowDesc` VARCHAR(1) DEFAULT '',
  `CommentDesc` VARCHAR(150) DEFAULT '',
  `TableName` VARCHAR(100) DEFAULT '',
  `FieldName` VARCHAR(100) DEFAULT '',
  `FieldFormat` VARCHAR(15) DEFAULT '',
  PRIMARY KEY  (`EdenId`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

-- CJongs 031110
ALTER TABLE vitalstats ADD COLUMN InjuryCity VARCHAR(35) DEFAULT '' AFTER FacilitySameAsChapel;

-- CJongs 040610
ALTER TABLE vitalstats ADD COLUMN VoidedDate VARCHAR(8) DEFAULT '' AFTER InjuryCity;

-- CJongs 041510
-- CREATE INDEX locationIdIndex ON transactionhistory (locationId)


