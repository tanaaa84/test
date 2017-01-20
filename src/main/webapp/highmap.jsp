<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="utf-8">
</head>
<body style="height: 100%; margin: 0">
	<div id="container" style="height: 100%"></div>
	<script type="text/javascript" src="js/echarts-all-3.js"></script>
	<script type="text/javascript" src="js/dataTool.min.js"></script>
	<script type="text/javascript" src="js/china.js"></script>
	<script type="text/javascript" src="js/world.js"></script>
	<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=E89cccc4b5854636f959a26a959a7a59"></script>  -->
	<script type="text/javascript" src="js/bmap.min.js"></script>
	<script type="text/javascript">
		var dom = document.getElementById("container");
		var myChart = echarts.init(dom);
		var app = {};
		option = null;
		var data = [ {
			name : '海门',
			value : 45
		}, {
			name : '鄂尔多斯',
			value : 32
		}, {
			name : '招远',
			value : 72
		}, {
			name : '舟山',
			value : 72
		},

		];
		var geoCoordMap = {
			'海门' : [ 121.15, 31.89 ],
			'鄂尔多斯' : [ 109.781327, 39.608266 ],
			'招远' : [ 120.38, 37.35 ],
			'舟山' : [ 122.207216, 29.985295 ],

		};

		var convertData = function(data) {
			var res = [];
			for (var i = 0; i < data.length; i++) {
				var geoCoord = geoCoordMap[data[i].name];
				if (geoCoord) {
					res.push({
						name : data[i].name,
						value : geoCoord.concat(data[i].value)
					});
				}
			}
			return res;
		};

		option = {
			backgroundColor : '#404a59',
			title : {
				text : '国航APP实时访问图',
				subtext : 'Data from Airchina Mobile',
				sublink : 'http://m.airchina.com',
				left : 'center',
				textStyle : {
					color : '#fff'
				}
			},
			tooltip : {
				trigger : 'item'
			},
			legend : {
				orient : 'vertical',
				y : 'bottom',
				x : 'right',
				data : [ 'pm2.5' ],
				textStyle : {
					color : '#fff'
				}
			},
			geo : {
				map : 'world',
				label : {
					emphasis : {
						show : false
					}
				},
				roam : true,
				itemStyle : {
					normal : {
						areaColor : '#323c48',
						borderColor : '#111'
					},
					emphasis : {
						areaColor : '#2a333d'
					}
				}
			},
			series : [ {
				name : 'pm2.5',
				type : 'scatter',
				coordinateSystem : 'geo',
				data : convertData(data),
				symbolSize : function(val) {
					return val[2] / 10;
				},
				label : {
					normal : {
						formatter : '{b}',
						position : 'right',
						show : false
					},
					emphasis : {
						show : true
					}
				},
				itemStyle : {
					normal : {
						color : '#ddb926',
				              formatter: '{b}',
				              position: 'right',
				              show: true
					}
				}
			},
			/*   {
			      name: 'Top 5',
			      type: 'effectScatter',
			      coordinateSystem: 'geo',
			      data: convertData(data.sort(function (a, b) {
			          return b.value - a.value;
			      }).slice(0, 6)),
			      symbolSize: function (val) {
			          return val[2] / 10;
			      },
			      showEffectOn: 'render',
			      rippleEffect: {
			          brushType: 'stroke'
			      },
				hoverAnimation: true,
			      label: {
			          normal: {
			              formatter: '{b}',
			              position: 'right',
			              show: true
			          }
			      },
			      itemStyle: {
			          normal: {
			              color: '#f4e925',
			              shadowBlur: 10,
			              shadowColor: '#333'
			          }
			      },
			      zlevel: 3
			  } */
			]
		};
		;
		if (option && typeof option === "object") {
			myChart.setOption(option, true);
		}

		var data1 = [ {
			name : '盐城',
			value : 51
		}, {
			name : '赤峰',
			value : 61
		}, {
			name : '青岛',
			value : 81
		}, {
			name : '乳山',
			value : 23
		}, {
			name : '金昌',
			value : 57
		}, {
			name : '泉州',
			value : 72
		} ];
		var geoCoordMap1 = {
			'盐城' : [ 120.13, 33.38 ],
			'赤峰' : [ 118.87, 42.28 ],
			'青岛' : [ 120.33, 36.07 ],
			'乳山' : [ 121.52, 36.89 ],
			'金昌' : [ 102.188043, 38.520089 ],
			'泉州' : [ 118.58, 24.93 ]
		};

		//这里用setTimeout代替ajax请求进行演示
		window.setInterval(function() {
			refreshData(data1, geoCoordMap1);
		}, 3000);

		function refreshData(data, geoCoordMap1) {
			if (!myChart) {
				return;
			}

			//更新数据
			var option = myChart.getOption();
			option.series[0].data = convertData(data);

			geoCoordMap = geoCoordMap1;

			myChart.setOption(option,true);

		}
	</script>
</body>
</html>