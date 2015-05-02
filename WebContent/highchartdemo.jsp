<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/highcharts.js"></script>

<script type="text/javascript">
	$(function() {
		//从后台获取json格式的数据  
		$.getJSON("HightChartServlet", function(data) {
			//初始化chart  
			var chart = new Highcharts.Chart(data);
		});

	});
</script>
</head>

<body>
	<h1>HighChart Demo!</h1>

	<div id="container" style="min-width: 800px; height: 450px;"></div>
</body>
</html>