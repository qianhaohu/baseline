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
<script>
	$(function() {

		var options = {
			chart : {
				type : 'line', //指定图表的类型，默认是折线图（line）  
				renderTo : 'container'
			},
			title : {
				text : '每日归档量' //指定图表标题  
			},
			/*  xAxis : {  
			     //categories : [ 'item_value'], //指定x轴分组  
			 		//type: 'datetime'
			 },   */
			xAxis : {
				categories : []
			},
			yAxis : {
				title : {
					text : '归档量（GB）' // 指定y轴的标题  
				}
			},
			tooltip : {
				valueSuffix : 'GB'
			},
			series : [ {
				//  name: '归档',
				data : [ 0 ]
			//data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
			} ]

		};
		//初始化chart  
		//var chart = new Highcharts.Chart(options); 
		var url = 'DataSizeServlet?dbname=finance'
		$.getJSON(url, function(data) {
			//定义数组
			var dataAry = new Array();
			var xarray = new Array();
			//options.name='归档';
			$.each(data, function(i, d) {
				//循环添加到数组中
				dataAry.push(d.item_value);
				xarray.push(d.bizdate);
			});
			options.series[0].name = '归档大小';
			options.xAxis.categories = xarray;
			options.series[0].data = dataAry;
			var chart = new Highcharts.Chart(options);
		});

		$("button").click(function() {
			url = "DataSizeServlet?dbname=" + $(this).text();
			//从后台获取json格式的数据  
			$.getJSON(url, function(data) {
				//定义数组
				var dataAry = new Array();
				var xarray = new Array();
				//options.name='归档';
				$.each(data, function(i, d) {
					//循环添加到数组中
					dataAry.push(d.item_value);
					xarray.push(d.bizdate);
				});
				options.series[0].name = '归档大小';
				options.xAxis.categories = xarray;
				options.series[0].data = dataAry;
				var chart = new Highcharts.Chart(options);
			});
		});
	});
</script>
</head>

<body>
	<p></p>
	<button id="btn1">finance</button>
	<button id="btn2">fossdb</button>
	<button id="btn3">finself</button>
	<button id="btn4">fsscdb</button>

	<div id="container" style="min-width: 800px; height: 400px;"></div>
</body>
</html>