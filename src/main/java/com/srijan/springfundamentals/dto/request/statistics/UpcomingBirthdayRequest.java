package com.srijan.springfundamentals.dto.request.statistics;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpcomingBirthdayRequest extends ModelBase {

    private WishType wishType;
}
