package api.clients;

import api.pojo.keycloak.Token;
import retrofit2.Response;
import retrofit2.http.*;

public interface KeycloakTokenClient {

    //TODO find out from epam diff btw pass and secret
    @FormUrlEncoded
    @POST("auth/realms/{realm}/protocol/openid-connect/token")
    Response<Token> generateAccessTokenByPassword(
            @Path("realm") String realm,
            @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password,
            @Field("client_id") String clientId,
            @Field("scope") String scope);

    @FormUrlEncoded
    @POST("auth/realms/{realm}/protocol/openid-connect/token")
    Response<Token> generateAccessTokenBySecret(
            @Path("realm") String realm,
            @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password,
            @Field("client_secret") String secret,
            @Field("client_id") String clientId,
            @Field("scope") String scope);

    @FormUrlEncoded
    @POST("auth/realms/{realm}/protocol/openid-connect/token")
    Response<Token> refreshAccessToken(
            @Path("realm") String realm,
            @Field("grant_type") String grantType,
            @Field("refresh_token") String refreshToken,
            @Field("client_id") String clientId,
            @Field("scope") String scope);

}
