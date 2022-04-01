package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<QSUser, Long> {
    QSUser findByEmailAddress(String email);

    /*@Modifying
    @Query("")
    void changeRoleOfUserToAdmin(User user);*/
    //TODO: Add query for updating the users role

    QSUser getDistinctByEmailAddress(String email);

    long deleteByEmailAddress(String email);

}