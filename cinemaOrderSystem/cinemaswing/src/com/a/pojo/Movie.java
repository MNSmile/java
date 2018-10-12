package com.a.pojo;

public class Movie {
	private Integer movieId;
	private String movieName;
	private String detail;
	private String duration;
	private String movieType;
	
	public Movie() {}
	
	public Movie(Integer movieId, String movieName, String detail, String duration, String movieType) {
		this.movieId = movieId;
		this.movieName = movieName;
		this.detail = detail;
		this.duration = duration;
		this.movieType = movieType;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", detail=" + detail + ", duration="
				+ duration + ", movieType=" + movieType + "]";
	}
}
