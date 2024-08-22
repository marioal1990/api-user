package cl.api.apiuser.bean.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Objeto que representa el mensaje de salida del método registro para el controlador
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
