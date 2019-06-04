package com.srijan.springfundamentals.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.srijan.springfundamentals.constants.AppConstants;
import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Rashim Dhaubanjar
 */

@Getter
@Setter
public class SuccessResponse<T> extends ModelBase {

    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private T data;
    @JsonIgnore
    private HttpStatus httpStatus;

    private SuccessResponse(Builder<T> builder) {
        this.message = builder.message;
        this.code = builder.code;
        this.httpStatus = builder.httpStatus;
        this.data = (T) builder.data;
    }

    public static class Builder<T> {

        private String message;
        private String code;
        private T data;

        private HttpStatus httpStatus;

        public Builder(T data) {
            this.code = AppConstants.ApplicationCodes.SUCCESS;
            this.message = "Successfully Fetched Resource";
            this.data = data;
        }

        public Builder(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public Builder<T> setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> setCode(String code) {
            this.code = code;
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

        public SuccessResponse<T> build() {
            return new SuccessResponse<T>(this);
        }

    }
}