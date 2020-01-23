package api.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Утилитарные методы для работы с данными из keycloak
 *
 * @author Mikhail_Karaliou
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
//TODO review class
public class KeycloakDataUtils {

    public static UserRepresentation buildUserRepresentation(String username, String password, String secret, String keycloakId) {
        UserRepresentation result = new UserRepresentation();
        ArrayList<CredentialRepresentation> credentials = new ArrayList<>(Arrays.asList(getCredential(CredentialRepresentation.PASSWORD, password)));
        if (!StringUtils.isEmpty(secret)) {
            credentials.add(getCredential(CredentialRepresentation.SECRET, secret));
        }
        result.setUsername(username);
        result.setId(keycloakId);
        result.setCredentials(credentials);
        return result;
    }

    public static Pair<String, String> userToLoginPassword(UserRepresentation user) {
        return currentUserToPair(user,
                CredentialRepresentation::getValue,
                element -> StringUtils.equalsIgnoreCase(CredentialRepresentation.PASSWORD, element.getType()));
    }

    public static Pair<String, String> userToLoginSecret(UserRepresentation user) {
        return currentUserToPair(user,
                CredentialRepresentation::getValue,
                element -> StringUtils.equalsIgnoreCase(CredentialRepresentation.SECRET, element.getType()));
    }

    public static boolean hasSecret(UserRepresentation user) {
        return user.getCredentials().stream()
                .anyMatch(element -> StringUtils.equalsIgnoreCase(CredentialRepresentation.SECRET, element.getType()));
    }

    private static Pair<String, String> currentUserToPair(UserRepresentation user,
                                                          Function<? super CredentialRepresentation, String> mapper,
                                                          Predicate<? super CredentialRepresentation> filter) {
        String login = user.getUsername();
        String value = user.getCredentials().stream()
                .filter(filter)
                .map(mapper)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Активный пользователь не содержит искомого метода аутентификации"));
        return ImmutablePair.of(login, value);
    }

    private static CredentialRepresentation getCredential(String type, String value) {
        CredentialRepresentation result = new CredentialRepresentation();
        result.setType(type);
        result.setValue(value);
        return result;
    }
}
