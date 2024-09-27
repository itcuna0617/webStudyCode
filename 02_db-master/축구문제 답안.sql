SELECT * FROM PLAYER;
SELECT * FROM TEAM;
SELECT * FROM STADIUM;
SELECT * FROM SCHEDULE;


--1. PLAYER ���̺��� K02, K05 ���� �ش��ϴ� �������� �̸��� ������, ���ȣ, Ű�� ��ȸ�Ͻÿ�.
SELECT 
       PLAYER_NAME �����̸�
     , POSITION ������
     , BACK_NO ���ȣ
     , HEIGHT Ű
  FROM PLAYER
 WHERE TEAM_ID IN ('K02', 'K05');


--2. PLAYER ���̺��� ������ ��õ� �������� �̸��� ������ ��ȸ�Ͻÿ�.
SELECT 
       PLAYER_NAME �����̸�
     , NATION ����
  FROM PLAYER
 WHERE NATION IS NOT NULL;


--3. PLAYER ���̺��� ��ID�� K02�̰ų� K07�� �������� �̸��� ������, ���ȣ, ��ID, Ű�� ��ȸ�Ͻÿ�.
SELECT 
       PLAYER_NAME �����̸�
     , POSITION ������
     , BACK_NO ���ȣ
     , TEAM_ID ��ID
     , HEIGHT Ű
  FROM PLAYER
 WHERE TEAM_ID IN ('K02', 'K07');


--4. TEAM ���̺��� �� ���� �����ȣ �� ���� '-' �����ڷ� ���Ͽ� ��ID�� �����ȣ ������ ��ȸ�Ͻÿ�.
SELECT 
       TEAM_ID ��ID
     , ZIP_CODE1 || '-' || ZIP_CODE2 �����ȣ
  FROM TEAM;


--5. PLAYER ���̺��� ��� �������� �ο� ���� ���� ũ�Ⱑ ��ϵ� ������ ��, �ִ� ����� �ּ� ����, ��� ������
--   ������ ��ȸ�Ͻÿ�.
SELECT 
       COUNT(*) "��ü �ο���"
     , COUNT(HEIGHT) "����ũ���� �ο���"
     , MAX(HEIGHT) "�ִ� ����"
     , MIN(HEIGHT) "�ּ� ����"
     , ROUND(AVG(HEIGHT), 2) "��� ����"
FROM PLAYER;


--6. PLAYER ���̺��� Ȱ���Ͽ� �� �� �� �ο����� ��ȸ�ϴ� SQL�� �ۼ��ϵ� ������
--   �ο� �� �������� �������� �����Ͽ� ��ȸ �Ͻÿ�.
SELECT 
       TEAM_ID ��ID
     , COUNT(TEAM_ID) �ο���
  FROM PLAYER
 GROUP BY TEAM_ID
 ORDER BY 2 DESC;


--7. PLAYER�� TEAM ���̺��� Ȱ���Ͽ� �� ������ �̸��� �Ҽ������� ��ȸ �Ͻÿ�.
SELECT 
       PLAYER_NAME �����̸�
     , TEAM_NAME �Ҽ�����
  FROM PLAYER
  JOIN TEAM USING(TEAM_ID);

--8. PLAYER, TEAM, STADIUM ���̺��� Ȱ���Ͽ� �� �������� ���� ��
--   ������, ������, �����, ����, Ȩ����� ���� ��ȸ�Ͻÿ�.
SELECT
       PLAYER_NAME ������
     , POSITION ������
     , NATION �����
     , TEAM_NAME ����
     , STADIUM_NAME Ȩ�����
  FROM PLAYER
  JOIN TEAM USING(TEAM_ID)
  JOIN STADIUM USING(STADIUM_ID);

--9. TEAM, STADIUM ���̺��� Ȱ���Ͽ� �� ���� �̸��� �����ID, �������� ��ȸ�ϵ�
--   �����ID�� �����ϴ� ���� ��ȸ �ϰ� ����� ����� ���� �������� ������ �ǵ��� ��ȸ�Ͻÿ�.
SELECT 
       TEAM_NAME ����
     , STADIUM_ID �����ID
     , STADIUM_NAME ������
  FROM TEAM
  JOIN STADIUM USING (STADIUM_ID)
 ORDER BY ������;


