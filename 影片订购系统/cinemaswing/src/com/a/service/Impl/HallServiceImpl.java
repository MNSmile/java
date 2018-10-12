package com.a.service.Impl;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.a.dao.BaseDao;
import com.a.dao.Impl.BaseDaoImpl;
import com.a.pojo.Hall;
import com.a.service.HallService;

public class HallServiceImpl implements HallService {
	private BaseDao bd ;
	@Override
	public int AddHallByCinemaId(Hall hall) {
		bd = new BaseDaoImpl();
		String sql = "insert into HALL(HALLID,HALLNAME,CID,CAPACITY,HALLSTATUS)";
		sql += " values(SEQ_HALL.nextval,'"+hall.getHallname()+"',"+hall.getCid()+",'"+hall.getCapacity()+"','1')";
		
		System.out.println(sql);
		
		int i = bd.executeUpdate(sql);
		return i;
	}
	
	@Override
	public Vector<Vector<String>> findAllHall() {
		bd = new BaseDaoImpl();
	    
		String sql = "SELECT h.HALLID,h.HALLNAME,h.CAPACITY,c.CINEMAID,c.CINEMANAME FROM HALL h";
		sql += " INNER JOIN CINEMA c ON c.CINEMAID = h.cid ";
		sql += " WHERE c.CINEMESTATUS = '1' AND h.HALLSTATUS = '1'";
		
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		
		for(Map<String,Object> map : queryForList) {
			Vector <String> rowData = new Vector<String>();
			rowData.add(map.get("HALLID").toString()); 
			rowData.add((String) map.get("HALLNAME"));
			rowData.add((String) map.get("CAPACITY"));
			rowData.add(map.get("CINEMAID").toString());
			rowData.add((String) map.get("CINEMANAME"));
			data.add(rowData);
		}
		
		return data;
	}

	@Override
	public String findHallCapacityById(String hallid) {
		bd = new BaseDaoImpl();
	    
		String sql = "SELECT CAPACITY FROM HALL h WHERE h.hallid = " + hallid;
		
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		
		return (String) queryForList.get(0).get("CAPACITY");
	}

	@Override
	public Vector<Vector<String>> findAllHallByCinemaId(Integer cinemaId) {
		bd = new BaseDaoImpl();
	    
		String sql = "SELECT * FROM HALL h WHERE h.HALLSTATUS = 1 AND h.CID = " + cinemaId;
		
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		
		for(Map<String,Object> map : queryForList) {
			Vector <String> rowData = new Vector<String>();
			rowData.add(map.get("HALLID").toString()); 
			rowData.add((String) map.get("HALLNAME"));
			rowData.add((String) map.get("CAPACITY"));
			data.add(rowData);
		}
		
		return data;
	}

	@Override
	public int deleteHallById(Integer hallid) {
		bd = new BaseDaoImpl();
		
		String sql = "UPDATE HALL h SET HALLSTATUS = 0";
		sql += "WHERE h.hallid = " + hallid;
		
		int i = bd.executeUpdate(sql);
		return i;
	}
	
}
