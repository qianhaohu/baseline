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

import com.deppon.baseline.bean.AWRBean;
import com.deppon.baseline.dao.AWRDao;
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
@WebServlet("/AWRServlet")
public class AWRServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AWRServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");

		String dbname = request.getParameter("dbname");
		String bizdate = request.getParameter("bizdate");
		// String inst_no = request.getParameter("inst_no");

		AWRDao dao = new AWRDao();

		List<AWRBean> list = new ArrayList();
		// 实例数
		Integer instnum = 1;
		// 数组大小，初始化0
		int rows = 0;

		try {
			// 数据库对应的实例数
			// instnum = dao.getInstancesByDBname(dbname);
			// AWR info
			list = dao.getAWRInfoByDBname(dbname, bizdate);
			// 返回记录数，转换为数组后的大小
			rows = list.size();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// X轴数据
		String[] xAxisArry = new String[rows];
		// 每秒逻辑读
		Number[] logicalReadsArr = new Number[rows];
		// 每秒物理读
		Number[] physicalReadsArr = new Number[rows];
		// 每秒解析次数
		Number[] parseArr = new Number[rows];
		// 每秒硬解析次数
		Number[] hardparseArr = new Number[rows];
		// 每秒执行次数
		Number[] execArr = new Number[rows];
		// 每秒trans_ps
		Number[] transArr = new Number[rows];

		// 取出每条数据分别填入数组中
		for (int i = 0; i < list.size(); i++) {
			AWRBean item = list.get(i);
			xAxisArry[i] = item.getBizdate();
			logicalReadsArr[i] = item.getLogicalreads_ps();
			physicalReadsArr[i] = item.getPhysicalreads_ps();
			parseArr[i] = item.getParse_ps();
			hardparseArr[i] = item.getHardparse_ps();
			execArr[i] = item.getExec_ps();
			transArr[i] = item.getTrans_ps();
		}

		Chart chart = new Chart();
		chart.setRenderTo("container");
		chart.setType(ChartType.CHART_LINE);

		Title title = new Title();
		title.setText("AWR性能数据");

		XAxis xAxis = new XAxis();
		// X轴数据
		xAxis.setCategories(xAxisArry);

		YAxis yAxis = new YAxis();
		Title yTitle = new Title();
		yTitle.setText("value");
		yAxis.setTitle(yTitle);

		// Y轴数据
		//
		Serie logicalReadsSerie = new Serie("逻辑读(GB)", logicalReadsArr);
		Serie physicalReadsSerie = new Serie("物理读(GB)", physicalReadsArr);
		Serie parseSerie = new Serie("解析次数", parseArr);
		Serie hardparseSerie = new Serie("硬解析次数", hardparseArr);
		Serie execSerie = new Serie("执行次数", execArr);
		Serie transSerie = new Serie("事务数", transArr);

		//
		List<Serie> series = new ArrayList<Serie>();
		series.add(logicalReadsSerie);
		series.add(physicalReadsSerie);
		series.add(parseSerie);
		series.add(hardparseSerie);
		series.add(execSerie);
		series.add(transSerie);

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
