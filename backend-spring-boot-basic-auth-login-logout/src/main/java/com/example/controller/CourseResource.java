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
import com.example.model.Course;
import com.example.repository.CourseJpaRepository;
import com.example.service.CoursesHardcodedService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class CourseResource {

	@Autowired
	private CoursesHardcodedService courseManagementService;
	
	//novo
	@Autowired
	private CourseJpaRepository courseRepository;

//	@GetMapping("/instructors/{username}/courses")
//	public List<Course> getAllCourses(@PathVariable String username) {
//		return courseManagementService.findAll();
//	}
//	
//	@GetMapping("/instructors/{username}/courses/{id}")
//	public Course getCourse(@PathVariable String username, @PathVariable long id) {
//		return courseManagementService.findById(id);
//	}
//
//	@DeleteMapping("/instructors/{username}/courses/{id}")
//	public ResponseEntity<Void> deleteCourse(@PathVariable String username, @PathVariable long id) {
//
//		Course course = courseManagementService.deleteById(id);
//
//		if (course != null) {
//			return ResponseEntity.noContent().build();
//		}
//
//		return ResponseEntity.notFound().build();
//	}
//
//	@PutMapping("/instructors/{username}/courses/{id}")
//	public ResponseEntity<Course> updateCourse(@PathVariable String username, @PathVariable long id,
//			@RequestBody Course course) {
//
//		Course courseUpdated = courseManagementService.save(course);
//
//		return new ResponseEntity<Course>(course, HttpStatus.OK);
//	}
//
//	@PostMapping("/instructors/{username}/courses")
//	public ResponseEntity<Void> createCourse(@PathVariable String username, @RequestBody Course course) {
//
//		Course createdCourse = courseManagementService.save(course);
//
//		// Location
//		// Get current resource url
//		/// {id}
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCourse.getId())
//				.toUri();
//
//		return ResponseEntity.created(uri).build();
//	}
	
	@GetMapping("/instructors/{username}/courses")
	public List<Course> getAllCourses(@PathVariable String username) {
		return courseRepository.findAll();
	}

	@GetMapping("/instructors/{username}/courses/{id}")
	public Course getCourse(@PathVariable String username, @PathVariable long id) {
		return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course Not Found with id " + id));
	}

	@DeleteMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable String username, @PathVariable long id) {

		courseRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable String username, @PathVariable long id,
			@RequestBody Course course) {

		course.setUsername(username);
		
		Course courseUpdated = courseRepository.save(course);

		return new ResponseEntity<Course>(courseUpdated, HttpStatus.OK);
	}

	@PostMapping("/instructors/{username}/courses")
	public ResponseEntity<Void> createCourse(@PathVariable String username, @RequestBody Course course) {
		
		course.setUsername(username);

		Course createdCourse = courseRepository.save(course);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCourse.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
	
}