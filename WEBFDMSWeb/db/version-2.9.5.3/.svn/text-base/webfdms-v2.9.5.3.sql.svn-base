-- CJongs 04/03/09
alter table  visitations modify column State varchar(35) default '';

alter table vitalstats add column DateOfNotifyToDirector varchar(8) default '' after BodyFoundMore24Hr;
alter table vitalstats add column TimeOfNotifyToDirectory varchar(10) default '' after DateOfNotifyToDirector;
alter table vitalstats add column HospiceStatus char(1) default '' after TimeOfNotifyToDirectory;
alter table vitalstats add column NotificationOfExaminerRequired char(1) default '' after HospiceStatus;

alter table vitalstats modify ChapelLocation varchar(50);


-- CJongs 04/15/09
alter table vitalstats modify DispositionCode varchar(30);
alter table vitalstats add column DispositionPlaceType varchar(20) default '' after NotificationOfExaminerRequired;

-- CJongs 04/27/09
alter table  vitalstats modify column TimeAtResidence varchar(15) default '';

-- CJongs 04/29/09
alter table  apvendors modify column AccountNumber varchar(20) default '';
alter table apvendors add index VendorCode(VendorCode);
alter table apvendors add index Name(Name);

-- CJongs 05/06/09
alter table veteransinfo add column FlagDocShowEligibility char(1) default '' after FlagBurialZipCode;

-- CJongs 05/13/09
CREATE TABLE `emaillog` (
  `logid` int(11) NOT NULL auto_increment,
  `starttime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `stoptime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `executetime` int(11) default NULL COMMENT 'milliseconds',
  `emailTo` varchar(150) default NULL,
  `emailCC` varchar(150) default NULL,
  `emailBCC` varchar(150) default NULL,
  `emailSubject` varchar(150) default NULL,
  `emailBody` text default NULL,
  `className` varchar(150) default NULL,
  PRIMARY KEY  (`logid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- CJongs 05/20/09
update speeddatarule set Col7 = 'License Number' where TabCategory = 'LOCDEATH';
alter table vitalstats add column LocDeathLicenseNumber varchar(30) default '' after DispositionPlaceType;
update speeddata set TabData = concat(TabData,',') where TabCategory = 'LOCDEATH';

ALTER TABLE apmaster TYPE=InnoDB;
ALTER TABLE apvendors TYPE=InnoDB;
ALTER TABLE invoice TYPE=InnoDB;
ALTER TABLE pricelist TYPE=InnoDB;
ALTER TABLE invmaster TYPE=InnoDB;
ALTER TABLE apvendorlocations TYPE=InnoDB;
ALTER TABLE vitalstats TYPE=InnoDB;
ALTER TABLE servicedata TYPE=InnoDB;
ALTER TABLE survivors TYPE=InnoDB;
ALTER TABLE pricelist TYPE=InnoDB;
ALTER TABLE invmaster TYPE=InnoDB;
ALTER TABLE locales TYPE=InnoDB;
ALTER TABLE locations TYPE=InnoDB;
ALTER TABLE locationemail TYPE=InnoDB;
ALTER TABLE invchapelindex TYPE=InnoDB;
ALTER TABLE formsavailable TYPE=InnoDB;
ALTER TABLE apaccounts TYPE=InnoDB;
ALTER TABLE arrangers TYPE=InnoDB;
ALTER TABLE billtoparties TYPE=InnoDB;
ALTER TABLE veteransinfo TYPE=InnoDB;
ALTER TABLE packagepricelist TYPE=InnoDB;
ALTER TABLE glacctdefault TYPE=InnoDB;
ALTER TABLE glaccttranslation TYPE=InnoDB;
ALTER TABLE combodata TYPE=InnoDB;

ALTER TABLE invoiceitems TYPE=InnoDB;
ALTER TABLE invoicetranshist TYPE=InnoDB;
ALTER TABLE apdistributionhistory TYPE=InnoDB;
ALTER TABLE apdistribution TYPE=InnoDB;

-- cjongs add 06/24/09
insert into localeconfigtypes 
	(localeConfigTypeID, 
	name, 
	description, 
	defaultValue
	)
	values
	(10, 
	'Order by Cont. Line on Financial Page', 
	'Order by Cont. Line on Financial Page', 
	0
	);

-- cjongs add 06/29/09
alter table invhistory add column Reason varchar(10) default '' after Identity;

/* This one is only for vertin.
update invhistory set Reason = Description 
where TransactionType = "D" 
and Description in ("R","S","T","r","s","t");

update invhistory set Reason = "E" 
where Reason = "R" or Reason = "r";
*/ 