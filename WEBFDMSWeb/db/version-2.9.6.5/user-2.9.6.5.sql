-- 09/27/10 CJongs
INSERT INTO companyoptions (NAME,description,defaultValue) VALUES('FuneralSync','FuneralSync',0); -- make sure that this is 25.


INSERT INTO configvalues 
	( 	ConfigID,	ConfigCode,	VALUE)
	VALUES
	(	1,	'db.funeralsync.jndi',	'java:jdbc/FDMS_Funeralsync');
	
INSERT INTO configvalues 
	( 	ConfigID,	ConfigCode,	VALUE)
	VALUES
	(	2,	'db.funeralsync.jndi',	'java:jdbc/FDMS_Funeralsync');
	
INSERT INTO configvalues 
	( 	ConfigID,	ConfigCode,	VALUE)
	VALUES
	(	3,	'db.funeralsync.jndi',	'java:jdbc/FDMS_Funeralsync');
	
INSERT INTO configvalues 
	( 	ConfigID,	ConfigCode,	VALUE)
	VALUES
	(	5,	'db.funeralsync.jndi',	'java:jdbc/FDMS_Funeralsync');		
	
-- 02/25/08 cjongs
INSERT INTO companyoptions (NAME,description,defaultValue) VALUES('SMS','SMS',0); -- make sure that this is 26.
INSERT INTO companyoptions (NAME,description,defaultValue) VALUES('Text to Speech','Text to Speech',0); -- make sure that this is 27.

----------------------------- // run to this much	
INSERT INTO companyoptions (NAME,description,defaultValue) VALUES('E Register Book','E Register Book',0); -- make sure that this is 28.

INSERT INTO companyoptions (NAME,description,defaultValue) VALUES('Reset Service Type of Atneed from Preneed Case','Reset Service Type of Atneed from Preneed Case',0); -- make sure that this is 29.

INSERT INTO companyoptions (NAME,description,defaultValue) VALUES('Turn off financial description change','Turn off financial description change',0); -- make sure that this is 30.
INSERT INTO companyoptions (NAME,description,defaultValue) VALUES('Turn off financial price change','Turn off financial price change',0); -- make sure that this is 31.


