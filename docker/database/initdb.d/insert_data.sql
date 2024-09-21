insert into members(id, nickname, user_role, img_url, created_at, modified_at, deleted_at) VALUES
(nextval('seq_member'), '김주영 (김주영)', 'ADMIN', null, CURRENT_TIMESTAMP, null, null);

insert into articles(id, member_id, restaurant_id, title, content, view_cnt, like_cnt, rate, created_at, modified_at, deleted_at) values
(nextval('seq_article'), 1, 1, 'title', 'content', 0, 0, '{3, 3, 3}', CURRENT_TIMESTAMP, null, null);
