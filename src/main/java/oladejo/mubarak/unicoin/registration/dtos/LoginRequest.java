package oladejo.mubarak.unicoin.registration.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String emailAddress;
    private String password;
}
