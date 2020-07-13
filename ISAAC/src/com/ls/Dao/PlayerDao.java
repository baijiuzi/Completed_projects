package com.ls.Dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ls.Bean.Player;
import com.ls.Util.PoolUtil;

public class PlayerDao {

	public Player login(Player player) {     //查询  用户名和密码是否否存在
		 
		QueryRunner qr = new QueryRunner(PoolUtil.getDataSource());
		
		String sql = "select * from player where username = ? and password = ?";
		
		Object[] params = {player.getUsername(),player.getPassword()};
		
		try {
			Player queryPlayer = qr.query(sql, new BeanHandler<Player>(Player.class), params);
			
			return queryPlayer;   //返回被查找到的对象
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("login 查询出错");
		}
		
	}
	public Player selectUsername(Player player) {
		QueryRunner qr = new QueryRunner(PoolUtil.getDataSource());
		
		String sql = "select * from player where username = ?";
		
		Object[] params = {player.getUsername()};
		
		try {
			Player queryPlayer = qr.query(sql, new BeanHandler<Player>(Player.class), params);
			
			return queryPlayer;   //返回被查找到的对象
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("selectUsername 查询出错");
		}
	}

	public void insert(Player player) {          //插入
		
		QueryRunner qr = new QueryRunner(PoolUtil.getDataSource());
		
		String sql = "INSERT into player VALUES(?,?)";
		
		Object[] params = {player.getUsername(),player.getPassword()};
		
		try {
			qr.update(sql, params);
			//System.out.println("插入新对象成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("insert 插入新对象出错");
		}
	}

	
	
	
	
	
	
	
	
}


