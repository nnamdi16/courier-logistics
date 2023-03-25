package com.logistics.courierLogistics.services.impl;

import com.logistics.courierLogistics.domain.dto.UserDto;
import com.logistics.courierLogistics.domain.request.CreateUserRequestDto;
import com.logistics.courierLogistics.exceptions.ModelAlreadyExistException;
import com.logistics.courierLogistics.model.User;
import com.logistics.courierLogistics.repositories.UserRepository;
import com.logistics.courierLogistics.services.UserService;
import com.logistics.courierLogistics.services.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private  final UserRepository userRepository;
    private final UserUtil userUtil;
    private  final ModelMapper modelMapper;



    @Override
    public UserDto createUser(CreateUserRequestDto requestDto) {
        boolean emailExists = userRepository.checkIfEmailExists(requestDto.getEmail());
        if (emailExists) {
            throw new ModelAlreadyExistException("email", "Email Already exist", HttpStatus.BAD_REQUEST.toString());
        }
        User user = userUtil.buildUserEntity(requestDto);
        final var savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);

    }
}
