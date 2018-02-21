package com.privalia.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModelProperty;

@Entity
@DynamicUpdate(value = true)
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "The database product ID")
	private Integer id;
	
	@Version
	@ApiModelProperty(notes = "The auto-generated version of the product")
	private Integer version;
	
	@ApiModelProperty(notes = "The application specific product ID")
	private String productId;
	
	@ApiModelProperty(notes = "The product description")
	@Size(min = 2 , max = 200)
	private String description;
	
	@ApiModelProperty(notes = "The image url of the product")
	@Size(min = 6 , max = 400)
	private String imageUrl;
	
	@ApiModelProperty(notes = "The image url of the product", required = true)
	@NotNull
	@Min(1)
	private BigDecimal price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}