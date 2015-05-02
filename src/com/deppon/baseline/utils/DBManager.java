package com.deppon.baseline.utils;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBManager {
	 private static ComboPooledDataSource ds = null;  
	    static{  
	        try{  
	            //创建连接池           
	            ds = new ComboPooledDataSource();  
	        }catch(Exception e){  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    //获取链接  
	    public static DataSource getDataSource(){  
	        return ds;  
	    }  
}
