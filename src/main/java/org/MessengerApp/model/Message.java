package org.MessengerApp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	private int id;
	private String message_detail;
	private Date created;
	private String author;
	private List<Link> linksList = new ArrayList<>();
	public Message() {
		
	}
	
	public Message(int id, String message_detail, Date created, String author) {
		super();
		this.id = id;
		this.message_detail = message_detail;
		this.created = created;
		this.author = author;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage_detail() {
		return message_detail;
	}
	public void setMessage_detail(String message_detail) {
		this.message_detail = message_detail;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
	

	public List<Link> getLinksList() {
		return linksList;
	}

	public void setLinksList(List<Link> linksList) {
		this.linksList = linksList;
	}

	public void addLink(String url, String rel) {
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		linksList.add(link);
	}

}
