package com.inventorsoft.controllers;

import com.inventorsoft.model.Test;
import com.inventorsoft.service.TestService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/tests")
@CrossOrigin(origins = "*")
public class TestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class.getName());

	private TestService testService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getTests(){
		return ResponseEntity.ok(testService.getTestsNames());
	}

	@GetMapping("/{testName}") //:\w+
	public ResponseEntity<Test> getTestByName(@PathVariable String testName){
		LOGGER.info("Get request for test: " + testName);
		return testService.getOptionalWithTestBy(testName).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

}
