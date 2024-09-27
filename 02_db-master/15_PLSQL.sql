-- PL/SQL (PROCEDURE LANGUAGE EXTENSION TO SQL �Ǵ� PROCEDURE LANGUAGE/SQL)
-- ����Ŭ ��ü�� ���� �� ������ ���
-- SQL�� ������ �����Ͽ� SQL���� ������
-- ������ ����, ����ó��, �ݺ�ó��, ����ó�� ���� �����Ѵ�.
 
BEGIN
  DBMS_OUTPUT.PUT_LINE('HELLO WORLD');
END;
/

-- ����� Ȯ���ϰ� ���� �� �Ʒ� �ڵ带 �ۼ��ؾ� �Ѵ�.(���α׷��� ���� ���� ���� �ٽ� �����ؾ� ��)
SET SERVEROUTPUT ON;

-- ������ �ʱ�ȭ, ���� �� ���
DECLARE
  EMP_ID NUMBER;
  EMP_NAME VARCHAR2(30);
BEGIN
  EMP_ID := 888;
  EMP_NAME := '���峲';
  
  DBMS_OUTPUT.PUT_LINE('EMP_ID: ' || EMP_ID);
  DBMS_OUTPUT.PUT_LINE('EMP_NAME: ' || EMP_NAME);
END;
/

-- %TYPE
-- : ���̺��� �ϳ��� �÷��� �����ϴ� ��� ���
--   ���۷��� ������ ����� �ʱ�ȭ, ������ ���
DECLARE
  EMP_ID EMPLOYEE.EMP_ID%TYPE;
  EMP_NAME EMPLOYEE.EMP_NAME%TYPE;
BEGIN
  SELECT A.EMP_ID
       , A.EMP_NAME
    INTO EMP_ID
       , EMP_NAME
    FROM EMPLOYEE A
   WHERE A.EMP_ID = '&EMP_ID';
   
  DBMS_OUTPUT.PUT_LINE('EMP_ID: ' || EMP_ID); 
  DBMS_OUTPUT.PUT_LINE('EMP_NAME: ' || EMP_NAME);
END;
/

-- ���۷��� ������ EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE,
-- SALARY�� �����ϰ�, EMPLOYEE ���̺���
-- ���, �̸�, �����ڵ�, �μ��ڵ�, �޿��� ��ȸ�Ͽ�
-- ������ ���۷��� ������ ��� ����Ͻÿ�.
-- ��, �Է¹��� �̸��� ��ġ�ϴ� ������ ������ ��ȸ�Ͻÿ�.
DECLARE
  EMP_ID EMPLOYEE.EMP_ID%TYPE;
  EMP_NAME EMPLOYEE.EMP_NAME%TYPE;
  DEPT_CODE EMPLOYEE.DEPT_CODE%TYPE;
  JOB_CODE EMPLOYEE.JOB_CODE%TYPE;
  SALARY EMPLOYEE.SALARY%TYPE;
BEGIN
  SELECT
         A.EMP_ID
       , A.EMP_NAME
       , A.DEPT_CODE
       , A.JOB_CODE
       , A.SALARY
    INTO EMP_ID
       , EMP_NAME
       , DEPT_CODE
       , JOB_CODE
       , SALARY
    FROM EMPLOYEE A
   WHERE A.EMP_NAME = '&EMP_NAME';
    
  DBMS_OUTPUT.PUT_LINE('EMP_ID: ' || EMP_ID);
  DBMS_OUTPUT.PUT_LINE('EMP_NAME: ' || EMP_NAME);
  DBMS_OUTPUT.PUT_LINE('DEPT_CODE: ' || DEPT_CODE);
  DBMS_OUTPUT.PUT_LINE('JOB_CODE: ' || JOB_CODE);
  DBMS_OUTPUT.PUT_LINE('SALARY: ' || SALARY);
END;
/

-- %ROWTYPE
-- : ���̺��� ��� �÷��� �÷��� �ڷ����� �����ϴ� ��� ���
DECLARE
  EMP EMPLOYEE%ROWTYPE;
BEGIN
  SELECT A.*
    INTO EMP
    FROM EMPLOYEE A
   WHERE A.EMP_ID = '&���';
   
  DBMS_OUTPUT.PUT_LINE('EMP_ID: ' || EMP.EMP_ID);
  DBMS_OUTPUT.PUT_LINE('EMP_NAME: ' || EMP.EMP_NAME);
  DBMS_OUTPUT.PUT_LINE('DEPT_CODE: ' || EMP.DEPT_CODE);
  DBMS_OUTPUT.PUT_LINE('JOB_CODE: ' || EMP.JOB_CODE);
  DBMS_OUTPUT.PUT_LINE('SALARY: ' || EMP.SALARY);  
