package oladejo.mubarak.unicoin.registration;

import oladejo.mubarak.unicoin.user.User;
import oladejo.mubarak.unicoin.user.UserRepository;
import oladejo.mubarak.unicoin.user.UserRole;
import oladejo.mubarak.unicoin.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServices userService;
   public String register(RegistrationRequest registrationRequest){
       boolean emailExists = userRepository.findByEmailAddressIgnoreCase
               (registrationRequest.getEmailAddress()).isPresent();
       if(emailExists)
           throw new IllegalStateException("Email Address already exists");
       return userService.createAccount(new User(
               registrationRequest.getEmailAddress(),
               registrationRequest.getFirstName(),
               registrationRequest.getLastName(),
               registrationRequest.getPassword(),
               UserRole.USER
       )
       );
    }
}
