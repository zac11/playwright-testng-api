package playwright.restfulbooker.data;

public class TokenBuilder {

    public static TokenCredentials getToken(){
        return TokenCredentials.builder()
                .username("admin")
                .password("password123")
                .build();
    }
}
