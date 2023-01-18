package oladejo.mubarak.unicoin.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class EmailServices implements EmailSender{
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void send(String to, String email) throws MessagingException {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, "Utf-8");
        mimeMessageHelper.setSubject("Confirm your email address");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setFrom("olade@gmail.com");
        mimeMessageHelper.setText(email, true);
        javaMailSender.send(mailMessage);
    }

}
