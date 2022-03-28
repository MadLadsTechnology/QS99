package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role extends User {
    private String roleName;
    @OneToMany
    private Collection<User> users;
}
