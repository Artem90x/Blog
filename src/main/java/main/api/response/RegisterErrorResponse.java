package main.api.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterErrorResponse {

    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;
    @JsonProperty("password")
    private String password;
    @JsonProperty("captcha")
    private String captcha;

    public void setEmail() {
        this.email = "Этот e-mail уже зарегистрирован";
    }

    public void setName() {
        this.name = "Имя указано неверно, Имя может содержать буквы латинского алфавита, цифры или знак подчеркивания";
    }

    public void setPassword() {
        this.password = "Пароль короче 6-ти символов";
    }

    public void setCaptcha() {
        this.captcha = "Код с картинки введён неверно";
    }
}
