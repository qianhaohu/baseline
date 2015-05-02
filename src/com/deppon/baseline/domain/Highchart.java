package com.deppon.baseline.domain;

import java.util.List;  

public class Highchart implements java.io.Serializable {  
  
    private static final long serialVersionUID = -5880168163194932425L;  
  
    private Chart chart;  
  
    private Title title;  
  
    private XAxis xAxis;  
  
    private YAxis yAxis;  
  
    private List<Serie> series;  
  
    public Chart getChart() {  
        return chart;  
    }  
  
    public void setChart(Chart chart) {  
        this.chart = chart;  
    }  
  
    public Title getTitle() {  
        return title;  
    }  
  
    public void setTitle(Title title) {  
        this.title = title;  
    }  
  
    public XAxis getxAxis() {  
        return xAxis;  
    }  
  
    public void setxAxis(XAxis xAxis) {  
        this.xAxis = xAxis;  
    }  
  
    public YAxis getyAxis() {  
        return yAxis;  
    }  
  
    public void setyAxis(YAxis yAxis) {  
        this.yAxis = yAxis;  
    }  
  
    public List<Serie> getSeries() {  
        return series;  
    }  
  
    public void setSeries(List<Serie> series) {  
        this.series = series;  
    }  
  
}  