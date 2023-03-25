package com.logistics.courierLogistics.services;

import com.logistics.courierLogistics.domain.dto.UserDto;
import com.logistics.courierLogistics.domain.request.CreateUserRequestDto;

public interface UserService {
    UserDto createUser(CreateUserRequestDto requestDto);
}
