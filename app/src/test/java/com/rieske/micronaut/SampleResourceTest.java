package com.rieske.micronaut;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

class SampleResourceTest extends FrameworkTest {

    @Inject
    @Client("/v1/sample")
    HttpClient client;

    @Test
    void nonExistingSampleYields404() {
        var exception = catchThrowableOfType(() -> get(1), HttpClientResponseException.class);
        assertThat(exception.getStatus().getCode()).isEqualTo(404);
    }

    @Test
    void addsAndRetrievesSample() {
        int id = 2;

        var createResponse = put(id, "foo");
        assertThat(createResponse.code()).isEqualTo(201);
        assertThat(createResponse.header("Location")).endsWith("/v1/sample/2");

        var fetchResponse = get(id);
        assertThat(fetchResponse.code()).isEqualTo(200);
        assertThat(fetchResponse.body()).isEqualTo("foo");
    }

    @Test
    void deletesExistingSample() {
        int id = 3;

        var createResponse = put(id, "foo");
        assertThat(createResponse.code()).isEqualTo(201);

        var deleteResponse = delete(id);
        assertThat(deleteResponse.code()).isEqualTo(202);

        var exception = catchThrowableOfType(() -> get(id), HttpClientResponseException.class);
        assertThat(exception.getStatus().getCode()).isEqualTo(404);
    }

    private HttpResponse<String> put(int id, String value) {
        return client.toBlocking().exchange(HttpRequest.PUT("/" + id, value).contentType(MediaType.TEXT_PLAIN_TYPE));
    }

    private HttpResponse<String> get(int id) {
        return client.toBlocking().exchange(HttpRequest.GET("/" + id).accept(MediaType.TEXT_PLAIN_TYPE), Argument.STRING);
    }

    private HttpResponse<String> delete(int id) {
        return client.toBlocking().exchange(HttpRequest.DELETE("/" + id));
    }
}
