package com.app.moda.americana.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.moda.americana.domain.Products;

public interface IProductRepository extends CrudRepository <Products, Long> {
	
}
