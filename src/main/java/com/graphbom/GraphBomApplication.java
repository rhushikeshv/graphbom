package com.graphbom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EntityScan

public class GraphBomApplication {

	public static void main(String[] args) {
        SpringApplication.run(GraphBomApplication.class, args);
	}

}
