package cl.api.apiuser.util;

import lombok.NoArgsConstructor;
import java.util.regex.Pattern;

/**
 * Clase Utilitaria de validaciones personalizadas
 */
@NoArgsConstructor
public class ValidationUtil {

    /**
     * Validación del correo electrónico
     * @param email Cadena de caracteres que representa al correo electrónico
     */
    public static void emailValid(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email está vacio");
        }

        boolean regexEmail = Pattern.compile(ConstantesUtil.EMAIL_REGEX)
                .matcher(email)
                .matches();
        if (!regexEmail) {
            throw new IllegalArgumentException("El email ingresado no cumple con el formato correcto");
        }
    }

    /**
     * Validación de la contraseña
     * @param password Cadena de caracteres que representa a la contraseña del usuario
     */
    public static void passwordValid(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("El password es vacio");
        }

        boolean regexPass = Pattern.compile(ConstantesUtil.PASSWORD_REGEX)
                .matcher(password)
                .matches();
        if (!regexPass) {
            throw new IllegalArgumentException("El password ingresado no cumple con el formato correcto");
        }
    }


}
