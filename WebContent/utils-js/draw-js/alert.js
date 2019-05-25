var percent = [90,5,5,87.5,5.5,7];
Highcharts.chart('container', {
	chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
	},
	credits:{
    	enabled: false
    	},
	title: {
			text: '异常供暖汇总'
	},
	tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	},
	plotOptions: {
			pie: {
					allowPointSelect: true,
					cursor: 'pointer',
					dataLabels: {
							enabled: false
					},
					showInLegend: true
			}
	},
	series: [{
			name: '占比',
			colorByPoint: true,
			data: [{
					name: '正常供暖',
					y: percent[0],
					sliced: true,
					selected: true
			}, {
					name: '供热不足',
					y: percent[1]
			}, {
					name: '供热过量',
					y: percent[2]
			}]
	}]
});
function search(){
	Highcharts.chart('container', {
		chart: {
				plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false,
				type: 'pie'
		},
		credits:{
	    	enabled: false
	    	},
		title: {
				text: '异常供暖汇总'
		},
		tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions: {
				pie: {
						allowPointSelect: true,
						cursor: 'pointer',
						dataLabels: {
								enabled: false
						},
						showInLegend: true
				}
		},
		series: [{
				name: '占比',
				colorByPoint: true,
				data: [{
						name: '正常供暖',
						y: percent[3],
						sliced: true,
						selected: true
				}, {
						name: '供热不足',
						y: percent[4]
				}, {
						name: '供热过量',
						y: percent[5]
				}]
		}]
});
}
