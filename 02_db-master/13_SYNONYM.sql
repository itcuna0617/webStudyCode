-- ���Ǿ�(SYNONYM)
-- �ٸ� �����ͺ��̽��� ���� ��ü�� ���� ���� Ȥ�� ���Ӹ�
-- ���� ����ڰ� ���̺��� ���� �� ���
-- �ٸ� ����ڰ� ���̺� ������ ��� '����ڸ�.���̺��'���� ǥ����
-- ���Ǿ ����ϸ� �����ϰ� ����� �� �ִ�.
 
-- ���� ���
-- CREATE SYNONYM ���Ӹ� FOR [����ڸ�.]��ü��;

-- <SYNONYM ��ü�� �����ϱ� ���� ������ �������� ���� �� ����> 
-- GRANT CREATE SYNONYM TO C##EMPLOYEE;
 
CREATE SYNONYM EMP FOR EMPLOYEE;

SELECT
       *
  FROM EMP;
  
SELECT
       *
  FROM EMPLOYEE;
  
-- ���Ǿ� ����
-- 1. ����� ���Ǿ�
--    : ��ü�� ���� ���� ������ �ο����� ����ڰ� ������ ���Ǿ�
-- 2. ���� ���Ǿ�
--    : ��� ������ �ִ� �����(DBA)�� ������ ���Ǿ�
--      ��� ����ڰ� ����� �� ����(PUBLIC)

-- <������ �������� ���� ���Ǿ� ������ ���� ������ ����>
--SELECT
--       A.*
--  FROM C##EMPLOYEE.DEPARTMENT A;   -- ������ ������ �Ϲ� �������� ���̺� ������ �����ϴ�.
--  
--CREATE PUBLIC SYNONYM DEPT FOR C##EMPLOYEE.DEPARTMENT;

SELECT
       A.*
  FROM DEPT A;


