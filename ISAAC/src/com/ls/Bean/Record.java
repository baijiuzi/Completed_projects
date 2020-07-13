package com.ls.Bean;

import java.util.Date;

public class Record {
	
	private Integer rid;
	private String username;
	private Date date;
	private Integer score;
	
	public Record() {
		// TODO Auto-generated constructor stub
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Record [username=" + username + ", date=" + date + ", score=" + score + "]";
	}
	
}
