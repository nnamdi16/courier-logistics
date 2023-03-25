package com.logistics.courierLogistics.exceptions;

import com.logistics.courierLogistics.domain.response.AppResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {
    @ExceptionHandler(ModelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public AppResponse handleUserNotFoundException(final ModelNotFoundException ex) {
        log.error("Not found exception thrown");

        return AppResponse.builder()
                .data("")
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .error(ex.getMessage())
                .success(false)
                .build();
    }



    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AppResponse handleBadRequestException(final BadRequestException ex) {
        log.error("Bad request exception thrown");

        return AppResponse.builder()
                .data("")
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .error(ex.getMessage())
                .success(false)
                .build();
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public AppResponse handleThrowable(final Throwable ex) {
        log.error("Unexpected Error", ex);

        return AppResponse.builder()
                .data("")
                .status(500)
                .success(false)
                .message("An unexpected internal server error occurred")
                .build();
    }


    @ExceptionHandler(ModelAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public AppResponse handleModelAlreadyExistException(final ModelAlreadyExistException ex) {
        log.error("Model Already Exist exception thrown");

        return AppResponse.builder()
                .data("")
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .success(false)
                .build();
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public AppResponse catchForbiddenException(ForbiddenException exception) {
        String message = exception.getMessage();
        if (exception.getMessage().equals("")) {
            message = "You are not allowed to perform that action.";
        }
        return AppResponse
                .builder()
                .data("")
                .status(HttpStatus.FORBIDDEN.value())
                .message(message)
                .success(false)
                .build();

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AppResponse processValidationError(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();

        FieldError error = result.getFieldError();

        return processFieldError(Objects.requireNonNull(error));
    }





    private AppResponse processFieldError(FieldError error) {

        return AppResponse.builder()
                .error(error.getDefaultMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(error.getDefaultMessage())
                .build();
    }

}
