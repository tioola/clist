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
import org.springframework.web.servlet.tags.MessageTag;

import com.clist.domain.constants.RowErrorCodes;
import com.clist.domain.constants.RowMessages;
import com.clist.domain.entities.Row;
import com.clist.domain.exceptions.CListDataNotFoundException;
import com.clist.domain.exceptions.CListException;
import com.clist.domain.vo.DMLResult;
import com.clist.repositories.RowRepository;
import com.clist.service.RowService;

@RequestMapping("/rows")
@RestController
public class RowResource {
	
	
	private RowService rowService;
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<Resource<Row>> saveRow(@RequestBody Row row) throws CListException{
		
		Row rowInserted = rowService.insertRow(row);

		return new ResponseEntity<Resource<Row>>(getRowResource(rowInserted), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT , consumes="application/json", produces="application/json")
	public ResponseEntity<Resource<Row>> updateRow(@RequestBody Row row) throws CListException{
		
		Row updatedRow = rowService.updateRow(row);
		
		return new ResponseEntity<Resource<Row>>(getRowResource(updatedRow),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{rowId}",method=RequestMethod.GET, consumes="application/json", produces="application/json")
	public ResponseEntity<Resource<Row>> findById(@PathVariable("rowId") String rowId) throws CListException{
		
		Row rowFound = rowService.findRow(rowId);
		
		return new ResponseEntity<Resource<Row>>(getRowResource(rowFound), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{rowId}", method=RequestMethod.DELETE, consumes="application/json", produces="application/json")
	public ResponseEntity<DMLResult> deleteRow(@PathVariable("rowId") String rowId) throws CListException{
		
		DMLResult result = rowService.deleteRow(rowId); 
		
		return new ResponseEntity<DMLResult>(result, HttpStatus.OK);
	}
	
	private Resource<Row> getRowResource(Row row) throws CListException{
		
		Resource<Row> resource = new Resource<Row>(row);
		resource.add(linkTo(methodOn(RowResource.class).findById(row.getId())).withSelfRel());
		return resource;
		
	}
	
	@Autowired
	public void setRowService(RowService rowService){
		this.rowService = rowService;
	}
}
