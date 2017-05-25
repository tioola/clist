package com.clist.front.resources;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
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
import com.clist.service.ListHeaderService;

@RestController
@RequestMapping("/listheaders")
public class ListHeaderResource {
	
	
	@Autowired
	private ListHeaderService listHeaderService;
	
	
	@RequestMapping( method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<Resource<ListHeader>> saveListHeader(@RequestBody ListHeader header) throws CListException{
		
		ListHeader insertedHeader = listHeaderService.insertListHeader(header);
				
		return new ResponseEntity<Resource<ListHeader>>(getListHeaderResource(insertedHeader),HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", produces="application/json")
	public ResponseEntity<Resource<ListHeader>> updateListHeader(@RequestBody ListHeader header) throws CListException{
		
		ListHeader updatedHeader = listHeaderService.updateListHeader(header);
		
		return new ResponseEntity<Resource<ListHeader>>(getListHeaderResource(updatedHeader),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{listHeaderId}", method=RequestMethod.GET, consumes="application/json", produces="application/json" )
	public ResponseEntity<Resource<ListHeader>> findById(@PathVariable("listHeaderId") String id ) throws CListException{
		
		ListHeader returnedHeader = listHeaderService.findListHeader(id);
		
		return new ResponseEntity<Resource<ListHeader>>(getListHeaderResource(returnedHeader),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{listHeaderId}",method=RequestMethod.DELETE ,consumes="application/json", produces="application/json")
	public ResponseEntity<DMLResult> deleteById(@PathVariable("listHeaderId") String id ) throws CListException{

		DMLResult result = listHeaderService.deleteListHeader(id);
		
		return new ResponseEntity<DMLResult>(result,HttpStatus.OK);
	}
	
	
	private Resource<ListHeader> getListHeaderResource(ListHeader listHeader) throws CListException{
		Resource<ListHeader> resource = new Resource<>(listHeader);
		
		resource.add(linkTo(methodOn(ListHeaderResource.class).findById(listHeader.getId())).withSelfRel());
		
		return resource;
	}
	
	@Autowired
	public void setListHeaderRepository(ListHeaderService listHeaderService){
		this.listHeaderService = listHeaderService;
	}
	
}
