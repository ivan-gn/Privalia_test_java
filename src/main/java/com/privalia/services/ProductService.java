package com.privalia.services;

import com.privalia.domain.Product;

public interface ProductService {

	Iterable <Product> listAll();
	Product getById(Integer id);
	
	Product saveOrUpdate(Product domainObject);
	
	void delete(Integer id);
	
}
