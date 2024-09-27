-- �ε���(INDEX)
-- : SQL ��ɹ��� �˻� ó�� �ӵ��� ����Ű�� ����
--   �÷��� ���ؼ� �����ϴ� ����Ŭ ��ü�̴�.
--   
--   �ϵ� ��ũ�� ��� ��ġ������ ���� ������ ���� �ּҷ�
--   DATA - ROWID�� ����
--   
--   �ε����� ���� ������ B*Ʈ�� �������� �����Ǿ� �ְ�
--   �ε����� �����ϱ� ���ؼ��� �ð��� �ʿ��ϴ�.
--   ���� �ε����� ���� �߰� ��������� �ʿ��ϱ� ������ �ݵ�� ���� ���� �ƴϴ�.
--   => �ε����� ������ �÷����� DML �۾��� ����� ��� ó�� �ӵ��� ��������.
--   ���� �Ϲ������� ���̺� ��ü �ο� ���� 15% ������ �����͸� ��ȸ�� �� �ε����� Ȱ���Ѵ�.
   
-- ����
-- �˻� �ӵ��� ������
-- �ý��ۿ� �ɸ��� ���ϸ� �ٿ��� �ý��� ��ü�� ������ ����Ŵ
 
-- ����
-- �ε����� ���� �߰� ��������� �ʿ���
-- �ε����� �����ϴµ� �ð��� �ɸ�
-- �������� �����۾�(INSERT/UPDATE/DELETE)�� ���� �Ͼ�� ���
-- REBUILD �۾��� �ֱ������� �� ��� �ǰ�, REBUILD�� ���� �� ���� ������
-- ������ ������ ���ϵȴ�.
 
-- �ε����� �����ϴ� ������ ��ųʸ� ��
SELECT
       A.*
  FROM USER_IND_COLUMNS A;
  
-- ROWID ����: ������Ʈ ��ȣ, ��� ���� ��ȣ, ��� ��ȣ, ������ ��ȣ
SELECT
       ROWID
     , A.EMP_ID
     , A.EMP_NAME
  FROM EMPLOYEE A;
  
-- �ε��� ����
-- 1. ���� �ε���(UNIQUE INDEX)
-- 2. ����� �ε���(NONUNIQUE INDEX)
-- 3. ���� �ε���(SINGLE INDEX)
-- 4. ���� �ε���(COMPOSITE INDEX)
-- 5. �Լ���� �ε���(FUNCTION BASED INDEX)
 
-- UNIQUE INDEX
-- UNIQUE INDEX�� ���� �� �÷����� �ߺ� ���� ���Ե� �� ����
-- ����Ŭ PRIMARY KEY ���������� �����ϸ�
-- �ڵ����� �ش� �÷��� UNIQUE INDEX�� ���� ��
-- PRIMARY KEY�� �̿��Ͽ� ACCESS�� ��쿡�� ���� ����� ȿ���� ����(ī��θ�Ƽ�� ���� �÷����� �˻��ϱ� ����)
 
-- �ε��� ������(INDEX REBUILD)
ALTER INDEX ����Ƽ1_PK REBUILD;

-- �ε��� ��Ʈ
-- �Ϲ������δ� ��Ƽ�������� ������ �ε����� Ÿ�ų� Ǯ ��ĳ���� �ؼ� ����� ���� ��� ȿ������ ������� �˻���
-- ������ �츮�� ���ϴ� ���̺� �ִ� �ε����� ����� �� �ֵ��� ���ִ� ����(��Ʈ)�� ���� ���� ����
-- SELECT�� ù �ٿ� ��Ʈ �ּ�(/*+ ���� */)�� �ۼ��Ͽ� ������ �ε����� �ο��� �� �ִ�.
-- �ּ��� '+'�� �ݵ�� ���̰� /*+ ������ �����̽��� �ݵ�� ��� ��
 
-- ��Ƽ������: SQL�� ���� ������ �����ȹ�� �����ϴ� �˰����̴�.
--ALTER INDEX ����Ƽ1_PK RENAME TO ENTITY1_PK;  -- �ε��� �̸��� �ٲٱ� ���� ����

-- �ε����� ������������ �����Ǿ INDEX_DESC�� �ؾ� ���������� ������ ���� �����ͺ��� ������� �������� ���� �ȴ�.
SELECT /*+ INDEX_DESC(EMPLOYEE ����Ƽ1_PK) */
--SELECT /*+ INDEX(A@SEL$1 ����Ƽ1_PK) */
       A.*
  FROM EMPLOYEE A;
  
SELECT /*+ INDEX(A@SEL$1 ����Ƽ1_PK) */
       A.*
  FROM EMPLOYEE A
 WHERE A.EMP_ID > '0'; -- WHERE ���� ���� �ε����� �޸� �÷����� ��ȸ
 
-- ������̸鼭 ���� �ε��� ����
CREATE INDEX IDX_EMPNO
ON EMPLOYEE(EMP_NAME);

-- �ߺ����� �ִ� �÷����� ���� �ε����� ������ �� ����.
CREATE UNIQUE INDEX IDX_DEPTCODE
ON EMPLOYEE(DEPT_CODE);

-- EMPLOYEE ���̺��� EMP_NO�� �޸� �ε����� ��ųʸ� ��� Ȯ���ϰ� ��������.
SELECT
       A.*
  FROM USER_IND_COLUMNS A;
 
-- �ε��� ����͸�
-- ������� �ε����� ��ȸ �� ��� �Ǿ����� Ȯ��
CREATE TABLE EMP001
AS
SELECT *
  FROM EMPLOYEE;  -- PK�� UNIQUE ���������� ���� ���̺� �����(�ڵ����� �ε����� �����Ǿ� ���� ����)
  
-- EMP001 ���̺��� EMP_ID�÷��� ���� UNIQUE INDEX �����: IDX_EID
CREATE UNIQUE INDEX IDX_EID
    ON EMP001(EMP_ID);
  
SELECT
       *
  FROM USER_INDEXES
 WHERE TABLE_NAME = 'EMP001';
 
-- ����͸� �� �ε��� ����
ALTER INDEX IDX_EID MONITORING USAGE;
 
-- �ε��� ����͸�
SELECT INDEX_NAME, TABLE_NAME, MONITORING, USED, START_MONITORING, END_MONITORING
  FROM V$OBJECT_USAGE;

-- V$OBJECT_USAGE: �ε��� Ȱ��� ���õ� �����͸� �����ϴ� ��
-- USED�÷�: ����͸� ���� �� �ش� �ε����� ��� �Ǿ����� Ȯ��

-- (1)�ε��� Ȱ�� X ��ü ��ȸ
SELECT
       *
  FROM EMP001;

SELECT INDEX_NAME, TABLE_NAME, MONITORING, USED, START_MONITORING, END_MONITORING
  FROM V$OBJECT_USAGE;
 
-- (2)�ε��� Ȱ�� O ��ü ��ȸ
SELECT /*+ INDEX_DESC(A IDX_EID) */
       A.*
  FROM EMP001 A;
 
SELECT INDEX_NAME, TABLE_NAME, MONITORING, USED, START_MONITORING, END_MONITORING
  FROM V$OBJECT_USAGE;
 
-- �ε��� ����͸� ����
ALTER INDEX IDX_EID NOMONITORING USAGE;

-- ���� �ε��� ���� ���
CREATE UNIQUE INDEX IDX_EID2
    ON EMP001(EMAIL, EMP_NAME); -- ���� �ε����� ī��θ�Ƽ�� ��������� ���� �÷��� ���� ��� �Ѵ�.
 





 