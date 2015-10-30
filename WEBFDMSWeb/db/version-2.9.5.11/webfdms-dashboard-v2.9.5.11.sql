

ALTER TABLE pmntcomponents TYPE=InnoDB;
ALTER TABLE transactionhistory TYPE=InnoDB;
ALTER TABLE financialdata TYPE=InnoDB;
ALTER TABLE charges TYPE=InnoDB;
ALTER TABLE invhistory TYPE=InnoDB;
ALTER TABLE invonhand TYPE=InnoDB;
ALTER TABLE person TYPE=InnoDB;
ALTER TABLE visitations TYPE=InnoDB;



--  cjongs add 07/22/09
CREATE TABLE `reportscheduling` (
  `SchedulingID` INT(11) NOT NULL AUTO_INCREMENT,
  `FormID` INT(11) NOT NULL DEFAULT '0',
  `Locale` VARCHAR(1500) NOT NULL DEFAULT '',
  `Location` VARCHAR(1500) NOT NULL DEFAULT '',
  `FromDate` DATE DEFAULT NULL,
  `ToDate` DATE DEFAULT NULL,
  `RunDate` DATE DEFAULT NULL,
  `EmailTo` VARCHAR(255) DEFAULT '',
  `EmailCC` VARCHAR(255) DEFAULT '',
  `RepeatType` CHAR(1) NOT NULL DEFAULT 'N',
  `RepeatNumber` INT(11) NOT NULL DEFAULT '0',
  `Datetime` DATETIME DEFAULT NULL,
  `ReportName` VARCHAR(255) DEFAULT '',
  `Status` CHAR(1) DEFAULT 'Q',
  `ReportType` VARCHAR(5) DEFAULT '',
  PRIMARY KEY  (`SchedulingId`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;





