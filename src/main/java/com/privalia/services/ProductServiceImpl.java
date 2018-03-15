package com.privalia.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privalia.domain.Product;
import com.privalia.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ProductRepository productRepository;
	
	@Autowired
	public ProductRepository getProductRepository() {
		return productRepository;
	}


	@Override
	public Iterable<Product> listAll() {
		logger.debug("listAllProducts called");
		return productRepository.findAll();
	}

	@Override
	public Product getById(Integer id) {
		logger.debug("getById called");
		return productRepository.findOne(id);
	}

	@Override
	public Product saveOrUpdate(Product product) {
		logger.debug("saveOrUpdate called");
		return productRepository.save(product);
	}

	@Override
	public void delete(Integer id) {
		logger.debug("delete called");	
		productRepository.delete(id);
	}	
}
