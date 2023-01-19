package oladejo.mubarak.unicoin.registration.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Setter;

@Data
public class ConfirmTokenRequest {
    @NotNull
    private String token;
    @NotNull
    private String email;
}
