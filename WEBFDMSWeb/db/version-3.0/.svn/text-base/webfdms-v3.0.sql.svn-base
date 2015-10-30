ALTER TABLE survivors CHANGE PreferCommunicateYN PreferComunicate VARCHAR(15) DEFAULT '';

ALTER TABLE creditcard ADD COLUMN ResponseCard VARCHAR(200) DEFAULT '' AFTER VitalsId;
ALTER TABLE creditcard ADD COLUMN ErrorDetail  VARCHAR(200) DEFAULT '' AFTER ResponseCard;
ALTER TABLE creditcard ADD COLUMN ApprovalCode VARCHAR(50) DEFAULT '' AFTER ErrorDetail;

/*	-- Comment by Parth
CREATE TABLE `eregister` (
  `RegisterId` INT(11) NOT NULL AUTO_INCREMENT,
  `CompanyId` INT(11) NOT NULL DEFAULT 0,
  `LocaleId` INT(11) NOT NULL DEFAULT 0,
  `LocationId` INT(11) NOT NULL DEFAULT 0,
  `CaseId` INT(11) NOT NULL DEFAULT 0,
  `RegBookHeader`  TEXT,
  `VideoLink` VARCHAR(255) DEFAULT '',
  `TargetWebPage` VARCHAR(255) DEFAULT '',
  `ReferenceId` INT(11) DEFAULT 0,
  PRIMARY KEY (`RegisterId`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

CREATE UNIQUE INDEX com_case_inx ON eregister (CompanyId,CaseId);*/
/*	-- Comment by Parth
ALTER TABLE eregister ADD COLUMN ImageUrl VARCHAR(255) DEFAULT "" AFTER ReferenceId;
ALTER TABLE eregister ADD COLUMN DeceasedFullName VARCHAR(255) DEFAULT ""  AFTER ImageUrl;
ALTER TABLE eregister ADD COLUMN locationName VARCHAR(255) DEFAULT "" AFTER DeceasedFullName;
ALTER TABLE eregister ADD COLUMN localeName VARCHAR(255) DEFAULT "" AFTER locationName;*/

ALTER TABLE obituary ADD COLUMN ObituaryLink TEXT AFTER ObitNotice;


-- Update for Ticket #5217 Email Address 
alter table vitalstats add column `informantPreferCommunicate` varchar(15) default '';  
alter table vitalstats add column `NextKinEmail` varchar(50) default NULL; 
alter table vitalstats add column `NextKinPreferCommunicate` varchar(15) default '';

-- Update for Ticket #5509 Add phone to service screen and Speeddata
update speeddatarule set col5='Phone' where tabcategory='Srvplace';
update speeddatarule set col5='Phone' where tabcategory='churches';
alter table servicedata add column PlaceOfServPhone varchar(19) default NULL;
alter table servicedata add column AddnlServicePhone varchar(19) default NULL;
alter table servicedata add column ChurchPhone varchar(19) default NULL;

ALTER TABLE securityconfig ADD COLUMN ObitConnectURL VARCHAR(100) DEFAULT NULL;

ALTER TABLE securityconfig ADD COLUMN lifefilesUserName VARCHAR (100) DEFAULT NULL;