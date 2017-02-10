<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="utf-8">
<style type="text/css">
.shape {
	width: 100%;
	height: 140px;
	position: fixed !important;
	position: absolute;
	left: 0px;
	bottom: 5px !important;
	bottom: auto;
	top: expression(eval(document.compatMode && document.compatMode ==
		'CSS1Compat')? documentElement.scrollTop+(documentElement.clientHeight-
		 this.clientHeight):document.body.scrollTop+(document.body.clientHeight-
		 this.clientHeight));
}

#btn {
	width: 100%;
	height: 200px;
	background: #111929;
	opacity:0.4;
	color:#9EE5FE;
	
}
</style>
</head>
<body style="height: 100%; margin: 0">

	<div id="container" style="height: 100%">
	
	</div>
		<div class="shape">
			<div id="btn">
				<table>
					<tr>
						<td><span id="time"></span></td>
					</tr>
					<tr>
						<td style="font-size:30px">机票销售总计：</td>
						<td style="font-size:30px"><div id="price"></div></td>
					</tr>
				</table>
			</div>
		</div>
	<script type="text/javascript" src="js/echarts-all-3.js"></script>
	<script type="text/javascript" src="js/dataTool.min.js"></script>
	<script type="text/javascript" src="js/china.js"></script>
	<script type="text/javascript" src="js/world.js"></script>
	<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=E89cccc4b5854636f959a26a959a7a59"></script> -->
	<script type="text/javascript" src="js/bmap.min.js"></script>
	<script type="text/javascript">
	

		var pri = 0;
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

		
	/* 	 var BJData = [
		      	     [{name:'海门'}, {name:'舟山',value:95}],
		      	     [{name:'海门'}, {name:'舟山',value:90}]
		      	 ]; */
		
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
				data : [ '实时数据' ],
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
				name : '实时数据',
				type : 'scatter',
				coordinateSystem : 'geo',
				data : convertData(data),
				roam: "scale",
				scaleLimit: { min: 0.8, max: 10.9 },//缩放
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
						formatter : '{b}',
						position : 'right',
						show : true
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


		function refreshData(data, geoCoordMap1) {
			if (!myChart) {
				return;
			}

			
	/* 		alert(data);
			alert(data[0].name);
			alert(data[0].value); */
			
			//更新数据
			var option = myChart.getOption();
			option.series[0].data = convertData(data);
			geoCoordMap = geoCoordMap1;
			myChart.setOption(option, true);
		}

		
		
	</script>



	<script type="text/javascript">
		var Chat = {};

		Chat.socket = null;

		Chat.connect = (function(host) {
			if ('WebSocket' in window) {
				Chat.socket = new WebSocket(host);
			} else if ('MozWebSocket' in window) {
				Chat.socket = new MozWebSocket(host);
			} else {
				Console
						.log('Error: WebSocket is not supported by this browser.');
				return;
			}

			Chat.socket.onopen = function() {
				Console.log('Info: WebSocket connection opened.');
				Chat.sendMessage();
			};

			Chat.socket.onclose = function() {
				//document.getElementById('chat').onkeydown = null;  
				Console.log('Info: WebSocket closed.');
			};

			Chat.socket.onmessage = function(message) {
				Console.log(message.data);
			};
		});

		Chat.initialize = function() {
			if (window.location.protocol == 'http:') {
				Chat.connect('ws://' + window.location.host + '/ect/highmap');
			} else {
				Chat.connect('wss://' + window.location.host + '/ect/highmap');
			}
		};

		Chat.sendMessage = (function() {

			Chat.socket.send("1");

		});

		var Console = {};


		Console.log = (function(message) {
		//	alert(message);
			var geoCoordMap2 = {};
			var date2 = [];
			if (message.indexOf("coordinate") > 0) {  //坐标数据
//				alert(message);
				var msg = eval('('+message+')'); 
				geoCoordMap2 = msg.coordinate;
				for ( var name in geoCoordMap2) {
					var geoCoord = geoCoordMap2[name];
					if (geoCoord) {
						date2.push({
							name : name,
							value : "30"
						});
					}
				}
				//alert("121");
				refreshData(date2, geoCoordMap2);
			}else if (message.indexOf("generalticket") > 0) {
				var msg = eval('('+message+')'); 
				document.getElementById("price").innerHTML = formatNumber(msg.generalticket.totalprice) + "元";
				document.getElementById("time").innerHTML = msg.generalticket.time;
			}

		});

		Chat.initialize();

		document.addEventListener("DOMContentLoaded", function() {
			// Remove elements with "noscript" class - <noscript> is not allowed in XHTML  
			var noscripts = document.getElementsByClassName("noscript");
			for (var i = 0; i < noscripts.length; i++) {
				noscripts[i].parentNode.removeChild(noscripts[i]);
			}
		}, false);
		
		
		
		function formatNumber(num) {
		    if (!/^(\+|-)?(\d+)(\.\d+)?$/.test(num)) {
		        return num;
		    }
		    var a = RegExp.$1, b = RegExp.$2, c = RegExp.$3;
		    var re = new RegExp("(\\d)(\\d{3})(,|$)");
		    while (re.test(b))   b = b.replace(re, "$1,$2$3");
		    return a + "" + b + "" + c;
		}
		

		
	</script>





</body>
</html>