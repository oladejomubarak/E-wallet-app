package oladejo.mubarak.unicoin.user;

import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices{
    @Override
    public String createAccount(User user) {
        return "registered";

    }
}