END;
/

-- IF��(���ǹ�)
-- ������ ���ϴ� PL/SQL�� �ۼ�
DECLARE
  EMP EMPLOYEE%ROWTYPE;
  YSALARY NUMBER;
BEGIN
  SELECT A.*
    INTO EMP
    FROM EMPLOYEE A
   WHERE A.EMP_NAME = '&�����';
   
  IF(EMP.BONUS IS NULL) THEN YSALARY := EMP.SALARY * 12;
  ELSIF(EMP.BONUS IS NOT NULL) THEN YSALARY := (EMP.SALARY * (1 + EMP.BONUS) * 12);
  END IF;
  
  DBMS_OUTPUT.PUT_LINE(EMP.EMP_ID || '      ' || EMP.EMP_NAME || '      '
                       || TO_CHAR(YSALARY, 'L999,999,999'));
END;
/

-- ������ �Է� �޾� SCORE ������ �����ϰ�
-- 90�� �̻��� 'A', 80�� �̻��� 'B', 70�� �̻��� 'C',
-- 60�� �̻��� 'D', 60�� �̸��� 'F'�� ���� ó���Ͽ�
-- GRADE ������ �����Ͽ�
-- '����� ������ 90���̰�, ������ A�����Դϴ�' ���·� ����Ͻÿ�.
DECLARE
  SCORE NUMBER;
  GRADE VARCHAR2(1);
BEGIN
  SCORE := '&����';
  
  IF SCORE >= 90 THEN GRADE := 'A';
  ELSIF SCORE >= 80 THEN GRADE := 'B';
  ELSIF SCORE >= 70 THEN GRADE := 'C';
  ELSIF SCORE >= 60 THEN GRADE := 'D';
  ELSE GRADE := 'F';
  END IF;
  
  DBMS_OUTPUT.PUT_LINE('����� ������ ' || SCORE
                       || '���̰�, ������ ' || GRADE
                       || '�����Դϴ�.');
END;
/

-- ����� �Է��ϸ� �ش� ����� �μ����� ���� �� �ֵ��� �غ���.(JOIN ��� ���ϰ� IF�� ���)
DECLARE
  VEMPNO EMPLOYEE.EMP_ID%TYPE;
  VENAME EMPLOYEE.EMP_NAME%TYPE;
  VDEPTNO EMPLOYEE.DEPT_CODE%TYPE;
  VDNAME VARCHAR2(20) := NULL;
BEGIN
  SELECT A.EMP_ID
       , A.EMP_NAME
       , A.DEPT_CODE
    INTO VEMPNO
       , VENAME
       , VDEPTNO
    FROM EMPLOYEE A
   WHERE A.EMP_ID = '&���'; 
   
 IF(VDEPTNO = 'D1') THEN VDNAME := '�λ������';
 END IF;
 IF(VDEPTNO = 'D2') THEN VDNAME := 'ȸ�������';
 END IF;
 IF(VDEPTNO = 'D3') THEN VDNAME := '�����ú�';
 END IF;
 IF(VDEPTNO = 'D4') THEN VDNAME := '����������';
 END IF;
 IF(VDEPTNO = 'D5') THEN VDNAME := '�ؿܿ���1��';
 END IF;
 IF(VDEPTNO = 'D6') THEN VDNAME := '�ؿܿ���2��';
 END IF;
 IF(VDEPTNO = 'D7') THEN VDNAME := '�ؿܿ���3��';
 END IF;
 IF(VDEPTNO = 'D8') THEN VDNAME := '���������';
 END IF;
 IF(VDEPTNO = 'D9') THEN VDNAME := '�ѹ���';
 END IF;
 IF(VDEPTNO IS NULL) THEN VDNAME := '�μ�����';
 END IF; 
 
 DBMS_OUTPUT.PUT_LINE('���    �̸�     �μ���');
 DBMS_OUTPUT.PUT_LINE('---------------------');
 DBMS_OUTPUT.PUT_LINE(VEMPNO || '     ' || VENAME || '     '
                      || VDNAME);
 
END;
/

