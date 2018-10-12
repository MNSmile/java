package com.a.pojo;

public class Cinema {
	private Integer cinemaId;
	private String cinemaName;
	private String address;
	private String cinemaStatus;
	
	public Cinema() {}
	
	public Cinema(Integer cinemaId, String cinemaName, String address) {
		this.cinemaId = cinemaId;
		this.cinemaName = cinemaName;
		this.address = address;
	}

	public Integer getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(Integer cinemaId) {
		this.cinemaId = cinemaId;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCinemaStatus() {
		return cinemaStatus;
	}

	public void setCinemaStatus(String cinemaStatus) {
		this.cinemaStatus = cinemaStatus;
	}

	@Override
	public String toString() {
		return "Cinema [cinemaId=" + cinemaId + ", cinemaName=" + cinemaName + ", address=" + address + "]";
	} 
	
}
