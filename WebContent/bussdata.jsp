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
<title>业务数据</title>
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/highcharts.js"></script>

<script>
	$(function() {

		//首次显示
		url = "BussinessDataServlet?dbname=fossdb&bizdate=day&item_name=快递";
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
			item_name = $("#item_name").children('option:selected').val();
			//拼装URL地址，传入参数dbname和item_name
			url = "BussinessDataServlet?dbname=" + dbname + "&bizdate=" + bizdate + "&item_name=" + item_name;
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
		<option value="fossdb" selected="selected">fossdb</option>
		<option value="finance">finance</option>
	</select> 
	<select id="item_name">
		<option value="零担" selected="selected">零担</option>
		<option value="快递">快递</option>
		<option value="装车">装车</option>
		<option value="签收">卸车</option>
		<option value="T_GL_VOUCHER">凭证</option>
		<option value="T_GL_VOUCHERENTRY">凭证分录</option>
		<option value="T_GL_ACCOUNTBALANCE">科目余额</option>
		<option value="T_GL_ASSISTBALANCE">辅助账余额</option>
	</select>
	<select id="bizdate">
		<option value="day" selected="selected">一天</option>
		<option value="week">一周</option>
		<option value="month">一个月</option>
		<option value="2month">两个月</option>
	</select> 

	<div id="container" style="min-width: 800px; height: 450px;"></div>
</body>
</html>