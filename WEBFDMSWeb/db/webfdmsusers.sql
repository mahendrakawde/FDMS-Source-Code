--
-- Current Database: webfdmsusers
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ webfdmsusers;

USE webfdmsusers;

--
-- Table structure for table `admin`
--

CREATE TABLE admin (
  admin_id int(12) NOT NULL auto_increment,
  username varchar(20) default NULL,
  password varchar(20) default NULL,
  PRIMARY KEY  (admin_id),
  UNIQUE KEY username (username)
) TYPE=MyISAM;

--
-- Table structure for table `alert`
--

CREATE TABLE alert (
  message_type char(1) default 'M',
  message text,
  viewable char(1) default 'N',
  seconds int(6) default '0'
) TYPE=MyISAM;

--
-- Table structure for table `userlocations`
--

CREATE TABLE userlocations (
  user_location_id int(12) NOT NULL auto_increment,
  user_id int(12) default '0',
  location_Id int(12) default '0',
  default_location char(1) default NULL,
  PRIMARY KEY  (user_location_id)
) TYPE=MyISAM;

--
-- Table structure for table `userlog`
--

CREATE TABLE userlog (
  userlog_id int(12) NOT NULL auto_increment,
  username varchar(40) default NULL,
  user_id int(12) unsigned zerofill default '000000000000',
  region_id int(10) default '0',
  date_logged_in datetime default NULL,
  ip varchar(25) default NULL,
  browser varchar(40) default NULL,
  user_agent varchar(100) default NULL,
  valid char(1) default NULL,
  UNIQUE KEY userlog_id (userlog_id)
) TYPE=MyISAM;

--
-- Table structure for table `usersecurity`
--

CREATE TABLE usersecurity (
  Name varchar(40) default NULL,
  Password varchar(30) default NULL,
  UserID int(11) NOT NULL auto_increment,
  Region int(11) default NULL,
  Administrator smallint(6) default NULL,
  Atneed smallint(6) default NULL,
  Preneed smallint(6) default NULL,
  Financial smallint(6) default NULL,
  Payments smallint(6) default NULL,
  AcctsRec smallint(6) default NULL,
  Forms smallint(6) default NULL,
  Reports smallint(6) default NULL,
  DeleteCases smallint(6) default NULL,
  Initials char(3) default NULL,
  Inventory smallint(6) default NULL,
  ViewOnly smallint(6) default NULL,
  DataUrl varchar(128) default NULL,
  DeleteCode char(1) default NULL,
  FirstName varchar(20) default NULL,
  LastName varchar(20) default NULL,
  EmailAddr varchar(50) default NULL,
  sqlUser varchar(20) NOT NULL default '',
  sqlPassword varchar(20) NOT NULL default '',
  caseListSortOrder varchar(30) default 'DeathDateKey',
  caseListPerScreen int(11) default '10',
  PRIMARY KEY  (UserID),
  UNIQUE KEY Name (Name)
) TYPE=InnoDB;