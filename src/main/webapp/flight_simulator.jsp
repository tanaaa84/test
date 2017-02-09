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
	<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=E89cccc4b5854636f959a26a959a7a59"></script>
 -->	
 	<script type="text/javascript" src="js/bmap.min.js"></script>
	<script type="text/javascript">
	 var dom = document.getElementById("container");
	 var myChart = echarts.init(dom);
	 var app = {};
	 option = null;
	 var geoCoordMap = {
	     '上海': [121.4648,31.2891],
	     '东莞': [113.8953,22.901],
	     '东营': [118.7073,37.5513],
	     '中山': [113.4229,22.478],
	     '临汾': [111.4783,36.1615],
	     '临沂': [118.3118,35.2936],
	     '丹东': [124.541,40.4242],
	     '丽水': [119.5642,28.1854],
	     '乌鲁木齐': [87.9236,43.5883],
	     '佛山': [112.8955,23.1097],
	     '保定': [115.0488,39.0948],
	     '兰州': [103.5901,36.3043],
	     '包头': [110.3467,41.4899],
	     '北京': [116.4551,40.2539],
	     '韶关': [113.7964,24.7028]
	 };

	 var BJData = [
	     [{name:'北京'}, {name:'上海',value:95}],
	     [{name:'北京'}, {name:'兰州',value:90}]
	 ];

	 var SHData = [
	     [{name:'上海'},{name:'佛山',value:95}],
	     [{name:'上海'},{name:'乌鲁木齐',value:10}]
	 ];

	 var GZData = [
	     [{name:'临沂'},{name:'丹东',value:95}],
	     [{name:'临沂'},{name:'中山',value:10}]
	 ];

	 var planePath = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';

	 var convertData = function (data) {
	     var res = [];
	     for (var i = 0; i < data.length; i++) {
	         var dataItem = data[i];
	         var fromCoord = geoCoordMap[dataItem[0].name];
	         var toCoord = geoCoordMap[dataItem[1].name];
	         if (fromCoord && toCoord) {
	             res.push({
	                 fromName: dataItem[0].name,
	                 toName: dataItem[1].name,
	                 coords: [fromCoord, toCoord]
	             });
	         }
	     }
	     return res;
	 };

	 var color = ['#a6c84c', '#ffa022', '#46bee9'];
	 var series = [];
	 [['北京', BJData], ['上海', SHData], ['临沂', GZData]].forEach(function (item, i) {
	     series.push(
	     {
	         name: item[0] + ' Top10',
	         type: 'lines',
	         zlevel: 2,
	         symbol: ['none', 'arrow'],
	         symbolSize: 10,
	         effect: {
	             show: true,
	             period: 6,
	             trailLength: 0,
	             symbol: planePath,
	             symbolSize: 15
	         },
	         lineStyle: {
	             normal: {
	                 color: color[i],
	                 width: 1,
	                 opacity: 0.6,
	                 curveness: 0.2
	             }
	         },
	         data: convertData(item[1])
	     });
	 });

	 option = {
	     backgroundColor: '#404a59',
	     title : {
	         text: '模拟航线',
	         subtext: 'Data from Airchina APP',
	         left: 'center',
	         textStyle : {
	             color: '#fff'
	         }
	     },
	     tooltip : {
	         trigger: 'item'
	     },
	     
	     geo: {
	         map: 'china',
	         label: {
	             emphasis: {
	                 show: false
	             }
	         },
	         roam: true,
	         itemStyle: {
	             normal: {
	                 areaColor: '#323c48',
	                 borderColor: '#404a59'
	             },
	             emphasis: {
	                 areaColor: '#2a333d'
	             }
	         }
	     },
	     series: series
	 };;
	 if (option && typeof option === "object") {
	     myChart.setOption(option, true);
	 }
	</script>





</body>
</html>