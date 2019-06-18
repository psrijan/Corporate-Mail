package com.srijan.springfundamentals.mapper;

import com.srijan.springfundamentals.dto.request.AddProfileRequest;
import com.srijan.springfundamentals.dto.response.FestivalGroupInfo;
import com.srijan.springfundamentals.dto.response.ProfileDetail;
import com.srijan.springfundamentals.dto.response.ServiceInfo;
import com.srijan.springfundamentals.dto.server.UpdateProfileDetail;
import com.srijan.springfundamentals.entities.FestivalGroup;
import com.srijan.springfundamentals.entities.Profile;
import com.srijan.springfundamentals.entities.Service;
import com.srijan.springfundamentals.util.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProfileMapper {

    public static Profile mapUpdateToProfile(UpdateProfileDetail updateProfileDetail) {
        Profile profile = new Profile();
        return null;
    }

    public static List<ProfileDetail> mapToProfileDetailList(List<Profile> profiles) {
        return profiles.stream().map(profile -> mapToProfileDetail(profile))
                .collect(Collectors.toList());
    }

    public static Profile mapToProfile(AddProfileRequest addProfileRequest) {

        Profile profile = ObjectMapper.map(addProfileRequest , Profile.class);
        profile.setActive('Y');

        List<FestivalGroup> festivalGroups=addProfileRequest.getFestivalGroupIdList().stream().map(aLong -> new FestivalGroup(aLong)).collect(Collectors.toList());
        List<Service> services=addProfileRequest.getServiceIdList().stream().map(aLong -> new Service(aLong)).collect(Collectors.toList());

        profile.setFestivalGroupList(festivalGroups);
        profile.setServiceList(services);

        return profile;
    }

    private static ProfileDetail mapToProfileDetail(Profile profile) {
        ProfileDetail profileDetail = new ProfileDetail();
        profileDetail.setName(profile.getName());
        profileDetail.setDescription(profile.getDescription());

        List<FestivalGroupInfo> groupInfos = mapToFestivalGroupInfoList(profile.getFestivalGroupList());
        profileDetail.setFestivalInfoList(groupInfos);

        List<ServiceInfo> serviceInfos = mapToServiceInfoList(profile.getServiceList());
        profileDetail.setServiceInfoList(serviceInfos);

        return profileDetail;
    }

    public static List<ServiceInfo> mapToServiceInfoList(List<Service> serviceList) {
        return serviceList.stream().map(service -> ObjectMapper.map(service , ServiceInfo.class)).collect(Collectors.toList());
    }

    public static List<FestivalGroupInfo> mapToFestivalGroupInfoList(List<FestivalGroup> festivalGroupList) {
        return festivalGroupList.stream().map(festivalGroup -> {
            FestivalGroupInfo festivalGroupInfo = new FestivalGroupInfo();
            festivalGroupInfo.setDescription(festivalGroup.getDescription());
            festivalGroupInfo.setName(festivalGroup.getName());
            festivalGroupInfo.setId(festivalGroup.getId());
            return festivalGroupInfo;
        }).collect(Collectors.toList());
    }

}
