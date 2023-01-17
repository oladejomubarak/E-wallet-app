package oladejo.mubarak.unicoin.registration;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import oladejo.mubarak.unicoin.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {
    @Autowired
    RegistrationServices registrationServices;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest,
                                                HttpServletRequest httpServletRequest) throws MessagingException {
       ApiResponse apiResponse = ApiResponse.builder()
               .statusCode(HttpStatus.OK.value())
               .data(registrationServices.register(registrationRequest))
               .timeStamp(ZonedDateTime.now())
               .path(httpServletRequest.getRequestURI())
               .isSuccessful(true)
               .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}
