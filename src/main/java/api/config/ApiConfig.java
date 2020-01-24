package api.config;

import org.aeonbits.owner.Config;

//@Config.Sources({"classpath:${config}.properties"})
@Config.Sources({"classpath:dev.properties"})
public interface ApiConfig extends Config {

    //TODO refactor naming
    @Key("environment.url")
    String getEnvBaseUrl();

    @Key("environment.authservice.url")
    String getAuthBaseUrl();

    @Key("environment.mortgageservice.url")
    String getMortgageBaseUrl();

    @Key("rabbit.url")
    String getRabbitBaseUrl();

    @Key("rabbit.login")
    String getRabbitUser();

    @Key("rabbit.password")
    String getRabbitPassword();

    @Key("rabbit.vhost")
    String getRabbitHost();

    @Key("keycloak.url")
    String getKeycloakBaseUrl();

    @Key("keycloak.client.id")
    String getKeycloakClientId();

    @Key("keycloak.realm")
    String getKeycloakRealm();

    @Key("keycloak.user.default.id")
    String getKeycloakDefaultUserId();

    @Key("keycloak.user.default.username")
    String getKeycloakDefaultUser();

    @Key("keycloak.user.default.password")
    String getKeycloakDefaultUserPassword();

    @Key("keycloak.user.non_ahml_employee.id")
    String getKeycloakNoAhmlId();

    @Key("keycloak.user.non_ahml_employee.username")
    String getKeycloakNoAhmlUser();

    @Key("keycloak.user.non_ahml_employee.password")
    String getKeycloakNoAhmlPassword();

    @Key("keycloak.user.additional.id")
    String getKeycloakAdditionalId();

    @Key("keycloak.user.additional.username")
    String getKeyCloakAdditionalUser();

    @Key("keycloak.user.additional.password")
    String getKeycloakAdditionalPassword();

    @Key("keycloak.user.mortgage.id")
    String getKeycloakMortgageId();

    @Key("keycloak.user.mortgage.username")
    String getKeycloakMortgageUser();

    @Key("keycloak.user.mortgage.password")
    String getKeycloakMortgagePassword();

    @Key("login.url")
    String getLoginUrl();

    @Key("logout.url")
    String getLogoutUrl();

}