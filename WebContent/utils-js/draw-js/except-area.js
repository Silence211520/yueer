// 没有查询的时候返回
   $.post("/xiaoyueyue/DefaultArea",
		   function(data){
			   var  datas = data.data;
			   var points = [];
			   for(var i = 0; i< datas.length; i++){
				   points.push(new BMap.Point(datas[i].longitude, datas[i].latitude));
			   }
			   var bm = new BMap.Map("allmap");
			   bm.centerAndZoom(new BMap.Point(126.626349, 45.71353), 13);
			   translateCallback = function(data) {
			   	if (data.status === 0) {
			   		for (var i = 0; i < data.points.length; i++) {
			   			bm.addOverlay(new BMap.Marker(data.points[i]));
			   			bm.setCenter(data.points[i]);
			   		}
			   	}
			   }
			   setTimeout(function() {
			   	var convertor = new BMap.Convertor();
			   	convertor.translate(points, 1, 5, translateCallback)
			   }, 1000);
			   
		   }
		   );  
// 有查询时候,返回
function search(){
	   var start_date = $("#start_date").val();
	   var end_date = $("#end_date").val();
	   $.post("/xiaoyueyue/ExceptionArea",
			   {start_date:start_date,end_date:end_date},
			   function(data){
				   var  datas = data.data;
				   var points = [];
				   for(var i = 0; i< datas.length; i++){
					   points.push(new BMap.Point(datas[i].longitude, datas[i].latitude));
				   }
				   var bm = new BMap.Map("allmap");
				   bm.centerAndZoom(new BMap.Point(126.626349, 45.71353), 13);
				   translateCallback = function(data) {
				   	if (data.status === 0) {
				   		for (var i = 0; i < data.points.length; i++) {
				   			bm.addOverlay(new BMap.Marker(data.points[i]));
				   			bm.setCenter(data.points[i]);
				   		}
				   	}
				   }
				   setTimeout(function() {
				   	var convertor = new BMap.Convertor();
				   	convertor.translate(points, 1, 5, translateCallback)
				   }, 1000);
				   
			   }
			   );  
   }