package com.sep.bitcoin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class BitcoinController {

	@RequestMapping("/test")
	public String test() {
		return "bitcoin";
	}

}