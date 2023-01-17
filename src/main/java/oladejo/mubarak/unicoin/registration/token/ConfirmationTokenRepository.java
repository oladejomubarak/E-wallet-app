package oladejo.mubarak.unicoin.registration.token;

import org.apache.el.parser.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<Token, Long> {
    Optional<ConfirmationToken> findByToken(String token);
}
