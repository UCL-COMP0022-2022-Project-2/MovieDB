DROP DATABASE IF EXISTS moviedb;
CREATE DATABASE IF NOT EXISTS moviedb;
USE moviedb;
CREATE TABLE movies
(
	movieId INT NOT NULL,
	title VARCHAR(1000) NOT NULL,
	genres VARCHAR(1000) NULL,
	year SMALLINT NOT NULL,
	CONSTRAINT movies_pk PRIMARY KEY (movieId)
);

CREATE TABLE links
(
	movieId INT NOT NULL,
	imdbId INT NOT NULL,
	tmdbId INT NOT NULL,
	CONSTRAINT links_pk PRIMARY KEY (movieId),
	CONSTRAINT links_movies_movieId_fk FOREIGN KEY (movieId) REFERENCES movies (movieId)
);

CREATE UNIQUE INDEX links_imdbId_uindex ON links (imdbId);

CREATE TABLE ratings
(
	userId INT NOT NULL,
	movieId INT NOT NULL,
	rating FLOAT(2,1) NOT NULL,
	timestamp TIMESTAMP NOT NULL,
	CONSTRAINT ratings_movies_movieId_fk FOREIGN KEY (movieId) REFERENCES movies (movieId)
);

CREATE TABLE tags
(
	userId INT NOT NULL,
	movieId INT NOT NULL,
	tag TEXT NOT NULL,
	timestamp TIMESTAMP NOT NULL,
	CONSTRAINT tags_movies_movieId_fk FOREIGN KEY (movieId) REFERENCES movies (movieId)
);

