SELECT * FROM PLAYER;
SELECT * FROM TEAM;
SELECT * FROM STADIUM;
SELECT * FROM SCHEDULE;


--1. PLAYER 테이블에서 K02, K05 팀에 해당하는 선수들의 이름과 포지션, 등번호, 키를 조회하시오.
SELECT 
       PLAYER_NAME 선수이름
     , POSITION 포지션
     , BACK_NO 등번호
     , HEIGHT 키
  FROM PLAYER
 WHERE TEAM_ID IN ('K02', 'K05');


--2. PLAYER 테이블에서 국적이 명시된 선수들의 이름과 국적을 조회하시오.
SELECT 
       PLAYER_NAME 선수이름
     , NATION 국적
  FROM PLAYER
 WHERE NATION IS NOT NULL;


--3. PLAYER 테이블에서 팀ID가 K02이거나 K07인 선수들의 이름과 포지션, 등번호, 팀ID, 키를 조회하시오.
SELECT 
       PLAYER_NAME 선수이름
     , POSITION 포지션
     , BACK_NO 등번호
     , TEAM_ID 팀ID
     , HEIGHT 키
  FROM PLAYER
 WHERE TEAM_ID IN ('K02', 'K07');


--4. TEAM 테이블에서 각 팀의 우편번호 두 개를 '-' 구분자로 합하여 팀ID와 우편번호 조합을 조회하시오.
SELECT 
       TEAM_ID 팀ID
     , ZIP_CODE1 || '-' || ZIP_CODE2 우편번호
  FROM TEAM;


--5. PLAYER 테이블에서 모든 선수들의 인원 수와 신장 크기가 등록된 선수의 수, 최대 신장과 최소 신장, 평균 신장의
--   정보를 조회하시오.
SELECT 
       COUNT(*) "전체 인원수"
     , COUNT(HEIGHT) "신장크기등록 인원수"
     , MAX(HEIGHT) "최대 신장"
     , MIN(HEIGHT) "최소 신장"
     , ROUND(AVG(HEIGHT), 2) "평균 신장"
FROM PLAYER;


--6. PLAYER 테이블을 활용하여 각 팀 별 인원수를 조회하는 SQL을 작성하되 정렬은
--   인원 수 기준으로 내림차순 정렬하여 조회 하시오.
SELECT 
       TEAM_ID 팀ID
     , COUNT(TEAM_ID) 인원수
  FROM PLAYER
 GROUP BY TEAM_ID
 ORDER BY 2 DESC;


--7. PLAYER와 TEAM 테이블을 활용하여 각 선수의 이름과 소속팀명을 조회 하시오.
SELECT 
       PLAYER_NAME 선수이름
     , TEAM_NAME 소속팀명
  FROM PLAYER
  JOIN TEAM USING(TEAM_ID);

--8. PLAYER, TEAM, STADIUM 테이블을 활용하여 각 선수들의 정보 중
--   선수명, 포지션, 출신지, 팀명, 홈경기장 명을 조회하시오.
SELECT
       PLAYER_NAME 선수명
     , POSITION 포지션
     , NATION 출신지
     , TEAM_NAME 팀명
     , STADIUM_NAME 홈경기장
  FROM PLAYER
  JOIN TEAM USING(TEAM_ID)
  JOIN STADIUM USING(STADIUM_ID);

--9. TEAM, STADIUM 테이블을 활용하여 각 팀의 이름과 경기장ID, 경기장명을 조회하되
--   경기장ID가 존재하는 팀만 조회 하고 결과는 경기장 명이 오름차순 정렬이 되도록 조회하시오.
SELECT 
       TEAM_NAME 팀명
     , STADIUM_ID 경기장ID
     , STADIUM_NAME 경기장명
  FROM TEAM
  JOIN STADIUM USING (STADIUM_ID)
 ORDER BY 경기장명;


