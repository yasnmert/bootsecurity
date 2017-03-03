package com.mert.controller;

import javax.servlet.http.HttpServletRequest;
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

import com.mert.model.Role;
import com.mert.model.User;

import com.mert.service.RoleServiceImpl;
import com.mert.service.TaskService;
import com.mert.service.UserServiceImpl;

@Controller
@RequestMapping("/personels")
public class UserController {


	@Autowired
	private UserServiceImpl personelService;

	@Autowired
	private RoleServiceImpl roleServiceImpl;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allPersonels() {
		ModelAndView modelAndView = new ModelAndView();
		//POINT=7 http://stackoverflow.com/questions/22364886/neither-bindingresult-nor-plain-target-object-for-bean-available-as-request-attr
		modelAndView.addObject("personels", personelService.findAll());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("personel");
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView savePersonel_type(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		user.setPassword(personelService.findUser(user.getId()).getPassword());
		personelService.save(user);
		modelAndView.addObject("rule", new User());
		modelAndView.addObject("personels", personelService.findAll());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("personel");
		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updatePersonel_Type(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new User());
		modelAndView.addObject("personel", personelService.findUser(id));
		modelAndView.addObject("roles", roleServiceImpl.findAll());
		modelAndView.addObject("mode", "MODE_UPDATE");
		modelAndView.setViewName("personel");
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deletePersonel_Type(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new User());
		personelService.delete(id);
		modelAndView.addObject("personels", personelService.findAll());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("personel");
		return modelAndView;
	}


}







