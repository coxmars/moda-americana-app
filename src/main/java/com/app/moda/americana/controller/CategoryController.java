package com.app.moda.americana.controller;

import com.app.moda.americana.domain.Category;
import com.app.moda.americana.domain.User;
import com.app.moda.americana.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

@Controller
public class CategoryController {
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/category")
	public String viewCategories(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("name", name);

		List<Category> viewCategory = categoryService.listAll();
		model.addAttribute("listCategories", viewCategory);
		model.addAttribute("currentPage", "category");
		// Verifica si se guardo la paca
		Object message = model.asMap().get("state");
		if (message != null) {
			model.addAttribute("state", message);
		}

		return ("category/category");
	}

	@GetMapping("/new_category")
	public String viewCategory(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("category", new Category());
		model.addAttribute("currentPage", "category");
		return ("category/new_category");
	}

	@PostMapping("/addCategory")
	public String addCategory(Category category, RedirectAttributes redirectAttributes) {

		// Valida si no hubo errores
		try {
			categoryService.addCategory(category);
			redirectAttributes.addFlashAttribute("state", "success");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("state", "error");
		}

		return ("redirect:/category");
	}

	@GetMapping("/category/edit/{id}")
	public String viewEditCategory(@PathVariable Long id, Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("name", name);
		model.addAttribute("category", categoryService.getOne(id));
		model.addAttribute("currentPage", "category");
		return ("category/edit_category");
	}

	@PostMapping("/category/{id}")
	public String updateCategory(@PathVariable Long id, @ModelAttribute("category") Category category, Model model,
			RedirectAttributes redirectAttributes) {

		model.addAttribute("currentPage", "category");
		// Valida si no hubo errores
		try {
			Category categoryTmp = categoryService.getOne(id);
			categoryTmp.setCategoryName(category.getCategoryName());
			categoryTmp.setCategoryDescription(category.getCategoryDescription());
			categoryService.editCategory(categoryTmp);
			redirectAttributes.addFlashAttribute("state", "success");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("state", "error");
		}

		return ("redirect:/category");
	}

	@GetMapping("/category/{id}")
	public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			categoryService.deleteCategory(id);
			redirectAttributes.addFlashAttribute("state", "delete");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("state", "error");
		}

		return ("redirect:/category");
	}

}
