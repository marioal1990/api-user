package cl.api.apiuser.controller;

import cl.api.apiuser.bean.dto.LoginControllerRequest;
import cl.api.apiuser.bean.dto.RegistroControllerRequest;
import cl.api.apiuser.bean.dto.UserControllerResponse;
import cl.api.apiuser.service.UserService;
import cl.api.apiuser.util.ValidationUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de los métodos HTTP del usuario
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserControllerResponse> login(@RequestBody @Valid LoginControllerRequest request) {
        log.info("/login loading requests: {}", request.toString());
        UserControllerResponse userControllerResponse;
        try {
            //validaciones personalizadas
            ValidationUtil.emailValid(request.getEmail());

            userControllerResponse = this.userService.login(request);
        } catch (Exception e) {
            userControllerResponse = new UserControllerResponse();
            userControllerResponse.setMensaje(e.getMessage());
        }
        return new ResponseEntity<>(userControllerResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Método HTTP que registra a un nuevo usuario
     * @param request Objeto que representa la entrada JSON para el registro del nuevo usuario
     * @return Objeto que representa el mensaje de salida en JSON
     */
    @RequestMapping(value = "/registro", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserControllerResponse> registro(@RequestBody @Valid RegistroControllerRequest request) {
        log.info("/registro loading requests: {}", request.toString());
        UserControllerResponse userControllerResponse;
        try {
            //validaciones personalizadas
            ValidationUtil.emailValid(request.getEmail());
            ValidationUtil.passwordValid(request.getPassword());

            userControllerResponse = this.userService.registro(request);
        } catch (Exception e) {
            userControllerResponse = new UserControllerResponse();
            userControllerResponse.setMensaje(e.getMessage());
        }
        return new ResponseEntity<>(userControllerResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Método HTTP que prueba el controlador
     * @return Objeto que representa el mensaje de salida en JSON
     */
    @RequestMapping(value = "/hearthbeat", method = RequestMethod.GET)
    public ResponseEntity<UserControllerResponse> hearthbeat() {
        log.info("/hearthbeat loading");
        UserControllerResponse userControllerResponse = new UserControllerResponse();
        userControllerResponse.setMensaje("Hearthbeat UserController OK");
        return new ResponseEntity<>(userControllerResponse, HttpStatus.OK);
    }
}
