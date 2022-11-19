package com.ntt.data.reto.cliente.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class RestTemplateUtil {
    private RestTemplate restTemplate = getRestTemplate();

    @Bean
    private RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public <T> ResponseEntity<?> getList(String ruta) {
        log.info("getList");
        return restTemplate.exchange(ruta, HttpMethod.GET, null, new ParameterizedTypeReference<List<T>>() {
        });
    }

    public <T> ResponseEntity<?> get(String ruta) {
        log.info("get");
        return restTemplate.exchange(ruta, HttpMethod.GET, null, new ParameterizedTypeReference<T>() {
        });
    }

    public <T> ResponseEntity<?> post(String ruta, T body) {
        log.info("post");
        return restTemplate.exchange(ruta, HttpMethod.POST, getEntity(body), new ParameterizedTypeReference<T>() {
        });
    }

    public <T> ResponseEntity<?> put(String ruta, T body) {
        log.info("put");
        return restTemplate.exchange(ruta, HttpMethod.PUT, getEntity(body), new ParameterizedTypeReference<T>() {
        });
    }

    public <T> ResponseEntity<?> delete(String ruta) {
        log.info("get");
        return restTemplate.exchange(ruta, HttpMethod.DELETE, null, new ParameterizedTypeReference<T>() {
        });
    }

    private <T> HttpEntity<T> getEntity(T t) {
        return t == null ? null : new HttpEntity<T>(t);
    }
}
