package oladejo.mubarak.unicoin.user;

import oladejo.mubarak.unicoin.user.dtos.ChangePasswordRequest;

import java.util.Optional;

public interface UserServices {
    public String createAccount(User user);
    public void enableUser(String emailAddress);
    public Optional<User> fndUserByEmail(String emailAddress);

    public String changePassword(ChangePasswordRequest changePasswordRequest);

}
