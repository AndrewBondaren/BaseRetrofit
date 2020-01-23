package api.pojo.keycloak;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenInput {

    //TODO config
    @Builder.Default
    @SerializedName("grant_type")
    private String grantType = "password";

    @Builder.Default
    @SerializedName("username")
    private String userName = "ynkarpov1";

    @Builder.Default
    @SerializedName("password")
    private String password = "12345";

    @Builder.Default
    @SerializedName("client_secret")
    private String clientSecret = "06d56bc1-c667-41e6-9ab3-424f6abef7be";

    @Builder.Default
    @SerializedName("client_id")
    private String clientId = "apix";

    @Builder.Default
    private String scope = "openid";

}
