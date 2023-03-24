package com.logistics.courierLogistics.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppResponse {

    private boolean success;
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object error;
    private String message;
    private int status;
}
