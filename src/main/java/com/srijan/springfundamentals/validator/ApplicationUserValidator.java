package com.srijan.springfundamentals.validator;

import com.srijan.springfundamentals.dto.request.user.AddUserRequest;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.exception.LimitedPrivilegeException;
import com.srijan.springfundamentals.exception.ResourceAlreadyExistException;
import com.srijan.springfundamentals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class ApplicationUserValidator {

//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserRepository userRepository;
//
////    @Autowired
////    UserGroupRepository userGroupRepository;
//
//    public void validateNewPassword(ApplicationUser changePasswordUser , ApplicationUser loginUser) {
//
//        if(!isGroupPermissionsValid(loginUser.getUserGroup() , changePasswordUser.getUserGroup())) {
//            throw new LimitedPrivilegeException("Logged In user can't update a user with higher permission than it's own");
//        }
//
//    }
//
//    public void validateUserDelete(ApplicationUser loginUser, ApplicationUser deleteUser) {
//        if (loginUser.getId().equals(deleteUser.getId())) {
//            throw new DataIntegrityViolationException("You can't Delete your own username");
//        }
//
//        if(!isGroupPermissionsValid(loginUser.getUserGroup() , deleteUser.getUserGroup())) {
//            throw new LimitedPrivilegeException("Logged In user can't update a user with higher permission than it's own");
//        }
//    }
//
//    public void validateNewUser(AddUserRequest addUserRequest, ApplicationUser loggedInUser) {
//        Optional<ApplicationUser> sameUsernameUser = userRepository.findApplicationUserByUsername(addUserRequest.getUsername());
//        if (sameUsernameUser.isPresent()) {
//            throw new ResourceAlreadyExistException("There is already a user with the following username: " + addUserRequest.getUsername());
//        }
//        UserGroup userGroup = userGroupRepository.findById(addUserRequest.getGroupId()).get();
//
//        if (loggedInUser.getUserGroup().getCode().equalsIgnoreCase(GroupConstants.SUPER_ADMIN))
//            return;
//
//        UserGroup mainGroup = loggedInUser.getUserGroup();
//        if(!isGroupPermissionsValid(mainGroup , userGroup))
//            throw new LimitedPrivilegeException("You are not allowed to create user with the following permission");
//
//        //super admin | user group of same type | child of logged in user , user group
////        if (!(loggedInUser.getUserGroup().getCode().equalsIgnoreCase(GroupConstants.SUPER_ADMIN) ||
////                loggedInUser.getUserGroup().getCode().equalsIgnoreCase(userGroup.getCode()) ||
////                (userGroup.getParent() != null && userGroup.getParent().getId().equals(loggedInUser.getUserGroup().getId())))) {
////            throw new LimitedPrivilegeException("You are not allowed to create user with the following permission");
////        }
//    }
//
//    public boolean isGroupPermissionsValid(UserGroup mainGroup, UserGroup compareGroup) {
//        Map<Long, UserMenu> availableMenus = mainGroup.getPermissions().stream().collect(Collectors.toMap(o -> o.getUserMenu().getId(), o -> o.getUserMenu()));
//        long count = compareGroup.getPermissions().stream().filter(groupPermission -> availableMenus.get(groupPermission.getUserMenu().getId()) == null).count();
//        return count == 0;
//    }
//
//    public void validateModifyUser(UserValidationDetail userValidationDetail) {
//        UpdateUserRequest updateUserRequest = userValidationDetail.getUpdateUserRequest();
//        ApplicationUser loggedInUser = userValidationDetail.getLoggedInUser();
//        ApplicationUser updateUser = userValidationDetail.getUpdateUser();
//
//        UserGroup newGroup = userGroupRepository.findById(updateUserRequest.getGroupId()).get();
//        UserGroup updateUserGroup = updateUser.getUserGroup();
//
//        //super admin can modify anything
//        if (loggedInUser.getUserGroup().getCode().equalsIgnoreCase(GroupConstants.SUPER_ADMIN))
//            return;
//
//        if(!isGroupPermissionsValid(loggedInUser.getUserGroup() , newGroup)){
//            throw new LimitedPrivilegeException("Logged In user doesn't have sufficient rights to add New Group");
//        }
//
//        if(!isGroupPermissionsValid(loggedInUser.getUserGroup() , updateUserGroup)) {
//            throw new LimitedPrivilegeException("Logged In user can't update a user with higher permission than it's own");
//        }
//    }
}
