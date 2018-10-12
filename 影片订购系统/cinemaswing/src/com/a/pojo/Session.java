package com.a.pojo;

public class Session {
	private Integer sessionId;
	private Integer hid;
	private Integer cid;
	private Integer movieId;
	private String sessionTime;
	private String price;
	private String remain;
	
	public Session() {}
	
	public Session(Integer sessionId, Integer hid, Integer cid, Integer movieId, String sessionTime, String price,
			String remain) {
		this.sessionId = sessionId;
		this.hid = hid;
		this.cid = cid;
		this.movieId = movieId;
		this.sessionTime = sessionTime;
		this.price = price;
		this.remain = remain;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getHid() {
		return hid;
	}

	public void setHid(Integer hid) {
		this.hid = hid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(String sessionTime) {
		this.sessionTime = sessionTime;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRemain() {
		return remain;
	}

	public void setRemain(String remain) {
		this.remain = remain;
	}

	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", hid=" + hid + ", cid=" + cid + ", movieId=" + movieId
				+ ", sessionTime=" + sessionTime + ", price=" + price + ", remain=" + remain + "]";
	}
	
	
	
}
