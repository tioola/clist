package com.clist.domain.entities;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Row {
	
	@Id
	private String id;
	
	@DBRef
	private ListHeader listHeader;
	
	@DBRef
	private User owner;
	
	private Set<Cell> mainValues;
	
	private Set<Cell> detailValues;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ListHeader getListHeader() {
		return listHeader;
	}

	public void setListHeader(ListHeader listHeader) {
		this.listHeader = listHeader;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<Cell> getMainValues() {
		return mainValues;
	}

	public void setMainValues(Set<Cell> mainValues) {
		this.mainValues = mainValues;
	}

	public Set<Cell> getDetailValues() {
		return detailValues;
	}

	public void setDetailValues(Set<Cell> detailValues) {
		this.detailValues = detailValues;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Row other = (Row) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return " {\"id\":\"" + id + "\",\"listHeader\":\"" + listHeader + "\",\"owner\":\"" + owner
				+ "\",\"mainValues\":\"" + mainValues + "\",\"detailValues\":\"" + detailValues + "\"}";
	}
	
	
	
}
