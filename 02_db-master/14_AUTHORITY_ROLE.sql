-- �ý��� �������� �����ϱ�
-- <����� ����(������ ������ ����)>
-- : ������� ������ ��ȣ����, ���Ѻο�

-- ����Ŭ �����ͺ��̽��� ��ġ�ϸ� �⺻������ �����Ǵ� ����
-- 1. SYS(������ ����)
-- 2. SYSTEM(������ ����)

-- 1. �ý��� ����: �����ͺ��̽� �����ڰ� ������ �ִ� ��������
--               ����Ŭ ����, ���̺�, ��, �ε��� ���� ���� ����
--               CREATE USER(����� ���� �����)
--               DROP USER(����� ���� ����)
--               DROP ANY TABLE(������ ���̺� ����)
--               QUERY REWRITE(�Լ� ��� �ε��� ���� ����)
--               BACKUP ANY TABLE(���̺� ���)
               
--    �ý��� �����ڰ� ����ڿ��� �ο��ϴ� ����
--               CREATE SESSION(�����ͺ��̽��� ����)
--               CREATE TABLE(���̺� ����)
--               CREATE VIEW(�� ����)
--               CREATE SEQUENCE(������ ����)
--               CREATE SYNONYM(���Ǿ� ����)
--               CREATE PROCEDURE(���ν��� ����)

-- <SYSTEM �������� ���� ����>
CREATE USER C##SAMPLE IDENTIFIED BY SAMPLE;
GRANT CREATE SESSION, CREATE TABLE TO C##SAMPLE;

-- ������ SAMPLE �������� ������ ����.
-- ���� �� ���̺� ����
 CREATE TABLE TEST_TABLE(
   COL1 VARCHAR2(20),
   COL2 NUMBER
 );

-- WITH ADMIN OPTION
-- : ����ڿ��� �ý��� ������ �ο��� �� �����
--   ������ �ο� ���� ����ڴ� �ٸ� ����ڿ��� �ش� ������ ������ �� ����
-- <SYSTEM �������� ���� �ο�>
GRANT CREATE SESSION TO C##SAMPLE
WITH ADMIN OPTION;

-- <SYSTEM �������� SAMPLE2 ���� �����ϱ�>
CREATE USER C##SAMPLE2 IDENTIFIED BY SAMPLE2;

-- <SAMPLE �������� SAMPLE2 �������� ���� �ο� �������� Ȯ������>
GRANT CREATE SESSION TO C##SAMPLE2
WITH ADMIN OPTION;

-- 2. ��ü ����: ����ڰ� Ư�� ��ü(���̺�, ��, ������, �Լ�)�� �����ϰų� ������ �� �ִ� ����
--              DML(SELECT/INSERT/UPDATE/DELETE)
--              GRANT ��������[(�÷���)] | ALL
--              ON ��ü�� | ROLE �̸� | PUBLIC
--              TO ����� �̸�;
--
-- ��ü�� DML ó���ϴ� ���� ����
--              ALTER TABLE, SEQUENCE
--              DELETE TABLE, VIEW
--              EXECUTE PROCEDURE
--              INDEX TABLE
--              REFERENCES TABLE
--              INSERT TABLE, VIEW
--              SELECT TABLE, VIEW, SEQUENCE
--              UPDATE TABLE, VIEW

-- WITH GRANT OPTION
-- : ����ڰ� Ư�� ��ü�� �����ϰų� ���� �� �� �ִ� ������ �ο� �����鼭
--   �� ������ �ٸ� ����ڿ��� �ٽ� �ο��� �� �ִ� �ɼ�

-- <SYSTEM �������� �ϱ�>
 GRANT SELECT ON C##EMPLOYEE.EMPLOYEE TO C##SAMPLE
 WITH GRANT OPTION;

-- <SAMPLE �������� �ϱ�>
 SELECT * FROM C##EMPLOYEE.EMPLOYEE;

-- SAMPLE ������ SAMPLE2�������� ���� �ο� �������� Ȯ��
 GRANT SELECT ON C##EMPLOYEE.EMPLOYEE TO C##SAMPLE2
 WITH GRANT OPTION;
 
-- ���� öȸ
-- : REVOKE ��ɾ� ���
-- <SYSTEM �������� �ϱ�>
 REVOKE SELECT ON C##EMPLOYEE.EMPLOYEE FROM C##SAMPLE;
 
-- ����(WITH GRANT OPTION�� WITH ADMIN OPTION�� ������)
-- WITH GRANT OPTION�� REVOKE �� �ٸ� ����ڿ��Ե� �ο��ߴ� ������ ��� ���� ȸ��
-- WITH ADMIN OPTION�� Ư�� ������� ���Ѹ� ȸ���� �ǰ� ������ �ٸ� ����ڿ��� �ο���
-- ������ ȸ���� ���� ����
 
-- ��(ROLE)
-- : ����ڿ��� ���� �����ϰ� ������ �ο��� �� �ֵ���
--   ���� ���� ������ ���� ���� ��
--   => ����� ���� ������ ���� �����ϰ� ȿ�������� �� �� �ְ� ��
--      �ټ��� ����ڿ��� ���������� �ʿ��� ���ѵ��� �ϳ��� �ѷ� ����
--      ����ڿ��Դ� Ư�� �ѿ� ���� ������ �ο��� �� �ֵ��� ��
--      
--      ����ڿ��� �ο��� ������ �����ϰ��� �� ����
--      �Ѹ� �����ϸ� �׷ѿ� ���� ������ �ο����� ����ڵ��� ������
--      �ڵ����� �����ȴ�.
--      ���� Ȱ��ȭ �ϰų� ��Ȱ��ȭ �ؼ� �Ͻ������� ������ �ο��ϰ�
--      öȸ�� �� ����
      
-- ���� ����
-- 1. ���� ���ǵ� ��
-- : ����Ŭ ��ġ �� �ý��ۿ��� �⺻������ ������
--   CONNECT ROLE
--   RESOURCE ROLE
-- 
-- 2. ����ڰ� �����ϴ� ��
-- : CREATE ROLE ������� �� ����
-- �� ������ �ݵ�� DBA������ �ִ� �����(SYS, SYSTEM)�� �� �� ����
-- 18C������ �� �̸��� �ݵ�� C##�� �ٿ��� �Ѵ�.
-- CREATE ROLE ���̸�;       -- 1. �� ����
-- GRANT �������� TO ���̸�; -- 2. ���� �� �ѿ� ���ѵ� �߰�
-- GRANT ���̸� TO ������;   -- 3. ����ڿ��� ������� �� �ο�

-- <SYSTEM �������� �ϱ�>
 CREATE ROLE C##MYROLE;

 GRANT CREATE VIEW, CREATE SEQUENCE TO C##MYROLE;
 REVOKE CREATE SEQUENCE FROM C##MYROLE;
 GRANT C##MYROLE TO C##SAMPLE;
   
-- SAMPLE ������ ROLE�� �ο� ������ �ݵ��!! ���� ���� �ߴٰ� �ٽ� �����ؾ� ROLE�� ���ѵ��� ����ȴ�.
   
 
 
 
 
 
 
 
 