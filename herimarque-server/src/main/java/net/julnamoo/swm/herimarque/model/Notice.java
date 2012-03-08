package net.julnamoo.swm.herimarque.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Notice implements Serializable{

	String date;
	String title;
	String content;
	
	public Notice() {}
	
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
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() 
	{
		return "[title@"+this.title + ", content@" + this.getContent() + "]";
	}
}
