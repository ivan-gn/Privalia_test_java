package com.privalia.restcontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingRestController.class);
	private static final String template = "Hello3, %s!";
	
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		LOGGER.info("Info greeting method called");
		LOGGER.debug("Debug Greeting Method Called");
		return String.format(template, name);
	}
	
}
