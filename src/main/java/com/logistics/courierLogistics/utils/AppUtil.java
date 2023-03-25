package com.logistics.courierLogistics.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.courierLogistics.domain.response.AppResponse;
import com.logistics.courierLogistics.exceptions.BadRequestException;
import com.logistics.courierLogistics.model.util.ID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


import java.security.NoSuchAlgorithmException;

@Slf4j
public enum AppUtil {

    INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(AppUtil.class);

    AppUtil() {

    }

    public  static  boolean stringIsNullOrEmpty(String arg) {
        if ((arg == null)) return true;
        else
            return ("".equals(arg)) || (arg.trim().length() == 0);
    }

    public static void validatePageRequest(int page, int size) {
        LOGGER.info("about to validate  page request ");
        if(page < 1 || size <  1) {
            throw new BadRequestException("page and size must be positive and not less than 1");
        }
    }

    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static String generateKeys(String keyName) {
        String keySampleSpace = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return keyName + ID.generateRandomCharacters(32, keySampleSpace);

    }

    public static boolean validateEmail(String contactEmail) {

        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(contactEmail);
    }

    public static String generateReferenceNumber(String prefix){
        return prefix+"-"+System.currentTimeMillis();
    }

    public static String generateUserVerificationCode() {
        LOGGER.debug("generateUserVerificationCode");
        long userVerificationCodeInt = ID.generateLong();//ensure unique
        String userVerificationCodeStr = String.valueOf(userVerificationCodeInt);

        if (stringIsNullOrEmpty(userVerificationCodeStr)) {
            return generateUserVerificationCode();
        }
        if (userVerificationCodeStr.length() > 6) {
            userVerificationCodeStr = userVerificationCodeStr.substring(0, 6);
        }
        LOGGER.info("userVerificationCodeStr {}", userVerificationCodeStr);
        return userVerificationCodeStr;
    }

    public static AppResponse buildAppResponse(String message, boolean success, Object data, Object error,
                                               int statusCode) {

        return AppResponse.builder()
                .data(data)
                .error(error)
                .message(message)
                .success(success)
                .status(statusCode)
                .build();
    }

//    public static Mono<? extends StateDto> handleThirdPartyError(Throwable err, String stateId) {
//        log.error("Error {} ", err.getMessage());
//
//        log.error("Exception occurred fetching state details {}, Message {} and state id {}",
//                err.getClass().getSimpleName(), err.getLocalizedMessage(), stateId);
//
//        if (err instanceof WebClientResponseException) {
//            String responseBodyAsString = ((WebClientResponseException) err).getResponseBodyAsString();
//            log.info("State response Body as string {} ", responseBodyAsString);
//            Gson gson = new Gson();
//            AppResponse response = gson.fromJson(responseBodyAsString, AppResponse.class);
//            throw new ModelNotFoundException(response.getError().toString());
//
//        }
//        return Mono.empty();
//    }
//
//    public static Mono<? extends LGADto> handleLevelTwoThirdPartyError(Throwable err, String lgaId) {
//        log.error("Error {} ", err.getMessage());
//        log.error("Exception occurred when fetching LGA details {}, Message {} and LGA id {}",
//                err.getClass().getSimpleName(), err.getLocalizedMessage(), lgaId);
//        if (err instanceof WebClientResponseException) {
//            String responseBodyAsString = ((WebClientResponseException) err).getResponseBodyAsString();
//            log.info("LGA response Body as string {} ", responseBodyAsString);
//            Gson gson = new Gson();
//            AppResponse response = gson.fromJson(responseBodyAsString, AppResponse.class);
//            throw new ModelNotFoundException(response.getError().toString());
//        }
//        return Mono.empty();
//    }
}
