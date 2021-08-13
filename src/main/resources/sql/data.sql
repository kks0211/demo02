CREATE SEQUENCE IF NOT EXISTS SEQ_BOARD MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1 INCREMENT BY 1 CACHE 8 NOCYCLE;
CREATE TABLE IF NOT EXISTS tbl_board
(
    bno        long(10)      AUTO_INCREMENT,
    title      varchar(200)  NOT NULL,
    content    varchar(2000) NOT NULL,
    writer     varchar(50)   NOT NULL,
    regdate    DATE     default sysdate,
    updatedate DATE default sysdate,
    replycnt int default 0,
    PRIMARY KEY (bno)
);

CREATE SEQUENCE IF NOT EXISTS seq_reply MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1 INCREMENT BY 1 CACHE 8 NOCYCLE;
CREATE TABLE IF NOT EXISTS tbl_reply
(
    rno        Long(10)     AUTO_INCREMENT,
    bno        long(10)      NOT NULL,
    reply      varchar(1000) NOT NULL,
    replyer    varchar(50)   NOT NULL,
    replydate  DATE default sysdate,
    updatedate DATE default sysdate,
    PRIMARY KEY (rno),
    foreign key (bno) references tbl_board(bno)
);

insert into tbl_board (title, content, writer) values ('제목1', '내용1', '글쓴이1');
insert into tbl_board (title, content, writer) values ('제목2', '내용2', '글쓴이2');
insert into tbl_board (title, content, writer) values ('제목3', '내용3', '글쓴이3');
insert into tbl_board (title, content, writer) values ('제목4', '내용4', '글쓴이4');
insert into tbl_board (title, content, writer) values ('제목5', '내용5', '글쓴이5');

