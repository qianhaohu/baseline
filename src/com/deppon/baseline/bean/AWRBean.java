package com.deppon.baseline.bean;

/**
 * 业务数据，对应T_ORA_SYSSTAT表
 * 
 * @author 022484
 *
 */

public class AWRBean {

	private String dbname;
	private Integer inst_no;
	
	//由snap_date、snap_time拼接的字符串
	private String bizdate;
	private String snap_date;
	private String snap_time;
	private Number elapse_min;
	private Number dbtime_min;
	private Number redo;
	private Number redo_ps;
	private Number logicalreads;
	private Number logicalreads_ps;
	private Number physicalreads;
	private Number physicalreads_ps;
	private Number execs;
	private Number exec_ps;
	private Number parse;
	private Number parse_ps;
	private Number hardparse;
	private Number hardparse_ps;
	private Number trans;
	private Number trans_ps;

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public Integer getInst_no() {
		return inst_no;
	}

	public void setInst_no(Integer inst_no) {
		this.inst_no = inst_no;
	}

	public String getSnap_date() {
		return snap_date;
	}

	public void setSnap_date(String snap_date) {
		this.snap_date = snap_date;
	}

	public String getSnap_time() {
		return snap_time;
	}

	public void setSnap_time(String snap_time) {
		this.snap_time = snap_time;
	}

	public Number getElapse_min() {
		return elapse_min;
	}

	public void setElapse_min(Number elapse_min) {
		this.elapse_min = elapse_min;
	}

	public Number getDbtime_min() {
		return dbtime_min;
	}

	public void setDbtime_min(Number dbtime_min) {
		this.dbtime_min = dbtime_min;
	}

	public Number getRedo() {
		return redo;
	}

	public void setRedo(Number redo) {
		this.redo = redo;
	}

	public Number getRedo_ps() {
		return redo_ps;
	}

	public void setRedo_ps(Number redo_ps) {
		this.redo_ps = redo_ps;
	}

	public Number getLogicalreads() {
		return logicalreads;
	}

	public void setLogicalreads(Number logicalreads) {
		this.logicalreads = logicalreads;
	}

	public Number getLogicalreads_ps() {
		return logicalreads_ps;
	}

	public void setLogicalreads_ps(Number logicalreads_ps) {
		this.logicalreads_ps = logicalreads_ps;
	}

	public Number getPhysicalreads() {
		return physicalreads;
	}

	public void setPhysicalreads(Number physicalreads) {
		this.physicalreads = physicalreads;
	}

	public Number getPhysicalreads_ps() {
		return physicalreads_ps;
	}

	public void setPhysicalreads_ps(Number physicalreads_ps) {
		this.physicalreads_ps = physicalreads_ps;
	}

	public Number getExecs() {
		return execs;
	}

	public void setExecs(Number execs) {
		this.execs = execs;
	}

	public Number getExec_ps() {
		return exec_ps;
	}

	public void setExec_ps(Number exec_ps) {
		this.exec_ps = exec_ps;
	}

	public Number getParse() {
		return parse;
	}

	public void setParse(Number parse) {
		this.parse = parse;
	}

	public Number getParse_ps() {
		return parse_ps;
	}

	public void setParse_ps(Number parse_ps) {
		this.parse_ps = parse_ps;
	}

	public Number getHardparse() {
		return hardparse;
	}

	public void setHardparse(Number hardparse) {
		this.hardparse = hardparse;
	}

	public Number getHardparse_ps() {
		return hardparse_ps;
	}

	public void setHardparse_ps(Number hardparse_ps) {
		this.hardparse_ps = hardparse_ps;
	}

	public Number getTrans() {
		return trans;
	}

	public void setTrans(Number trans) {
		this.trans = trans;
	}

	public Number getTrans_ps() {
		return trans_ps;
	}

	public void setTrans_ps(Number trans_ps) {
		this.trans_ps = trans_ps;
	}

	public String getBizdate() {
		return bizdate;
	}

	public void setBizdate(String bizdate) {
		this.bizdate = bizdate;
	}

}
