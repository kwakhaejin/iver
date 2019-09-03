/*package iver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RequestMapping("/iverv")
@Api(value="TEST IVERV")
@RestController
public class ProxyController {
	//뷰프로젝트와 분리, 연동하기 위해서 apache의 proxypass를 controllermapping 시킴(차선책으로)
	@RequestMapping("/view")
	public String iverPage() {
		System.out.println("????");
		return "/iverv/index.html";
	}

}
*/