--10. PLAYER 테이블을 활용하여 신장 크기가 모든 선수의 신장 길이의 평균 이상인
--    선수들의 선수명, 포지션, 등번호를 선수이름 기준 오름차순으로 조회하시오.
SELECT 
       PLAYER_NAME 선수명
     , POSITION 포지션
     , BACK_NO 등번호
  FROM PLAYER
 WHERE HEIGHT >= (SELECT AVG(HEIGHT)
                    FROM PLAYER
                   WHERE HEIGHT IS NOT NULL);

--11. 선수 중 '정현수'라는 동명이인이 속한 팀의 한글 명칭과 영문 명칭, 소속 지역을 조회하시오.
SELECT 
       TEAM_NAME "한글 명칭"
     , E_TEAM_NAME "영문 명칭"
     , REGION_NAME "소속 지역"
  FROM TEAM
 WHERE TEAM_ID IN (SELECT TEAM_ID 
                     FROM PLAYER
                    WHERE PLAYER_NAME = '정현수')
 ORDER BY TEAM_NAME;        


--12. PLAYER 테이블에서 각 팀에 속한 선수들 중 소속된 팀의 평균 신장보다 신장 길이가 작은
--    선수들의 팀명, 선수명, 포지션, 등번호, 신장 길이를 조회하시오.
SELECT
       M.TEAM_ID
     , TEAM_NAME 팀명
     , PLAYER_NAME 선수명
     , POSITION 포지션
     , BACK_NO 등번호
     , HEIGHT 신장길이
  FROM PLAYER M
  JOIN TEAM T ON(M.TEAM_ID = T.TEAM_ID)
 WHERE HEIGHT < (SELECT AVG(HEIGHT)
                   FROM PLAYER 
                  GROUP BY M.TEAM_ID)
 ORDER BY 1;


--13. 선수의 이름과 포지션, 등번호, 팀ID, 팀명을 조회하는 뷰(V_TEAM_PLAYER)를 하나 생성한 뒤
--     생성한 뷰를 활용하여 '황'씨성을 가진 선수들의 정보를 조회하시오.
--> 뷰 생성 권한 부여받아야될것!

CREATE OR REPLACE VIEW V_TEAM_PLAYER(선수명, 포지션, 등번호, 팀ID, 팀명)
AS 
SELECT PLAYER_NAME
     , POSITION
     , BACK_NO
     , TEAM_ID
     , TEAM_NAME
  FROM PLAYER 
  JOIN TEAM USING(TEAM_ID);
   
SELECT 
       * 
  FROM V_TEAM_PLAYER
 WHERE 선수명 LIKE '황%';


--14. 울산 현대 팀에 '박주호' 선수가 새로 영입되었다.
--     해당 선수의 정보 중 포지션은 DF이며 1987년 3월 16일생, 신장과 몸무게가 각각 176cm, 75kg으로 
--     나간다고 했을 때, 박주호 선수의 선수ID를 기존 선수들 중 가장 큰 숫자를 지닌 선수에서 
--     숫자를 하나 증가시켜 추가할 수 있는 쿼리를 작성하시오.
SELECT 
       MAX(PLAYER_ID) + 1
  FROM PLAYER;

SELECT 
       TEAM_ID
  FROM TEAM
 WHERE TEAM_NAME = '울산현대';

INSERT 
  INTO PLAYER
(
  PLAYER_NAME
, POSITION
, BIRTH_DATE
, HEIGHT
, WEIGHT
, PLAYER_ID
, TEAM_ID
)
VALUES
(
  '박주호'
, 'DF'
, '87/03/16'
, 176
, 75
, (SELECT MAX(PLAYER_ID) + 1 
     FROM PLAYER)
, (SELECT TEAM_ID 
     FROM TEAM 
    WHERE TEAM_NAME = '울산현대')
);
COMMIT;

SELECT * FROM PLAYER
ORDER BY 1 DESC;


--15. STADIUM 테이블의 정보를 참조하여, TEAM 테이블의 ADDRESS를 
--    해당 팀의 홈 경기장인 STADIUM 명으로 수정하는 
--    쿼리를 작성하시오.
UPDATE TEAM T
   SET ADDRESS = (SELECT STADIUM_NAME
                    FROM STADIUM S
                   WHERE T.STADIUM_ID = S.STADIUM_ID);


