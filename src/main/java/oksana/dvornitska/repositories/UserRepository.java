package oksana.dvornitska.repositories;

import oksana.dvornitska.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByName(String name);
    Optional<User> findUserById (Integer id);
}
