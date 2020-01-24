package api.steps;

import api.clients.KeycloakTokenClient;
import api.pojo.keycloak.TokenInput;
import com.google.inject.Inject;

// TODO
public class KeycloakSteps {

    private final KeycloakTokenClient keycloakTokenClient;

    @Inject
    public KeycloakSteps(final KeycloakTokenClient keycloakTokenClient) {
        this.keycloakTokenClient = keycloakTokenClient;
    }

    public String getToken(TokenInput tokenInput, String realm) {
        return keycloakTokenClient.generateAccessTokenBySecret(
                        realm,
                        tokenInput.getGrantType(),
                        tokenInput.getUserName(),
                        tokenInput.getPassword(),
                        tokenInput.getClientSecret(),
                        tokenInput.getClientId(),
                        tokenInput.getScope()
                ).body().getToken();
    }

    //TODO
    public String getToken(TokenInput tokenInput, String realm, String user, String password) {
        return
                keycloakTokenClient.generateAccessTokenBySecret(
                        realm,
                        tokenInput.getGrantType(),
                        user,
                        password,
                        tokenInput.getClientSecret(),
                        tokenInput.getClientId(),
                        tokenInput.getScope()
                ).body().getToken();
    }

}
