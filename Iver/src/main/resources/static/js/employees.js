$(document).ready(function(){
	//var pageTag = $("#pagenationBlock").children("a");
	var currentNumber = 1;
	var rowNum = 30;
	var rowsFlag = false;
	var totalPageNum;
	
	//전체 페이지개수를 가져와서 처음 화면에 뿌려줄때 pagenation 값 설정하기
	/*function totalPageFunc(){
		//마지막 a태그의 class 중 마지막 페이지가 있으면 리턴 빠져나가기
		$.ajax({
			type:"POST",
			url:"getTotalRowNum",
			data:JSON.stringify({"rowNum":rowNum}),
			dataType:'json',
			contentType:"application/json",
			success:function(data){
				totalPageNum = data;
				
				if(flag == "rows") rowsFunction(rowsFlag);
				
				if($("a[id="+totalPageNum+"]").length === 1){
					//마지막 페이지가 있을 경우 다음 x
					return false;
				}
				if(flag == "next") nextFunction(totalPageNum);
				
			},
			error:function(error){
				console.log(error.status);
			}
		});
	}*/
	
	$(document).on("click", ".clickCurrentPage", function(){
		currentNumber = $(this).text();
		var data = {"pageNum":$(this).text(), "rowNum":rowNum};
		$.ajax({
			type:"POST",
			url:"getPage",
			data:JSON.stringify(data),
			dataType:'json',
			contentType:"application/json",
			success:function(data){
				$("#getList").children().remove();
				$.each(data, function(i, v){
					$("#getList").append('<tr><td>'+v.empNo+'</td>'+
							'<td>'+v.birthDate+'</td>'+
							'<td>'+v.firstName+'</td>'+
							'<td>'+v.lastName+'</td>'+
							'<td>'+v.gender+'</td>'+
							'<td>'+v.hireDate+'</td></tr>'
						);
				});
			},
			error:function(error){
				console.log(error.status);
			}
		});
	});
	
	//row수 변경
	//row수 변경시 총 페이지수가 바뀜, 마지막 페이지 로 이동, currentNumber 변경
	$(".dropdown-content").children("a").click(function(){
		//console.log(rowNum, "rowNum", currentNumber, "currentNumber");
		rowsFlag= false;
		
		nextPaging("rows"); //fistPaging 으로 변경
		//rows가 변경되었을때 현재페이지 =1
		if(rowNum != Number($(this).text())){
			currentNumber = 1;
			rowsFlag = true;
			
			$(".dropbtn").text($(this).text());
		}
		rowNum = $(this).text();
		
		var data = {"pageNum":currentNumber, "rowNum":$(this).text()};
		$.ajax({
			type:"POST",
			url:"getPage",
			data:JSON.stringify(data),
			dataType:'json',
			contentType:"application/json",
			success:function(data){
				$("#getList").children().remove();
				$.each(data, function(i, v){
					$("#getList").append('<tr><td>'+v.empNo+'</td>'+
							'<td>'+v.birthDate+'</td>'+
							'<td>'+v.firstName+'</td>'+
							'<td>'+v.lastName+'</td>'+
							'<td>'+v.gender+'</td>'+
							'<td>'+v.hireDate+'</td></tr>'
						);
				});
			},
			error:function(error){
				console.log(error.status);
			}
		});
		
	});
	
	
	//이전 클릭
	$(".beforePage").click(function(){
		$(".nextPage").css("display", "inline-block");
		//첫번째 pagination일 경우 리턴 빠져나가기 
		
		if($("a[id=1]").length === 1){
			return false;
		}
		var firstNum = Number($("#pagenationBlock").children(":first").text()) - 1;
		var minusFirstNum = Number(firstNum) - 9;
		
		$.pageBlock = $("#pagenationBlock");
		$.pageBlock.children().remove();
		for(var i=minusFirstNum; i<=firstNum; i++){
			$.pageBlock.append("<a class='clickCurrentPage "+ i +"' id="+i+">"+i+"</a>");
		}
		
		if($("a[id=1]").length === 1){
			$(".beforePage").css("display", "none");
		}
	});
	
	function nextPaging(flag){
		//마지막 a태그의 class 중 마지막 페이지가 있으면 리턴 빠져나가기
		$.ajax({
			type:"POST",
			url:"getTotalRowNum",
			data:JSON.stringify({"rowNum":rowNum}),
			dataType:'json',
			contentType:"application/json",
			success:function(data){
				totalPageNum = data;
				
				if(flag == "rows") rowsFunction(rowsFlag);
				
				if($("a[id="+totalPageNum+"]").length === 1){
					//$("a[id="+totalPageNum+"]").css("display", none);
					//마지막 페이지가 있을 경우 다음 x
					return false;
				}
				if(flag == "next") nextFunction(totalPageNum);
				
			},
			error:function(error){
				console.log(error.status);
			}
		});
	}
	
	//rows 변경시 1page 로 이동
	//rows 변경되지 않을시 현재상태 유지
	function rowsFunction(rowsFlag){
		if(rowsFlag == false){
			return false;
		}
		
		$.pageBlock = $("#pagenationBlock");
		$.pageBlock.children().remove();
		
		for(var i=1; i<=10; i++){
			$.pageBlock.append("<a class='clickCurrentPage "+ i +"' id="+i+">"+i+"</a>");
		}
	}
	
	function nextFunction(totalPageNum){
		var addFirstNum = Number($("#pagenationBlock").children(":first").text()) + 10;
		var lastNum = Number(addFirstNum) + 9;
		$.pageBlock = $("#pagenationBlock");
		$.pageBlock.children().remove();
		if(totalPageNum <= lastNum){
			for(var i=addFirstNum; i<=totalPageNum; i++){
				$.pageBlock.append("<a class='clickCurrentPage "+ i +"' id="+i+">"+i+"</a>");
			}
			if($("a[id="+totalPageNum+"]").length === 1){
				//$("a[id="+totalPageNum+"]").css("display", none);
				//마지막 페이지가 있을 경우 다음 x
				$(".nextPage").css("display", "none");
			}
			
		} else {
			for(var i=addFirstNum; i<=lastNum; i++){
				$.pageBlock.append("<a class='clickCurrentPage "+ i +"' id="+i+">"+i+"</a>");
			}
		}
	}
	
	//다음 클릭
	$(".nextPage").click(function(){
		$(".beforePage").css("display", "inline-block");		
		nextPaging("next");
	});
	
});
	


