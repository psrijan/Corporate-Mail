package com.srijan.springfundamentals.builder;

import com.srijan.springfundamentals.dto.response.GenericResponse;

public class ResponseBuilder {

    public static GenericResponse buildSuccessResponse(String message) {
        return new GenericResponse.Builder(true, message).build();

    }
}
