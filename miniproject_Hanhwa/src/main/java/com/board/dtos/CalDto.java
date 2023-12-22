package com.board.dtos;

import java.util.Date;

import lombok.Data;

//@Data
public class CalDto {
	
	private String id;
	private int seq;
	private String title;
	private String content;
	private String mdate;
	private Date regdate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "CalDto [id=" + id + ", seq=" + seq + ", title=" + title + ", content=" + content + ", mdate=" + mdate
				+ ", regdate=" + regdate + "]";
	}
	public CalDto(String id, int seq, String title, String content, String mdate, Date regdate) {
		super();
		this.id = id;
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.mdate = mdate;
		this.regdate = regdate;
	}
	public CalDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}