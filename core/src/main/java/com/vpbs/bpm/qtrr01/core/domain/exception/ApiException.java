package com.vpbs.bpm.qtrr01.core.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Data
public class ApiException extends Exception {
    private Error error;

    public ApiException() {
    }

    public ApiException(HttpStatus status, String title, String detail) {
        super(detail);
        error = new Error(status.value(), title, detail, new Timestamp(System.currentTimeMillis()));
    }

    public ApiException(int status, String title, String detail) {
        super(detail);
        this.error = new Error(status, title, detail, new Timestamp(System.currentTimeMillis()));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error {
        private int status;
        private String title;
        private String detail;
        private Timestamp timestamp;
    }
}
