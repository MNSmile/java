package com.a.dao.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.a.conn.ConnectionFactory;
import com.a.dao.BaseDao;

public class BaseDaoImpl implements BaseDao{
	private Connection conn;
	private Statement s;
	private ResultSet rs;
	/**
	 * 关闭相关链接
	 */
	private void closeAny() {
		try {
			if (rs != null) rs.close();
			if (s != null) s.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 增删改
	 * @param sql
	 * @return 影响的行数
	 */
	@Override
	public int executeUpdate(String sql) {
		int i = 0; 
		conn = ConnectionFactory.getConn();
		try {
			s = conn.createStatement();
			i = s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAny();
		}
		return i;
	}
	
	/**
	 * 通用查询方法,用于含有别名的数据集合，别名是一个虚名，不存在于实体的表中。
	 * @param sql
	 * @param colList
	 * @return
	 */
	@Override
	public List<Map<String,Object>> queryForList(String sql, List<String> colList) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		conn = ConnectionFactory.getConn();
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				Map<String,Object> rowMap = new HashMap<String, Object>();
				for (String key: colList) { //此循环结束之后一条记录就封装在一个map中
					String value = rs.getString(key); 
					rowMap.put(key, value);
				}
				resultList.add(rowMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAny();
		}
		return resultList;
	}
	
	
	/**
	 * 用于没有别名的表（可以是多个表）数据查询
	 * @param sql
	 * @return 存有表数据的List<Map<String,String>>对象
	 */
	@Override
	public List<Map<String, Object>> queryForList(String sql) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		conn = ConnectionFactory.getConn();
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql); //执行之后得到
			//System.out.println(sql);
			while (rs.next()) {
			
				Map<String, Object> rowMap = new HashMap<String, Object>();
				ResultSetMetaData rsm = rs.getMetaData();
				//获取查询的列的个数
				int countColumn = rsm.getColumnCount();
				//System.out.println(countColumn);
				for (int i = 1; i <= countColumn; i++) {
					String columnName = rsm.getColumnName(i); //得到第几列列名
					Object columnValue = rs.getString(columnName); //由列名得到数据
					rowMap.put(columnName, columnValue);
				}
				resultList.add(rowMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAny();
		}
		return resultList;
	}
	/**
	 * 批量增删改
	 * @param sqlList
	 * @return 影响的行数
	 */
	@Override
	public int executeUpdateTrans(List<String> sqlList) {
		int i = 0;
		conn = ConnectionFactory.getConn();
		try {
			//将自动提交生效取消
			conn.setAutoCommit(false);
			s = conn.createStatement();
			for (String sql : sqlList) {
				i += s.executeUpdate(sql);
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				i = 0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeAny();
		}
		return i;
	}


	
	@Override
	public int executeInsert(String sql, String[] generatedColumns) {
		int primaryKey = 0;
		conn = ConnectionFactory.getConn();
		try {
			s = conn.createStatement();
			int i = s.executeUpdate(sql, generatedColumns);
			if (i > 0) { //执行成功 i > 0 才会去获取主键
				rs = s.getGeneratedKeys();
				if (rs.next()) {
					primaryKey = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAny();
		}
		return primaryKey;
	}


	@Override
	public int executeInsert(String sql) {
		int primaryKey = 0;
		conn = ConnectionFactory.getConn();
		
		try {
			s = conn.createStatement();
			int i = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			if (i > 0) {
				rs = s.getGeneratedKeys();
				if (rs.next()) {
					primaryKey = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAny();
		}
		return primaryKey;
	}
}
