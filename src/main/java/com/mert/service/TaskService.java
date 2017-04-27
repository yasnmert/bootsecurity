package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mert.repository.TaskRepository;
import com.mert.model.Task;

@Service
@Transactional
public class TaskService {

	private final TaskRepository taskRepository;
	

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public List<Task> findAll(){
		List<Task> tasks = new ArrayList<>();
		tasks = taskRepository.findAll();
		return tasks;
	}
	
	public Task findTask(int id){
		return taskRepository.findOne(id);
	}
	
	public void save(Task task){
		taskRepository.save(task);
	}
	
	public void delete(int id){
		taskRepository.delete(id);

	}
}
