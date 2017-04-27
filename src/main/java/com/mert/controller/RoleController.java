package com.mert.controller;

/**
 * Created by Yasin Mert on 25.02.2017.
 */
import javax.validation.Valid;

import com.mert.model.User;
import com.mert.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mert.service.RoleService;
import com.mert.model.Role;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {


	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newRole(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("role", new Role());
		modelAndView.addObject("roles", roleService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_NEW");
		modelAndView.setViewName("role");
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveRole(@Valid Role role, BindingResult bindingResult) {
		roleService.save(role);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/roles/all");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		return modelAndView;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allRoles() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new Role());
		//POINT=7 http://stackoverflow.com/questions/22364886/neither-bindingresult-nor-plain-target-object-for-bean-available-as-request-attr
		modelAndView.addObject("roles", roleService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("role");
		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateRole(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("personel_type", new Role());
		modelAndView.addObject("role", roleService.findRole(id));
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_UPDATE");
		modelAndView.setViewName("role");
		return modelAndView;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteRole(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/roles/all");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		roleService.delete(id);
		return modelAndView;
	}

	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		return user;
	}
}
