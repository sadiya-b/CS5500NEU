package com.neucorecentra.qaqctool;

import Controller.ProjectController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;

@SpringBootApplication
@RestController
public class QaQcToolApplication {
	ProjectController p = new ProjectController();

	private static final String UPLOADED_FOLDER = "/Users/pragunsharma/Desktop/NorthEastern Classes/Spring 2022/CS 5500/QAQC/CS5500NEU/Project1/qa-qc-tool/qa-qc-tool/Upload/";
	private String fileName = "";
	public static void main(String[] args) {
		SpringApplication.run(QaQcToolApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	//code for uploading "project" file
	@PostMapping("/uploadExcel")
	public ModelAndView processUpload(@RequestParam("file")MultipartFile file) {
		if (file.isEmpty()) {
			System.out.println("Error: File is Empty.");
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			fileName = file.getOriginalFilename();
			Files.write(path, bytes);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		return new ModelAndView("redirect:/print.html");
	}

	@GetMapping("/print")
	public String printData() {
		List<String> data;
		try {
			data = p.readContent(Paths.get(UPLOADED_FOLDER + fileName).toString());
		} catch (IOException exception) {
			System.out.println(exception.getLocalizedMessage());
			return exception.getLocalizedMessage();
		}
		if (data.isEmpty()) {
			return "No errors detected in file. File Upload is successful.";
		} else {
			StringBuilder sb = new StringBuilder(data.toString());
			sb.deleteCharAt(0);
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
	}
}
