-- ���̺� ����,������,
--����Ʈ,�μ�Ʈ,������Ʈ,����,
--����¡,
grant connect, resource to spring;

GRANT CREATE SESSION TO spring;

GRANT CREATE TABLE, RESOURCE TO spring;

SELECT * FROM v$resource_limit WHERE resource_name IN ('processes','sessions');

ALTER SYSTEM SET processes = 200 scope=spfile;

delete tbl_attach where attachNo=1 and uuid='uuid';
--�׿�, pk���� �ܺ�Ű���� �÷�����
-- ���̺�� : tbl_board

select * from tbl_board where bno=3;

select count(*) from tbl_board;
select count(*) from tbl_reply;

insert into tbl_board
(select seq_tbl_board.nextval, title, content, writer, sysdate, sysdate from tbl_board);

select * from tbl_board;

alter table tbl_board add (replycnt number(10));

--ROW_NUMBER()�Լ� ����Ͽ� ����¡ó��
select * from
(select ROW_NUMBER() OVER (ORDER BY bno desc) row_num, tbl.*
from tbl_board tbl)
where row_num between 11 and 20
;

-- ÷������ ���̺� ����
create table tbl_attach ( 
  attachno number(10) not null,
  uuid varchar2(100) not null,
  uploadPath varchar2(200) not null,
  fileName varchar2(100) not null, 
  filetype char(1) ,
  regdate date
);
-- ������ ����
create sequence seq_attach;
-- PK ����
alter table tbl_attach add constraint pk_attach primary key (uuid);
-- 1�� ����
insert into tbl_attach values (SEQ_ATTACH.nextval, 'uuid', 'uploadPath', 'filename', 'N', sysdate);
-- board ���̺� ÷������ �÷� ����
alter table tbl_board add (attachNo number(10));
--������ ã��
select SEQ_ATTACH.nextval from dual;


select attachNo, uuid, uploadPath, fileName, fileType as image
--uploadpath : ��/��/�� ���
--uuid_fileName : ����� ���� �̸� , || = +
--s_uuid_fileName : ������� ����� ���� �̸�
,uploadpath || uuid || '_' || fileName as savePath
,uploadpath || 's_' || uuid || '_' || fileName as s_savePath
from tbl_attach
where attachNo = 1;


--��¥�� ������ ���ְ� row_number�� �̿��� ���������(����¡ó��)
select  rn, rno, bno, reply, replyer, replydate,
        CASE
            WHEN to_char(sysdate, 'yyyymmdd') = to_char(updatedate, 'yyyymmdd') THEN
                to_char(updatedate, 'hh:mi:ss')
            ELSE
                to_char(updatedate, 'yyyy/mm/dd')
        END updatedate from
--rownumber rn���ϱ�
    (SELECT
    ROW_NUMBER() OVER (ORDER BY rno desc) rn, tbl_reply.*
    FROM
        tbl_reply where bno =2
        order by rno desc)
where rn between 1 and 10;

-- ����¡ ó��
select * from
(select rownum rn, bno from tbl_board order by bno desc)
where rn between (������*�������� �Խù���)-1 and ��������ȣ * �������� �Խù���;

--����¡ ó��2
select * from
(SELECT ROW_NUMBER() OVER(ORDER BY a.bno desc) row_num
     , a.*
  FROM tbl_board a)
where row_num between 11 and 20;



select * from test;
create table test (no number(3));
insert into test values(1);
 
select rownum ,no from (select no from test
order by no desc)where rownum between 6 and 10;

select rownum, no from test order by no desc;

update tbl_board
 		set title='���������׽�Ʈ', content='������', writer='�ۼ���', updatedate=sysdate 
 		where bno=3;


insert into tbl_board
values(SEQ_TBL_BOARD.nextval,'����','����','�ۼ���',sysdate,sysdate);

insert into tbl_board
(select SEQ_TBL_BOARD.nextval, title, content, writer, regdate, updatedate
from tbl_board);

--���� ���̺�
CREATE table tbl_reply(
rno NUMBER(10,0),
bno number(10,0) NOT NULL,
reply varchar2(1000 byte) NOT NULL,
replyer varchar2(50 byte) NOT NULL,
replydate date DEFAULT sysdate,
updatedate date DEFAULT sysdate
);
CREATE SEQUENCE seq_reply;
alter table tbl_reply add constraint pk_reply primary key (rno);
alter table tbl_reply add constraint fk_reply_board
foreign key(bno) references tbl_board (bno);

--constraint fk_ foreign key (bno)
--references tbl_reply
 		select rno, bno, reply, replyer, replydate,
 		   case
		     when to_char(sysdate, 'yyyymmdd') = to_char(updatedate,'yyyymmdd')
		     then to_char(updatedate, 'hh:mi:ss')
		     else to_char(updatedate, 'yyyy/mm/dd')
		   end updatedate
 		from tbl_reply where rno=2;

 		select rno, bno, reply, replyer, replydate,
 		   case
		     when to_char(sysdate, 'yyyymmdd') = to_char(updatedate,'yyyymmdd')
		     then to_char(updatedate, 'hh:mi:ss')
		     else to_char(updatedate, 'yyyy/mm/dd')
		   end updatedate 
 		from tbl_reply where bno=2
 		order by rno desc;

select
    case
     when to_char(sysdate, 'yyymmdd') = to_char(updatedate,'yyyymmdd')
     then to_char(updatedate, 'yyyy/mm/dd')
     else to_char(updatedate, 'hh:mi:ss')
    end updatedate
