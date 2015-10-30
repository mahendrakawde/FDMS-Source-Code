-- cjongs 05/06/11

-- create connection to database using FDMS_Share (webfdms-share-ds.xml).

CREATE TABLE `eregister` (
  `RegisterId` INT(11) NOT NULL AUTO_INCREMENT,
  `CompanyId` INT(11) NOT NULL DEFAULT 0,
  `LocaleId` INT(11) NOT NULL DEFAULT 0,
  `LocationId` INT(11) NOT NULL DEFAULT 0,
  `CaseId` INT(11) NOT NULL DEFAULT 0,
  `RegBookHeader`  text,
  `VideoLink` VARCHAR(255) DEFAULT '',
  `TargetWebPage` VARCHAR(255) DEFAULT '',
  PRIMARY KEY (`RegisterId`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

ALTER TABLE eregister ADD COLUMN ReferenceId INT(11) DEFAULT 0 AFTER TargetWebPage;
CREATE UNIQUE INDEX com_case_inx ON eregister (CompanyId,CaseId);

ALTER TABLE eregister ADD COLUMN ImageUrl VARCHAR(255) DEFAULT '' AFTER ReferenceId;
ALTER TABLE eregister ADD COLUMN DeceasedFullName VARCHAR(255) DEFAULT '' AFTER ImageUrl;
ALTER TABLE eregister ADD COLUMN locationName VARCHAR(255) DEFAULT '' AFTER DeceasedFullName;
ALTER TABLE eregister ADD COLUMN localeName VARCHAR(255) DEFAULT '' AFTER locationName;