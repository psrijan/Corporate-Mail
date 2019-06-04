package com.srijan.springfundamentals.validator;

import com.srijan.springfundamentals.dto.ModelBase;
import com.srijan.springfundamentals.dto.request.user.UpdateUserRequest;
import com.srijan.springfundamentals.entities.ApplicationUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserValidationDetail extends ModelBase {
    private UpdateUserRequest updateUserRequest;
    private ApplicationUser loggedInUser;
    private ApplicationUser updateUser;
}
