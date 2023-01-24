package oladejo.mubarak.unicoin.user;

import oladejo.mubarak.unicoin.user.dtos.ChangePasswordRequest;
import oladejo.mubarak.unicoin.user.dtos.DeleteUserRequest;
import oladejo.mubarak.unicoin.user.dtos.ResetPasswordRequest;

import java.util.Optional;

public interface UserServices {
    public String createAccount(User user);
    public void enableUser(String emailAddress);
    public Optional<User> fndUserByEmail(String emailAddress);

    public String changePassword(ChangePasswordRequest changePasswordRequest);
    public String deleteUser(String email, DeleteUserRequest deleteUserRequest);

    public String forgotPasswordSendToken(String email, String token);
    public String forgotCPasswordConfirmToken(String token);

    public String resetPassword(ResetPasswordRequest resetPasswordRequest);
}
