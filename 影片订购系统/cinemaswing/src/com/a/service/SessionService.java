package com.a.service;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.a.pojo.Session;

public interface SessionService {
	/**
	 * 排片
	 * @param s
	 * @return 影响行数
	 */
	public int addSession(Session s);
	
	/**
	 * 从场次表中查出你想排片的影厅的最后一场电影的时间,电影ID,场次ID
	 * @param hid
	 * @param cid
	 * @param sid
	 * @return 最后一场电影播放时间和电影id
	 */
	public Map<String,Object> findLatestMovieSessionTime(Integer hid, Integer cid);
	
	/**
	 * 查询影讯
	 * @return
	 */
	public Vector<Vector<String>> findAnyMovieInfo(String movieName, String cinemaName);
	
	/**
	 * 通过场次ID查找电影
	 * @param sessionid
	 * @return 
	 */
	public Map<String,Object> findMovieInfoBySessionId(String sessionid);
	
	
	/**
	 * 通过场次ID查找某一电影已经选了的座位
	 * @param sessionId 场次id
	 * @return 座位号集合
	 */
	public Set<String> findSeatSetBySessionId(String sessionId);
	
	
	/**
	 * 更新某一场次余座
	 * @param sessionid
	 * @param seatSum -1 代表余座加一，1代表余座减一
	 * @return 影响行数
	 */
	public int updateSeatRemainBySessionId(String sessionid, int seatSum);
	
	
	/**
	 * 通过场次id,得到某人购票的电影名、影院、影厅、座位号、放映时间、时长、单价(查到的都是放映时间大于系统时间)
	 * @param sessionid 场次ID
	 * @param status 票的状态，0代表废票，1代表可以使用的票
	 * @param uid 用户id
	 * @return 
	 */
	public Vector<Vector<String>> findMovieRelatedInformationBySessionid(String sessionid, String status, Integer uid);
	
	/**
	 * 通过场次ID查找本场次所有信息
	 * @param sessionId 场次ID
	 * @return 包含场次所有信息的Map集合
	 */
	public Map<String,Object> findSessionInformationBysessionId(String sessionId);
}




