package com.mert.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mert.service.RoleServiceImpl;
import com.mert.service.UserService;
import com.mert.service.UserServiceImpl;
import com.mert.model.Role;
import com.mert.model.User;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {


	@Autowired
	private RoleServiceImpl roleService;
	

	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newRole(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("role", new Role());
		modelAndView.addObject("roles", roleService.findAll());
		modelAndView.addObject("mode", "MODE_NEW");
		modelAndView.setViewName("role");
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveRole(@Valid Role role, BindingResult bindingResult) {
		roleService.save(role);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/roles/all");
		return modelAndView;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allRoles() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new Role());
		//POINT=7 http://stackoverflow.com/questions/22364886/neither-bindingresult-nor-plain-target-object-for-bean-available-as-request-attr
		modelAndView.addObject("roles", roleService.findAll());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("role");
		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateRole(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("personel_type", new Role());
		modelAndView.addObject("role", roleService.findRole(id));
		modelAndView.addObject("mode", "MODE_UPDATE");
		modelAndView.setViewName("role");
		return modelAndView;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteRole(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/roles/all");
		roleService.delete(id);
		return modelAndView;
	}

}
