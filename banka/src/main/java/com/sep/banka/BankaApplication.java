package com.sep.banka;

import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;
import com.sep.banka.logger.LoggerWrite;

@EnableEurekaClient
@SpringBootApplication
public class BankaApplication {

	@Bean
	public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
		DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
		System.setProperty("javax.net.ssl.keyStore", "src/main/resources/banka.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/banka.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
		builder.withClientName("banka");
		builder.withSystemSSLConfiguration();
		builder.withMaxTotalConnections(10);
		builder.withMaxConnectionsPerHost(10);
		args.setEurekaJerseyClient(builder.build());
		return args;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BankaApplication.class, args);
		LoggerWrite.setup();
	}

}
