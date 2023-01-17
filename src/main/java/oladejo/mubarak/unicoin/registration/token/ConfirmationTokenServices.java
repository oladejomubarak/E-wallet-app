package oladejo.mubarak.unicoin.registration.token;

import org.springframework.beans.factory.annotation.Autowired;

public class ConfirmationTokenServices {
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    public void

    public void getConfirmationToken(String token){
        confirmationTokenRepository.findByToken(token);
    }
}
