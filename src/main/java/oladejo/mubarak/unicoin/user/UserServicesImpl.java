package oladejo.mubarak.unicoin.user;

import lombok.AllArgsConstructor;
import oladejo.mubarak.unicoin.registration.token.ConfirmationToken;
import oladejo.mubarak.unicoin.registration.token.ConfirmationTokenServices;
import oladejo.mubarak.unicoin.user.dtos.ChangePasswordRequest;
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
        User foundUser = userRepository.findByEmailAddressIgnoreCase(changePasswordRequest.getEmailAddress())
                .orElseThrow(()-> new IllegalStateException("Email not found"));
        if(!changePasswordRequest.getOldPassword().equals(foundUser.getPassword()))
            throw new IllegalStateException("Incorrect password");
         foundUser.setPassword(changePasswordRequest.getNewPassword() != null && !changePasswordRequest.getNewPassword().equals("")
         ? changePasswordRequest.getNewPassword() : foundUser.getPassword());
        if (!foundUser.getPassword().equals(changePasswordRequest.getConfirmNewPassword()))
            throw new IllegalStateException("passwords do not match");
         return "password changed successfully";
    }
}
