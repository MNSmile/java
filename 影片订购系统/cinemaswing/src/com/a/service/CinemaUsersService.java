package com.a.service;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.a.pojo.CinemaUsers;

public interface CinemaUsersService {
	
	/**
	 * 登录
	 * @param uaccount 账户名
	 * @param passwd  密码
	 * @param states  管理员或者普通用户
	 * @return 返回 null 或者 Map集合，null 表示账号密码错误，map集合包含账户信息为 KEY
	 */
	public Map<String,Object> Login(String uaccount,String passwd, String states);
	
	/**
	 * 注册普通用户
	 * @param cinemausers
	 * @return 主键 
	 */
	public int registUser(CinemaUsers cinemausers);
	
	/**
	 * 查询用户信息
	 * @param name 用户名字
	 * @param account 用户账号
	 * @return 返回用户信息集合Vector，范型Vector保存的是一条记录
	 */
	public Vector<Vector> findUserInfo(String name, String account);
	
	/**
	 * 删除用户
	 * @param userid
	 * @return 影响行数
	 */
	public int deleteUserById(Integer userid);
	
	/**
	 * 更新用户信息
	 * @param cinemausers 用户封装类
	 * @return 影响行数
	 */
	public int updateUserInfoById(CinemaUsers cinemausers);
	
	
	/**
	 * 初始化用户密码
	 * @param userId 用户ID
	 * @return 影响行数
	 */
	public int updateUserPwdById(Integer userId);
	
	/**
	 * 通过用户ID查询用户信息
	 * @param userid
	 * @return 用户所有信息
	 */
	public List<Map<String,Object>> findUserById(Integer userid);
	
	/**
	 * 通过用户账号查询用户所有信息
	 * @param account
	 * @return
	 */
	public List<Map<String,Object>> findUserByAccount(String account);
}
