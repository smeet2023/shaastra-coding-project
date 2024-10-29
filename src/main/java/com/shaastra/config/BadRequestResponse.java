//package com.shaastra.config;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.ConstraintViolationException;
//
//@RestControllerAdvice
//public class BadRequestResponse {
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<String> handleValidationExceptions(ConstraintViolationException ex) {
//        List<String> errors = ex.getConstraintViolations().stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.toList());
//
////        UpdateApiResponse response = new UpdateApiResponse("Validation failed", errors);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString() + "@@@@@@");
//    }
//}
