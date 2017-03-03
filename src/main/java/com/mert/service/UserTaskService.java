package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mert.repository.UserTaskRepository;
import com.mert.model.UserTask;

@Service
@Transactional
public class UserTaskService {

	private final UserTaskRepository userTaskRepository;

	public UserTaskService(UserTaskRepository userTaskRepository) {
		this.userTaskRepository = userTaskRepository;
	}
	
	public List<UserTask> findAll(){
		List<UserTask> user_tasks = new ArrayList<>();
		for(UserTask user_task : userTaskRepository.findAll()){
			user_tasks.add(user_task);
		}
		return user_tasks;
	}
	
	public UserTask findUserTask(int id){
		return userTaskRepository.findOne(id);
	}
	
	public void save(UserTask user_task){
		userTaskRepository.save(user_task);
	}
	
	public void delete(int id){
		userTaskRepository.delete(id);

	}
}
