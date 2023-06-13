package com.app.moda.americana.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.moda.americana.domain.Products;
import com.app.moda.americana.service.CategoryService;
import com.app.moda.americana.service.ProductService;
import com.app.moda.americana.service.ProviderService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProviderService providerService;

	// Estos metodos son para pacas //

	@GetMapping("/all/pacas")
	public String listPacas(Model model) {
		boolean isBale = true;
		List<Products> products = productService.getProducts(isBale);
		model.addAttribute("products", products); // El nombre entre "" se debe invocar en el index y luego se pasa
													// valor cliente a la par
		// Usamos model.addAttribute para enviar los valores al html
		model.addAttribute("currentPage", "paca");

		// Verifica si se guardo la paca
		Object message = model.asMap().get("state");
		if (message != null) {
			model.addAttribute("state", message);
		}

		return "products/paca";
	}

	@GetMapping("/new/paca")
	public String showPacaForm(Products product, Model model) {
		model.addAttribute("categories", categoryService.listAll());
		model.addAttribute("providers", providerService.getProviders());
		model.addAttribute("currentPage", "paca");
		return "products/new_paca";
	}

	@PostMapping("/save/paca")
	public String savePaca(Products product, Model model, RedirectAttributes redirectAttributes) {
		// Establecer el valor de isBale
		product.setIsBale(true);
		// Valida si no hubo errores
		try {
			productService.save(product);
			redirectAttributes.addFlashAttribute("state", "success");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("state", "error");
		}
		return "redirect:/product/all/pacas"; // Con esto redirigimos a la lista pacas
	}

	@GetMapping("/modify/paca/{productId}")
	public String modifyPaca(Products product, Model model) {
		product = productService.getProduct(product);
		String editar = "editar";
		model.addAttribute("editar", editar);
		model.addAttribute("products", product);
		model.addAttribute("categories", categoryService.listAll());
		model.addAttribute("providers", providerService.getProviders());
		model.addAttribute("currentPage", "paca");
		return "products/new_paca";
	}

	@GetMapping("/delete/paca/{productId}")
	public String deletePaca(Products product, RedirectAttributes redirectAttributes) {		

		// Valida si no hubo errores
		try {
			productService.delete(product);
			redirectAttributes.addFlashAttribute("state", "delete");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("state", "error");
		}
		return "redirect:/product/all/pacas";
	}

	// Estos metodos son para prendas unitarias //

	@GetMapping("/all/unitary")
	public String listUnitaryGarments(Model model) {
		boolean isBale = false;
		List<Products> products = productService.getProducts(isBale);
		model.addAttribute("products", products); // El nombre entre "" se debe invocar en el index y luego se pasa
													// valor cliente a la par
		// Usamos model.addAttribute para enviar los valores al html
		model.addAttribute("currentPage", "unitary");
		// Verifica si se guardo la paca
		Object message = model.asMap().get("state");
		if (message != null) {
			model.addAttribute("state", message);
		}
		return "products/unitary_clothing";
	}

	@GetMapping("/new/unitary")
	public String showUnitaryGarmentForm(Products product, Model model) {
		model.addAttribute("categories", categoryService.listAll());
		model.addAttribute("providers", providerService.getProviders());
		model.addAttribute("currentPage", "unitary");
		return "products/new_unitary_clothing";
	}

	@PostMapping("/save/unitary")
	public String saveUnitaryGarment(Products product, RedirectAttributes redirectAttributes) {
		// Establecer el valor de isBale
		product.setIsBale(false);
		// Valida si no hubo errores
		try {
			productService.save(product);
			redirectAttributes.addFlashAttribute("state", "success");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("state", "error");
		}
		return "redirect:/product/all/unitary"; // Con esto redirigimos a la lista proveedores
	}

	@GetMapping("/modify/unitary/{productId}")
	public String modifyUnitaryGarment(Products product, Model model) {
		product = productService.getProduct(product);
		model.addAttribute("products", product);
		model.addAttribute("categories", categoryService.listAll());
		model.addAttribute("providers", providerService.getProviders());
		model.addAttribute("currentPage", "unitary");
		return "products/new_unitary_clothing";
	}

	@GetMapping("/delete/unitary/{productId}")
	public String deleteUnitaryGarment(Products product, RedirectAttributes redirectAttributes) {
		
		// Valida si no hubo errores
		try {
			productService.delete(product);
			redirectAttributes.addFlashAttribute("state", "delete");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("state", "error");
		}
		return "redirect:/product/all/unitary";
	}

}
