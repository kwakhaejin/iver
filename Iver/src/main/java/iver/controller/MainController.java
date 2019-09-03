package iver.controller;

import java.util.HashMap;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import iver.dto.BoardDto;
import iver.dto.TableDto;
import iver.service.BoardService;

@RequestMapping("/iver/test")
@Api(value="TEST API")
@RestController
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private BoardService boardService;
	
	//private ObjectMapper mapper = new ObjectMapper(); //json 변환 인스턴스 생성
	
	@SuppressWarnings("null")
	@RequestMapping(value="getList", method=RequestMethod.GET, produces= {"application/json; charset=UTF-8", "text/plain"})
	public String getTableList() {
		JSONObject jResult = new JSONObject();
		//return jResult.toString();
		try {
			List<BoardDto> list = boardService.selectBoardList();
			System.out.println(list+"list@@");
			jResult.put("tableList", list);
			System.out.println(jResult +"jResult");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("erors", e);
		}
		return jResult.toString();
	}
	
	@RequestMapping(value="getTestList", method=RequestMethod.GET, produces= {"application/json; charset=UTF-8", "text/plain"})
	public String getTestList(@RequestParam(value="rows", required = true) Integer rows,
							  @RequestParam(value="page", required = true) Integer page) {
		JSONObject jResult = new JSONObject();
		try {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("rowCnt", rows);
			map.put("currentPage", page);
			List<TableDto> list = boardService.selectTestList(map);
			jResult.put("tableList", list);
		} catch (Exception e) {
			logger.error("erors", e);
		}
		return jResult.toString();
	}
	
}
