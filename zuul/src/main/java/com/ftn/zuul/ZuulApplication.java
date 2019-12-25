package com.ftn.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ftn.zuul.filter.ZuulPreFilter;


@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {

	


	public static void main(String[] args) {
		/*System.setProperty("KEY_STORE_CLASSPATH", "src/main/resources/zuul.jks");
		System.setProperty("KEY_STORE_PASSWORD", "password");
		System.setProperty("KEY_ALIAS", "zuul");
		System.setProperty("EUREKA_INSTANCE_HOSTNAME", "localhost");
		System.setProperty("CLIENT_SERVICEURL_DEFAULTZONE", "https://localhost:8761/eureka/");
		System.setProperty("TRUST_STORE_CLASSPATH", "zuul.jks");
		System.setProperty("TRUST_STORE_PASSWORD", "classpath:password");*/
		SpringApplication.run(ZuulApplication.class, args);
	}
	
	@Bean
    public ZuulPreFilter simpleFilter() {
      return new ZuulPreFilter();
    }

}
