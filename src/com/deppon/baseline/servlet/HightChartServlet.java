package com.deppon.baseline.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deppon.baseline.domain.Chart;
import com.deppon.baseline.domain.ChartType;
import com.deppon.baseline.domain.Highchart;
import com.deppon.baseline.domain.Serie;
import com.deppon.baseline.domain.Title;
import com.deppon.baseline.domain.XAxis;
import com.deppon.baseline.domain.YAxis;
import com.google.gson.Gson;

@WebServlet("/HightChartServlet")
public class HightChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HightChartServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");    
		response.setContentType("text/html;charset=utf-8");  
	         
	    Chart chart = new Chart();  
	    chart.setRenderTo("container");  
	    chart.setType(ChartType.CHART_COLUMN);  
	  
	    Title title = new Title();  
	    title.setText("我的第1个Highcarts图表！");  
	  
	    XAxis xAxis = new XAxis();  
	    xAxis.setCategories(new String[] { "my", "first", "chart" });  
	  
	    YAxis yAxis = new YAxis();  
	    Title yTitle = new Title();  
	    yTitle.setText("Y轴标题");  
	    yAxis.setTitle(yTitle);  
	  
	    Serie data_jane = new Serie("Jane", new Integer[] { 1, 0, 4 });  
	    Serie data_john = new Serie("Jone", new Integer[] { 5, 7, 3 });  
	  
	    List<Serie> series = new ArrayList<Serie>();  
	    series.add(data_jane);  
	    series.add(data_john);  
	  
	    Highchart highchart = new Highchart();  
	    highchart.setChart(chart);  
	    highchart.setTitle(title);  
	    highchart.setxAxis(xAxis);  
	    highchart.setyAxis(yAxis);  
	    highchart.setSeries(series);  
	  
	    Gson gson = new Gson();  
	      
	    PrintWriter out = response.getWriter();  
	    out.print(gson.toJson(highchart));  
	    System.out.println("JSON:"+gson.toJson(highchart));
	    out.flush();  
	    out.close();  
	}
}
