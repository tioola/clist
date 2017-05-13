package com.clist.front.resources;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clist.domain.constants.UserErrorCodes;
import com.clist.domain.constants.UserMessages;
import com.clist.domain.entities.User;
import com.clist.domain.exceptions.CListDataNotFoundException;
import com.clist.domain.exceptions.CListException;
import com.clist.repositories.UserRepository;


@RequestMapping("/users")
@RestController
public class UserResource {

	@Autowired
	private MessageSource messageSource;
	
	private UserRepository userRepository;
	
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws CListException{
		
		try{
			User userSaved = userRepository.insert(user);
			
			if(userSaved == null){
				throw new CListException(UserErrorCodes.USER_NOT_INSERTED, messageSource.getMessage(UserMessages.USER_NOT_SAVED,null,null));
			}
			return new ResponseEntity<User>( userSaved,HttpStatus.CREATED);
		}catch(Exception ex){
			throw new CListException(UserErrorCodes.USER_NOT_INSERTED, messageSource.getMessage(UserMessages.USER_NOT_SAVED_EXCEPTION,null,null) + ex.getMessage());
		}
	}

	
	@RequestMapping(value="/{userId}",method=RequestMethod.GET, consumes="application/json", produces="application/json")
	public ResponseEntity<User> findById(@PathVariable("userId") String userId) throws CListDataNotFoundException{
		
		User userReturned = userRepository.findOne(userId);
		
		if(userReturned == null){
			throw new CListDataNotFoundException(UserErrorCodes.USER_NOT_FOUND, messageSource.getMessage(UserMessages.USER_NOT_FOUND,null,null));
		}
		
		return new ResponseEntity<User>(userReturned, HttpStatus.OK);
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository){
		this.userRepository = userRepository;
	}
}
