package com.deppon.baseline.bean;

/**
 * 业务数据，对应t_business_data表
 * @author 022484
 *
 */

public class BusinessDataBean {

	private String dbname;
	private String bizdate;
	private String item_name;
	private Number item_value;
	private String unit;
	private String freq;
	private String remark;

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public Number getItem_value() {
		return item_value;
	}

	public void setItem_value(Number item_value) {
		this.item_value = item_value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getFreq() {
		return freq;
	}

	public void setFreq(String freq) {
		this.freq = freq;
	}

	public String getBizdate() {
		return bizdate;
	}

	public void setBizdate(String bizdate) {
		this.bizdate = bizdate;
	}

	@Override
	public String toString() {
		return "BusinessDataBean [dbname=" + dbname + ", item_name=" + item_name
				+ ", item_value=" + item_value + ", unit=" + unit + ", freq="
				+ freq + ", bizdate=" + bizdate + "]";
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
