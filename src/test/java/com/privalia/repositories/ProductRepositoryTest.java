package com.privalia.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.privalia.configuration.RepositoryConfiguration;
import com.privalia.domain.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RepositoryConfiguration.class})
public class ProductRepositoryTest {

	private ProductRepository productRepository;
	private Product product1  = null;
	private Product product2 = null;
	@Rule
	public TestName testName = new TestName();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryTest.class);
	
	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Before
	public void setUp() throws Exception{
		
		product1 = new Product();
		product1.setDescription("Privalia Spring Framework");
		product1.setPrice(new BigDecimal("21.95"));
		product1.setProductId("1232");
		productRepository.save(product1);
		
		product2 = new Product();
		product2.setDescription("Privalia Spring Framework");
		product2.setPrice(new BigDecimal("91.95"));
		product2.setProductId("986");
		productRepository.save(product2);
		
		LOGGER.info("Started test "+ testName.getMethodName());
//		save
//		getall
//		update
//		delete
		
	}
	
	
	@After
	public void after() throws Exception{
		productRepository.deleteAll();
		LOGGER.info("Finished test "+ testName.getMethodName());

	}
	
	@Test
	public void testSaveProduct() {
		Product product = new Product();
		product.setDescription("Description cambiada");
		product.setPrice(new BigDecimal("18.95"));
		product.setProductId("1234");
		
		assertNull(product.getId());
		
		productRepository.save(product);
		
		assertNotNull(product.getId());

		
	}
	
	@Test
	public void testGetAllProducts() {
		Iterable<Product> products = productRepository.findAll();
		long size = products.spliterator().getExactSizeIfKnown();
		assertEquals(2,size);
	}
	
	@Test
	public void testModifyProduct() {
		product1.setDescription("Description cambiada");
		Product updatedProduct = productRepository.save(product1);
		assertEquals(updatedProduct.getDescription(), "Description cambiada");

	}

	@Test
	public void testDelete() {
		productRepository.delete(product2.getId());
		assertNull(productRepository.findOne(product2.getId()));
	}
	
	@Test
	public void testFindByProductId() {
		assertNotNull(productRepository.findByProductId(product1.getProductId()));
	}
	
	@Test
	public void  testfindByDescriptionAndPrice() {
		assertNotNull(productRepository.findByDescriptionAndPrice(product1.getDescription(), product1.getPrice()));
	}
	
	@Test
	public void testUpdateProduct(){
		assertTrue(productRepository.updateProduct(product1.getId(), "UPDATED DESCRIPTION") == 1);
	}
		
}
