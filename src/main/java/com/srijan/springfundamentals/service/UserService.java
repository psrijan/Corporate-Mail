package com.srijan.springfundamentals.service;

import com.srijan.springfundamentals.dto.request.user.AddUserRequest;
import com.srijan.springfundamentals.dto.request.user.UpdateUserRequest;
import com.srijan.springfundamentals.dto.response.GenericResponse;
import com.srijan.springfundamentals.dto.response.ApplicationUserDetail;
import com.srijan.springfundamentals.dto.server.ChangeUserStatus;

import java.util.List;

public interface UserService {

    GenericResponse addNewUser(AddUserRequest addUserRequest);

    GenericResponse updateUser(UpdateUserRequest updateUserRequest, Long updateUserId);

    GenericResponse deleteUser(Long deleteId);

    List<ApplicationUserDetail> getAllUsers();

    ApplicationUserDetail getUserById(Long id);

    GenericResponse resetPassword(Long passwordChangeId);

    GenericResponse changeUserStatus(ChangeUserStatus changeUserStatus);
}
