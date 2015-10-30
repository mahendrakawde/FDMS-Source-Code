-- CJongs 03/02/10
INSERT INTO configvalues 
	( 
	ConfigID, 
	ConfigCode, 
	VALUE
	)
	VALUES
	( 
	3, 
	'EdenLocation', 
	'eden'
	);
	
-- cjongs 04/01/10
INSERT INTO ums_role 
	( 
	NAME, 
	DESCR, 
	STATUS
	)
	VALUES
	( 
	'Webservie User', 
	'', 
	'1'
	);
	
INSERT INTO ums_role 
	( 
	NAME, 
	DESCR, 
	STATUS
	)
	VALUES
	( 
	'Webservie Admin', 
	'', 
	'1'
	);		
	
			
ALTER TABLE usersecurity ADD COLUMN FdmsNetwork TINYINT(1) DEFAULT 0 AFTER Bank;
ALTER TABLE usersecurity ADD COLUMN FdmsDashboard TINYINT(1) DEFAULT 0 AFTER FdmsNetwork;
ALTER TABLE usersecurity ADD COLUMN FdmsWebservice TINYINT(1) DEFAULT 0 AFTER FdmsDashboard;
ALTER TABLE usersecurity ADD COLUMN FddeWebservice TINYINT(1) DEFAULT 0 AFTER FdmsWebservice;	
	
UPDATE usersecurity SET FdmsNetwork = 1, FdmsDashboard = 1, FdmsWebservice= 0, FddeWebservice = 0;	
	