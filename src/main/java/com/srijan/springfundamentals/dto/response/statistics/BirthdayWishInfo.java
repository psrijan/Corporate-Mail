package com.srijan.springfundamentals.dto.response.statistics;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BirthdayWishInfo extends ModelBase {

    private String name;
    private String birthday;
}
