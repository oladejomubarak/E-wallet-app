package oladejo.mubarak.unicoin.user;

import lombok.AllArgsConstructor;
import oladejo.mubarak.unicoin.registration.token.ConfirmationToken;
import oladejo.mubarak.unicoin.registration.token.ConfirmationTokenServices;
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
}
