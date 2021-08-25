CREATE SEQUENCE IF NOT EXISTS SEQ_BOARD MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1 INCREMENT BY 1 CACHE 8 NOCYCLE;
CREATE TABLE IF NOT EXISTS tbl_board
(
    bno        long(10) AUTO_INCREMENT,
    title      varchar(200)  NOT NULL,
    content    varchar(2000) NOT NULL,
    writer     varchar(50)   NOT NULL,
    regdate    DATE default sysdate,
    updatedate DATE default sysdate,
    replycnt   int  default 0,
    categoryid Long(10) default 1,
    PRIMARY KEY (bno)
);

CREATE SEQUENCE IF NOT EXISTS seq_reply MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1 INCREMENT BY 1 CACHE 8 NOCYCLE;
CREATE TABLE IF NOT EXISTS tbl_reply
(
    rno        Long(10) AUTO_INCREMENT,
    bno        long(10)      NOT NULL,
    reply      varchar(1000) NOT NULL,
    replyer    varchar(50)   NOT NULL,
    replydate  DATE default sysdate,
    updatedate DATE default sysdate,
    PRIMARY KEY (rno),
    foreign key (bno) references tbl_board (bno)
);

/*
insert into tbl_board (title, content, writer) values ('제목1', '내용1', '글쓴이1');
insert into tbl_board (title, content, writer) values ('제목2', '내용2', '글쓴이2');
insert into tbl_board (title, content, writer) values ('제목3', '내용3', '글쓴이3');
insert into tbl_board (title, content, writer) values ('제목4', '내용4', '글쓴이4');
insert into tbl_board (title, content, writer) values ('제목5', '내용5', '글쓴이5');

*/

CREATE TABLE IF NOT EXISTS tbl_member
(
    userid     varchar(50)  NOT NULL,
    userpw     varchar(100) NOT NULL,
    username   varchar(100) NOT NULL,
    regdate    DATE    default sysdate,
    updatedate DATE    default sysdate,
    enabled    char(1) default '1',
    PRIMARY KEY (userid)
);

CREATE TABLE IF NOT EXISTS tbl_member_auth
(
    userid varchar(50),
    auth   varchar(50),
    foreign key (userid) references tbl_member (userid)
);

CREATE SEQUENCE IF NOT EXISTS SEQ_CATEGORY MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1 INCREMENT BY 1 CACHE 8 NOCYCLE;
CREATE TABLE IF NOT EXISTS category
(
    categoryid        Long(10) AUTO_INCREMENT,
    categoryname    varchar(200) NOT NULL,
    regdate    DATE default sysdate,
    updatedate DATE default sysdate,
    PRIMARY KEY (categoryid)
);