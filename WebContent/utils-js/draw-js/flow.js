var chart = Highcharts.chart('container', {
            chart: {
                type: 'spline'
            },
            title: {
                text: '流量变化曲线图'
            },
//            subtitle: {
//                text: '2019年1月15日和16日某用户的流量变化曲线图'
//            },
            xAxis: {
                type: 'datetime',
                labels: {
                    overflow: 'justify'
                }
            },
            yAxis: {
                title: {
                    text: '流量 (L/min)'
                },
                min: 0,
                minorGridLineWidth: 0,
                gridLineWidth: 0,
                alternateGridColor: null,
                plotBands: [{ // Light air
                    from: 1,
                    to: 5,
                    color: 'rgba(68, 170, 213, 0.1)',
                    label: {
                        text: '流量下限',
                        style: {
                            color: '#606060'
                        }
                    }
                },    { // Fresh breeze
                    from: 10,
                    to: 20,
                    color: 'rgba(68, 170, 213, 0.1)',
                    label: {
                        text: '适宜的流量范围',
                        style: {
                            color: '#606060'
                        }
                    }
                }, { // Strong breeze
                    from: 25,
                    to: 30,
                    color: 'rgba(68, 170, 213, 0.1)',
                    label: {
                        text: '流量上线',
                        style: {
                            color: '#606060'
                        }
                    }
                }]
            },
            tooltip: {
                valueSuffix: ' 	L/min'
            },
            credits:{
		    	enabled: false
		    	},
            plotOptions: {
                spline: {
                    lineWidth: 4,
                    states: {
                        hover: {
                            lineWidth: 5
                        }
                    },
                    marker: {
                        enabled: false
                    },
                    pointInterval: 3600000, // one hour
                    pointStart: Date.UTC(2019, 1, 15, 0, 0, 0)
                }
            },
            series: [{
                name: '0x18001F',
                data: [23, 23.5, 24, 22, 23.5, 24, 25, 24.5, 25, 25, 24, 23,
                       22, 22.5, 23, 23.5, 23, 24, 25, 24, 23, 22, 23, 23.5,
                       23, 24, 24.5, 24, 24.3, 24, 25, 24, 23.5, 23, 22,
                       23, 23.5, 24, 24, 24.5, 24, 23.5, 23, 24, 24.3, 24, 23,
                       22, 23]
            }, {
                name: '0x18002F',
                data: [17, 18.5, 18, 17, 16.5, 16, 15, 15.5, 16, 16.5, 17, 17.5,
                       17, 16.5, 16, 18, 17, 17.5, 17, 17.3, 16, 16.5, 17, 17.5,
                       16, 16.5, 16, 17, 17.5, 18, 17.5, 17.7, 16.6, 16, 15, 15.5,
                       17, 17.5, 16, 17.5, 16, 17.5, 17, 16, 16.5,17, 16, 17, 16.8]
            }],
            navigation: {
                menuItemStyle: {
                    fontSize: '10px'
                }
            }
        });