package com.srijan.springfundamentals.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileDetail {
    private String name;
    private String description;
    private List<FestivalGroupInfo> festivalInfoList;
    private List<ServiceInfo> serviceInfoList;
}
