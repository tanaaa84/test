<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html, #allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}
/* #allmap{height:600px;width:100%;} */
#r-result {
	width: 100%;
}

.anchorBL {
	display: none
}
</style>


<style type="text/css">
.shape {
	width: 200px;
	height: 140px;
	position: fixed !important;
	position: absolute;
	left: 5px;
	bottom: 5px !important;
	bottom: auto;
	top: expression(eval(document.compatMode && document.compatMode ==
		'CSS1Compat')? documentElement.scrollTop+(documentElement.clientHeight-
		 this.clientHeight):document.body.scrollTop+(document.body.clientHeight-
		 this.clientHeight));
}

#btn {
	width: 300px;
	height: 200px;
	background: #0b312f;
	opacity:0.6;
	color:yellow;
	
}
</style>


<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=E89cccc4b5854636f959a26a959a7a59"></script>
<title>实时大屏幕</title>
</head>
<body>
	<div id="allmap"></div>
	<div class="shape">
		<div id="btn"></div>
	</div>

</body>
</html>

<script type="text/javascript">
	var map = new BMap.Map('allmap');
	map.centerAndZoom(new BMap.Point(10.404269, 39.916042), 2);
	map.enableScrollWheelZoom(); // 允许滚轮缩放
	var mapStyle = {
		features : [ "road", "building", "water", "land" ],//隐藏地图上的poi
		style : "midnight" //设置地图风格为高端黑
	}
	map.setMapStyle(mapStyle);
	var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件      
	map.addControl(top_left_navigation);    
	
	
	

	document.getElementById("btn").innerHTML = "<br/> 机票销售总计：2189320元<br/><br/>  里程换票总计：232900元</br><br/>  付费选座总计：150000元</br>";


	// //创建小狐狸
	//  var pt = new BMap.Point(116.417, 39.909);
	//  var myIcon = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/fox.gif", new BMap.Size(300,157));
	//  var marker2 = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
	//  map.addOverlay(marker2);              // 将标注添加到地图中
	//   //  map.removeOverlay(marker2);

	// 编写自定义函数,创建标注
	function addMarker(point) {
		//alert(point);
		var marker = new BMap.Marker(point);
		map.addOverlay(marker);

		setTimeout(function() {
			map.removeOverlay(marker); //add your code
		}, 2 * 1000);//延迟5000毫米
	}

	//var point1 = new BMap.Point(113.1180110311, 41.1285303362);
	//addMarker(point1);

	//var point2 = new BMap.Point(119.1349957807, 33.7877334408);
	//addMarker(point2); 

	////////////////////////
	var Chat = {};

	Chat.socket = null;

	Chat.connect = (function(host) {
		if ('WebSocket' in window) {
			Chat.socket = new WebSocket(host);
		} else if ('MozWebSocket' in window) {
			Chat.socket = new MozWebSocket(host);
		} else {
			Console.log('Error: WebSocket is not supported by this browser.');
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
			Chat.connect('ws://' + window.location.host + '/ect/flowmap');
		} else {
			Chat.connect('wss://' + window.location.host + '/ect/flowmap');
		}
	};

	Chat.sendMessage = (function() {

		Chat.socket.send("1");

	});

	var Console = {};

	Console.log = (function(message) {
		//   	 alert("11");
		// var console = document.getElementById('console');  
		var p = document.createElement('p');
		p.style.wordWrap = 'break-word';
		p.innerHTML = message;

		//alert(message);

		if (message.indexOf(",") > 0) {
			var array = message.split(",");
			var point5 = new BMap.Point(array[0], array[1]);
			addMarker(point5);
			//alert(array[0]+"-" +array[1]);
		}

		//         console.appendChild(p);  
		//       while (console.childNodes.length > 25) {  
		//         console.removeChild(console.firstChild);  
		//   }  
		// console.scrollTop = console.scrollHeight;  
	});

	Chat.initialize();

	document.addEventListener("DOMContentLoaded", function() {
		// Remove elements with "noscript" class - <noscript> is not allowed in XHTML  
		var noscripts = document.getElementsByClassName("noscript");
		for (var i = 0; i < noscripts.length; i++) {
			noscripts[i].parentNode.removeChild(noscripts[i]);
		}
	}, false);

	////////////////////////TODO

	function checkhHtml5() {
		if (typeof (Worker) === "undefined") {
			if (navigator.userAgent.indexOf("MSIE 9.0") <= 0) {
				alert("定制个性地图示例：IE9以下不兼容，推荐使用百度浏览器、chrome、firefox、safari、IE10");
			}
		}
	}
	checkhHtml5();
</script>