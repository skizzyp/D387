package edu.wgu.d387_sample_code;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

import java.util.Properties;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;


@SpringBootApplication
public class D387SampleCodeApplication {
	static ExecutorService messageExecutor = newFixedThreadPool(5);

	public static void main(String[] args) {
		SpringApplication.run(D387SampleCodeApplication.class, args);
		Properties prop = new Properties();
		messageExecutor.execute(() -> {
			try {
				InputStream stream = new ClassPathResource("welcome_en_US.properties").getInputStream();
				prop.load(stream);
				String welcome = prop.getProperty("welcome");
				System.out.println(welcome);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		messageExecutor.execute(() -> {
			try {
				InputStream stream = new ClassPathResource("welcome_fr_CA.properties").getInputStream();
				prop.load(stream);
				String welcome = prop.getProperty("welcome");
				System.out.println(welcome);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}





