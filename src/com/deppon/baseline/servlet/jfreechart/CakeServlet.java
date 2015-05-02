package com.deppon.baseline.servlet.jfreechart;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
/**
 * 生成饼状统计图
 * @说明 
 * @author cuisuqiang
 * @version 1.0
 * @since
 */
@SuppressWarnings("serial")
public class CakeServlet extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// 默认数据类型
		DefaultPieDataset dataType = new DefaultPieDataset();
		// 数据参数 内容，数量
		dataType.setValue("IE6", 156);
		dataType.setValue("IE7", 230);
		dataType.setValue("IE8", 45);
		dataType.setValue("火狐", 640);
		dataType.setValue("谷歌", 245);
		try {
			DefaultPieDataset data = dataType;
			// 生成普通饼状图除掉 3D 即可
			// 生产3D饼状图
			PiePlot3D plot = new PiePlot3D(data);
			JFreeChart chart = new JFreeChart(
					"用户使用的浏览器类型",            // 图形标题
					JFreeChart.DEFAULT_TITLE_FONT, // 标题字体
					plot,                          // 图标标题对象
					true                           // 是否显示图例
			);
			// 设置整个图片的背景色
			chart.setBackgroundPaint(Color.PINK);
			// 设置图片有边框
			chart.setBorderVisible(true);
			// 配置字体
			Font kfont = new Font("宋体", Font.PLAIN, 12);    // 底部
			Font titleFont = new Font("宋体", Font.BOLD, 25); // 图片标题
			// 图片标题
			chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
			// 底部
			chart.getLegend().setItemFont(kfont);
			ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,
					chart, 500, 300, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}