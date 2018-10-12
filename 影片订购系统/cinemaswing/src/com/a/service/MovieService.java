package com.a.service;

import java.util.Vector;

import com.a.pojo.Movie;

public interface MovieService {
	
	/**
	 * 添加电影
	 * @param movie 条件
	 * @return 主键
	 */
	public int addMovieReturnId(Movie movie);
	
	/**
	 * 查找所有电影
	 * @return 
	 */
	public Vector<Vector<String>> findAllMovies();
	
	/**
	 * 通过电影ID找到电影时长
	 * @param movieId
	 * @return
	 */
	public String findMovieDurationByMovieId(Integer movieId); 
	
	/**
	 * 比较电影放映时间先后
	 * @param time1 当前影厅放映电影最后时间
	 * @param time2 排片上映时间
	 * @param duration 当前影厅放映电影时长
	 * @return
	 */
	public boolean compareDate(String time1, String time2, String duration);
}
