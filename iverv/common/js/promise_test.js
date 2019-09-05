function getTest(){
	return new Promise(function(resolve, reject){
		var data = 100;
		resolve(data);
	});
}

getTest().then(function(datas){
	console.log("datas", datas);
});