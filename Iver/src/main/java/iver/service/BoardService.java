package iver.service;

import java.util.HashMap;
import java.util.List;

import iver.dto.BoardDto;
import iver.dto.TableDto;

public interface BoardService {
	List<BoardDto> selectBoardList() throws Exception;
	List<TableDto> selectTestList(HashMap<String, Integer> map) throws Exception;
	//List<TableDto> selectPagingList(HashMap<String, Integer> amp) throws Exception;
	Integer selectTotalPageCnt() throws Exception;
}
