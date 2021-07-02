package com.rieske.micronaut;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends FrameworkTest {

    @Test
    void appStarts() {
        assertThat(app.isRunning()).isTrue();
        assertThat(app.getApplicationConfiguration().getName()).contains("my-app");
        assertThat(app.isServer()).isTrue();
    }
}
