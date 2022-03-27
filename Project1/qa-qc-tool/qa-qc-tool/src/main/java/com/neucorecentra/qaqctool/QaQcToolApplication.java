package com.neucorecentra.qaqctool;

import com.neucorecentra.qaqctool.model.Projects;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@SpringBootApplication
@RestController
public class QaQcToolApplication {
	Projects p = new Projects();

	public static void main(String[] args) {
		SpringApplication.run(QaQcToolApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}


	@GetMapping("/print")
	public List<Projects> printData(){
		List<Projects> data = new ArrayList<>();
		try {
			data =  p.readContent("qa-qc-tool/Projects_20220223_sample.xlsx");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
