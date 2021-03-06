-- 테이블 생성,시퀀스,
--리스트,인서트,업데이트,삭제,
--페이징,
		insert into users (id, pwd, enabled, name, email)
			values (#{id}, #{pwd}, 1, #{name}, #{email});
		
        insert into users (id, pwd, enabled, name, email)
			values ('user05', '123456', 1, '이준기', 'leehjcap1@gmail.com');
            
		insert into users_role values (#{id}, #{role});

select * from users where id='user01' and pwd='1234';
------------------------------------------------------1
select role_id from users_role where id='user01';
-------------------------------------------------------
select * from users where sessionkey='C8D29AD8FA72BCE2791FDEFB984365CE' 
                      and sessionlimit > sysdate;

alter table users add sessionkey varchar(50);
alter table users add sessionlimit date;
update users set sessionkey='sessionkey'
                ,sessionlimit = sysdate + 7
where id = 'user01';

	select pwd from users where id = 'user01';
select * from users where id = 'user01' or name = '';



grant connect, resource to spring;

GRANT CREATE SESSION TO spring;

GRANT CREATE TABLE, RESOURCE TO spring;

SELECT * FROM v$resource_limit WHERE resource_name IN ('processes','sessions');

ALTER SYSTEM SET processes = 200 scope=spfile;

delete tbl_attach where attachNo=1 and uuid='uuid';
--그외, pk설정 외부키설정 컬럼생성
-- 테이블명 : tbl_board

-- 사용자 테이블 생성
create table ousers(
  	id varchar2(50) primary key,
  	pwd varchar2(100) not null,
	name varchar2(20) not null,
	email varchar2(100) not null,
    tel varchar2(100) not null,
    enabled char(1) default '1' --사용할까말까,볼까말까
);

-- 메뉴 테이블 생성
CREATE TABLE MENU 
(	
    MENU_ID VARCHAR2(50 BYTE) PRIMARY KEY, 
	UP_MENU_ID VARCHAR2(50 BYTE), 
    TITLE VARCHAR2(50 BYTE), 
	URL VARCHAR2(50 BYTE), 
	SORT NUMBER(2,0), 
	VISIBLE CHAR(1 BYTE)
);

select * from menu;

Insert into SPRING.MENU (MENU_ID,TITLE,URL,UP_MENU_ID,SORT,VISIBLE) values ('m01','대메뉴1','/board/list',null,1,'Y');
Insert into SPRING.MENU (MENU_ID,TITLE,URL,UP_MENU_ID,SORT,VISIBLE) values ('m02','대메뉴2','/board/list',null,2,'Y');
Insert into SPRING.MENU (MENU_ID,TITLE,URL,UP_MENU_ID,SORT,VISIBLE) values ('m03','대메뉴3','/board/list',null,3,'Y');
Insert into SPRING.MENU (MENU_ID,TITLE,URL,UP_MENU_ID,SORT,VISIBLE) values ('m05','1-중메뉴1','/board/list','m01',1,'Y');
Insert into SPRING.MENU (MENU_ID,TITLE,URL,UP_MENU_ID,SORT,VISIBLE) values ('m06','1-중메뉴2','/board/list','m01',2,'Y');
Insert into SPRING.MENU (MENU_ID,TITLE,URL,UP_MENU_ID,SORT,VISIBLE) values ('m07','2-중메뉴1','/board/list','m02',1,'Y');
Insert into SPRING.MENU (MENU_ID,TITLE,URL,UP_MENU_ID,SORT,VISIBLE) values ('m08','3-중메뉴1','/board/list','m03',1,'Y');
Insert into SPRING.MENU (MENU_ID,TITLE,URL,UP_MENU_ID,SORT,VISIBLE) values ('m09','5-메뉴1','/board/list','m03',1,'Y');
Insert into SPRING.MENU (MENU_ID,TITLE,URL,UP_MENU_ID,SORT,VISIBLE) values ('m10','5-메뉴2','/board/list','m03',2,'Y');
Insert into SPRING.MENU (MENU_ID,TITLE,URL,UP_MENU_ID,SORT,VISIBLE) values ('m11','5-메뉴3','/board/list','m03',3,'Y');

SELECT
    level, -- 루트를 기준으로 1레벨 시작
    up_menu_id, -- 상위메뉴
    menu_id, -- 메뉴
    title, -- 메뉴명
    url -- 메뉴 주소
FROM
    menu
START WITH up_menu_id IS NULL -- 루트노드(시작)는 상위메뉴가 null
CONNECT BY up_menu_id = PRIOR menu_id  -- 부모 자식관계
ORDER SIBLINGS BY  sort; -- 정렬

alter table ousers add colum email varchar(255);
 
-- 권한 테이블 생성 
-- 다중 PK를 설정 할 경우 제약조건으로 하단에 추가 합니다. 	
create table ousers_role (
    id       VARCHAR2(50) NOT NULL,  --일반 사용자
    role_id  VARCHAR2(50) NOT NULL,  --권한 사용자
    constraint fk_ousers_role FOREIGN KEY ( id ) REFERENCES ousers ( id ),
    constraint pk_ousers_role PRIMARY KEY (id, role_id) --2개모두 pk
);
-- 사용자 등록
insert into ousers (id, pwd, enabled, name, email, tel) values ('user01', '1234', '1', '이름', '9@naver.com', '010-1234-5678');
insert into users (id, pwd, enabled, name, email) values ('user02', '1234', '1', '이번', '2@naver.com');
insert into users (id, pwd, enabled, name, email) values ('user03', '1234', '1', '삼번', '3@naver.com');

 
-- 사용자별 권한 등록
insert into ousers_role (id, role_id) values ('user01', 'ROLE_USER');
insert into ousers_role (id, role_id) values ('user01', 'ROLE_ADMIN');
insert into users_role (id, role_id) values ('user02', 'ROLE_USER');
insert into users_role (id, role_id) values ('user03', 'ROLE_USER');




select * from tbl_board where bno=3;

select count(*) from tbl_board;
select count(*) from tbl_reply;

insert into tbl_board
(select seq_tbl_board.nextval, title, content, writer, sysdate, sysdate from tbl_board);

select * from tbl_board;

alter table tbl_board add (replycnt number(10));

--ROW_NUMBER()함수 사용하여 페이징처리
select * from
(select ROW_NUMBER() OVER (ORDER BY bno desc) row_num, tbl.*
from tbl_board tbl)
where row_num between 11 and 20
;

-- 첨부파일 테이블 생성
create table tbl_attach ( 
  attachno number(10) not null,
  uuid varchar2(100) not null,
  uploadPath varchar2(200) not null,
  fileName varchar2(100) not null, 
  filetype char(1) ,
  regdate date
);
-- 시퀀스 생성
create sequence seq_attach;
-- PK 생성
alter table tbl_attach add constraint pk_attach primary key (uuid);
-- 1건 삽입
insert into tbl_attach values (SEQ_ATTACH.nextval, 'uuid', 'uploadPath', 'filename', 'N', sysdate);
-- board 테이블에 첨부파일 컬럼 생성
alter table tbl_board add (attachNo number(10));
--시퀀스 찾기
select SEQ_ATTACH.nextval from dual;


select attachNo, uuid, uploadPath, fileName, fileType as image
--uploadpath : 년/월/일 경로
--uuid_fileName : 저장된 파일 이름 , || = +
--s_uuid_fileName : 썸네일이 저장된 파일 이름
,uploadpath || uuid || '_' || fileName as savePath
,uploadpath || 's_' || uuid || '_' || fileName as s_savePath
from tbl_attach
where attachNo = 1;


--날짜는 밖으로 빼주고 row_number일 이용한 순서만들기(페이징처리)
select  rn, rno, bno, reply, replyer, replydate,
        CASE
            WHEN to_char(sysdate, 'yyyymmdd') = to_char(updatedate, 'yyyymmdd') THEN
                to_char(updatedate, 'hh:mi:ss')
            ELSE
                to_char(updatedate, 'yyyy/mm/dd')
        END updatedate from
--rownumber rn정하기
    (SELECT
    ROW_NUMBER() OVER (ORDER BY rno desc) rn, tbl_reply.*
    FROM
        tbl_reply where bno =2
        order by rno desc)
where rn between 1 and 10;

-- 페이징 처리
select * from
(select rownum rn, bno from tbl_board order by bno desc)
where rn between (페이지*페이지당 게시물수)-1 and 페이지번호 * 페이지당 게시물수;

--페이징 처리2
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
 		set title='수정매퍼테스트', content='컨텐츠', writer='작성자', updatedate=sysdate 
 		where bno=3;


insert into tbl_board
values(SEQ_TBL_BOARD.nextval,'제목','내용','작성자',sysdate,sysdate);

insert into tbl_board
(select SEQ_TBL_BOARD.nextval, title, content, writer, regdate, updatedate
from tbl_board);

--리플 테이블
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
(seq_reply.nextval, 2, '가나다', '홍길동', sysdate, sysdate);

insert into tbl_reply
(select seq_reply.nextval, bno, reply, replyer, replydate, updatedate
from tbl_reply);

delete tbl_board where bno=2;

delete tbl_reply where rno=3;

update tbl_reply set reply='test', replyer='홍길동', updatedate=sysdate where rno=2;

select * from tbl_reply where rno=2;

select * from tbl_reply;

CREATE table tbl_board(
bno NUMBER(10) primary key,
title varchar2(200 byte) NOT NULL,
content varchar2(2000 byte) NOT NULL,
writer varchar2(50 byte) NOT NULL,
regdate date DEFAULT sysdate,
updatedate date);

COMMENT ON TABLE spring.tbl_board is '게시판';
COMMENT ON COLUMN spring.tbl_board.bno is '번호';
COMMENT ON COLUMN spring.tbl_board.title is '제목';
COMMENT ON COLUMN spring.tbl_board.content is '내용';
COMMENT ON COLUMN spring.tbl_board.writer is '작성자';
COMMENT ON COLUMN spring.tbl_board.regdate is '작성일';
COMMENT ON COLUMN spring.tbl_board.updatedate is '업데이트일';

-- 시퀀스

create SEQUENCE seq_tbl_board;

select seq_tbl_board.nextval from dual;
select seq_tbl_board.currval from dual;

-- 시퀀스명 : seq_tbl_board

-- 사용자 생성
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

--백업 테이블
create table tbl_board_bk(
    bkno number(10,0) PRIMARY key,  --추가--
    bno number(10,0) not null,
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    regdate date DEFAULT sysdate,
    updatedate date DEFAULT sysdate,
    editdate date DEFAULT sysdate   --추가--
);

INSERT INTO tbl_board_bk
(SELECT seq_board_bk.NEXTVAL, bno, title, content, writer, regdate, updatedate, sysdate
    FROM tbl_board WHERE bno = 2);


alter table tbl_board add (replycnt number(10));

select count(*) from tbl_reply where bno = 2;

--백업테이블 insert
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

-- insertAjax 리플 등록 할때
--리플 삭제 할때
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
where (title like '%제목%' or content like '%글번호%')
)
where rm between 1 and 10;

--페이지번호, 페이지당 게시물수
--1 , 10
--페이지당 넘버


drop table tbl;

CREATE sequence seq_tbl;

select * from tbl_board where bno=3;

DELETE FROM tbl_board WHERE bno=3;

insert into tbl_board values
(seq_tbl_board.nextval, '제목', '내용', '작성자', sysdate, sysdate);


insert into tbl_board
(select seq_tbl_board.nextval, title, content, writer, regdate, updatedate from tbl_board);