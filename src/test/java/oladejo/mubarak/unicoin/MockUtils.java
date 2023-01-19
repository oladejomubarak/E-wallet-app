package oladejo.mubarak.unicoin;

import oladejo.mubarak.unicoin.email.EmailSender;
import oladejo.mubarak.unicoin.registration.token.ConfirmationTokenRepository;
import oladejo.mubarak.unicoin.registration.token.ConfirmationTokenServices;
import oladejo.mubarak.unicoin.user.UserRepository;
import oladejo.mubarak.unicoin.user.UserServices;
import oladejo.mubarak.unicoin.user.UserServicesImpl;

import static org.mockito.Mockito.mock;

public class MockUtils {
    public static final UserRepository userRepositoryMock = mock(UserRepository.class);
    //private static final ConfirmationTokenRepository confirmationTokenRepositoryMock = mock(ConfirmationTokenRepository.class);
    public static final EmailSender emailSenderMock = mock(EmailSender.class);

    public static UserServicesImpl userServices(){
        return new UserServicesImpl(userRepositoryMock,
                confirmationTokenServicesMock());
    }

    public static ConfirmationTokenServices confirmationTokenServicesMock(){
        return new ConfirmationTokenServices();
    }


}
