package cl.api.apiuser.controller;

import cl.api.apiuser.bean.dto.RegistroControllerRequest;
import cl.api.apiuser.bean.dto.RegistroControllerResponse;
import cl.api.apiuser.service.UserService;
import cl.api.apiuser.util.ValidationUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controlador de los métodos HTTP del usuario
 */
@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Método HTTP que registra a un nuevo usuario
     * @param request Objeto que representa la entrada JSON para el registro del nuevo usuario
     * @return Objeto que representa el mensaje de salida en JSON
     */
    @RequestMapping(value = "/registro", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistroControllerResponse> registro(@RequestBody @Valid RegistroControllerRequest request) {
        log.info("requests: {}", request.toString());
        RegistroControllerResponse registroControllerResponse;
        try {
            //validaciones personalizadas
            ValidationUtil.emailValid(request.getEmail());

            registroControllerResponse = this.userService.registro(request);
        } catch (Exception e) {
            registroControllerResponse = new RegistroControllerResponse();
            registroControllerResponse.setMensaje(e.getMessage());
        }
        return new ResponseEntity<>(registroControllerResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Método HTTP que prueba el controlador
     * @return Objeto que representa el mensaje de salida en JSON
     */
    @RequestMapping(value = "/hearthbeat", method = RequestMethod.GET)
    public ResponseEntity<RegistroControllerResponse> hearthbeat() {
        RegistroControllerResponse registroControllerResponse = new RegistroControllerResponse();
        registroControllerResponse.setUuid(UUID.randomUUID().toString());
        registroControllerResponse.setMensaje("Hearthbeat UserController OK");
        return new ResponseEntity<>(registroControllerResponse, HttpStatus.OK);
    }
}
