package oladejo.mubarak.unicoin.user.dtos;

import lombok.Data;

@Data
public class ChangePasswordRequest {

    private String emailAddress;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
