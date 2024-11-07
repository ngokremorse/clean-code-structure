package com.vpbs.bpm.qtrr01.external.api.exception;

import com.vpbs.bpm.qtrr01.core.domain.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class ApiExceptionHandler {


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleAllException(Exception ex, WebRequest request) {
        log.error(ex);
        return (new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Có lỗi xảy ra", ex.getLocalizedMessage())).getError();
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<?> handleMethodArgumentNotValidException(ResponseStatusException ex, WebRequest request) {
        log.error(ex);
        return ResponseEntity.status(ex.getBody().getStatus()).body((new ApiException(ex.getBody().getStatus(), ex.getBody().getTitle(), ex.getLocalizedMessage())).getError());
    }

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<?> handleApiException(ApiException ex, WebRequest request) {
        log.error(ex);
        return ResponseEntity.status(ex.getError().getStatus()).body(ex.getError());
    }
}
