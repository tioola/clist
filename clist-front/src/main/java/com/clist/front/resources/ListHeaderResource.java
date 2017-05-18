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

import com.clist.domain.constants.ListHeaderErrorCodes;
import com.clist.domain.constants.ListHeaderMessages;
import com.clist.domain.entities.ListHeader;
import com.clist.domain.exceptions.CListDataNotFoundException;
import com.clist.domain.exceptions.CListException;
import com.clist.domain.vo.DMLResult;
import com.clist.repositories.ListHeaderRepository;

@RestController
@RequestMapping("/listheaders")
public class ListHeaderResource {
	
	
	@Autowired
	private MessageSource messageSource;
	
	private ListHeaderRepository repo;
	
	
	@RequestMapping( method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<Resource<ListHeader>> saveListHeader(@RequestBody ListHeader header) throws CListException{
		
		ListHeader insertedHeader = null;
		try{
			insertedHeader = repo.insert(header);
		}catch(Exception e){
			throw new CListException(ListHeaderErrorCodes.NOT_INSERTED, messageSource.getMessage(ListHeaderMessages.NOT_SAVED_EXCEPTION, null,null)+ ":" + e.getMessage());
		}
		
		if(insertedHeader == null){
			throw new CListException(ListHeaderErrorCodes.NOT_INSERTED, messageSource.getMessage(ListHeaderMessages.NOT_SAVED, null,null));
		}
		
		return new ResponseEntity<Resource<ListHeader>>(getListHeaderResource(insertedHeader),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{listHeaderId}", method=RequestMethod.GET, consumes="application/json", produces="application/json" )
	public ResponseEntity<Resource<ListHeader>> findById(@PathVariable("listHeaderId") String id ) throws CListDataNotFoundException{
		
		ListHeader returnedHeader = repo.findOne(id);
		
		if(returnedHeader == null){
			throw new CListDataNotFoundException(ListHeaderErrorCodes.NOT_FOUND, messageSource.getMessage(ListHeaderMessages.NOT_FOUND, null,null));
		}
		
		return new ResponseEntity<Resource<ListHeader>>(getListHeaderResource(returnedHeader),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{listHeaderId}",method=RequestMethod.DELETE ,consumes="application/json", produces="application/json")
	public ResponseEntity<DMLResult> deleteById(@PathVariable("listHeaderId") String id ) throws CListException{

		DMLResult result = new DMLResult();
		
		try{
			Long count = repo.deleteById(id);
			result.setCount(count);
			result.setMessage(messageSource.getMessage(ListHeaderMessages.SUCCESSFULLY_DELETED, null,null));
		}catch(Exception ex){
			throw new CListException(ListHeaderErrorCodes.EXCEPTION_WHILE_DELETING ,messageSource.getMessage(ListHeaderMessages.NOT_DELETED_EXCEPTION,  null, null) + ":" + ex.getMessage());
		}
	    
		if(result.getCount() == null){
			throw new CListDataNotFoundException(ListHeaderErrorCodes.NOT_FOUND, messageSource.getMessage(ListHeaderMessages.NOT_DELETED, null,null));
		}
		
		return new ResponseEntity<DMLResult>(result,HttpStatus.OK);
	}
	
	
	private Resource<ListHeader> getListHeaderResource(ListHeader listHeader) throws CListDataNotFoundException{
		Resource<ListHeader> resource = new Resource<>(listHeader);
		
		resource.add(linkTo(methodOn(ListHeaderResource.class).findById(listHeader.getId())).withSelfRel());
		
		return resource;
	}
	
	@Autowired
	public void setListHeaderRepository(ListHeaderRepository repo){
		this.repo = repo;
	}
	
}
