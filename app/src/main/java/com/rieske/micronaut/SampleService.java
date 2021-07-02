package com.rieske.micronaut;

import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
class SampleService {

    private final Map<Integer, String> samples = new HashMap<>();

    void addSample(int id, String value) {
        samples.put(id, value);
    }

    Optional<String> getSample(int id) {
        return Optional.ofNullable(samples.get(id));
    }

    void deleteSample(int id) {
        samples.remove(id);
    }
}
