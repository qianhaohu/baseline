package com.deppon.baseline.bean;

/**
 * T_ORA_IOSTAT
 * @author 022484
 *
 */
public class IOPSBean {

	private String dbname;
	private String bizdate;
	private Number mbps;
	private Number iops;
	private Number io_latency;

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getBizdate() {
		return bizdate;
	}

	public void setBizdate(String bizdate) {
		this.bizdate = bizdate;
	}

	public Number getMbps() {
		return mbps;
	}

	public void setMbps(Number mbps) {
		this.mbps = mbps;
	}

	public Number getIops() {
		return iops;
	}

	public void setIops(Number iops) {
		this.iops = iops;
	}

	public Number getIo_latency() {
		return io_latency;
	}

	public void setIo_latency(Number io_latency) {
		this.io_latency = io_latency;
	}

}
