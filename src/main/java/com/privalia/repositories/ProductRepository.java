package com.privalia.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.privalia.domain.Product;


@RepositoryRestResource
public interface ProductRepository extends CrudRepository <Product, Integer>{
	Product findByProductId(String productId);
	
	Product findByDescriptionAndPrice(String description, BigDecimal price);
	
	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.description = :description WHERE p.id =:id")
	int updateProduct(@Param("id")int id,@Param("description") String description);
	
}
