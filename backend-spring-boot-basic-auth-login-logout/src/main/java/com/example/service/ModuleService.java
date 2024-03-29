package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.model.Book;
import com.example.model.Module;
import com.example.repository.ModuleRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ModuleService {

	@Autowired
	private ModuleRepository moduleRepository;

	public List<Module> findAll() {
		return moduleRepository.findAll();
	}
	
	public Page<Module> findAll(Pageable pageable) {
		return moduleRepository.findAll(pageable);
	}

	public Optional<Module> findOne(Long id) {
		return moduleRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Module save(Module entity) {
		return moduleRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Module entity) {
		moduleRepository.delete(entity);
	}
	
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		moduleRepository.deleteById(id);;
	}

}
	
