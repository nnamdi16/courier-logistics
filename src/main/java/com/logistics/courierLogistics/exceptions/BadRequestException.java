package com.logistics.courierLogistics.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@EqualsAndHashCode(callSuper = true)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message){super(message);}
}
