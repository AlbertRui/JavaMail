package me.javamail.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 连接数据库的工具类
 * @author Administrator
 *
 */
public class JDBCUtils {
    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String user = "root";
    private static String passwd = "******";
    private static String driver = "com.mysql.jdbc.Driver";
   
    /**
     * 通过C3P0数据库连接池连接数据源
     * @return
     * @throws Exception
     */
	public static ComboPooledDataSource getDataSource() throws Exception{
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass( driver);
        	cpds.setJdbcUrl( url );
        	cpds.setUser(user);                                  
        	cpds.setPassword(passwd);  
        	cpds.setMinPoolSize(5);                                     
        	cpds.setAcquireIncrement(5);
        	cpds.setMaxPoolSize(30);
        	cpds.setMaxIdleTime(60);
		return cpds;
	}
}
