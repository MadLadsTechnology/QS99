package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<QSUser, Long> {

    /*@Modifying
    @Query("")
    void changeRoleOfUserToAdmin(User user);*/
    //TODO: Add query for updating the users role

    QSUser getDistinctByEmailAddress(String email);

    void deleteByEmailAddress(String email);
}