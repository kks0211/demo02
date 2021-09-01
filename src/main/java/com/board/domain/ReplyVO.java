package com.board.domain;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVO {

    private Long rno;
    private Long bno;

    private String reply;
    private String replyer;
    private Date replyDate;
    private Date updateDate;
}
