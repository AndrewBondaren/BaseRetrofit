package api.retrofit;

import api.config.ApiConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

//TODO think if you need it
@RequiredArgsConstructor(staticName = "create")
public class TokenInterceptor implements Interceptor {

    @NonNull
    private final ApiConfig apiConfig;

    @Override
    //TODO
    public Response intercept(final Chain chain) throws IOException {
        final Request originalRequest = chain.request();

        final HttpUrl url = originalRequest.url()
                .newBuilder()
                .build();

        return chain.proceed(originalRequest.newBuilder().url(url).build());

    }

}
