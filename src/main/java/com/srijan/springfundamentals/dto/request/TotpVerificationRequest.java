package com.srijan.springfundamentals.dto.request;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class TotpVerificationRequest extends ModelBase {
    @NotEmpty
    private String code;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
