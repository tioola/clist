package com.clist.front.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clist.domain.constants.UserErrorCodes;
import com.clist.domain.constants.UserMessages;
import com.clist.domain.entities.User;
import com.clist.domain.exceptions.CListDataNotFoundException;
import com.clist.domain.exceptions.CListException;
import com.clist.domain.vo.DMLResult;
import com.clist.repositories.UserRepository;


@RequestMapping("/users")
@RestController
public class UserResource {

	@Autowired
	private MessageSource messageSource;
	
	private UserRepository userRepository;
	
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws CListException{
		
		User userSaved =null;
		
		try{
			userSaved = userRepository.insert(user);
		}catch(Exception ex){
			throw new CListException(UserErrorCodes.NOT_INSERTED, messageSource.getMessage(UserMessages.NOT_SAVED_EXCEPTION,null,null) + ex.getMessage());
		}
		
		if(userSaved == null){
			throw new CListException(UserErrorCodes.NOT_INSERTED, messageSource.getMessage(UserMessages.NOT_SAVED,null,null));
		}
		
		return new ResponseEntity<User>( userSaved,HttpStatus.CREATED);
	}

	
	@RequestMapping(value="/{userId}",method=RequestMethod.GET, consumes="application/json", produces="application/json")
	public ResponseEntity<Resource<User>> findById(@PathVariable("userId") String userId) throws CListException{
		
		User userReturned = userRepository.findOne(userId);
		
		if(userReturned == null){
			throw new CListDataNotFoundException(UserErrorCodes.NOT_FOUND, messageSource.getMessage(UserMessages.NOT_FOUND,null,null));
		}
		
		return new ResponseEntity<Resource<User>>(getUserResource(userReturned), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{userId}", method=RequestMethod.DELETE, consumes="application/json", produces="application/json")
	public ResponseEntity<DMLResult> deleteById(@PathVariable("userId") String id) throws CListException{
		
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
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	private Resource<User> getUserResource(User user) throws CListException{
		
		Resource<User> resource = new Resource<User>(user);
		
		resource.add(linkTo(methodOn(UserResource.class).findById(user.getId())).withSelfRel());
		return resource;
		
	}
	

	@Autowired
	public void setUserRepository(UserRepository userRepository){
		this.userRepository = userRepository;
	}
}
