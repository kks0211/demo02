package com.board.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Search {

    private String type;
    private String keyword;

    public String[] getTypeArr() {
        return type == null ? new String[]{} : type.split("");
    }
}
