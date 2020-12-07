package com.ncedu.cheetahtest.entity.user;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UserPaginatedDto {
    @NonNull private List<UserDto> users;

    @NonNull private int totalElements;

}
