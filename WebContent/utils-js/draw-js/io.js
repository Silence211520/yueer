var chart = Highcharts.chart('container', {
	chart: {
		type: 'column'
	},
	title: {
		text: '供暖参数统计'
	},
	xAxis: {
		categories: ['进水口温度', '出水口温度', '霍尔流量']
	},
	yAxis: {
		min: 0,
		title: {
			text: '参数取值'
		},
		stackLabels: {  // 堆叠数据标签
			enabled: true,
			style: {
				fontWeight: 'bold',
				color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
			}
		}
	},
	legend: {
		align: 'right',
		x: -30,
		verticalAlign: 'top',
		y: 25,
		floating: true,
		backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
		borderColor: '#CCC',
		borderWidth: 1,
		shadow: false
	},
	tooltip: {
		formatter: function () {
			return '<b>' + this.x + '</b><br/>' +
				this.series.name + ':<br> ' + this.y + '(平均)'
		}
	},
	plotOptions: {
		column: {
			stacking: 'normal',
			dataLabels: {
				enabled: true,
				color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
				style: {
					// 如果不需要数据标签阴影，可以将 textOutline 设置为 'none'
					textOutline: '1px 1px black'
				}
			}
		}
	},
	series: [{
		name: '2018-12',
		data: [89.5, 70.4, 19.5]
	}, {
		name: '2019-1',
		data: [90.2, 70, 20.2]
	}, {
		name: '2019-2',
		data: [91.3, 70.2, 22]
	}]
});