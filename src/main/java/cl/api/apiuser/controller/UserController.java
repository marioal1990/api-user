package cl.api.apiuser.controller;

import cl.api.apiuser.bean.dto.RegistroControllerRequest;
import cl.api.apiuser.bean.dto.RegistroControllerResponse;
import cl.api.apiuser.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/registro", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistroControllerResponse> registro(@RequestBody @Valid RegistroControllerRequest request) {
        log.info("requests: {}", request.toString());
        RegistroControllerResponse registroControllerResponse;
        try {
            registroControllerResponse = this.userService.registro(request);
        } catch (Exception e) {
            registroControllerResponse = new RegistroControllerResponse();
            registroControllerResponse.setMensaje(e.getMessage());
        }
        return new ResponseEntity<>(registroControllerResponse, HttpStatus.BAD_REQUEST);
    }
}
