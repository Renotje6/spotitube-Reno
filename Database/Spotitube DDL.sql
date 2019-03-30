-- DDL script
DROP DATABASE spotitube;
CREATE DATABASE IF NOT EXISTS spotitube;
USE spotitube;

CREATE TABLE IF NOT EXISTS userAccounts (
 `username` VARCHAR(255) NOT NULL,
 `password` VARCHAR(255) NOT NULL,
 PRIMARY KEY (`username`)
 );
 
 CREATE TABLE IF NOT EXISTS accountTokens (
 `token` VARCHAR(255) NOT NULL,
 `username` VARCHAR(255) NOT NULL,
 `expiryDate` VARCHAR(255) NOT NULL,
 PRIMARY KEY (`username`, `token`),
 CONSTRAINT `fk_username_userAccounts` FOREIGN KEY (`username`) 
	REFERENCES `userAccounts` (`username`) 
	ON DELETE CASCADE 
    ON UPDATE CASCADE
 );
 
 CREATE TABLE IF NOT EXISTS playlist (
 `playlistId` INTEGER AUTO_INCREMENT,
 `name` VARCHAR(255) NOT NULL,
 `username` VARCHAR(255) NOT NULL,
 PRIMARY KEY(`playlistId`, `username`),
 CONSTRAINT `fk_owner_userAccounts_username` FOREIGN KEY (`username`)
	REFERENCES `userAccounts` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
 );
 
 CREATE TABLE IF NOT EXISTS tracks (
 `trackId` INTEGER AUTO_INCREMENT,
 `title` VARCHAR(255) NOT NULL,
 `performer` VARCHAR(255) NOT NULL,
 `duration` INTEGER NOT NULL,
 `playcount` INTEGER NULL,
 `url` VARCHAR(255) NOT NULL,
 PRIMARY KEY(`trackId`)
 );
 
 CREATE TABLE IF NOT EXISTS songs (
 `trackId` INTEGER AUTO_INCREMENT,
 `album` VARCHAR(255) NULL,
 PRIMARY KEY(`trackId`),
 CONSTRAINT `fk_song_id_tracks_id` FOREIGN KEY (`trackId`)
	REFERENCES `tracks` (`trackId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS videos (
`trackId` INTEGER AUTO_INCREMENT,
`publicationDate` VARCHAR(255) NULL,
`description` VARCHAR(255) NULL,
PRIMARY KEY(`trackId`),
CONSTRAINT `fk_video_id_tracks_id` FOREIGN KEY (`trackId`)
	REFERENCES `tracks` (`trackId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tracks_in_playlist (
`playlistId` INTEGER NOT NULL,
`trackId` INTEGER NOT NULL,
`offlineAvailable` BOOLEAN NOT NULL,
PRIMARY KEY(`playlistId`, `trackId`),
CONSTRAINT `fk_tracksInPlaylist_id_playlist_id` FOREIGN KEY (`playlistId`)
	REFERENCES `playlist` (`playlistId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT `fk_tracksInPlaylist_id_tracks_id` FOREIGN KEY (`trackId`)
	REFERENCES `tracks` (`trackId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE VIEW songs_view AS
SELECT tracks.trackId,
	   tracks.title, 
       tracks.performer, 
       tracks.duration, 
       tracks.playcount, 
       tracks.url,
       songs.album
FROM tracks
INNER JOIN songs ON tracks.trackId = songs.trackId;

CREATE VIEW videos_view AS
SELECT tracks.trackId,
	   tracks.title, 
       tracks.performer, 
       tracks.duration, 
       tracks.playcount, 
       tracks.url,
       videos.publicationDate,
       videos.description
FROM tracks
INNER JOIN videos ON tracks.trackId = videos.trackId;

INSERT INTO userAccounts (`username`, `password`) VALUES('reno', 'rovers');

INSERT INTO playlist (`name`, `username`)
VALUES ('study playlist', 'reno'),
       ('chill hits', 'reno'),
       ('All Videos', 'reno');

INSERT INTO tracks (`title`, `performer`, `duration`, `playcount`, `url`)
VALUES  ('My Fight', 'From Ashes to New', 202, 1, 'https://www.youtube.com/watch?v=K0a9a8PUPrA'),
        ('Basket Case', 'Green Day', 176, 1,'https://www.youtube.com/watch?v=GTwJo0HeNmU'),
        ('Are You Ready?', 'Disturbed', 268, 1, 'https://www.youtube.com/watch?v=55NJzOSuKuY'),
        ('I`m Yours', 'Jason Mraz', 247, 1, 'https://www.youtube.com/watch?v=RILP53OR63k'),
        ('Hey, Soul Sister', 'Train', 217, 1, 'https://www.youtube.com/watch?v=kVpv8-5XWOI'),
        ('Sweet Home Alabama', 'Lynyrd Skynyrd', 300, 1, 'https://www.youtube.com/watch?v=ye5BuYf8q4o'),
        ('How To Cook The Perfect Pasta', 'Gordon Ramsay', 91, 1, 'https://www.youtube.com/watch?v=UYhKDweME3A'),
        ('Boeing 747 Cockpit View - Take-Off from Miami Intl. (MIA)', 'The Pilot Channel', 776, 1, 'https://www.youtube.com/watch?v=DWqwzCTIW3Q'),
        ('How to Teach your Puppy to Sit and Stay', 'Zak George’s Dog Training Revolution', 441, 1, 'https://www.youtube.com/watch?v=DPNz6reMVXY');


INSERT INTO tracks_in_playlist (`playlistId`, `trackId`, `offlineAvailable`)
VALUES	(1, 1, FALSE),
        (1, 2, FALSE),
        (1, 3, TRUE),
        (2, 4, TRUE),
        (2, 5, TRUE),
        (2, 6, FALSE),
        (3, 7, FALSE),
        (3, 8, FALSE),
        (3, 9, TRUE);

INSERT INTO songs (`trackId`, `album`)
VALUES	(1, 'Rock'),
        (2, 'Rock'),
        (3, 'Rock'),
        (4, 'We sing, we dance, we steal things'),
        (5, 'Hey, Soul Sister'),
        (6, 'Sweet Home Alabma');

INSERT INTO videos(`trackId`, `publicationDate`, `description`)
VALUES (7, '09-01-2014', 'Top tips on how to how to cook angel hair pasta - with principles that you can apply to cooking any shape. If you have any others, let us know - always keen to learn.'),
       (8, '16-12-2016', 'This video shows the full start-up, take-off RW09 and manually flown departure from Miami (KMIA/MIA). Filmed from a KLM Martinair Boeing 747-400ERF. With about 9 hour to go the flight was heading to Amsterdam.'),
       (9, '19-07-2017', 'How to Train your Puppy to Sit and Stay! This video is sponsored by Petflow!');