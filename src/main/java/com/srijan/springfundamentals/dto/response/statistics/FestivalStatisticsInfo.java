package com.srijan.springfundamentals.dto.response.statistics;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FestivalStatisticsInfo extends ModelBase {

    private String festivalName;
    private String wishedPerson;
    private String wishDate;
}
