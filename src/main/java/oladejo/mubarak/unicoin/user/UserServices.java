package oladejo.mubarak.unicoin.user;

public interface UserServices {
    public String createAccount(User user);
    public void enableUser(String emailAddress);
}
