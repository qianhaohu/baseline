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
<title>IOPS</title>
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/highcharts.js"></script>

<script>
	$(function() {
	
		//首次显示
		url = "IOPSServlet?dbname=finance&bizdate=day";
		$.getJSON(url, function(data) {
			//初始化chart  
			var chart = new Highcharts.Chart(data);
		});
		//下拉列表改变事件：包括数据库和数据类型
		$("select").change(function() {

			//如果选中的是AWR性能数据
			//获取当前选中的dbname
			dbname = $("#dbname").children('option:selected').val();
			bizdate = $("#bizdate").children('option:selected').val();
			//拼装URL地址，传入参数dbname和item_name
			url = "IOPSServlet?dbname=" + dbname + "&bizdate=" + bizdate;
			//从后台获取json格式的数据  
			$.getJSON(url, function(data) {
				//初始化chart  
				var chart = new Highcharts.Chart(data);
			});
		});
	});
</script>
</head>

<body>
	<h1>Welcome to Performance BaseLine Repository!</h1>

	数据库：
	<select id="dbname">
		<option value="FINANCE_XD">finance_xd</option>
		<option value="SMSDBRAC">SMSDBRAC</option>
		<option value="bpsdb">bpsdb</option>
		<option value="crmdb">crmdb</option>
		<option value="dpcc">dpcc</option>
		<option value="esbdb">esbdb</option>
		<option value="finance" selected="selected">finance</option>
		<option value="finself">finself</option>
		<option value="fossdb">fossdb</option>
		<option value="fsscdb">fsscdb</option>
		<option value="gisdb">gisdb</option>
		<option value="reportdb">reportdb</option>	
	</select>
	<select id="bizdate">
		<option value="day" selected="selected">一天内</option>
		<option value="week">一周内</option>
		<option value="month">一个月内</option>
		<option value="2month">两个月内</option>
	</select>

	<div id="container" style="min-width: 800px; height: 450px;"></div>
</body>
</html>