package rbsoftskkm.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Токен авторизации пользователя. */
public class UserToken {
    @JsonProperty("tokenId")
    public String TokenId = "";

    @JsonProperty("expire")
    public String Expire = "";
}
