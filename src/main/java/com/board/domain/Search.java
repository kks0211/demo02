package com.board.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Search {

    private String type;
    private String keyword;
    private String category;

    public String[] getTypeArr() {
        return type == null ? new String[]{} : type.split("");
    }

}
