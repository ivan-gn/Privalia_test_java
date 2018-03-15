package com.privalia.services;

import java.util.List;

public interface CRUDService<T> {

	Iterable <?> listAll();
	T getById(Integer id);
	
	T saveOrUpdate(T domainObject);
	
	void delete(Integer id);
}
