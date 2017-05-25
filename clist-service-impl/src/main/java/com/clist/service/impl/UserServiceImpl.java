package com.clist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.clist.domain.constants.UserErrorCodes;
import com.clist.domain.constants.UserMessages;
import com.clist.domain.entities.User;
import com.clist.domain.exceptions.CListDataNotFoundException;
import com.clist.domain.exceptions.CListException;
import com.clist.domain.vo.DMLResult;
import com.clist.repositories.UserRepository;
import com.clist.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public User insertUser(User user) throws CListException {
		
		User userSaved =null;
		
		try{
			userSaved = userRepository.insert(user);
		}catch(Exception ex){
			throw new CListException(UserErrorCodes.NOT_INSERTED, messageSource.getMessage(UserMessages.NOT_SAVED_EXCEPTION,null,null) + ":" + ex.getMessage());
		}
		
		if(userSaved == null){
			throw new CListException(UserErrorCodes.NOT_INSERTED, messageSource.getMessage(UserMessages.NOT_SAVED,null,null));
		}
		
		return userSaved;
	}

	@Override
	public User updateUser(User user) throws CListException {
		
		User userUpdated = null;
		try{
			userUpdated = userRepository.save(user);
		}catch(Exception ex){
			throw new CListException(UserErrorCodes.EXCEPTION_WHILE_UPDATING, messageSource.getMessage(UserMessages.NOT_UPDATED_EXCEPTION, null,null) + ":" + ex.getMessage());
		}
		
		if(userUpdated == null){
			throw new CListException(UserErrorCodes.EXCEPTION_WHILE_DELETING, messageSource.getMessage(UserMessages.NOT_UPDATED,null,null));
		}
		
		return userUpdated;
	}

	@Override
	public User findUser(String id) throws CListException {
		User userReturned = userRepository.findOne(id);
		
		if(userReturned == null){
			throw new CListDataNotFoundException(UserErrorCodes.NOT_FOUND, messageSource.getMessage(UserMessages.NOT_FOUND,null,null));
		}
		return userReturned;
	}

	@Override
	public DMLResult deleteUser(String id) throws CListException {
		DMLResult result = new DMLResult();
		try{
			
			Long count = userRepository.deleteById(id);
			
			result.setCount(count);
			result.setMessage(messageSource.getMessage(UserMessages.SUCCESSFULLY_DELETED, null,null));
			
		}catch(Exception ex){
			throw new CListException(UserErrorCodes.EXCEPTION_WHILE_DELETING, messageSource.getMessage(UserMessages.NOT_DELETED_EXCEPTION,null,null));
		}
		
		if(result.getCount() == 0){
			throw new CListDataNotFoundException(UserErrorCodes.NOT_FOUND, messageSource.getMessage(UserMessages.NOT_DELETED,null,null));
		}
		
		
		return result;
	}
	
	@Autowired
	public void setUserRepository(UserRepository userRepository){
		this.userRepository = userRepository;
	}
}
