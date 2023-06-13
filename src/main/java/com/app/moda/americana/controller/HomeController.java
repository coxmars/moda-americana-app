package com.app.moda.americana.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.app.moda.americana.domain.Contact;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.moda.americana.domain.User;
import com.app.moda.americana.service.ProductService;
import com.app.moda.americana.service.UserService;

@Controller
public class HomeController {

    private final UserService userService;
    private final ProductService productService;

    public HomeController(UserService userService,ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }
    
    
    @GetMapping("/binnacle")
    public String prueba() {
        return ("binnacle/binnacle");
    }
    
    @GetMapping("/testimonial")
    public String prueba2() {
        return ("testimonials/testimonial");
    }
    
    @GetMapping("/form_contact")
    public String prueba9(Model model) {
    	model.addAttribute("contact", new Contact());
        return ("contacts/form_contact");
    }
    
    
    @GetMapping("")
    public String viewHomePage() {
        return ("welcome/welcome");
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "invalid-session", defaultValue = "false") boolean invalidSession, Model model) {

        // Problema aquí

        if (invalidSession) {
            model.addAttribute("invalidSession", "Session expired, please re-login");
        }

        return "authentication/login";

    }

	// @Secured("ROLE_ADMIN")
	@GetMapping("/dash")
	public String viewDash(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("name", name);

		List<User> viewDash = userService.listAll();
		model.addAttribute("listUsers", viewDash);
		model.addAttribute("currentPage", "dash");
		// Lógica consulta para grafico genero
		List<Map<String, Object>> usersByGender = userService.usersByGender();
		// Lógica consulta para grafico cantidad productos por categoria
		Map<String, Object> productsByCategory = productService.getProductsByCategory();
		// Lógica consulta para grafico cantidad pacas compradas por proveedor
		Map<String, Object> boughtBalesByProvider = productService.getBoughtBalesByProvider();
		if (!usersByGender.isEmpty() & !productsByCategory.isEmpty() & !boughtBalesByProvider.isEmpty()) {
			model.addAttribute("usersByGender", usersByGender);
			model.addAttribute("categories", productsByCategory.get("categories"));
			model.addAttribute("quantities", productsByCategory.get("quantities"));
			model.addAttribute("providers", boughtBalesByProvider.get("providers"));
			model.addAttribute("baleQuantities", boughtBalesByProvider.get("baleQuantities"));
		}
		return ("index");
	}
    
    @GetMapping("/new_user")
    public String viewNewUser(Model model) {
        model.addAttribute("user", new User());
        return ("users/new_user");
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)) {
            return "authentication/verify_success";
        } else {
            return "authentication/verify_fail";
        }
    }

}