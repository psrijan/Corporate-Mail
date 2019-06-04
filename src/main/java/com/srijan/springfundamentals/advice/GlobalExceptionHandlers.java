package com.srijan.springfundamentals.advice;

import com.srijan.springfundamentals.dto.response.ApiError;
import com.srijan.springfundamentals.exception.ResourceAlreadyExistException;
import com.srijan.springfundamentals.exception.UnauthorizedLoginException;
import com.sun.media.sound.InvalidDataException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.NoPermissionException;
import javax.persistence.EntityNotFoundException;
import java.rmi.ServerException;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlers extends ResponseEntityExceptionHandler {

    /**
     * Handle MissingServletRequestParameterException. Triggered when a
     * 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,
                                                                          HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(new ApiError(BAD_REQUEST , ex));
    }

    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is
     * invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers,
                                                                     HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
    }

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when valid
     *                validation fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     * Valid validation.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        log.error("Exception : {}", ex);
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }

    /**
     * Handles javax.validation.ConstraintViolationException. Thrown when
     *
     * @param ex the ConstraintViolationException
     * @return the ApiError object
     * Validated fails.
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
        log.error("Exception : {}", ex);
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(apiError);
    }

    /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more
     * detail than javax.persistence.EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        log.error("Exception : {}", ex);
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * Handles ResourceAlreadyExistException. Created to encapsulate errors with
     * more detail
     *
     * @param ex the ResourceAlreadyExistException
     * @return the ApiError object
     */
    @ExceptionHandler(ResourceAlreadyExistException.class)
    protected ResponseEntity<Object> handleEntityAlreadyExist(ResourceAlreadyExistException ex) {
        log.error("Exception : {}", ex);
        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * Handles UnauthorizedLoginException. Created to encapsulate errors with
     * more detail
     *
     * @param ex the UnauthorizedLoginException
     * @return the ApiError object
     */
    @ExceptionHandler(UnauthorizedLoginException.class)
    protected ResponseEntity<Object> handleUnauthroizedLogin(UnauthorizedLoginException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }


    /**
     * Handles NotacceptedRequestException. Created to encapsulate errors with
     * more detail
     *
     * @param ex the NotacceptedRequestException
     * @return the ApiError object
     */
//    @ExceptionHandler(NotacceptedRequestException.class)
//    protected ResponseEntity<Object> handleNotAccepted(NotacceptedRequestException ex) {
//        ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE);
//        apiError.setMessage(ex.getMessage());
//        return buildResponseEntity(apiError);
//    }


    /**
     * Handles RuntimeException. Created to encapsulate errors with more detail
     *
     * @param ex the RuntimeException
     * @return the ApiError object
     */
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        log.error("Exception : {}", ex);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is
     * malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex      HttpMessageNotWritableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        String error = "Error writing JSON output";
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different
     * DB causes.
     *
     * @param ex      the DataIntegrityViolationException
     * @param request Web Request
     * @return the ApiError object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            log.error("Exception : " + ex.getMessage());
            return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, "Database error", ex.getCause()));
        }
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<Object> handleMultipartException(MaxUploadSizeExceededException ex, WebRequest request) {
        if (ex.getCause() instanceof MaxUploadSizeExceededException) {
            log.error("Exception : " + ex.getMessage());
            return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, "File size exceeds limit", ex.getCause()));
        }
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex      the Exception
     * @param request Web Request
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(),
                ex.getValue(), ex.getRequiredType().getSimpleName()));
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    protected ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
        log.error("Exception : {}", ex);
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }


    /**
     * Handles PasswordExpiredException. Created to encapsulate errors with
     * more detail
     *
     * @param ex the PasswordExpiredException
     * @return the ApiError object
     */
//    @ExceptionHandler(PasswordExpiredException.class)
//    protected ResponseEntity<Object> handlePasswordException(PasswordExpiredException ex) {
//        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
//        apiError.setMessage(ex.getMessage());
//        apiError.setStatusCode("PE");
//        return buildResponseEntity(apiError);
//    }


    /**
     * Handles PasswordExpiredException. Created to encapsulate errors with
     * more detail
     *
     * @param ex the PasswordExpiredException
     * @return the ApiError object
     */
//    @ExceptionHandler(FirstLoginException.class)
//    protected ResponseEntity<Object> handleFirstLoginException(FirstLoginException ex) {
//        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
//        apiError.setMessage(ex.getMessage());
//        apiError.setStatusCode("FL");
//        return buildResponseEntity(apiError);
//    }

    /**
     * Handles Invalid Data. Created to encapsulate errors with
     * more detail
     *
     * @param ex the PasswordExpiredException
     * @return the ApiError object
     */
    @ExceptionHandler(InvalidDataException.class)
    protected ResponseEntity<Object> handleInvalidData(InvalidDataException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }


    @ExceptionHandler(NoPermissionException.class)
    protected ResponseEntity<Object> handleNoPermissionException(NoPermissionException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ServerException.class)
    protected ResponseEntity<Object> handleGeneralServerException(ServerException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }



}
