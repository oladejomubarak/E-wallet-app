package oladejo.mubarak.unicoin.user.dtos;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String sentToken;
    private String newPassword;
    private String confirmNewPassword;
}
