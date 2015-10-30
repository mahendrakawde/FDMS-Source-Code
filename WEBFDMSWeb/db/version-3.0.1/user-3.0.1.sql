use webfdmsusers_keystone;

-- Create Location Options  Table for Location wise options.

CREATE TABLE `webfdmsusers_keystone`.`locationoptions` (
  `locationOptionID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `defaultValue` int(11) DEFAULT NULL,
  PRIMARY KEY (`locationOptionID`)
);



-- Create Location Option value Table for Location wise options values. 

CREATE TABLE `webfdmsusers_keystone`.`locationoptionvalues` (
  `locationOptionValueID` int(11) NOT NULL AUTO_INCREMENT,
  `locationOptionValue` int(11) NOT NULL,
  `companyID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `locationID` int(11) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  PRIMARY KEY (`locationOptionValueID`)
);

-- Added Ergister Book Option for Location access Ticket # 5594	User Access to Eregister book
insert  into `webfdmsusers_keystone`.`locationoptions`(`locationOptionID`,`name`,`description`,`defaultValue`) values (1,'E-Register Book','E-Register Book',0);

-- Added Import Father- Mother information from vitals to Relative page
insert  into `webfdmsusers_keystone`.`locationoptions`(`locationOptionID`,`name`,`description`,`defaultValue`) values (2,'Show Father/Mother info on Relative Page','Show Father/Mother info on Relative Page',0);

-- Added Ergister Book Option for User access Ticket # 5594	User Access to Eregister book 
ALTER TABLE `webfdmsusers_keystone`.`usersecurity` ADD COLUMN `eregisterbook` TINYINT(1) DEFAULT 0 NOT NULL; 

-- Added Uppercase Code Number #5578 Upper Case Case Number 
INSERT INTO `webfdmsusers_keystone`.`companyoptions`(`companyOptionID`,`name`,`description`,`defaultValue`) VALUES ( NULL,'Turn on Uppercase Case number','Turn on Uppercase Case number inputs','0'); -- It must be 35