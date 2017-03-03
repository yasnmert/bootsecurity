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
import com.mert.service.UserTaskService;

@Controller
@RequestMapping("/myprofile")
public class UserPageController {


	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserTaskService userTaskService;

	@RequestMapping(value = "/inf", method = RequestMethod.GET)
	public ModelAndView showProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new User());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		//POINT=7 http://stackoverflow.com/questions/22364886/neither-bindingresult-nor-plain-target-object-for-bean-available-as-request-attr
		modelAndView.addObject("user", userService.findUser(user.getId()));
		modelAndView.addObject("mode", "MODE_INF");
		modelAndView.setViewName("user_profile");
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveProfile(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		user.setPassword(userService.findUser(user.getId()).getPassword());
		user.setRole(userService.findUser(user.getId()).getRole());
		user.setActive(userService.findUser(user.getId()).getActive());
		userService.save(user);
		modelAndView.addObject("rule", new User());
		modelAndView.addObject("user", userService.findUser(user.getId()));
		modelAndView.addObject("mode", "MODE_INF");
		modelAndView.setViewName("user_profile");
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView updateProfile(@RequestParam int id) {
		System.out.println(id+" hhhhhh");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new User());
		modelAndView.addObject("user", userService.findUser(id));
		modelAndView.addObject("mode", "MODE_EDIT");
		modelAndView.setViewName("user_profile");
		return modelAndView;
	}

	
	@RequestMapping(value = "/görevlerim", method = RequestMethod.GET)
	public ModelAndView updatePersonel_Type(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new User());
		modelAndView.addObject("user", userService.findUser(id));
		modelAndView.addObject("usertasks", userTaskService.findAll());
		modelAndView.addObject("mode", "MODE_TASKS");
		modelAndView.setViewName("user_profile");
		return modelAndView;
	}
	
	
}







