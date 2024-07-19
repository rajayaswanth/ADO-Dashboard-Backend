package com.imagin.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfiguration {
	
	@Value("${ado.base.org.url}")
	private String adoOrgBaseUrl;
	
	@Bean(name = "orgBaseClient")
	WebClient adoOrgBaseUrlClient() {
		HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).baseUrl(adoOrgBaseUrl).build();
	}
	
}
