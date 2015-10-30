-- Added by David Rollins 05/07/2007
ALTER TABLE `locations` ADD COLUMN `AcctUserID` BIGINT NOT NULL DEFAULT 0 COMMENT 'Assigned Accountant\'s UserID' AFTER `Email`,
 ADD INDEX `Index_Locale`(`Locale`),
 ADD INDEX `Index_Company`(`CompanyNumber`),
 ADD INDEX `Index_AcctUserID`(`AcctUserID`);  

-- Added by David Rollins 05/07/2007

create table `locationemail`(
    `LocationEmailID` bigint unsigned not null auto_increment,
   `CompanyID` bigint unsigned default '0' not null,
   `LocaleID` bigint unsigned default '0' not null,
   `LocationID` bigint unsigned default '0' not null,
   `EmailType` int unsigned default '0' not null,
   `EmailAddr` varchar(120) default '' not null,
	  PRIMARY KEY  (`LocationEmailID`),
  	KEY `Index_Location` (`LocationID`),
 		 KEY `Index_Company` (`CompanyID`)
);

-- Added by David Rollins 05/22/2007
ALTER TABLE `vitalstats` MODIFY COLUMN `PNSource` VARCHAR(40);

-- Added by David Rollins 06/12/2007
ALTER TABLE `apmaster` ADD COLUMN `AssociatedVitalsID` INTEGER,
 ADD COLUMN `Check1099` BOOLEAN NOT NULL DEFAULT false AFTER `AssociatedVitalsID`,
 ADD COLUMN `Check1099Amount` INTEGER AFTER `Check1099`; 
 
-- Added by David Rollins 06/25/2007 
ALTER TABLE `apvendors` ADD COLUMN `InternalVendor` BOOLEAN NOT NULL DEFAULT false AFTER `DeleteCode`; 

-- Added by David Rollins 06/25/2007 
CREATE TABLE `apvendorlocations` (
  `vendorLocationID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `vendorID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `locationID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `localeID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `defaultAcct` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY(`vendorLocationID`),
  INDEX `Index_Vendor`(`vendorID`),
  INDEX `Index_Locale`(`localeID`),
  INDEX `Index_Location`(`locationID`, `localeID`)
)
ENGINE = InnoDB;

-- Added By Srini Kotha 06/27/2007 
 alter table `obituary` add column `AsimasId` bigint (11)   NULL COMMENT 'Id received from ASIMAS System' after `ObitNotice`;
 
-- Added By Chai 07/12/2007
alter table servicedata add Minister1_Email varchar(50);
alter table servicedata add Minister2_Email varchar(50);
update speeddatarule set Col1 = 'Email' where TabCategory = 'Minister';

-- Added By Chai 07/18/2007
alter table vitalstats add CountyOfBirth varchar(30); 


-- Added By David Rollins 07/19/2007
CREATE TABLE `formsavailablefilter` (
  `FormFilterID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `FormID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `FilterTypeID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `FilterParameter` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `FilterValue` VARCHAR(45),
  PRIMARY KEY(`FormFilterID`),
  INDEX `Index_FormID`(`FormID`)
)
ENGINE = InnoDB;

create table `formsavailablefiltertype`(
   `FormFilterTypeID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
   `filtername` varchar(80) default '' not null,
   `description` varchar(200),
   `defaultParameter` int,
   `defaultValue` int,   
   primary key (`FormFilterTypeID`)
)
ENGINE = InnoDB;

insert into `formsavailablefiltertype` (`FormFilterTypeID`, `filtername`, `description`, `defaultParameter`, `defaultValue` ) values (1, 'Date Filter', 'Filter by Date', 0,0 );


-- Added By David Rollins 07/25/2007
ALTER TABLE `locations` ADD COLUMN `internalVendorLimit` DOUBLE NOT NULL DEFAULT 0,
 ADD COLUMN `externalVendorLimit` DOUBLE NOT NULL DEFAULT 0 AFTER `internalVendorLimit`;
 
