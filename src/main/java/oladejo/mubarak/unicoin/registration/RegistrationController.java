package oladejo.mubarak.unicoin.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {
    @Autowired
    RegistrationServices registrationServices;
    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest registrationRequest){
       return registrationServices.register(registrationRequest);
    }
}
