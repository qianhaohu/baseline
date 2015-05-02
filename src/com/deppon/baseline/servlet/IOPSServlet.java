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

import com.deppon.baseline.bean.IOPSBean;
import com.deppon.baseline.dao.IOPSDao;
import com.deppon.baseline.domain.Chart;
import com.deppon.baseline.domain.ChartType;
import com.deppon.baseline.domain.Highchart;
import com.deppon.baseline.domain.Serie;
import com.deppon.baseline.domain.Title;
import com.deppon.baseline.domain.XAxis;
import com.deppon.baseline.domain.YAxis;
import com.google.gson.Gson;

@WebServlet("/IOPSServlet")
public class IOPSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IOPSServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String dbname = request.getParameter("dbname");
		String bizdate = request.getParameter("bizdate");

		IOPSDao dao = new IOPSDao();
		List<IOPSBean> list = new ArrayList();
		int rows=0;
		try {
			list = dao.getIOPSByDBname(dbname, bizdate);
			rows=list.size();
			System.out.println(list);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String[] xAxisArry = new String[rows];
		Number[] yAxisIOPSArr = new Number[rows];
		Number[] yAxisMBPSArr =  new Number[rows];
		Number[] yAxisIOLatencyArr =  new Number[rows];
		for (int i = 0; i < list.size(); i++) {
			IOPSBean item = list.get(i);
			xAxisArry[i] = item.getBizdate();
			yAxisIOPSArr[i] = item.getIops();
			yAxisMBPSArr[i] = item.getMbps();
			yAxisIOLatencyArr[i] = item.getIo_latency();
		}

		Chart chart = new Chart();
		chart.setRenderTo("container");
		chart.setType(ChartType.CHART_LINE);

		Title title = new Title();
		title.setText("数据库IO性能数据");

		XAxis xAxis = new XAxis();
		// X轴数据
		xAxis.setCategories(xAxisArry);

		YAxis yAxis = new YAxis();
		Title yTitle = new Title();
		yTitle.setText("IO");
		yAxis.setTitle(yTitle);

		// Y轴数据
		Serie iops = new Serie("IOPS(请求数/s)", yAxisIOPSArr);
		Serie mbps = new Serie("MBPS(MB/s)", yAxisMBPSArr);
		Serie iolatency = new Serie("IO_Latency(IO延迟ms)", yAxisIOLatencyArr);

		//
		List<Serie> series = new ArrayList<Serie>();
		series.add(iops);
		series.add(mbps);
		series.add(iolatency);

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
