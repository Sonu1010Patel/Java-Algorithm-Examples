CREATE DATABASE Example;

USE Example;

CREATE TABLE sizes (
	height	INT,
    weight	INT,
    size	VARCHAR(3)
);

INSERT INTO sizes (height, weight, size)
VALUES (158,	58,		"M"),
       (158,	59,		"M"),
	   (158,	63,		"M"),
	   (160,	59,		"M"),
	   (160,	60,		"M"),
       (163,	60,		"M"),
	   (163,	61,		"M"),
	   (160,	64,		"L"),
	   (163,	64,		"L"),
	   (165,	61,		"L"),
	   (165,	62,		"L"),
	   (165,	65,		"L"),
	   (168,	62,		"L"),
	   (168,	63,		"L"),
	   (168,	66,		"L"),
	   (170,	63,		"L"),
	   (170,	64,		"L"),
	   (170,	68,		"L");

SELECT * FROM sizes;