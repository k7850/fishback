
-------------------------------------------------------------------------------------------------------------------------

--유저

INSERT INTO user_tb (`id`, `email`, `password`, `user_type_enum`, `username`)
VALUES ('1', 'ssar@naver.com', '1234', 'NORMAL', '쌀');
INSERT INTO user_tb (`id`, `email`, `password`, `user_type_enum`, `username`)
VALUES ('2', 'cos@naver.com', '1234', 'NORMAL', '코스');
INSERT INTO user_tb (`id`, `email`, `password`, `user_type_enum`, `username`)
VALUES ('3', 'admin@naver.com', '1234', 'ADMIN', '관리자');
INSERT INTO user_tb (`id`, `email`, `password`, `user_type_enum`, `username`)
VALUES ('4', 'love@naver.com', '1234', 'NORMAL', '럽브');


UPDATE user_tb
SET CREATED_AT = '2023-11-11'
WHERE CREATED_AT IS NULL;

UPDATE user_tb
SET UPDATED_AT = '2023-11-11'
WHERE UPDATED_AT IS NULL;

-------------------------------------------------------------------------------------------------------------------------

--어항

INSERT INTO aquarium_tb (`user_id`,`title`, `intro`, `photo`, `is_fresh_water`, `size`, `s1`, `s2`, `s3`, `s4`, `s5`)
VALUES ('1', '수초어항', '코리도라스 상태 체크하고 안좋으면 격리시켜야함', 'aquarium/aquarium1.jpg', 'true', '30/30/30', '여과기:aaa', '히터:bbb', '바닥재:ccccc', '조명:dddd', 'CO2:eeeee');

INSERT INTO aquarium_tb (`user_id`,`title`, `intro`, `photo`, `is_fresh_water`, `size`, `s1`, `s2`, `s3`, `s4`, `s5`)
VALUES ('1', '어항2', '어항2소개', 'aquarium/aquarium2.jpg', 'false', '90/30/30', '여과기:aa', '히터:bbb', '바닥재:ccccc', '조명:dddd', 'CO2:eeeee');

INSERT INTO aquarium_tb (`user_id`,`title`, `intro`, `photo`, `is_fresh_water`, `size`, `s1`, `s2`, `s3`, `s4`, `s5`)
VALUES ('1', '어항3', '어항3소개', 'aquarium/aquarium3.jpg', 'true', '45/45/45', '여과기:aaa', '히터:bbb', '바닥재:ccccc', '조명:dddd', 'CO2:eeeee');

INSERT INTO aquarium_tb (`user_id`,`title`, `intro`, `photo`, `is_fresh_water`, `size`, `s1`, `s2`, `s3`, `s4`, `s5`)
VALUES ('1', '어항4', '어항4소개', 'aquarium/aquarium4.jpg', 'true', '60/45/45', '여과기:aaa', '히터:bbb', '바닥재:ccccc', '조명:dddd', 'CO2:eeeee');



UPDATE aquarium_tb
SET CREATED_AT = '2023-11-11'
WHERE CREATED_AT IS NULL;

UPDATE aquarium_tb
SET UPDATED_AT = '2023-11-11'
WHERE UPDATED_AT IS NULL;


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--물고기

INSERT INTO fish_tb (`aquarium_id`,`name`, `text`, `quantity`, `price`, `photo`, `fish_class_enum`)
VALUES ('1', '네온이', '13일 데려옴', '6', '1000', 'book/neon_tetra.jpg', 'FISH');

INSERT INTO fish_tb (`aquarium_id`,`name`, `quantity`, `price`, `photo`, `fish_class_enum`)
VALUES ('1', '라스', '3', '2000', 'book/rasbora.jpg', 'FISH');

INSERT INTO fish_tb (`aquarium_id`,`name`, `text`, `price`, `photo`, `fish_class_enum`)
VALUES ('1', '점박이', '밥 잘먹나 확인 필요!', '1200', 'book/corydoras.jpg', 'FISH');

INSERT INTO fish_tb (`aquarium_id`,`name`, `text`, `price`, `photo`, `fish_class_enum`)
VALUES ('1', '헤어그라스', '잎 누렇게 변하면 비료 주기', '1800', 'book/hairgrass.png', 'PLANT');

INSERT INTO fish_tb (`aquarium_id`,`name`, `quantity`, `price`, `photo`, `fish_class_enum`)
VALUES ('1', '레드', '1', '500', 'book/cherry_shrimp.png', 'OTHER');

INSERT INTO fish_tb (`aquarium_id`,`name`, `text`, `quantity`, `price`, `photo`, `fish_class_enum`)
VALUES ('1', '옐로', '맨날 숨어서 안보이네', '1', '700', 'book/cherry_shrimp.png', 'OTHER');




UPDATE fish_tb
SET CREATED_AT = '2023-11-11'
WHERE CREATED_AT IS NULL;

UPDATE fish_tb
SET UPDATED_AT = '2023-11-11'
WHERE UPDATED_AT IS NULL;


-------------------------------------------------------------------------------------------------------------------------

--일정

INSERT INTO schedule_tb (`aquarium_id`, `schedule_enum`, `title`, `between_day`, `is_completed`)
VALUES ('1', '간격', '사료 급여', '1', 'false');

INSERT INTO schedule_tb (`aquarium_id`, `schedule_enum`, `title`, `between_day`, `is_completed`)
VALUES ('1', '간격', '사료 특식 주기', '10', 'false');

INSERT INTO schedule_tb (`aquarium_id`, `schedule_enum`, `title`, `target_day`, `is_completed`)
VALUES ('1', '지정', '수초 심기', now(), 'false');

INSERT INTO schedule_tb (`aquarium_id`, `schedule_enum`, `title`, `target_day`, `is_completed`)
VALUES ('1', '지정', '여과재 교체', '2023-11-24 11:00:00.0', 'false');

INSERT INTO schedule_tb (`aquarium_id`, `schedule_enum`, `title`, `between_day`, `is_completed`)
VALUES ('1', '요일', '벽면 청소', '1', 'false');

INSERT INTO schedule_tb (`aquarium_id`, `schedule_enum`, `title`, `between_day`, `is_completed`)
VALUES ('1', '요일', '물 환수', '5', 'false');

UPDATE schedule_tb
SET CREATED_AT = '2023-11-11'
WHERE CREATED_AT IS NULL;

UPDATE schedule_tb
SET UPDATED_AT = '2023-11-11'
WHERE UPDATED_AT IS NULL;

-------------------------------------------------------------------------------------------------------------------------


--보드

INSERT INTO board_tb (`aquarium_id`, `user_id`, `title`, `text`)
VALUES ('1', '1', '게시글1', '게시글내용1');

UPDATE board_tb
SET CREATED_AT = '2023-11-11'
WHERE CREATED_AT IS NULL;

UPDATE board_tb
SET UPDATED_AT = '2023-11-11'
WHERE UPDATED_AT IS NULL;

-------------------------------------------------------------------------------------------------------------------------
