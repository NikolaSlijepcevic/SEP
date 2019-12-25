package com.sep.banka.controller;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sep.banka.logger.LoggerWrite;

@RequestMapping("/")
@RestController
public class BankaController {
	
	Logger logg= Logger.getLogger(LoggerWrite.class.getClass().getName());

	@RequestMapping("/test")
	public String test(){
		
		
		logg.info("NP_EVENT event " +this.getClass().getName()+ " " +new Date().toString());
		return "banka";
	}

}
