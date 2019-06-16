package com.srijan.springfundamentals.controller;

import com.srijan.springfundamentals.dto.request.AddProfileRequest;
import com.srijan.springfundamentals.dto.request.UpdateProfileRequest;
import com.srijan.springfundamentals.dto.response.GenericResponse;
import com.srijan.springfundamentals.dto.response.ProfileDetail;
import com.srijan.springfundamentals.dto.server.UpdateProfileDetail;
import com.srijan.springfundamentals.service.ProfileService;
import com.srijan.springfundamentals.util.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("profile")
@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProfileDetail> getProfileList() {
        log.info("Entering Get Profile List...");
        return profileService.getProfileList();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse addNewProfile(@Valid @RequestBody AddProfileRequest addProfileRequest) {
        log.info("Entering Add New Profile API...");
        GenericResponse genericResponse = profileService.addNewProfile(addProfileRequest);
        return genericResponse;
    }

    @PutMapping(path = "/{profileId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse updateProfile(@RequestBody @Valid UpdateProfileRequest updateProfileRequest, @PathVariable("profileId") Long profileId) {
        log.info("Entering Update Profile Api...");
        UpdateProfileDetail profileDetail = ObjectMapper.map(updateProfileRequest, UpdateProfileDetail.class);
        profileDetail.setId(profileId);
        GenericResponse response = profileService.updateExistingProfile(profileDetail);
        return response;
    }

    @DeleteMapping(path = "/{profileId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse deleteProfile(@PathVariable("profileId") Long profileId) {
        log.info("Entering Delete Api...");
        GenericResponse response = profileService.deleteProfile(profileId);
        return response;
    }
}
