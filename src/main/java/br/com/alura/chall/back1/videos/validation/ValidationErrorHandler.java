package br.com.alura.chall.back1.videos.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {
    
    // Invalid Post Handler @NotBlank
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<CategoriaFormErrorDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        
        List<CategoriaFormErrorDto> errors = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            CategoriaFormErrorDto categoriaFormErrorDto = new CategoriaFormErrorDto(e.getField(), e.getDefaultMessage());
            errors.add(categoriaFormErrorDto);
        });

        return errors;
    }

    // Invalid Post Handler @Size @Length
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<CategoriaFormErrorDto> constraintViolationExceptionHandler(ConstraintViolationException exception){

        List<CategoriaFormErrorDto> errors = new ArrayList<>();

        exception.getConstraintViolations().iterator().forEachRemaining(e -> {
            CategoriaFormErrorDto categoriaFormErrorDto = new CategoriaFormErrorDto(e.getPropertyPath().toString(), e.getMessage());
            errors.add(categoriaFormErrorDto);
        });

        return errors;

    }

}
