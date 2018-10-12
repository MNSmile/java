package com.a.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TicketService {
	
	/**
	 * 购票的方法
	 * @param userid 用户ID
	 * @param sessionid 场次ID
	 * @param ticketSet 票的集合
	 * @param price 票单价
	 * @return 影响行数,其中余额减去票价*票数
	 */
	public int shoppingTicket(Integer userid, String sessionid, Set<String> ticketSet, Double price);
	
	
	/**
	 * 展示票
	 * @param status 状态：历史票(0)还是还没使用的票(1)
	 * @param userid 用户ＩＤ
	 * @return
	 */
	public List<Map<String,Object>> findSessionidByUserId(String status, Integer userid);
	
	/**
	 * 通过票ID找到对应场次ID
	 * @param ticketid
	 */
	public String findSessionidByTicketid(String ticketid);
	
	
	/**
	 * 设置票为废票
	 */
	public int setTicketStatus0(String ticketid);
	
	/**
	 * 查看某一场次是否有人买了票
	 * @param sessionid 场次ID
	 * @return
	 */
	public List<Map<String,Object>> findTicketsBySessionid(Integer sessionid);
}
	