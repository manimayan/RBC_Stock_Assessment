package com.rbc.stock.exception;

import com.rbc.stock.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            RecordAlreadyExistsException.class,
            NoRecordExistException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
            RBCStockException.class

    })
    public ResponseEntity<ServiceResponse> handleException(Exception ex) {
        if (ex instanceof RecordAlreadyExistsException) {
            return handleRecordAlreadyExistsException((RecordAlreadyExistsException) ex);
        } else if (ex instanceof NoRecordExistException) {
            return handleNoRecordExistException((NoRecordExistException) ex);
        } else if (ex instanceof MethodArgumentNotValidException) {
            return handleValidationException((MethodArgumentNotValidException) ex);
        } else if (ex instanceof HttpMessageNotReadableException) {
            return handleHttpMessageNotReadableException((HttpMessageNotReadableException) ex);
        } else if (ex instanceof RBCStockException) {
            return handleRBCStockException((RBCStockException) ex);
        }

        ServiceResponse genericServiceResponse = ServiceResponse.builder()
                .message("Unknown exception occurred")
                .build();
        return new ResponseEntity<>(genericServiceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ServiceResponse> handleRecordAlreadyExistsException(RecordAlreadyExistsException ex) {
        ServiceResponse serviceResponse = ServiceResponse.builder()
                .rbcServiceCode(Constants.CANNOT_ADD_RECORD_BECAUSE_IT_ALREADY_EXISTS_CD)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    private ResponseEntity<ServiceResponse> handleNoRecordExistException(NoRecordExistException ex) {
        ServiceResponse serviceResponse = ServiceResponse.builder()
                .rbcServiceCode(Constants.NO_RECORD_EXISTS_CD)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    private ResponseEntity<ServiceResponse> handleValidationException(MethodArgumentNotValidException ex) {
        ServiceResponse serviceResponse = ServiceResponse.builder()
                .message(ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(",")))
                .build();
        return new ResponseEntity<>(serviceResponse, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ServiceResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ServiceResponse serviceResponse = ServiceResponse.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(serviceResponse, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ServiceResponse> handleRBCStockException(RBCStockException ex) {
        ServiceResponse serviceResponse = ServiceResponse.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(serviceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
