-- 07/30/10 CJongs
ALTER TABLE usersecurity ADD COLUMN DashboardReport TINYINT(1) DEFAULT 0 AFTER Bank;

-- 08/24/10 CJongs
INSERT INTO companyoptions (NAME,description,defaultValue) VALUES('Turn off automatic assign Contract Number of PreNeed Case','Turn off automatic assign Contract Number of PreNeed Case',0); -- make sure that this is 23.

-- 09/27/10 CJongs
INSERT INTO companyoptions (NAME,description,defaultValue) 
VALUES('SmartFile Member',
'SmartFile Member',0); -- make sure that this is 24.
