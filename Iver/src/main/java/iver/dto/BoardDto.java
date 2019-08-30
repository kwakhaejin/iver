package iver.dto;

import lombok.Data;

@Data
public class BoardDto {
	int boardIdx;
	String title;
	String content;
	int hitCnt;
	String creatorId;
	String createdDatetime;
	String updaterId;
	String updateDatetime;
}
