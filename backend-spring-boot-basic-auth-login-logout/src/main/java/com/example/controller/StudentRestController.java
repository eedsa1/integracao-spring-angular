package com.example.controller;

import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import com.example.model.Student;
import com.example.service.ModuleService;
import com.example.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentRestController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ModuleService moduleService;

	@GetMapping(value="/students/{id}")
	public ResponseEntity<?> show(Model model, @PathVariable("id") Long id) {
		try {
			Student student = null;
			student = studentService.findOne(id).get();
			return new ResponseEntity(student, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("Estudante n�o encontrado!");
		}		
	}
	
	@GetMapping(value="/students-all")
	public ResponseEntity<?> getStudents(Pageable pageable) {
		Page<Student> page = studentService.findAll(pageable);
		
		if(page.getTotalElements()==0) {
			throw new ResourceNotFoundException("N�o h� estudantes cadastrados!");
		}
		return new ResponseEntity(page, HttpStatus.OK); 
	}
	
	@DeleteMapping(value="/students/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		try {
			studentService.deleteById(id);
			return new ResponseEntity(HttpStatus.OK); 
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("N�o foi poss�vel remover o estudante!");
		}
	}
	
	@PostMapping(value="/students/{id}")
	public ResponseEntity<?> create(@Valid @RequestBody Student entityStudent){
		try {
			Student student = null;
			student = studentService.save(entityStudent);
			return new ResponseEntity(student, HttpStatus.OK);
		}
		catch (Exception e) {
			throw new ResourceNotFoundException("Erro ao cadastrar estudante! Reinicie o procedimento!");
		}
	}

}
