(function($){
	var ajaxOk = false;

	$.setChain = {};
	$.setChain.queue;
	
	//여러개 실행시켜서 마지막 끝나는 시점에 loading bar 종료 같은거 만들기.
	
	$(document).ajaxStart(function(){
		ajaxOk = false;
	}).ajaxStop(function(data){ 
		ajaxOk = true;
		if($.setChain.queue != undefined && $.setChain.queue.length != 0){
			//console.log("$.setChain.queue에 데이터가 존재합니다.", $.setChain.queue);
			$.setChain.callList();
		}
	})
	
	$.setChain.chains = function(_funcList){
		$.setChain.queue = _funcList;
		$.setChain.callList();
	}

	//ajax 순차적으로 실행..
	$.setChain.callList = function(){
		// console.log("$.setChain.queue", $.setChain.queue);
		$.setChain.queue[0]();
		$.setChain.queue.shift();
		//console.log($.setChain.queue, "$.setChain.queue shift");
	}
	
})(jQuery);
