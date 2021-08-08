INSERT INTO users
VALUES (1, 'admin', 'admin', 'admin', DATE '1990-08-08', 'W', '222-1234-5678', SYSTIMESTAMP, SYSTIMESTAMP);

INSERT INTO ingredients VALUES (1, 'Gin', 40, '17세기에 네덜란드에서 의약품으로 발명되었다가 술로 음용하게 되었다. 곡류를 증류한 원액에 쥬니퍼 베리를 담가 재증류시킨 술이다.');
INSERT INTO ingredients VALUES (2, 'Whiskey', 40, '미국 테세시주에서 생상된 테네시 위스키이다.');
INSERT INTO ingredients VALUES (3, '베르무트', 18, '포도주에 브랜디나 당분을 섞고, 향쑥·용담·키니네·창포뿌리 등의 향료나 약초를 넣어 향미를 낸 리큐어');

INSERT INTO cocktails VALUES (1, 'Dry Martini', '마티니는 보드카 마티디와 진 마티니가 있다.', 'Cocktail', 1, SYSTIMESTAMP, SYSTIMESTAMP);
INSERT INTO cocktails VALUES (2, 'Jack Coke', '잭 다니엘과 콜라를 섞은 칵테일', 'Highball', 1, SYSTIMESTAMP, SYSTIMESTAMP);

INSERT INTO recipes VALUES (1, 1, 60);
INSERT INTO recipes VALUES (1, 3, 20);
INSERT INTO recipes VALUES (2, 2, 30);
