package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roleName;
    @OneToMany
    private Collection<User> users;

    public Long getId() {
        return id;
    }

}
