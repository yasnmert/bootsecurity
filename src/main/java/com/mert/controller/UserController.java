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

import com.mert.model.User;

import com.mert.service.RoleServiceImpl;
import com.mert.service.UserService;
import com.mert.service.UserServiceImpl;

@Controller
@RequestMapping("/personels")
public class UserController {


	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private RoleServiceImpl roleServiceImpl;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allUsers() {
		ModelAndView modelAndView = new ModelAndView();
		//POINT=7 http://stackoverflow.com/questions/22364886/neither-bindingresult-nor-plain-target-object-for-bean-available-as-request-attr
		modelAndView.addObject("personels", userServiceImpl.findAll());
		modelAndView.addObject("mode", "MODE_ALL");
		//--------------------------------------------
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User control = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("control", control.getRole().getRole());//Authentication for NavBar
		//---------------------------------------------
		modelAndView.setViewName("personel");
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("redirect:/personels/all");
		user.setPassword(userServiceImpl.findUser(user.getId()).getPassword());
		user.setActive(userServiceImpl.findUser(user.getId()).getActive());
		userServiceImpl.save(user);
		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateUser(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new User());
		modelAndView.addObject("personel", userServiceImpl.findUser(id));
		modelAndView.addObject("roles", roleServiceImpl.findAll());
		modelAndView.addObject("mode", "MODE_UPDATE");
		//--------------------------------------------
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User control = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("control", control.getRole().getRole());//Authentication for NavBar
		//---------------------------------------------
		modelAndView.setViewName("personel");
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/personels/all");
		modelAndView.addObject("rule", new User());
		userServiceImpl.delete(id);
		return modelAndView;
	}


}







