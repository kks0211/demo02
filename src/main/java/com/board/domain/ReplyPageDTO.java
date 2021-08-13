package com.board.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyPageDTO {
    private int replyCnt;
    private List<ReplyVO> list;
}
