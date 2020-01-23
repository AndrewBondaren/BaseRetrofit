package api.retrofit;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Slf4j(topic = "API")
@NoArgsConstructor(staticName = "create")
public class DebugLoggingInterceptor implements Interceptor {

    private static final String BINARY = "(binary)";

    private static final String IMAGE_JPEG = "image/jpeg";

    private static final String FORM_DATA = "multipart/form-data";

    //TODO Refactor
    //CHECKSTYLE:OFF
    @Override
    public Response intercept(final Interceptor.Chain chain) throws IOException {
        final StringBuilder logInfo = new StringBuilder(System.lineSeparator());
        final Request request = chain.request();
        final String requestUrl = request.url().toString();
        final String body = "Body: ";

        logInfo.append("Request: ")
                .append(request.method())
                .append(' ')
                .append(requestUrl)
                .append(System.lineSeparator());
        if (request.headers().size() > 0) {
            logInfo.append(request.headers().toString());
        }

        final RequestBody requestBody = request.body();
        if (nonNull(requestBody) && requestBody.contentLength() > 0) {
            if (requireNonNull(requestBody.contentType()).toString().contains(IMAGE_JPEG) ||
                    requireNonNull(requestBody.contentType()).toString().contains(FORM_DATA)) {
                logInfo.append(body)
                        .append(BINARY)
                        .append(System.lineSeparator());
            } else {
                logInfo.append(body)
                        .append(readRequestBody(requestBody))
                        .append(System.lineSeparator());
            }

        }

        final Map<String, String> paramsMap = new HashMap<>();
        request.url().queryParameterNames().forEach(param -> paramsMap.put(param, request.url().queryParameter(param)));
        logInfo.append("Parameters: ")
                .append(paramsMap.toString())
                .append(System.lineSeparator());

        final Response response = chain.proceed(request);

        final Response.Builder responseBuilder = response.newBuilder();

        final ResponseBody responseBody = response.body();

        logInfo.append("Response code: ")
                .append(response.code())
                .append(System.lineSeparator());
        if (nonNull(responseBody)) {
            if (requireNonNull(responseBody.contentType()).toString().contains(IMAGE_JPEG) ||
                    requireNonNull(responseBody.contentType()).toString().contains(FORM_DATA)) {
                logInfo.append(body)
                        .append(BINARY)
                        .append(System.lineSeparator());
            } else {
                final byte[] bytes = responseBody.bytes();
                logInfo.append(body)
                        .append(StringEscapeUtils
                                .unescapeJson(new String(bytes, StandardCharsets.UTF_8)));
                responseBuilder.body(ResponseBody.create(responseBody.contentType(), bytes));
            }
        }

        log.info(logInfo.toString());

        return responseBuilder.build();
    }

    private static String readRequestBody(final RequestBody requestBody) throws IOException {
        final Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        return buffer.readString(StandardCharsets.UTF_8);
    }
}
