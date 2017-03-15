package com.mert.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.UserTask;
import com.mert.service.TaskService;
import com.mert.service.UserServiceImpl;
import com.mert.service.UserTaskService;

@Controller
@RequestMapping("/admin/personel-task")
public class UserTaskController {


	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private UserTaskService userTaskService;

	@Autowired
	private TaskService taskService;


	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newUserTask() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user_task", new UserTask());
		modelAndView.addObject("users", userServiceImpl.findAll());
		modelAndView.addObject("tasks", taskService.findAll());
		modelAndView.addObject("mode", "MODE_NEW");
		modelAndView.setViewName("user_task");
		return modelAndView;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allUserTasks() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new UserTask());
		// POINT=7
		// http://stackoverflow.com/questions/22364886/neither-bindingresult-nor-plain-target-object-for-bean-available-as-request-attr
		modelAndView.addObject("user_tasks", userTaskService.findAll());
		modelAndView.addObject("users", userServiceImpl.findAll());
		modelAndView.addObject("tasks", taskService.findAll());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("user_task");
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUserTask(@Valid UserTask userTask, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/personel-task/all");
		userTaskService.save(userTask);
		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateUserTask(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new UserTask());
		modelAndView.addObject("user_task", userTaskService.findUserTask(id));
		modelAndView.addObject("users", userServiceImpl.findAll());
		modelAndView.addObject("tasks", taskService.findAll());
		modelAndView.addObject("mode", "MODE_UPDATE");
		modelAndView.setViewName("user_task");
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUserTask(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/personel-task/all");
		userTaskService.delete(id);
		return modelAndView;
	}

}
