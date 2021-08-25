package com.board.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Long categoryId;
    private String categoryName;
    private Date regDate;
    private Date updateDate;
}
