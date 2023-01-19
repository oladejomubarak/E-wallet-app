package oladejo.mubarak.unicoin.registration.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
}
