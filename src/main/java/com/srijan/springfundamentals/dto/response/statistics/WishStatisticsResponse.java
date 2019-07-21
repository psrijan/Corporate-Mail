package com.srijan.springfundamentals.dto.response.statistics;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WishStatisticsResponse extends ModelBase {

    private List<BirthdayWishInfo> wishInfoList;
    private long count;
}
