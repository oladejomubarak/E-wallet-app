package oladejo.mubarak.unicoin.registration.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenServices {
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getConfirmationToken(String token){
       return confirmationTokenRepository.findByToken(token);
    }
    public void deleteExpiredToken(){
        confirmationTokenRepository.deleteConfirmationTokensByExpiredAtBefore(LocalDateTime.now());
    }
    public void setConfirmedAt(String token){
        confirmationTokenRepository.setConfirmedAt(LocalDateTime.now(), token);
    }
}
