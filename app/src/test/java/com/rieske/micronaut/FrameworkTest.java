package com.rieske.micronaut;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest(propertySources = "classpath:test.properties")
class FrameworkTest {

    @Inject
    EmbeddedServer app;
}
