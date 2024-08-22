package cl.api.apiuser.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String created;
    private String modified;
    private String lastLogin;
    private boolean active;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "number")
    private List<Phone> phones;

}
