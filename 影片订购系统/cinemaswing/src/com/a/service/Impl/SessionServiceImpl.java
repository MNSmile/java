package com.a.service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.a.dao.BaseDao;
import com.a.dao.Impl.BaseDaoImpl;
import com.a.pojo.Session;
import com.a.service.SessionService;

public class SessionServiceImpl implements SessionService {
	private BaseDao bd;
	
	@Override
	public int addSession(Session s) {
		bd = new BaseDaoImpl();
		/*String sessionTime = s.getSessionTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(sessionTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		String sql = "INSERT INTO SESSIONS(SESSIONSID,HID,CID,MOVIEID,SESSIONTIME,PRICE,REMAIN)";
		sql += " VALUES(SEQ_SESSIONS.nextval,"+s.getHid()+","+s.getCid()+","+s.getMovieId()+",";
		sql += "TO_DATE('"+ s.getSessionTime()+"','yyyy-mm-dd hh24:mi:ss')"+",'"+s.getPrice()+"','"+s.getRemain()+"')";
		
		//System.out.println(sql);
		int i = bd.executeUpdate(sql);
		return i;
	}
	@Override
	public Map<String,Object> findLatestMovieSessionTime(Integer hid, Integer cid) {
		bd = new BaseDaoImpl();
		String sql = "SELECT s.SESSIONTIME,s.MOVIEID,s.SESSIONSID FROM SESSIONS s";
		sql += " WHERE s.SESSIONTIME = (";
		sql += " SELECT MAX(s.SESSIONTIME) FROM SESSIONS s";
		sql += " INNER JOIN CINEMA c ON c.CINEMAID = s.CID";
		sql += " INNER JOIN HALL h ON h.HALLID = s.HID";
		sql += " WHERE h.HALLSTATUS = 1";
		sql += " AND s.HID="+ hid +" AND s.CID="+cid+")";
		//System.out.println(sql);
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		//System.out.println(queryForList);
		if (queryForList!=null && !queryForList.isEmpty()) {
			return queryForList.get(0);
		}
		return null;
	}
	
	@Override
	public Vector<Vector<String>> findAnyMovieInfo(String movieName, String cinemaName) {
		bd = new BaseDaoImpl();
		
		String sql = "SELECT s.SESSIONSID,c.CINEMANAME,h.HALLNAME,m.MOVIENAME,s.SESSIONTIME,s.PRICE,s.REMAIN,m.DURATION FROM SESSIONS s";
		sql += " INNER JOIN CINEMA c ON s.CID = c.CINEMAID";
		sql += " INNER JOIN HALL h ON h.HALLID = s.HID";
		sql += " INNER JOIN MOVIE m ON s.MOVIEID = m.MOVIEID";
		sql += " WHERE s.SESSIONTIME >= SYSDATE";
		if (movieName != null && !"".equals(movieName)) 
			sql += " AND m.MOVIENAME like '%" + movieName + "%'";
		if (cinemaName != null && !"".equals(cinemaName))
			sql += " AND c.CINEMANAME like '%" + cinemaName + "%'";
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		
		for (Map<String,Object> rowmap : queryForList) {
			//将查询结果（每一条记录）放在rowData中
			Vector<String> rowData = new Vector<String>();
			rowData.add(rowmap.get("SESSIONSID").toString());
			rowData.add(rowmap.get("CINEMANAME").toString());
			rowData.add(rowmap.get("HALLNAME").toString());
			rowData.add(rowmap.get("MOVIENAME").toString());
			rowData.add(rowmap.get("SESSIONTIME").toString());
			rowData.add(rowmap.get("DURATION").toString());
			rowData.add(rowmap.get("PRICE").toString());
			rowData.add(rowmap.get("REMAIN").toString());
			
			//将rowData（包含每一条记录）放在 data 中
			data.add(rowData);
		}
		return data ;
	}
	
	@Override
	public Map<String, Object> findMovieInfoBySessionId(String sessionid) {
		bd = new BaseDaoImpl();
		
		String sql = "SELECT s.SESSIONSID,c.CINEMANAME,h.HALLNAME,h.HALLID,m.MOVIENAME,s.SESSIONTIME,s.PRICE,s.REMAIN,m.DURATION FROM SESSIONS s";
		sql += " INNER JOIN CINEMA c ON s.CID = c.CINEMAID";
		sql += " INNER JOIN HALL h ON h.HALLID = s.HID";
		sql += " INNER JOIN MOVIE m ON s.MOVIEID = m.MOVIEID";
		sql += " WHERE s.SESSIONSID = "+sessionid;
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		
		return queryForList.get(0);
	}
	
	@Override
	public Set<String> findSeatSetBySessionId(String sessionId) {
		bd = new BaseDaoImpl();
		
		String sql = "SELECT t.SEAT FROM SESSIONS s ";
		sql += " INNER JOIN TICKET t ON s.SESSIONSID = t.SID";
		sql += " WHERE t.STATUS = 1";
		sql += " AND s.SESSIONSID = " + sessionId;
		
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		Set<String> seatSet = new HashSet<String>();
		for (Map<String, Object> map : queryForList) {
			seatSet.add((String) map.get("SEAT"));
		}
		//System.out.println(seatSet.toString());
		return seatSet;
	}
	
	@Override
	public int updateSeatRemainBySessionId(String sessionid, int seatSum) {
		bd = new BaseDaoImpl();
		//交括号是处理seatSum有负数的情况，比如退票
		String sql = "UPDATE SESSIONS SET REMAIN = REMAIN - ("+seatSum+")";
		sql += " WHERE sessionsid = "+sessionid;
		
		int i = bd.executeUpdate(sql);
		
		return i;
	}
	@Override
	public Vector<Vector<String>> findMovieRelatedInformationBySessionid(String sessionid, String status, Integer uid) {
		bd = new BaseDaoImpl();
		
		String sql = "SELECT t.TICKETID, m.MOVIENAME, c.CINEMANAME, h.HALLNAME,s.SESSIONTIME,m.DURATION,s.PRICE,t.SEAT";
		sql += " FROM SESSIONS s";
		sql += " INNER JOIN CINEMA c ON c.CINEMAID = s.CID";
		sql += " INNER JOIN HALL h ON h.HALLID = s.HID";
		sql += " INNER JOIN movie m ON m.MOVIEID = s.MOVIEID";
		sql += " INNER JOIN TICKET t ON t.SID = s.SESSIONSID";
		sql += " INNER JOIN CINEMAUSERS cu ON cu.USERID = t.USERID";
		sql += " WHERE c.CINEMESTATUS = 1 AND h.HALLSTATUS = 1 AND t.STATUS = " + status;
		sql += " AND cu.USERID = " + uid; 
		sql += " AND s.SESSIONSID = " + sessionid;
		
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		
		for (Map<String,Object> rowmap : queryForList) {
			//将查询结果（每一条记录）放在rowData中
			Vector<String> rowData = new Vector<String>();
			rowData.add(rowmap.get("TICKETID").toString()); //电影
			rowData.add(rowmap.get("MOVIENAME").toString()); //电影
			rowData.add(rowmap.get("CINEMANAME").toString()); //影院
			rowData.add(rowmap.get("HALLNAME").toString()); //影厅
			rowData.add(rowmap.get("SEAT").toString()); //座位
			rowData.add(rowmap.get("SESSIONTIME").toString()); //时间
			rowData.add(rowmap.get("DURATION").toString()); //时长
			rowData.add(rowmap.get("PRICE").toString()); //单价
			
			//将rowData（包含每一条记录）放在 data 中
			data.add(rowData);
		}
		return data ;
	}
	@Override
	public Map<String, Object> findSessionInformationBysessionId(String sessionId) {
		bd = new BaseDaoImpl();
		
		String sql = "select * from SESSIONS t WHERE t.SESSIONSID = '"+sessionId+"'";
		
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		return queryForList.get(0);
	}
}
