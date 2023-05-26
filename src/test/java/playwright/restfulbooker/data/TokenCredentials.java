package playwright.restfulbooker.data;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenCredentials {

    private String username;
    private String password;


}
