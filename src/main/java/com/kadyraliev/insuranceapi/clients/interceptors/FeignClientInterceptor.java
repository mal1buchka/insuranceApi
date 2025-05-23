package com.kadyraliev.insuranceapi.clients.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Value("${insurance.token}")
    private String token;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}

