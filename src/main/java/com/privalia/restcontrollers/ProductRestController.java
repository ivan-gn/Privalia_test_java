package com.privalia.restcontrollers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.privalia.domain.Product;
import com.privalia.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
@Api(
		value = "online store",
		description = "Operations pertaining to products in Online Store"
)
public class ProductRestController {

	private ProductService productService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
		
	}
	
	@ApiOperation(value = "Search a product with an ID", response = Product.class)
	@RequestMapping(value = "/show/{id}",method = RequestMethod.GET, produces = "application/json")
	public Product showProduct(@PathVariable Integer id) {
		Product product = productService.getById(id);
		return product;
	}
	
	@ApiOperation(value = "View a list of available products", response = Iterable.class)

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),

	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),

	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),

	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")

	public Iterable<Product> list() {

		Iterable<Product> productList = productService.listAll();

		return productList;

	}


	@ApiOperation(value = "Add a product")

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")

	public ResponseEntity<String> saveProduct(@Valid @RequestBody Product product) {

		productService.saveOrUpdate(product);

		return new ResponseEntity<String>("Product saved successfully", HttpStatus.OK);

	}


	@ApiOperation(value = "Update a product")

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")

	public ResponseEntity<String> updateProduct(@PathVariable Integer id, @Valid @RequestBody Product product) {

		Product storedProduct = productService.getById(id);

		storedProduct.setDescription(product.getDescription());

		storedProduct.setImageUrl(product.getImageUrl());

		storedProduct.setPrice(product.getPrice());

		productService.saveOrUpdate(storedProduct);

		return new ResponseEntity<String>("Product updated successfully", HttpStatus.OK);

	}


	@ApiOperation(value = "Delete a product")

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")

	public ResponseEntity<String> delete(@PathVariable(value = "id", required = true) Integer id) {

		productService.delete(id);

		return new ResponseEntity<String>("Product deleted successfully", HttpStatus.OK);


	}

	
}
