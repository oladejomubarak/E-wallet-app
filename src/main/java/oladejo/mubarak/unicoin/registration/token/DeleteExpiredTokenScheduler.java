package oladejo.mubarak.unicoin.registration.token;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class DeleteExpiredTokenScheduler {

    @Autowired
    private ConfirmationTokenServices confirmationTokenServices;
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteExpiredToken(){
        confirmationTokenServices.deleteExpiredToken();


    }
}
