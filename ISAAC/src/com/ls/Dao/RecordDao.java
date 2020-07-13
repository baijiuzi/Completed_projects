package com.ls.Dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ls.Bean.Record;
import com.ls.Util.PoolUtil;

public class RecordDao {
	
	static int rid;

	public void insertRecord(Record r) {
		
		QueryRunner qr = new QueryRunner(PoolUtil.getDataSource());
		
		String sql = "INSERT into record(username,date) VALUES(?,?)";
		
		//System.out.println("插入用户名的rid   "+ rid);
		
		Object[] params = {r.getUsername(),r.getDate()};
		
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("insertUsername 出错");
		}
	}
	
	public void updateRecord(Record r) {
		
		rid = RecordDao.maxRid(r);                //得查询 查询最大主键就成
		
		QueryRunner qr = new QueryRunner(PoolUtil.getDataSource());
		
		String sql = "UPDATE record set score=? WHERE username=? and rid=?";
		
		System.out.println("更新成绩的rid   "+ rid);
		
		Object[] params = {r.getScore(),r.getUsername(),rid};
		
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("updateRecord 出错");
		}
	}
	
	public static int maxRid(Record r){
		
		QueryRunner qr = new QueryRunner(PoolUtil.getDataSource());
		
		String sql = "SELECT MAX(rid) from record";
		
		Object[] params = null;
		
		try {
			Integer maxRid = qr.query(sql, new ScalarHandler<Integer>("MAX(rid)"), params);
			return maxRid;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("maxRid 出错");
		}
	}
	
	public List<Record> selectAll() {
		
		QueryRunner qr = new QueryRunner(PoolUtil.getDataSource());
		
		String sql = "select * from record";
		
		Object[] params = null;
		
		try {
			List<Record> querys = qr.query(sql, new BeanListHandler<Record>(Record.class), params);
			return querys;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("selectAll 输出所有信息出错");
		}
	}
	

	

}
