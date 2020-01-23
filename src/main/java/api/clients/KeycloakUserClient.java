package api.clients;

import org.keycloak.representations.idm.UserRepresentation;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;

public interface KeycloakUserClient {

    @GET("auth/admin/realms/{realm}/users")
    Response<List<UserRepresentation>> readAll(
            @Path("realm") String realm);

    @GET("auth/admin/realms/{realm}/users/{id}")
    Response<UserRepresentation> read(
            @Path("realm") String realm,
            @Path("id") String userId);

    @POST("auth/admin/realms/{realm}/users")
    Response<Void> create(
            @Path("realm") String realm,
            @Body UserRepresentation user);

    @PUT("auth/admin/realms/{realm}/users/{id}")
    Response<Void> update(
            @Path("realm") String realm,
            @Path("id") String userId,
            @Body UserRepresentation user);

    @DELETE("auth/admin/realms/{realm}/users/{id}")
    Response<Void> delete(
            @Path("realm") String realm,
            @Path("id") String userId);

}
