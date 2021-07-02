package com.rieske.micronaut;

import io.micronaut.context.annotation.Context;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Context
class SampleService {

    private final DataSource dataSource;

    private final Map<Integer, String> samples = new HashMap<>();

    SampleService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
