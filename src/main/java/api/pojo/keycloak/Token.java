package api.pojo.keycloak;

import api.pojo.BaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Token extends BaseResponse {

    @SerializedName("access_token")
    private String token;

    @SerializedName("expires_in")
    private long expiresIn;

    @SerializedName("refresh_expires_in")
    private long refreshExpiresIn;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("id_token")
    private String idToken;

    @SerializedName("not-before-policy")
    private int notBeforePolicy;

    @SerializedName("session_state")
    private String sessionState;

}
