package oladejo.mubarak.unicoin.user;

import oladejo.mubarak.unicoin.registration.token.ConfirmationToken;
import oladejo.mubarak.unicoin.registration.token.ConfirmationTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
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
}
