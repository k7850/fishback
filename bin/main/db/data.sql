
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

INSERT INTO aquarium_tb (`user_id`,`title`, `intro`, `photo`, `is_fresh_water`, `size`)
VALUES ('1', '수초어항', '코리도라스 상태 체크하고 안좋으면 격리시켜야함', 'aquarium/dummy/aquarium1.jpg', 'true', '30/30/30');

INSERT INTO aquarium_tb (`user_id`,`title`, `intro`, `photo`, `is_fresh_water`, `size`)
VALUES ('1', '해수어항', '어항2소개', 'aquarium/dummy/aquarium2.jpg', 'false', '90/30/30');

INSERT INTO aquarium_tb (`user_id`,`title`, `photo`, `is_fresh_water`, `size`)
VALUES ('1', '디스커스어항', 'aquarium/dummy/aquarium3.jpg', 'true', '45/45/45');

INSERT INTO aquarium_tb (`user_id`,`title`, `intro`, `photo`, `is_fresh_water`, `size`)
VALUES ('1', '시클리드어항', '', 'aquarium/dummy/aquarium4.jpg', 'true', '60/45/45');



UPDATE aquarium_tb
SET CREATED_AT = '2023-11-11'
WHERE CREATED_AT IS NULL;

UPDATE aquarium_tb
SET UPDATED_AT = '2023-11-11'
WHERE UPDATED_AT IS NULL;


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--장비

INSERT INTO equipment_tb (`aquarium_id`,`category`, `name`)
VALUES ('1', '여과기', '걸이식 여과기 Bi7');

INSERT INTO equipment_tb (`aquarium_id`,`category`, `name`)
VALUES ('1', '여과기', '저면 여과기');

INSERT INTO equipment_tb (`aquarium_id`,`category`, `name`)
VALUES ('1', '조명', 'LED 등커버 200');

INSERT INTO equipment_tb (`aquarium_id`,`category`, `name`)
VALUES ('1', '히터', 'AD-55');

INSERT INTO equipment_tb (`aquarium_id`,`category`, `name`)
VALUES ('1', '바닥재', '흑사');

INSERT INTO equipment_tb (`aquarium_id`,`category`, `name`)
VALUES ('2', '여과기', 'Suw-2');

INSERT INTO equipment_tb (`aquarium_id`,`category`, `name`)
VALUES ('2', '스키머', '리선 50L');




UPDATE equipment_tb
SET CREATED_AT = '2023-11-11'
WHERE CREATED_AT IS NULL;

UPDATE equipment_tb
SET UPDATED_AT = '2023-11-11'
WHERE UPDATED_AT IS NULL;


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--생물도감

INSERT INTO book_tb (`normal_name`,`biology_name`, `photo`, `difficulty`, `is_fresh_water`, `fish_class_enum`, `text`)
VALUES ('네온 테트라', 'Paracheirodon innesi', 'book/neon_tetra.jpg', '2', 'true','FISH', '아마존강에 서식하는 카라신과의 열대어. 소형 테트라 어종으로 저렴한 가격에 떼를 지어 다니는 모습이 아름다워 입문종으로 널리 사랑받고 있다.');

INSERT INTO book_tb (`normal_name`,`biology_name`, `photo`, `difficulty`, `is_fresh_water`, `fish_class_enum`, `text`)
VALUES ('라스보라', 'Danioninae', 'book/rasbora.jpg', '4', 'true','FISH', '라스보라는 잉어목 잉어과 다니오아과에 속하는 어류들을 총칭한다. 다니오속의 종들은 라스보라로 부르기보다는 다니오라고 부르는 것이 일반적이지만, 몇몇 종은 라스보라로 부르기도 한다.');

INSERT INTO book_tb (`normal_name`,`biology_name`, `photo`, `difficulty`, `is_fresh_water`, `fish_class_enum`, `text`)
VALUES ('코리도라스', 'Corydoras', 'book/corydoras.jpg', '3', 'true','FISH', '아마존 강 및 남미를 대표하는 열대어 중 하나. 메기목 칼리크티스과에 속하며, 남아메리카의 아마존 수계와 라플라타 수계에 폭넓게 분포하는 물고기이다. 어원은 그리스어로 kory는 투구(헬멧), doras는 피부이며 "투구(헬멧)같은 피부"라는 뜻으로 두개골이 단단해서 붙여진 이름이다.');

INSERT INTO book_tb (`normal_name`,`biology_name`, `photo`, `difficulty`, `is_fresh_water`, `fish_class_enum`, `text`)
VALUES ('흰동가리', 'Amphiprioninae', 'book/clownfish.png', '4', 'false','FISH', '자리돔과 흰동가리아과에 속하는 물고기를 통칭하여 흰동가리라고 부른다. 또 다른 말은 광대어 혹은 아네모네 피시. 영화 니모를 찾아서로 유명해진 그 물고기. 좁은 의미로는 국내 기록종인 흰동가리(Amphiprion clarkii)를 부르는 말이다. 단체를 이루어 큰 암컷과 큰 수컷이 무리를 지배하며, 그 밑에는 부하 수컷 서너마리가 함께 산다. 큰 암컷이 죽으면 부하 수컷 중 가장 큰 개체가 암컷으로 성전환(웅성선숙)을 한다. 잡식성으로 해조류, 플랑크톤, 소형 갑각류 등을 먹고 산다.');



