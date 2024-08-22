package cl.api.apiuser.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConstantesUtil {

    public static final String DATETIME_FORMAT_DMY_HMS = "dd-MMM-yyyy HH:mm:ss";
    public static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
}
