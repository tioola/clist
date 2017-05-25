package com.clist.service;

import com.clist.domain.entities.User;
import com.clist.domain.exceptions.CListException;
import com.clist.domain.vo.DMLResult;

public interface UserService {

	User insertUser(User user) throws CListException;
	
	User updateUser(User user) throws CListException;
	
	User findUser(String id) throws CListException;
	
	DMLResult deleteUser(String user) throws CListException;
	
}
