package com.sep.nctest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NcTestApplication {

	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/test.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		SpringApplication.run(NcTestApplication.class, args);
	}

}