INSERT INTO book_tb (`normal_name`,`biology_name`, `photo`, `difficulty`, `is_fresh_water`, `fish_class_enum`, `text`)
VALUES ('체리새우', 'Neocaridina davidi', 'book/cherry_shrimp.png', '4', 'true','OTHER', '관상용으로 인기있는 민물새우. 체리새우라는 이름으로 알려져 있는데, 정확히는 붉은 점박이 모프만을 체리새우라 지칭하고, 모프마다 제각기 다른 이름을 가진다. 야생형은 그냥 Neocaridina davidi로 불리며, 외견상 새뱅이와 구분하기 힘들다. 새뱅이와는 같은 속에 있는 근연 관계이다.');

INSERT INTO book_tb (`normal_name`,`biology_name`, `photo`, `difficulty`, `is_fresh_water`, `fish_class_enum`, `text`)
VALUES ('페닌슐라쿠터', 'Neocaridina davidi', 'book/peninsula_cooter.jpg', '2', 'true','OTHER', '페닌슐라쿠터는 파충강 거북목 잠경아목 늪거북과 쿠터속에 속하는 거북의 한 종류이다. 다른 반수생 거북들보다 성장속도가 매우 빠르며 가격이 저렴하고 사육이 비교적 용이해 반려 거북으로 인기가 많다.');

INSERT INTO book_tb (`normal_name`,`biology_name`, `photo`, `difficulty`, `is_fresh_water`, `fish_class_enum`, `text`)
VALUES ('별불가사리', 'Asterina pectinifera', 'book/seastar.jpg', '5', 'false','OTHER', '연변목 별불가사리과의 불가사리로 한반도, 일본, 러시아의 사할린섬, 연해주 등지에 분포하며, 실을 감아놓는 실패를 닮아 실패불가사리라고도 불린다. 한국에서 제일 흔하게 볼 수 있으며 불가사리하면 가장 먼저 떠오르는 종 중 하나다.');




INSERT INTO book_tb (`normal_name`,`biology_name`, `photo`, `difficulty`, `is_fresh_water`, `fish_class_enum`, `text`)
VALUES ('모스볼', 'Aegagropila linnaei', 'book/moss_ball.png', '1', 'true','PLANT', '마리모라고도 한다. 대마디말과에 속하는 담수성 녹조류의 일종. 공 모양으로 뭉쳐서 자란다. 일본 홋카이도의 아칸 호(阿寒湖)에서 최초로 발견되었으며 이곳에서 자라는 마리모는 국가 천연기념물로 지정되어 있어서 호수에서 자라는 마리모를 채취하는 것은 불법이다. 유럽에서는 아이슬란드 북동부 뮈바튼 호(Mývatn)에 서식하는 개체가 유명하다.');







UPDATE book_tb
SET CREATED_AT = '2023-11-11'
WHERE CREATED_AT IS NULL;

UPDATE book_tb
SET UPDATED_AT = '2023-11-11'
WHERE UPDATED_AT IS NULL;



--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--물고기

INSERT INTO fish_tb (`aquarium_id`,`name`, `text`, `quantity`, `price`, `fish_class_enum`, `book_id`)
VALUES ('1', '네온이', '13일 데려옴', '6', '1000', 'FISH', '1');

INSERT INTO fish_tb (`aquarium_id`,`name`, `quantity`, `price`, `fish_class_enum`, `book_id`)
VALUES ('1', '라보', '3', '2000',  'FISH', '2');

INSERT INTO fish_tb (`aquarium_id`,`name`, `text`, `price`, `fish_class_enum`, `book_id`)
VALUES ('1', '점박이', '밥 잘먹나 확인 필요!', '1200', 'FISH', '3');



INSERT INTO fish_tb (`aquarium_id`,`name`, `quantity`, `price`, `fish_class_enum`, `book_id`)
VALUES ('1', '레드', '1', '500', 'OTHER', '5');

INSERT INTO fish_tb (`aquarium_id`,`name`, `text`, `quantity`, `price`, `fish_class_enum`, `book_id`)
VALUES ('1', '옐로', '맨날 숨어서 안보이네', '1', '700', 'OTHER', '5');



INSERT INTO fish_tb (`aquarium_id`,`name`, `price`, `fish_class_enum`, `book_id`)
VALUES ('1', '모스볼', '2500', 'PLANT', '8');

INSERT INTO fish_tb (`aquarium_id`,`name`, `text`, `price`, `photo`, `fish_class_enum`)
VALUES ('1', '헤어그라스', '잎 누렇게 변하면 비료 주기', '1800', 'book/hairgrass.png', 'PLANT');







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
