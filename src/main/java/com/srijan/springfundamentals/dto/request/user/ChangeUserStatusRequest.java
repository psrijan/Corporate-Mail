package com.srijan.springfundamentals.dto.request.user;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChangeUserStatusRequest extends ModelBase {
    @NotNull
    private boolean active;
}
