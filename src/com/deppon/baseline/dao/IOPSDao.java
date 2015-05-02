package com.deppon.baseline.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.deppon.baseline.bean.BaseCntBean;
import com.deppon.baseline.bean.IOPSBean;
import com.deppon.baseline.utils.DBManager;

public class IOPSDao {

	/**
	 * IOPS
	 * 
	 * @param dbname
	 * @param bizdate
	 * @throws SQLException
	 */
	public List<IOPSBean> getIOPSByDBname(String dbname, String bizdate)
			throws SQLException {
		QueryRunner runner = new QueryRunner(DBManager.getDataSource());
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT dbname, TO_CHAR(T.BIZDATE, 'yyyy-mm-dd hh24:mi') bizdate,   SUM(T.MBPS) MBPS,   SUM(T.IOPS) IOPS,   SUM(T.IO_LATENCY) IO_LATENCY  FROM T_ORA_IOSTAT T ");
		sql.append(" where t.dbname=? ");
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
		sql.append(" GROUP BY to_char(t.bizdate,'yyyy-mm-dd hh24:mi'), T.DBNAME ORDER BY BIZDATE");

		System.out.println(sql.toString());

		List list = (List) runner.query(sql.toString(),
				new BeanListHandler(IOPSBean.class), dbname);
		
		return list;
	}

	
	/**
	 * 返回实例个数
	 * @param dbname
	 * @return
	 * @throws SQLException
	 */
	public Integer getInstancesByDBname(String dbname) throws SQLException{
		
		QueryRunner runner = new QueryRunner(DBManager.getDataSource());
		Integer inst_no = 1;
		String sql = "select count(distinct INST_NO) cnt "
				+ " FROM T_ORA_SYSSTAT T WHERE T.DBNAME = ? ";
		List list =  (List) runner.query(sql,
				new BeanListHandler(BaseCntBean.class), dbname);
		inst_no=((BaseCntBean) list.get(0)).getCnt();
		return inst_no;
	}
}
