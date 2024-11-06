package com.shaastra.controllers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaastra.entities.Students;
import com.shaastra.exceptions.ApplicationException;
import com.shaastra.exceptions.ResourceNotFoundException;
import com.shaastra.repositories.StudentRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/students")
@Tag(name = "StudentController", description = "API documentation for Students")


public class StudentController 
{
    private final StudentRepository studentRepository;
//    private LocalValidatorFactoryBean validator; // Inject Validator

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
//        this.validator = validator;
    }

    @PostMapping
    public ResponseEntity<Students> createStudent(@RequestBody Students student) 
    {
        Students savedStudent = studentRepository.save(student);
        logger.info("Handling post request");
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping("/{erpId}")
    public ResponseEntity<Students> getStudentById(@PathVariable long erpId) 
    {
        Students student = studentRepository.findById(erpId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + erpId));
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Students>> getAllStudents() 
    {
    	try {
    		List<Students> students = studentRepository.findAll();
    		return new ResponseEntity<>(students, HttpStatus.OK);
		} 
    	catch (Exception e) 
    	{
    		throw new ApplicationException("Failed to retrieve Students!", e);
		}
    }

    @PutMapping("/{erpId}")
    public ResponseEntity<Students> updateStudent(@PathVariable long erpId, @RequestBody Students studentDetails) {
        Students student = studentRepository.findById(erpId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + erpId));

        Students updatedStudent = studentRepository.save(student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }
    
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Students> updateStudentsByFields(
            @PathVariable long id,
            @RequestBody Map<String, Object> updates) {

        Students existingStudent = studentRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Student with the id : " + id + " could not be found !"));

            updates.forEach((key, value) -> 
            {
                Field field = ReflectionUtils.findField(Students.class, key);

                if (field != null) 
                {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, existingStudent, value);

                    // Manually validate the specific field being updated
//                    Set<ConstraintViolation<Students>> violations = validator.validateProperty(toUpdateStudent, key);
//                    if (!violations.isEmpty()) {
//                        throw new ConstraintViolationException(violations);
//                    }
                    System.out.println("Field updated successfully: " + key);
                }
            });

            studentRepository.saveAndFlush(existingStudent);
            return ResponseEntity.ok(existingStudent);
    }

    
    @DeleteMapping("/{erpId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long erpId) {
        studentRepository.findById(erpId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + erpId));

        studentRepository.deleteById(erpId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<String> handleFieldValidationExceptions(ConstraintViolationException ex) {
//        List<String> errors = ex.getConstraintViolations().stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.toList());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
//    }
}
