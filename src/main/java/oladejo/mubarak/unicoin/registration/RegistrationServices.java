package oladejo.mubarak.unicoin.registration;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import oladejo.mubarak.unicoin.email.EmailSender;
import oladejo.mubarak.unicoin.exceptions.registration.RegistrationException;
import oladejo.mubarak.unicoin.registration.dtos.ConfirmTokenRequest;
import oladejo.mubarak.unicoin.registration.dtos.LoginRequest;
import oladejo.mubarak.unicoin.registration.dtos.RegistrationRequest;
import oladejo.mubarak.unicoin.registration.token.ConfirmationToken;
import oladejo.mubarak.unicoin.registration.token.ConfirmationTokenServices;
import oladejo.mubarak.unicoin.user.User;
import oladejo.mubarak.unicoin.user.UserRepository;
import oladejo.mubarak.unicoin.user.UserRole;
import oladejo.mubarak.unicoin.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private ConfirmationTokenServices confirmationTokenServices;

    @Autowired
    private UserServices userService;
   public String register(RegistrationRequest registrationRequest) throws MessagingException {
       boolean emailExists = userRepository.findByEmailAddressIgnoreCase
               (registrationRequest.getEmailAddress()).isPresent();
       if(emailExists)
           throw new RegistrationException("Email Address already exists");
       String token = userService.createAccount(new User(
               registrationRequest.getEmailAddress(),
               registrationRequest.getFirstName(),
               registrationRequest.getLastName(),
               registrationRequest.getPassword(),
               UserRole.USER
       ));
       emailSender.send(registrationRequest.getEmailAddress(),
               buildEmail(registrationRequest.getFirstName(), token));
       return token;
    }
    public String confirmToken(ConfirmTokenRequest confirmTokenRequest){
        ConfirmationToken token = confirmationTokenServices.getConfirmationToken(confirmTokenRequest.getToken()).orElseThrow(()->
                new IllegalStateException("Token does not exist"));
        if(token.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token has expired");
        }
        if (token.getConfirmedAt() != null){
            throw new IllegalStateException("Token has been used");
        }
        confirmationTokenServices.setConfirmedAt(token.getToken());
        userService.enableUser(confirmTokenRequest.getEmail());
        return "confirmed";
    }
    public String login (LoginRequest loginRequest){
       var foundUser = userService.fndUserByEmail(loginRequest.getEmailAddress());
       if(foundUser.isEmpty()) throw new RegistrationException(String.format
               ("The email %s has not been registered with any account", loginRequest.getEmailAddress()));
       if(loginRequest.getPassword().equals(foundUser.get().getPassword())) foundUser.get().setPassword(loginRequest.getPassword());
           else
              throw new RegistrationException("Incorrect password");
           if (foundUser.get().getIsDisabled().equals(true)) throw new RuntimeException("Inactive user");
        return "You're successfully logged in";
    }


    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">" + link + "</p></blockquote>\n Link will expire in 10 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }


}
