package com.example.controller;

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
import com.example.service.BookService;
import com.example.service.ModuleService;

@RestController
@RequestMapping("/books")
public class BookRestController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ModuleService moduleService;
	
	@GetMapping(value="/books/{id}")
	public ResponseEntity<?> show(Model model, @PathVariable("id") Long id) {
		try {
			Book book = null;
			book = bookService.findOne(id).get();
			return new ResponseEntity(book, HttpStatus.OK); 
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("Livro n�o encontrado!");
		}
	}
	
	@GetMapping(value="/books-all")
	public ResponseEntity<?> getBooks(Pageable pageable) {
		Page<Book> page = bookService.findAll(pageable);
		
		if(page.getTotalElements()==0) {
			throw new ResourceNotFoundException("N�o h� livros cadastrados!");
		}
		return new ResponseEntity(page, HttpStatus.OK); 
	}
	
	@DeleteMapping(value="/books/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		try {
			bookService.deleteById(id);
			return new ResponseEntity(HttpStatus.OK); 
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("N�o foi poss�vel remover o livro!");
		}
	}
	
	@PostMapping(value="/books/{id}")
	public ResponseEntity<?> create(@Valid @RequestBody Book entityBook){
		try {
			Book book = null;
			book = bookService.save(entityBook);
			return new ResponseEntity(book, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("Erro ao cadastrar livro! Reinicie o procedimento!");
		}	
	}

}
