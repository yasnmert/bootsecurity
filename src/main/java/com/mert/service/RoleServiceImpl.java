package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.Role;
import com.mert.model.Task;
import com.mert.repository.RoleRepository;
@Service("roleService")
public class RoleServiceImpl implements RoleService{

	
	@Autowired
    private RoleRepository roleRepository;
	
	
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public List<Role> findAll(){
		List<Role> roles = new ArrayList<>();
		for(Role role : roleRepository.findAll()){
			roles.add(role);
		}
		return roles;
	}
	
	public Role findRole(int id){
		return roleRepository.findOne(id);
	}
	
	public void save(Role role){
		roleRepository.save(role);
	}
	
	public void delete(int id){
		roleRepository.delete(id);

	}

	@Override
	public Role findRole(String role) {
		// TODO Auto-generated method stub
		return roleRepository.findByRole(role);
	}

}
