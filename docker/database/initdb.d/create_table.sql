create sequence seq_member start 1;
create table members
(
    id          bigint default nextval('seq_member') NOT NULL,
    nickname    varchar(255)                         NOT NULL,
    user_role   varchar(50)                          NOT NULL,
    img_url     text                                 NULL,
    created_at  timestamp                            NOT NULL,
    modified_at timestamp,
    deleted_at  timestamp,
    primary key (id),
    unique (nickname)
);

create sequence seq_restaurant start 1;
create table restaurants
(
    id            bigint default nextval('seq_restaurant') NOT NULL,
    name          varchar(255)                             NOT NULL,
    jibun_address jsonb                                    NULL,
    road_address  json                                     NULL,
    latitude      numeric(10, 8)                           NULL,
    longitude     numeric(11, 8)                           NULL,
    biz_type      varchar(100)                             NOT NULL,
    link          text                                     NULL,
    created_at    timestamp                                NOT NULL,
    modified_at   timestamp,
    deleted_at    timestamp,
    primary key (id)
);

create sequence seq_article start 1;
create table articles
(
    id            bigint default nextval('seq_article') NOT NULL,
    member_id     bigint                                NOT NULL,
    restaurant_id bigint                                NOT NULL,
    title         text                                  NOT NULL,
    content       text                                  NOT NULL,
    view_cnt      bigint                                NOT NULL,
    like_cnt      bigint                                NOT NULL,
    rate          smallint[3]                           NOT NULL,
    created_at    timestamp                             NOT NULL,
    modified_at   timestamp,
    deleted_at    timestamp,
    primary key (id),
    foreign key (member_id) references members (id) on delete cascade,
    foreign key (restaurant_id) references restaurants (id)
);

create sequence seq_article_image increment 3 start 1;
create table article_images
(
    id          bigint default nextval('seq_article_image') NOT NULL,
    article_id  bigint                                      NOT NULL,
    img_url     text                                        NOT NULL,
    created_at  timestamp                                   NOT NULL,
    modified_at timestamp,
    primary key (id),
    foreign key (article_id) references articles (id) on delete cascade
);

create sequence seq_article_like start 1;
create table article_likes
(
    id         bigint default nextval('seq_article_like') NOT NULL,
    member_id  bigint                                     NOT NULL,
    article_id bigint                                     NOT NULL,
    created_at timestamp                                  NOT NULL,
    primary key (id),
    foreign key (member_id) references members (id) on delete cascade,
    foreign key (article_id) references articles (id) on delete cascade
);

create sequence seq_article_comment start 1;
create table article_comments
(
    id                bigint default nextval('seq_article') NOT NULL,
    member_id         bigint                                NOT NULL,
    article_id        bigint                                NOT NULL,
    parent_comment_id bigint                                NULL,
    content           text                                  NOT NULL,
    created_at        timestamp                             NOT NULL,
    modified_at       timestamp,
    primary key (id),
    foreign key (member_id) references members (id) on delete cascade,
    foreign key (article_id) references articles (id) on delete cascade
);

create sequence seq_article_bookmark start 1;
create table article_bookmarks
(
    id         bigint default nextval('seq_article_bookmark') NOT NULL,
    member_id  bigint                                         NOT NULL,
    article_id bigint                                         NOT NULL,
    created_at timestamp                                      NOT NULL,
    primary key (id),
    foreign key (member_id) references members (id) on delete cascade,
    foreign key (article_id) references articles (id) on delete cascade
);
