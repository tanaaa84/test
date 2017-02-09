<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title> 


<script type="text/javascript">

var str = "{\"coordinate\":{\"8299\":[108.91204,34.634128],\"8300\":[26.055876,44.438386],\"8301\":[114.07558,27.714996],\"8302\":[120.10683,30.270292],\"8303\":[116.30364,39.934093],\"8304\":[117.47577,26.716769]}}";


var str1 = "{\"a\": {\"b\": \"aaa\",\"c\": \"vv\"}}";

var str2 = eval('('+str1+')'); 

alert(str2.a.b); 
alert(str1); 




</script>


</head>
<body>

<button type="button">Click Me!</button>

</body>
</html>