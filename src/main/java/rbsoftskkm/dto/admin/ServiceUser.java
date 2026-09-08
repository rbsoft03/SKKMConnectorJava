package rbsoftskkm.dto.admin;

import com.fasterxml.jackson.annotation.JsonInclude;
import rbsoftskkm.dto.enums.*;

/**
 * Пользователь сервера ККМ:
 * Id - Идентификатор пользователя на сервере (нужен при изменении / удалении)
 * UserName - Логин для входа
 * FullName - Отображаемое ФИО / полное имя
 * Vatin - ИНН пользователя
 * Role - Роль. Используйте enum {@link ServiceUserRole}
 * TokenId - Идентификатор токена API (обычно приходит в ответе сервера)
 * Password - Пароль (указывайте при создании пользователя и смене пароля)
 */
public class ServiceUser {
    /** Идентификатор пользователя на сервере ККМ. Нужен при изменении и удалении. */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String Id;

    /** Логин для входа (Basic Auth / учётная запись сервера). */
    public String UserName = "";

    /** Полное имя пользователя (ФИО или отображаемое имя). */
    public String FullName = "";

    /** ИНН пользователя (при наличии). */
    public String Vatin = "";

    /** Роль пользователя. Используйте enum {@link ServiceUserRole}. */
    public ServiceUserRole Role;

    /** Идентификатор токена API. Обычно заполняется сервером в ответе. */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String TokenId;

    /** Пароль учётной записи. Указывайте при создании пользователя и при смене пароля. */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String Password;
}
