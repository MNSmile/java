package com.a.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao {
	/**
	 * 查询
	 * @param sql
	 * @param colList 查询的列名
	 * @return 
	 */
	public List<Map<String,Object>> queryForList(String sql, List<String> colList) ;
	
	
	/**
	 * 查询
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String sql) ;
	
	
	/**
	 * 增删改
	 * @param sql
	 * @return
	 */
	public int executeUpdate(String sql);
	

	/**
	 * 批量执行
	 * @param sqlList sql 集合
	 * @return
	 */
	public int executeUpdateTrans(List<String> sqlList);
	
	
	/**
	 * 插入一条数据并返回主键值（Oracle）
	 * @param sql
	 * @param generatedColumns 为包含列名的String 数组
	 * @return
	 */
	public int executeInsert(String sql, String [] generatedColumns);
	
	
	/**
	 * 插入一条数据并返回主键值（MySQL）
	 * @param sql
	 * @return 主键值
	 */
	public int executeInsert(String sql) ;
}
