package com.srijan.springfundamentals.service.impl;

import com.srijan.springfundamentals.dto.request.AddProfileRequest;
import com.srijan.springfundamentals.dto.response.FestivalGroupInfo;
import com.srijan.springfundamentals.dto.response.GenericResponse;
import com.srijan.springfundamentals.dto.response.ProfileDetail;
import com.srijan.springfundamentals.dto.response.ServiceInfo;
import com.srijan.springfundamentals.dto.server.UpdateProfileDetail;
import com.srijan.springfundamentals.entities.FestivalGroup;
import com.srijan.springfundamentals.entities.Profile;
import com.srijan.springfundamentals.mapper.ProfileMapper;
import com.srijan.springfundamentals.repository.FestivalGroupRepository;
import com.srijan.springfundamentals.repository.ProfileRepository;
import com.srijan.springfundamentals.repository.ServiceRepository;
import com.srijan.springfundamentals.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private FestivalGroupRepository festivalGroupRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<ProfileDetail> getProfileList() {
        List<Profile> profiles = profileRepository.getAllProfile();
        return ProfileMapper.mapToProfileDetailList(profiles);
    }

    @Override
    public GenericResponse addNewProfile(AddProfileRequest addProfileRequest) {
        Profile profile = ProfileMapper.mapToProfile(addProfileRequest);
        profileRepository.save(profile);
        return new GenericResponse.Builder(true , "Successfully Added New Profile").build();
    }

    @Override
    public GenericResponse updateExistingProfile(UpdateProfileDetail updateProfileDetail) {
        Profile profile = ProfileMapper.mapUpdateToProfile(updateProfileDetail);
        profileRepository.save(profile);
        return new GenericResponse.Builder<>(true , "Successfully Added New Profile").build();
    }

    @Override
    public GenericResponse deleteProfile(Long profileId) {
        Profile profile = new Profile(profileId);
        profile.setActive('D');
        profileRepository.save(profile);
        return new GenericResponse.Builder<>(true , "Successfully Deleted Profile").build();
    }

    @Override
    public List<FestivalGroupInfo> getAllFestivalGroup() {
        List<FestivalGroup> festivalGroupList = festivalGroupRepository.findAll();
        return ProfileMapper.mapToFestivalGroupInfoList(festivalGroupList);
    }

    @Override
    public List<ServiceInfo> getAllServiceInfo() {
        List<com.srijan.springfundamentals.entities.Service> serviceList =
                serviceRepository.getServiceList();
        return ProfileMapper.mapToServiceInfoList(serviceList);
    }


}
