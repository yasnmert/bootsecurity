package com.mert.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mert.service.TaskService;
import com.mert.service.UserTaskService;
import com.mert.model.Task;

@Controller
@RequestMapping("/admin/tasks")
public class TaskController {


	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserTaskService userTaskService;

	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newTask(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("task", new Task());
		modelAndView.addObject("tasks", taskService.findAll());
		modelAndView.addObject("mode", "MODE_NEW");
		modelAndView.setViewName("task");
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveTask(@Valid Task task, BindingResult bindingResult) {
		task.setDateCreated(new Date());
		taskService.save(task);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/tasks/all");
		return modelAndView;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allTasks() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new Task());
		//POINT=7 http://stackoverflow.com/questions/22364886/neither-bindingresult-nor-plain-target-object-for-bean-available-as-request-attr
		modelAndView.addObject("tasks", taskService.findAll());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("task");
		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateTask(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new Task());
		modelAndView.addObject("task", taskService.findTask(id));
		modelAndView.addObject("mode", "MODE_UPDATE");
		modelAndView.setViewName("task");
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteTask(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/tasks/all");
		modelAndView.addObject("rule", new Task());
		taskService.delete(id);
		return modelAndView;
	}

	@RequestMapping(value = "/per_inf", method = RequestMethod.GET)
	public ModelAndView infTask(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new Task());
		modelAndView.addObject("task", taskService.findTask(id));
		modelAndView.addObject("usertasks", userTaskService.findAll());
		modelAndView.addObject("mode", "MODE_INF");
		modelAndView.setViewName("task");
		return modelAndView;
	}
}
