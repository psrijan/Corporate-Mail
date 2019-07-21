package com.srijan.springfundamentals.dto.response;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClientDetail extends ModelBase {
    private Long id;
    private String name;
    private String emailAddress;
    private char active;
    private Date birthday;
    private String relation;
    private Long profileId;
    private String profileName;
    private Long applicationUserId;
}
