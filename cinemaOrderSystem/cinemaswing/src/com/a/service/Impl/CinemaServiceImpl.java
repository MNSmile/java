package com.a.service.Impl;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.a.dao.BaseDao;
import com.a.dao.Impl.BaseDaoImpl;
import com.a.pojo.Cinema;
import com.a.service.CinemaService;

public class CinemaServiceImpl implements CinemaService {
	private BaseDao bd;
	@Override
	public Vector<Vector<String>> findCinema(Cinema cinema) {
		bd = new BaseDaoImpl();
		String sql = "select * from CINEMA where CINEMESTATUS = 1 ";
		if (cinema.getCinemaId() != null ) sql += " and CINEMAID = "+cinema.getCinemaId();
		if (cinema.getCinemaName() != null && !"".equals(cinema.getCinemaName())) 
			sql += " and CINEMANAME like '%"+cinema.getCinemaName()+"%'";
		if (cinema.getAddress() != null && !"".equals(cinema.getAddress()))
			sql += " and ADDRESS like '%"+cinema.getAddress()+"%'";
		
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		for(Map<String,Object> map : queryForList) {
			Vector <String> rowData = new Vector<String>();
			rowData.add(map.get("CINEMAID").toString()); 
			rowData.add((String) map.get("CINEMANAME"));
			rowData.add((String) map.get("ADDRESS"));
			data.add(rowData);
		}
		return data;
	}
	@Override
	public int AddCinema(Cinema cinema) {
		bd = new BaseDaoImpl();
		String sql = "INSERT INTO CINEMA(CINEMAID,CINEMANAME,ADDRESS,Cinemestatus)";
		sql += " VALUES(SEQ_CINEMA.nextval,'"+cinema.getCinemaName()+"','"+cinema.getAddress()+"','1')";
		int i = bd.executeUpdate(sql);
		return i;
	}
	@Override
	public int deleteCinema(Integer cinemaId) {
		bd = new BaseDaoImpl();
		
		String sql = "UPDATE cinema c SET c.cinemestatus = 0";
		sql += " WHERE c.cinemaid = " + cinemaId;
		
		int i = bd.executeUpdate(sql);
		
		return i;
	}

}
