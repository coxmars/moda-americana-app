package com.app.moda.americana.controller;

import com.app.moda.americana.domain.Role;
import com.app.moda.americana.domain.User;
import com.app.moda.americana.service.RoleService;
import com.app.moda.americana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.*;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

	private final UserService userService;

	private final PasswordEncoder passwordEncoder;

	private final RoleService roleService;

	public UserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
	}


	@GetMapping("/404")
		public String viewError(){
			return "/404";
	}

	@GetMapping("/users")
	public String viewUsers(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("name", name);

		List<User> viewDash = userService.listAll();
		model.addAttribute("listUsers", viewDash);
		model.addAttribute("currentPage", "user");
		// Verifica si se guardo la paca
		Object message = model.asMap().get("state");
		if (message != null) {
			model.addAttribute("state", message);
		}
		return ("users/users");
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return ("authentication/signup_form");
	}

	@PostMapping("/process_register")
	public String processRegister(User user, HttpServletRequest request)
			throws MessagingException, IOException {
		userService.register(user, getSiteURL(request));
		return "authentication/register_success";
	}

	@PostMapping("/process_new_user")
	public String processNewUser(User user, HttpServletRequest request)
			throws MessagingException, IOException {
		userService.register(user, getSiteURL(request));
		return "redirect:/users";
	}

	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
		userService.deleteUser(id);
		return "redirect:/users";
	}

	@PostMapping("/user/delete/{id}")
	public String deleteUserProfile(@PathVariable Long id, HttpSession session){
		userService.deleteUser(id);
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/user/update/{id}")
	public String viewEditUser(@PathVariable Long id, Model model) {
		// userService.deleteUser(id);
		model.addAttribute("currentPage", "user");
		model.addAttribute("user", userService.getOne(id));
		model.addAttribute("role", roleService.getOne(id));
		return "users/edit_my_profile";
	}

	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user,
			@ModelAttribute("role") Role role, RedirectAttributes redirectAttributes) {

		// Valida si no hubo errores
		try {
			User userTmp = userService.getOne(id);
			Role roleTmp = roleService.getOne(id);
			userTmp.setEmail(user.getEmail());
			userTmp.setGender(user.getGender());
			userTmp.setIdentification(user.getIdentification());
			userTmp.setIdentificationType(user.getIdentificationType());
			userTmp.setPromotion(user.isPromotion());
			userTmp.setPhoneNumber(user.getPhoneNumber());
			userTmp.setFirstName(user.getFirstName());
			userTmp.setLastName(user.getLastName());
			roleTmp.setAuthority(role.getAuthority());
			userService.updateUser(userTmp);
			roleService.updateRole(roleTmp);
			redirectAttributes.addFlashAttribute("state", "success");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("state", "error");
		}

		return "redirect:/users";
	}

	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

	@GetMapping("/user/password")
	public String viewupdatePassword(Model model) {
		model.addAttribute("user", new User());
		return "users/changePassword";
	}

	@PostMapping("/user/password/{id}")
	public String updatePassword(@PathVariable Long id, @ModelAttribute("user") User user) {
		User userTmp = userService.getOne(id);
		// String encodedPassword = passwordEncoder.encode(user.getPassword());
		if (passwordEncoder.matches(user.getPassword(), userTmp.getPassword())) {
			return "users/changePasswordSuccess";
		} else {
			return "users/changePassword";
		}

	}

	@GetMapping("/my_profile/{id}")
	public String viewProfile(@PathVariable Long id, Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("name",name);
		model.addAttribute("user", userService.getOne(id));
		List<User> viewDash = userService.listAll();
		model.addAttribute("listUsers", viewDash);
		return ("users/my_profile");
	}

	@PostMapping("/user/profile/update/{id}")
	public String updateMyProfile(@PathVariable Long id, @ModelAttribute("user") User user, @RequestParam(name = "file", required = false) MultipartFile image, RedirectAttributes redirectAttributes) {
		try {
			User userTmp = userService.getOne(id);
			userTmp.setEmail(user.getEmail());
			userTmp.setGender(user.getGender());
			userTmp.setIdentification(user.getIdentification());
			userTmp.setIdentificationType(user.getIdentificationType());
			userTmp.setPromotion(user.isPromotion());
			userTmp.setPhoneNumber(user.getPhoneNumber());
			userTmp.setFirstName(user.getFirstName());
			userTmp.setLastName(user.getLastName());
			userService.updateUser(userTmp);
			redirectAttributes.addFlashAttribute("state", "success");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("state", "error");
		}

		return "redirect:/my_profile/" + id;
	}
	@PostMapping("/user/password/changePasswordSuccess/{id}")
	public String updatePasswordConfirm(@PathVariable Long id, @ModelAttribute("user") User user,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("confirmnewPassword") String confirmnewPassword) {

		User userTmp = userService.getOne(id);

		if (newPassword.equals(confirmnewPassword)) {
			String encodedPassword = passwordEncoder.encode(newPassword);
			userTmp.setPassword(encodedPassword);
			userService.updateUser(userTmp);
			// return "redirect:/users";
		} else {
			// return "/users/changePasswordSuccess";

		}

		return "redirect:/login";
	}

}
