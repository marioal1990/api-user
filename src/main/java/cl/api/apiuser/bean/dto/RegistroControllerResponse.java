package cl.api.apiuser.bean.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistroControllerResponse {

    private String uuid;
    private String created;
    private String modified;
    @JsonAlias("last_login")
    private String lastLogin;
    private String token;
    @JsonAlias("isactive")
    private boolean isActive;

    private String mensaje;
}
