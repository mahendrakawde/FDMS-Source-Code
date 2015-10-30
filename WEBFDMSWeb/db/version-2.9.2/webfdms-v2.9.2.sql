-- Added by David Rollins 11/28/2006
ALTER TABLE `invmaster` MODIFY COLUMN `ItemName` VARCHAR(20) NOT NULL DEFAULT '';

-- Added by David Rollins 11/28/2006
insert into speeddatarule (TabCategory, Col0, Col1) values ('Honorific', 'Abbreviation', 'Description');

-- Added by David Rollins 11/30/2006
ALTER TABLE `vitalstats` ADD COLUMN `WasPeaceOfficer` CHAR(1);

-- Added by David Rollins 01/04/2007
ALTER TABLE `vitalstats` ADD COLUMN `InformantCellPhone` VARCHAR(20);
ALTER TABLE `vitalstats` ADD COLUMN `NextKinCellPhone` VARCHAR(20);
ALTER TABLE `PERSON` ADD COLUMN `ExecutorCellPhone` VARCHAR(15);
ALTER TABLE `billtoparties` ADD COLUMN `CellPhone` VARCHAR(20);

-- Added by David Rollins 01/08/07
CREATE TABLE `localeconfigs` (
  `localeConfigID` int(11) NOT NULL auto_increment,
  `localeConfigTypeID` int(11) NOT NULL,
  `localeID` int(11) default NULL,
  `value` int(11) default NULL,
  PRIMARY KEY  (`localeConfigID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `localeconfigtypes` (
  `localeConfigTypeID` int(11) NOT NULL,
  `name` varchar(80) NOT NULL,
  `description` varchar(200) default NULL,
  `defaultValue` int(11) default NULL,
  PRIMARY KEY  (`localeConfigTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `localeconfigtypes` (`localeConfigTypeID`,`name`,`description`,`defaultValue`) VALUES 
 (1,'Bill to Party - Cash Sale','Show Cash Sale option on Bill to Party Screen',1),
 (2,'Bill To Party - Refused','Show Refused option on Bill to Party Screen',1),
 (3,'Services - Restoration','Show Restoration option on Services Screen',1),
 (4,'Finance - Service Plan','Show Service Plan option on Finance Screen',1);

-- Added by David Rollins 01/16/2007
ALTER TABLE `vitalstats` ADD COLUMN `InformantIsBillToParty` CHAR(1);

ALTER TABLE `invmaster` DROP INDEX `nameIndex`,
 ADD UNIQUE `nameIndex`(`ItemName`, `Locale`);

-- The following commands are alternatives to aid in discovering which fields might not match this type of key.
-- ALTER TABLE `invmaster` DROP INDEX `nameIndex`;
-- ALTER TABLE `invmaster` ADD UNIQUE `nameIndex`(`ItemName`, `Locale`);

-- Added by David Rollins 02/02/2007
ALTER TABLE `speeddata` ADD INDEX `TabCategory`(`TabCategory`), ADD INDEX `Locale`(`Locale`), ENGINE = InnoDB;


-- Added by David Rollins 02/09/2007
ALTER TABLE `pricelist` MODIFY COLUMN `ItemCategory` VARCHAR(50);
ALTER TABLE `charges` MODIFY COLUMN `CategoryCode` VARCHAR(50);
ALTER TABLE `transactionhistory` MODIFY COLUMN `ItemCategory` VARCHAR(50);

-- Added by David Rollins 2/24/2007
ALTER TABLE `transactionhistory` MODIFY COLUMN `AmountOfTran` BIGINT;

-- Added by David Rollins 2/27/2007
ALTER TABLE `charges` MODIFY COLUMN `Amount` BIGINT;

-- Added by David Rollins 2/27/2007
ALTER TABLE `veteransinfo` MODIFY COLUMN `FlagOtherVetService` VARCHAR(60);

-- Added by David Rollins 03/09/2007
ALTER TABLE `transactionhistory` MODIFY COLUMN `Description` VARCHAR(50);
ALTER TABLE `vitalstats` ADD INDEX `ChapelIndex`(`ChapelNumber`);
ALTER TABLE `vitalstats` ADD INDEX `DecLNameIndex`(`DeceasedLastName`);
ALTER TABLE `vitalstats` ADD INDEX `DecFNameIndex`(`DeceasedFirstName`);
ALTER TABLE `vitalstats` ADD INDEX `ContractCodeIndex`(`ContractCode`);
ALTER TABLE `servicedata` ADD INDEX `VitalsIDIndex`(`VitalsMasterKey`);
ALTER TABLE `vitalstats` MODIFY COLUMN `EmbalmingReason` VARCHAR(60);
ALTER TABLE `financialdata` MODIFY COLUMN `TotalPaidToDate` BIGINT;
ALTER TABLE `financialdata` MODIFY COLUMN `LastPaymentAmount` BIGINT;

