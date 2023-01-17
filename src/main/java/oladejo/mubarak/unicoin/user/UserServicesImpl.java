package oladejo.mubarak.unicoin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices{
    @Autowired
    private UserRepository userRepository;
    @Override
    public String createAccount(User user) {
        userRepository.save(user);
        return "created";

    }
}
