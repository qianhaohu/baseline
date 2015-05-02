package com.deppon.baseline.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.deppon.baseline.bean.AWRBean;
import com.deppon.baseline.bean.BaseCntBean;
import com.deppon.baseline.utils.DBManager;

public class AWRDao {
	
	/**
	 * 获取单个实例的数据库AWR性能数据
	 * @param dbname
	 * @param inst_no
	 * @param bizdate
	 * @return
	 * @throws SQLException
	 */
	public List<AWRBean> getAWRInfoByDBname(String dbname, Integer inst_no,String bizdate)
			throws SQLException {
		QueryRunner runner = new QueryRunner(DBManager.getDataSource());
		StringBuffer sql = new StringBuffer();
		sql.append("select dbname, to_char(SNAP_DATE,'yyyy-mm-dd ')||snap_time bizdate,inst_no, snap_date, snap_time,"
				+ " elapse_min, dbtime_min,redo, redo_ps, logicalreads, logicalreads_ps, "
				+ "physicalreads, physicalreads_ps, execs, exec_ps, "
				+ "parse, parse_ps, hardparse, hardparse_ps, trans, trans_ps "
				+ " FROM T_ORA_SYSSTAT T WHERE T.DBNAME = ?   AND T.INST_NO = ?");
		switch(bizdate){
		case "day":
			sql.append(" and SNAP_DATE>=sysdate-2");
			break;
		case "week":
			sql.append(" and SNAP_DATE>=sysdate-7");
			break;
		case "month":
			sql.append(" and SNAP_DATE>=sysdate-30");
			break;
		case "2month":
			sql.append(" and SNAP_DATE>=sysdate-60 ");
			break;
		}
		
		sql.append(" ORDER BY T.SNAP_DATE, T.SNAP_TIME");


		List<AWRBean> list = (List<AWRBean>) runner.query(sql.toString(),
				new BeanListHandler(AWRBean.class), dbname, inst_no);

		
		return list;
	}

	/**
	 * 获取整个数据库AWR性能数据sum,按照数据库、时间分组
	 * @param dbname
	 * @param bizdate
	 * @return
	 * @throws SQLException
	 */
	public List<AWRBean> getAWRInfoByDBname(String dbname,String bizdate)
			throws SQLException {
		QueryRunner runner = new QueryRunner(DBManager.getDataSource());
		StringBuffer sql = new StringBuffer();
		sql.append("select dbname, to_char(SNAP_DATE,'yyyy-mm-dd ')||snap_time bizdate,"
				+ " sum(elapse_min) elapse_min, sum(dbtime_min) dbtime_min,sum(redo) redo, sum(redo_ps) redo_ps, "
				+ "round(sum(logicalreads*8/1024/1024),2) logicalreads, round(sum(logicalreads_ps*8/1024/1024),2) logicalreads_ps, "
				+ "round(sum(physicalreads*8/1024/1024),2) physicalreads, round(sum(physicalreads_ps*8/1024/1024),2) physicalreads_ps, "
				+ "sum(execs) execs, sum(exec_ps) exec_ps, sum(parse) parse, sum(parse_ps) parse_ps, "
				+ "sum(hardparse) hardparse, sum(hardparse_ps) hardparse_ps, sum(trans) trans, sum(trans_ps) trans_ps "
				+ " FROM T_ORA_SYSSTAT T WHERE T.DBNAME = ?  ");
		switch(bizdate){
		case "day":
			sql.append(" and SNAP_DATE>=sysdate-2");
			break;
		case "week":
			sql.append(" and SNAP_DATE>=sysdate-7");
			break;
		case "month":
			sql.append(" and SNAP_DATE>=sysdate-30");
			break;
		case "2month":
			sql.append(" and SNAP_DATE>=sysdate-60 ");
			break;
		}
		
		sql.append("group by t.dbname,to_char(SNAP_DATE,'yyyy-mm-dd ')||snap_time ORDER BY bizdate");


		List<AWRBean> list = (List<AWRBean>) runner.query(sql.toString(),
				new BeanListHandler(AWRBean.class), dbname);

		
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
		String sql = "select insts cnt "
				+ " FROM T_BAS_DBINFO T WHERE T.DBNAME = ? ";
		List list =  (List) runner.query(sql,
				new BeanListHandler(BaseCntBean.class), dbname);
		inst_no=((BaseCntBean) list.get(0)).getCnt();
		return inst_no;
	}
}