-- CASE��
DECLARE
  VEMPNO EMPLOYEE.EMP_ID%TYPE;
  VENAME EMPLOYEE.EMP_NAME%TYPE;
  VDEPTNO EMPLOYEE.DEPT_CODE%TYPE;
  VDNAME VARCHAR2(20) := NULL;
BEGIN
  SELECT A.EMP_ID
       , A.EMP_NAME
       , A.DEPT_CODE
    INTO VEMPNO
       , VENAME
       , VDEPTNO
    FROM EMPLOYEE A
   WHERE A.EMP_ID = '&���'; 
   
  VDNAME := CASE VDEPTNO
              WHEN 'D1' THEN '�λ������'
              WHEN 'D2' THEN 'ȸ�������'
              WHEN 'D3' THEN '�����ú�'
              WHEN 'D4' THEN '����������'
              WHEN 'D5' THEN '�ؿܿ���1��'
              WHEN 'D6' THEN '�ؿܿ���2��'
              WHEN 'D7' THEN '�ؿܿ���3��'
              WHEN 'D8' THEN '���������'
              WHEN 'D9' THEN '�Ѹ���'
              ELSE '�μ�����'
            END;
 
 DBMS_OUTPUT.PUT_LINE('���    �̸�     �μ���');
 DBMS_OUTPUT.PUT_LINE('---------------------');
 DBMS_OUTPUT.PUT_LINE(VEMPNO || '     ' || VENAME || '     '
                      || VDNAME);
END;
/

-- LOOP��(�ڹ��� WHILE��)
DECLARE
  N NUMBER := 1;
BEGIN
  LOOP
    DBMS_OUTPUT.PUT_LINE(N);
    N := N + 1;
    IF N > 5 THEN EXIT;
    END IF;
  END LOOP;
END;
/

-- FOR IN��(�ڹ��� FOR�� ���� ��)
CREATE TABLE TEST1(
  BUNHO NUMBER,
  NALJJA DATE
);

BEGIN
  FOR I IN 1..10
    LOOP
      INSERT
        INTO TEST1
        (
          BUNHO
        , NALJJA
        )
        VALUES
        (
          I
        , SYSDATE + I
        );
  END LOOP;
END;
/

SELECT * FROM TEST1;

COMMIT;

-- ������ ¦���� ����ϱ�
DECLARE
  RESULT NUMBER;
BEGIN
  FOR DAN IN 2..9
    LOOP
      IF MOD(DAN, 2) = 0
        THEN FOR SU IN 1..9
               LOOP
                 RESULT := DAN * SU;
                 DBMS_OUTPUT.PUT_LINE(DAN || ' * ' || SU || ' = ' || RESULT); 
             END LOOP;
             DBMS_OUTPUT.PUT_LINE('');
      END IF;
  END LOOP;
END;
/

-- ���̺� Ÿ���� ����(������ �迭) ���� �� �� ���� ���
DECLARE
  TYPE EMP_ID_TABLE_TYPE IS TABLE OF EMPLOYEE.EMP_ID%TYPE
  INDEX BY BINARY_INTEGER;
  TYPE EMP_NAME_TABLE_TYPE IS TABLE OF EMPLOYEE.EMP_NAME%TYPE
  INDEX BY BINARY_INTEGER;
  
  EMP_ID_TABLE EMP_ID_TABLE_TYPE;
  EMP_NAME_TABLE EMP_NAME_TABLE_TYPE;
  
  I BINARY_INTEGER := 0;
BEGIN
  I := 1;               -- PL/SQL������ �迭�� �ε��� ü�谡 1������ �����Ѵ�.
  FOR K IN (SELECT EMP_ID, EMP_NAME FROM EMPLOYEE)
    LOOP
      EMP_ID_TABLE(I) := K.EMP_ID;
      EMP_NAME_TABLE(I) := K.EMP_NAME;
      
      I := I + 1;
  END LOOP;
  
  FOR J IN 1..(I - 1)
    LOOP
      DBMS_OUTPUT.PUT_LINE('EMP_ID: ' || EMP_ID_TABLE(J)
                           || ', EMP_NAME: ' || EMP_NAME_TABLE(J));              
  END LOOP;
END;
/

