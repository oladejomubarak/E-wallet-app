package oladejo.mubarak.unicoin.registration.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull
    private String emailAddress;
    @NotNull
    private String password;
}
