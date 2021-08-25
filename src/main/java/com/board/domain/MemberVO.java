package com.board.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {

	private String userid;
	private String userpw;
	private String userName;
	private boolean enabled;

	private Date regDate;
	private Date updateDate;
	private List<AuthVO> authList;

}
