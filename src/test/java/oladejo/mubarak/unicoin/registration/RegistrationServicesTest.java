package oladejo.mubarak.unicoin.registration;

import jakarta.mail.MessagingException;
import oladejo.mubarak.unicoin.MockUtils;
import oladejo.mubarak.unicoin.email.EmailSender;
import oladejo.mubarak.unicoin.registration.dtos.RegistrationRequest;
import oladejo.mubarak.unicoin.user.User;
import oladejo.mubarak.unicoin.user.UserRepository;
import oladejo.mubarak.unicoin.user.UserServices;
import oladejo.mubarak.unicoin.user.UserServicesImpl;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.MockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static oladejo.mubarak.unicoin.MockUtils.emailSenderMock;
import static oladejo.mubarak.unicoin.MockUtils.userRepositoryMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class RegistrationServicesTest{
    private static final UserServicesImpl userServicesMock = spy(MockUtils.userServices());


    private RegistrationServices registrationServices = new RegistrationServices(
            userRepositoryMock, emailSenderMock, MockUtils.confirmationTokenServicesMock(), userServicesMock
    );

    @Test
    public void testRegister() throws MessagingException {
        RegistrationRequest registrationRequest = new RegistrationRequest(
                "oladejo",
                "Mubarak",
                "oladejomubarakade@gmail.com",
                "Ade100"
        );
        doReturn("5c9c5b48-16a9-49e3-b639-a2a7b0108e5d")
                .when(userServicesMock).createAccount(any(User.class));

        String token = registrationServices.register(registrationRequest);
        assertEquals(token,"5c9c5b48-16a9-49e3-b639-a2a7b0108e5d" );
    }

}