-- Added By Chai Jongs 08/20/2007
ALTER TABLE financialdata modify LastPaymentMemo varchar(50);

-- Added By David Rollins 08/30/2007
ALTER TABLE `apvendorlocations` ADD COLUMN `companyID` INTEGER UNSIGNED NOT NULL DEFAULT 0 AFTER `defaultAcct`,
 ADD INDEX `Index_CompanyID`(`companyID`);

-- Added By David Rollins 09/11/2007 
ALTER TABLE `locales` ADD COLUMN `Country` VARCHAR(45) NOT NULL DEFAULT 'us' AFTER `FDMS_LocaleType`;
 
 
-- Added by Chai Jongs 9/14/2007 
alter table apvendors add VendorState varchar(14);
alter table apvendors add VendorCountry varchar(30);
alter table apvendors add Phone2 varchar(20);
alter table apvendors add Fax varchar(20);
alter table apvendors add AccountNumber varchar(10);
alter table apvendors add Title varchar(10);
alter table apvendors add VendorType varchar(15);
alter table apvendors add DiscountPercentage decimal(5,2);
alter table apvendors add DiscountIfInDays int(5);
alter table apvendors add DiscountDueInDays int(5);
alter table apvendors add Vendor1099Type varchar(15);
alter table apvendors add Vendor1099Payment decimal(10,2);
alter table apvendors add TaxID varchar(30);
alter table apvendors add ApprovedVendor char(1);
alter table apvendors add CreditLimit decimal(10,2);


-- Added by David Rollins 9/24/2007 
CREATE TABLE `invoice` (
  `invoiceID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `vendorID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `locationID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `invoiceNumber` VARCHAR(50) NOT NULL DEFAULT '',
  `invoiceDate` DATE NOT NULL DEFAULT 0,
  `invoiceDueDate` DATE NOT NULL DEFAULT 0,
  `invoicePaid` BOOLEAN NOT NULL DEFAULT 0,
  `glcategory` VARCHAR(20) NOT NULL DEFAULT '',
  `amountOfInvoice` DOUBLE NOT NULL DEFAULT 0,
  `description` VARCHAR(120),
  `1099Type` INTEGER UNSIGNED,
  `1099Amount` DOUBLE,
  `discountFlag` BOOLEAN NOT NULL DEFAULT 0,
  `discountAmount` DOUBLE,
  `discountSubjectAmount` DOUBLE,
  `discountPercent` DOUBLE,
  `discountDollars` DOUBLE,
  `discountDue` INTEGER UNSIGNED,
  `discountDueFreqCode` INTEGER UNSIGNED,
  `discountDueDate` DATE,
  `recurringFlag` BOOLEAN NOT NULL DEFAULT 0,
  `inactive` BOOLEAN NOT NULL DEFAULT 0,
  `recurringCount` INTEGER UNSIGNED,
  `recurringFreq` INTEGER UNSIGNED,
  `recurringFreqCode` INTEGER UNSIGNED,
  `checkingAccount` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `checkingStatus` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `taxPercent` DOUBLE,
  `checkCreated` BOOLEAN, 
  `checkNumber` BIGINT UNSIGNED NOT NULL DEFAULT 0, 
  PRIMARY KEY(`invoiceID`),
  INDEX `Index_VendorID`(`vendorID`),
  INDEX `Index_Paid`(`invoicePaid`),
  INDEX `Index_Date`(`invoiceDate`),
  INDEX `Index_Inactive`(`inactive`),
  INDEX `Index_DueDate`(`invoiceDueDate`)
)
ENGINE = InnoDB;


-- Added by David Rollins 9/24/2007 
CREATE TABLE `invoiceitems` (
  `invoiceItemID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `invoiceID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `inventoryItem` BOOLEAN,
  `itemCode` VARCHAR(45),
  `itemDesc` VARCHAR(45),
  `costPerUnit` DOUBLE,
  `quantity` INTEGER UNSIGNED,
  `glAcctNum` VARCHAR(45),
  `itemCost` DOUBLE,
  `taxable` BOOLEAN,
  `apAccountID` INTEGER UNSIGNED,
  PRIMARY KEY(`invoiceItemID`),
  INDEX `Index_Invoice`(`invoiceID`)
)
ENGINE = InnoDB;


-- Added by David Rollins 9/24/2007 
CREATE TABLE `invoicegltrans` (
  `invoiceGLTransID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `invoiceID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `glAcctNum` VARCHAR(45) NOT NULL DEFAULT '',
  `amount` DOUBLE NOT NULL DEFAULT 0,
  `description` VARCHAR(80) NOT NULL DEFAULT '',
  `apAccountID` INTEGER UNSIGNED,
  PRIMARY KEY(`invoiceGLTransID`),
  INDEX `Index_Invoice`(`invoiceID`)
)
ENGINE = InnoDB;

-- Added by David Rollins 9/24/2007 
CREATE TABLE `invoicerecurringhist` (
  `invoiceRecurringHistID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `invoiceID` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `invoiceDate` DATE NOT NULL DEFAULT 0,
  `invoiceDueDate` DATE NOT NULL DEFAULT 0,
  `invoicePaid` BOOLEAN NOT NULL DEFAULT 0,
  `amountPaid` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY(`invoiceRecurringHistID`),
  INDEX `Index_Invoice`(`invoiceID`)
)
ENGINE = InnoDB;
 

