package com.app.moda.americana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.moda.americana.domain.Contact;
import com.app.moda.americana.service.ContactService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/all")
	public String inicio(Model model) {
		var contacts = contactService.getContacts();
		model.addAttribute("contacts", contacts); // El nombre entre "" se debe invocar en el index y luego se pasa
													// valor cliente a la par
		// Usamos model.addAttribute para enviar los valores al html
		return "contacts/contact";
	}

	@PostMapping("/save")
	public String saveContact(Contact contact) {
		contactService.save(contact);
		return "redirect:/"; // Con esto redirigimos a la página principal/index
	}

	@GetMapping("/delete/{idContact}")
	public String deleteContact(Contact contact, RedirectAttributes redirectAttributes) {

		// Valida si no hubo errores
		try {
			contactService.delete(contact);
			redirectAttributes.addFlashAttribute("state", "delete");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("state", "error");
		}
		return "redirect:/contact/all";
	}
}
