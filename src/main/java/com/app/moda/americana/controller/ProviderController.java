package com.app.moda.americana.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.moda.americana.domain.Provider;
import com.app.moda.americana.service.ProviderService;

@Controller
@RequestMapping("/provider")
public class ProviderController {

	@Autowired
	private ProviderService providerService;

	@GetMapping("/all")
	public String listProviders(Model model) {
		List<Provider> providers = providerService.getProviders();
		model.addAttribute("providers", providers); // El nombre entre "" se debe invocar en el index y luego se pasa
													// valor cliente a la par
		// Usamos model.addAttribute para enviar los valores al html
		model.addAttribute("currentPage", "provider");
		// Verifica si se guardo la paca
		Object message = model.asMap().get("state");
		if (message != null) {
			model.addAttribute("state", message);
		}
		return "providers/provider";
	}

	@GetMapping("/new")
	public String showProviderForm(Provider provider, Model model) {
		model.addAttribute("currentPage", "providerNew");
		return "providers/new_provider";
	}

	@PostMapping("/save")
	public String saveProvider(Provider provider, RedirectAttributes redirectAttributes) {

		// Valida si no hubo errores
		try {
			providerService.save(provider);
			redirectAttributes.addFlashAttribute("state", "success");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("state", "error");
		}
		return "redirect:/provider/all"; // Con esto redirigimos a la lista proveedores
	}

	@GetMapping("/modify/{providerId}")
	public String modifyProvider(Provider provider, Model model) {
		provider = providerService.getProvider(provider);
		model.addAttribute("provider", provider);
		model.addAttribute("currentPage", "provider");
		return "providers/new_provider";
	}

	@GetMapping("/delete/{providerId}")
	public String deleteProvider(Provider provider, RedirectAttributes redirectAttributes) {

		// Valida si no hubo errores
		try {
			providerService.delete(provider);
			redirectAttributes.addFlashAttribute("state", "delete");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("state", "error");
		}
		return "redirect:/provider/all";
	}
}
