package api.module;

import api.allure.AllureOkHttp3WithSteps;
import api.annotations.ThreadLocalScope;
import api.clients.KeycloakTokenClient;
import api.config.ApiConfig;
import api.retrofit.*;
import api.utils.CustomScopeUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import okhttp3.OkHttpClient;
import org.aeonbits.owner.ConfigFactory;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import api.retrofit.SSLSocketFactory;
import java.util.concurrent.TimeUnit;

public class ApiModule extends AbstractModule {

    @Override
    public void configure() {
        bindScope(ThreadLocalScope.class, CustomScopeUtils.THREAD_LOCAL);
    }

    //TODO instance?
    @Provides
    protected ApiConfig provideApiConfig() {
        return ConfigFactory.create(ApiConfig.class, System.getProperties());
    }

    @Provides
    protected OkHttpClient providesDefaultOkHttpClient(final ApiConfig config) {
        return new OkHttpClient.Builder()
                .addInterceptor(new AllureOkHttp3WithSteps())
                .addInterceptor(DebugLoggingInterceptor.create())
                .callTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true)
                .sslSocketFactory(SSLSocketFactory.getTrustAll(), new AllTrustManager())
                .build();
    }

//    @Provides
//    protected TestService providesTestClient(final ApiConfig config,
//                                                       final ObjectMapper mapper,
//                                                       final OkHttpClient client) {
//
//        final OkHttpClient Test = client.newBuilder()
//                .cookieJar(DefaultCookieJar.create())
//                .addInterceptor(TokenInterceptor.create(config))
//                .build();
//
//        final Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(DefaultCallAdapterFactory.create())
//                .addConverterFactory(JacksonConverterFactory.create(mapper))
//                .addConverterFactory(ToJsonStringConverterFactory.create(mapper))
//                .baseUrl(config.getBaseUrl())
//                .client(Test)
//                .build();
//        return retrofit.create(TestService.class);
//
//    }

    @Provides
    protected KeycloakTokenClient providesIKeycloakTokenClient(final ApiConfig config,
                                                               final ObjectMapper mapper,
                                                               final OkHttpClient client) {

        final OkHttpClient Test = client.newBuilder()
                .cookieJar(DefaultCookieJar.create())
                //.addInterceptor(TokenInterceptor.create(config))
                .build();

        final Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(DefaultCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addConverterFactory(ToJsonStringConverterFactory.create(mapper))
                .baseUrl(config.getEnvBaseUrl())
                .client(Test)
                .build();
        return retrofit.create(KeycloakTokenClient.class);
    }
}
