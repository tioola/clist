package com.clist.repositories;

import java.util.List;

public interface GenericRepository<T,ID> {

	<S extends T> List<S> save(Iterable<S> entites);
	<S extends T> S insert(S entity);
	<S extends T> S save(S entity);
	T findOne(ID id); 
	Long deleteById(ID id);
}
