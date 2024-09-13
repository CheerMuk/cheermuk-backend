insert into members(id, nickname, user_role, img_url, created_at, modified_at, deleted_at) VALUES
(nextval('seq_member'), '김주영(김주영)', 'ADMIN', null, CURRENT_TIMESTAMP, null, null);

insert into restaurants(id, name, jibun_address, road_address, coordinates, biz_type, link, created_at, modified_at, deleted_at) values
(nextval('seq_restaurant'), 'text', '{"sido": "경기", "detail": "123호", "sigungu": "수원시", "postCode": "12345", "eupmeyondong": "팔달구 인계동"}', '{"address":"도로명 주소","roadCode":"도로 코드"}',
 'POINT(1.1 1.2)'::geometry, 'KOREAN', null, CURRENT_TIMESTAMP, null, null);

insert into articles(id, member_id, restaurant_id, title, content, view_cnt, like_cnt, rate, created_at, modified_at, deleted_at) values
(nextval('seq_article'), 1, 1, 'title', 'content', 0, 0, '{3, 3, 3}', CURRENT_TIMESTAMP, null, null);
