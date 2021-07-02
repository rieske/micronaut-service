package com.rieske.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Put;

import java.net.URI;

@Controller(SampleResource.PATH)
class SampleResource {

    static final String PATH = "/v1/sample";

    private final SampleService sampleService;

    SampleResource(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @Put(uri = "/{id}", consumes = MediaType.TEXT_PLAIN)
    HttpResponse<?> addSample(@PathVariable int id, @Body String sample) {
        sampleService.addSample(id, sample);
        return HttpResponse.created(URI.create(PATH + "/" + id));
    }

    @Get(uri = "/{id}", produces = MediaType.TEXT_PLAIN)
    HttpResponse<String> getSample(@PathVariable int id) {
        return sampleService.getSample(id).map(HttpResponse::ok).orElseGet(HttpResponse::notFound);
    }

    @Delete(uri = "/{id}")
    HttpResponse<?> deleteSample(@PathVariable int id) {
        sampleService.deleteSample(id);
        return HttpResponse.accepted();
    }
}
