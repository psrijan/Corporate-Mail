package com.srijan.springfundamentals.dto.server;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationUserDetail extends ModelBase {
    private Long id;
    private String username;
    private String name;
    private String emailAddress;
    private boolean active;
}