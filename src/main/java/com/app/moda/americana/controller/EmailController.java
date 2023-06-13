package com.app.moda.americana.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.moda.americana.domain.Promotion;
import com.app.moda.americana.domain.User;
import com.app.moda.americana.service.EmailService;
import com.app.moda.americana.service.UserService;

@Controller
@RequestMapping("/email")
public class EmailController {

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;

	@GetMapping("/send-promotion")
	public ModelAndView sendPromotionForm() {
		ModelAndView modelAndView = new ModelAndView("emails/promotion-form");
		modelAndView.addObject("promotion", new Promotion());
		return modelAndView;
	}

	@PostMapping("/send-promotion")
	public String sendPromotion(@ModelAttribute("promotion") Promotion promotion,
			RedirectAttributes redirectAttributes) {
		// Filtra los usuarios con la promoción activa
		List<String> emails = userService.listAll()
				.stream()
				.filter(User::isPromotion)
				.map(User::getEmail)
				.collect(Collectors.toList());

		// Valida si no hubo errores
		try {
			emailService.sendPromotionEmails(emails, promotion.getSubject(), promotion.getDescription());
			redirectAttributes.addFlashAttribute("state", "send");
			return "redirect:/email/send-promotion?success";
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("state", "error");
			return "redirect:/email/send-promotion?error";
		}
	}

}
