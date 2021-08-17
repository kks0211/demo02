package com.board.domain;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthVO {

  private String userid;
  private String auth;
  
}
