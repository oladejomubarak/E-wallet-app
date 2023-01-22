package oladejo.mubarak.unicoin.registration;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import oladejo.mubarak.unicoin.registration.dtos.LoginRequest;
import oladejo.mubarak.unicoin.registration.dtos.RegistrationRequest;
import oladejo.mubarak.unicoin.registration.dtos.ConfirmTokenRequest;
import oladejo.mubarak.unicoin.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/confirm")
    public ResponseEntity<?> confirmToken(@RequestBody ConfirmTokenRequest confirmTokenRequest,
                                          HttpServletRequest httpServletRequest) throws MessagingException{
        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .data(registrationServices.confirmToken(confirmTokenRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

    }
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest,
                                   HttpServletRequest httpServletRequest) throws MessagingException{
        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .data(registrationServices.login(loginRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

    }
}
