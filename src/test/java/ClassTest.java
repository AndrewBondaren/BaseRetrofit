import api.module.JsonHelperModule;
import api.pojo.keycloak.TokenInput;
import com.google.inject.Inject;
import name.falgout.jeffrey.testing.junit5.GuiceExtension;
import name.falgout.jeffrey.testing.junit5.IncludeModule;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(GuiceExtension.class)
@IncludeModule({api.module.ApiModule.class, JsonHelperModule.class})
public class ClassTest {

//    @Inject
//    private api.steps.KeycloakSteps keycloakSteps;
//
//    @Inject
//    private api.clients.KeycloakTokenClient keycloakTokenClient;

    @Inject
    private api.config.ApiConfig apiConfig;

    @Test
    public void methodTest() {
        System.out.println(apiConfig.getEnvBaseUrl());
//        keycloakTokenClient.generateAccessTokenBySecret("realm", "password", "ynkarpov1", "12345",
//                "06d56bc1-c667-41e6-9ab3-424f6abef7be", "epam", "openid");

//        System.out.println(keycloakSteps.getToken(TokenInput.builder().build(), "param"));
    }

}