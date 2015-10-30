CREATE TABLE presentation_theme (
	ThemeId INT(11) AUTO_INCREMENT NOT NULL,
	ThemeDesc VARCHAR(255),
	PRIMARY KEY (`ThemeId`)
);

CREATE TABLE presentation_images (
	ImageId INT(11) AUTO_INCREMENT NOT NULL,
	ImageName VARCHAR(255),
	ImagePath VARCHAR(255),
	ImageNumber INT(11),
	ThemeId INT(11),
	PRIMARY KEY(`ImageId`),
	FOREIGN KEY (ThemeId) REFERENCES presentation_theme(ThemeId)
		ON DELETE CASCADE
);

CREATE TABLE eregister_background_themes (
	ThemeId INT(11) NOT NULL AUTO_INCREMENT,
	ThemeDesc VARCHAR(255),
	PRIMARY KEY(`ThemeId`)
);

CREATE TABLE service_screen_themes (
	ThemeId INT(11) NOT NULL AUTO_INCREMENT,
	ThemeDesc VARCHAR(255),
	PRIMARY KEY(`ThemeId`)
);
