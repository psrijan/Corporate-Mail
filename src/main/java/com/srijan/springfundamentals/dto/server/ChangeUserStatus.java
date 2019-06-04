package com.srijan.springfundamentals.dto.server;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeUserStatus extends ModelBase {
    private Long id;
    private boolean active;
}
