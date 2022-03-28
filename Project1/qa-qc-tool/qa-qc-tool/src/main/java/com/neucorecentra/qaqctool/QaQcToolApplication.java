package com.neucorecentra.qaqctool;

import Controller.ProjectController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;

@SpringBootApplication
@RestController
public class QaQcToolApplication {
	ProjectController p = new ProjectController();

	private static final String UPLOADED_FOLDER = "Project1/qa-qc-tool/qa-qc-tool/Upload";

	public static void main(String[] args) {
		SpringApplication.run(QaQcToolApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	//code for uploading "project" file
	@PostMapping("/uploadExcel")
	public void processUpload(@RequestParam("file")MultipartFile file) {
		if (file.isEmpty()) {
			//display error
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	@GetMapping("/print")
	public String printData(){
		List<String> data = new ArrayList<>();
		try {
			data =  p.readContent("Project1/qa-qc-tool/qa-qc-tool/Upload/Projects_20220223_sample.xlsx");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data.isEmpty()?"No errors detected in the file.":"Following warnings/Errors found.<br>"+data.toString();
	}

}
