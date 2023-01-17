package oladejo.mubarak.unicoin.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ApiResponse {
    private ZonedDateTime timeStamp;
    private int statusCode;
    private String path;
    private Object data;
    private Boolean isSuccessful;
}
