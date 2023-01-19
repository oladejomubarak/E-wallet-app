package oladejo.mubarak.unicoin.email;

import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;


public interface EmailSender {
    void send(String to, String email) throws MessagingException;
}
