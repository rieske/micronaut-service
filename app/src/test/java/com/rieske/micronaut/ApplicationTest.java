package com.rieske.micronaut;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class ApplicationTest {

    @Inject
    EmbeddedApplication<?> app;

    @Test
    void appStarts() {
        assertThat(app.isRunning()).isTrue();
        assertThat(app.getApplicationConfiguration().getName()).contains("my-app");
        assertThat(app.isServer()).isTrue();
    }
}
