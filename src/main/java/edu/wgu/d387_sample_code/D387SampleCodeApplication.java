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

}





