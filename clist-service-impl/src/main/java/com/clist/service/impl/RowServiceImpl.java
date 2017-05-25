package com.clist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.clist.domain.constants.RowErrorCodes;
import com.clist.domain.constants.RowMessages;
import com.clist.domain.entities.Row;
import com.clist.domain.exceptions.CListDataNotFoundException;
import com.clist.domain.exceptions.CListException;
import com.clist.domain.vo.DMLResult;
import com.clist.repositories.RowRepository;
import com.clist.service.RowService;


@Service
public class RowServiceImpl implements RowService{

	
	private RowRepository rowRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@Override
	public Row insertRow(Row row) throws CListException {
		Row rowInserted = null;
		
		try{
			rowInserted = rowRepository.insert(row);
		}catch(Exception ex){
			throw new CListException(RowErrorCodes.NOT_INSERTED, messageSource.getMessage(RowMessages.NOT_SAVED_EXCEPTION, null,null) + ":" + ex.getMessage());
		}
		
		if(rowInserted == null){
			throw new CListException(RowErrorCodes.NOT_INSERTED, messageSource.getMessage(RowMessages.NOT_SAVED, null,null));
		}
		
		return rowInserted;
		
	}

	@Override
	public Row updateRow(Row row) throws CListException {
		Row updatedRow = null;
		
		try{
			updatedRow = rowRepository.save(row);
		}catch(Exception ex){
			throw new CListException(RowErrorCodes.EXCEPTION_WHILE_UPDATING, messageSource.getMessage(RowMessages.NOT_UPDATED_EXCEPTION, null,null) + ":" + ex.getMessage());
		}
		
		if(updatedRow == null){
			throw new CListDataNotFoundException(RowErrorCodes.NOT_FOUND, messageSource.getMessage(RowMessages.NOT_UPDATED, null,null));
		}
		
		return updatedRow;
	}

	@Override
	public Row findRow(String id) throws CListDataNotFoundException {
		Row rowFound = rowRepository.findOne(id);
		
		if(rowFound == null){
			throw new CListDataNotFoundException(RowErrorCodes.NOT_FOUND, messageSource.getMessage(RowMessages.NOT_FOUND, null,null));
		}
		
		return rowFound;
	}

	@Override
	public DMLResult deleteRow(String id) throws CListException {
		DMLResult result = new DMLResult();
		
		try{
			Long count = rowRepository.deleteById(id);
			result.setCount(count);
			result.setMessage(messageSource.getMessage(RowMessages.SUCCESSFULLY_DELETED, null,null));
		}catch(Exception ex){
			throw new CListException(RowErrorCodes.EXCEPTION_WHILE_UPDATING, messageSource.getMessage(RowMessages.NOT_DELETED, null,null) + ":" + ex.getMessage());
		}
		
		if(result.getCount() == 0){
			throw new CListException(RowErrorCodes.EXCEPTION_WHILE_DELETING, messageSource.getMessage(RowMessages.NOT_DELETED_EXCEPTION, null,null));
		}
		
		return result;
	}
	
	@Autowired
	public void setRowRepository(RowRepository rowRepository){
		this.rowRepository = rowRepository;
	}
	
}
