package com.srijan.springfundamentals.controller;

import com.srijan.springfundamentals.dto.request.user.AddUserRequest;
import com.srijan.springfundamentals.dto.request.user.ChangeUserStatusRequest;
import com.srijan.springfundamentals.dto.request.user.UpdateUserRequest;
import com.srijan.springfundamentals.dto.response.ApplicationUserDetail;
import com.srijan.springfundamentals.dto.response.GenericResponse;
import com.srijan.springfundamentals.dto.server.ChangeUserStatus;
import com.srijan.springfundamentals.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApplicationUserDetail getUser(@PathVariable("id") Long id) {
        log.info("Entering Get User API...");
        ApplicationUserDetail applicationUserDetails = userService.getUserById(id);
        return applicationUserDetails;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ApplicationUserDetail> getAllUsers() {
        log.info("Entering Get All User API...");
        List<ApplicationUserDetail> applicationUserDetails = userService.getAllUsers();
        return applicationUserDetails;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse addNewUser(@RequestBody @Valid AddUserRequest addUserRequest) {
        log.info("Entering Add new User API ...");
        return userService.addNewUser(addUserRequest);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse modifyUser(@RequestBody @Valid UpdateUserRequest updateUserRequest, @PathVariable("id") Long id) {
        log.info("Entering Modify User API ...");
        return userService.updateUser(updateUserRequest, id);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse deleteUser(@PathVariable("id") Long id) {
        log.info("Entering Delete User API ...");
        return userService.deleteUser(id);
    }

    @PutMapping(path = "/password-reset/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse changePassword(@PathVariable("id") Long id) {
        log.info("Entering Change Password API...");
        return userService.resetPassword(id);
    }

    @PutMapping(path = "/status/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse changeStatus(@PathVariable("id") Long id, @RequestBody @Valid ChangeUserStatusRequest changeUserStatusRequest) {
        log.info("Entering Change User Status Request API...");
        ChangeUserStatus changeUserStatus = new ChangeUserStatus();
        changeUserStatus.setId(id);
        changeUserStatus.setActive(changeUserStatusRequest.isActive());
        return userService.changeUserStatus(changeUserStatus);
    }
}
