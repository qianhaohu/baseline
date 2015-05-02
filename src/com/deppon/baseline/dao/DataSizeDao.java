package com.deppon.baseline.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.deppon.baseline.bean.DataSizeBean;
import com.deppon.baseline.utils.DBManager;

public class DataSizeDao {

	public List<DataSizeBean> getDataSizeInfo(String dbname, String item_name, String bizdate) throws SQLException {
		QueryRunner runner = new QueryRunner(DBManager.getDataSource());
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT TO_CHAR(BIZDATE, 'yyyy-mm-dd') BIZDATE, t.dbname,t.item_name,t.item_value,t.unit,t.freq  "
				+ " FROM T_ORA_DATASIZE t  "
				+ "WHERE DBNAME = ? AND ITEM_NAME = ? ");
		switch(bizdate){
		case "day":
			sql.append(" and t.bizdate>=sysdate-2");
			break;
		case "week":
			sql.append(" and t.bizdate>=sysdate-7");
			break;
		case "month":
			sql.append(" and t.bizdate>=sysdate-30");
			break;
		case "2month":
			sql.append(" and t.bizdate>=sysdate-60 ");
			break;
		}
		
		sql.append(" ORDER BY t.BIZDATE");

		List<DataSizeBean> list = (List<DataSizeBean>) runner.query(sql.toString(),
				new BeanListHandler(DataSizeBean.class), dbname, item_name);
		
		return list;
	}


}
