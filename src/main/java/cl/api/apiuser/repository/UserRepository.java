package cl.api.apiuser.repository;


import cl.api.apiuser.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Método que busca el usuario por el email y su contraseña
     * @param email Valor de cadena de caracteres que representa el email del usuario a buscar
     * @param password Valor de cadena de caracteres que representa la contraseña del usuario a buscar
     * @return Objeto entidad Usuario
     */
    User findByEmailAndPassword(String email, String password);
}
