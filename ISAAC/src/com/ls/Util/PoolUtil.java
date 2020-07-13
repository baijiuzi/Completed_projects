package com.ls.Util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class PoolUtil {

	private static Properties prop = new Properties();  //读取Properties配置文件
	private static DataSource dataSource;  //外界所能获取的连接池对象
	
	static{
		InputStream is = PoolUtil.class.getClassLoader().getResourceAsStream("druid.properties");
		try {
			prop.load(is);
			dataSource = DruidDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Error: PoolUtil加载配置文件失败");
		}
	}
	
	public static DataSource getDataSource(){   //返回连接池对象
		return dataSource;
	}
	
	
	public static Connection getConnection(){   //返回连接池所给的一个连接
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Error: 连接池分配新连接失败");
		}
	}
	
	public static void close(Connection conn,Statement sta,ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(sta != null){
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