-- Added by David Rollins 10/4/2007  Has been added to main invoice table. 
ALTER TABLE `invoice` ADD COLUMN `discountFlag` BOOLEAN NOT NULL DEFAULT 0 AFTER `1099Amount`;
ALTER TABLE `invoice` ADD COLUMN `taxPercent` DOUBLE AFTER `checkingStatus`;  
ALTER TABLE `invoice` MODIFY COLUMN `glcategory` VARCHAR(20) NOT NULL DEFAULT '';
ALTER TABLE `invoice` ADD COLUMN `checkCreated` BOOLEAN NOT NULL DEFAULT 0 AFTER `taxPercent`;
ALTER TABLE `invoice` ADD COLUMN `checkNumber` BIGINT UNSIGNED NOT NULL DEFAULT 0 AFTER `checkCreated`;

ALTER TABLE `invoiceitems` ADD COLUMN `itemCost` DOUBLE AFTER `glAcctNum`;
ALTER TABLE `invoiceitems` ADD COLUMN `taxable` BOOLEAN AFTER `itemCost`;
ALTER TABLE `invoiceitems` ADD COLUMN `apAccountID` INTEGER UNSIGNED AFTER `taxable`;

ALTER TABLE `invoicegltrans` ADD COLUMN `apAccountID` INTEGER UNSIGNED;  


-- Added by Chai Jongs 10/25/2007  Has been added to servicedata  table. 
ALTER TABLE `servicedata` ADD COLUMN `CemeteryBlockNumber` VARCHAR(30);  

-- Added by Chai Jongs 10/29/2007 the service type need to be change for contain logger info.
alter table `financialdata` modify column `SaleType` varchar(50) default '';


