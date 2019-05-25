var points = [ new BMap.Point(126.622052, 45.715125), // b4
new BMap.Point(126.627917, 45.713925) // 子衿园1栋
//new BMap.Point(126.640727, 45.71198), // 四季芳洲
//new BMap.Point(126.637741, 45.711138), // 测绘地理信息小区
//new BMap.Point(126.627146, 45.713559) // 化学院
];

// 地图初始化
var bm = new BMap.Map("allmap");
bm.centerAndZoom(new BMap.Point(126.626349, 45.71353), 13);

// 坐标转换完之后的回调函数
translateCallback = function(data) {
	if (data.status === 0) {
		for (var i = 0; i < data.points.length; i++) {
			bm.addOverlay(new BMap.Marker(data.points[i]));
			bm.setCenter(data.points[i]);
		}
	}
}