--10. PLAYER ���̺��� Ȱ���Ͽ� ���� ũ�Ⱑ ��� ������ ���� ������ ��� �̻���
--    �������� ������, ������, ���ȣ�� �����̸� ���� ������������ ��ȸ�Ͻÿ�.
SELECT 
       PLAYER_NAME ������
     , POSITION ������
     , BACK_NO ���ȣ
  FROM PLAYER
 WHERE HEIGHT >= (SELECT AVG(HEIGHT)
                    FROM PLAYER
                   WHERE HEIGHT IS NOT NULL);

--11. ���� �� '������'��� ���������� ���� ���� �ѱ� ��Ī�� ���� ��Ī, �Ҽ� ������ ��ȸ�Ͻÿ�.
SELECT 
       TEAM_NAME "�ѱ� ��Ī"
     , E_TEAM_NAME "���� ��Ī"
     , REGION_NAME "�Ҽ� ����"
  FROM TEAM
 WHERE TEAM_ID IN (SELECT TEAM_ID 
                     FROM PLAYER
                    WHERE PLAYER_NAME = '������')
 ORDER BY TEAM_NAME;        


--12. PLAYER ���̺��� �� ���� ���� ������ �� �Ҽӵ� ���� ��� ���庸�� ���� ���̰� ����
--    �������� ����, ������, ������, ���ȣ, ���� ���̸� ��ȸ�Ͻÿ�.
SELECT
       M.TEAM_ID
     , TEAM_NAME ����
     , PLAYER_NAME ������
     , POSITION ������
     , BACK_NO ���ȣ
     , HEIGHT �������
  FROM PLAYER M
  JOIN TEAM T ON(M.TEAM_ID = T.TEAM_ID)
 WHERE HEIGHT < (SELECT AVG(HEIGHT)
                   FROM PLAYER 
                  GROUP BY M.TEAM_ID)
 ORDER BY 1;


--13. ������ �̸��� ������, ���ȣ, ��ID, ������ ��ȸ�ϴ� ��(V_TEAM_PLAYER)�� �ϳ� ������ ��
--     ������ �並 Ȱ���Ͽ� 'Ȳ'������ ���� �������� ������ ��ȸ�Ͻÿ�.
--> �� ���� ���� �ο��޾ƾߵɰ�!

CREATE OR REPLACE VIEW V_TEAM_PLAYER(������, ������, ���ȣ, ��ID, ����)
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
 WHERE ������ LIKE 'Ȳ%';


--14. ��� ���� ���� '����ȣ' ������ ���� ���ԵǾ���.
--     �ش� ������ ���� �� �������� DF�̸� 1987�� 3�� 16�ϻ�, ����� �����԰� ���� 176cm, 75kg���� 
--     �����ٰ� ���� ��, ����ȣ ������ ����ID�� ���� ������ �� ���� ū ���ڸ� ���� �������� 
--     ���ڸ� �ϳ� �������� �߰��� �� �ִ� ������ �ۼ��Ͻÿ�.
SELECT 
       MAX(PLAYER_ID) + 1
  FROM PLAYER;

SELECT 
       TEAM_ID
  FROM TEAM
 WHERE TEAM_NAME = '�������';

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
  '����ȣ'
, 'DF'
, '87/03/16'
, 176
, 75
, (SELECT MAX(PLAYER_ID) + 1 
     FROM PLAYER)
, (SELECT TEAM_ID 
     FROM TEAM 
    WHERE TEAM_NAME = '�������')
);
COMMIT;

SELECT * FROM PLAYER
ORDER BY 1 DESC;


--15. STADIUM ���̺��� ������ �����Ͽ�, TEAM ���̺��� ADDRESS�� 
--    �ش� ���� Ȩ ������� STADIUM ������ �����ϴ� 
--    ������ �ۼ��Ͻÿ�.
UPDATE TEAM T
   SET ADDRESS = (SELECT STADIUM_NAME
                    FROM STADIUM S
                   WHERE T.STADIUM_ID = S.STADIUM_ID);


