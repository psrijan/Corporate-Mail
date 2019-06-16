package com.srijan.springfundamentals.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddProfileRequest {

    private String name;
    private String description;
    private String type;
    private List<Long> serviceIdList;
    private List<Long> festivalGroupIdList;

}
