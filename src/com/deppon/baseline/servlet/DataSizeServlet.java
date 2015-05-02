package com.deppon.baseline.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deppon.baseline.bean.DataSizeBean;
import com.deppon.baseline.dao.DataSizeDao;
import com.deppon.baseline.domain.Chart;
import com.deppon.baseline.domain.ChartType;
import com.deppon.baseline.domain.Highchart;
import com.deppon.baseline.domain.Serie;
import com.deppon.baseline.domain.Title;
import com.deppon.baseline.domain.XAxis;
import com.deppon.baseline.domain.YAxis;
import com.google.gson.Gson;

/**
 * Servlet implementation class DataSizeServlet
 */
@WebServlet("/DataSizeServlet")
public class DataSizeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataSizeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String dbname = request.getParameter("dbname");
		String bizdate = request.getParameter("bizdate");
		String item_name = request.getParameter("item_name");

		DataSizeDao dao = new DataSizeDao();

		List<DataSizeBean> list = new ArrayList();

		// 数组大小，初始化0
		int rows = 0;

		try {
			// list
			list = dao.getDataSizeInfo(dbname, item_name, bizdate);
			// 返回记录数，转换为数组后的大小
			rows = list.size();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// X轴数据
		String[] xAxisArry = new String[rows];
		// Y
		Number[] itemValueArr = new Number[rows];

		// 取出每条数据分别填入数组中
		for (int i = 0; i < list.size(); i++) {
			DataSizeBean item = list.get(i);
			xAxisArry[i] = item.getBizdate();
			itemValueArr[i] = item.getItem_value();
		}

		Chart chart = new Chart();
		chart.setRenderTo("container");
		chart.setType(ChartType.CHART_LINE);

		Title title = new Title();
		title.setText("系统数据指标");

		XAxis xAxis = new XAxis();
		// X轴数据
		xAxis.setCategories(xAxisArry);

		YAxis yAxis = new YAxis();
		Title yTitle = new Title();
		yTitle.setText("值(GB)");
		yAxis.setTitle(yTitle);

		// Y轴数据
		//
		Serie itemValueSerie = new Serie(item_name, itemValueArr);


		//
		List<Serie> series = new ArrayList<Serie>();
		series.add(itemValueSerie);


		Highchart highchart = new Highchart();
		highchart.setChart(chart);
		highchart.setTitle(title);
		highchart.setxAxis(xAxis);
		highchart.setyAxis(yAxis);
		highchart.setSeries(series);

		Gson gson = new Gson();

		PrintWriter out = response.getWriter();
		out.print(gson.toJson(highchart));
		System.out.println("JSON:" + gson.toJson(highchart));
		out.flush();
		out.close();
	}


}
