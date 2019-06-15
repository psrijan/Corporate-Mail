package com.srijan.springfundamentals.service.impl;


import com.srijan.springfundamentals.dto.request.user.AddUserRequest;
import com.srijan.springfundamentals.dto.request.user.UpdateUserRequest;
import com.srijan.springfundamentals.dto.response.GenericResponse;
import com.srijan.springfundamentals.dto.response.ApplicationUserDetail;
import com.srijan.springfundamentals.dto.server.ChangeUserStatus;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.mapper.UserMapper;
import com.srijan.springfundamentals.provider.UserSessionFactory;
import com.srijan.springfundamentals.repository.UserRepository;
import com.srijan.springfundamentals.service.UserService;
import com.srijan.springfundamentals.util.PasswordUtil;
import com.srijan.springfundamentals.validator.ApplicationUserValidator;
import com.srijan.springfundamentals.validator.UserValidationDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationUserValidator applicationUserValidator;

    @Autowired
    private UserSessionFactory userSessionFactory;


    @Override
    public List<ApplicationUserDetail> getAllUsers() {
        List<ApplicationUser> applicationUsers = userRepository.findApplicationUserList();
        if (applicationUsers == null)
            return null;
        return applicationUsers.stream().map(applicationUser -> UserMapper.mapToUserDetail(applicationUser))
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationUserDetail getUserById(Long id) {
        ApplicationUser applicationUser = userRepository.findApplicationUserById(id).get();
        return UserMapper.mapToUserDetail(applicationUser);
    }

    @Transactional
    @Override
    public GenericResponse addNewUser(AddUserRequest addUserRequest) {
//        applicationUserValidator.validateNewUser(addUserRequest, userSessionFactory.getAuthenticatedUser());
        String password = PasswordUtil.getRandomPassword();
        ApplicationUser user = UserMapper.mapToNewUser(addUserRequest, passwordEncoder.encode(password));
        userRepository.save(user);
        return new GenericResponse.Builder(true, "Successfully created Application User").build();
    }


    @Override
    public GenericResponse updateUser(UpdateUserRequest updateUserRequest, Long updateUserId) {
        Optional<ApplicationUser> optApplicationUser = userRepository.findApplicationUserById(updateUserId);
        ApplicationUser updateUser = optApplicationUser.orElseThrow(() -> new EntityNotFoundException("Cant find user with id " + updateUserId));
        UserValidationDetail userValidationDetail = UserValidationDetail.builder()
                .loggedInUser(userSessionFactory.getAuthenticatedUser())
                .updateUserRequest(updateUserRequest)
                .updateUser(userRepository.findApplicationUserById(updateUserId).get())
                .build();
//        applicationUserValidator.validateModifyUser(userValidationDetail);
        UserMapper.mapToExistingUser(updateUser, updateUserRequest);
        userRepository.save(updateUser);
        return new GenericResponse.Builder(true, "Successfully updated application user ").build();
    }

    @Override
    public GenericResponse deleteUser(Long deleteId) {
        ApplicationUser loginUser = userSessionFactory.getAuthenticatedUser();
        Optional<ApplicationUser> optionalApplicationUser = userRepository.findApplicationUserById(deleteId);
        ApplicationUser applicationUser = optionalApplicationUser.orElseThrow(() -> new EntityNotFoundException("Can't find application user with id" + deleteId));
//        applicationUserValidator.validateUserDelete(loginUser, applicationUser);
        applicationUser.setActive('D');
        userRepository.save(applicationUser);
        return new GenericResponse.Builder(true, "Successfully Deleted application user with id " + deleteId).build();
    }

    @Override
    public GenericResponse resetPassword(Long passwordChangeId) {
        Optional<ApplicationUser> optionalApplicationUser = userRepository.findApplicationUserById(passwordChangeId);
        ApplicationUser loggedInUser = userSessionFactory.getAuthenticatedUser();
        ApplicationUser changePasswordUser = optionalApplicationUser.orElseThrow(() -> new EntityNotFoundException("Can't find application user with id" + passwordChangeId));
//        applicationUserValidator.validateNewPassword(changePasswordUser, loggedInUser);
        String password = PasswordUtil.getRandomPassword();
        changePasswordUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(changePasswordUser);
//        sendEmail(password, changePasswordUser);
        return new GenericResponse.Builder<>(true, "Successfully Reset Password").build();
    }

    @Override
    public GenericResponse changeUserStatus(ChangeUserStatus changeUserStatus) {
        ApplicationUser loginUser = userSessionFactory.getAuthenticatedUser();
        Optional<ApplicationUser> optionalApplicationUser = userRepository.findApplicationUserById(changeUserStatus.getId());
        ApplicationUser applicationUser = optionalApplicationUser.get();
        applicationUser.setActive(changeUserStatus.isActive() ? 'Y' : 'N');
        userRepository.save(applicationUser);
        return new GenericResponse.Builder(true, "Successfully Changed User Status").build();
    }

}
