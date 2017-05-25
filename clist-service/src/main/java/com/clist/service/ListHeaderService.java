package com.clist.service;

import com.clist.domain.entities.ListHeader;
import com.clist.domain.exceptions.CListException;
import com.clist.domain.vo.DMLResult;

public interface ListHeaderService {
	
	ListHeader insertListHeader(ListHeader listHeader) throws CListException;
	
	ListHeader updateListHeader(ListHeader listHeader) throws CListException;
	
	ListHeader findListHeader(String id) throws CListException;
	
	DMLResult deleteListHeader(String id) throws CListException;
	
}
