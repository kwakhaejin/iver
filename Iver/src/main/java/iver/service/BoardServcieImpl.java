package iver.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iver.dto.BoardDto;
import iver.dto.TableDto;
import iver.mapper.BoardMapper;

@Service
public class BoardServcieImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public List<TableDto> selectTestList(HashMap<String, Integer> map) throws Exception {
		int rowCnt = map.get("rowCnt");
		int currentPage = map.get("currentPage");
		
		int last = rowCnt; 
		int first = (currentPage*rowCnt) - rowCnt;
		HashMap<String, Integer> dataMap = new HashMap<String, Integer>();
		dataMap.put("last", last);
		dataMap.put("first", first);
		return boardMapper.selectTestList(dataMap);
	}

	
	//페이징
	//전체 페이지 개수 가져오기
	@Override
	public Integer selectTotalPageCnt() throws Exception {
		return boardMapper.selectTotalPageCnt();
	}

}
