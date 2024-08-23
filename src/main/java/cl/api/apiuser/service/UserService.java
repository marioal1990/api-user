package cl.api.apiuser.service;

import cl.api.apiuser.bean.User;
import cl.api.apiuser.bean.dto.LoginControllerRequest;
import cl.api.apiuser.bean.dto.RegistroControllerRequest;
import cl.api.apiuser.bean.dto.UserControllerResponse;
import cl.api.apiuser.repository.UserRepository;
import cl.api.apiuser.util.ParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Método que registra un nuevo usuario
     * @param registroControllerRequest Objeto de entrada de petición desde el controlador
     * @return Objeto de salida que representa los datos del usuario nuevo
     */
    public UserControllerResponse registro(RegistroControllerRequest registroControllerRequest) {
        UserControllerResponse userControllerResponse = null;
        User user = this.userRepository.findByEmailAndPassword(registroControllerRequest.getEmail(), registroControllerRequest.getPassword());
        if (user != null) {
            throw new IllegalArgumentException("El correo ya registrado");
        } else {
            User newUser = ParseUtil.registroControllerRequestToUser(registroControllerRequest);
            User userCreated = this.userRepository.save(newUser);

            //RESPUESTA
            userControllerResponse = new UserControllerResponse();
            userControllerResponse.setUuid(userCreated.getUuid());
            userControllerResponse.setCreated(userCreated.getCreated());
            userControllerResponse.setModified(userCreated.getModified());
            userControllerResponse.setLastLogin(userCreated.getCreated());
            userControllerResponse.setActive(userCreated.isActive());
            return userControllerResponse;
        }
    }

    /**
     * Método que logea un usuario previamente creado
     * @param request Objeto de entrada de petición desde el controlador
     * @return Objeto de salida que representa los datos del usuario encontrado
     */
    public UserControllerResponse login(LoginControllerRequest request) {
        UserControllerResponse userControllerResponse = null;
        User user = this.userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (user != null) {
            //RESPUESTA
            userControllerResponse = new UserControllerResponse();
            userControllerResponse.setUuid(user.getUuid());
            userControllerResponse.setCreated(user.getCreated());
            userControllerResponse.setModified(user.getModified());
            userControllerResponse.setLastLogin(user.getLastLogin());
            userControllerResponse.setActive(user.isActive());
            userControllerResponse.setMensaje("Usuario logeado");
            return userControllerResponse;
        } else {
            throw new IllegalArgumentException("El usuario no existe");
        }
    }
}
