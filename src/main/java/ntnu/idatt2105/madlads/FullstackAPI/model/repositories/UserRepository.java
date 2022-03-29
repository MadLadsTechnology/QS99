package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAddress(String email);
    User findById(long id);
}