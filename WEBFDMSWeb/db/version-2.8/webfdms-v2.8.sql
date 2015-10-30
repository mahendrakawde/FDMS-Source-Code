-- Added by Kier 03/01/2006 --
alter table person add column ExecutorEmail varchar (50) NULL;

-- Added by Guad on 04/21/2006
CREATE TABLE dbversion (
	DbVersionID		INT(5)		NOT NULL	AUTO_INCREMENT,
	WebfdmsVersion	VARCHAR(10)	,
	SqlScript		TEXT		,
	ModifiedBy		VARCHAR(20)	,
	ModifiedDate	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(DbVersionID)
);

-- Added by Guad on 04/24/2006
USE webfdmsusers;
CREATE TABLE dblog (
	DbLogID			INT(5)		NOT NULL	AUTO_INCREMENT,
	SqlScript		TEXT		,
	ModifiedBy		VARCHAR(20)	,
	ModifiedDate	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(DbLogID)
);

-- Added by Ranando 05/12/2006
Alter table Vitalstats
	add XopsyToFindCause Char comment 'Was the biopsy/autopsy used to find the cause of death?',
	add OperationType varchar(64) comment 'What kind of operation was performed?',
	add InjuryState varchar(2) comment 'State where fatal injury occurred' after InjuryCityState,
	add InjuryCounty varchar(30) comment 'County where fatal injury occurred' after InjuryCityState,
	add InjuryAptNo	varchar(16) comment 'Apartment where fatal injury occurred' after InjuryCityState,
	add InjuryZipCode varchar(16) comment 'Zip code where fatal injury occurred' after InjuryCityState;

Create Database If Not Exists commondata;

DROP TABLE IF EXISTS `zipcodedata`;

CREATE TABLE `zipcodedata` (                                                                                                     
	`ID` int(11) NOT NULL auto_increment COMMENT 'Unique record identifier used to implement foreign keys and a failsafe system',  
	`ZipCode` varchar(16) NOT NULL COMMENT 'USA Zip Code (01234-6789)',                                                            
	`State` char(2) NOT NULL COMMENT '2 digit State/Province Abbreviation',                                                        
	`City` varchar(64) default NULL COMMENT 'City name or common alias',                                                           
	`County` varchar(64) default NULL COMMENT 'County of interest or other division of a foreign country',                         
	`Country` varchar(64) NOT NULL COMMENT 'Country of interest',                                                                  
	`TimeZone` int(11) NOT NULL default '0' COMMENT 'GMT Time Zone offset for this ZipCode',                                       
	`DLSTime` tinyint(1) NOT NULL default '0' COMMENT 'True if this ZipCode uses Daylight Savings Time',                           
	`Deleted` tinyint(1) NOT NULL default '0' COMMENT 'Record deletion flag used to implement a failsafe system',                  
	`Failsafe` tinyint(1) NOT NULL default '0' COMMENT 'Flag marking this record as part of an uncommitted rollback',              
	PRIMARY KEY  (`ID`)                                                                                                            
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;
