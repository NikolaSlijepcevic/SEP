package com.sep.bitcoin;

import java.security.NoSuchAlgorithmException;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

@SpringBootApplication
public class BitcoinApplication {

	@Bean
	public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
		DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
		System.setProperty("javax.net.ssl.keyStore", "src/main/resources/bitcoin.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/bitcoin.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
		builder.withClientName("bitcoin");
		builder.withSystemSSLConfiguration();
		builder.withMaxTotalConnections(10);
		builder.withMaxConnectionsPerHost(10);
		args.setEurekaJerseyClient(builder.build());
		return args;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BitcoinApplication.class, args);
		
		        Wallet w= new Wallet();
	}

}
