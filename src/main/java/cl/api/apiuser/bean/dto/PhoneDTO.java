package cl.api.apiuser.bean.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneDTO {

    @JsonAlias("number")
    private String number;
    @JsonAlias("citycode")
    private String cityCode;
    @JsonAlias("contrycode")
    private String contryCode;
}
