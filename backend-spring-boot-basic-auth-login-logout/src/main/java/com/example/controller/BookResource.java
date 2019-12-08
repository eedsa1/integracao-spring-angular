package com.example.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.model.Book;
import com.example.model.Course;
import com.example.model.Module;
import com.example.repository.BookRepository;
import com.example.repository.CourseJpaRepository;
import com.example.service.CoursesHardcodedService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class BookResource {

	@Autowired
	private CoursesHardcodedService courseManagementService;
	
	//novo
	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/instructors/{username}/books")
	public List<Book> getAllModules(@PathVariable String username) {
		return bookRepository.findAll();
	}

	@GetMapping("/instructors/{username}/books/{id}")
	public Book getBook(@PathVariable String username, @PathVariable long id) {
		return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Module Not Found with id " + id));
	}

	@DeleteMapping("/instructors/{username}/books/{id}")
	public ResponseEntity<Void> deleteModule(@PathVariable String username, @PathVariable long id) {

		bookRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/instructors/{username}/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable String title, @PathVariable String author, @PathVariable long id,
			@RequestBody Book book) {

		book.setTitle(title);
		book.setAuthor(author);
		
		Book bookUpdated = bookRepository.save(book);

		return new ResponseEntity<Book>(bookUpdated, HttpStatus.OK);
	}

	@PostMapping("/instructors/{username}/books")
	public ResponseEntity<Void> createModule(@PathVariable String title, @PathVariable String author, @RequestBody Book book) {
		
		book.setTitle(title);
		book.setAuthor(author);

		Book createdModule = bookRepository.save(book);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdModule.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
	
}