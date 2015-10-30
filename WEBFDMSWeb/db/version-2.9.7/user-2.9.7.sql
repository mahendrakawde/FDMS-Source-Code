CREATE TABLE `aldorinfo` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Address` VARCHAR(50) DEFAULT '',
  `City` VARCHAR(30) DEFAULT '',
  `State` VARCHAR(30) DEFAULT '',
  `Zip` VARCHAR(5) DEFAULT '',
  `County` VARCHAR(30) DEFAULT '',
  `Phone1` VARCHAR(20) DEFAULT '',
  `Phone2` VARCHAR(20) DEFAULT '',
  `Website` VARCHAR(50) DEFAULT '',
  `Email` VARCHAR(50) DEFAULT '',
  `ManagerName` VARCHAR(50) DEFAULT '',
  `Note` TEXT DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

INSERT INTO companyoptions (NAME,description,defaultValue) VALUES('Turn on accounting interface peoplesoft Dashboard','Turn on accounting interface peoplesoft Dashboard',0); -- make sure that this is 32.

 alter table usersecurity add column easyPayment tinyint(1) not null default 0; -- easypayment option added 

-- Enable/Diable edit operation for Financial & Pricelist
ALTER TABLE usersecurity ADD COLUMN EnableFinancialChange TINYINT(1) DEFAULT 0 AFTER Bank;
--EnableFinancialPricelist