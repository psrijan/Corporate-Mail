package com.srijan.springfundamentals.dto.server;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateProfileDetail {

    private Long id;
    private String name;
    private String description;
    private String type;
    private List<Long> serviceIdList;
    private List<Long> festivalGroupIdList;

}
