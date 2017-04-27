package com.mert.controller;

/**
 * Created by Yasin Mert on 25.02.2017.
 */
import javax.validation.Valid;

import com.mert.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.mert.model.UserTask;
import com.mert.service.TaskService;
import com.mert.service.UserService;
import com.mert.service.UserTaskService;

@Controller
@RequestMapping("/admin/user-task")
public class UserTaskController {


	@Autowired
	private UserService userService;

	@Autowired
	private UserTaskService userTaskService;

	@Autowired
	private TaskService taskService;


	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newUserTask() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user_task", new UserTask());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("tasks", taskService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_NEW");
		modelAndView.setViewName("user_task");
		return modelAndView;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allUserTasks() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new UserTask());
		modelAndView.addObject("user_tasks", userTaskService.findAll());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("tasks", taskService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("user_task");
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUserTask(@Valid UserTask userTask, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/user-task/all");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		userTaskService.save(userTask);
		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateUserTask(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new UserTask());
		modelAndView.addObject("user_task", userTaskService.findUserTask(id));
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("tasks", taskService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_UPDATE");
		modelAndView.setViewName("user_task");
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUserTask(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/user-task/all");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		userTaskService.delete(id);
		return modelAndView;
	}

	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		return user;
	}

}
