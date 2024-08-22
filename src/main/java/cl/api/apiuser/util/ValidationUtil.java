package cl.api.apiuser.util;

import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

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


}
