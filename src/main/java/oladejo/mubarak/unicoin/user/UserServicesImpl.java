package oladejo.mubarak.unicoin.user;

import lombok.AllArgsConstructor;
import oladejo.mubarak.unicoin.registration.token.ConfirmationToken;
import oladejo.mubarak.unicoin.registration.token.ConfirmationTokenServices;
import oladejo.mubarak.unicoin.user.dtos.ChangePasswordRequest;
import oladejo.mubarak.unicoin.user.dtos.DeleteUserRequest;
import oladejo.mubarak.unicoin.user.dtos.ResetPasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServicesImpl implements UserServices{
    @Autowired
    private UserRepository userRepository;
    @Autowired

    private ConfirmationTokenServices confirmationTokenServices;
    @Override
    public String createAccount(User user) {
        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                user

        );
        confirmationTokenServices.saveConfirmationToken(confirmationToken);
        return token;
    }

    @Override
    public void enableUser(String emailAddress) {
        userRepository.enable(emailAddress);
    }

    @Override
    public Optional<User> fndUserByEmail(String emailAddress) {
        return userRepository.findByEmailAddressIgnoreCase(emailAddress);
    }

    @Override
    public String changePassword(ChangePasswordRequest changePasswordRequest) {
       User foundUser = userRepository.findByEmailAddressIgnoreCase(changePasswordRequest.getEmailAddress()).get();
        if(!changePasswordRequest.getOldPassword().equals(foundUser.getPassword()))
            throw new IllegalStateException("Incorrect password");
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmNewPassword()))
            throw new IllegalStateException("passwords do not match");
        //foundUser.setPassword(changePasswordRequest.getOldPassword());
         foundUser.setPassword(changePasswordRequest.getNewPassword());
        //foundUser.setPassword(changePasswordRequest.getConfirmNewPassword());
        userRepository.save(foundUser);

         return "password changed successfully";
    }

    @Override
    public String deleteUser(String email, DeleteUserRequest deleteUserRequest) {
        User foundUser = userRepository.findByEmailAddressIgnoreCase(email).get();
        if(!foundUser.getPassword().equals(deleteUserRequest.getPassword()))
            throw new IllegalStateException("wrong password");
        String token = UUID.randomUUID().toString();
        String deleteEmail = "deleted"+foundUser.getEmailAddress()+token;
        foundUser.setPassword(deleteUserRequest.getPassword());
        foundUser.setEmailAddress(deleteEmail);
        userRepository.save(foundUser);
        return "user deleted successfully";
    }

    @Override
    public String forgotPasswordSendToken(String email, String token) {
        return null;
    }

    @Override
    public String forgotCPasswordConfirmToken(String token) {
        return null;
    }

    @Override
    public String resetPassword(ResetPasswordRequest resetPasswordRequest) {
        return null;
    }
}
