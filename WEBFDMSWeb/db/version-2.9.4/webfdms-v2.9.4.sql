-- Added by David Rollins 2/23/2007
CREATE TABLE `invversion` (
  `InvVersionID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `PriceListID` VARCHAR(50),
  `CompanyID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `Name` VARCHAR(45) NOT NULL DEFAULT '',
  `Description` VARCHAR(200),
  `InvFromDate` DATE,
  `InvToDate` DATE,
  `Active` BOOLEAN NOT NULL DEFAULT 0,
  `Timestamp` TIMESTAMP NOT NULL DEFAULT 0, 
  PRIMARY KEY(`InvVersionID`),
  INDEX `Index_PriceListID`(`PriceListID`),
  INDEX `Index_CompanyID`(`CompanyID`)
)
ENGINE = InnoDB;

-- Added by David Rollins 2/23/2007
CREATE TABLE `invversionsel` (
  `InvVersionSelID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `InvVersionID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `LocaleID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `LocationID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `CompanyID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY(`InvVersionSelID`),
  INDEX `Index_InvVersionID` (`InvVersionID`),
  INDEX `Index_LocaleID`(`LocaleID`),
  INDEX `Index_LocationID` (`LocationID`),
  INDEX `Index_CompanyID`(`CompanyID`)
)
ENGINE = InnoDB;

-- Added by David Rollins 2/23/2007
ALTER TABLE `invmaster` ADD COLUMN `InvVersionID` INTEGER UNSIGNED NOT NULL DEFAULT 0;

-- Added by David Rollins 2/23/2007
ALTER TABLE `invmaster` DROP INDEX `nameIndex`,
 ADD UNIQUE `nameIndex`(`ItemName`, `Locale`, `InvVersionID`);

-- Added by David Rollins 2/23/2007
ALTER TABLE `packagepricelist` ADD COLUMN `RecordType` SMALLINT UNSIGNED NOT NULL DEFAULT 1,
 ADD COLUMN `InvMasterID` INTEGER UNSIGNED AFTER `RecordType`;

-- Added by David Rollins 2/28/07
insert into invversion (InvVersionID, PriceListID, CompanyID, Name, Description, InvFromDate, InvToDate, Active, Timestamp) values 
    (1, (select DISTINCT PriceListVersion from pricelist where localeID in ( select localeID from locales where companyid = 6 ) order by PriceListVersion DESC limit 2, 1), (select max(companyid) from locales), "Default", "Default", null, null, 1, '2007-01-01 01:01:01');

-- Added by David Rollins 3/05/07
update invmaster set InvVersionID = (select InvVersionID from invversion where companyID = 1);




/***************************************
-- Cemetary Manager delivered by QPrime.  
-- 
-- 03/13/2007
-- BEGIN CHANGES
***************************************/

SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `cem_deceased` (
  `DecId` int(10) NOT NULL auto_increment,
  `DecFirstName` varchar(14) default NULL,
  `DecLastName` varchar(24) default NULL,
  `DecMidName` varchar(14) default NULL,
  `DecTitle` varchar(10) default NULL,
  `DecAptNo` varchar(15) default NULL,
  `DecStreet` varchar(29) default NULL,
  `DecCity` varchar(29) default NULL,
  `DecState` varchar(19) default NULL,
  `DecZip` varchar(10) default NULL,
  `DecPhone` varchar(20) default NULL,
  `DecSpaceID` int(10) default NULL,
  `DecSuffix` varchar(10) default NULL,
  `DecMemorialName` varchar(45) default NULL,
  `DecMaidenName` varchar(24) default NULL,
  `DecPlaceOfDeath` varchar(29) default NULL,
  `DecDateOfBirth` varchar(20) default NULL,
  `DecDateOfDeath` varchar(20) default NULL,
  `DecAge` int(3) default NULL,
  `DecIntermentDate` varchar(20) default NULL,
  PRIMARY KEY  (`DecId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `cem_nextofkin` (
  `NOKId` int(10) NOT NULL auto_increment,
  `NOKTitle` varchar(10) default NULL,
  `NOKFirstName` varchar(14) default NULL,
  `NOKLastName` varchar(24) default NULL,
  `NOKMidName` varchar(14) default NULL,
  `NOKPhone` varchar(20) default NULL,
  `NOKRelation` varchar(20) default NULL,
  `NOKStreet1` varchar(29) default NULL,
  `NOKStreet2` varchar(29) default NULL,
  `NOKStreet3` varchar(29) default NULL,
  `NOKCity` varchar(29) default NULL,
  `NOKState` varchar(19) default NULL,
  `NOKZip` varchar(10) default NULL,
  `NOKSpaceID` int(10) default NULL,
  PRIMARY KEY  (`NOKId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `cem_owners` (
  `OwnerID` int(10) NOT NULL auto_increment,
  `OwnFirstName` varchar(14) default NULL,
  `OwnLastName` varchar(24) default NULL,
  `OwnMidName` varchar(14) default NULL,
  `OwnTitle` varchar(10) default NULL,
  `OwnAptNo` varchar(15) default NULL,
  `OwnStreet` varchar(29) default NULL,
  `OwnCity` varchar(29) default NULL,
  `OwnState` varchar(19) default NULL,
  `OwnZip` varchar(10) default NULL,
  `OwnPhone` varchar(20) default NULL,
  PRIMARY KEY  (`OwnerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `cem_properties` (
  `PropID` int(10) NOT NULL auto_increment,
  `PropOwnerID1` int(10) NOT NULL,
  `PropOwnerID2` int(10) default NULL,
  `PropOwnerID3` int(10) default NULL,
  `PropBuyerID` int(10) default NULL,
  `PropPNFlag` tinyint(1) default NULL,
  `PropOccStat` tinyint(1) default NULL,
  `PropType` varchar(20) default NULL,
  `PropSpaceID` int(10) default NULL,
  `PropAgreementDate` varchar(45) default NULL,
  `PropAgreeTime` varchar(10) default NULL,
  `PropCemetery` varchar(45) default NULL,
  `PropDirector` varchar(45) default NULL,
  `PropWorkOrderNum` int(10) default NULL,
  PRIMARY KEY  (`PropID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `cem_space` (
  `SpaceID` int(10) NOT NULL auto_increment,
  `SpcType` char(1) default NULL,
  `SpcTypeNumber` int(10) default NULL,
  `SpcParentID` int(10) default NULL,
  `SpcTypeName` varchar(20) default NULL,
  `SpcDesc` varchar(20) default NULL,
  `SpcBought` tinyint(1) default NULL,
  `SpcPropId` int(10) default NULL,
  PRIMARY KEY  (`SpaceID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `cem_PreNeed` (
  `Cem_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `Cem_loc` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_MapID` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_Record` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_VitalsKey` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_ContractDate` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_PNBuyerTitle`       VARCHAR(10),
  `Cem_PNBuyerFirstName`   VARCHAR(14),
  `Cem_PNBuyerMidName`     VARCHAR(14),
  `Cem_PNBuyerLastName`    VARCHAR(24),
  `Cem_PNBuyerStreet`      VARCHAR(29),
  `Cem_PNBuyerAptNo`       VARCHAR(15),
  `Cem_PNBuyerCity`        VARCHAR(29),
  `Cem_PNBuyerState`       VARCHAR(19),
  `Cem_PNBuyerZip`         VARCHAR(10),
  `Cem_PNBuyerPhone`         VARCHAR(10), 
  `Cem_plottype` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_section` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_block` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_lot_tier` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_grave_row` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_Amount` VARCHAR(45) NOT NULL DEFAULT '',  
  `Cem_MiscDesc` VARCHAR(45) NOT NULL DEFAULT '',  
  `Cem_MiscAmount` VARCHAR(45) NOT NULL DEFAULT '',  
  `Cem_PNCounselor`        VARCHAR(39),
  PRIMARY KEY(`Cem_id`)
)
ENGINE = InnoDB
COMMENT = 'This is the actual plot info';


CREATE TABLE `cem_atneed` (
  `Cem_id`  INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `Cem_loc` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_MapID` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_Record` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_VitalsKey` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_ContractDate` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_ANBuyerTitle` VARCHAR(10),
  `Cem_ANBuyerFirstName` VARCHAR(14),
  `Cem_ANBuyerMidName` VARCHAR(14),
  `Cem_ANBuyerLastName` VARCHAR(24),
  `Cem_ANBuyerStreet` VARCHAR(29),
  `Cem_ANBuyerAptNo` VARCHAR(15),
  `Cem_ANBuyerCity` VARCHAR(29),
  `Cem_ANBuyerState` VARCHAR(19),
  `Cem_ANBuyerZip` VARCHAR(10),
  `Cem_ANBuyerPhone` VARCHAR(10),
  `Cem_plottype` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_section` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_block` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_lot_tier` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_grave_row` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_Amount` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_MiscDesc` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_MiscAmount` VARCHAR(45) NOT NULL DEFAULT '',
  `Cem_ANCounselor` VARCHAR(39),
  PRIMARY KEY  (`Cem_id`)
)
ENGINE=InnoDB
COMMENT= 'THis is the actual plot info';


CREATE TABLE `cem_record` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `vitalskey` VARCHAR(45) NOT NULL DEFAULT '',
  `purchasefname` VARCHAR(45) NOT NULL DEFAULT '',
  `purchasemname` VARCHAR(45) NOT NULL DEFAULT '',
  `purchaselname` VARCHAR(45) NOT NULL DEFAULT '',
  `purchasestreet` VARCHAR(45) NOT NULL DEFAULT '',
  `purchasecity` VARCHAR(45) NOT NULL DEFAULT '',
  `purchasestate` VARCHAR(45) NOT NULL DEFAULT '',
  `purchasecounty` VARCHAR(45) NOT NULL DEFAULT '',
  `purchasezip` VARCHAR(45) NOT NULL DEFAULT '',
  PRIMARY KEY(`id`)
);

ALTER TABLE `locales` ADD COLUMN `FDMS_LocaleType`  TINYINT(1) UNSIGNED 
	NOT NULL DEFAULT 1;

/***************************************
-- Cemetary Manager delivered by QPrime.  
-- END CHANGES
***************************************/

-- Added by Srini Kotha 04/02/2007
INSERT INTO `localeconfigtypes` (`localeConfigTypeID`,`name`,`description`,`defaultValue`) VALUES 
 (5,'Services - Show detailed Address for PallBearers','Show detailed Address for PallBearers on Services Screen',1);

-- Name length Changes by Srini Kotha 04/10/2007
alter Table `apvendors` CHANGE COLUMN `Name` `Name` varchar(150) NOT NULL default '';
alter Table `apvendors` CHANGE COLUMN `ContactName` `ContactName` varchar(150) default NULL;
alter Table `arrangers` CHANGE COLUMN `Name` `Name` varchar(150) NOT NULL default '';
alter Table `billtoparties` CHANGE COLUMN `FirstName` `FirstName` varchar(50) default NULL;
alter Table `billtoparties` CHANGE COLUMN  `LastName` `LastName` varchar(50) default NULL;
alter Table `cem_atneed` CHANGE COLUMN `Cem_ANBuyerFirstName` `Cem_ANBuyerFirstName` varchar(50) default NULL;
alter Table `cem_atneed` CHANGE COLUMN `Cem_ANBuyerMidName` `Cem_ANBuyerMidName` varchar(50) default NULL;
alter Table `cem_atneed` CHANGE COLUMN `Cem_ANBuyerLastName` `Cem_ANBuyerLastName` varchar(50) default NULL;
alter Table `cem_deceased` CHANGE COLUMN `DecFirstName` `DecFirstName` varchar(50) default NULL;
alter Table `cem_deceased` CHANGE COLUMN `DecLastName` `DecLastName` varchar(50) default NULL;
alter Table `cem_deceased` CHANGE COLUMN `DecMidName` `DecMidName` varchar(50) default NULL;
alter Table `cem_deceased` CHANGE COLUMN `DecMaidenName` `DecMaidenName` varchar(50) default NULL;
alter Table `cem_deceased` CHANGE COLUMN `DecMemorialName` `DecMemorialName` varchar(150) default NULL;
alter Table `cem_nextofkin` CHANGE COLUMN `NOKFirstName` `NOKFirstName` varchar(50) default NULL;
alter Table `cem_nextofkin` CHANGE COLUMN `NOKLastName` `NOKLastName` varchar(50) default NULL;
alter Table `cem_nextofkin` CHANGE COLUMN `NOKMidName` `NOKMidName` varchar(50) default NULL;
alter Table `cem_owners` CHANGE COLUMN `OwnFirstName` `OwnFirstName` varchar(50) default NULL;
alter Table `cem_owners` CHANGE COLUMN `OwnLastName` `OwnLastName` varchar(50) default NULL;
alter Table `cem_owners` CHANGE COLUMN `OwnMidName` `OwnMidName` varchar(50) default NULL;
alter Table `cem_record` CHANGE COLUMN `purchasefname` `purchasefname` varchar(50) NOT NULL default '';
alter Table `cem_record` CHANGE COLUMN `purchasemname` `purchasemname` varchar(50) NOT NULL default '';
alter Table `cem_record` CHANGE COLUMN `purchaselname` `purchaselname` varchar(50) NOT NULL default '';
alter Table `donations` CHANGE COLUMN `FirstName` `FirstName` varchar(50) default NULL;
alter Table `donations` CHANGE COLUMN `LastName` `LastName` varchar(50) default NULL;
alter Table `locales` CHANGE COLUMN `ManagerName` `ManagerName` varchar(150) default NULL;
alter Table `locations` CHANGE COLUMN `ManagerName` `ManagerName` varchar(150) default NULL;
alter Table `person` CHANGE COLUMN `ExecutorFirstName` `ExecutorFirstName` varchar(50) default NULL;
alter Table `person` CHANGE COLUMN `ExecutorLastName` `ExecutorLastName` varchar(50) default NULL;
alter Table `pncontracts` CHANGE COLUMN  `FulfillmentContactName` `FulfillmentContactName` varchar(150) default NULL;
alter Table `pncontracts` CHANGE COLUMN  `CancellationFundsName` `CancellationFundsName` varchar(150) default NULL;
alter Table `survivors` CHANGE COLUMN `LastName` `LastName` varchar(50) default NULL;
alter Table `survivors` CHANGE COLUMN  `FirstName` `FirstName` varchar(50) default NULL;
alter Table `survivors` CHANGE COLUMN  `MiddleName` `MiddleName` varchar(50) default NULL;
alter Table `survivors` CHANGE COLUMN  `MaidenName` `MaidenName` varchar(50) default NULL;
alter Table `veteransinfo` CHANGE COLUMN `FlagVetName` `FlagVetName` varchar(150) default NULL;
alter Table `veteransinfo` CHANGE COLUMN `Ben1ClaimantName` `Ben1ClaimantName` varchar(150) default NULL;
alter Table `veteransinfo` CHANGE COLUMN `HeadFirstName` `HeadFirstName` varchar(50) default NULL;
alter Table `veteransinfo` CHANGE COLUMN `HeadMiddleName` `HeadMiddleName` varchar(50) default NULL;
alter Table `veteransinfo` CHANGE COLUMN `HeadLastName` `HeadLastName` varchar(50) default NULL;
alter Table `veteransinfo` CHANGE COLUMN `FlagVetOtherName` `FlagVetOtherName` varchar(150) default NULL;
alter Table `vitalstats` CHANGE COLUMN `DeceasedFirstName` `DeceasedFirstName` varchar(50) NOT NULL default '';
alter Table `vitalstats` CHANGE COLUMN `DeceasedLastName` `DeceasedLastName` varchar(50) NOT NULL default '';
alter Table `vitalstats` CHANGE COLUMN `SpouseFirstName` `SpouseFirstName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `SpouseMiddleName` `SpouseMiddleName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `SpouseLastName` `SpouseLastName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `FathersName` `FathersName` varchar(150) default NULL;
alter Table `vitalstats` CHANGE COLUMN `MothersName` `MothersName` varchar(150) default NULL;
alter Table `vitalstats` CHANGE COLUMN `InformantFirstName` `InformantFirstName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `InformantLastName` `InformantLastName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `DoctorName` `DoctorName` varchar(150) default NULL;
alter Table `vitalstats` CHANGE COLUMN `FatherFirstName` `FatherFirstName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN  `FatherMiddleName` `FatherMiddleName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `FatherLastName` `FatherLastName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `NextKinFirstName` `NextKinFirstName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `NextKinLastName` `NextKinLastName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `MotherFirstName` `MotherFirstName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `MotherMiddleName` `MotherMiddleName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN  `MotherLastName` `MotherLastName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `DeceasedFullName` `DeceasedFullName` varchar(150) default NULL;
alter Table `vitalstats` CHANGE COLUMN `EmbalmerName` `EmbalmerName` varchar(150) default NULL;
alter Table `vitalstats` CHANGE COLUMN `PNBuyerFirstName` `PNBuyerFirstName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `PNBuyerMidName`  `PNBuyerMidName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `PNBuyerLastName` `PNBuyerLastName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `DeceasedMidName` `DeceasedMidName` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `DirectorName` `DirectorName` varchar(150) default NULL;
alter Table `vitalstats` CHANGE COLUMN `BirthSurname` `BirthSurname` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `BirthGivenNames` `BirthGivenNames` varchar(150) default NULL;
alter Table `vitalstats` CHANGE COLUMN `Doctor2Name` `Doctor2Name` varchar(150) default NULL;
alter Table `vitalstats` CHANGE COLUMN `CustomerName` `CustomerName` varchar(150) default NULL;
alter Table `vitalstats` CHANGE COLUMN `FatherForename` `FatherForename` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `FatherSurnameBirth` `FatherSurnameBirth` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `FatherOtherSurname` `FatherOtherSurname` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `MotherForename` `MotherForename` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `MotherSurnameBirth` `MotherSurnameBirth` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `MotherOtherSurname` `MotherOtherSurname` varchar(50) default NULL;
alter Table `vitalstats` CHANGE COLUMN `DeceasedMaidenName` `DeceasedMaidenName` varchar(59) default NULL;
alter Table `vitalstats` CHANGE COLUMN `DoctorOccupation` `DoctorOccupation` varchar(100) default NULL;
alter Table `vitalstats` CHANGE COLUMN `FatherOccupation` `FatherOccupation` varchar(100) default NULL;  
alter Table `vitalstats` CHANGE COLUMN `MotherOccupation` `MotherOccupation` varchar(100) default NULL;
alter Table `vitalstats` CHANGE COLUMN `Occupation` `Occupation` varchar(100) default NULL;

-- Added by Srini Kotha 04/16/2007
CREATE TABLE `bookmarks` (                  
             `Name` varchar(254) default NULL,         
             `Description` varchar(254) default NULL,  
             `id` int(11) NOT NULL auto_increment,     
             `Link` varchar(254) default NULL,         
             `Order` int(11) default NULL,             
             PRIMARY KEY  (`id`)                       
           );
CREATE TABLE `bookmarkxref` (                              
                `bookmark_location_id` int(12) NOT NULL auto_increment,  
                `bookmark_id` int(12) NOT NULL,                          
                `location_id` int(12) default NULL,                      
                `locale_id` int(12) default NULL,                        
                PRIMARY KEY  (`bookmark_location_id`)                    
              ); 
-- Added by Srini Kotha 04/18/2007
INSERT INTO `localeconfigtypes` (`localeConfigTypeID`,`name`,`description`,`defaultValue`) VALUES 
 (6,'Show Forms Completed','Show Forms Completed - Report Right Margin',1);
INSERT INTO `localeconfigtypes` (`localeConfigTypeID`,`name`,`description`,`defaultValue`) VALUES 
 (7,'Show At Need Check List','Show At Need Check List - Report Right Margin',1);
INSERT INTO `localeconfigtypes` (`localeConfigTypeID`,`name`,`description`,`defaultValue`) VALUES 
 (8,'Show After Care Check List','Show After Care Check List - Report Right Margin',1);
INSERT INTO `localeconfigtypes` (`localeConfigTypeID`,`name`,`description`,`defaultValue`) VALUES 
 (9,'Show BookMarks','Show BookMarks - Report Right Margin',0);
 







-- Added by David Rollins 04/16/2007
ALTER TABLE `apmaster` ADD COLUMN `ApprovalStatus` INTEGER AFTER `VoidedCode`,
 ADD COLUMN `ApprovedBy` BIGINT AFTER `ApprovalStatus`,
 ADD COLUMN `ApprovedTmstmp` TIMESTAMP AFTER `ApprovedBy`;

-- Added by David Rollins 04/19/2007
ALTER TABLE `speeddata` 
	ADD INDEX `IdentityIndex`(`TabCategory`),
	ADD INDEX `TabDataIndex`(`TabData`),
	ADD INDEX `LocationIndex`(`LocationId`),
	ADD INDEX `LocaleIndex`(`Locale`),
	ADD INDEX `LocaleLocationIndex`(`Locale`, `LocationId`);

-- Added by David Rollins 04/19/2007
ALTER TABLE `visitations` 
	ADD INDEX `EventDateIndex`(`EventDate`),
	ADD INDEX `VitalsIndex`(`VitalsMasterKey`),
	ADD INDEX `DeleteIndex`(`Deleted`);

-- Added by David Rollins 04/19/2007 
ALTER TABLE `speeddata` ADD INDEX `SortSeqIndex`(`SortSequence`),
 ADD INDEX `CombinedIndex`(`TabCategory`, `Locale`, `LocationId`);
 
-- Added by Srini 05/18/2007 webfdms#638
alter table `invhistory` change `Quantity` `Quantity` bigint (11)   NULL;  

-- Added by David Rollins 06/01/2007  
ALTER TABLE `vitalstats` MODIFY COLUMN `ServiceDateKey` VARCHAR(8) CHARACTER SET latin1 COLLATE latin1_swedish_ci; 

-- Added by David Rollins 06/07/2007  
ALTER TABLE `pmntcomponents` MODIFY COLUMN `SaleAmount` BIGINT, MODIFY COLUMN `PaidAmount` BIGINT;

-- Added by David Rollins 6/20/2007
ALTER TABLE `charges` MODIFY COLUMN `Amount` BIGINT, MODIFY COLUMN `TaxExemptAmt` BIGINT;


-- Added By David Rollins 09/11/2007 
ALTER TABLE `locales` ADD COLUMN `Country` VARCHAR(45) NOT NULL DEFAULT 'us' AFTER `FDMS_LocaleType`;
 
 -- Added by CJongs 01/25/08
alter table financialdata  modify SaleType varchar(50);
alter table charges modify InventoryItemName varchar(200);
alter table transactionhistory modify InventoryItemName varchar(200);
alter table invhistory modify Description varchar(50);
alter table transactionhistory modify Description varchar(50);

-- Added by CJongs 03/03/08 
alter table vitalstats add BirthPlaceCounty varchar(30) default "";

-- Added by CJongs 03/05/08
alter table invmaster modify TaxCode varchar(10);

-- Added by CJongs 03/10/08
alter table vitalstats add MotherMaidenName varchar(50);
alter table vitalstats add FatherMaidenName varchar(50);

-- Added by CJongs 05/20/08
alter table servicedata modify CremationDateOfService varchar(8);
alter table servicedata modify AddnlServiceDate varchar(8);

alter table veteransinfo modify FlagBirthDate varchar(8);
alter table veteransinfo modify FlagDeathDate varchar(8);
alter table veteransinfo modify FlagAppDate varchar(8);
alter table veteransinfo modify Ben1BurialDate varchar(8);
alter table veteransinfo modify Ben2Box23bDatePurc varchar(8);
alter table veteransinfo modify Ben2Box23cDatePay varchar(8);
alter table veteransinfo modify Ben2Box32Date varchar(8);
alter table veteransinfo modify DateModified varchar(8);

alter table financialdata modify FirstPaymentDueDate varchar(8);
alter table financialdata modify FinChargeSchedule varchar(8);

alter table invonhand modify DateIn varchar(8);

-- Added by CJongs 06/03/08
alter table vitalstats modify shippingdata varchar(300);

-- Added by CJongs 06/10/08
alter table servicedata modify CardStyle varchar(100);

-- Added by CJongs 06/15/08
alter table formsavailable modify ReportName varchar(60);

-- Added by CJongs 7/2/08
alter table veteransinfo modify HeadConsignNameAddr varchar(100);
alter table veteransinfo modify HeadCemNameAddress varchar(100);
