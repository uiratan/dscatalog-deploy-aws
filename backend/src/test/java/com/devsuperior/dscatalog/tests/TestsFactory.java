package com.devsuperior.dscatalog.tests;

import java.time.Instant;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

public class TestsFactory {

	public static Product createProduct() {
		Product product = new Product(1L, "Phone", "Good Phone", 800.0, "img", Instant.parse("2020-07-14T10:00:00Z"));
		product.getCategories().add(new Category(2L, "Eletrônicos"));
		return product;
	}
	
	public static ProductDTO createProductDTO() {
		Product product = createProduct();
		return new ProductDTO(product, product.getCategories());
	}
	
}
