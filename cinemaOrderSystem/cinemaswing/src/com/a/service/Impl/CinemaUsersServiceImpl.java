package com.a.service.Impl;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.a.dao.BaseDao;
import com.a.dao.Impl.BaseDaoImpl;
import com.a.pojo.CinemaUsers;
import com.a.service.CinemaUsersService;

import oracle.net.aso.q;

public class CinemaUsersServiceImpl implements CinemaUsersService {
	private BaseDao bd ;

	@Override
	public Map<String, Object> Login(String uaccount, String passwd, String states) {
		bd = new BaseDaoImpl();
		
		String sql = "SELECT * FROM CINEMAUSERS "; 
		sql += " WHERE UACCOUNT = '"+uaccount+"'";
		sql += " AND PASSWD = '"+passwd+"'";
		sql += " AND STATES = '"+states+"'";
		
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		if (queryForList.size() > 0) { //查询到信息则返回信息集合
			return queryForList.get(0);
		} else {  //否则返回 null
			return null;
		}
	}

	@Override
	public int registUser(CinemaUsers cinemausers) {
		bd = new BaseDaoImpl();
		
		String sql = "INSERT INTO Cinemausers(USERID,NAME,PASSWD,LEVELS,BALANCE,UACCOUNT,STATES,STATUS) "; 
		sql += " VALUES(SEQ_CINEMALUSERS.NEXTVAL,'";
		sql += cinemausers.getName()+"','";
		sql += cinemausers.getPasswd()+"','";
		sql += cinemausers.getLevels()+"',";
		sql += cinemausers.getBalance()+",'";
		sql += cinemausers.getUaccount()+"','";
		sql += cinemausers.getStates()+"','";
		sql += cinemausers.getStatus()+"')";
		String [] generatedColumns = {"USERID"};
		
		int pk = bd.executeInsert(sql, generatedColumns);
		
		return pk;
	}

	@Override
	public Vector<Vector> findUserInfo(String name, String account) {
		bd = new BaseDaoImpl();
		
		String sql = "SELECT * FROM CINEMAUSERS WHERE STATES = 0 AND STATUS = 1 ";
		if (!"".equals(name) && null != name) sql += " and NAME like '%"+name+"%'";
		if (!"".equals(account) && null != account) sql += " and UACCOUNT like '%"+account+"%'";
		
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		
		Vector<Vector> data = new Vector<Vector>();
		
		for (Map<String,Object> rowmap : queryForList) {
			//将查询结果（每一条记录）放在rowData中
			Vector<String> rowData = new Vector<String>();
			rowData.add(rowmap.get("USERID").toString());
			rowData.add((String)rowmap.get("NAME"));
			rowData.add((String)rowmap.get("UACCOUNT"));
			rowData.add((String)rowmap.get("LEVELS"));
			rowData.add((String)rowmap.get("BALANCE"));
			rowData.add((String)rowmap.get("PASSWD"));
			rowData.add((String)rowmap.get("STATES"));
			//将rowData（包含每一条记录）放在 data 中
			data.add(rowData);
		}
		return data ;
	}

	@Override
	public int deleteUserById(Integer userid) {
		bd = new BaseDaoImpl();
		String sql = "update CINEMAUSERS set status = 0 where USERID = '"+userid+"'";
		
		int i = bd.executeUpdate(sql);
		return i;
	}

	@Override
	public int updateUserInfoById(CinemaUsers cinemausers) {
		bd = new BaseDaoImpl();
		
		String sql = "update CINEMAUSERS set userid=userid ";
		if (cinemausers.getName() != null && !"".equals(cinemausers.getName()))
			sql += " ,NAME = '"+cinemausers.getName()+"'";
		if (cinemausers.getPasswd() != null && !"".equals(cinemausers.getPasswd())) 
			sql += " ,PASSWD = '"+cinemausers.getPasswd()+"'";
		if (cinemausers.getLevels() != null)
			sql += " ,LEVELS = '"+cinemausers.getLevels()+"'";
		if (cinemausers.getBalance() != null)
			sql += " ,BALANCE = "+cinemausers.getBalance();
		if (cinemausers.getUaccount() != null)
			sql += " ,UACCOUNT = '"+cinemausers.getUaccount()+"'";
		if (cinemausers.getStates() != null)
			sql += " ,STATES = '"+cinemausers.getStates()+"'";
		if (cinemausers.getStatus() != null)
			sql += " ,STATUS = '"+cinemausers.getStatus()+"'";
		sql += " where USERID = " + cinemausers.getUserid();
		
		System.out.println("CinemaUsersServiceImpl:\n"+sql);
		
		int i = bd.executeUpdate(sql);
		return i;
	}

	@Override
	public int updateUserPwdById(Integer userId) {
		bd = new BaseDaoImpl();
		String sql = "update CINEMAUSERS set PASSWD = '123456' where STATUS = 1 AND USERID = '"+userId+"'";
		int i = bd.executeUpdate(sql);
		return i;
	}

	@Override
	public List<Map<String,Object>> findUserById(Integer userid) {
		bd = new BaseDaoImpl();
		
		String sql = "SELECT * FROM CINEMAUSERS WHERE USERID = "+userid;
	
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		
		return queryForList;
	}

	@Override
	public List<Map<String, Object>> findUserByAccount(String account) {
		bd = new BaseDaoImpl();
		
		String sql = "SELECT * FROM CINEMAUSERS WHERE UACCOUNT = '"+ account +"'";
		
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		
		return queryForList;
	}
}
