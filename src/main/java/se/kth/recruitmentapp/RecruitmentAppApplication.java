package se.kth.recruitmentapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.kth.recruitmentapp.repository.RoleRepository;

@SpringBootApplication
public class RecruitmentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentAppApplication.class, args);
	}


	@Bean
	public CommandLineRunner dataLoader(RoleRepository roleRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				System.out.println(roleRepository.findAll());
			}
		};
	}
}
