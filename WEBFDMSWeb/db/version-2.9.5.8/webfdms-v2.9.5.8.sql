-- CJongs 07/14/09
ALTER TABLE pmntcomponents TYPE=InnoDB;
ALTER TABLE transactionhistory TYPE=InnoDB;
ALTER TABLE financialdata TYPE=InnoDB;
ALTER TABLE charges TYPE=InnoDB;
ALTER TABLE invhistory TYPE=InnoDB;
ALTER TABLE invonhand TYPE=InnoDB;
ALTER TABLE person TYPE=InnoDB;
ALTER TABLE visitations TYPE=InnoDB;

-- cjongs
alter table apmaster modify Memo varchar(120) default '';
alter table apdistributionhistory modify memo varchar(120) default '';

-- cjongs add 08/06/09
alter table vitalstats add column NextKinMiddleName varchar(30) default '' after LocDeathLicenseNumber;

-- cjongs add 08/07/09

update survivors set Living = 'Y' where (Living = '' or Living is null or length(Living) = 0);
alter table survivors modify Living char(1) default 'Y';
