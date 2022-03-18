DROP DATABASE IF EXISTS moviedb;
CREATE DATABASE IF NOT EXISTS moviedb;
USE moviedb;
create table genre_personality
(
    genre               varchar(200) not null
        primary key,
    openness            float(2, 1)  not null,
    agreeableness       float(2, 1)  not null,
    emotional_stability float(2, 1)  not null,
    conscientiousness   float(2, 1)  not null,
    extraversion        float(2, 1)  not null
);

create table movies
(
    movieId int           not null
        primary key,
    title   varchar(1000) not null,
    genres  varchar(200)  null,
    year    smallint      not null
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
);

create table personality_rating
(
    userId  varchar(200) not null,
    movieId int          not null,
    rating  float(2, 1)  not null,
    constraint personality_rating_userId_fk
        foreign key (userId) references personality (userId)
);

create index pr_movieId_index
    on personality_rating (movieId);

create index pr_userId_index
    on personality_rating (userId);

create table predicted_rating
(
    movieId int          not null
        primary key,
    rating  double(3, 2) null,
    constraint predicted_rating_ibfk_1
        foreign key (movieId) references movies (movieId)
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

create index rating_index
    on ratings (rating);

create index ratings_userId_movieId_index
    on ratings (userId, movieId);

create table tag_personality
(
    tag                 varchar(200) not null
        primary key,
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

create index tags_userId_movieId_index
    on tags (userId, movieId);

