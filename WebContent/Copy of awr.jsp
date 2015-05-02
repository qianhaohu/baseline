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
<script src="js/jquery-1.8.3.min.js"></script>
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
					text : '大小（GB）' // 指定y轴的标题  
				}
			},
			tooltip : {
				valueSuffix : 'GB' //
			},
			series : [ {
				name : '',
				data : [ 0 ]
			} ]

		};

		

		//下拉列表改变事件：包括数据库和数据类型
		$("select").change(function() {

			//如果选中的是AWR性能数据
			//获取当前选中的dbname
			dbname = $("#dbname").children('option:selected').val();
			bizdate = $("#bizdate").children('option:selected').val();
			//拼装URL地址，传入参数dbname和item_name
			url = "AWRServlet?dbname=" + dbname+"&bizdate="+bizdate;
			//从后台获取json格式的数据  
			$.getJSON(url, function(data) {
				var viewAry = new Array();
				var xarray = new Array();
				//循环添加到数组中
				$.each(data, function(i, chartInfo) {

					var yarray = new Array();
					//x轴累加
					for (var x = 0; x < chartInfo.chartDatas.length; x++) {
						xarray.push(chartInfo.chartDatas[x].x_coordinate);
					}
					//y数据
					for (var y = 0; y < chartInfo.chartDatas.length; y++) {
						yarray.push(chartInfo.chartDatas[y].y_coordinate);
					}
					var view = {
						name : chartInfo.seriesName,
						data : yarray
					};
					viewAry.push(view);
					
				});
				
				options.series = viewAry;
				options.xAxis.categories = xarray;

				//指定图表标题
				options.title.text = 'AWR性能指标';
				var chart = new Highcharts.Chart(options);
			});
		});

	});
</script>
</head>

<body>
	<h1>Welcome to Performance BaseLine Repository!</h1>

	数据库：
	<select id="dbname">
		<option value="fossdb">fossdb</option>
		<option value="finance" selected="selected">finance</option>
		<option value="finself">finself</option>
		<option value="fsscdb">fsscdb</option>
	</select> 
	<select id="bizdate">
		<option value="day">一天</option>
		<option value="week" selected="selected">一周</option>
		<option value="month">一个月</option>
		<option value="3month">三个月</option>
	</select> 

	<div id="container" style="min-width: 800px; height: 450px;"></div>
</body>
</html>