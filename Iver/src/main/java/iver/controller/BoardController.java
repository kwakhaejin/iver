package iver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import iver.dto.BoardDto;
import iver.dto.TableDto;
import iver.service.BoardService;

@Controller
public class BoardController {
	private int listSize = 30;
	private int rangeSize = 10;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("board/openBoardList")
	public ModelAndView openBoardList() throws Exception{
		ModelAndView mv = new ModelAndView("board/boardList");
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	//처음 로딩
	@RequestMapping("board/testTable")
	public ModelAndView testTable() throws Exception{
		ModelAndView mv = new ModelAndView("board/pagingTable");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("rowCnt", listSize);
		map.put("currentPage", 1);
		List<TableDto> list = boardService.selectTestList(map);
		
		int range = 1;
		//range = 각 페이지 범위 시작 번호(일단 1로 세팅)
		//시작 페이지
		//int startPage = (range -1)*rangeSize + 1;
		
		//전체페이지 로직 필요함
		int totalPage = selectTotalPageCnt(listSize);
		//끝 페이지
		int endPage = totalPage > rangeSize? range*rangeSize : totalPage;
		
		//시작 페이지와 끝페이지 정보를 보냄
		Map<String, Integer> startEnd = new HashMap<String,Integer>();
		startEnd.put("start", range);
		startEnd.put("end", endPage);
		
		mv.addObject("totalNum", totalPage);
		mv.addObject("startEnd", startEnd);
		mv.addObject("list", list);
		return mv;
	}
	
	@PostMapping(value="board/getPage")
	@ResponseBody
	public List<TableDto> getSelectAjax(@Valid @RequestBody Map<String, Integer> pageNum) throws Exception{
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		
		if(pageNum.get("rowNum") != null) {
			map.put("rowCnt", pageNum.get("rowNum"));
		} else {
			map.put("rowCnt", listSize);
		}
		map.put("currentPage", pageNum.get("pageNum"));
		List<TableDto> list = boardService.selectTestList(map);
		return list;
	}
	
	//이전 => range가 1일경우 클릭x, 1이 아닐경우 클릭o
	//다음 => 다음페이지가 없을 경우 클릭x
	
	//이전 or 다음 클릭했을시 호출 ajax pagination의 값을 리턴
	//pagination ajax
	@RequestMapping(value="board/getPagination")
	@ResponseBody
	public Map<String, Integer> getPagination() throws Exception{
		Map<String, Integer> map = new HashMap<String, Integer>();
		//service에서 startPage, endPage 가져오기
		return map;
	}
	
	
	//data row개수는 가변적이므로 전체 row 개수도 변하므로 ajax
	@RequestMapping(value="board/getTotalRowNum")
	@ResponseBody
	public Integer getTotalRowNum(@Valid @RequestBody Map<String, Integer> rowNum) throws Exception{
		int allPage = selectTotalPageCnt(rowNum.get("rowNum"));
		return allPage;
	}
	
	//전체 페이지 수 구하는 로직
	public Integer selectTotalPageCnt(int rowNum) throws Exception{
		int totalCnt = boardService.selectTotalPageCnt();
		//전체 페이지 수
		double dNumber= totalCnt/(double)rowNum;
		int allPage = (int) Math.ceil(dNumber);
		System.out.println(allPage+"전체 페이지 수"); //자료형 타입이 문제엿음..166.6개=>167page
		
		return allPage;
	}
	
}
