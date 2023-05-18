package com.wishlist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Interceptor to catch and handle response errors.
 */
@RestControllerAdvice
public final class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles {@link NotFoundException} exceptions.
     * @param exc Original exception.
     * @return Proper response.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFound(final NotFoundException exc) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ProblemDetail.forStatusAndDetail(
                    HttpStatus.NOT_FOUND,
                    "%s not found.".formatted(exc.entity().readable())
                )
            );
    }

    /**
     * Handles {@link BadRequestException} exceptions.
     * @param exc Original exception.
     * @return Proper response.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequest(final BadRequestException exc) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ProblemDetail.forStatusAndDetail(
                    HttpStatus.BAD_REQUEST, exc.getMessage()
                )
            );
    }

}
