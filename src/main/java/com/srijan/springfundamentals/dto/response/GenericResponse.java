package com.srijan.springfundamentals.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Rashim Dhaubanjar
 */

@Getter
@Setter
public class GenericResponse<T> extends ModelBase {

    private static final long serialVersionUID = 1L;
    private boolean success;
    private String message;
    private String code;
    private String developerMessage;
    private String link;
    private T data;
    @JsonIgnore
    private HttpStatus httpStatus;

    private GenericResponse(Builder<T> builder) {
        this.success = builder.status;
        this.message = builder.message;
        this.code = builder.code;
        this.developerMessage = builder.developerMessage;
        this.link = builder.link;
        this.httpStatus = builder.httpStatus;
        this.data = (T) builder.data;
    }

    public static class Builder<T> {

        private boolean status;
        private String message;

        private String code;
        private String developerMessage;
        private String link;
        private T data;

        private HttpStatus httpStatus;

        public Builder(boolean status, String message) {
            this.status = status;
            this.message = message;
        }

        public Builder<T> setStatus(boolean status) {
            this.status = status;
            return this;
        }

        public Builder<T> setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder<T> setDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder<T> setLink(String link) {
            this.link = link;
            return this;
        }

        public Builder<T> setHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder<T> setData(T t) {
            this.data = t;
            return this;
        }

        public GenericResponse<T> build() {
            return new GenericResponse<T>(this);
        }
    }
}