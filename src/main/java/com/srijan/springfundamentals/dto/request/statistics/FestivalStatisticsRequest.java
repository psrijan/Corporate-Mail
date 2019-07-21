package com.srijan.springfundamentals.dto.request.statistics;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FestivalStatisticsRequest extends ModelBase {

    public WishType wishType;

}