--16. SCHEDULE�� ��ϵ� ������ �� ���� ���� ���� ��ϵ� ������ ��¥�� ����� ��, 
--     HOME���� AWAY���� �� ��� �� ���� ����� ���� ���� ��ȸ�Ͻÿ�.
SELECT * FROM SCHEDULE;

SELECT 
       SCHE_DATE �������
     , STADIUM_NAME ������
     , H.TEAM_NAME HOME����
     , HOME_SCORE HOME������
     , A.TEAM_NAME AWAY����
     , AWAY_SCORE AWAY������
  FROM SCHEDULE S
  JOIN STADIUM USING(STADIUM_ID)
  JOIN TEAM H ON (S.HOMETEAM_ID = H.TEAM_ID)
  JOIN TEAM A ON (S.AWAYTEAM_ID = A.TEAM_ID)
 WHERE (HOME_SCORE+AWAY_SCORE) = (SELECT MAX(HOME_SCORE+AWAY_SCORE)
                                    FROM SCHEDULE);
                                 

--17. STADIUM ���̺��� HOMETEAM�� ID�� ������ ���� ���� ������� ��ȸ�Ͽ�
--    'K'�� ���� ��ϵǾ� �ִ� HOMETEAM_ID �ִ밪 + 1 �� ������ ID�� �ο��ϵ�(�����ϵ�),
--    STADIUM_ID ������ �ο��ϴ� �������� �ۼ��Ͻÿ�.
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


--18. ������̳� ��忡 ���� �繫���� ��ġ���� ���� �� ��� ��ȭ��ȣ, FAX��ȣ �� �ּ�,
--     �Ҽӵ� ������ ���� ������ �� ���� ������������ ��ȸ�ϴ� ������ �ۼ��Ͻÿ�.
SELECT 
       TEAM_NAME ����
     , TEL ��ȭ��ȣ
     , FAX �ѽ���ȣ
     , ADDRESS �ּ�
     , (SELECT COUNT(*)
          FROM PLAYER P
         WHERE P.TEAM_ID = T.TEAM_ID) "�ҼӼ��� ��"
  FROM TEAM T
 WHERE ADDRESS NOT LIKE '%�����%' 
   AND ADDRESS NOT LIKE '%���%'
 ORDER BY 5 DESC;


--19. �ֱ� �ѱ� ���������� ������Ȳ�� �� ������ �� ���� �� ���� ������ 3�� ������ ������
--    �����ϰ� �Ǿ���. TEAM ���̺��� Ȱ���Ͽ� ���� �Ҽӵ� ������ 3�� ������ ������ ã��
--    �ش� �����͸� �����ϴ� ������ �ۼ��Ͻÿ�.
DELETE 
  FROM TEAM
 WHERE TEAM_ID IN (SELECT TEAM_ID
                     FROM PLAYER
                    RIGHT JOIN TEAM USING(TEAM_ID)
                    GROUP BY TEAM_ID
                   HAVING COUNT(TEAM_ID) <= 3);
--> PLAYER ���̺��� �ܷ�Ű �������ǿ� ����Ǿ� �����߻��� ��!
--> PLAYER ���̺��� �ܷ�Ű �������ǿ� �ɼ����� ON DELETE CASCADE �߰�����ߵ�

-- ���������� ���� �Ұ�! �����ߴٰ� �ٽ� �ɼ� �߰��ؼ� �������� �߰�������!
ALTER TABLE PLAYER
 DROP CONSTRAINT PLAYER_FK;
ALTER TABLE PLAYER
  ADD CONSTRAINT PLAYER_FK FOREIGN KEY(TEAM_ID) REFERENCES TEAM ON DELETE CASCADE;


--20. �ڽ��� �ۼ��� ������ �� ����� ���ϰ� ������ ������ ���� �긮���� ���ÿ�.