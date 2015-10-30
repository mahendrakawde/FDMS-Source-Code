
-- added by David Rollins 6/16/2006
ALTER TABLE `usersecurity` ADD COLUMN `chgPassword` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
 ADD COLUMN `userLocked` SMALLINT UNSIGNED NOT NULL DEFAULT 0 AFTER `chgPassword`,
 ADD COLUMN `companyID` INTEGER UNSIGNED NOT NULL DEFAULT 0 AFTER `userLocked`,
 ADD COLUMN `lockoutTmstmp` TIMESTAMP NOT NULL DEFAULT 0 AFTER `companyID`;


-- added by David Rollins 6/20/2006
CREATE TABLE companies (
	CompanyID int NOT NULL auto_increment
		Comment 'Logical identifier for this company',
	Name varchar(96) NOT NULL
		Comment 'Company name',
	Address1 varchar(64) NOT NULL
		Comment 'Company address: line 1',
	Address2 varchar(64) default NULL
		Comment 'Company address: line 2',
	Address3 varchar(64) default NULL
		Comment 'Company address: line 3',
	City varchar(64) NOT NULL
		Comment 'Company city',
	State char(2) default NULL
		Comment 'Company state',
	Country varchar(64) NOT NULL
		Comment 'Company country',
	PostCode varchar(16) NOT NULL
		Comment 'Company postal code',
	BillingAddress1 varchar(64) NOT NULL
		Comment 'Company address: line 1',
	BillingAddress2 varchar(64) default NULL
		Comment 'Company address: line 2',
	BillingAddress3 varchar(64) default NULL
		Comment 'Company address: line 3',
	BillingCity varchar(64) NOT NULL
		Comment 'Company billing city',
	BillingState char(2) default NULL
		Comment 'Company billnig state',
	BillingCountry varchar(64) NOT NULL
		Comment 'Company billing country',
	BillingPostCode varchar(16) NOT NULL
		Comment 'Company billing postal code',
	Deleted bool NOT NULL default '0'
		Comment 'Record deletion flag used to implement a failsafe system',
	DataURL varchar(128) NOT NULL
		Comment 'Database URL',
	SQLUser varchar(20) NOT NULL
		Comment 'SQL Username',
	SQLPass varchar(20) NOT NULL
		Comment 'SQL Password',
	PRIMARY KEY	(CompanyID)
);

-- added by drollins 6/26/06
ALTER TABLE `usersecurity` ADD COLUMN `passwordTmstmp` TIMESTAMP NOT NULL DEFAULT 0;


-- Added by David Rollins 6/67/06

ALTER TABLE `usersecurity` MODIFY COLUMN `Password` VARCHAR(45) 
	CHARACTER SET latin1 COLLATE latin1_swedish_ci;


-- Added by David Rollins 7/03/06

ALTER TABLE `userlocations` ADD COLUMN `locale_Id` INTEGER(12) UNSIGNED NOT NULL DEFAULT 0;


-- Added by David Rollins 8/3/2006

ALTER TABLE `userlog` MODIFY COLUMN `user_agent` VARCHAR(150);