-- ���ڵ� Ÿ���� ���� ���� �� �� ���� �� ���
DECLARE
  TYPE EMP_RECORD_TYPE IS RECORD(
    EMP_ID EMPLOYEE.EMP_ID%TYPE,
    EMP_NAME EMPLOYEE.EMP_NAME%TYPE,
    DEPT_TITLE DEPARTMENT.DEPT_TITLE%TYPE,
    JOB_NAME JOB.JOB_NAME%TYPE
  );
  
  EMP_RECORD EMP_RECORD_TYPE;
BEGIN
  SELECT A.EMP_ID
       , A.EMP_NAME
       , B.DEPT_TITLE
       , C.JOB_NAME
    INTO EMP_RECORD
    FROM EMPLOYEE A
    LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
    LEFT JOIN JOB C ON (A.JOB_CODE = C.JOB_CODE)
   WHERE A.EMP_ID = '&���' ;
   
  DBMS_OUTPUT.PUT_LINE('���: ' || EMP_RECORD.EMP_ID);
  DBMS_OUTPUT.PUT_LINE('�̸�: ' || EMP_RECORD.EMP_NAME);
  DBMS_OUTPUT.PUT_LINE('�μ�: ' || EMP_RECORD.DEPT_TITLE);
  DBMS_OUTPUT.PUT_LINE('����: ' || EMP_RECORD.JOB_NAME);
END;
/

-- ����ó��
-- ������ ����Ŭ�� ���� �Ǿ� �ִ� ���� ó��
BEGIN
  UPDATE EMPLOYEE A
     SET A.EMP_ID = '&���'
   WHERE A.EMP_ID = 202;
EXCEPTION
  WHEN DUP_VAL_ON_INDEX THEN
  DBMS_OUTPUT.PUT_LINE('�̹� �����ϴ� ����Դϴ�.');
END;
/

SELECT * FROM EMPLOYEE;  -- ���ܰ� �߻��ϸ� BEGIN�� ������ ����� ���� �ƴϴ�.

-- ���ǵ��� ���� ����ó��
-- PRAGMA EXCEPTION_INIT�������� ���� �̸��� ����Ŭ ���� ��ȣ ����
DECLARE
  DUP_EMPNO EXCEPTION;
  PRAGMA EXCEPTION_INIT(DUP_EMPNO, -00001);
BEGIN
  UPDATE EMPLOYEE A
     SET A.EMP_ID = '&���'
   WHERE A.EMP_ID = 202;
EXCEPTION
  WHEN DUP_EMPNO THEN
  DBMS_OUTPUT.PUT_LINE('�̹� �����ϴ� ����Դϴ�.');
END;
/

--���ܸ�	���� �ڵ�	����
--ACCESS_INTO_NULL	ORA-06530	LOB�� ���� ��ü �ʱ�ȭ ���� ���� ���¿��� ���
--CASE_NOT_FOUND	ORA-06592	CASE�� ���� ���� ����
--CURSOR_ALREADY_OPEN	ORA-06511	Ŀ���� �̹� OPEN�� �����ε� OPEN �Ϸ��� �õ�
--DUP_VAL_ON_INDEX	ORA-00001	���� �ε����� �ִ� �÷��� �ߺ������� INSERT, UPDATE ����
--INVALID_CURSOR	ORA-01001	�������� �ʴ� Ŀ���� ����
--INVALID_NUMBER	ORA-01722	���ڸ� ���ڷ� ��ȯ�� �� ������ ���
--LOGIN_DENIED	ORA-01017	�߸��� ����� �̸��̳� ��й�ȣ�� �α����� �õ�
--NO_DATA_FOUND	ORA-01403	SELECT INTO �� �����Ͱ� �� �ǵ� ���� ���
--NOT_LOGGED_ON	ORA-01012	�α׿µ��� �ʾҴµ� DB�� ������ ��
--PROGRAM_ERROR	ORA-06501	PL/SQL �ڵ�󿡼� ���� ������ ������ ��, �� ������ �߻��ϸ� ������Ŭ�� ����(Contact Oracle Support)���� �޽����� ��µ�
--STORAGE_ERROR	ORA-06500	���α׷� ���� �� �޸𸮰� ������ ���
--TIMEOUT_ON_RESOURCE	ORA-00051	�����ͺ��̽� �ڿ��� ��ٸ��� ���� Ÿ�Ӿƿ� �߻� ��
--TOO_MANY_ROWS	ORA-01422	SELECT INTO �� ����� �� ����� �� �ο� �̻��� ��
--VALUE_ERROR	ORA-06502	��ġ �Ǵ� �� ����
--ZERO_DIVIDE	ORA-01476	0���� ���� ��





