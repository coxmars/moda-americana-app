package com.app.moda.americana.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.moda.americana.domain.Products;
import com.app.moda.americana.repository.IProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private IProductRepository productRepository;

	@Override
	@Transactional(readOnly = true) // Este metodo hace una conexión de solo lectura
	public List<Products> getProducts(boolean bale) {
		List<Products> allProducts = (List<Products>) productRepository.findAll();
		List<Products> filteredPacaProducts = new ArrayList<>();
		List<Products> filteredUnitaryProducts = new ArrayList<>();

		if (bale) {
			for (Products product : allProducts) {
				if (product.isBale()) {
					filteredPacaProducts.add(product);
				}
			}

		} else if (!bale) {
			for (Products product : allProducts) {
				if (!product.isBale()) {
					filteredUnitaryProducts.add(product);
				}
			}
			return filteredUnitaryProducts;
		}

		return filteredPacaProducts;
	}

	@Override
	@Transactional
	public void save(Products product) {
		if (product == null) {
			throw new IllegalArgumentException("El objeto Products no puede ser nulo.");
		}
		if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Product_Name es obligatorio.");
		}
		if (product.getProductDescription() == null || product.getProductDescription().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Product_Description es obligatorio.");
		}
		if (product.getProductQuantity() == 0) {
			throw new IllegalArgumentException("El campo productQuantity es obligatorio.");
		}
		if (product.getUnitPrice() == null) {
			throw new IllegalArgumentException("El campo Unit_Price es obligatorio.");
		}
		if (product.getGenderProduct() == null || product.getGenderProduct().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Gender_Product es obligatorio.");
		}
		if (product.getProductPrice() == null) {
			throw new IllegalArgumentException("El campo Product_Price es obligatorio.");
		}
		if (product.getCategory() == null) {
			throw new IllegalArgumentException("La relación con la tabla Category es obligatoria.");
		}
		if (product.getProvider() == null) {
			throw new IllegalArgumentException("La relación con la tabla Provider es obligatoria.");
		}
		productRepository.save(product);
	}

	@Override
	@Transactional
	public void delete(Products product) {
		productRepository.delete(product);
	}

	@Override
	@Transactional(readOnly = true) // Este metodo hace una conexión de solo lectura
	public Products getProduct(Products product) {
		// Se hace busqueda por ID, en caso contrario es null
		return productRepository.findById(product.getProductId()).orElse(null);
	}

	// Tratar de guardarlo en un procedimiento almacenado / Usar JPQL
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> sumProductsByCategory() {
		return entityManager.createQuery(
				"SELECT c.categoryName, SUM(p.productQuantity) FROM Products p JOIN p.category c GROUP BY c.categoryName")
				.getResultList();
	}

	// Tratar de guardarlo en un procedimiento almacenado / Usar JPQL
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> countBoughtBalesByProvider() {
		return entityManager.createQuery(
				"SELECT p.provider.providerName, COUNT(p.productId) FROM Products p WHERE p.isBale = true GROUP BY p.provider.providerName")
				.getResultList();
	}

	// Metodo para el grafico cantidad productos por categoria
	@Override
	public Map<String, Object> getProductsByCategory() {
		List<Object[]> productsByCategory = sumProductsByCategory();
		List<String> categories = new ArrayList<>();
		List<Long> quantities = new ArrayList<>();
		for (Object[] result : productsByCategory) {
			categories.add((String) result[0]);
			quantities.add((Long) result[1]);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("categories", categories);
		data.put("quantities", quantities);
		return data;
	}

	// Metodo para el grafico cantidad productos por categoria
	@Override
	public Map<String, Object> getBoughtBalesByProvider() {
		List<Object[]> boughtBalesByProvider = countBoughtBalesByProvider();
		List<String> providers = new ArrayList<>();
		List<Long> baleQuantities = new ArrayList<>();
		for (Object[] result : boughtBalesByProvider) {
			providers.add((String) result[0]);
			baleQuantities.add((Long) result[1]);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("providers", providers);
		data.put("baleQuantities", baleQuantities);
		return data;
	}

}
