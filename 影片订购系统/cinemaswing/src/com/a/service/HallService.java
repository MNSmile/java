package com.a.service;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.a.pojo.Hall;

public interface HallService {
	
	/**
	 * 添加影厅
	 * @param cinemaId 影院ID
	 * @return 影响行数
	 */
	public int AddHallByCinemaId(Hall hall);
	
	/**
	 * 查询是否存在hallName的影厅
	 * @param hallName
	 * @return 
	 */
	public int findHallByCinemalId(String hallName);
	/**
	 * 查找所有影厅信息
	 * @return
	 */
	public Vector<Vector<String>> findAllHall();
	
	
	/**
	 * 通过影厅ID查找影厅容量
	 * @param hallid
	 * @return 影厅容量
	 */
	public String findHallCapacityById(String hallid);
	
	/**
	 * 查询某一影院所有影厅信息
	 * @param cinemaId
	 * @return
	 */
	public Vector<Vector<String>> findAllHallByCinemaId(Integer cinemaId);
	
	/**
	 * 删除影厅
	 * @param hallid
	 * @return
	 */
	public int deleteHallById(Integer hallid);
}
