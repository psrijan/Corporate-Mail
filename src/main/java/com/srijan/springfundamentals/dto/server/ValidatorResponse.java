package com.srijan.springfundamentals.dto.server;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidatorResponse extends ModelBase {
    private boolean success;
    private String message;
}
