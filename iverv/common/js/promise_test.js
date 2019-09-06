function getTest(){
	return new Promise(function(resolve, reject){
		var data = 100;
		console.log(1);
		resolve(data);
		console.log(2);
	});
}

getTest().then(function(datas){
	console.log("datas3", datas);
});