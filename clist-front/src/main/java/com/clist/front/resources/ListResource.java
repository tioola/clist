package com.clist.front.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clist.domain.entities.List;
import com.clist.domain.enums.ListType;

@RestController
@RequestMapping("/hello")
public class ListResource {
	
	
	@RequestMapping(value="/hi", method=RequestMethod.GET)
	public List findList(){
		List hi = new List();
		hi.setId("1234");
		hi.setType(ListType.COLABORATIVE);
		
		return hi;
	}
	
	
}
