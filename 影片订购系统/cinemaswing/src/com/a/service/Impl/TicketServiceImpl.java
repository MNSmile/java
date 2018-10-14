package com.a.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.a.dao.BaseDao;
import com.a.dao.Impl.BaseDaoImpl;
import com.a.service.TicketService;

public class TicketServiceImpl implements TicketService {
	private BaseDao bd ;
	@Override
	public int shoppingTicket(Integer userid, String sessionid, Set<String> ticketSet, Double price) {
		bd = new BaseDaoImpl();
		
		List<String> sqlList = new ArrayList<String>();
		for (String str : ticketSet) {
			String sql = " INSERT INTO Ticket(TICKETId,USERID,SID,SEAT,STATUS)";
			sql += "VALUES(SEQ_TICKET.nextval,"+userid+","+sessionid+",'"+str+"','1')";
			sqlList.add(sql);
		}
		String sql = "UPDATE CINEMAUSERS SET BALANCE = BALANCE-"+price*ticketSet.size();
		sql += " WHERE USERID = " + userid;
		sqlList.add(sql);
		int i = bd.executeUpdateTrans(sqlList);
		return i;
	}
	@Override
	public List<Map<String,Object>> findSessionidByUserId(String status, Integer userid) {
		bd = new BaseDaoImpl();
		
		String sql = "SELECT SID FROM ticket WHERE STATUS = " + status;
		sql += " AND USERID = " + userid;
		
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		
		return queryForList;
	}
	@Override
	public String findSessionidByTicketid(String ticketid) {
		bd = new BaseDaoImpl();

		String sql = "SELECT SID FROM TICKET t WHERE t.TICKETID = "+ticketid;
		
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		String sessionid = queryForList.get(0).get("SID").toString();
		return sessionid;
	}
	@Override
	public int setTicketStatus0(String ticketid) {
		bd = new BaseDaoImpl();
		String sql = "UPDATE TICKET t SET t.STATUS = 0 WHERE t.TICKETID = " + ticketid;
		int i = bd.executeUpdate(sql);
		return i;
	}
	@Override
	public List<Map<String, Object>> findTicketsBySessionid(Integer sessionid) {
		bd = new BaseDaoImpl();
		String sql = "select t.* from TICKET t WHERE SID = " + sessionid;
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		return queryForList;
	}
	@Override
	public Integer findUserIdByTicketId(String ticketId) {
		bd = new BaseDaoImpl();
		String sql = "SELECT t.USERID FROM TICKET t";
		sql += " WHERE t.ticketid = '"+ticketId+"'";
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		return Integer.parseInt(queryForList.get(0).get("USERID").toString());
	}
	
}