from tbl_reply;
insert into tbl_reply;
select * from tbl_reply;
select seq_reply.nextval, bno, reply, relyer, sysdate, sysdate
from tbl_reply;



select * from tbl_board where bno=2;

insert into tbl_reply values
(seq_reply.nextval, 2, '������', 'ȫ�浿', sysdate, sysdate);

insert into tbl_reply
(select seq_reply.nextval, bno, reply, replyer, replydate, updatedate
from tbl_reply);

delete tbl_board where bno=2;

delete tbl_reply where rno=3;

update tbl_reply set reply='test', replyer='ȫ�浿', updatedate=sysdate where rno=2;

select * from tbl_reply where rno=2;

select * from tbl_reply;

CREATE table tbl_board(
bno NUMBER(10) primary key,
title varchar2(200 byte) NOT NULL,
content varchar2(2000 byte) NOT NULL,
writer varchar2(50 byte) NOT NULL,
regdate date DEFAULT sysdate,
updatedate date);

COMMENT ON TABLE spring.tbl_board is '�Խ���';
COMMENT ON COLUMN spring.tbl_board.bno is '��ȣ';
COMMENT ON COLUMN spring.tbl_board.title is '����';
COMMENT ON COLUMN spring.tbl_board.content is '����';
COMMENT ON COLUMN spring.tbl_board.writer is '�ۼ���';
COMMENT ON COLUMN spring.tbl_board.regdate is '�ۼ���';
COMMENT ON COLUMN spring.tbl_board.updatedate is '������Ʈ��';

-- ������

create SEQUENCE seq_tbl_board;

select seq_tbl_board.nextval from dual;
select seq_tbl_board.currval from dual;

-- �������� : seq_tbl_board

-- ����� ����
create user spring2 IDENTIFIED by spring2;

grant connect, resource, DBA to spring2;

CREATE TABLE tbl_board(
bno number(10,0) PRIMARY key,
title varchar2(200) not null,
content varchar2(2000) not null,
writer varchar2(50) not null,
regdate date DEFAULT sysdate,
updatedate date);

create table tbl_reply(
	rno number(10,0),
	bno number(10,0) not null,
	reply varchar2(1000) not null,
	replyer varchar2 (50) not null,
	replyDate date default sysdate,
	updateDate date default sysdate
	);
    
CREATE SEQUENCE seq_board_bk;

--��� ���̺�
create table tbl_board_bk(
    bkno number(10,0) PRIMARY key,  --�߰�--
    bno number(10,0) not null,
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    regdate date DEFAULT sysdate,
    updatedate date DEFAULT sysdate,
    editdate date DEFAULT sysdate   --�߰�--
);

INSERT INTO tbl_board_bk
(SELECT seq_board_bk.NEXTVAL, bno, title, content, writer, regdate, updatedate, sysdate
    FROM tbl_board WHERE bno = 2);


alter table tbl_board add (replycnt number(10));

select count(*) from tbl_reply where bno = 2;

--������̺� insert
INSERT INTO tbl_board_bk
    ( SELECT
        seq_board_bk.NEXTVAL,
        bno,
        title,
        content,
        writer,
        regdate,
        updatedate,
        sysdate
    FROM
        tbl_board
    WHERE
        bno = 2
    );
    
    CREATE SEQUENCE seq_board_bk;

CREATE TABLE tbl_board_bk (
    bkno      NUMBER(10, 0) PRIMARY KEY,
    bno       NUMBER(10, 0) NOT NULL,
    title     VARCHAR2(200) NOT NULL,
    content   VARCHAR2(2000) NOT NULL,
    writer    VARCHAR2(50) NOT NULL,
    regdate DATE DEFAULT sysdate,
    updatedate DATE DEFAULT sysdate,
    editdate  DATE DEFAULT sysdate
);

-- insertAjax ���� ��� �Ҷ�
--���� ���� �Ҷ�
update tbl_board set replycnt = (select count(*) from tbl_reply where bno=2)
where bno=2;

select * from tbl_reply;

select count(*) from tbl_reply where bno=2;

create sequence seq_reply;

alter table tbl_reply add constraint pk_reply primary key (rno);

alter table tbl_reply add constraint fk_reply_board
foreign key (bno) references tbl_board (bno);

insert into tbl_reply (rno, bno, reply, replyer)
        values (seq_reply.nextval, 1, 33333, 3456);
        
select * from tbl_reply;

insert into tbl_reply values (seq_reply.nextval, 2, 33333, 3456, sysdate, sysdate);

insert into tbl_reply
(select seq_reply.nextval, 2, reply, replyer, replydate, updatedate
from tbl_reply);

ALTER SEQUENCE seq_reply INCREMENT BY 1;
SELECT seq_reply.NEXTVAL FROM DUAL;
SELECT seq_reply.CURRVAL FROM DUAL;

select * from tbl_reply;

select * from tbl_board;

--rownumber()
select * from
(select ROW_NUMBER() OVER (ORDER BY bno desc) rm, tbl.*
from tbl_board tbl 
where (title like '%����%' or content like '%�۹�ȣ%')
)
where rm between 1 and 10;

--��������ȣ, �������� �Խù���
--1 , 10
--�������� �ѹ�


drop table tbl;

CREATE sequence seq_tbl;

select * from tbl_board where bno=3;

DELETE FROM tbl_board WHERE bno=3;

insert into tbl_board values
(seq_tbl_board.nextval, '����', '����', '�ۼ���', sysdate, sysdate);


insert into tbl_board
(select seq_tbl_board.nextval, title, content, writer, regdate, updatedate from tbl_board);