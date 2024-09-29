package edu.wgu.d387_sample_code;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;


@SpringBootApplication
public class D387SampleCodeApplication {
	static ExecutorService messageExecutor = newFixedThreadPool(5);

	//list to store welcome messages
	private static final List<String> messages = new ArrayList<>();

	//list to store times and dates
	private static final List<String> presTimes = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(D387SampleCodeApplication.class, args);
		Properties prop = new Properties();
		messageExecutor.execute(() -> {
			try {
				InputStream stream = new ClassPathResource("welcome_en_US.properties").getInputStream();
				prop.load(stream);
				String welcome = prop.getProperty("welcome");
				messages.add(welcome);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		messageExecutor.execute(() -> {
			try {
				InputStream stream = new ClassPathResource("welcome_fr_CA.properties").getInputStream();
				prop.load(stream);
				String welcome = prop.getProperty("welcome");
				messages.add(welcome);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		ZoneId zEastern=ZoneId.of("America/New_York");
		ZoneId zUTC=ZoneId.of("UTC");
		ZoneId zMountain=ZoneId.of("America/Denver");
		ZoneId zoneId=ZoneId.systemDefault();

		//set local time for presentation
		LocalDateTime localDateTime=LocalDateTime.of(2024,12,10,12,30);

		//create formatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");


		ZonedDateTime zonedDateTime=localDateTime.atZone(zoneId);

		//convert to eastern
		ZonedDateTime zonedDateTimeEastern=zonedDateTime.withZoneSameInstant(zEastern);
		LocalDateTime localDateTimeEastern=zonedDateTimeEastern.toLocalDateTime();

		//format eastern time
		String EST = localDateTimeEastern.format(formatter);
		presTimes.add(EST);
		System.out.println("Eastern time "+EST);

		//convert to mountain
		ZonedDateTime zonedDateTimeMountain=zonedDateTime.withZoneSameInstant(zMountain);
		LocalDateTime localDateTimeMountain=zonedDateTimeMountain.toLocalDateTime();

		String MST = localDateTimeMountain.format(formatter);
		presTimes.add(MST);
		System.out.println("Mountain time "+MST);


		//convert to UTC
		ZonedDateTime zonedDateTimeUTC=zonedDateTime.withZoneSameInstant(zUTC);
		LocalDateTime localDateTimeUTC=zonedDateTimeUTC.toLocalDateTime();
		String UTC = localDateTimeUTC.format(formatter);
		presTimes.add(UTC);
		System.out.println("Universal Time "+ UTC);
	}

	public static List<String> getMessages() {
		return messages;
	}

	public static List<String> getPresTimes() {
		return presTimes;
	}
}




