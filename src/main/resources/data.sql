-- Food 테이블에 데이터 삽입
INSERT INTO food (name, english_name, photo_url, video_url) VALUES
('김치찌개', 'Kimchi Stew', 'kimchijjigae.jpg', 'kimchijjigae_video.mp4'),
('불고기', 'Bulgogi', 'bulgogi.jpg', 'bulgogi_video.mp4');

-- food_primary_ingredients 테이블에 데이터 삽입
-- food_id 값은 food 테이블의 실제 ID와 일치해야 함
-- 각 재료는 별도의 컬럼으로 저장됨
INSERT INTO food_primary_ingredients (food_id, name, quantity) VALUES
                                                                   (1, '김치', '500g'),
                                                                   (1, '돼지고기', '200g'),
                                                                   (2, '소고기', '300g'),
                                                                   (2, '양파', '1개');

-- food_sauce_ingredients 테이블에 데이터 삽입
-- food_id 값은 food 테이블의 실제 ID와 일치해야 함
-- 각 소스 재료는 별도의 컬럼으로 저장됨
INSERT INTO food_sauce_ingredients (food_id, name, quantity) VALUES
                                                                 (1, '고춧가루', '두 스푼'),
                                                                 (1, '다진 마늘', '한 스푼'),
                                                                 (2, '간장', '세 스푼'),
                                                                 (2, '설탕', '한 스푼');


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


/*{ Post Test Data
    "foodName": "된장찌개",
    "foodEnglishName": "Doenjang Stew",
    "photoUrl": "doenjangjjigae.jpg",
    "videoUrl": "doenjangjjigae_video.mp4",
    "primaryIngredients": [
        {"name": "된장", "quantity": "d100g"},
        {"name": "두부", "quantity": "200g"}
    ],
    "sauceIngredients": [
        {"name": "고추가루", "quantity": "1스푼"},
        {"name": "다진 마늘", "quantity": "1스푼"}
    ],
    "instructions": [
        "물을 끓인다",
        "된장을 푼다",
        "재료를 넣고 끓인다"
    ],
    "wineName": "Chardonnay",
    "wineType": "WHITE",
    "boldness": 50,
    "acidity": 60,
    "fizziness": 30,
    "tannic": 20,
    "likesCount": 5
}*/