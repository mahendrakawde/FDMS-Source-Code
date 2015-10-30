
-- cjongs add 07/13/09
insert into configvalues 	(	ConfigID,	ConfigCode,	Value	)	values	(	'9',	'RR.CrystalServer.Dashboard.ReportGenerator.IP.1',	'keyrpt1.aldorsolutions.com'	); 
insert into configvalues 	(	ConfigID,	ConfigCode,	Value )	values	(	'9',	'RR.CrystalServer.Dashboard.ReportGenerator.IP.2',	'keyrpt2.aldorsolutions.com'	);
insert into configvalues 	(	ConfigID,	ConfigCode,	Value )	values	(	'9',	'CrystalServer.Dashboard.dsn',	'DashboardDSN'	);
insert into configvalues 	(	ConfigID,	ConfigCode,	Value )	values	(	'9',	'CrystalServer.Dashboard.Timeout',	'1200000'	);

insert into companyoptions (name, description, defaultValue)values('Dashboard AP Module', 'Dashboard AP Module', '0');  -- make sure that this one id is 16
insert into companyoptions (name, description, defaultValue)values('Dashboard Report Module', 'Dashboard Report Module', '0'); -- make sure that this one id is 17

-- remember that locale = 0 and category = 18 for now
-- cjongs add 07/30/09
insert into companyoptions (name, description, defaultValue)values('Dashboard Report Scheduling Module', 'Dashboard Report Scheduling Module', '0'); -- make sure that this one id is 18
-- the report need to set locale = 0 and catagory group is 18.

-- cjongs 10/20/09
INSERT INTO companyoptions (NAME,description,defaultValue) VALUES('At-Need Contract Number Required','At-Need Contract Number Required',0); -- make sure that this is 19.
	
-- CJongs 09/01/09
alter table usersecurity add column Bank tinyint(1) default 0 after AdjustFinancial;

-- CJongs 11/12/09
INSERT INTO companyoptions (NAME,description,defaultValue) VALUES('Turn off automatic assign Contract Number','Turn off automatic assign Contract Number',0); -- make sure that this is 20.

-- CJongs 11/18/09
alter table usersecurity add column FdmsAdmin tinyint(1) default 0 after userLocked;

	