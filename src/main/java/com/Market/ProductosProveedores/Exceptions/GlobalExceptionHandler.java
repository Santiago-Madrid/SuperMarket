package com.Market.ProductosProveedores.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Market.ProductosProveedores.Dto.ExceptionDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDto> handleBadRequest(BadRequestException ex) {
        System.out.println("ENTRÓ AL HANDLER");
        ExceptionDto error = new ExceptionDto();
        error.setMessage(ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleValidationErrors(MethodArgumentNotValidException ex) {
        ExceptionDto error = new ExceptionDto();
        String mensaje = ex.getBindingResult().getFieldError().getDefaultMessage();
        error.setMessage(mensaje);

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
