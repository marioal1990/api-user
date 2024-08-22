package cl.api.apiuser.bean;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "phones")
@Data
public class Phone {

    @Id
    @Column(name = "number")
    private Long number;
    private String cityCode;
    private String contryCode;
}
