package br.com.stoom.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MapValidationErrorService {

    public ResponseEntity<?> MapValidationService(BindingResult result) {
        Map<String, String> mapErrors = Stream.of(result)
                .filter(result1 -> result1.hasErrors())
                .flatMap(r -> r.getFieldErrors().stream())
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        return mapErrors.isEmpty() ? null : new ResponseEntity<Map<String, String>>(mapErrors, HttpStatus.BAD_REQUEST);
    }
}
