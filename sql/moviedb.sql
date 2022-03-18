DROP DATABASE IF EXISTS moviedb;
CREATE DATABASE IF NOT EXISTS moviedb;
USE moviedb;
create table genre_personality
(
    genre               char(200)   not null
        primary key,
    openness            float(2, 1) not null,
    agreeableness       float(2, 1) not null,
    emotional_stability float(2, 1) not null,
    conscientiousness   float(2, 1) not null,
    extraversion        float(2, 1) not null
);

create table movies
(
    movieId int           not null
        primary key,
    title   varchar(1000) not null,
    genres  varchar(1000) null,
    year    smallint      not null
);

create table links
(
    movieId int not null
        primary key,
    imdbId  int not null,
    tmdbId  int not null,
    constraint links_imdbId_uindex
        unique (imdbId),
    constraint links_movies_movieId_fk
        foreign key (movieId) references movies (movieId)
);

create table personality
(
    userId              varchar(200) not null
        primary key,
    openness            float(2, 1)  not null,
    agreeableness       float(2, 1)  not null,
    emotional_stability float(2, 1)  not null,
    conscientiousness   float(2, 1)  not null,
    extraversion        float(2, 1)  not null
#     assigned_metric     varchar(100) not null,
#     assigned_condition  varchar(100) not null,
#     movie_1             int          not null,
#     predicted_rating_1  float        not null,
#     movie_2             int          not null,
#     predicted_rating_2  float        not null,
#     movie_3             int          not null,
#     predicted_rating_3  float        not null,
#     movie_4             int          not null,
#     predicted_rating_4  float        not null,
#     movie_5             int          not null,
#     predicted_rating_5  float        not null,
#     movie_6             int          not null,
#     predicted_rating_6  float        not null,
#     movie_7             int          not null,
#     predicted_rating_7  float        not null,
#     movie_8             int          not null,
#     predicted_rating_8  float        not null,
#     movie_9             int          not null,
#     predicted_rating_9  float        not null,
#     movie_10            int          not null,
#     predicted_rating_10 float        not null,
#     movie_11            int          not null,
#     predicted_rating_11 float        not null,
#     movie_12            int          not null,
#     predicted_rating_12 float        not null,
#     is_personalized     int          not null,
#     enjoy_watching      int          not null
);

create table personality_rating
(
    userId   char(200)   not null,
    movieId  int         not null,
    rating   float(2, 1) not null,
#     datetime timestamp   null,
    constraint personality_rating_userId_fk
        foreign key (userId) references personality (userId)
);

create table predicted_rating
(
    movieId int unsigned not null
        primary key,
    rating  double(3, 2) null
);

create table ratings
(
    userId    int         not null,
    movieId   int         not null,
    rating    float(2, 1) not null,
    timestamp timestamp   not null,
    constraint ratings_movies_movieId_fk
        foreign key (movieId) references movies (movieId)
);

create table tag_personality
(
    tag                 varchar(100) null,
    openness            double(3, 2) null,
    agreeableness       double(3, 2) null,
    emotional_stability double(3, 2) null,
    conscientiousness   double(3, 2) null,
    extraversion        double(3, 2) null
);

create table tags
(
    userId    int       not null,
    movieId   int       not null,
    tag       text      not null,
    timestamp timestamp not null,
    constraint tags_movies_movieId_fk
        foreign key (movieId) references movies (movieId)
);

alter table predicted_rating MODIFY movieId INT;
alter table predicted_rating add CONSTRAINT foreign key (movieId) references movies(movieId);
alter table tag_personality add CONSTRAINT primary key (tag);