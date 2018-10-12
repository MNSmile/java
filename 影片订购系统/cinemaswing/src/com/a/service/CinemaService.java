package com.a.service;

import java.util.Vector;

import com.a.pojo.Cinema;

public interface CinemaService {
	
	/**
	 * 查询影院
	 * @param cinema
	 * @return 所有影院信息
	 */
	public Vector<Vector<String>> findCinema(Cinema cinema);
	
	/**
	 * 添加影院
	 * @param cinema
	 * @return
	 */
	public int AddCinema(Cinema cinema);
	
	/**
	 * 删除影院
	 * @param cinemaId 
	 * @return 影响行数
	 */
	public int deleteCinema(Integer cinemaId);
	
	
}
