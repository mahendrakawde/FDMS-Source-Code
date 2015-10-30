-- cjongs

CREATE TABLE `smsscheduling` (
  `SchedulingID` int(11) NOT NULL AUTO_INCREMENT,
  `LocaleId` int(11) NOT NULL DEFAULT '0',
  `LocationId` int(11) NOT NULL DEFAULT '0',
  `FromDate` date DEFAULT NULL,
  `ToDate` date DEFAULT NULL,
  `RunDate` date DEFAULT NULL,
  `RunTimeHH` int(11) DEFAULT '0',
  `RunTimeMM` int(11) DEFAULT '0',
  `RepeatType` char(1) NOT NULL DEFAULT 'N',
  `RepeatNumber` int(11) NOT NULL DEFAULT '0',
  `Datetime` datetime DEFAULT NULL,
  `StartDateTime` datetime DEFAULT NULL,
  `StopDateTime` datetime DEFAULT NULL,
  `CountryCode` varchar(1) DEFAULT '',
  `AreaCode` varchar(3) DEFAULT '',
  `Phone` varchar(7) DEFAULT '',
  `SQLScript` varchar(1000) DEFAULT '',
  `StartMessage` varchar(255) DEFAULT '',
  `EndMessage` varchar(255) DEFAULT '',
  `Status` char(1) DEFAULT 'Q',
  `RunType` varchar(5) DEFAULT '',
  `UserId` int(11) DEFAULT '0',
  `Firstname` varchar(40) DEFAULT '',
  `Lastname` varchar(40) DEFAULT '',
  `SentMessage` varchar(255) DEFAULT '',
  `CompanyId` int(11) DEFAULT '0',
  `DataURL` varchar(128) DEFAULT '',
  `dbLookup` varchar(90) DEFAULT '',
  `CompanyName` varchar(70) DEFAULT '',
  `SMSSqlId` int(11) DEFAULT '0',
  `SendType` char(3) DEFAULT '',
  PRIMARY KEY (`SchedulingID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Table structure for table `smssstoreproc` */

CREATE TABLE `smssstoreproc` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL DEFAULT '',
  `SqlScript` varchar(1000) NOT NULL DEFAULT '',
  `Comment` varchar(100) DEFAULT '',
  `OutP1` varchar(255) DEFAULT '',
  `InP1` varchar(50) DEFAULT '',
  `InP2` varchar(50) DEFAULT '',
  `InP3` varchar(50) DEFAULT '',
  `InP4` varchar(50) DEFAULT '',
  `InP5` varchar(50) DEFAULT '',
  `InP6` varchar(50) DEFAULT '',
  `InP7` varchar(50) DEFAULT '',
  `DeleteCode` varchar(1) DEFAULT '',
  `Type` varchar(1) DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
