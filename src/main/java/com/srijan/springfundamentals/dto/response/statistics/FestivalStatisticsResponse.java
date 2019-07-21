package com.srijan.springfundamentals.dto.response.statistics;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FestivalStatisticsResponse extends ModelBase {

    private List<FestivalStatisticsInfo> festivalStatisticsInfoList;
    private long count;

}
