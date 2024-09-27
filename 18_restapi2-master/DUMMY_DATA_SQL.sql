-- password : 1234
INSERT INTO TBL_MEMBER VALUES (SEQ_MEMBER_CODE.NEXTVAL, 'admin', '관리자', '$2a$10$COvazywgZPXseeKaYhruh.pAYYfcSeGO5aSrHOsLZN0X8joNwW2dW', 'ohgiraffers@gmail.com');
INSERT INTO TBL_MEMBER VALUES (SEQ_MEMBER_CODE.NEXTVAL, 'test01', '오지라퍼', '$2a$10$N34MRj4tKVD0AxwvEcC8eOLUyBpXloPKE7Yw.S4/kj5fD1OU5BWsi', 'test01@naver.com');

INSERT INTO TBL_AUTHORITY VALUES (SEQ_AUTHORITY_CODE.NEXTVAL, 'ROLE_ADMIN', '관리자');
INSERT INTO TBL_AUTHORITY VALUES (SEQ_AUTHORITY_CODE.NEXTVAL, 'ROLE_USER', '일반회원');

INSERT INTO TBL_MEMBER_ROLE VALUES (1, 1);
INSERT INTO TBL_MEMBER_ROLE VALUES (1, 2);
INSERT INTO TBL_MEMBER_ROLE VALUES (2, 2);

INSERT INTO TBL_CATEGORY VALUES (SEQ_CATEGORY_CODE.NEXTVAL, '식사');
INSERT INTO TBL_CATEGORY VALUES (SEQ_CATEGORY_CODE.NEXTVAL, '디저트');
INSERT INTO TBL_CATEGORY VALUES (SEQ_CATEGORY_CODE.NEXTVAL, '음료');

INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '열무김치라떼', 4500, '열무로 만든 김치 라떼', 'Y', 3, '06a0060ae2da4dffb9a8a440ba5d9c5e.PNG', 10);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '우럭스무디', 5000, '우럭으로 만든 스무디', 'Y', 3, 'fcb3e0c8f94940cf99724d26e6020259.PNG', 15);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '생갈치쉐이크', 6000, '생으로 갈아만든 갈치 쉐이크', 'Y', 3, '8e2492fd197e42d5855ffbbb5142b4ed.PNG', 17);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '갈릭미역파르페', 7000, '갈릭 미역을 섞어 만든 파르페', 'Y', 2, '58b3fd68f6074de2b33d4430fd29244b.PNG', 19);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '앙버터김치찜', 13000, '가장 먹을만한 김치찜', 'Y', 1, '7580adcf59d04240b7a16f6cf07bd34b.PNG', 19);

INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '생마늘샐러드', 12000, '생마늘을 넣어 만든 샐러드', 'Y', 2, '7b91aee3ddec49a69a9b7d2849493f7f.PNG', 24);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '민트미역국', 15000, '민트를 넣어만든 미역국', 'Y', 1, 'af732dfe3e4f482bac8d1ef0bd9be02b.PNG', 12);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '한우딸기국밥', 20000, '한우에 딸기를 얹은 국밥', 'Y', 1, '8a4cd876df574970a565b41e47561080.PNG', 22);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '홍어마카롱', 9000, '홍어를 갈아 넣은 마카롱', 'Y', 2, 'c0a177a658b44f749699f91a23c47d8b.PNG', 17);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '코다리마늘빵', 7000, '코다리 마늘빵은 진리', 'Y', 2, '053626c2d16f4814a5e81b842a115dc7.PNG', 17);

INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '정어리빙수', 10000, '정어리를 갈아 만든 빙수', 'Y', 2, '323a5df17163482d90a74f8198a4e4c6.PNG', 12);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '날치알스크류바', 2000, '스크류바에 날치알이라니', 'Y', 2, 'd97144a76a7b42fabfcf8a5662762040.PNG', 12);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '직화구이젤라또', 8000, '젤라또를 직화로', 'Y', 2, 'a4195b272f764a3ab81e85fbbba6c067.PNG', 16);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '과메기커틀릿', 13000, '커틀렛 속에 쏙 과메기', 'Y', 1, '702215dbe9784ebf92561d5504b0b5a6.PNG', 11);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '죽방멸치튀김우동', 11000, '너도 나도 죽방 멸치 우동', 'Y', 1, '5c226e59e7c342608d220ce0c476d01f.PNG', 13);

INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '흑마늘아메리카노', 3000, '흑마늘을 넣은 아메아메아메리카노', 'Y', 3, '684fd2cccfc74ad4944619bc72f76788.PNG', 21);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '아이스가리비관자육수', 50000, '아이스 육수 가리비 관자', 'Y', 1, 'b150ff66223a46adabb75a71299cb25a.PNG', 16);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '붕어빵초밥', 20000, '초밥이 붕어빵에 있는것인가 그 반대인가', 'Y', 1, 'c260a42b01394faba92ed9a7ca868aa9.PNG', 11);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '까나리코코넛쥬스', 5000, '코코넛 주스에 까나리 한스푼', 'Y', 3, '21406eb90b2f4ae09ea0af015d2df6fe.PNG', 28);
INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '마라깐쇼한라봉', 22000, '마라깐쇼 한라봉 고고', 'Y', 3, 'eccf4c58d72241ddb4c84401485d4363.png', 21);

INSERT INTO TBL_PRODUCT VALUES (SEQ_PRODUCT_CODE.NEXTVAL, '돌미나리백설기', 5000, '백설기 속에 씹히는 돌미나리', 'Y', 2, '9a6c1b13af0a469fa2d9e3084f6e438c.PNG', 12);



INSERT INTO TBL_REVIEW VALUES (SEQ_REVIEW_CODE.NEXTVAL, 1, 2, '열무김치라떼 리뷰 입니다.', '정말 맛있는 이 라떼 저만 먹을수 없습니다! 꼭 드세요 두번 드세요~!', TO_CHAR(SYSDATE, 'RR/MM/DD HH:MI:SS'));
INSERT INTO TBL_REVIEW VALUES (SEQ_REVIEW_CODE.NEXTVAL, 21, 2, '돌미나리백설기 리뷰 입니다.', '돌을 씹어 먹을수 있는 분만 드시기 바랍니다~! 진짜 돌이에요', TO_CHAR(SYSDATE, 'RR/MM/DD HH:MI:SS'));
INSERT INTO TBL_REVIEW VALUES (SEQ_REVIEW_CODE.NEXTVAL, 20, 2, '마라깐쇼한라봉 리뷰 입니다.', '한라봉을 먹는데 매콤하네요? 시원합니다~!', TO_CHAR(SYSDATE, 'RR/MM/DD HH:MI:SS'));
INSERT INTO TBL_REVIEW VALUES (SEQ_REVIEW_CODE.NEXTVAL, 19, 2, '까나리코코넛쥬스 리뷰 입니다.', '까나리 까나리 신나는 노래~ 나도한번 불러본다', TO_CHAR(SYSDATE, 'RR/MM/DD HH:MI:SS'));
INSERT INTO TBL_REVIEW VALUES (SEQ_REVIEW_CODE.NEXTVAL, 18, 2, '붕어빵초밥 리뷰 입니다.', '붕어빵으로 만든 초밥인데 이게 빵인지 붕어인지 모르겠습니다.', TO_CHAR(SYSDATE, 'RR/MM/DD HH:MI:SS'));
INSERT INTO TBL_REVIEW VALUES (SEQ_REVIEW_CODE.NEXTVAL, 17, 2, '아이스가리비관자육수 리뷰 입니다.', '이가 시려워 꽁 발이 시려워 꽁 관자육수 때문에 꽁꽁꽁!', TO_CHAR(SYSDATE, 'RR/MM/DD HH:MI:SS'));


COMMIT;
