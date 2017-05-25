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
import com.clist.service.UserService;


@RequestMapping("/users")
@RestController
public class UserResource {
	
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws CListException{
		
		User userSaved = userService.insertUser(user);
			
		return new ResponseEntity<User>( userSaved,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", produces="application/json")
	public ResponseEntity<Resource<User>> updateUser(@RequestBody User user) throws CListException {
		
		User userUpdated = userService.updateUser(user);
		
		return new ResponseEntity<Resource<User>>(getUserResource(userUpdated), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{userId}",method=RequestMethod.GET, consumes="application/json", produces="application/json")
	public ResponseEntity<Resource<User>> findById(@PathVariable("userId") String userId) throws CListException{
		
		User userReturned = userService.findUser(userId);
		
		return new ResponseEntity<Resource<User>>(getUserResource(userReturned), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{userId}", method=RequestMethod.DELETE, consumes="application/json", produces="application/json")
	public ResponseEntity<DMLResult> deleteById(@PathVariable("userId") String id) throws CListException{
		
		DMLResult result = userService.deleteUser(id);
	
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	private Resource<User> getUserResource(User user) throws CListException{
		
		Resource<User> resource = new Resource<User>(user);
		
		resource.add(linkTo(methodOn(UserResource.class).findById(user.getId())).withSelfRel());
		return resource;
		
	}
	
	@Autowired
	public void setUserService(UserService userService){
		this.userService = userService;
	}
}
