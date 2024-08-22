package cl.api.apiuser.service;

import cl.api.apiuser.bean.User;
import cl.api.apiuser.bean.dto.RegistroControllerRequest;
import cl.api.apiuser.bean.dto.RegistroControllerResponse;
import cl.api.apiuser.repository.UserRepository;
import cl.api.apiuser.util.ParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RegistroControllerResponse registro(RegistroControllerRequest registroControllerRequest) {
        RegistroControllerResponse registroControllerResponse = null;
        User user = this.userRepository.findByEmailAndPassword(registroControllerRequest.getEmail(), registroControllerRequest.getPassword());
        if (user != null) {
            throw new IllegalArgumentException("El correo ya registrado");
        } else {
            User newUser = ParseUtil.registroControllerRequestToUser(registroControllerRequest);
            User userCreated = this.userRepository.save(newUser);

            //RESPUESTA
            registroControllerResponse = new RegistroControllerResponse();
            registroControllerResponse.setUuid(UUID.randomUUID().toString());
            registroControllerResponse.setCreated(userCreated.getCreated());
            registroControllerResponse.setModified(userCreated.getModified());
            registroControllerResponse.setLastLogin(userCreated.getCreated());
            registroControllerResponse.setActive(userCreated.isActive());
            return registroControllerResponse;
        }
    }
}
