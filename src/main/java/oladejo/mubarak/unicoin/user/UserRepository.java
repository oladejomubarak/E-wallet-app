package oladejo.mubarak.unicoin.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAddressIgnoreCase(String emailAddress);

    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.isDisabled = false  WHERE  user.emailAddress = ?1")
    void enable (String emailAddress);
}
