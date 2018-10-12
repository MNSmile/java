package com.a.pojo;

public class Hall {
	private Integer hallid;
	private String hallname;
	private Integer cid;
	private String capacity;
	private String hallstatus;
	
	public Hall() {}
	
	public Hall(Integer hallid, String hallname, Integer cid, String capacity) {
		this.hallid = hallid;
		this.hallname = hallname;
		this.cid = cid;
		this.capacity = capacity;
	}

	public Integer getHallid() {
		return hallid;
	}

	public void setHallid(Integer hallid) {
		this.hallid = hallid;
	}

	public String getHallname() {
		return hallname;
	}

	public void setHallname(String hallname) {
		this.hallname = hallname;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getHallstatus() {
		return hallstatus;
	}

	public void setHallstatus(String hallstatus) {
		this.hallstatus = hallstatus;
	}

	@Override
	public String toString() {
		return "Hall [hallid=" + hallid + ", hallname=" + hallname + ", cid=" + cid + ", capacity=" + capacity
				+ ", hallstatus=" + hallstatus + "]";
	}
	
}
