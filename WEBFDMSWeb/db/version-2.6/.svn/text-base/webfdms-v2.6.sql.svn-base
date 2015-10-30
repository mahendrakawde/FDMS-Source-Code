ALTER TABLE vitalstats ADD DecAddrSameAsInf CHAR(1);
ALTER TABLE vitalstats ADD Active TINYINT(1) DEFAULT 1;
ALTER TABLE vitalstats CHANGE ContractCode ContractCode VARCHAR(20);


CREATE INDEX ActiveIndex ON vitalstats (Active);

-- line(s) below added by Guadalupe Garica on 09/22/2005
CREATE TABLE externalappconfig (
	ExternalAppId			INT(3)	NOT NULL DEFAULT '0',
	ExternalAppConfigId		INT(12)	NOT NULL DEFAULT '0',
	UNIQUE(ExternalAppId)
);

CREATE TABLE vitalsexternalapp_xref (
	VitalsMasterKey 	INT(12)		NOT NULL DEFAULT '0',
	ExternalAppId		INT(3)		NOT NULL DEFAULT '0',
	ExternalVitalsId	INT(12)		NOT NULL DEFAULT '0'
);

-- line(s) below added by Guadalupe Garica on 09/23/2005
ALTER TABLE vitalstats ADD COLUMN FatherAge tinyint DEFAULT '0';
ALTER TABLE vitalstats ADD COLUMN MotherAge tinyint DEFAULT '0';
ALTER TABLE vitalstats ADD COLUMN DoctorOccupation VARCHAR(30);

-- line(s) below added by Kiersten Heyl on 09/29/2005
ALTER TABLE locations ADD Website VARCHAR(200);
ALTER TABLE locations ADD Email VARCHAR(100);

alter table `survivors` ,
add column `MiddleName` varchar (49)   NULL  after `FirstName`, 
add column `Salutation` varchar (5)   NULL  after `MiddleName`, 
add column `Suffix` varchar (5)   NULL  after `Salutation`, 
add column `MaidenName` varchar (49)   NULL  after `Suffix`, 
add column `DisplayName` varchar (250)   NULL  after `MaidenName`, 
add column `Address2` varchar (39)   NULL  after `Address`, 
add column `Phone2` varchar (19)   NULL  after `Phone`, 
add column `Email` varchar (100)   NULL  after `Phone2`, 
add column `Living` char (1)   NULL  after `Email`, 
add column `PNLead` char (1)   NULL  after `Living`;
