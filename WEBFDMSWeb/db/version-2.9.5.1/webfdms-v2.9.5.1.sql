
-- Added by CJongs 09/23/08.
update apvendors,apaccounts
set  apvendors.DefaultAcct = apaccounts.AccountID
where apvendors.DefaultAcct = apaccounts.AccountNumber;

update apvendorlocations,apaccounts
set  apvendorlocations.DefaultAcct = apaccounts.AccountID
where apvendorlocations.DefaultAcct = apaccounts.AccountNumber;

alter table apvendors change column DefaultAcct DefaultAcctID int(11);
alter table apvendorlocations change column defaultAcct defaultAcctID int(11);

update apvendors set deletecode = 'D' where name = '';

-- Added by CJongs 09/24/08
alter table locations modify CashGlAcct varchar(15);
alter table locations modify ArGlAcct varchar(15); 
alter table locations modify FinChrgGlAcct varchar(15);
alter table locations modify ApGlAcct varchar(15);
alter table locations modify DiscountGlAcct varchar(15);

-- Added by CJongs 09/26/08
update formsavailable set exportType = 'XLS' where exportType = 'xls';

-- Added by CJongs 09/26/08
update formsavailable set exportType = "HTML40" where exportType = "HTML"; 
update formsavailable set exportType = "HTML32" where exportType = "HTML_32"; 
update formsavailable set exportType = "HTML32" where ReportName = "mergeovershrink.rpt";

-- Added by CJongs 11/19/08
alter table locales modify column Name varchar(50);

-- Added by CJongs 11/20/08
alter table transactionhistory modify InventoryItemName varchar(39);
alter table charges modify InventoryItemName varchar(39);
alter table invhistory modify ItemName varchar(39);
alter table invhistory modify Description varchar(50);
alter table transactionhistory add column Sequence smallint(6) default 0 after DatetimeOfTrans;
alter table transactionhistory add column Comment varchar(150) default "" after Sequence;

-- Added by CJongs 02/02/09
alter table apvendors modify InternalVendor tinyint(1);
alter table apvendors modify VendorCode varchar(20) not null;

-- Added by CJongs 02/19/09
alter table vitalstats add NPCheckBox smallint(6) after CallInfoNote;
alter table vitalstats add NPDateSigned varchar(8) after NPCheckBox;
alter table vitalstats add NPLicenseNumber varchar(28) after NPDateSigned;

-- Added by CJongs 02/19/09
alter table locales modify PreneedLicense char(1) null default "";
alter table locales modify AtneedLicense char(1) null default "";
alter table locations modify County varchar(30) null default "";

-- Added by CJongs 03/09/09
alter table invoice add column apMasterID int(11) default 0 after invoiceStatus;

-- Added by CJongs 03/09/09
alter table apmaster add column VendorName varchar(150) default "" after Check1099Amount;
-- Run this one then comment out if the version change.
update apmaster, apvendors set apmaster.VendorName = apvendors.Name
where apmaster.VendorID = apvendors.VendorID;

update invoice,apmaster set invoice.apMasterID = apmaster.masterid 
where invoice.apMasterID = 0 and apmaster.InvoiceID = invoice.invoiceid;

update apvendors set Name = replace(Name,'"','');
update apvendors set Name = replace(Name,"'",'');

-- Added by CJongs 03/26/09
alter table vitalstats add column BodyFoundMore24Hr char(1) default '' after NPLicenseNumber;

-- Added by CJongs 03/27/09
insert into formsavailable
(
	Locale, 
	Description, 
	ReportName, 
	Category, 
	SelectionFormula, 
	exportType, 
	MarginLeft, 
	MarginRight, 
	MarginTop, 
	MarginBottom, 
	ChainToReport, 
	UserDefined, 
	Datapull, 
	StoredProc, 
	XmlFile
)
values
(
	0, 
	'DemandCheckReprint', 
	'DemandCheckEdit.rpt', 
	19, 
	'{ApMaster.MasterID}=%recid%', 
	'PDF', 
	0, 
	0, 
	0, 
	0, 
	0, 
	0, 
	'CR', 
	'', 
	''
)

