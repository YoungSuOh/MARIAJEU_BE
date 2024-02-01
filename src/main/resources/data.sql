-- Food 테이블에 데이터 삽입
INSERT INTO food (name, english_name, photo_url, video_url) VALUES
('김치찌개', 'Kimchi Stew', 'kimchijjigae.jpg', 'kimchijjigae_video.mp4'),
('불고기', 'Bulgogi', 'bulgogi.jpg', 'bulgogi_video.mp4');

-- food_primary_ingredients 테이블에 데이터 삽입 (ID는 food 테이블의 ID에 맞게 조정)
INSERT INTO food_primary_ingredients (food_id, primary_ingredient) VALUES
(1, '김치'),
(1, '돼지고기'),
(2, '소고기'),
(2, '양파');

-- food_sauce_ingredients 테이블에 데이터 삽입
INSERT INTO food_sauce_ingredients (food_id, sauce_ingredient) VALUES
                                                                   (1, '고춧가루'),
                                                                   (1, '다진 마늘'),
                                                                   (2, '간장'),
                                                                   (2, '설탕');

-- food_instructions 테이블에 데이터 삽입
INSERT INTO food_instructions (food_id, instruction) VALUES
                                                         (1, '김치를 볶는다'),
                                                         (1, '물을 붓고 끓인다'),
                                                         (2, '고기를 양념한다'),
                                                         (2, '고기를 구워서 먹는다');


INSERT INTO wine (name, country, age, price, type, boldness, acidity, fizziness, tannic, type_description) VALUES
('Bordeaux', 'France', 5, 50.0, 'RED', 80, 70, 10, 50, 'A classic full-bodied red wine'),
('Chardonnay', 'USA', 3, 30.0, 'WHITE', 40, 60, 20, 30, 'A popular white wine with a light taste'),
('Prosecco', 'Italy', 2, 25.0, 'SPARKLING', 30, 50, 80, 20, 'A sparkling wine perfect for celebrations');


INSERT INTO food_article (food_id, wine_id) VALUES
(1, 1),
(2, 2);

-- FoodArticleLikes 데이터 삽입 (예시, 필요에 따라 수정)
INSERT INTO food_article_likes (food_article_id) VALUES
(1),
(2);