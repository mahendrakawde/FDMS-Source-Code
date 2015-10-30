CREATE TABLE uploaded_files (
	FileId INT(11) AUTO_INCREMENT NOT NULL,
	CaseId INT(11),
	CompanyId INT(11),
	ParentId INT(11),
	FileName VARCHAR(255),
	FilePath VARCHAR(255),
	FileType VARCHAR(255),
	ParentTableName VARCHAR(255),
	PRIMARY KEY(`FileId`)
);

/*CREATE TABLE imagemapping (
	ImageId INT(11) AUTO_INCREMENT NOT NULL,
	CaseId INT(11),
	CompanyId INT(11),
	PresentationImageId INT(11),
	ImageCategory VARCHAR(255),
	PRIMARY KEY (`ImageId`)
)*/

CREATE TABLE eregister_image_mapping (
	MappingId INT(11) AUTO_INCREMENT NOT NULL,
	CaseId INT(11),
	CompanyId INT(11),
	ImageId INT(11),
	IsUploadedImage BOOLEAN,
	ImageOrder INT(11),
	ImageBelongsTo VARCHAR(255),
	PRIMARY KEY(`MappingId`)
);

ALTER TABLE eregister ADD COLUMN ServiceMessage TEXT AFTER RegBookHeader;
ALTER TABLE eregister ADD COLUMN QrCodePath VARCHAR(255);
ALTER TABLE eregister ADD COLUMN (DateOfBirth VARCHAR(200), DateOfDeath VARCHAR(200));
ALTER TABLE eregister ADD COLUMN (BackgroundThemeId INT(11), ServiceScreenThemeId INT(11));
ALTER TABLE eregister ADD COLUMN ArrangerId INT(11) AFTER CaseId;
ALTER TABLE eregister ADD COLUMN ObitLink TEXT AFTER TargetWebPage;
