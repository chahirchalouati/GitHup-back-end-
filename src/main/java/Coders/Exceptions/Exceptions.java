/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Exceptions;

import Coders.DTO.ApiResponse;
import java.util.Date;
import javax.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 *
 * @author Chahir Chalouati
 */
@ControllerAdvice
public class Exceptions {

    // @ExceptionHandler(InternalAuthenticationServiceException.class)
    // private ResponseEntity
    // authenticationServiceException(InternalAuthenticationServiceException e) {
    // return new ResponseEntity<>(new ApiResponse("Bad Credentials ",
    // HttpStatus.BAD_REQUEST, new Date()), HttpStatus.BAD_REQUEST);
    //
    // }
    @ExceptionHandler(PostPersistException.class)
    private ResponseEntity<?> postPersistException(PostPersistException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST, new Date()),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private ResponseEntity<?> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST, new Date()),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(__RequestRejectedException.class)
    private ResponseEntity<?> RequestRejectedException(__RequestRejectedException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST, new Date()),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MultipartException.class)
    private ResponseEntity<?> multipartException(MultipartException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST, new Date()),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    private ResponseEntity<?> missingServletRequestPartException(MissingServletRequestPartException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST, new Date()),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<?> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        return new ResponseEntity<>(new ApiResponse("Missing Request Parameter :" + e.getParameterName(),
                HttpStatus.BAD_REQUEST, new Date()), HttpStatus.BAD_REQUEST);

    }
    //
    // @ExceptionHandler(UsernameNotFoundException.class)
    // private ResponseEntity usernameNotFoundException(UsernameNotFoundException e)
    // {
    // return new ResponseEntity<>(new ApiResponse("User Not Found ",
    // HttpStatus.NOT_FOUND, new Date()), HttpStatus.NOT_FOUND);
    //
    // }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, new Date()),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    private ResponseEntity<?> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST, new Date()),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), HttpStatus.UNAUTHORIZED, new Date()),
                HttpStatus.UNAUTHORIZED);

    }

    // @ExceptionHandler(BadCredentialsException.class)
    // private ResponseEntity BadCredentialsException(BadCredentialsException e) {
    // return new ResponseEntity<>(new ApiResponse(e.getMessage(),
    // HttpStatus.UNAUTHORIZED, new Date()), HttpStatus.UNAUTHORIZED);
    //
    // }
    @ExceptionHandler({UserExistException.class})
    private ResponseEntity<?> userExistException(UserExistException exception) {
        return new ResponseEntity<>(new ApiResponse(exception.getMessage(), HttpStatus.CONFLICT, new Date()),
                HttpStatus.CONFLICT);

    }

    @ExceptionHandler({EntityNotFoundException.class})
    private ResponseEntity<?> entityNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>(new ApiResponse(exception.getMessage(), HttpStatus.NOT_FOUND, new Date()),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({JwtExpiredTokenException.class})
    private ResponseEntity<?> JwtExpiredTokenException(JwtExpiredTokenException exception) {
        return new ResponseEntity<>(new ApiResponse(exception.getMessage(), HttpStatus.NOT_FOUND, new Date()),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({StorageException.class})
    private ResponseEntity<?> storageException(StorageException exception) {
        return new ResponseEntity<>(
                new ApiResponse(exception.getMessage(), HttpStatus.NOT_FOUND, new Date()),
                HttpStatus.NOT_FOUND);

    }
}
