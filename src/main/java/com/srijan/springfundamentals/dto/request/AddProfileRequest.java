package com.srijan.springfundamentals.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class AddProfileRequest {

    @NotEmpty
    private String name;
    private String description;
    @NotEmpty
    private String type;
    @NotEmpty
    private List<@NotNull Long> serviceIdList;
    @NotEmpty
    private List<@NotNull Long> festivalGroupIdList;

}
