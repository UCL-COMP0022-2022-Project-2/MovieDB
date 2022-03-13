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

create table personality
(
    userId              VARCHAR(200) not null,
    openness            FLOAT(2, 1)   not null,
    agreeableness       FLOAT(2, 1)   not null,
    emotional_stability FLOAT(2, 1)   not null,
    conscientiousness   FLOAT(2, 1)   not null,
    extraversion        FLOAT(2, 1)   not null,
    assigned_metric     VARCHAR(100)  not null,
    assigned_condition  VARCHAR(100)  not null,
    movie_1             int           not null,
    predicted_rating_1  float         not null,
    movie_2             int           not null,
    predicted_rating_2  float         not null,
    movie_3             int           not null,
    predicted_rating_3  float         not null,
    movie_4             int           not null,
    predicted_rating_4  FLOAT         not null,
    movie_5             int           not null,
    predicted_rating_5  FLOAT         not null,
    movie_6             int           not null,
    predicted_rating_6  FLOAT         not null,
    movie_7             int           not null,
    predicted_rating_7  FLOAT         not null,
    movie_8             int           not null,
    predicted_rating_8  FLOAT         not null,
    movie_9             int           not null,
    predicted_rating_9  FLOAT         not null,
    movie_10            int           not null,
    predicted_rating_10 FLOAT         not null,
    movie_11            int           not null,
    predicted_rating_11 FLOAT         not null,
    movie_12            int           not null,
    predicted_rating_12 FLOAT         not null,
    is_personalized     int           not null,
    enjoy_watching      int           not null,
    constraint personality_pk primary key (userId)
);

create table personality_rating
(
    userId   char(200) NOT NULL,
    movie_id int NOT NULL,
    rating FLOAT(2, 1) NOT NULL,
    datetime TIMESTAMP,
    CONSTRAINT personality_rating_userId_fk FOREIGN KEY (userId) REFERENCES personality (userId)
);

create table genre_personality
(
    genre               char(200) NOT NULL,
    openness            FLOAT(2, 1)   not null,
    agreeableness       FLOAT(2, 1)   not null,
    emotional_stability FLOAT(2, 1)   not null,
    conscientiousness   FLOAT(2, 1)   not null,
    extraversion        FLOAT(2, 1)   not null,
    CONSTRAINT genre_personality_fk primary key  (genre)
);

