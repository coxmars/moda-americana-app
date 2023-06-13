package com.app.moda.americana.service;

import com.app.moda.americana.domain.Category;
import com.app.moda.americana.repository.ICategoryRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {

	private final ICategoryRepository categoryRepository;

	public CategoryService(ICategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> listAll() {
		return categoryRepository.findAll();
	}

	public Category getOne(Long id) {
		return categoryRepository.findById(id).get();
	}

	public void addCategory(Category category) {
		if (category == null) {
			throw new IllegalArgumentException("El objeto Category no puede ser nulo.");
		}
		if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Category Name es obligatorio.");
		}
		if (category.getCategoryDescription() == null || category.getCategoryDescription().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Category Description es obligatorio.");
		}
		
		categoryRepository.save(category);
	}

	public void editCategory(Category category) {
		if (category == null) {
	        throw new IllegalArgumentException("El objeto Category no puede ser nulo.");
	    }
	    if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo Category Name es obligatorio.");
	    }
	    if (category.getCategoryDescription() == null || category.getCategoryDescription().trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo Category Description es obligatorio.");
	    }
	    if (category.getDateCategory() == null) {
	        throw new IllegalArgumentException("El campo Date Category es obligatorio.");
	    }
	    categoryRepository.save(category);
	}

	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}
}
