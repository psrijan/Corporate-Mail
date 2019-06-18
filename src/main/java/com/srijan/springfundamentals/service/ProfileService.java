package com.srijan.springfundamentals.service;

import com.srijan.springfundamentals.dto.request.AddProfileRequest;
import com.srijan.springfundamentals.dto.response.FestivalGroupInfo;
import com.srijan.springfundamentals.dto.response.GenericResponse;
import com.srijan.springfundamentals.dto.response.ProfileDetail;
import com.srijan.springfundamentals.dto.response.ServiceInfo;
import com.srijan.springfundamentals.dto.server.UpdateProfileDetail;

import java.util.List;

public interface ProfileService {

    List<ProfileDetail> getProfileList();

    GenericResponse addNewProfile(AddProfileRequest addProfileRequest);

    GenericResponse updateExistingProfile(UpdateProfileDetail updateProfileDetail);

    GenericResponse deleteProfile(Long profileId);

    List<FestivalGroupInfo> getAllFestivalGroup();

    List<ServiceInfo> getAllServiceInfo();


}
