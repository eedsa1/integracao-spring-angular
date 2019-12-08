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
import com.example.model.Student;
import com.example.repository.BookRepository;
import com.example.repository.CourseJpaRepository;
import com.example.repository.StudentRepository;
import com.example.service.CoursesHardcodedService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class StudentResource {

	@Autowired
	private CoursesHardcodedService courseManagementService;
	
	//novo
	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/instructors/{username}/students")
	public List<Student> getAllStudents(@PathVariable String username) {
		return studentRepository.findAll();
	}

	@GetMapping("/instructors/{username}/students/{id}")
	public Student getStudent(@PathVariable String username, @PathVariable long id) {
		return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found with id " + id));
	}

	@DeleteMapping("/instructors/{username}/students/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable String username, @PathVariable long id) {

		studentRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/instructors/{username}/students/{id}")
	public ResponseEntity<Student> updateBook(@PathVariable String name, @PathVariable String email, @PathVariable String registration, 
			@PathVariable String author, @PathVariable long id,
			@RequestBody Student student) {

		student.setName(name);
		student.setEmail(email);
		student.setRegistration(registration);
		
		Student studentUpdated = studentRepository.save(student);

		return new ResponseEntity<Student>(studentUpdated, HttpStatus.OK);
	}

	@PostMapping("/instructors/{username}/students")
	public ResponseEntity<Void> createStudent(@PathVariable String name, @PathVariable String email, @PathVariable String registration,
			@PathVariable String author, @RequestBody Student student) {
		
		student.setName(name);
		student.setEmail(email);
		student.setRegistration(registration);

		Student createdStudent = studentRepository.save(student);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdStudent.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
	
}