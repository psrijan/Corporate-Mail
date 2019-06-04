package com.srijan.springfundamentals.mapper;

import com.srijan.springfundamentals.dto.request.user.AddUserRequest;
import com.srijan.springfundamentals.dto.request.user.UpdateUserRequest;
import com.srijan.springfundamentals.dto.server.ApplicationUserDetail;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.util.ObjectMapper;

public class UserMapper {

    public static ApplicationUser mapToNewUser(AddUserRequest addUserRequest, String encodedPassword) {
        ApplicationUser user = ObjectMapper.map(addUserRequest, ApplicationUser.class);
        user.setActive('Y');
//        user.setTotpEnabled('N');
        user.setPassword(encodedPassword);
//        user.setUserGroup(new UserGroup(addUserRequest.getGroupId()));
//        user.setDepartment(new Department(addUserRequest.getDepartmentId()));
        return user;
    }

    public static void mapToExistingUser(ApplicationUser applicationUser, UpdateUserRequest updateUserRequest) {
        ObjectMapper.mapExcludingNulls(updateUserRequest, applicationUser);
//        applicationUser.setUserGroup(new UserGroup(updateUserRequest.getGroupId()));
//        applicationUser.setDepartment(new Department(updateUserRequest.getDepartmentId()));
    }

    public static ApplicationUserDetail mapToUserDetail(ApplicationUser applicationUser) {
        ApplicationUserDetail applicationUserDetail = ObjectMapper.map(applicationUser, ApplicationUserDetail.class);
//        UserDepartmentDetail departmentDetail = ObjectMapper.map(applicationUser.getDepartment(), UserDepartmentDetail.class);
//        UserGroupResponse userGroupDetail = UserGroupMapper.createUserGroupResponse(applicationUser.getUserGroup());
//        departmentDetail.setActive(applicationUser.getDepartment().getActive() == 'Y');
        applicationUserDetail.setActive(applicationUser.getActive() == 'Y');
//        applicationUserDetail.setGroup(userGroupDetail);
//        applicationUserDetail.setDepartment(departmentDetail);
        return applicationUserDetail;
    }
}
