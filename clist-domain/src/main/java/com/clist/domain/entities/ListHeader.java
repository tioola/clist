package com.clist.domain.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.clist.domain.enums.ListType;

public class ListHeader {

	@Id
	private String id;
	
	private ListType type;
	
	@DBRef
	private User admin;
	
	@DBRef
	private List<User> usersAllowed;
	
	private List<Column> mainColumns;
	
	private List<Column> detailColumns;

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

	public List<User> getUsersAllowed() {
		return usersAllowed;
	}

	public void setUsersAllowed(List<User> usersAllowed) {
		this.usersAllowed = usersAllowed;
	}

	public List<Column> getMainColumns() {
		return mainColumns;
	}

	public void setMainColumns(List<Column> mainColumns) {
		this.mainColumns = mainColumns;
	}

	public List<Column> getDetailColumns() {
		return detailColumns;
	}

	public void setDetailColumns(List<Column> detailColumns) {
		this.detailColumns = detailColumns;
	}
	
	
	
}
