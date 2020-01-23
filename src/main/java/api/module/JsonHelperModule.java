package api.module;

import api.utils.JsonHelper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;


public class JsonHelperModule extends AbstractModule {

    //TODO
    @Override
    protected void configure() {}

    //TODO settings
    @Provides
    public ObjectMapper providesDefaultObjectMapper() {
        return(new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        );
    }

    @Provides
    public JsonHelper providesJsonHelper(final ObjectMapper objectMapper) {
        return new JsonHelper(objectMapper);
    }

}
