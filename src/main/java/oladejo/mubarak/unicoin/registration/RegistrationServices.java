package oladejo.mubarak.unicoin.registration;

import org.springframework.stereotype.Service;

@Service
public class RegistrationServices {
   public String register(RegistrationRequest registrationRequest){
       return "registered";
    }
}