-- Added by Chad Lehnert 12/12/2007 - Added accoutn desription to the inventory and services
ALTER TABLE `invmaster` ADD COLUMN `AccountDescCDID` INT(15);
ALTER TABLE `pricelist` ADD COLUMN `AccountDescCDID` INT(15);
ALTER TABLE `financialdata` ADD COLUMN `SalesDescCDID` INT(15);
ALTER TABLE `transactionhistory` ADD COLUMN `PriceListID` INT(11) AFTER `InvCostOfSale`;
DROP TABLE IF EXISTS `combodata`;
CREATE TABLE `combodata` (
  `comboDataID` int(15) NOT NULL auto_increment,
  `local` int(11) NOT NULL,
  `type` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `value` varchar(50) NOT NULL,
  `description` varchar(200) default NULL,
  `createdDTS` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `updatedDTS` timestamp NULL default NULL,
  PRIMARY KEY  (`comboDataID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (1,0,1,'Professional Services\r\n','1','Professional Services\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (2,0,1,'Facility Charges','2','Facility Charges\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (3,0,1,'Automotive Equipment Charges','3','Automotive Equipment Charges\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (4,0,1,'Other Professional Services','4','Other Professional Services\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (5,0,1,'Crematory Fees','5','Crematory Fees\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (6,0,1,'Discount \r\n','6','Discount \r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (7,0,1,'Package Discount\r\n','7','Package Discount\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (8,0,1,'Caskets','8','Caskets\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (9,0,1,'Urns','9','Urns\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (10,0,1,'Vaults','10','Vaults\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (11,0,1,'Urn-Vaults\r\n','11','Urn-Vaults\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (12,0,1,'Clothing Sales','12','Clothing Sales\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (13,0,1,'Monuments and Markers','13','Monuments and Markers\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (14,0,1,'Other Merchandise','14','Other Merchandise\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (15,0,1,'Flower Sales','15','Flower Sales\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (16,0,1,'Cash Advance Income','16','Cash Advance Income\r\n','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (17,0,2,'At Need Burial','1','At Need Burial ','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (18,0,2,'At Need Cremation','2','At Need Cremation','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (19,0,2,'Maturing Preneed Burial','3','Maturing Preneed Burial','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (20,0,2,'Maturing Preneed Cremation','4','Maturing Preneed Cremation','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (21,0,2,'Shipping','5','Shipping','0000-00-00 00:00:00',NULL);
insert  into `combodata`(`comboDataID`,`local`,`type`,`name`,`value`,`description`,`createdDTS`,`updatedDTS`) values (22,0,2,'Trade\r\n','6','Trade\r\n','0000-00-00 00:00:00',NULL);
DROP TABLE IF EXISTS `glaccttranslation`;
CREATE TABLE `glaccttranslation` (
  `glAcctTransID` int(15) NOT NULL auto_increment,
  `accountDescCDID` int(15) NOT NULL,
  `saleTypeCDID` int(15) NOT NULL,
  `assetAcctGL` varchar(20) NOT NULL,
  `costOfGoodsSoldAcctGL` varchar(20) NOT NULL,
  `salesAcctGL` varchar(20) NOT NULL,
  `createdDTS` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updatedDTS` timestamp NULL default NULL,
  PRIMARY KEY  (`glAcctTransID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (1,1,1,'0','0','30010','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (2,2,1,'0','0','30020','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (3,3,1,'0','0','30030','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (4,4,1,'0','0','30040','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (5,5,1,'0','0','99999','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (6,6,1,'0','0','30090','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (7,7,1,'0','0','30091','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (8,8,1,'12010','40010','30110','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (9,9,1,'12020','99999','99999','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (10,10,1,'12030','40040','30140','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (11,11,1,'12040','99999','99999','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (12,12,1,'0','0','30150','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (13,13,1,'12050','40060','30160','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (14,14,1,'0','0','30180','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (15,15,1,'0','0','54030','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (16,16,1,'0','0','54010','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (17,1,2,'0','0','31010','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (18,2,2,'0','0','31020','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (19,3,2,'0','0','31030','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (20,4,2,'0','0','31040','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (21,5,2,'0','0','31050','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (22,6,2,'0','0','31090','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (23,7,2,'0','0','31091','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (24,8,2,'12010','41010','31110','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (25,9,2,'12020','41030','31130','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (26,10,2,'12030','41040','31140','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (27,11,2,'12040','41040','31140','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (28,12,2,'0','0','31150','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (29,13,2,'12050','41060','31160','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (30,14,2,'0','0','31180','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (31,15,2,'0','0','54030','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (32,16,2,'0','0','54010','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (33,1,3,'0','0','32010','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (34,2,3,'0','0','32020','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (35,3,3,'0','0','32030','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (36,4,3,'0','0','32040','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (37,5,3,'0','0','99999','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (38,6,3,'0','0','32090','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (39,7,3,'0','0','32091','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (40,8,3,'12010','42010','32110','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (41,9,3,'12020','42030','32130','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (42,10,3,'12030','42040','32140','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (43,11,3,'12040','99999','99999','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (44,12,3,'0','0','32150','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (45,13,3,'12050','42060','32160','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (46,14,3,'0','0','32180','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (47,15,3,'0','0','54030','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (48,16,3,'0','0','54010','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (49,1,4,'0','0','39010','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (50,2,4,'0','0','39020','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (51,3,4,'0','0','39030','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (52,4,4,'0','0','39040','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (53,5,4,'0','0','39050','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (54,6,4,'0','0','39090','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (55,7,4,'0','0','39091','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (56,8,4,'12010','49010','39110','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (57,9,4,'12020','49110','39130','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (58,10,4,'12030','99999','99999','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (59,11,4,'12040','49040','39140','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (60,12,4,'0','0','39150','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (61,13,4,'12050','49160','39160','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (62,14,4,'0','0','39180','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (63,15,4,'0','0','54030','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (64,16,4,'0','0','54010','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (65,1,5,'0','0','36010','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (66,2,5,'0','0','36020','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (67,3,5,'0','0','36030','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (68,4,5,'0','0','36010','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (69,5,5,'0','0','36010','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (70,6,5,'0','0','36090','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (71,7,5,'0','0','36091','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (72,8,5,'12010','46010','36110','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (73,9,5,'12020','46010','36110','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (74,10,5,'12030','46010','36140','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (75,11,5,'12040','46010','36140','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (76,12,5,'0','0','36160','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (77,13,5,'12050','46080','36160','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (78,14,5,'0','0','36160','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (79,15,5,'0','0','54030','2007-12-20 08:34:22',NULL);
insert  into `glaccttranslation`(`glAcctTransID`,`accountDescCDID`,`saleTypeCDID`,`assetAcctGL`,`costOfGoodsSoldAcctGL`,`salesAcctGL`,`createdDTS`,`updatedDTS`) values (80,16,5,'0','0','54010','2007-12-20 08:34:22',NULL);


-- Added by Chai Jongs 12/20/2007  drop glitem related.
DROP TABLE IF EXISTS `invoicegltrans`;


-- Added by Chad Lehnert 12/29/2007
CREATE TABLE `invoicetranshist` (
  `invoiceTransHistoryID` int(15) NOT NULL auto_increment,
  `invoiceID` int(10) NOT NULL,
  `locationID` int(11) NOT NULL,
  `type` int(10) NOT NULL,
  `description` varchar(120) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `glAccount` varchar(20) NOT NULL,
  `transactionDate` date NOT NULL,
  `posted` char(1) NOT NULL,
  `createdDTS` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `updatedDTS` timestamp NULL default NULL,
  PRIMARY KEY  (`invoiceTransHistoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `apdistribution` ADD COLUMN `PrintedDate` DATE AFTER `TranType`;

-- Added by Chai Jongs 01/02/2008 drop tax related to AP module
alter table invoice drop taxPercent;
alter table invoiceitems drop taxable;

-- Added by Chad 01/04/08 - Added a column to the speed data rule for payments.
update speeddatarule set Col2='Subtracts from balance? (Y/N)' where TabCategory='PAYTYPES';
update speeddata set TabData = concat(TabData, ",Y") where tabcategory like '%paytype%';

alter table invoice add invoiceStatus char(1) default 'C';

-- Added by Chai 01/18/08 - Drop inactive field on invoice
alter table invoice drop inactive;

-- Added by Chad 01/18/08 - We had to increase the size to get the sql to work.
alter table invhistory modify ReferenceNumber VARCHAR(20);

-- Added by Chad 02/11/08 - We had to increase the size to get the sql to work.
ALTER TABLE apmaster ADD COLUMN `InvoiceID` INTEGER AFTER `MasterID`;
CREATE TABLE `apdistributionhistory` (
  `apDistHistID` int(11) NOT NULL auto_increment,
  `apMasterID` int(11) NOT NULL default '0',
  `apAccountNumber` varchar(15) NOT NULL,
  `amount` int(11) NOT NULL default '0',
  `discountAmount` int(11) NOT NULL,
  `type` char(1) NOT NULL,
  `posted` char(1) default NULL,
  `memo` varchar(30) default NULL,
  `createdDTS` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`apDistHistID`),
  KEY `Master_index` (`apMasterID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Added by Chad 02/14/08 - We needed a unique vendor code.
alter table apvendors add VendorCode varchar(20) default '' after VendorID ;

-- Added by Chai 02/15/08 - update the charges table to keep that the item is from package or not.
alter table charges add column fromPackage tinyint(1) default 0;

-- Added by Chai 02/18/08 - Take out the checkNumber from Invoice table
alter table invoice drop checkNumber;

-- Added by Chai 03/06/08 - Increase  the length of location's name to 50
alter table locations modify Name varchar(50);

-- Added by CJongs 03/10/08
alter table vitalstats add MotherMaidenName varchar(50);
alter table vitalstats add FatherMaidenName varchar(50);

-- Added by CJongs 03/14/08
alter table apvendors drop Title;
alter table apvendors drop VendorType;

-- Added by CJongs 04/24/08
alter table transactionhistory add ServiceDate varchar(8);

-- Added by Chad 04/28/08
-- Dont Run THis NOW  WAIT
-- update transactionhistory set ServiceDate = (select ServiceDateKey from vitalstats where VitalsMasterKey=transactionhistory.VitalsMasterKey);
-- Thanks for not running the previous line of code.

-- Added by Chad 4/29/08
ALTER TABLE `combodata` MODIFY COLUMN `value` INT;

-- Added by CJongs 06/17/08
alter table vitalstats modify CaseCode varchar(20);
alter table vitalstats modify FacilityStreet varchar(50);

-- Added by CJongs 7/01/08
alter table transactionhistory add DatetimeOfTrans datetime;

-- Added by CJongs 08/15/08
alter table invhistory modify ItemName varchar(20);

-- Added by CJongs 8/04/08
alter table transactionhistory modify Description varchar(79);
alter table transactionhistory modify InventoryItemName varchar(20);
alter table charges modify Description varchar(79);
alter table charges modify InventoryItemName varchar(20);
alter table invhistory modify ItemName varchar(20);

-- Added by CJongs 08/27/08
alter table vitalstats modify PNDepository varchar(40);

-- Added by CJongs 08/28/08
alter table formsavailable add column Datapull varchar(10) default 'CR';
alter table formsavailable add column StoredProc varchar(60) default '';
alter table formsavailable add column XmlFile varchar(60) default '';

-- Added by CJongs 09/04/08
alter table vitalstats add column CallInfoNote varchar(500) after FatherMaidenName;

-- Added by CJongs 09/04/08
alter table vitalstats modify PreneedCasketName varchar(40);

-- Added by CJongs 09/08/08
create index value on combodata (value);
create index SalesDescCDID on financialdata (SalesDescCDID);
create index LocaleNumber on vitalstats (LocaleNumber);

-- Added By chad 09/12/08
## This code is needed to update the distribution for those sites that run export info
INSERT into apdistributionhistory (apMasterID, apAccountNumber, amount, discountAmount, type, posted, memo, createdDTS)
SELECT MasterID, (select AccountNumber from apaccounts where AccountID = DistributionAcctID), DistributionAmount, 0, '', 
Posted, Memo, now() FROM apdistribution;

-- Added by CJongs 09/17/08
-- alter table apvendors modify DefaultAcct varchar(15);
-- alter table apvendorlocations modify defaultAcct varchar(15);

-- Added by CJongs 09/15/08
-- delete from apvendorlocations;
-- update apvendors, apaccounts set apvendors.DefaultAcct = apaccounts.AccountNumber
-- where apvendors.DefaultAcct = apaccounts.AccountID;
-- insert into apvendorlocations (vendorID,locationID,localeID,defaultAcct,companyID) 
-- (select VendorID,LocationID,Locale,DefaultAcct,companyid from apvendors,locales where locales.LocaleID = apvendors.locale);








