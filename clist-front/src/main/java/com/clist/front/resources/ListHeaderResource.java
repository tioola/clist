package com.clist.front.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clist.domain.entities.Column;
import com.clist.domain.entities.ListHeader;
import com.clist.domain.entities.User;
import com.clist.domain.enums.ListType;
import com.clist.domain.enums.Visibility;
import com.clist.repositories.ListHeaderRepository;

@RestController
@RequestMapping("/listheader")
public class ListHeaderResource {
	
	
	@Autowired
	private ListHeaderRepository repo;
	
	@RequestMapping(value="/save", method=RequestMethod.GET)
	public String storeListHeader(){
		ListHeader hi = new ListHeader();
		
		User admin = new User();
		admin.setName("Diogo Favero Fabrile");
		admin.setId("diogof.fabrile@gmail.com");

		User nara = new User();
		nara.setName("Nara volponi Lopes");
		nara.setId("nara.lopes@gmail.com");
		
		
		
		Column columna = new Column();
		columna.setDescription("Empresa");
		columna.setPosition("A");
		columna.setSize(40L);
		columna.setVisibleTo(Visibility.ALL);

		Column columnb = new Column();
		columnb.setDescription("Cidade");
		columnb.setPosition("B");
		columnb.setSize(20L);
		columnb.setVisibleTo(Visibility.ALL);
		
		
		Column columnc = new Column();
		columnc.setDescription("Salario");
		columnc.setPosition("C");
		columnc.setSize(10L);
		columnc.setVisibleTo(Visibility.ALL);
		
		Column columnd = new Column();
		columnd.setDescription("Details");
		columnd.setPosition("D");
		columnd.setSize(100L);
		columnd.setVisibleTo(Visibility.ALL);
		
		List<Column> mainColumns = new ArrayList<Column>();
		mainColumns.add(columna);
		mainColumns.add(columnb);
		mainColumns.add(columnc);
		
		List<Column> detailColumns = new ArrayList<Column>();
		detailColumns.add(columnd);
		
		List<User> usersAllowed = new ArrayList<User>();
		usersAllowed.add(nara);
		

		hi.setUsersAllowed(usersAllowed);
		hi.setMainColumns(mainColumns);
		hi.setDetailColumns(detailColumns);
		


		hi.setId("diogofabrile");
		hi.setAdmin(admin);
		hi.setType(ListType.COLABORATIVE);
		
		repo.insert(hi);
		return "Saved";
	}
	
	@RequestMapping(value="/sample", method=RequestMethod.GET)
	public ListHeader searchListHeader(){
		ListHeader hi = new ListHeader();
		
		User admin = new User();
		admin.setName("Diogo Favero Fabrile");
		admin.setId("diogof.fabrile@gmail.com");

		User nara = new User();
		nara.setName("Nara volponi Lopes");
		nara.setId("nara.lopes@gmail.com");
		
		
		
		Column columna = new Column();
		columna.setDescription("Empresa");
		columna.setPosition("A");
		columna.setSize(40L);
		columna.setVisibleTo(Visibility.ALL);

		Column columnb = new Column();
		columnb.setDescription("Cidade");
		columnb.setPosition("B");
		columnb.setSize(20L);
		columnb.setVisibleTo(Visibility.ALL);
		
		
		Column columnc = new Column();
		columnc.setDescription("Salario");
		columnc.setPosition("C");
		columnc.setSize(10L);
		columnc.setVisibleTo(Visibility.ALL);
		
		Column columnd = new Column();
		columnd.setDescription("Details");
		columnd.setPosition("D");
		columnd.setSize(100L);
		columnd.setVisibleTo(Visibility.ALL);
		
		List<Column> mainColumns = new ArrayList<Column>();
		mainColumns.add(columna);
		mainColumns.add(columnb);
		mainColumns.add(columnc);
		
		List<Column> detailColumns = new ArrayList<Column>();
		detailColumns.add(columnd);
		
		List<User> usersAllowed = new ArrayList<User>();
		usersAllowed.add(nara);
		

		hi.setUsersAllowed(usersAllowed);
		hi.setMainColumns(mainColumns);
		hi.setDetailColumns(detailColumns);
		


		hi.setId("diogofabrile");
		hi.setAdmin(admin);
		hi.setType(ListType.COLABORATIVE);
		
		return hi;
	}
	
	
}
