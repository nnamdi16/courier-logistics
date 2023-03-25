package com.logistics.courierLogistics.services.util;

import com.logistics.courierLogistics.domain.request.CreateUserRequestDto;
import com.logistics.courierLogistics.model.User;
import com.logistics.courierLogistics.model.util.AppConstant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {
    private final ModelMapper modelMapper;

    public User buildUserEntity(CreateUserRequestDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        user.setStatus(AppConstant.NEW_ENTITY);
        return user;

    }
}
