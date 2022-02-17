package se.kth.recruitmentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


/**
 * Entry point of the spring application.
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RecruitmentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentAppApplication.class, args);
	}

}
