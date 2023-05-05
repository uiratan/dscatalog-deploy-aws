package com.devsuperior.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.tests.TestsFactory;

@DataJpaTest
class ProductRepositoryTest {
	
	private long existingId;
	private long nonExistingId;
	private long countTotalProducts;
	
	@Autowired
	private ProductRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Product product = TestsFactory.createProduct();
		product.setId(null);
		
		product = repository.save(product);
		
		Assertions.assertNotNull(product.getId());
		Assertions.assertEquals(countTotalProducts+1, product.getId());
	}
	
	@Test
	void deleShouldDeleteObjectWhenIdExists() {
		repository.deleteById(existingId);		
		Optional<Product> result = repository.findById(existingId);
		
		Assertions.assertFalse(result.isPresent());		
	}
	
	@Test
	void deleShouldThrowEmptyResultDataAccessExceptionWhenIdNotExists() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});				
	}
	
	@Test
	void findByIdShouldReturnFilledOptionalWhenIdExists() {
		Optional<Product> result = repository.findById(existingId);
		Assertions.assertNotNull(result);
	}
	
	@Test
	void findByIdShouldReturnNullOptionalWhenIdNotExists() {
		Optional<Product> result = repository.findById(nonExistingId);
		Assertions.assertEquals(Optional.empty(), result);
	}
	

}
