package com.clist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.clist.domain.constants.ListHeaderErrorCodes;
import com.clist.domain.constants.ListHeaderMessages;
import com.clist.domain.entities.ListHeader;
import com.clist.domain.exceptions.CListDataNotFoundException;
import com.clist.domain.exceptions.CListException;
import com.clist.domain.vo.DMLResult;
import com.clist.repositories.ListHeaderRepository;
import com.clist.service.ListHeaderService;

@Service
public class ListHeaderServiceImpl implements ListHeaderService {

	
	private ListHeaderRepository listHeaderRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public ListHeader insertListHeader(ListHeader listHeader) throws CListException {
		
		ListHeader insertedHeader = null;
		try{
			insertedHeader = listHeaderRepository.insert(listHeader);
		}catch(Exception e){
			throw new CListException(ListHeaderErrorCodes.NOT_INSERTED, messageSource.getMessage(ListHeaderMessages.NOT_SAVED_EXCEPTION, null,null)+ ":" + e.getMessage());
		}
		
		if(insertedHeader == null){
			throw new CListException(ListHeaderErrorCodes.NOT_INSERTED, messageSource.getMessage(ListHeaderMessages.NOT_SAVED, null,null));
		}
		
		return insertedHeader;
	}

	@Override
	public ListHeader updateListHeader(ListHeader listHeader) throws CListException {
		
		ListHeader updatedHeader = null;
		try{
			updatedHeader =  listHeaderRepository.save(listHeader);
		}catch(Exception e){
			throw new CListException(ListHeaderErrorCodes.EXCEPTION_WHILE_UPDATING, messageSource.getMessage(ListHeaderMessages.NOT_UPDATED_EXCEPTION, null,null) + ":" + e.getMessage());
		}
		
		if(updatedHeader == null){
			throw new CListException(ListHeaderErrorCodes.EXCEPTION_WHILE_UPDATING, messageSource.getMessage(ListHeaderMessages.NOT_UPDATED_EXCEPTION, null,null));
		}
		
		return updatedHeader;
	}

	@Override
	public ListHeader findListHeader(String id) throws CListException {

		ListHeader returnedHeader = listHeaderRepository.findOne(id);
		
		if(returnedHeader == null){
			throw new CListDataNotFoundException(ListHeaderErrorCodes.NOT_FOUND, messageSource.getMessage(ListHeaderMessages.NOT_FOUND, null,null));
		}
		
		return returnedHeader;
	}

	@Override
	public DMLResult deleteListHeader(String id) throws CListException {
		
		DMLResult result = new DMLResult();
		
		try{
			Long count = listHeaderRepository.deleteById(id);
			result.setCount(count);
			result.setMessage(messageSource.getMessage(ListHeaderMessages.SUCCESSFULLY_DELETED, null,null));
		}catch(Exception ex){
			throw new CListException(ListHeaderErrorCodes.EXCEPTION_WHILE_DELETING ,messageSource.getMessage(ListHeaderMessages.NOT_DELETED_EXCEPTION,  null, null) + ":" + ex.getMessage());
		}
	    
		if(result.getCount() == 0){
			throw new CListDataNotFoundException(ListHeaderErrorCodes.NOT_FOUND, messageSource.getMessage(ListHeaderMessages.NOT_DELETED, null,null));
		}
		
		return result;
	}
	
	@Autowired
	public void setListHeaderRepository(ListHeaderRepository listHeaderRepository){
		this.listHeaderRepository = listHeaderRepository;
	}

}
