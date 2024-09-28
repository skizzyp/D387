package edu.wgu.d387_sample_code;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@SpringBootApplication
public class D387SampleCodeApplication {

	private final List<String> messages = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(D387SampleCodeApplication.class, args);
		Thread englishThread = new Thread(() -> displayWelcomeMessage("welcome_en_US.properties"));
		Thread frenchThread = new Thread(() -> displayWelcomeMessage("welcome_fr_CA.properties"));

		englishThread.start();
		frenchThread.start();

		try {
			englishThread.join();
			frenchThread.join();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void displayWelcomeMessage(String propFile) {
		Properties prop = new Properties();
		try {
			InputStream stream = new ClassPathResource(propFile).getInputStream();
			prop.load(stream);
			String welcome = prop.getProperty("welcome");
			System.out.println(welcome);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}
