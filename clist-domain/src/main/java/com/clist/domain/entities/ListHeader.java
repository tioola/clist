package com.clist.domain.entities;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.clist.domain.enums.ListType;

@Document
public class ListHeader {

	@Id
	private String id;
	
	private String template;
	
	private Set<String> tags;
	
	private ListType type;
	
	@DBRef
	private User admin;
	
	@DBRef
	private Set<User> usersAllowed;
	
	private Set<Column> mainColumns;
	
	private Set<Column> detailColumns;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ListType getType() {
		return type;
	}

	public void setType(ListType type) {
		this.type = type;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Set<User> getUsersAllowed() {
		return usersAllowed;
	}

	public void setUsersAllowed(Set<User> usersAllowed) {
		this.usersAllowed = usersAllowed;
	}

	public Set<Column> getMainColumns() {
		return mainColumns;
	}

	public void setMainColumns(Set<Column> mainColumns) {
		this.mainColumns = mainColumns;
	}

	public Set<Column> getDetailColumns() {
		return detailColumns;
	}

	public void setDetailColumns(Set<Column> detailColumns) {
		this.detailColumns = detailColumns;
	}
	
	
	
	
}
