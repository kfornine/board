-- ���̺� ����
-- ���̺�� : tbl_board
select * from tbl_board where bno=3;

select count(*) from tbl_board;

insert into tbl_board
(select seq_tbl_board.nextval, title, content, writer, sysdate, sysdate from tbl_board);

select * from tbl_board;



--ROW_NUMBER()�Լ� ����Ͽ� ����¡ó��
select * from
(select ROW_NUMBER() OVER (ORDER BY bno desc) row_num, tbl.*
from tbl_board tbl)
where row_num between 11 and 20
;


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

CREATE SEQUENCE seq_reply;

alter table tbl_reply add constraint pk_reply primary key (rno);
alter table tbl_reply add constraint fk_reply_board
foreign key(bno) references tbl_board (bno);

select * from tbl_board where bno=2;

insert into tbl_reply values
(seq_reply.nextval, 2, '������', 'ȫ�浿', sysdate, sysdate);

delete tbl_board where bno=2;

delete tbl_reply where rno=3;

update tbl_reply set reply='test', replyer='ȫ�浿', updatedate=sysdate where rno=2;

select * from tbl_reply where rno=2;

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