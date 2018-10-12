package com.a.service.Impl;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.a.dao.BaseDao;
import com.a.dao.Impl.BaseDaoImpl;
import com.a.pojo.Movie;
import com.a.service.MovieService;

public class MovieServiceImpl implements MovieService {
	private BaseDao bd;
	@Override
	public int addMovieReturnId(Movie movie) {
		bd = new BaseDaoImpl();
		
		String sql = "INSERT INTO MOVIE(MOVIEID,MOVIENAME,DETAIL,DURATION,MOVIETYPE)"; 
		sql += " VALUES(SQL_MOVIE.nextval,'"+movie.getMovieName()+"','"+movie.getDetail()+"','"+movie.getDuration()+"','"+movie.getMovieType()+"')";
		System.out.println(sql);
		
		String [] generatedColumns = {"MOVIEID"};
		
		int pk = bd.executeInsert(sql, generatedColumns);
		
		return pk;
	}
	@Override
	public Vector<Vector<String>> findAllMovies() {
		bd = new BaseDaoImpl();
		String sql = "SELECT * FROM MOVIE";
		
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		
		for(Map<String,Object> map : queryForList) {
			Vector <String> rowData = new Vector<String>();
			rowData.add(map.get("MOVIEID").toString()); 
			rowData.add((String) map.get("MOVIENAME"));
			rowData.add((String) map.get("DURATION"));
			rowData.add((String) map.get("MOVIETYPE"));
			data.add(rowData);
		}
		return data;
	}
	@Override
	public String findMovieDurationByMovieId(Integer movieId) {
		bd = new BaseDaoImpl();
		String sql = "SELECT DURATION FROM MOVIE "; 
		sql += " WHERE MOVIEID = " + movieId;
		//System.out.println(sql);
		
		List<Map<String,Object>> queryForList = bd.queryForList(sql);
		return (String) queryForList.get(0).get("DURATION");
	}
	
	@Override
	public boolean compareDate(String time1, String time2, String duration) {
		bd = new BaseDaoImpl();
		
		return false;
	}

}
