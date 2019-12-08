package com.example.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Book;
import com.example.model.Module;
import com.example.service.ModuleService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
//@RequestMapping("/modules")
public class ModuleRestController {
	
	@Autowired
	private ModuleService moduleService;
	
	@GetMapping(value="/modules/{username}/modules/{id}")
	public ResponseEntity<?> show(Model model, @PathVariable("username") String username, @PathVariable("id") Long id) {
		try {
			Module module = null;
			module = moduleService.findOne(id).get();
			return new ResponseEntity(module, HttpStatus.OK); 
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("M�dulo n�o encontrado!");
		}	
	}
	
	@GetMapping("/modules-all/{username}/modules")
	public ResponseEntity<?> getAllModules(Pageable pageable, @PathVariable("username") String username) {
		Page<Module> page = moduleService.findAll(pageable);
		
		if(page.getTotalElements()==0) {
			throw new ResourceNotFoundException("N�o h� m�dulos cadastrados!");
		}
		return new ResponseEntity(page, HttpStatus.OK); 
	}
	
	@DeleteMapping(value="/modules/{username}/modules/{id}")
	public ResponseEntity<?> delete(@PathVariable("username") String username, @PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		try {
			moduleService.deleteById(id);
			return new ResponseEntity(HttpStatus.OK); 
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("N�o foi poss�vel remover o m�dulo!");
		}
	}
	
	@PostMapping(value="/modules/{username}/modules/{id}")
	public ResponseEntity<?> create(@PathVariable("username") String username, @PathVariable("id") Integer id, @Valid @RequestBody Module entityModule){
		try {
			Module module = null;
			module = moduleService.save(entityModule);
			return new ResponseEntity(module, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("Erro ao cadastrar m�dulo! Reinicie o procedimento!");
		}	
	}
	
}
