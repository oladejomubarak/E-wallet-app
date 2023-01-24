package oladejo.mubarak.unicoin.user;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import oladejo.mubarak.unicoin.user.dtos.ChangePasswordRequest;
import oladejo.mubarak.unicoin.user.dtos.DeleteUserRequest;
import oladejo.mubarak.unicoin.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    @Autowired
    private UserServices userServices;
    @PatchMapping("/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                                            HttpServletRequest httpServletRequest) throws MessagingException {
        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .data(userServices.changePassword(changePasswordRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
    @PutMapping("/deleteuser/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email, @RequestBody DeleteUserRequest deleteUserRequest,
                                            HttpServletRequest httpServletRequest) throws MessagingException {
        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .data(userServices.deleteUser(email, deleteUserRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}
