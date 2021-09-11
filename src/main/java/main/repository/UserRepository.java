package main.repository;

import main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u.email FROM User u WHERE u.email = :email")
    String findUserByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO users (email, name, reg_time, password, is_moderator) " +
                    "VALUES (:email, :name, :time, :password, 0)", nativeQuery = true)
    void insertUser(@Param("email") String email, @Param("name") String name, @Param("password") String password,
                    @Param("time") LocalDate time);
}