--16. SCHEDULE에 기록된 정보들 중 가장 높은 골이 기록된 경기들의 날짜와 경기장 명, 
--     HOME팀과 AWAY팀의 팀 명과 각 팀이 기록한 골의 수를 조회하시오.
SELECT * FROM SCHEDULE;

SELECT 
       SCHE_DATE 경기일자
     , STADIUM_NAME 경기장명
     , H.TEAM_NAME HOME팀명
     , HOME_SCORE HOME팀점수
     , A.TEAM_NAME AWAY팀명
     , AWAY_SCORE AWAY팀점수
  FROM SCHEDULE S
  JOIN STADIUM USING(STADIUM_ID)
  JOIN TEAM H ON (S.HOMETEAM_ID = H.TEAM_ID)
  JOIN TEAM A ON (S.AWAYTEAM_ID = A.TEAM_ID)
 WHERE (HOME_SCORE+AWAY_SCORE) = (SELECT MAX(HOME_SCORE+AWAY_SCORE)
                                    FROM SCHEDULE);
                                 

--17. STADIUM 테이블에서 HOMETEAM의 ID가 정해져 있지 않은 경기장을 조회하여
--    'K'와 현재 등록되어 있는 HOMETEAM_ID 최대값 + 1 을 조합한 ID로 부여하되(수정하되),
--    STADIUM_ID 순으로 부여하는 쿼리문을 작성하시오.
DECLARE
   S STADIUM%ROWTYPE;
BEGIN
    FOR S IN (SELECT *
                FROM STADIUM
               WHERE HOMETEAM_ID IS NULL)
    LOOP
        UPDATE STADIUM
        SET HOMETEAM_ID = 'K'|| (SELECT SUBSTR(MAX(HOMETEAM_ID),2)+1 
                                   FROM STADIUM)
        WHERE STADIUM_ID = S.STADIUM_ID;
    END LOOP;
END;
/
SELECT * FROM STADIUM;


--18. 경기장이나 운동장에 구단 사무실이 위치하지 않은 팀 명과 전화번호, FAX번호 및 주소,
--     소속된 선수의 수를 선수의 수 기준 내림차순으로 조회하는 쿼리를 작성하시오.
SELECT 
       TEAM_NAME 팀명
     , TEL 전화번호
     , FAX 팩스번호
     , ADDRESS 주소
     , (SELECT COUNT(*)
          FROM PLAYER P
         WHERE P.TEAM_ID = T.TEAM_ID) "소속선수 수"
  FROM TEAM T
 WHERE ADDRESS NOT LIKE '%경기장%' 
   AND ADDRESS NOT LIKE '%운동장%'
 ORDER BY 5 DESC;


--19. 최근 한국 스폰서들의 경제상황이 안 좋아져 팀 구단 중 현재 선수가 3명 이하인 구단을
--    정리하게 되었다. TEAM 테이블을 활용하여 현재 소속된 선수가 3명 이하인 구단을 찾아
--    해당 데이터를 삭제하는 쿼리를 작성하시오.
DELETE 
  FROM TEAM
 WHERE TEAM_ID IN (SELECT TEAM_ID
                     FROM PLAYER
                    RIGHT JOIN TEAM USING(TEAM_ID)
                    GROUP BY TEAM_ID
                   HAVING COUNT(TEAM_ID) <= 3);
--> PLAYER 테이블의 외래키 제약조건에 위배되어 오류발생할 것!
--> PLAYER 테이블의 외래키 제약조건에 옵션으로 ON DELETE CASCADE 추가해줘야됨

-- 제약조건은 수정 불가! 삭제했다가 다시 옵션 추가해서 제약조건 추가해주자!
ALTER TABLE PLAYER
 DROP CONSTRAINT PLAYER_FK;
ALTER TABLE PLAYER
  ADD CONSTRAINT PLAYER_FK FOREIGN KEY(TEAM_ID) REFERENCES TEAM ON DELETE CASCADE;


--20. 자신이 작성한 쿼리를 옆 사람과 비교하고 서로의 쿼리에 대해 브리핑해 보시오.