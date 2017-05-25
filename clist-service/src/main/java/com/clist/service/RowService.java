package com.clist.service;

import com.clist.domain.entities.Row;
import com.clist.domain.exceptions.CListDataNotFoundException;
import com.clist.domain.exceptions.CListException;
import com.clist.domain.vo.DMLResult;

public interface RowService {
	
	Row insertRow(Row row) throws CListException;
	
	Row updateRow(Row row) throws CListException;
	
	Row findRow(String id) throws CListDataNotFoundException;
	
	DMLResult deleteRow(String id) throws CListException; 
	
}
