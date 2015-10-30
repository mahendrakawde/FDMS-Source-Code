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